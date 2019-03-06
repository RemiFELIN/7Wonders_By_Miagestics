package moteur;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import moteur.carte.*;

public class Jeu {
    private final int NBCARTES = 35;
    private int TAILLE_DECK = 0; //Taille initial ! (=== mesJoueurs.length)

    private final ArrayList<ArrayList<Carte>> tabDeck = new ArrayList<ArrayList<Carte>>(3);
    private int age = 1;

    private ArrayList<Joueur> mesJoueurs;

    public Jeu(int nbJoueurs) {
        mesJoueurs = new ArrayList<Joueur>(nbJoueurs);
        for (int i = 0; i < nbJoueurs; i++)
            mesJoueurs.add(new Joueur(i));

        TAILLE_DECK = (int) Math.floor(NBCARTES / nbJoueurs);
        initCartes();
    }

    public final void initCartes() {
        for (int j = 1; j <= 3; j++){
            ArrayList<Carte> tabCarte = new ArrayList<Carte>(NBCARTES);
            for (int i = 0; i < NBCARTES/2; i++)
                tabCarte.add(new Theatre());
            if(NBCARTES/2 == 1){
                for (int i = 0; i < NBCARTES/2 + 1; i++)
                    tabCarte.add(new Taverne());
            }else{
                for (int i = 0; i < NBCARTES/2; i++)
                    tabCarte.add(new Taverne());
            }
            Collections.shuffle(tabCarte);
            tabDeck.add(tabCarte);
        }
        
}

    public final void roulementCarte(){
        //TODO autre sens
        ArrayList<Carte> first = mesJoueurs.get(0).getDeckMain();
        for(int i=0; i<mesJoueurs.size()-1; i++)
            mesJoueurs.get(i).setDeckMain(mesJoueurs.get(i+1).getDeckMain());
        
        mesJoueurs.get(mesJoueurs.size()-1).setDeckMain(first);
    }

    public final void distributionCarte() {
        for (int i=0; i<mesJoueurs.size(); i++) {
            ArrayList<Carte> carteJoueur = new ArrayList<Carte>(TAILLE_DECK);
            for (int j = 0; j < TAILLE_DECK; j++) {
                Carte c = tabDeck.get(this.age-1).get(0);
                tabDeck.get(this.age-1).remove(0);
                carteJoueur.add(c);
            }
            mesJoueurs.get(i).setDeckMain(carteJoueur);
        }
        log("Les cartes ont été distribuées !\n");
    }

    public final void recuperationCarte(){
        for (int i=0; i<mesJoueurs.size(); i++) {
            tabDeck.get(age-2).add(mesJoueurs.get(i).getDerniereCarte());
        }
    }
    
    public final boolean finAge(){
        
        if (mesJoueurs.get(0).getDeckMain().size() == 1){
            this.age++;
            return true;
        }
        else
            return false;
    }

    public final boolean finJeu(){
        return age > 3;
    }
    
    public final ArrayList<Joueur> getClassement(){
        mesJoueurs.sort(new Comparator<Joueur>(){
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

    public final int getAge(){
        return this.age;
    }

    public final ArrayList<ArrayList<Carte>> getDecks(){
        return this.tabDeck;
    }

    public final int getTailleDeck(){
        return TAILLE_DECK;
    }


    public final ArrayList<Carte> getDeckMain(){
        return mesJoueurs.get(0).getDeckMain();
    }
    
    //GETTER
    public ArrayList<Carte> getDeckPrincipal(){
        return tabDeck.get(this.age-1);
    }
}