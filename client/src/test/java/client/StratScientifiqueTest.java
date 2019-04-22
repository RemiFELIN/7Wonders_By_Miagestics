package client;

import client.strategie.StratScientifique;

import commun.Carte;
import commun.VisionJeu;
import commun.Joueur;
import commun.Merveille;
import commun.Action;
import static commun.Ressource.*;
import static commun.Couleur.*;
import static commun.SymboleScientifique.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

/**
 * Fichier de test unitaire pour test la classe StratScientifique
 * @author Benoît Montorsi, Pierre Saunders
 */
public class StratScientifiqueTest {

    StratScientifique stratClient;
    VisionJeu j, jGauche, jDroite;
    Joueur central, gauche, droite;
    ArrayList<Carte> deckMain, deckPlateau;

    @Before
    public void setUp() {
        central = new Joueur(0);
        gauche = new Joueur(1);
        droite = new Joueur(2);

        deckMain = new ArrayList<Carte>();

        Carte c = new Carte("CarteTestRessource", BLANC, 0,0,0,0,0);
        deckMain.add(c);

        c = new Carte("CarteTestScientifique", VERT, 0, COMPAS);
        c.ajouterRessource(MINERAI);
        c.ajouterRessource(MINERAI);
        c.ajouterRessource(BOIS);
        deckMain.add(c);

        c = new Carte("CarteTestScientifique", VERT, 0, COMPAS);
        c.ajouterRessource(MINERAI);
        c.ajouterRessource(BOIS);
        deckMain.add(c);

        deckPlateau=new ArrayList<Carte>();
        c = new Carte("CarteTestRessource", BLANC, 0);
        c.ajouterRessource(BOIS);
        deckPlateau.add(c);

        c = new Carte("CarteTestRessource2", BLANC, 0);
        c.ajouterRessource(MINERAI);
        deckPlateau.add(c);

        j = new VisionJeu(0, 0, new int[]{0, 0, 0}, new int[]{0, 0, 0}, new Merveille("test", 'A', BOIS, 1), deckMain, deckPlateau);
        jGauche = new VisionJeu(1, 0, new int[]{0, 0, 0}, new int[]{0, 0, 0}, new Merveille("test2", 'A', BOIS, 1), deckPlateau);
        jDroite = new VisionJeu(2, 0, new int[]{0, 0, 0}, new int[]{0, 0, 0}, new Merveille("test3", 'A', BOIS, 1), deckPlateau);
        j.setVoisinDroite(jDroite);
        j.setVoisinGauche(jGauche);
    }

    @Test
    public void testStratScientifique() throws Exception {
        stratClient = new StratScientifique();

        Action pc = stratClient.getAction(j);
        assertEquals(2, pc.getNumeroCarte());
    }
}
