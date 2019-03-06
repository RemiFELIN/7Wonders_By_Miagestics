package serveur;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;

import moteur.*;
import moteur.jsonParser.JSONAction;
import static moteur.jsonParser.JSONParser.*;
import moteur.action.PoserCarte;

import java.util.ArrayList;

public class Serveur {

    private SocketIOServer serveur;
    private SocketIOClient client;
    private Jeu jeu;
    private int nbJoueursConnectees = 0;
    private int nbJoueurCoupFini = 0;
    private int carteDistribué = 0;

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
                        jeu.distributionCarte();
                        sendCartes();
                    }
                }
            }
        });

        serveur.addEventListener("jouerCarte", JSONAction.class, new DataListener<JSONAction>(){
            @Override
            public final void onData(SocketIOClient socketIOClient, JSONAction a, AckRequest ackRequest) throws Exception {
                switch(a.type){

                    case "DefausserCarte":
                        //TODO: implementer Défaussage de carte !
                        throw new Exception("Action non implémenter");

                    case "PoserCarte":
                        PoserCarte pc = new PoserCarte(a.idJoueur, a.numeroCarte);
                        jeu.getJoueurs().get(pc.getIdJoueur()).poserCarte(pc.getNumeroCarte());
                    break;


                    default:
                        throw new Exception("Action non autorisée");
                }

                nbJoueurCoupFini++;
                if(nbJoueurCoupFini == nbJoueursConnectees){
                    Jeu.log("\nFin tour ! Les scores :");
                    ArrayList<Joueur> tabJ = jeu.getJoueurs();
                    for(int i=0; i<tabJ.size(); i++) {
                        Jeu.log("Score joueur " + i + " : " + tabJ.get(i).getScore());
                    }
                    for(int i = 0; i<tabJ.size()-2; i++){
                        if(!jeu.finJeu())
                            Jeu.log("\nDébut du prochain tour:");
                    }
                    if(jeu.finAge()){
                        Jeu.log("Fin de l'Age !");
                        if(jeu.finJeu()){
                            Jeu.log("Fin du jeu !");
                            ArrayList<Joueur> clas = jeu.getClassement();
                            for(int i=0; i<clas.size(); i++) {
                                Joueur j = clas.get(i);
                                Jeu.log(i+1 + " > " + j.toString() + " avec " + j.getScore());
                            }
                        } else {
                            // Amélioration 
                            jeu.recuperationCarte();
                            Jeu.log("\nRécupération de la dernière carte de l'Age");
                            jeu.distributionCarte();
                            Jeu.log("\nDistribution des nouveaux decks");
                            jeu.roulementCarte();
                            sendCartes();
                            nbJoueurCoupFini = 0;
                        }
                        
                    } else {
                        jeu.roulementCarte();
                        sendCartes();
                        nbJoueurCoupFini = 0;
                    }
                }
            }
        });

        serveur.addEventListener("recuCarte", Integer.class, new DataListener<Integer>() {
            @Override
            public final void onData(SocketIOClient socketIOClient, Integer id, AckRequest ackRequest) throws Exception {
                carteDistribué++;
                if (carteDistribué == nbJoueursConnectees ){
                    carteDistribué = 0;
                    client.sendEvent("debutTour");
                }
            }
        });
    }

    public final void sendCartes() {
        for (int i = 0; i < nbJoueursConnectees; i++) {
            String ja = deckToJSON(jeu.getJoueurs().get(i).getDeckMain());
            client.sendEvent("getCarte" + i, ja);
        }
    }

    public final void démarrer() {
        Jeu.log("Serveur: Démarrage");
        serveur.start();
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