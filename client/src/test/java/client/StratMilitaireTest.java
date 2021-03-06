package client;

import client.strategie.StratMilitaire;

import commun.Carte;
import commun.VisionJeu;
import commun.Merveille;
import commun.Action;
import static commun.Ressource.*;
import static commun.Couleur.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

/**
 * Fichier de test unitaire pour test la classe StratMilitaire
 * @author Benoît Montorsi, Pierre Saunders
 */
public class StratMilitaireTest {

    StratMilitaire stratClient;
    VisionJeu vj;

    @Before
    public final void setUp() {
        ArrayList<Carte> deckMain = new ArrayList<Carte>();
        deckMain.add(new Carte("CarteTestVide", BLANC, 0));

        Carte c = new Carte("CarteTestRessource", BLANC, 0);
        c.ajouterRessource(MINERAI);
        deckMain.add(c);

        c = new Carte("CarteTestMilitaire", ROUGE, 1, 0, 0, 2);
        deckMain.add(c);

        deckMain.add(new Carte("CarteTestPointVictoire", BLANC, 0, 0, 2, 0));

        vj = new VisionJeu(0, 0, new int[] { 0, 0, 0 }, 0, new Merveille("test", 'A', BOIS, 1), deckMain, new ArrayList<Carte>());
        VisionJeu jGauche = new VisionJeu(1, 0, new int[] { 0, 0, 0 }, 0, new Merveille("test2", 'A', BOIS, 1), new ArrayList<Carte>());
        VisionJeu jDroite = new VisionJeu(2, 0, new int[] { 0, 0, 0 }, 0, new Merveille("test3", 'A', BOIS, 1), new ArrayList<Carte>());
        vj.setVoisinDroite(jDroite);
        vj.setVoisinGauche(jGauche);
    }

    @Test
    public final void testStratMilitaire() throws Exception {
        stratClient = new StratMilitaire(false);

        Action pc = stratClient.getAction(vj);
        assertEquals(2, pc.getNumeroCarte());
    }
}
