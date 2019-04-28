package moteur;

import serveur.Serveur;

import commun.Action;
import commun.Joueur;
import commun.VisionJeu;

import static commun.ConsoleLogger.*;

import java.net.BindException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Permet de lancer le jeu avec le serveur
 * @author Pierre Saunders, Rémi Felin
 */
public class WrapperJeu {

    private Serveur serveur;
    private Jeu jeu;

    // STRAT
    private boolean reset = false;
    private int nombreDeParties, partieCourante;
    // [nombre victoire][score fin de jeu]
    private int[][] statJoueur;
    private String[] nomStratJoueur;

    /**
     * Permet créer et lancer le serveur du jeu
     * @param adresse une adresse
     * @param port un port
     * @param minJ nombre minimum de joueur
     * @param maxJ nombre maximum de joueur
     * @throws BindException
     */
    public WrapperJeu(String adresse, int port, int minJ, int maxJ) throws BindException {
        serveur = new Serveur(adresse, port, minJ, maxJ);
        configServeur();
    }

    /**
     * Permet de lancer le serveur pour le lanceur strat
     * @param adresse
     * @param port
     * @param nombreDeParties
     * @param nomStratJoueur
     * @throws BindException
     */
    public WrapperJeu(String adresse, int port, int nombreDeParties, String[] nomStratJoueur) throws BindException {
        serveur = new Serveur(adresse, port, nomStratJoueur.length);
        configServeur();
        this.nombreDeParties = nombreDeParties;
        this.nomStratJoueur = nomStratJoueur;
        reset = true;
        statJoueur = new int[nomStratJoueur.length][];
        for (byte i = 0; i < statJoueur.length; i++) {
            statJoueur[i] = new int[2];
        }
    }

    /**
     * Permet de configurer et de lancer le serveur du jeu
     */
    private final void configServeur() {
        serveur.onRejoindreJeu(this::onRejoindreJeu);
        serveur.onDebutTour(this::onDebutTour);
        serveur.démarrer();
    }

    /**
     * Permet de créer une instance de jeu
     * @param nbJoueursConnectees
     */
    private final void creerJeu(int nbJoueursConnectees) {
        jeu = new Jeu(nbJoueursConnectees);
        jeu.distributionCarte();
        serveur.sendVisionsJeu(jeu.getVisionsJeu());
    }

    /**
     * Permet lorsque suffisament de joueurs se soit connectées de lancer la partie
     * @param nbJoueursConnectees
     */
    public final void onRejoindreJeu(int nbJoueursConnectees) {
        if (reset == false) {
            log(YELLOW_BOLD_BRIGHT + "\n-----------------------------------------------");
            log(YELLOW_BOLD_BRIGHT + "- 7 WONDERS : nombre de joueurs connectés : " + nbJoueursConnectees + " -");
            log(YELLOW_BOLD_BRIGHT + "-----------------------------------------------\n");
        }
        creerJeu(nbJoueursConnectees);
    }

    /**
     * Permet lors de la fin d'un tour de poursuivre le dérouelement de la partie
     * @param acs
     * @return nombre de joueurs d'on le Action est fini
     */
    public final int onDebutTour(HashMap<Integer, Action> acs) {

        for (int i = 0; i < acs.size(); i++) {
            Action ja = acs.get(i);
            String descJouer = jeu.jouerAction(ja);
            if (descJouer == null) {
                error("Action du joueur " + i + " de type " + ja.getType().toString() + " est impossible !");
                return i;
            }
            if (reset == false)
                log(descJouer);
        }

        if (reset == false)
            log(YELLOW + "\nFin du tour " + jeu.getTour() + " de l'âge " + jeu.getAge() + "\nLes scores :");
        ArrayList<Joueur> tabJ = jeu.getJoueurs();
        if (reset == false)
            for (int i = 0; i < tabJ.size(); i++)
                log(YELLOW + "Score joueur " + i + " : " + tabJ.get(i).getScoreFinTour(new VisionJeu(tabJ.get(i - 1 < 0 ? tabJ.size() - 1 : i - 1)), new VisionJeu(tabJ.get((i + 1) % tabJ.size()))));

        if (jeu.finAge()) {
            if (reset == false) {
                log("\n--------------------");
                log("- Fin de l'Age " + jeu.getAge() + " ! -");
                log("--------------------\n");
            }

            if (jeu.finJeu()) {
                ArrayList<int[]> clas = jeu.getClassement();
                if (reset == false) {
                    log(YELLOW_BOLD_BRIGHT + "Fin du jeu !");
                    // 7 + 3 + 8 + 6 + 3 + 1
                    StringBuilder textClas = new StringBuilder(clas.size() * 28);
                    for (int i = 0; i < clas.size(); i++) {
                        int[] data = clas.get(i);
                        textClas.append(YELLOW_BOLD_BRIGHT + (i + 1) + " > Joueur " + data[0] + " avec " + data[1] + "\n");
                        if (reset) {
                            // on sauvegarde le premier
                            if (i == 0)
                                statJoueur[data[0]][0]++;
                            statJoueur[i][1] += data[1];
                        }
                    }
                    serveur.sendClassement(clas);
                    log(textClas.toString());
                    serveur.terminer();
                } else {
                    for (int i = 0; i < clas.size(); i++) {
                        int[] data = clas.get(i);
                        if (i == 0)
                            statJoueur[data[0]][0]++;
                        statJoueur[i][1] += data[1];
                    }
                    partieCourante++;
                    if (partieCourante == nombreDeParties) {
                        log("\nLes résultat de la simulation :\n");
                        // calcul moyenne points joueurs
                        double[] moyPoint = new double[statJoueur.length];
                        for (int i = 0; i < tabJ.size(); i++)
                            moyPoint[i] = statJoueur[i][1] / nombreDeParties;

                        serveur.sendClassement(clas);
                        // on affiche les joueurs, leur strat, la moyenne de leur point et le nb de
                        // victoires
                        for (int i = 0; i < tabJ.size(); i++)
                            log("Joueur " + i + " - " + nomStratJoueur[i] + " - " + moyPoint[i] + " - " + statJoueur[i][0] + "\n");
                        serveur.terminer();
                    } else {
                        creerJeu(statJoueur.length);
                        log("fin simulation :  " + partieCourante + " / 500");
                    }
                }
            } else {
                jeu.ageSuivant();
                jeu.distributionCarte();
                if (reset == false)
                    log(GREEN_BOLD + "Distribution des nouveaux decks\n");
                jeu.roulementCarte();
                serveur.sendVisionsJeu(jeu.getVisionsJeu());
            }
        } else {
            jeu.tourSuivant();
            if (reset == false) {
                log(GREEN_BOLD + "\nDébut du tour " + jeu.getTour());
                log(GREEN_BOLD + "-------------------------\n");
            }
            jeu.roulementCarte();
            serveur.sendVisionsJeu(jeu.getVisionsJeu());
        }
        return acs.size();
    }
}