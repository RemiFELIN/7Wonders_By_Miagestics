package commun;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static commun.Ressource.*;
import static commun.SymboleScientifique.*;

import java.util.ArrayList;

public class MerveilleTest {

    private Merveille merveille;
    private Etape[] etapes = new Etape[3];

    @Before
    public void setUp(){
        merveille = new Merveille("Le Colosse de Rhodes", 'A', MINERAI, 3);
        etapes[0] = new Etape(new Ressource[]{BOIS, BOIS}, 3, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "effet etape 1");
        merveille.ajouterEtape(etapes[0], 1);
        etapes[1] = new Etape(new Ressource[]{MINERAI, MINERAI, MINERAI}, 0, 5, 2, new Ressource[]{}, new SymboleScientifique[]{TABLETTE}, "effet etape 2");
        merveille.ajouterEtape(etapes[1], 2);
        etapes[2] = new Etape(new Ressource[]{ARGILE}, 0, 0, 3, new Ressource[]{VERRE, VERRE}, new SymboleScientifique[]{}, "effet etape 3");
        merveille.ajouterEtape(etapes[2], 3);
    }

    @Test
    public void testGetFace() {
        assertEquals("testGetFace", 'A', merveille.getFace());
    }

    @Test
    public void testGetNom() {
        assertEquals("testGetNom", "Le Colosse de Rhodes", merveille.getNom());
    }

    @Test
    public void testGetRessource() {
        assertEquals("testGetRessource", MINERAI, merveille.getRessource());
    }

    @Test
    public void testGetEtape() {
        for(byte i=0; i<etapes.length; i++)
            testEqualEtape(etapes[i], merveille.getEtape(i+1));
    }

    private final void testEqualEtape(Etape etape, Etape etape1){
        assertEquals("etape equal point victoire :", etape.getPointVictoire(), etape1.getPointVictoire());
        assertEquals("etape equal point militaire :", etape.getPointMilitaire(), etape1.getPointMilitaire());
        assertEquals("etape equal piece :", etape.getPiece(), etape1.getPiece());
        assertEquals("etape equal cout ressources :", etape.getRessourcesCout(), etape1.getRessourcesCout());
        assertEquals("etape equal ressources bonus :", etape.getRessourcesBonus(), etape1.getRessourcesBonus());
        assertEquals("etape equal effet :", etape.getEffet(), etape1.getEffet());
        assertEquals("etape equal symbole scientifique :", etape.getSymboleScientifique(), etape1.getSymboleScientifique());
    }
}