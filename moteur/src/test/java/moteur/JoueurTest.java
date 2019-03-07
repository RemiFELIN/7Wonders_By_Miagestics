package moteur;

import org.junit.Before;
import org.junit.Test;

import moteur.carte.Theatre;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class JoueurTest {

    public Joueur joueur;

    @Before
    public void setUp(){
        joueur = new Joueur(1);
    }

    @Test
    public void testCalculScore() {
        ArrayList<Carte> deck = new ArrayList<Carte>(5);
        int nbCarte = 5;
        for(byte i = 0; i<nbCarte; i++)
            deck.add(new Theatre());
     
        joueur.setDeckMain(deck);
        for(byte i = 0; i<nbCarte; i++)
            joueur.poserCarte(0);

        //5 gold + 10 Score laurier
        assertEquals(15, joueur.getScore());
    }
}