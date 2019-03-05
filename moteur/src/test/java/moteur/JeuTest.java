package moteur;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class JeuTest {

    public Jeu testDuJeu;

    @Before
    public void setUp(){
        testDuJeu =  new Jeu(3);
    }

    @Test
    public void distributionCarte() {
        /*
        Tests : le paquet se décrémente t-il lorsque le joueur tire une carte ?
        */
        ArrayList<Carte> deck = testDuJeu.getDeckPrincipal();
        ArrayList<Joueur> joueurs = testDuJeu.getJoueurs();
        int TAILLE_DECK = deck.size();
        int NB_CARTES_PAR_JOUEURS = (int) Math.floor(testDuJeu.getDeckPrincipal().size()/joueurs.size());

        testDuJeu.distributionCarte();

        int TAILLE_DECK_ATTENDU = TAILLE_DECK-joueurs.size()*NB_CARTES_PAR_JOUEURS;
        assertEquals( TAILLE_DECK_ATTENDU, testDuJeu.getDeckPrincipal().size() );
    }

    @Test
    public void roulementCarte() {
        /*
        Test : est-ce que chaques joueurs ont bien donné leur deck respectif au joueur suivant ?
        */
        ArrayList<Joueur> joueurs = testDuJeu.getJoueurs();
        ArrayList<Carte> first = joueurs.get(0).getDeckMain();
        testDuJeu.roulementCarte();

        for(int j=0; j < joueurs.size()-1; j++){
            assertEquals( joueurs.get(j).getDeckMain(), joueurs.get(j+1).getDeckMain() );
        }
        assertEquals( joueurs.get(joueurs.size()-1).getDeckMain() , first);
    }
}