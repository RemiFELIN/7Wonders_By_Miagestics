package client;

import client.strategie.StratRandom;
import moteur.*;
import moteur.action.PoserCarte;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class StratRandomTest {

    @Mock
    StratRandom stratClient;
    Joueur j;
    VisionJeu visionJ;
    ArrayList<Carte> deck;

    @Before
    public void setUp() {
        deck = new ArrayList<Carte>();
        deck.add(new Carte("CarteTestVide", Couleur.BLANC, 0));
        Carte c = new Carte("CarteTestRessource", Couleur.BLANC, 0);
        c.ajouterRessource(Ressource.MINERAI);
        deck.add(c);
        deck.add(new Carte("CarteTestLaurier", Couleur.BLANC, 0, 0, 2, 0, 0));
        visionJ=new VisionJeu(0,0,new Merveille("test",'A',Ressource.BOIS,1),deck,deck);

    }

    @Test
    public void testStratRandom() throws Exception {
        stratClient = new StratRandom();

        StratRandom stratRandomMock = org.mockito.Mockito.mock(StratRandom.class);
        when(stratRandomMock.getAction(visionJ)).thenReturn(new PoserCarte(0, 2));

        PoserCarte pc = (PoserCarte) stratRandomMock.getAction(visionJ);
        assertEquals(2, pc.getNumeroCarte());
    }

}
