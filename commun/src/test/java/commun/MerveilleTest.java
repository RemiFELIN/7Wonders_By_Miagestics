package commun;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static commun.Ressource.BOIS;
import static commun.Ressource.MINERAI;
import static org.junit.Assert.*;

public class MerveilleTest {

    public Merveille merveille;
    Etape etape;
    Etape[] etapes;
    ArrayList<Merveille> plateaux;

    @Before
    public void setUp(){
        plateaux = new ArrayList<Merveille>();
        merveille = new Merveille("Le Colosse de Rhodes", 'A', MINERAI, 3);
        etapes = new Etape[3];
        etape = new Etape(new Ressource[]{BOIS, BOIS}, 3, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
        merveille.ajouterEtape(etape, 1);
        plateaux.add(merveille);
    }

    @Test
    public void testGetFace() {
        assertEquals("testGetFace",'A',merveille.getFace());
    }

    @Test
    public void testGetNom() {
        assertEquals("testGetNom","Le Colosse de Rhodes",merveille.getNom());
    }

    @Test
    public void testGetRessource() {
        assertEquals("testGetRessource",MINERAI,merveille.getRessource());
    }

    @Test
    public void testGetEtape() {
        assertEquals("testGetEtape:",etape.getPointVictoire(),merveille.getEtape(1).getPointVictoire());
        assertEquals("testGetEtape:",etape.getPointMilitaire(),merveille.getEtape(1).getPointMilitaire());
        assertEquals("testGetEtape:",etape.getPiece(),merveille.getEtape(1).getPiece());
        assertEquals("testGetEtape:",etape.getRessourcesCout(),merveille.getEtape(1).getRessourcesCout());
        assertEquals("testGetEtape:",etape.getRessourcesBonus(),merveille.getEtape(1).getRessourcesBonus());
        assertEquals("testGetEtape:",etape.getEffet(),merveille.getEtape(1).getEffet());
        assertEquals("testGetEtape:",etape.getSymboleScientifique(),merveille.getEtape(1).getSymboleScientifique());
    }

}