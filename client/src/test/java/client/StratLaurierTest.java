package client;

import client.strategie.StratLaurier;

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
 * Fichier de test unitaire pour test la classe StartLaurier
 * @author Benoît Montorsi, Pierre Saunders
 */
public class StratLaurierTest {

    StratLaurier stratClient;
    VisionJeu vj;

    @Before
    public void setUp() {
        ArrayList<Carte> deckMain = new ArrayList<Carte>();

        Carte c = new Carte("CarteTestRessource", BLANC, 0);
        deckMain.add(c);

        c = new Carte("CarteTestLaurier", BLEU, 0, 0, 2, 0, 0);
        c.ajouterRessource(MINERAI);
        c.ajouterRessource(MINERAI);
        c.ajouterRessource(BOIS);
        deckMain.add(c);

        c = new Carte("CarteTestLaurier", BLEU, 0, 0, 3, 0, 0);
        c.ajouterRessource(MINERAI);
        c.ajouterRessource(BOIS);
        deckMain.add(c);

        ArrayList<Carte> deckPlateau=new ArrayList<Carte>();
        c = new Carte("CarteTestRessource", BLANC, 0);
        c.ajouterRessource(BOIS);
        deckPlateau.add(c);

        c = new Carte("CarteTestRessource2", BLANC, 0);
        c.ajouterRessource(MINERAI);
        deckPlateau.add(c);

        vj = new VisionJeu(0, 0, new int[]{0, 0, 0}, 0, new Merveille("test", 'A', BOIS, 1), deckMain, deckPlateau);
        VisionJeu jGauche = new VisionJeu(1, 0, new int[]{0, 0, 0}, 0, new Merveille("test2", 'A', BOIS, 1), new ArrayList<Carte>());
        VisionJeu jDroite = new VisionJeu(2, 0, new int[]{0, 0, 0}, 0, new Merveille("test3", 'A', BOIS, 1), new ArrayList<Carte>());
        vj.setVoisinDroite(jDroite);
        vj.setVoisinGauche(jGauche);
    }

    @Test
    public void testStratLaurier() throws Exception {
        stratClient = new StratLaurier();

        Action pc = stratClient.getAction(vj);
        assertEquals(2, pc.getNumeroCarte());
    }
}
