package moteur;

import commun.Action;
import commun.Ressource;
import commun.Couleur;
import commun.Carte;
import commun.Merveille;
import commun.Joueur;
import commun.VisionJeu;
import static commun.ConsoleLogger.*;

import java.net.BindException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.HashMap;

/**
 * @authors Yannick Cardini, Benoît Montorsi, Rémi Felin, Pierre Saunders
 */
public class Jeu {

    private ArrayList<Joueur> mesJoueurs;
    private final ArrayList<ArrayList<Carte>> tabDeck = new ArrayList<ArrayList<Carte>>(3);
    private int age = 1, tour = 1;

    public static final void main(String args[]) throws BindException {
        if (args.length == 2) {
            String adresse = args[0];
            int port = Integer.parseInt(args[1]);
            new wrapperJeu(adresse, port);
        }
    }
    /**
     * Constructeur qui permet d'initialiser le début de partie
     * @param nbJoueurs nombre de joueurs dans la partie
     */
    public Jeu(int nbJoueurs) {
        mesJoueurs = new ArrayList<Joueur>(nbJoueurs);
        for (int i = 0; i < nbJoueurs; i++)
            mesJoueurs.add(new Joueur(i));

        distributionPlateau();
        initCartes();
    }
    //GETTER
    /**
     * @author Pierre Saunders
     * @return le tour en cours
     */
    public final int getTour(){ return tour; }
    /**
     * @return l'âge en cours
     */
    public final int getAge(){ return age; }
    /**
     * @return tous les deck de la fosse
     */
    public final ArrayList<ArrayList<Carte>> getDecks(){ return tabDeck; }
    /**
     * @return la taille du deck de l'âge en cours
     */
    public final int getTailleDeck(){ return tabDeck.get(1).size(); }
    /**
     * @return le deck de l'âge en cours
     */
    public final ArrayList<Carte> getDeckPrincipal(){ return tabDeck.get(this.age-1); }
    /**
     * @return les joueurs
     */
    public final ArrayList<Joueur> getJoueurs(){ return mesJoueurs; }
    /**
     * Permet de passer au tour suivant
     * @author Pierre Saunders
     */
    public final void tourSuivant(){ tour++; }
    /**
     * Permet d'initialiser les cartes dans les decks
     */
    public final void initCartes() {
        for (int j = 0; j < 3; j++){
            ArrayList<Carte> tabCarte = Carte.getDeck(j+1,mesJoueurs.size());
            ArrayList<Carte> deckGuildes = new ArrayList<Carte>();

            if(j == 2){
                deckGuildes = Carte.getDeckGuildes(mesJoueurs.size());
                tabCarte.addAll(deckGuildes);
            }
            Collections.shuffle(tabCarte);
            tabDeck.add(tabCarte);
        }    
    }
    /**
     * Permet de faire tourner les cartes (horaire ou anti-horaire selon l'âge) parmi les joueurs
     * @author Pierre Saunders
     */
    public final void roulementCarte(){
        if(age%2 == 1){ //Pour age 1 et 3 => rotation horaire
            ArrayList<Carte> first = mesJoueurs.get(0).getDeckMain();
            for(int i=0; i<mesJoueurs.size()-1; i++)
                mesJoueurs.get(i).setDeckMain(mesJoueurs.get(i+1).getDeckMain());
            
            mesJoueurs.get(mesJoueurs.size()-1).setDeckMain(first);
        } else { //Pour age 2 => rotation anti-hoiraire
            int size = mesJoueurs.size()-1;
            ArrayList<Carte> last = mesJoueurs.get(size).getDeckMain();
            for(int i=size; i>0; i--)
                mesJoueurs.get(i).setDeckMain(mesJoueurs.get(i-1).getDeckMain());
            
            mesJoueurs.get(0).setDeckMain(last);
        }
    }
    /**
     * Distribue les cartes des deck de l'âge en cours aux joueurs
     * @author Thomas Gauci
     */
    public final void distributionCarte() {
        int nbCartes = tabDeck.get(this.age-1).size()/mesJoueurs.size();
        for (int i=0; i<mesJoueurs.size(); i++) {
            ArrayList<Carte> carteJoueur = new ArrayList<Carte>();

            for (int j = 0; j < nbCartes; j++) {
                Carte c = tabDeck.get(this.age-1).get(0);
                tabDeck.get(this.age-1).remove(0);
                carteJoueur.add(c);
            }
            mesJoueurs.get(i).setDeckMain(carteJoueur);

        }
    }
    /**
     * Distribue les plateaux aux joueurs
     * @author Yannick Cardini
     */
    public final void distributionPlateau() {
        ArrayList<Merveille> plateaux = Merveille.getPlateaux();
        Merveille m;
        for (int i=0; i<mesJoueurs.size(); i++) {
            Random random = new Random();
            m = plateaux.remove(random.nextInt(plateaux.size()));
            mesJoueurs.get(i).setPlateau(m);
        }
    }
    /**
     * Permet d'effecter une action donnée
     * @param ja action à effectuer
     * @return description de l'action effectuée
     */
    public final String jouerAction(Action ja){
        Carte c;
        Joueur j = mesJoueurs.get(ja.getIdJoueur());
        String desc = "";
        switch(ja.getType()){

            case DefausserCarte:
                c = j.defausserCarte(ja.getNumeroCarte());
                desc = "Le joueur "+ja.getIdJoueur()+" a défaussé la carte "+Couleur.consoleColor(c.getCouleur())+c.getNom();
                tabDeck.get(this.age-1).add(c);
            break;

            case AcheterRessource:
                int idJoueurAPayer=ja.getIdJoueur()+ja.getNumVoisin();

                if(idJoueurAPayer<0)
                {
                    idJoueurAPayer=mesJoueurs.size()-1;
                }
                else if(idJoueurAPayer>mesJoueurs.size()-1)
                {
                    idJoueurAPayer=0;
                }

                mesJoueurs.get(idJoueurAPayer).recevoirPaiement(j.payer(2));
                desc = "Le joueur "+ja.getIdJoueur()+" a acheté des ressources au joueur "+ idJoueurAPayer + "\n";

            case PoserCarte:
                c = j.poserCarte(ja.getNumeroCarte());
                desc += "Le joueur "+ja.getIdJoueur()+" a posé la carte "+Couleur.consoleColor(c.getCouleur())+c.getNom();
                ArrayList<Ressource> cr = c.getCoutRessources();
                if(cr.size() > 0){
                    desc += WHITE + " qui coûte ";
                    HashMap<Ressource, Integer> hr = new HashMap<Ressource, Integer>();

                    for(Ressource r : cr)
                        hr.put(r, hr.get(r) == null ? 1 : hr.get(r)+1);

                    for(Ressource r : hr.keySet())
                            desc += hr.get(r) + " de "+r.toString()+", ";
                    
                    desc = desc.substring(0, desc.length()-2);
                }
            break;

            case ConstruireMerveille:
                if(age >= 2) {
                    if (j.getPlateau().getEtape(age - 1).getEtat() == true)
                        j.getPlateau().getEtape(age).construire();
                }else
                    j.getPlateau().getEtape(age).construire();
                int etape = j.construireEtape(age, ja.getNumeroCarte());
                desc = "Le joueur "+ja.getIdJoueur()+" a construit l'étape "+etape+" de sa merveille "+j.getPlateau().getNom();
                break;
        }
        return desc;
    }
    /**
     * Test si l'âge en cours est terminé
     * @author Pierre Saunders
     * @return vrai si âge terminé sinon faux
     */
    public final boolean finAge(){
        if(tour > 7)
            return true;
        
        Boolean isFin = true;
        for(byte i=0; i<mesJoueurs.size(); i++){
            if(mesJoueurs.get(i).getDeckMain().size() != 1){
                isFin = false;
                break;
            }
        }
        return isFin;
    }
    /**
     * Permet d'appliquer les confilts miltaires entre 2 joueurs lors de la fin d'un âge
     * @author Pierre Saunders
     * @param j1 joueur 1
     * @param j2 joueur 2
     */
    private final void compareConfiltsJoueur(Joueur j1, Joueur j2){
        int r1 = j1.getForceMilitaire();
        int r2 = j2.getForceMilitaire();
        if(r1 != r2){
            if(r1 > r2){
                j1.ajouterJetonVictoire(age);
                j2.ajouterJetonDefaite(age);
            //r1 <= r2
            } else {
                j2.ajouterJetonVictoire(age);
                j1.ajouterJetonDefaite(age);
            }
        }
    }
    /**
     * Permet de passer à l'âge suivant
     * @author Pierre Saunders
     */
    public final void ageSuivant(){

        //Calcul confilts militaire
        for(byte i=0; i<mesJoueurs.size()-1; i++)
            compareConfiltsJoueur(mesJoueurs.get(i), mesJoueurs.get(i+1));

        compareConfiltsJoueur(mesJoueurs.get(mesJoueurs.size()-1), mesJoueurs.get(0));

        age++;
        tour = 1; //reset tour
    }
    /**
     * Test si le jeu est terminée
     * @return vrai si le jeu est terminée sinon faux
     * @author Thomas Gauci
     */
    public final boolean finJeu(){ return age >= 3; }
    /**
     * Permet d'obtenir le classement final des joueurs
     * @author Pierre Saunders
     * @return liste classée des joueurs
     */
    public final ArrayList<Joueur> getClassement(){
        ArrayList<Joueur> classé = new ArrayList<Joueur>(mesJoueurs);

        classé.sort(new Comparator<Joueur>(){
            public int compare(Joueur j1, Joueur j2) {
                // j2 > j1 ?  j2,j1 : j1,j2
                return Integer.compare(j2.getScore(), j1.getScore());
            }
        });
        return classé;
    }
    /**
     * Permet d'obtenir les VisionJeu de tous les joueurs
     * @author Pierre Saunders
     * @return les VisionJeu
     */
    public final ArrayList<VisionJeu> getVisionsJeu(){
        ArrayList<VisionJeu> v = new ArrayList<VisionJeu>(mesJoueurs.size());

        for(Joueur j : mesJoueurs)
            v.add(new VisionJeu(j));

        v.get(0).setVoisinGauche(v.get(v.size()-1));
        v.get(0).setVoisinDroite(v.get(1));

        for(int i=1; i<v.size()-1; i++){
            v.get(i).setVoisinGauche(v.get(i-1));
            v.get(i).setVoisinDroite(v.get(i+1));
        }

        v.get(v.size()-1).setVoisinGauche(v.get(v.size()-2));
        v.get(v.size()-1).setVoisinDroite(v.get(0));

        return v;
    }
}