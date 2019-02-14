package client;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URISyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import moteur.Moteur;

public class Client {

    private Socket connexion;
    private int id;
    final Object attenteDéconnexion = new Object();

    public Client(int id, String adresse, int port) {
        this.id = id;
        String urlAdresse = "http://" + adresse + ":" + port;

        Moteur.log("Hello therer");

        try {
            connexion = IO.socket(urlAdresse);
            Moteur.log("Client: Abonnement connexion Joueur "+this.id);

            connexion.on("connect", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Moteur.log("Client: connexion Joueur " + id);
                    JSONObject myId = new JSONObject(id);
                    connexion.emit("identification", myId);
                }
            });

            connexion.on("disconnect", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    connexion.disconnect();
                    connexion.close();

                    synchronized (attenteDéconnexion) {
                        attenteDéconnexion.notify();
                        Moteur.log("Client: déconnexion Joueur " + id);
                    }
                }
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Moteur.log("Client: Crash dans Joueur " + this.id);
        }
    }

    public void démarrer() {
        connexion.connect();

        Moteur.log("Client: en attente de déconnexion Joueur "+this.id);
        synchronized (attenteDéconnexion) {
            try {
                attenteDéconnexion.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Moteur.log("Client: erreur dans l'attente Joueur " + this.id);
            }
        }
    }

    public final static void main(String args[]){

        if(args.length == 3){
            String adresse = args[0];
            int port = Integer.parseInt(args[1]);
            int id = Integer.parseInt(args[2]);
            Client c = new Client(id, adresse, port);
            c.démarrer();
        }
    }
}