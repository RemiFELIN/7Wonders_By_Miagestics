package moteur;

import java.util.ArrayList;
import java.util.Collections;

public class Jeu {
    private final int NBCARTES = 12;
    private final int TAILLE_DECK = 4;

    private ArrayList<Carte> tabCarte = new ArrayList<Carte>();

    private Joueur mesJoueurs[];

    public Jeu(int nbJoueurs){

        mesJoueurs = new Joueur[nbJoueurs];
        for(int i=0; i<nbJoueurs; i++){
            mesJoueurs[i] = new Joueur();
        }

        initCartes();

        distributionCarte();
    }
    

    public void initCartes(){
        for(int i=0; i<NBCARTES; i++)
            tabCarte.add(new Carte(i));
        
            Collections.shuffle(tabCarte);
    }

    public final static int indexOf(int[] tab, int n){

        for(int i=0; i<tab.length; i++)
            if(tab[i] == n)
                return i;
        
        return -1;
    }

    public void distributionCarte(){

        for(int i=0; i<mesJoueurs.length; i++){
            mesJoueurs[i] = new Joueur();

            Carte[] carteJoueur = new Carte[TAILLE_DECK];
            int idCarte = 0;
            for(int j=0; j<TAILLE_DECK; j++){
                Carte c = tabCarte.get(0);
                tabCarte.remove(0);
                carteJoueur[idCarte++] = c;
            }
            mesJoueurs[i].setCartes(carteJoueur);
        }
    }

    public void getJoueursCartes(){
        for (int i=0; i<mesJoueurs.length; i++) {
            mesJoueurs[i].getDeck();
        }
    }

    public static final void main(String args[]){
        Jeu j = new Jeu(3);
        j.getJoueursCartes();
    }
    
    public final static void log(String s){
        System.out.println(s);
    }
}