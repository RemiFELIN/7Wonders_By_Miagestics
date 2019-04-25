package moteur;

import commun.Carte;
import commun.Action;
import commun.Joueur;
import commun.Merveille;
import commun.VisionJeu;
import static commun.Couleur.*;
import static commun.Ressource.*;
import static commun.TypeAction.*;
import static commun.ConsoleLogger.error;

import java.util.ArrayList;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Fichier de test unitaire pour test la classe Jeu
 */
public class JeuTest {

    private Jeu testDuJeu;

    @Before
    public final void setUp() {
        testDuJeu = new Jeu(7);
    }

    @Test
    public final void distributionCarteTest() {
        /*
         * Tests : le paquet se décrémente t-il lorsque le joueur tire une carte ?
         */
        ArrayList<Carte> deck = testDuJeu.getDecks().get(0);
        ArrayList<Joueur> joueurs = testDuJeu.getJoueurs();
        int TAILLE_DECK = deck.size();
        int NB_CARTES_PAR_JOUEURS = TAILLE_DECK / joueurs.size();
        testDuJeu.distributionCarte();

        int TAILLE_DECK_ATTENDU = TAILLE_DECK - joueurs.size() * NB_CARTES_PAR_JOUEURS;
        assertEquals(TAILLE_DECK_ATTENDU, testDuJeu.getDecks().get(0).size());
    }

    @Test
    public final void roulementCarteTest() {
        /*
         * Test : est-ce que chaques joueurs ont bien donné leur deck respectif au
         * joueur suivant ?
         */
        ArrayList<Joueur> joueurs = testDuJeu.getJoueurs();
        ArrayList<Carte> first = joueurs.get(0).getDeckMain();
        testDuJeu.roulementCarte();

        for (int j = 0; j < joueurs.size() - 1; j++)
            assertEquals(joueurs.get(j).getDeckMain(), joueurs.get(j + 1).getDeckMain());

        assertEquals(joueurs.get(joueurs.size() - 1).getDeckMain(), first);

        changeField("age", 2);
        int size = joueurs.size() - 1;
        ArrayList<Carte> last = joueurs.get(size).getDeckMain();

        testDuJeu.roulementCarte();

        for (int j = size; j > 0; j--)
            assertEquals(joueurs.get(j).getDeckMain(), joueurs.get(j - 1).getDeckMain());

        assertEquals(joueurs.get(0).getDeckMain(), last);
    }

    @Test
    public final void finAgeTest() {
        // Le jeu n'a pas été touché donc l'age n'est pas fini
        assertEquals(false, testDuJeu.finAge());

        // Est que le finAge se termine aprés 6 tour ?
        changeField("tour", 7);
        assertEquals(false, testDuJeu.finAge());
        changeField("tour", 5);

        // modifier le deck main pour qu'il reste qu'une seule carte
        ArrayList<Joueur> joueurs = testDuJeu.getJoueurs();
        ArrayList<Carte> c = new ArrayList<Carte>();
        c.add(new Carte("CarteTest", BLANC, 0));
        for (int i = 0; i < joueurs.size(); i++) {
            joueurs.get(0).setDeckMain(c);
        }
        assertEquals(false, testDuJeu.finAge());
        // modifier le deck main pour qu'il reste plusieurs cartes
        c.add(new Carte("CarteTest", BLANC, 0));
        for (int i = 0; i < joueurs.size(); i++) {
            joueurs.get(0).setDeckMain(c);
        }
        assertEquals(false, testDuJeu.finAge());
    }

    @Test
    public final void finJeuTest() {
        changeField("age", 1);
        assertEquals(false, testDuJeu.finJeu());
        changeField("age", 4);
        assertEquals(true, testDuJeu.finJeu());
    }

    @Test
    public final void jouerActionTest() {
        testDuJeu.distributionCarte();

        Action ja = new Action(PoserCarte, 0, 2);

        int prevSize = testDuJeu.getJoueurs().get(0).getDeckMain().size();
        assertNotEquals(null, testDuJeu.jouerAction(ja));
        int afterSize = testDuJeu.getJoueurs().get(0).getDeckMain().size();
        assertEquals(afterSize, prevSize - 1);

        ja = new Action(DefausserCarte, 1, 0);

        assertNotEquals(null, testDuJeu.jouerAction(ja));

        prevSize = testDuJeu.getJoueurs().get(1).getDeckMain().size();
        int prevPiece = testDuJeu.getJoueurs().get(1).getPiece();
        assertNotEquals(null, testDuJeu.jouerAction(ja));
        afterSize = testDuJeu.getJoueurs().get(1).getDeckMain().size();
        int afterPiece = testDuJeu.getJoueurs().get(1).getPiece();
        assertEquals(afterSize, prevSize - 1);
        assertEquals(prevPiece + 3, afterPiece);
    }

