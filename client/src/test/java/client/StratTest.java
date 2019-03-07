package client;

import moteur.action.PoserCarte;
import moteur.carte.Theatre;

import java.util.ArrayList;
import moteur.Carte;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.* ;
import org.mockito.Mock;

public class StratTest {

    @Mock
    Strategie stratClient;
    
    ArrayList<Carte> deck;

    @Before
    public void setUp() {
        deck = new ArrayList<Carte>();
        deck.add(new Theatre());
        deck.add(new Theatre());
        deck.add(new Theatre());
    }

    @Test
    public void testStratMax() throws Exception {
        stratClient = new StratMax();

        PoserCarte pc = (PoserCarte) stratClient.getAction(0, deck);
        assertEquals(0, pc.getNumeroCarte());
    }

    @Test
    public void testStratRandom() throws Exception {
        stratClient = new StratRandom();

        StratRandom stratRandomMock = org.mockito.Mockito.mock(StratRandom.class);
        when(stratRandomMock.getAction(0, deck)).thenReturn(new PoserCarte(0, 2));

        PoserCarte pc = (PoserCarte) stratRandomMock.getAction(0, deck);
        assertEquals(2, pc.getNumeroCarte());
    }
}