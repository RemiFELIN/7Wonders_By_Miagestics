package serveur;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;

import moteur.Jeu;

public class Serveur {

    SocketIOServer serveur;
    SocketIOClient mesClient[];
    Object attenteConnexion = new Object();
    Jeu jeu;
    int connexionsAutorisees = 0;

    private final static int MIN_JOUEURS = 3;
    private final static int MAX_JOUEURS = 3;

    public Serveur(String adresse, int port) {

        Configuration config = new Configuration();
        config.setHostname(adresse);
        config.setPort(port);

        serveur = new SocketIOServer(config);

        Jeu.log("Serveur: Création listener");

        mesClient = new SocketIOClient[MAX_JOUEURS];

        serveur.addConnectListener(new ConnectListener() {
            public void onConnect(SocketIOClient socketIOClient) {

                if (connexionsAutorisees > 3) {
                    Jeu.log("Connexion impossible: déjà 3 joueurs dans la partie");
                    socketIOClient.disconnect();
                } else {
                    Jeu.log("Serveur: Connexion de" + socketIOClient.getRemoteAddress());
                    mesClient[connexionsAutorisees++] = socketIOClient;
                    if (connexionsAutorisees == 3) {
                        Jeu.log("Serveur: Connexion de" + socketIOClient.getRemoteAddress());
                        Jeu.log("3 joueurs de connectés: début de la partie");
                        // Jeu.démarrer();
                    }
                }
            }
        });

    }

    public void démarrer() {
        Jeu.log("Serveur: Démarrage");
        serveur.start();

        Jeu.log("Serveur: en attente de connexion...");
        synchronized (attenteConnexion) {
            try {
                attenteConnexion.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Jeu.log("Serveur: Crash");
            }
        }
        Jeu.log("Serveur: Une connexion est arrivée, on arrête");
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
