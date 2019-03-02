package moteur;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import java.lang.reflect.Field;

public class JeuTest {

    private final int NB_JOUEURS_A_TEST = 7;
    private final int NB_CARTES_A_TEST = 35;

    protected Jeu jeu;

    @Before
    public void setUp() throws Exception {
        jeu = new Jeu(NB_JOUEURS_A_TEST);

        Field f = Jeu.class.getDeclaredField("tabCarte");
        ArrayList<Carte> tabCarte = new ArrayList<Carte>(NB_CARTES_A_TEST);
        for(byte i=0; i<NB_CARTES_A_TEST; i++)
            tabCarte.add(new Carte(i));
        
        f.setAccessible(true);
        f.set(jeu, tabCarte);
    }

    @Test
    public void testDistribution(){
        jeu.distributionCarte();
        ArrayList<Joueur> alJoueurs = jeu.getJoueurs();
        int value = 0;
        for(int i=0; i<alJoueurs.size(); i++){
            ArrayList<Carte> deckJoueur = alJoueurs.get(i).getDeckMain();
            for(int j=0; j<deckJoueur.size(); j++)
                assertEquals(deckJoueur.get(j).getValue(), value++);
        }
    }

    @Test
    public void testRoulementCarte(){
        jeu.distributionCarte();
        jeu.roulementCarte();
        ArrayList<Joueur> alJoueurs = jeu.getJoueurs();
        int value = 5;
        for(int i=0; i<alJoueurs.size(); i++){
            ArrayList<Carte> deckJoueur = alJoueurs.get(i).getDeckMain();
            if(i == alJoueurs.size()-1)
                value = 0;
            for(int j=0; j<deckJoueur.size(); j++)
                assertEquals(deckJoueur.get(j).getValue(), value++);
        }
    }
}