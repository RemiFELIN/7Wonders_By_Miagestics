package moteur;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class JoueurTest {

    protected Joueur j;

    @Before
    public void setUp(){
        j = new Joueur(0);
    }

    @Test
    public void testCalculScore() throws Exception {
        ArrayList<Carte> deck = new ArrayList<Carte>(5);
        for(byte i = 0; i<5; i++){
            Carte c = new Carte(2);
            deck.add(c);
        }
        j.setDeckMain(deck);
        for(byte i = 0; i<5; i++)
            j.poserCarte(0);

        assertEquals(10, j.getScore());
    }
}