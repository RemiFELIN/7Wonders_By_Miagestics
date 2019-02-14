package serveur;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;

import moteur.Moteur;

public class Serveur {

    SocketIOServer serveur;
    Object attenteConnexion = new Object();

    public Serveur(String adresse, int port) {

        Configuration config = new Configuration();
        config.setHostname(adresse);
        config.setPort(port);

        serveur = new SocketIOServer(config);

        Moteur.log("Serveur: Création listener");

        serveur.addConnectListener(new ConnectListener() {
            public void onConnect(SocketIOClient socketIOClient) {
                Moteur.log("Serveur: Connexion de" + socketIOClient.getRemoteAddress());
            }
        });
    }

    public void démarrer() {
        Moteur.log("Serveur: Démarrage");
        serveur.start();

        Moteur.log("Serveur: en attente de connexion...");
        synchronized (attenteConnexion) {
            try {
                attenteConnexion.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Moteur.log("Serveur: Crash");
            }
        }
        Moteur.log("Serveur: Une connexion est arrivée, on arrête");
        serveur.stop();
    }

    public static final void main(String args[]) {
        if (args.length == 2) {
            String adresse = args[0];
            int port = Integer.parseInt(args[1]);
            Serveur s = new Serveur(adresse, port);
            s.démarrer();
        }
    }
}