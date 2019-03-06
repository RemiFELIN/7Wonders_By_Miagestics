package moteur;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class JeuTest {

    public Jeu testDuJeu;

    @Before
    public void setUp() {
        testDuJeu = new Jeu(3);
    }

    @Test
    public void distributionCarteTest() {
        /*
         * Tests : le paquet se décrémente t-il lorsque le joueur tire une carte ?
         */
        ArrayList<Carte> deck = testDuJeu.getDecks().get(0);
        ArrayList<Joueur> joueurs = testDuJeu.getJoueurs();
        int TAILLE_DECK = deck.size();
        int NB_CARTES_PAR_JOUEURS = (int) Math.floor(TAILLE_DECK/ joueurs.size());

        testDuJeu.distributionCarte();

        int TAILLE_DECK_ATTENDU = TAILLE_DECK - joueurs.size() * NB_CARTES_PAR_JOUEURS;
        assertEquals(TAILLE_DECK_ATTENDU, testDuJeu.getDecks().get(0).size());
    }

    @Test
    public void roulementCarteTest() {
        /*
         * Test : est-ce que chaques joueurs ont bien donné leur deck respectif au
         * joueur suivant ?
         */
        ArrayList<Joueur> joueurs = testDuJeu.getJoueurs();
        ArrayList<Carte> first = joueurs.get(0).getDeckMain();
        testDuJeu.roulementCarte();

        for (int j = 0; j < joueurs.size() - 1; j++) {
            assertEquals(joueurs.get(j).getDeckMain(), joueurs.get(j + 1).getDeckMain());
        }
        assertEquals(joueurs.get(joueurs.size() - 1).getDeckMain(), first);
    }

    /*@Test
    public void recuperationCarteTest(){

        changeField("age", 2);
        int tailledeck = testDuJeu.getDecks().get(0).size();
        int joueurs = testDuJeu.getJoueurs().size();
        ArrayList<Carte> c = new ArrayList<Carte>(1);
        c.add(new Carte(2, 2));
        for (int i = 0; i < joueurs; i++)
           testDuJeu.getJoueurs().get(i).setDeckMain(c);

        testDuJeu.recuperationCarte();
        // test le deck pour voir si le paquet a recupéré des cartes
        int tabDeck = testDuJeu.getDecks().get(0).size();
        Jeu.log(tabDeck);
        assertEquals(tailledeck+testDuJeu.getJoueurs().size(), tabDeck);
    }*/

    @Test
    public void finAgeTest() {
        // modifier le deck main pour qu'il reste qu'une seule carte
        ArrayList<Joueur> joueurs = testDuJeu.getJoueurs();
        ArrayList<Carte> c = new ArrayList<Carte>();
        c.add(new Carte(1, 1));
        for (int i = 0; i < joueurs.size(); i++) {
            joueurs.get(0).setDeckMain(c);
        }
        assertEquals(true, testDuJeu.finAge());
        // modifier le deck main pour qu'il reste plusieurs cartes
        c.add(new Carte(1, 1));
        for (int i = 0; i < joueurs.size(); i++) {
            joueurs.get(0).setDeckMain(c);
        }
        assertEquals(false, testDuJeu.finAge());
    }

    @Test
    public void finJeuTest() {
        changeField("age", 1);
        assertEquals(false,testDuJeu.finJeu());

        changeField("age", 4);

        assertEquals(true,testDuJeu.finJeu());

    }

    private void changeField(String nomField, Object value){
        Field f;
        try {
            f = Jeu.class.getDeclaredField(nomField);
            f.setAccessible(true);
            f.set(testDuJeu, value); 
        } catch (Exception e) {
            // TODO Auto-generated catch block
        } 
    }
}
