package moteur;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import moteur.carte.*;

public class Jeu {
    private final int NBCARTES = 35;
    private int TAILLE_DECK = 0; //Taille initial ! (=== mesJoueurs.length)

    private final ArrayList<Carte> tabCarte = new ArrayList<Carte>();

    private ArrayList<Joueur> mesJoueurs;

    public Jeu(int nbJoueurs) {

        mesJoueurs = new ArrayList<Joueur>(nbJoueurs);
        for (int i = 0; i < nbJoueurs; i++)
            mesJoueurs.add(new Joueur(i));

        TAILLE_DECK = (int) Math.floor(NBCARTES / nbJoueurs);

        initCartes();
        distributionCarte();
    }

    public final void initCartes() {
        for (int i = 0; i < NBCARTES/2; i++)
            tabCarte.add(new Taverne());
        if(NBCARTES/2 == 1 ){
            for (int i = 0; i < NBCARTES/2 + 1; i++)
                tabCarte.add(new Theatre());
        }else{
            for (int i = 0; i < NBCARTES/2; i++)
                tabCarte.add(new Theatre());
        }
        Collections.shuffle(tabCarte);
    }

    public final void roulementCarte(){
        //TODO autre sens
        ArrayList<Carte> first = mesJoueurs.get(0).getDeckMain();

        for(int i=0; i<mesJoueurs.size()-1; i++){
            mesJoueurs.get(i).setDeckMain(mesJoueurs.get(i+1).getDeckMain());
        }

        mesJoueurs.get(mesJoueurs.size()-1).setDeckMain(first);
    }

    public final void distributionCarte() {

        for (int i=0; i<mesJoueurs.size(); i++) {

            ArrayList<Carte> carteJoueur = new ArrayList<Carte>(TAILLE_DECK);
            for (int j = 0; j < TAILLE_DECK; j++) {
                Carte c = tabCarte.get(0);
                tabCarte.remove(0);
                carteJoueur.add(c);
            }
            mesJoueurs.get(i).setDeckMain(carteJoueur);
        }
    }

    //TODO add jUnit + seperate file
    //Unit test
    /*public static void main(String[] args) {
        Jeu jeu = new Jeu(7);

        ArrayList<Joueur> joueurs = jeu.getJoueurs();
        log("\nDeck avant");
        for(int i=0; i<joueurs.size(); i++){
            ArrayList<Carte> deckJoueur = joueurs.get(i).getDeckMain();
            String deckString = "";
            for(int j=0; j<deckJoueur.size(); j++)
                deckString += deckJoueur.get(j).getValue()+":";
            
            log("Deck joueur "+i+" => "+ deckString);
        }

        jeu.roulementCarte();

        log("\nDeck aprés");
        for(int i=0; i<joueurs.size(); i++){
            ArrayList<Carte> deckJoueur = joueurs.get(i).getDeckMain();
            String deckString = "";
            for(int j=0; j<deckJoueur.size(); j++)
                deckString += deckJoueur.get(j).getValue()+":";
            
            log("Deck joueur "+i+" => "+ deckString);
        }
    }*/

    public final boolean finJeu(){
        return mesJoueurs.get(0).getDeckMain().size() == 1;
    }

    public final ArrayList<Joueur> getClassement(){
        mesJoueurs.sort(new Comparator<Joueur>(){
            @Override
            public int compare(Joueur j1, Joueur j2) {
                // j2 > j1 ?  j2,j1 : j1,j2
                return Integer.compare(j2.getScore(), j1.getScore());
            }
        });
        return mesJoueurs;
    }

    public final ArrayList<Joueur> getJoueurs(){
        return mesJoueurs;
    }

    public final static int indexOf(int[] tab, int n) {

        for (int i = 0; i < tab.length; i++)
            if (tab[i] == n)
                return i;

        return -1;
    }

    public final static void log(Object obj) {
        System.out.println(obj);
    }

    public final static void error(String s, Exception err){
        System.out.print(s);
        System.err.println(" : " + err.getMessage());
    }
}