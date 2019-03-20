package client;

import client.strategie.StratLaurier;
import moteur.Carte;
import moteur.Couleur;
import moteur.Ressource;
import moteur.action.PoserCarte;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class StratLaurierTest {

    @Mock
    StratLaurier stratClient;

    ArrayList<Carte> deck;

    @Before
    public void setUp() {
        deck = new ArrayList<Carte>();
        deck.add(new Carte("CarteTestVide", Couleur.BLANC, 0));
        Carte c = new Carte("CarteTestRessource", Couleur.BLANC, 0);
        c.ajouterRessource(Ressource.MINERAI);
        deck.add(c);
        deck.add(new Carte("CarteTestLaurier", Couleur.BLANC, 0, 0, 2, 0, 0));
    }

    @Test
    public void testStratLaurier() throws Exception {
        stratClient = new StratLaurier();

        PoserCarte pc = (PoserCarte) stratClient.getAction(0, deck);
        assertEquals(2, pc.getNumeroCarte());
    }
}
