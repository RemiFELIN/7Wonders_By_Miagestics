package client;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URISyntaxException;
import java.util.Random;
import java.util.ArrayList;

import static moteur.Jeu.log;
import static moteur.Jeu.error;
import moteur.*;
import moteur.action.Action;

import client.strategie.*;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class Client {

    private Socket connexion;
    private int id;
    private Strategie stratClient = null;
    private Action actionAJouer;

    public Client(final int id, String adresse, int port) {
        this.id = id;
        String urlAdresse = "http://" + adresse + ":" + port;

        Strategie[] mesStrat = new Strategie[]{new StratLaurier(), new StratRandom(), new StratRessources(), new StratMilitaire()};
        stratClient = mesStrat[new Random().nextInt(mesStrat.length)];

        try {
            connexion = IO.socket(urlAdresse);
        } catch (URISyntaxException e) {
            error("Client: Crash dans Joueur " + this.id, e);
            return;
        }
        log("Client: Abonnement connexion Joueur " + this.id);

        connexion.on("connect", new Emitter.Listener() {
            @Override
            public final void call(Object... args) {
                log("Client: connexion Joueur " + id);
                connexion.emit("rejoindre jeu", id);
            }
        });

        connexion.on("getVision" + id, new Emitter.Listener() {
            @Override
            public final void call(Object... args) {
                log("Le client " + id + " a reçu sa vision de jeu");
                try {
                    JSONObject jo = (JSONObject) args[0];
                    
                    VisionJeu j = new VisionJeu(
                        jo.getInt("id"),
                        jo.getInt("piece"),
                        parseJSONMerveille(jo.getJSONObject("plateau")),
                        JSONParser.parseJSONArray(jo.getJSONArray("deckMain")),
                        JSONParser.parseJSONArray(jo.getJSONArray("deckPlateau"))
                    );

                    VisionJeu g = new VisionJeu(
                        jo.getInt("voisinGaucheId"),
                        jo.getInt("voisinGauchePiece"),
                        parseJSONMerveille(jo.getJSONObject("plateau")),
                        JSONParser.parseJSONArray(jo.getJSONArray("voisinGaucheDeckPlateau"))
                    );
                    j.setVoisinGauche(g);
                    
                    VisionJeu d = new VisionJeu(
                        jo.getInt("voisinDroiteId"),
                        jo.getInt("voisinDroitePiece"),
                        parseJSONMerveille(jo.getJSONObject("plateau")),
                        JSONParser.parseJSONArray(jo.getJSONArray("voisinDroiteDeckPlateau"))
                    );
                    j.setVoisinDroite(d);

                    actionAJouer = stratClient.getAction(id, j.getDeckMain());
                    connexion.emit("recuCarte", id);

                } catch (JSONException e){
                    //error("Client "+id+" erreur getVision !", e);
                    e.printStackTrace();
                }
            }
        });

        connexion.on("debutTour", new Emitter.Listener(){
            @Override
            public final void call(Object... args) {
                connexion.emit("jouerCarte", new JSONObject(actionAJouer));
            }
        });

        connexion.on("disconnect", new Emitter.Listener() {
            @Override
            public final void call(Object... args) {
                connexion.disconnect();
                connexion.close();
            }
        });
    }

    private final Merveille parseJSONMerveille(JSONObject jm){
        Merveille m = new Merveille(jm.getString("nom"), (jm.getString("face")).charAt(0), Ressource.fromString(jm.getString("ressource")));

        if(jm.isNull("coutEtape") == false)
            m.setCoupEtape(JSONParser.parseJSONRessource(jm.getJSONArray("coutEtape")));
        if(jm.isNull("bonusEtapeRes") == false)
            m.setBonusEtapeRes(JSONParser.parseJSONRessource(jm.getJSONArray("bonusEtapeRes")));
        if(jm.isNull("bonusEtapeMilitaire") == false)
            m.setBonusEtapeMilitaire(JSONParser.parseJSONArrayInt(jm.getJSONArray("bonusEtapeMilitaire")));
        if(jm.isNull("bonusEtapePiece") == false)
            m.setBonusEtapePiece(JSONParser.parseJSONArrayInt(jm.getJSONArray("bonusEtapePiece")));
        if(jm.isNull("bonusEtapeEffect") == false)
            m.setBonusEtapeEffect(JSONParser.parseJSONArrayString(jm.getJSONArray("bonusEtapeEffect")));

        return m;
    }

    public final void démarrer() {
        connexion.connect();
    }

    public final static void main(String args[]) {

        if (args.length == 3) {
            String adresse = args[0];
            int port = Integer.parseInt(args[1]);
            int id = Integer.parseInt(args[2]);
            Client c = new Client(id, adresse, port);
            c.démarrer();
        }
    }
}