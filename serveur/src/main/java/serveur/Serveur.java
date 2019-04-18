package serveur;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;

import commun.Action;
import commun.VisionJeu;

import static commun.ConsoleLogger.*;

import java.net.BindException;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Permet la réception et envoi de message aux clients
 * 
 * @author Pierre Saunders
 */
public class Serveur {

    private SocketIOServer serveur;
    private ArrayList<SocketIOClient> clients = new ArrayList<SocketIOClient>();
    private int nbJoueursConnectees, nbJoueurActionRecu, carteDistribué = 0;
    private int MIN_JOUEURS, MAX_JOUEURS;
    private HashMap<Integer, Action> actionRecu;

    /**
     * Constructeur
     * 
     * @param adresse adresse
     * @param port    port
     * @param minJ    nombre minimum de joueurs avant de commencer une partie
     * @param maxJ    nombre maximum de joueurs autorisées dans une partie
     */
    public Serveur(String adresse, int port, int minJ, int maxJ) throws BindException {

        MIN_JOUEURS = minJ;
        MAX_JOUEURS = maxJ;

        final Configuration config = new Configuration();
        config.setHostname(adresse);
        config.setPort(port);

        serveur = new SocketIOServer(config);

        log(GREEN_BOLD_BRIGHT + "Serveur: Création listener\n");

        serveur.addConnectListener(new ConnectListener() {
            public final void onConnect(SocketIOClient socketIOClient) {
                clients.add(socketIOClient);
            }
        });
    }

    /**
     * Permet de s'abonner à l'événement d'un client qui se connecte au serveur
     * 
     * @param fnc callback
     */
    public final void onRejoindreJeu(Consumer<Integer> fnc) {
        serveur.addEventListener("rejoindre jeu", Integer.class, new DataListener<Integer>() {
            @Override
            public final void onData(SocketIOClient socketIOClient, Integer id, AckRequest ackRequest)
                    throws Exception {
                if (nbJoueursConnectees > MAX_JOUEURS) {
                    log(RED_BOLD_BRIGHT + "Connexion impossible: déjà " + MAX_JOUEURS + " joueurs dans la partie");
                } else {
                    log(GREEN_BOLD_BRIGHT + "Serveur: Connexion de joueur " + id);
                    nbJoueursConnectees++;
                    if (nbJoueursConnectees == MIN_JOUEURS) {
                        log(GREEN_BOLD_BRIGHT + MIN_JOUEURS + " joueurs de connectés: début de la partie\n");
                        actionRecu = new HashMap<Integer, Action>(nbJoueursConnectees);
                        fnc.accept(nbJoueursConnectees);
                    }
                }
            }
        });
    }

    /**
     * Permet de s'abonner à l'événement de la réception d'un Action et lancer le
     * tour si suffisament d'action on était reçu
     * 
     * @param debutTour callback quand on reçoit suffisament d'Action
     */
    public final void onDebutTour(Function<HashMap<Integer, Action>, Integer> debutTour) {
        serveur.addEventListener("jouerAction", Action.class, new DataListener<Action>() {
            @Override
            public final void onData(SocketIOClient socketIOClient, Action ja, AckRequest ackRequest) throws Exception {
                if (actionRecu.containsKey(ja.getIdJoueur()) == false) {
                    nbJoueurActionRecu++;
                    actionRecu.put(ja.getIdJoueur(), ja);
                    if (nbJoueurActionRecu == nbJoueursConnectees) {
                        log(GREEN_BOLD_BRIGHT + "Serveur: fin du tour\n");
                        int idJoueur = debutTour.apply(actionRecu);
                        if (idJoueur == nbJoueursConnectees) {
                            nbJoueurActionRecu = 0;
                            actionRecu.clear();
                        } else {
                            log(GREEN_BOLD_BRIGHT + "Serveur: action du joueur " + idJoueur
                                    + " est incorrect\n en attente d'un renvoi");
                            actionRecu.remove(idJoueur);
                            nbJoueurActionRecu--;
                        }
                    }
                } else
                    log(GREEN_BOLD_BRIGHT + "Serveur: action du joueur " + ja.getIdJoueur() + " déja reçu");
            }
        });
    }

    /**
     * Wrapper pour envoyer les classement aux joueurs
     * 
     * @param clas tableau bi-dimensionnel de format clas[idJoueur] = [place, score]
     */
    public final void sendClassement(int[][] clas) {
        for (byte i = 0; i < clas.length; i++)
            for (SocketIOClient client : clients)
                client.sendEvent("finJeuClassement" + i, clas[i]);
    }

    /**
     * Wrapper pour envoyer les VisionJeu aux clients
     * 
     * @param vj collections de VisionJeu
     */
    public final void sendVisionsJeu(ArrayList<VisionJeu> vj) {
        for (byte i = 0; i < vj.size(); i++)
            for (SocketIOClient client : clients)
                client.sendEvent("getVision" + i, vj.get(i));
    }

    /**
     * Permet de se désabonner des évenements et fermer le serveur
     */
    public final void terminer() {
        log(GREEN_BOLD_BRIGHT + "Serveur: Fermeture");
        for (SocketIOClient client : clients)
            client.disconnect();
        serveur.removeAllListeners("rejoindre jeu");
        serveur.removeAllListeners("jouerAction");
        serveur.removeAllListeners("recuCarte");
        serveur.stop();
        // System.exit(0);
    }

    /**
     * Permet de démarrer le serveur
     */
    public final void démarrer() {
        log(GREEN_BOLD_BRIGHT + "Serveur: Démarrage");

        serveur.addEventListener("recuCarte", Integer.class, new DataListener<Integer>() {
            @Override
            public final void onData(SocketIOClient socketIOClient, Integer id, AckRequest ackRequest)
                    throws Exception {
                carteDistribué++;
                if (carteDistribué == nbJoueursConnectees) {
                    carteDistribué = 0;
                    log(GREEN_BOLD + "Tous les clients ont reçu leur vision, début du tour\n");
                    for (SocketIOClient client : clients)
                        client.sendEvent("debutTour");
                }
            }
        });
        serveur.start();
    }
}