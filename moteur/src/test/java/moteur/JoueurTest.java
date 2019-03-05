package moteur;

import org.junit.Before;
import org.junit.Test;
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
        for(byte i = 0; i<5; i++)
            deck.add(new Carte(2));
        
        joueur.setDeckMain(deck);
        for(byte i = 0; i<5; i++)
            joueur.poserCarte(0);

        assertEquals(10, joueur.getScore());
    }
}