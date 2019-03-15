package moteur;

import org.junit.Before;
import org.junit.Test;

import moteur.carte.Taverne;
import moteur.jsonParser.JSONAction;
import moteur.action.*;

import static moteur.Jeu.log;

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
        int NB_CARTES_PAR_JOUEURS = (int) Math.floor(TAILLE_DECK/joueurs.size());

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

        for (int j = 0; j < joueurs.size() - 1; j++)
            assertEquals(joueurs.get(j).getDeckMain(), joueurs.get(j + 1).getDeckMain());
        
        assertEquals(joueurs.get(joueurs.size() - 1).getDeckMain(), first);

        changeField("age", 2);
        int size = joueurs.size()-1;
        ArrayList<Carte> last = joueurs.get(size).getDeckMain();
        
        testDuJeu.roulementCarte();

        for(int j=size; j>0; j--)
            assertEquals(joueurs.get(j).getDeckMain(), joueurs.get(j - 1).getDeckMain());
        
        assertEquals(joueurs.get(0).getDeckMain(), last);
    }

    @Test
    public void recuperationCarteTest(){

        int tailledeck = testDuJeu.getDecks().get(0).size();

        changeField("TAILLE_DECK", 1);
        testDuJeu.distributionCarte();

        assertEquals(true, testDuJeu.finAge());
        testDuJeu.ageSuivant();
        testDuJeu.recuperationCarte();
        // test le deck pour voir si le paquet a recupéré toutes les cartes cartes
        int tabDeck = testDuJeu.getDecks().get(0).size();
        assertEquals(tailledeck, tabDeck);
        ArrayList<Joueur> joueurs = testDuJeu.getJoueurs();
        for (int i = 0; i < joueurs.size(); i++) 
            assertEquals(0, joueurs.get(i).getDeckMain().size());
    }

   @Test
   public void finAgeTest() {
       // modifier le deck main pour qu'il reste qu'une seule carte
       ArrayList<Joueur> joueurs = testDuJeu.getJoueurs();
       ArrayList<Carte> c = new ArrayList<Carte>();
       c.add(new Taverne());
       for (int i = 0; i < joueurs.size(); i++) {
           joueurs.get(0).setDeckMain(c);
       }
       assertEquals(true, testDuJeu.finAge());
       // modifier le deck main pour qu'il reste plusieurs cartes
       c.add(new Taverne());
       for (int i = 0; i < joueurs.size(); i++) {
           joueurs.get(0).setDeckMain(c);
       }
       assertEquals(false, testDuJeu.finAge());
   }

   @Test
   public void finJeuTest() {
       changeField("age", 1);
       assertEquals(false, testDuJeu.finJeu());
       changeField("age", 4);
       assertEquals(true, testDuJeu.finJeu());
   }

   @Test
   public void jouerActionTest(){
        testDuJeu.distributionCarte();

        JSONAction ja = new JSONAction();
        ja.idJoueur = 0;
        ja.numeroCarte = 2;
        ja.type = "PoserCarte";

        int prevSize = testDuJeu.getJoueurs().get(0).getDeckMain().size();
        assertEquals(true, testDuJeu.jouerAction(ja));
        int afterSize = testDuJeu.getJoueurs().get(0).getDeckMain().size();
        assertEquals(afterSize, prevSize-1);

        ja.idJoueur = 1;
        ja.numeroCarte = 0;
        ja.type = "DefausserCarte";

        assertEquals(true, testDuJeu.jouerAction(ja));

        prevSize = testDuJeu.getJoueurs().get(1).getDeckMain().size();
        int prevPiece = testDuJeu.getJoueurs().get(1).getPiece();
        assertEquals(true, testDuJeu.jouerAction(ja));
        afterSize = testDuJeu.getJoueurs().get(1).getDeckMain().size();
        int afterPiece = testDuJeu.getJoueurs().get(1).getPiece();
        assertEquals(afterSize, prevSize-1);
        assertEquals(prevPiece+3, afterPiece);
   }

   private void changeField(String nomField, Object value){
       Field f;
       try {
           f = Jeu.class.getDeclaredField(nomField);
           f.setAccessible(true);
           f.set(testDuJeu, value); 
       } catch (Exception e) {
           log("Test: Impossible de modifier le champ "+nomField);
       } 
   }
}
