package client;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URISyntaxException;
import java.util.Random;
import java.util.ArrayList;

import moteur.Jeu;
import moteur.Carte;
import moteur.action.Action;
import static moteur.jsonParser.JSONParser.*;
import org.json.JSONObject;

public class Client {

    private Socket connexion;
    private int id;
    Strategie stratClient = null;
    private Action actionAJouer;

    public Client(final int id, String adresse, int port) {
        this.id = id;
        String urlAdresse = "http://" + adresse + ":" + port;

        Random r=new Random();
        if(r.nextInt(2)==0) stratClient=new StratMax();
        else stratClient=new StratRandom();

        try {
            connexion = IO.socket(urlAdresse);
        } catch (URISyntaxException e) {
            Jeu.error("Client: Crash dans Joueur " + this.id, e);
            return;
        }
        Jeu.log("Client: Abonnement connexion Joueur " + this.id);

        connexion.on("connect", new Emitter.Listener() {
            @Override
            public final void call(Object... args) {
                Jeu.log("Client: connexion Joueur " + id);
                connexion.emit("rejoindre jeu", id);
            }
        });

        connexion.on("getCarte" + id, new Emitter.Listener() {
            @Override
            public final void call(Object... args) {
                Jeu.log("Réception carte ! " + id);
                ArrayList<Carte> deck = JSONToDeck(args[0]);
                if(deck != null){
                    actionAJouer = (Action) stratClient.getAction(id, deck);
                    connexion.emit("recuCarte", id);
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