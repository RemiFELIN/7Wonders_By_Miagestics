package commun;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static commun.Couleur.*;
import static commun.Ressource.*;
import static commun.SymboleScientifique.*;

import java.util.ArrayList;

/**
 * Fichier de test unitaire pour tester la classe VisionJeu
 */
public class VisionTest {

    //VisionJeu de soi voisin gauche et voisin droite respectivement
    private VisionJeu vj, vjG, vjD;
    //Merveille de soi, voisin gauche et voisin droite respectivement
    private Merveille m, m1, m2;
    //Deck plateau de soi, voisin gauche et voisin droite respectivement
    private ArrayList<Carte> d = new ArrayList<Carte>();
    private ArrayList<Carte> d1 = new ArrayList<Carte>();
    private ArrayList<Carte> d2 = new ArrayList<Carte>();

    private int nbPiece, id, jDef;
    private int[] jVic;

    @Before
    public void setUp(){
        d.add(new Carte("CarteTestVide", BLANC, 0));        
        d.add(new Carte("CarteTestLaurier", BLANC, 0, 0, 2, 0, 0));

        d1.add(new Carte("CarteTestVideGauche", ROUGE, 2)); 
        d1.add(new Carte("CarteTestLaurierGauche", VERT, 3, 0, 3, 1, 0));

        d2.add(new Carte("CarteTestVideDroite", MARRON, 1)); 
        d2.add(new Carte("CarteTestLaurierDroite", VIOLET, 1, 0, 5, 1, 5));

        m = new Merveille("test", 'A', BOIS, 1);
        m1 = new Merveille("test2", 'A', BOIS, 1);
        m2 = new Merveille("test3", 'A', BOIS, 1);

        nbPiece = 4;
        id = 0;
        jVic = new int[]{0, 0, 0};
        jDef = 0;

        vj = new VisionJeu(id, nbPiece, jVic, jDef, m, new ArrayList<Carte>(), d);
        vjG = new VisionJeu(1, 0, new int[]{0, 1, 0}, 2, m1, d1);
        vjD = new VisionJeu(2, 0, new int[]{2, 0, 1}, 0, m2, d2);
        vj.setVoisinGauche(vjG);
        vj.setVoisinDroite(vjD);
    }

    @Test
    public void testGetter() {
        assertEquals("visionJeu getId :", id, vj.getId());
        assertEquals("visionJeu getPiece :", nbPiece, vj.getPiece());
        assertEquals("visionJeu getPlateau", m, vj.getPlateau());
        assertArrayEquals("visionJeu getJetonsVictoire", jVic, vj.getJetonsVictoire());
        assertEquals("visionJeu getJetonsDefaite", jDef, vj.getJetonsDefaite());
        assertEquals("visionJeu getDeckMain", 0, vj.getDeckMain().size());
        assertEquals("visionJeu getDeckPlateau", d, vj.getDeckPlateau());
    }

    @Test
    public final void testGetterVoisinGauche(){
        assertEquals("visionJeu getVoisinGaucheId :", vjG.getId(), vj.getVoisinGaucheId());
        assertEquals("visionJeu getVoisinGauchePiece", vjG.getPiece(), vj.getVoisinGauchePiece());
        assertArrayEquals("visionJeu getVoisinGaucheJetonsVictoire", vjG.getJetonsVictoire(), vj.getVoisinGaucheJetonsVictoire());
        assertEquals("visionJeu getVoisinGaucheJetonsDefaite", vjG.getJetonsDefaite(), vj.getVoisinGaucheJetonsDefaite());

        Merveille vgp = vj.getVoisinGauchePlateau();
        assertEquals("visionJeu getVoisinGauchePlateau setter", m1, vgp);
        assertEquals("visionJeu getVoisinGauchePlateau getter", vjG.getPlateau(), vgp);

        ArrayList<Carte> vgDp = vj.getVoisinGaucheDeckPlateau();
        assertEquals("visionJeu getVoisinGaucheDeckPlateau setter", d1, vgDp);
        assertEquals("visionJeu getVoisinGaucheDeckPlateau getter", vjG.getDeckPlateau(), vgDp);
    }

    @Test
    public final void testGetterVoisinDroite(){
        assertEquals("visionJeu getVoisinDroiteId :", vjD.getId(), vj.getVoisinDroiteId());
        assertEquals("visionJeu getVoisinDroitePiece", vjD.getPiece(), vj.getVoisinDroitePiece());
        assertArrayEquals("visionJeu getVoisinDroiteJetonsVictoire", vjD.getJetonsVictoire(), vj.getVoisinDroiteJetonsVictoire());
        assertEquals("visionJeu getVoisinDroiteJetonsDefaite", vjD.getJetonsDefaite(), vj.getVoisinDroiteJetonsDefaite());

        Merveille vdp = vj.getVoisinDroitePlateau();
        assertEquals("visionJeu getVoisinDroitePlateau setter", m2, vdp);
        assertEquals("visionJeu getVoisinDroitePlateau getter", vjD.getPlateau(), vdp);

        ArrayList<Carte> vdDp = vj.getVoisinDroiteDeckPlateau();
        assertEquals("visionJeu getVoisinDroiteDeckPlateau setter", d2, vdDp);
        assertEquals("visionJeu getVoisinDroiteDeckPlateau getter", vjD.getDeckPlateau(), vdDp);
    }
}