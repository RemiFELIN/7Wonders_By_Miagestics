package client;

import moteur.action.PoserCarte;
import moteur.Carte;
import moteur.Couleur;
import moteur.Ressource;
import client.strategie.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;
import org.mockito.Mock;

public class StratTest {

    @Mock
    Strategie stratClient;

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
    public void testStratMax() throws Exception {
        stratClient = new StratMax();

        PoserCarte pc = (PoserCarte) stratClient.getAction(0, deck);
        assertEquals(2, pc.getNumeroCarte());
    }

    @Test
    public void testStratRandom() throws Exception {
        stratClient = new StratRandom();

        StratRandom stratRandomMock = org.mockito.Mockito.mock(StratRandom.class);
        when(stratRandomMock.getAction(0, deck)).thenReturn(new PoserCarte(0, 2));

        PoserCarte pc = (PoserCarte) stratRandomMock.getAction(0, deck);
        assertEquals(2, pc.getNumeroCarte());
    }

    @Test
    public void testStratRessources() throws Exception {
        stratClient = new StratRessources();

        PoserCarte pc = (PoserCarte) stratClient.getAction(0, deck);
        assertEquals(1, pc.getNumeroCarte());
    }
}