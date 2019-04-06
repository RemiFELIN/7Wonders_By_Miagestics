package moteur;

import org.junit.Before;
import org.junit.Test;

import commun.Carte;
import commun.Joueur;
import commun.VisionJeu;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Fichier de test unitaire pour tester la classe VisionJeu
 * @author Pierre Saunders
 */
public class VisionJeuTest {

    public Jeu jeu;
    public final int NOMBRE_JOUEURS = 7;

    @Before
    public void setUp() {
        jeu = new Jeu(NOMBRE_JOUEURS);
        jeu.distributionCarte();
    }

    @Test
    public void getVisionJeuTest(){
        ArrayList<VisionJeu> vj = new ArrayList<VisionJeu>(NOMBRE_JOUEURS);
        ArrayList<Joueur> joueurs = jeu.getJoueurs();
        for(int i=0; i<NOMBRE_JOUEURS; i++){
            Joueur j = joueurs.get(i);
            vj.add(new VisionJeu(i, j.getPiece(), j.getPlateau(), j.getDeckMain(), j.getDeckPlateau()));
        }

        ArrayList<VisionJeu> vc = jeu.getVisionsJeu();

        VisionJeu j = vj.get(0);
        VisionJeu c = vc.get(0);
        assertEqualsVisionJeu(j, c);
        assertEqualsVisionJeuVoisin(c, vj.get(NOMBRE_JOUEURS-1), vc.get(1));

        for(int i=1; i<NOMBRE_JOUEURS-1; i++){
            j = vj.get(i);
            c = vc.get(i);
            assertEqualsVisionJeu(j, c);
            assertEqualsVisionJeuVoisin(c, vj.get(i-1), vc.get(i+1));
        }

        j = vj.get(NOMBRE_JOUEURS-1);
        c = vc.get(NOMBRE_JOUEURS-1);
        assertEqualsVisionJeu(j, c);
        assertEqualsVisionJeuVoisin(c, vj.get(NOMBRE_JOUEURS-2), vc.get(0));
    }

    private void assertEqualsVisionJeu(VisionJeu a, VisionJeu b){
        assertEquals(a.getId(), b.getId());
        assertEquals(a.getPiece(), b.getPiece());
        assertEquals(a.getPlateau(), b.getPlateau());
        assertEquals(a.getDeckPlateau(), b.getDeckPlateau());
    }

    private void assertEqualsVisionJeuVoisin(VisionJeu j, VisionJeu g, VisionJeu d){
        assertEquals(j.getVoisinGaucheId(), g.getId());
        assertEquals(j.getVoisinGauchePiece(), g.getPiece());
        assertEquals(j.getVoisinGaucheDeckPlateau(), g.getDeckPlateau());
        assertEquals(j.getVoisinGauchePlateau(), g.getPlateau());

        assertEquals(j.getVoisinDroiteId(), d.getId());
        assertEquals(j.getVoisinDroitePiece(), d.getPiece());
        assertEquals(j.getVoisinDroiteDeckPlateau(), d.getDeckPlateau());
        assertEquals(j.getVoisinDroitePlateau(), d.getPlateau());
    }
}