package serveur;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;

import moteur.*;
import moteur.action.JSONAction;
import static moteur.Jeu.log;

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

        log("Serveur: Création listener\n");

        serveur.addConnectListener(new ConnectListener() {
            public final void onConnect(SocketIOClient socketIOClient) {
                if (client == null)
                    client = socketIOClient;
            }
        });

        serveur.addEventListener("rejoindre jeu", Integer.class, new DataListener<Integer>() {
            @Override
            public final void onData(SocketIOClient socketIOClient, Integer id, AckRequest ackRequest)
                    throws Exception {
                if (nbJoueursConnectees > MAX_JOUEURS) {
                    log("Connexion impossible: déjà " + MAX_JOUEURS + " joueurs dans la partie");
                } else {
                    log("Serveur: Connexion de joueur " + id);
                    nbJoueursConnectees++;
                    if (nbJoueursConnectees == MIN_JOUEURS) {
                        log(MIN_JOUEURS + " joueurs de connectés: début de la partie\n");
                        log("\n-----------------------------------------------");
                        log("- 7 WONDERS : nombre de joueurs connectés : " + nbJoueursConnectees + " -");
                        log("-----------------------------------------------\n");
                        jeu = new Jeu(nbJoueursConnectees);
                        jeu.distributionCarte();
                        sendVisionsJeu();
                    }
                }
            }
        });

        serveur.addEventListener("jouerCarte", JSONAction.class, new DataListener<JSONAction>() {
            @Override
            public final void onData(SocketIOClient socketIOClient, JSONAction ja, AckRequest ackRequest)
                    throws Exception {

                if (jeu.jouerAction(ja) == false)
                    throw new Exception("Action non autorisée");

                nbJoueurCoupFini++;
                if (nbJoueurCoupFini == nbJoueursConnectees) {
                    log("\nFin du tour ! Les scores :");
                    ArrayList<Joueur> tabJ = jeu.getJoueurs();
                    for (int i = 0; i < tabJ.size(); i++) {
                        log("Score joueur " + i + " : " + tabJ.get(i).getScore());
                    }
                    if (jeu.finAge()) {
                        log("\n--------------------");
                        log("- Fin de l'Age " + jeu.getAge() + " ! -");
                        log("--------------------\n");

                        if (jeu.finJeu()) {
                            log("Fin du jeu !");
                            ArrayList<Joueur> clas = jeu.getClassement();
                            for (int i = 0; i < clas.size(); i++) {
                                Joueur j = clas.get(i);
                                log(i + 1 + " > " + j.toString() + " avec " + j.getScore());
                            }
                        } else {
                            jeu.ageSuivant();
                            jeu.recuperationCarte();
                            log("Récupération de la dernière carte de l'Age");
                            jeu.distributionCarte();
                            log("Distribution des nouveaux decks\n");
                            jeu.roulementCarte();
                            sendVisionsJeu();
                            nbJoueurCoupFini = 0;
                        }

                    } else {
                        log("\nDébut du prochain tour...");
                        log("-------------------------\n");
                        jeu.roulementCarte();
                        sendVisionsJeu();
                        nbJoueurCoupFini = 0;
                    }
                }
            }
        });

        serveur.addEventListener("recuCarte", Integer.class, new DataListener<Integer>() {
            @Override
            public final void onData(SocketIOClient socketIOClient, Integer id, AckRequest ackRequest)
                    throws Exception {
                carteDistribué++;
                if (carteDistribué == nbJoueursConnectees) {
                    carteDistribué = 0;
                    client.sendEvent("debutTour");
                }
            }
        });
    }

    public final void sendVisionsJeu() {
        ArrayList<VisionJeu> vj = jeu.getVisionsJeu();
        for (int i = 0; i < vj.size(); i++)
            client.sendEvent("getVision" + i, vj.get(i));
    }

    public final void démarrer() {
        log("Serveur: Démarrage");
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