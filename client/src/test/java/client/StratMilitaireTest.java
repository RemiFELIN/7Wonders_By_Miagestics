package client;

import client.strategie.*;
import moteur.Carte;
import moteur.Couleur;
import moteur.Ressource;
import moteur.action.PoserCarte;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class StratMilitaireTest {

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
        c = new Carte("CarteTestMilitaire", Couleur.ROUGE, 1, 0, 0, 2, 0);
        deck.add(c);
        deck.add(new Carte("CarteTestLaurier", Couleur.BLANC, 0, 0, 2, 0, 0));
    }


    @Test
    public void testStratMilitaire() throws Exception {
        stratClient = new StratMilitaire();

        PoserCarte pc = (PoserCarte) stratClient.getAction(0, deck);
        assertEquals(2, pc.getNumeroCarte());
    }
}
