package commun;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static commun.Ressource.*;
import static commun.SymboleScientifique.*;

import java.util.ArrayList;

/**
 * Fichier de test unitaire pour tester la classe Merveille
 */
public class MerveilleTest {

    private Merveille merveille;
    private final Etape[] etapes = new Etape[3];
    private final String nom = "Le Colosse de Rhodes";
    private final char face = 'A';

    @Before
    public final void setUp(){
        merveille = new Merveille(nom, face, MINERAI, 3);
        etapes[0] = new Etape(new Ressource[]{BOIS, BOIS}, 3, 0, 0, new Ressource[]{}, new SymboleScientifique[]{});
        merveille.ajouterEtape(etapes[0], 1);
        etapes[1] = new Etape(new Ressource[]{MINERAI, MINERAI, MINERAI}, 0, 5, 2, new Ressource[]{}, new SymboleScientifique[]{TABLETTE});
        merveille.ajouterEtape(etapes[1], 2);
        etapes[2] = new Etape(new Ressource[]{ARGILE}, 0, 0, 3, new Ressource[]{VERRE, VERRE}, new SymboleScientifique[]{});
        merveille.ajouterEtape(etapes[2], 3);
    }

    @Test
    public final void testGetter() {
        assertEquals("testGetFace", face, merveille.getFace());
        assertEquals("testGetNom", nom, merveille.getNom());
        assertEquals("testGetRessource", MINERAI, merveille.getRessource());
        for(byte i=0; i<etapes.length; i++)
            testEqualEtape(etapes[i], merveille.getEtape(i+1));
    }

    private final void testEqualEtape(Etape etape, Etape etape1){
        assertEquals("etape equal point victoire :", etape.getPointVictoire(), etape1.getPointVictoire());
        assertEquals("etape equal point militaire :", etape.getPointMilitaire(), etape1.getPointMilitaire());
        assertEquals("etape equal piece :", etape.getPiece(), etape1.getPiece());
        assertEquals("etape equal cout ressources :", etape.getRessourcesCout(), etape1.getRessourcesCout());
        assertEquals("etape equal ressources bonus :", etape.getRessourcesBonus(), etape1.getRessourcesBonus());
        assertEquals("etape equal effet :", etape.getEffetMerveille(), etape1.getEffetMerveille());
        assertEquals("etape equal symbole scientifique :", etape.getSymboleScientifique(), etape1.getSymboleScientifique());
    }
}