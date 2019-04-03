package client;

import client.strategie.StratRandom;
import moteur.*;
import static moteur.TypeAction.*;
import static moteur.Ressource.*;
import static moteur.Couleur.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class StratRandomTest {

    @Mock
    StratRandom stratClient;
    VisionJeu vj;

    @Before
    public void setUp() {
        ArrayList<Carte> deckMain = new ArrayList<Carte>();
        deckMain.add(new Carte("CarteTestVide", BLANC, 0));

        Carte c = new Carte("CarteTestRessource", BLANC, 0);
        c.ajouterRessource(Ressource.MINERAI);
        deckMain.add(c);

        deckMain.add(new Carte("CarteTestLaurier", BLANC, 0, 0, 2, 0, 0));

        vj = new VisionJeu(0, 0, new Merveille("test", 'A', BOIS, 1), deckMain, new ArrayList<Carte>());
        VisionJeu jGauche = new VisionJeu(1, 0, new Merveille("test2", 'A', BOIS, 1), new ArrayList<Carte>());
        VisionJeu jDroite = new VisionJeu(2, 0, new Merveille("test3", 'A', BOIS, 1), new ArrayList<Carte>());
        vj.setVoisinDroite(jDroite);
        vj.setVoisinGauche(jGauche);
    }

    @Test
    public void testStratRandom() throws Exception {
        stratClient = new StratRandom();

        StratRandom stratRandomMock = org.mockito.Mockito.mock(StratRandom.class);
        when(stratRandomMock.getAction(vj)).thenReturn(new Action(PoserCarte,0, 2));

        Action pc = stratRandomMock.getAction(vj);
        assertEquals(2, pc.getNumeroCarte());
    }

}
