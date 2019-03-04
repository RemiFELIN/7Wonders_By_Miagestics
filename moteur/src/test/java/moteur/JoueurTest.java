package moteur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class JoueurTest {

    public Joueur joueur;

    @BeforeEach
    public void setUp(){
        joueur = new Joueur(0);
    }

    @Test
    public void testCalculScore() throws Exception {
        ArrayList<Carte> deck = new ArrayList<Carte>(5);
        for(byte i = 0; i<5; i++)
            deck.add(new Carte(2));
        
        joueur.setDeckMain(deck);
        for(byte i = 0; i<5; i++)
            joueur.poserCarte(0);

        assertEquals(10, joueur.getScore());
    }
}