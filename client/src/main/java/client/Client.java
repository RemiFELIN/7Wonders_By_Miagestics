package client;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URISyntaxException;
import java.util.Random;

import client.strategie.*;
import static client.JSONParser.*;

import commun.VisionJeu;
import commun.Action;
import static commun.ConsoleLogger.*;
import static commun.EventConnection.*;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * @authors Pierre Saunders, Yannick Cardini, Benoît Montorsi, Rémi Felin
 */
public class Client {

    private Socket connexion;
    private int id;
    private Strategie stratClient = null;
    private Action actionAJouer;
    private boolean isLog = true;

    /**
     * Constructeur concu pour le lanceur Strat
     * @param id
     * @param adresse
     * @param port
     * @param strat
     */
    public Client(int id, String adresse, int port, Strategie strat){
        stratClient = strat;
        initClient(id, adresse, port);
        isLog = false;
    }

    //getter concu pour le lanceur Strat
    public String getStrategie(){
        return stratClient + "";
    }

    /**
     * Constructeur concu pour le lanceur partie
     * @param id id
     * @param adresse l'adresse du serveur
     * @param port port du serveur
     */
    public Client(int id, String adresse, int port) {
        Strategie[] mesStrat = new Strategie[] { new StratPointVictoire(true), new StratRandom(true), new StratRessources(true), new StratMilitaire(true), new StratScientifique(true) };
        stratClient = mesStrat[new Random().nextInt(mesStrat.length)];
        initClient(id, adresse, port);
    }

    private final void initClient(final int id, String adresse, int port){
        this.id = id;
        String urlAdresse = "http://" + adresse + ":" + port;


        try {
            connexion = IO.socket(urlAdresse);
        } catch (URISyntaxException e) {
            error("Client: Crash dans Joueur " + this.id, e);
            return;
        }
        if(isLog)
            log(BLUE_BOLD_BRIGHT + "Client: Abonnement connexion Joueur " + this.id);

        connexion.on(CONNEXION, new Emitter.Listener() {
            @Override
            public final void call(Object... args) {
                if(isLog)
                    log(BLUE_BOLD_BRIGHT + "Client: connexion Joueur " + id);
                connexion.emit(REJOINDRE_JEU, id);
            }
        });

        connexion.on(GET_VISIONJEU(id), new Emitter.Listener() {
            @Override
            public final void call(Object... args) {
                if(isLog)
                    log(BLUE_BOLD_BRIGHT + "Le client " + id + " a reçu sa vision de jeu");
                try {
                    JSONObject jo = (JSONObject) args[0];

                    VisionJeu j = new VisionJeu(jo.getInt("id"), jo.getInt("piece"), parseJSONArrayInt(jo.getJSONArray("jetonsVictoire")), jo.getInt("jetonsDefaite"),
                            parseJSONMerveille(jo.getJSONObject("plateau")), parseJSONArrayCarte(jo.getJSONArray("deckMain")), parseJSONArrayCarte(jo.getJSONArray("deckPlateau")));

                    VisionJeu g = new VisionJeu(jo.getInt("voisinGaucheId"), jo.getInt("voisinGauchePiece"), parseJSONArrayInt(jo.getJSONArray("voisinGaucheJetonsVictoire")),
                            jo.getInt("voisinGaucheJetonsDefaite"), parseJSONMerveille(jo.getJSONObject("plateau")), parseJSONArrayCarte(jo.getJSONArray("voisinGaucheDeckPlateau")));
                    j.setVoisinGauche(g);

                    VisionJeu d = new VisionJeu(jo.getInt("voisinDroiteId"), jo.getInt("voisinDroitePiece"), parseJSONArrayInt(jo.getJSONArray("voisinDroiteJetonsVictoire")),
                            jo.getInt("voisinDroiteJetonsDefaite"), parseJSONMerveille(jo.getJSONObject("plateau")), parseJSONArrayCarte(jo.getJSONArray("voisinDroiteDeckPlateau")));
                    j.setVoisinDroite(d);

                    actionAJouer = stratClient.getAction(j);
                    connexion.emit(RECU_CARTE, id);

                } catch (JSONException e) {
                    error("Client " + id + " erreur getVision !", e);
                }
            }
        });

        connexion.on(DEBUT_TOUR, new Emitter.Listener() {
            @Override
            public final void call(Object... args) {
                connexion.emit(JOUER_ACTION, new JSONObject(actionAJouer));
            }
        });

        connexion.on(FIN_JEU(id), new Emitter.Listener() {
            @Override
            public final void call(Object... args) {
                JSONArray info = (JSONArray) args[0];
                if(isLog)
                    log(BLUE_BOLD_BRIGHT + "Le client " + id + " est à la place " + info.getInt(0) + " avec " + info.get(1) + " de score");
            }
        });

        connexion.on(DECONNEXION, new Emitter.Listener() {
            @Override
            public final void call(Object... args) {
                if(isLog)
                    log(BLUE_BOLD_BRIGHT + "Le client " + id + " est déconnecté ");
                // connexion.off();
                // connexion.disconnect();
                connexion.close();
            }
        });
    }

    /**
     * Permet de débuter la connexion au serveur
     */
    public final void démarrer() {
        connexion.connect();
    }
}