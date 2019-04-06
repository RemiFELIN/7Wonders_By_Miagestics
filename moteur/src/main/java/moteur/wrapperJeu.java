package moteur;

import serveur.Serveur;
import commun.Action;
import static commun.ConsoleLogger.*;

import java.util.ArrayList;

/**
 * Permet de lancer le jeu avec le serveur
 * @author Pierre Saunders
 */
public class wrapperJeu {

    private Serveur serveur;
    private Jeu jeu;

    /**
     * Permet de lancer le serveur du jeu
     * @param une adresse
     * @param un port
     */
    public wrapperJeu(String adresse, int port){
        //super(adresse, port);
        serveur = new Serveur(adresse, port, 4, 7);
        serveur.onRejoindreJeu(this::onRejoindreJeu);
        serveur.onJouerCarte(this::onJouerCarte, this::onFinTour);
        serveur.démarrer();
    }
    /**
     * Envoie les VisionJeu à tout les clients
     */
    public final void sendVisionsJeu() {
        ArrayList<VisionJeu> vj = jeu.getVisionsJeu();
        for (int i = 0; i < vj.size(); i++)
            serveur.sendEvent("getVision" + i, vj.get(i));
    }
    /**
     * Permet lorsque suffisament de joueurs se soit connectées de lancer la partie
     * @param nbJoueursConnectees
     */
    public final void onRejoindreJeu(int nbJoueursConnectees){
        log(YELLOW_BOLD_BRIGHT + "\n-----------------------------------------------");
        log(YELLOW_BOLD_BRIGHT + "- 7 WONDERS : nombre de joueurs connectés : " + nbJoueursConnectees
                + " -");
        log(YELLOW_BOLD_BRIGHT + "-----------------------------------------------\n");
        jeu = new Jeu(nbJoueursConnectees);
        jeu.distributionCarte();
        sendVisionsJeu();
    }
    /**
     * Permet lorsque de l'on reçoit une action de l'effectuer
     * @param l'action à effectuer
     * @return vrai si action valide sinon faux
     */
    public final boolean onJouerCarte(Action ja) {
        String descJouer = jeu.jouerAction(ja);
        if (descJouer == null)
            return false;
        else
            log(descJouer);
        return true;
    }
    /**
     * Permet lors de la fin d'un tour de poursuivre le dérouelement de la partie
     * @return nombre de joueurs d'on le coup est fini
     */
    public final int onFinTour(){
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
                    serveur.sendEvent("finJeuClassement"+j.getId(), new int[]{i, s});
                }
                log(textClas.toString());
                serveur.terminer();
            } else {
                jeu.ageSuivant();
                jeu.distributionCarte();
                log(GREEN_BOLD + "Distribution des nouveaux decks\n");
                jeu.roulementCarte();
                sendVisionsJeu();
            }
        } else {
            jeu.tourSuivant();
            log(GREEN_BOLD + "\nDébut du tour " + jeu.getTour());
            log(GREEN_BOLD + "-------------------------\n");
            jeu.roulementCarte();
            sendVisionsJeu();
        }
        return 0;
    }
}