package serveur;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;

import commun.*;
import static commun.ConsoleLogger.*;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Permet la réception et envoi de message aux clients
 * @author Pierre Saunders
 */
public class Serveur {

    private SocketIOServer serveur;
    private SocketIOClient client;
    private int nbJoueursConnectees, nbJoueurCoupFini, carteDistribué = 0;
    private int MIN_JOUEURS, MAX_JOUEURS;

    /**
     * Constructeur
     * @param une adresse
     * @param un port
     * @param le nombre minimum de joueurs avant de commencer une partie
     * @param le nombre maximum de joueurs autorisées dans une partie
     */
    public Serveur(String adresse, int port, int minJ, int maxJ) {

        MIN_JOUEURS = minJ;
        MAX_JOUEURS = maxJ;

        final Configuration config = new Configuration();
        config.setHostname(adresse);
        config.setPort(port);

        serveur = new SocketIOServer(config);

        log(GREEN_BOLD_BRIGHT + "Serveur: Création listener\n");

        serveur.addConnectListener(new ConnectListener() {
            public final void onConnect(SocketIOClient socketIOClient) {
                if (client == null)
                    client = socketIOClient;
            }
        });

        serveur.addEventListener("recuCarte", Integer.class, new DataListener<Integer>() {
            @Override
            public final void onData(SocketIOClient socketIOClient, Integer id, AckRequest ackRequest)
                    throws Exception {
                carteDistribué++;
                if (carteDistribué == nbJoueursConnectees) {
                    carteDistribué = 0;
                    log(GREEN_BOLD + "Tous les clients ont reçu leur vision, début du tour\n");
                    client.sendEvent("debutTour");
                }
            }
        });
    }
    /**
     * Permet de s'abonner à l'événement d'un client qui se connecte au serveur
     * @param function de callback
     */
    public final void onRejoindreJeu(Consumer<Integer> fnc){
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
                        fnc.accept(nbJoueursConnectees);
                    }
                }
            }
        });
    }
    /**
     * Permet de s'abonner à l'événement de la réception d'un Action
     * @param function de callback quand on reçoit l'Action
     * @param function de callback quand c'est la fin d'un tour
     */
    public final void onJouerCarte(Function<Action, Boolean> jouerAction, Supplier<Integer> fin){
        serveur.addEventListener("jouerCarte", Action.class, new DataListener<Action>() {
            @Override
            public final void onData(SocketIOClient socketIOClient, Action ja, AckRequest ackRequest) throws Exception {
                if(jouerAction.apply(ja)){
                    nbJoueurCoupFini++;
                    if (nbJoueurCoupFini == nbJoueursConnectees)
                       nbJoueurCoupFini = fin.get();
                }
            }
        });
    }
    /**
     * Permet d'envoyer un Event aux clients (Warpper)
     * @param nom de l'évenement
     * @param data à envoyer
     */
    public final void sendEvent(String evt, Object o){
        client.sendEvent(evt, o);
    }
    /**
     * Permet de se désabonner des évenements et fermer le serveur
     */
    public final void terminer(){
        log(GREEN_BOLD_BRIGHT+"Serveur: Fermeture");
        client.disconnect();
        serveur.removeAllListeners("rejoindre jeu");
        serveur.removeAllListeners("jouerCarte");
        serveur.removeAllListeners("recuCarte");
        serveur.stop();
        //System.exit(0);
    }
    /**
     * Permet de démarrer le serveur
     */
    public final void démarrer() {
        log(GREEN_BOLD_BRIGHT + "Serveur: Démarrage");
        serveur.start();
    }
}