package serveur;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;

import moteur.*;
import moteur.Action;
import static moteur.ConsoleLogger.*;

import java.util.ArrayList;

public class Serveur {

    private SocketIOServer serveur;
    private SocketIOClient client;
    private Jeu jeu;
    private int nbJoueursConnectees = 0;
    private int nbJoueurCoupFini = 0;
    private int carteDistribué = 0;

    private final static int MIN_JOUEURS = 4;
    private final static int MAX_JOUEURS = 7;

    public Serveur(String adresse, int port) {

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
                        log(YELLOW_BOLD_BRIGHT + "\n-----------------------------------------------");
                        log(YELLOW_BOLD_BRIGHT + "- 7 WONDERS : nombre de joueurs connectés : " + nbJoueursConnectees
                                + " -");
                        log(YELLOW_BOLD_BRIGHT + "-----------------------------------------------\n");
                        jeu = new Jeu(nbJoueursConnectees);
                        jeu.distributionCarte();
                        sendVisionsJeu();
                    }
                }
            }
        });

        serveur.addEventListener("jouerCarte", Action.class, new DataListener<Action>() {
            @Override
            public final void onData(SocketIOClient socketIOClient, Action ja, AckRequest ackRequest) throws Exception {

                String descJouer = jeu.jouerAction(ja);
                if (descJouer == null)
                    throw new Exception("Action non autorisée");
                else
                    log(descJouer);

                nbJoueurCoupFini++;
                if (nbJoueurCoupFini == nbJoueursConnectees) {
                    log(YELLOW + "\nFin du tour " + jeu.getTour() + " Les scores :");
                    ArrayList<Joueur> tabJ = jeu.getJoueurs();
                    for (int i = 0; i < tabJ.size(); i++)
                        log(YELLOW + "Score joueur " + i + " : " + tabJ.get(i).getScore());

                    if (jeu.finAge()) {
                        log("\n--------------------");
                        log("- Fin de l'Age " + jeu.getAge() + " ! -");
                        log("--------------------\n");

                        if (jeu.finJeu()) {
                            log(YELLOW_BOLD_BRIGHT + "Fin du jeu !");
                            ArrayList<Joueur> clas = jeu.getClassement();
                            // 7 + 3 + 8 + 6 + 3 + 1
                            StringBuilder textClas = new StringBuilder(clas.size() * 28);
                            for (int i = 1; i < clas.size()+1; i++) {
                                Joueur j = clas.get(i-1);
                                int s = j.getScore();
                                textClas.append(YELLOW_BOLD_BRIGHT + i + " > " + j.toString() + " avec " + s + "\n");
                                client.sendEvent("finJeuClassement"+j.getId(), new int[]{i, s});
                            }
                            log(textClas.toString());
                            terminer();
                        } else {
                            jeu.ageSuivant();
                            jeu.distributionCarte();
                            log(GREEN_BOLD + "Distribution des nouveaux decks\n");
                            jeu.roulementCarte();
                            sendVisionsJeu();
                            nbJoueurCoupFini = 0;
                        }

                    } else {
                        jeu.tourSuivant();
                        log(GREEN_BOLD + "\nDébut du tour " + jeu.getTour());
                        log(GREEN_BOLD + "-------------------------\n");
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
                    log(GREEN_BOLD + "Tous les clients ont reçu leur vision, début du tour\n");
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

    public final void terminer(){
        log(GREEN_BOLD_BRIGHT+"Serveur: Fermeture");
        client.disconnect();
        serveur.removeAllListeners("rejoindre jeu");
        serveur.removeAllListeners("jouerCarte");
        serveur.removeAllListeners("recuCarte");
        serveur.stop();
        //System.exit(0);
    }

    public final void démarrer() {
        log(GREEN_BOLD_BRIGHT + "Serveur: Démarrage");
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