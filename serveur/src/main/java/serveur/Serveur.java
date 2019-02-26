package serveur;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;

import moteur.*;

import java.util.ArrayList;

public class Serveur {

    private SocketIOServer serveur;
    private SocketIOClient client;
    final Object attenteConnexion = new Object();
    private Jeu jeu;
    private int nbJoueursConnectees = 0;
    private int nbJoueurCoupFini = 0;

    private final static int MIN_JOUEURS = 3;
    private final static int MAX_JOUEURS = 3;

    public Serveur(String adresse, int port) {

        final Configuration config = new Configuration();
        config.setHostname(adresse);
        config.setPort(port);

        serveur = new SocketIOServer(config);

        Jeu.log("Serveur: Création listener\n");

        serveur.addConnectListener(new ConnectListener() {
            public final void onConnect(SocketIOClient socketIOClient) {
                if (client == null)
                    client = socketIOClient;
            }
        });

        serveur.addEventListener("rejoindre jeu", Integer.class, new DataListener<Integer>() {
            @Override
            public final void onData(SocketIOClient socketIOClient, Integer id, AckRequest ackRequest) throws Exception {
                if (nbJoueursConnectees > MAX_JOUEURS) {
                    Jeu.log("Connexion impossible: déjà " + MAX_JOUEURS + " joueurs dans la partie");
                } else {
                    Jeu.log("Serveur: Connexion de joueur " + id);
                    nbJoueursConnectees++;
                    if (nbJoueursConnectees == MIN_JOUEURS) {
                        Jeu.log(MIN_JOUEURS + " joueurs de connectés: début de la partie\n");
                        jeu = new Jeu(nbJoueursConnectees);
                        sendCartes();
                    }
                }
            }
        });

        serveur.addEventListener("jouerCarte", Coup.class, new DataListener<Coup>(){
            @Override
            public final void onData(SocketIOClient socketIOClient, Coup coup, AckRequest ackRequest) throws Exception {
                Jeu.log(coup.toString());
                jeu.getJoueurs().get(coup.getId()).poserCarte(coup.getNumeroCarte());
                nbJoueurCoupFini++;
                if(nbJoueurCoupFini == nbJoueursConnectees){
                    Jeu.log("\nFin tour ! Les scores :");
                    ArrayList<Joueur> tabJ = jeu.getJoueurs();
                    for(int i=0; i<tabJ.size(); i++) {
                        Jeu.log("Score joueur " + i + " : " + tabJ.get(i).getScore());
                    }
                    for(int i = 0; i<tabJ.size()-2; i++){
                        if(!jeu.finJeu())
                            Jeu.log("Début du prochain tour ...\n");
                    }
                    if(jeu.finJeu()){
                        Jeu.log("Fin du jeu !");
                        ArrayList<Joueur> clas = jeu.getClassement();
                        for(int i=0; i<clas.size(); i++) {
                            Joueur j = clas.get(i);
                            Jeu.log(i+1 + " > " + j.toString() + " avec " + j.getScore());
                        }
                    } else {
                        jeu.roulementCarte();
                        sendCartes();
                        nbJoueurCoupFini = 0;
                    }
                }
            }
        });
    }

    public final void sendCartes() {
        for (int i = 0; i < nbJoueursConnectees; i++) {
            final ArrayList<Carte> carteJoueurs = jeu.getJoueurs().get(i).getDeckMain();
            client.sendEvent("getCarte" + i, carteJoueurs);
        }
    }

    public final void démarrer() {
        Jeu.log("Serveur: Démarrage");
        serveur.start();

        Jeu.log("Serveur: en attente de connexion...");
        synchronized (attenteConnexion) {
            try {
                attenteConnexion.wait();
            } catch (InterruptedException e) {
                Jeu.error("Serveur: Crash", e);
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