    @Test
    public final void testCompareConfiltsJoueur() {
        assertArrayEquals(new int[] { 1, 1, 1, 1, 1, 1, 1 }, getScoreJoueurs());

        ArrayList<Carte> cs = new ArrayList<Carte>(1);
        cs.add(new Carte("CarteTestMilitaire", BLANC, 1, 0, 0, 1));

        testDuJeu.getJoueurs().get(1).setDeckMain(cs);
        testDuJeu.getJoueurs().get(1).poserCarte(0);

        testDuJeu.ageSuivant();

        // J1 perd et égalité => - 1
        // J2 gagne 2 fois => + 2
        // J3 perd et égalité => - 1
        assertArrayEquals(new int[] { 0, 3, 0, 1, 1, 1, 1 }, getScoreJoueurs());

        cs = new ArrayList<Carte>(1);
        cs.add(new Carte("CarteTestMilitaire", BLANC, 1, 0, 0, 2));

        testDuJeu.getJoueurs().get(2).setDeckMain(cs);
        testDuJeu.getJoueurs().get(2).poserCarte(0);

        testDuJeu.ageSuivant();

        // J1 perd 2 fois => - 2
        // J2 gagne et perd => + 3 - 1
        // J3 gagne 2 fois => + 6
        // J4 perd => -1
        assertArrayEquals(new int[] { 0, 5, 6, 0, 1, 1, 1 }, getScoreJoueurs());
    }

    private int[] getScoreJoueurs() {
        ArrayList<Joueur> mj = testDuJeu.getJoueurs();
        int[] lesScores = new int[mj.size()];
        Merveille m = new Merveille("MerveilleTest", 'A', MINERAI, 3);
        VisionJeu vGauche = new VisionJeu(0, 0, new int[] { 0, 0, 0 }, 0, m, new ArrayList<Carte>());
        VisionJeu vDroite = new VisionJeu(2, 0, new int[] { 0, 0, 0 }, 0, m, new ArrayList<Carte>());
        for (byte i = 0; i < mj.size(); i++)
            lesScores[i] = mj.get(i).getScore(vGauche, vDroite);

        return lesScores;
    }

    @Test
    public final void getVisionJeuTest() {
        ArrayList<Joueur> joueurs = testDuJeu.getJoueurs();
        int nbJoueurs = joueurs.size();
        ArrayList<VisionJeu> vj = new ArrayList<VisionJeu>(nbJoueurs);
        for (int i = 0; i < nbJoueurs; i++) {
            Joueur j = joueurs.get(i);
            vj.add(new VisionJeu(j));
        }

        ArrayList<VisionJeu> vc = testDuJeu.getVisionsJeu();

        VisionJeu j, c;

        for (int i = 0; i < nbJoueurs; i++) {
            j = vj.get(i);
            c = vc.get(i);
            assertEqualsVisionJeu(j, c);
            assertEqualsVisionJeuVoisin(c, vj.get(i - 1 < 0 ? nbJoueurs - 1 : i - 1), vc.get((i + 1) % nbJoueurs));
        }
    }

    private final void assertEqualsVisionJeu(VisionJeu a, VisionJeu b) {
        assertEquals(a.getId(), b.getId());
        assertEquals(a.getPiece(), b.getPiece());
        assertEquals(a.getJetonsVictoire(), b.getJetonsVictoire());
        assertEquals(a.getJetonsDefaite(), b.getJetonsDefaite());
        assertEquals(a.getPlateau(), b.getPlateau());
        assertEquals(a.getDeckPlateau(), b.getDeckPlateau());
    }

    private final void assertEqualsVisionJeuVoisin(VisionJeu j, VisionJeu g, VisionJeu d) {
        assertEquals(j.getVoisinGaucheId(), g.getId());
        assertEquals(j.getVoisinGauchePiece(), g.getPiece());
        assertEquals(j.getVoisinGaucheJetonsVictoire(), g.getJetonsVictoire());
        assertEquals(j.getVoisinGaucheJetonsDefaite(), g.getJetonsDefaite());
        assertEquals(j.getVoisinGaucheDeckPlateau(), g.getDeckPlateau());
        assertEquals(j.getVoisinGauchePlateau(), g.getPlateau());

        assertEquals(j.getVoisinDroiteId(), d.getId());
        assertEquals(j.getVoisinDroitePiece(), d.getPiece());
        assertEquals(j.getVoisinDroiteJetonsVictoire(), d.getJetonsVictoire());
        assertEquals(j.getVoisinDroiteJetonsDefaite(), d.getJetonsDefaite());
        assertEquals(j.getVoisinDroiteDeckPlateau(), d.getDeckPlateau());
        assertEquals(j.getVoisinDroitePlateau(), d.getPlateau());
    }

    private final void changeField(String nomField, Object value) {
        Field f;
        try {
            f = Jeu.class.getDeclaredField(nomField);
            f.setAccessible(true);
            f.set(testDuJeu, value);
        } catch (Exception e) {
            error("Test: Impossible de modifier le champ " + nomField, e);
        }
    }
}
