package client;

import client.strategie.StratRessources;
import moteur.*;
import static moteur.TypeAction.*;
import static moteur.Ressource.*;
import static moteur.Couleur.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class StratRessourcesTest {

    @Mock
    StratRessources stratClient;
    VisionJeu vj;

    @Before
    public void setUp() {
        ArrayList<Carte> deckMain = new ArrayList<Carte>();
        deckMain.add(new Carte("CarteTestVide", BLANC, 0));

        Carte c = new Carte("CarteTestRessource", BLANC, 0);
        c.ajouterRessource(MINERAI);
        deckMain.add(c);
        
        deckMain.add(new Carte("CarteTestLaurier", BLANC, 0, 0, 2, 0, 0));

        vj = new VisionJeu(0, 0, new Merveille("test", 'A', BOIS, 1), deckMain, new ArrayList<Carte>());
        VisionJeu jGauche = new VisionJeu(1, 0, new Merveille("test2", 'A', BOIS, 1), new ArrayList<Carte>());
        VisionJeu jDroite = new VisionJeu(2, 0, new Merveille("test3", 'A', BOIS, 1), new ArrayList<Carte>());
        vj.setVoisinDroite(jDroite);
        vj.setVoisinGauche(jGauche);
    }

    @Test
    public void testStratRessources() throws Exception {
        stratClient = new StratRessources();

        Action pc = stratClient.getAction(vj);
        assertEquals(1, pc.getNumeroCarte());
    }
}
