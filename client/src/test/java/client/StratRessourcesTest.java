package client;

import client.strategie.StratRessources;
import moteur.*;
import moteur.action.PoserCarte;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class StratRessourcesTest {

    @Mock
    StratRessources stratClient;
    Joueur j;
    VisionJeu visionJ;
    ArrayList<Carte> deck;

    @Before
    public void setUp() {
        j=new Joueur(0);
        deck = new ArrayList<Carte>();
        deck.add(new Carte("CarteTestVide", Couleur.BLANC, 0));
        Carte c = new Carte("CarteTestRessource", Couleur.BLANC, 0);
        c.ajouterRessource(Ressource.MINERAI);
        deck.add(c);
        deck.add(new Carte("CarteTestLaurier", Couleur.BLANC, 0, 0, 2, 0, 0));
        visionJ=new VisionJeu(0,0,new Merveille("test",'A',Ressource.BOIS,1),deck,deck);

    }

    @Test
    public void testStratRessources() throws Exception {
        stratClient = new StratRessources();

        PoserCarte pc = (PoserCarte) stratClient.getAction(visionJ);
        assertEquals(1, pc.getNumeroCarte());
    }
}
