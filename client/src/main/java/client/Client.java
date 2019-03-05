package client;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URISyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import moteur.Jeu;
import moteur.Coup;

public class Client {

    private Socket connexion;
    private int id;
    private final Object attenteDéconnexion = new Object();
    private Coup coupAJouer;

    public Client(final int id, String adresse, int port) {
        this.id = id;
        String urlAdresse = "http://" + adresse + ":" + port;

        try {
            connexion = IO.socket(urlAdresse);
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
                    int carteN = -1, carteValue = -1;
                    try {
                        JSONArray jo = (JSONArray) args[0];
                        for (int i = 0; i < args.length; i++){
                            int value = jo.getJSONObject(i).getInt("laurier");
                            if(value > carteValue){
                                carteValue = value;
                                carteN = i;
                            }
                        }
                        coupAJouer = new Coup(id, carteN);

                    } catch (JSONException e){
                        Jeu.error("Error JSON !", e);
                    }
                	connexion.emit("recuCarte", id);
                    
                }
            });

            connexion.on("debutTour", new Emitter.Listener(){
                
	           @Override
                public final void call(Object... args) {
                    
                	connexion.emit("jouerCarte", new JSONObject(coupAJouer));
                    
                }
            });

            connexion.on("disconnect", new Emitter.Listener() {
                @Override
                public final void call(Object... args) {
                    connexion.disconnect();
                    connexion.close();

                    synchronized (attenteDéconnexion) {
                        attenteDéconnexion.notify();
                        Jeu.log("Client: déconnexion Joueur " + id);
                    }
                }
            });
        } catch (URISyntaxException e) {
            Jeu.error("Client: Crash dans Joueur " + this.id, e);
        }
    }

    public final void démarrer() {
        connexion.connect();

        synchronized (attenteDéconnexion) {
            try {
                attenteDéconnexion.wait();
            } catch (InterruptedException e) {
                Jeu.error("Client: erreur dans l'attente déconnexion Joueur " + this.id, e);
            }
        }
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
