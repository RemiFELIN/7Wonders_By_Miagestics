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
public class wrapperJeu {

    private Serveur serveur;
    private Jeu jeu;
    private boolean reset = false;
    private int nombreDeParties, partieCourante;
    //STRAT
    private String[] mesJoueurs = new String[5];
    private double[] mean = new double[5];
    private double[] meanJ = new double[5];
    private String[] stratDesJoueurs = new String[]{"pointVictoire", "random", "ressources", "militaire", "scientifiques"};
    private int[] nbVictoiresJ = new int[5];

    /**
     * Permet de lancer le serveur du jeu
     * @param adresse une adresse
     * @param port un port
     */
    public wrapperJeu(String adresse, int port, int minJ, int maxJ) throws BindException {
        // super(adresse, port);
        serveur = new Serveur(adresse, port, minJ, maxJ);
        serveur.onRejoindreJeu(this::onRejoindreJeu);
        serveur.onDebutTour(this::onDebutTour);
        serveur.démarrer();
    }

    /**
     * permet de lancer le serveur pour le lanceur strat
     * @param adresse
     * @param port
     * @param nombreDeParties
     * @throws BindException
     */
    public wrapperJeu(String adresse, int port, int nombreDeParties) throws BindException {
        this(adresse, port, 5, 5);
        this.nombreDeParties = nombreDeParties;
        reset = true;
    }

    private final void creerJeu(int nbJoueursConnectees){
        jeu = new Jeu(nbJoueursConnectees);
        jeu.distributionCarte();
        serveur.sendVisionsJeu(jeu.getVisionsJeu());
    }

    /**
     * Permet lorsque suffisament de joueurs se soit connectées de lancer la partie
     * @param nbJoueursConnectees
     */
    public final void onRejoindreJeu(int nbJoueursConnectees) {
        log(YELLOW_BOLD_BRIGHT + "\n-----------------------------------------------");
        log(YELLOW_BOLD_BRIGHT + "- 7 WONDERS : nombre de joueurs connectés : " + nbJoueursConnectees + " -");
        log(YELLOW_BOLD_BRIGHT + "-----------------------------------------------\n");
        creerJeu(nbJoueursConnectees);
    }

    /**
     * Permet lors de la fin d'un tour de poursuivre le dérouelement de la partie
     * @return nombre de joueurs d'on le Action est fini
     */
    public final int onDebutTour(HashMap<Integer, Action> acs) {

        for (int i = 0; i < acs.size(); i++) {
            Action ja = acs.get(i);
            String descJouer = jeu.jouerAction(ja);
            if (descJouer == null) {
                log("Action du joueur " + i + " de type " + ja.getType().toString() + " est impossible !");
                return i;
            } else
                log(descJouer);
        }

        log(YELLOW + "\nFin du tour " + jeu.getTour() + " de l'âge " + jeu.getAge() + "\nLes scores :");
        ArrayList<Joueur> tabJ = jeu.getJoueurs();
        for (int i = 0; i < tabJ.size(); i++)
            log(YELLOW + "Score joueur " + i + " : " + tabJ.get(i).getScoreFinTour(new VisionJeu(tabJ.get(i - 1 < 0 ? tabJ.size() - 1 : i - 1)), new VisionJeu(tabJ.get((i + 1) % tabJ.size()))));

        if (jeu.finAge()) {
            log("\n--------------------");
            log("- Fin de l'Age " + jeu.getAge() + " ! -");
            log("--------------------\n");

            if (jeu.finJeu()) {
                log(YELLOW_BOLD_BRIGHT + "Fin du jeu !");
                ArrayList<int[]> clas = jeu.getClassement();
                // 7 + 3 + 8 + 6 + 3 + 1
                StringBuilder textClas = new StringBuilder(clas.size() * 28);
                for (int i = 0; i < clas.size(); i++) {
                    int[] data = clas.get(i);
                    //on sauvegarde le premier
                    if(i == 0)
                       nbVictoiresJ[data[0]] += 1;
                    textClas.append(YELLOW_BOLD_BRIGHT + (i + 1) + " > Joueur " + data[0] + " avec " + data[1] + "\n");
                    meanJ[i] += data[1];
                }
                if(reset == false) {
                    serveur.sendClassement(clas);
                    log(textClas.toString());
                    serveur.terminer();
                } else {
                    //strat : sauvegarde data
                    partieCourante++;
                    if(partieCourante > nombreDeParties - 1){
                        System.out.println("\nLes résultat de la simulation :\n");
                        //calcul moyenne points joueurs
                        for(int i = 0; i < tabJ.size(); i++)
                            mean[i] = meanJ[i] / nombreDeParties;

                        reactiverLog();
                        serveur.sendClassement(clas);
                        //on affiche les joueurs, leur strat, la moyenne de leur point et le nb de victoires
                        for(int i = 0; i < tabJ.size(); i++)
                            System.out.println("Joueur " + i + " - " + stratDesJoueurs[i] + " - " + mean[i] + " - " + nbVictoiresJ[i]);
                        log("");
                        serveur.terminer();
                    } else {
                        creerJeu(tabJ.size());
                        reactiverLog();
                        log("simulation :  " + partieCourante + " / 500");
                        desactiverLog();
                    }
                }
            } else {
                jeu.ageSuivant();
                jeu.distributionCarte();
                log(GREEN_BOLD + "Distribution des nouveaux decks\n");
                jeu.roulementCarte();
                serveur.sendVisionsJeu(jeu.getVisionsJeu());
            }
        } else {
            jeu.tourSuivant();
            log(GREEN_BOLD + "\nDébut du tour " + jeu.getTour());
            log(GREEN_BOLD + "-------------------------\n");
            jeu.roulementCarte();
            serveur.sendVisionsJeu(jeu.getVisionsJeu());
        }
        return acs.size();
    }
}