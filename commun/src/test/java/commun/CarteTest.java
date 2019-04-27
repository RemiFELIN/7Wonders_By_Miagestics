package commun;

import static commun.Couleur.*;
import static commun.SymboleScientifique.*;
import static commun.Ressource.*;
import static commun.EffetGuilde.*;
import static commun.EffetCommercial.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Fichier de test unitaire pour tester la classe Carte
 */
public class CarteTest {

    private Carte c;
    private final String nom = "CarteTest", batSuiv = "BatimentTest";
    private final Couleur couleur = BLANC;
    private final int age = 1, coutPiece = 3, pointVictoire = 4, puissanceMilitaire = 5;
    private final SymboleScientifique symboleScientifique = ROUAGE;
    private ArrayList<Ressource> coutRessources, ressources;
    private final EffetGuilde effetGuilde = SCIENTIFIQUES;
    private final EffetCommercial effetCommercial = BONUS_ETAPE_MERVEILLE;
    
    @Before
    public final void setUp(){
        coutRessources = new ArrayList<Ressource>();
        ressources = new ArrayList<Ressource>();
        coutRessources.add(MINERAI);
        coutRessources.add(PIERRE);
        ressources.add(ARGILE);
        ressources.add(ARGILE);
        ressources.add(TEXTILE);
    }

    @Test
    public final void testGetterCarteBasique(){
        c = new Carte(nom, couleur, age);
        testCarte(c, "Basique", nom, couleur, age, 0, 0, 0, null, "", null, null);
    }

    @Test
    public final void testGetterCarteClassique(){
        c = new Carte(nom, couleur, age, coutPiece, pointVictoire, puissanceMilitaire);
        testCarte(c, "Classique", nom, couleur, age, coutPiece, pointVictoire, puissanceMilitaire, null, "", null, null);
    }

    @Test
    public final void testGetterCarteScientifique(){
        c = new Carte(nom, couleur, age, symboleScientifique);
        testCarte(c, "Scientifique", nom, couleur, age, 0, 0, 0, symboleScientifique, "", null, null);
    }

    @Test
    public final void testGetterCarteScientifiqueAvecBatiment(){
        c = new Carte(nom, couleur, age, symboleScientifique, batSuiv);
        testCarte(c, "ScientifiqueAvecBatiment", nom, couleur, age, 0, 0, 0, symboleScientifique, batSuiv, null, null);
    }

    @Test
    public final void testGetterCarteComplete(){
        c = new Carte(nom, couleur, age, coutPiece, pointVictoire, puissanceMilitaire, batSuiv);
        testCarte(c, "Complete", nom, couleur, age, coutPiece, pointVictoire, puissanceMilitaire, null, batSuiv, null, null);
    }

    @Test
    public final void testGetterCarteGuilde(){
        c = new Carte(effetGuilde);
        testCarte(c, "Guilde", "Guilde des " + effetGuilde.toString(), VIOLET, 3, 0, 0, 0, null, "", effetGuilde, null);
    }

    @Test
    public final void testGetterCarteCommercial(){
        c = new Carte(nom, couleur, age, effetCommercial);
        testCarte(c, "Commercial", nom, couleur, age, 0, 0, 0, null, "", null, effetCommercial);
    }

    @Test
    public final void testGetterCarteCommercialAvecBatiment(){
        c = new Carte(nom, couleur, age, effetCommercial, batSuiv);
        testCarte(c, "CommercialAvecBatiment", nom, couleur, age, 0, 0, 0, null, batSuiv, null, effetCommercial);
    }

    private final void testCarte(Carte c, String type, String nom, Couleur couleur,
        int age, int coutPiece, int pointVictoire, int puissanceMilitaire,
        SymboleScientifique symboleScientifique, String batSuiv, EffetGuilde effetGuilde, EffetCommercial effetCommercial){
        assertEquals("carte "+type+" nom :", nom, c.getNom());
        assertEquals("carte "+type+" couleur :", couleur, c.getCouleur());
        assertEquals("carte "+type+" age :", age, c.getAge());
        assertEquals("carte "+type+" coutPiece :", coutPiece, c.getCoutPiece());
        assertEquals("carte "+type+" pointVictoire :", pointVictoire, c.getPointVictoire());
        assertEquals("carte "+type+" puissanceMilitaire :", puissanceMilitaire, c.getPuissanceMilitaire());
        assertEquals("carte "+type+" ressources taille :", 0, c.getRessources().size());
        assertEquals("carte "+type+" coutRessources taille :", 0, c.getCoutRessources().size());
        assertEquals("carte "+type+" batSuiv :", batSuiv, c.getBatimentSuivant());
        assertEquals("carte "+type+" symbole scientifique :", symboleScientifique, c.getSymboleScientifique());
        assertEquals("carte "+type+" effet guilde :", effetGuilde, c.getEffetGuilde());
        assertEquals("carte "+type+" effet commercial :", effetCommercial, c.getEffetCommercial());
    }
}