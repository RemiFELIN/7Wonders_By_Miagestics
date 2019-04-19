package commun;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static commun.TypeAction.*;

/**
 * Fichier de test unitaire pour tester la classe Action
 */
public class ActionTest {

    private Action a;
    private final int idJ = 2, nV = 1, nC = 4;

    @Test
    public void testGetterInitial(){
        a = new Action();
        assertNull("action inital type :", a.getType());
        assertEquals("action inital id joueur :", -1, a.getIdJoueur());
        assertEquals("action inital numero carte :", -1, a.getNumeroCarte());
        assertEquals("action inital num voisin :", 0, a.getNumVoisin());
    } 
    
    @Test
    public void testGetterPoserCarte(){
        a = new Action(PoserCarte, idJ, nC);
        assertEquals("action PoserCarte type :", PoserCarte, a.getType());
        assertEquals("action PoserCarte id joueur :", idJ, a.getIdJoueur());
        assertEquals("action PoserCarte numero carte :", nC, a.getNumeroCarte());
        assertEquals("action PoserCarte num voisin :", 0, a.getNumVoisin());
    }
    
    @Test
    public void testGetterDefausserCarte(){
        a = new Action(DefausserCarte, idJ, nC);
        assertEquals("action DefausserCarte type :", DefausserCarte, a.getType());
        assertEquals("action DefausserCarte id joueur :", idJ, a.getIdJoueur());
        assertEquals("action DefausserCarte numero carte :", nC, a.getNumeroCarte());
        assertEquals("action DefausserCarte num voisin :", 0, a.getNumVoisin());
    }
    
    @Test
    public void testGetterConstruireMerveille(){
        a = new Action(ConstruireMerveille, idJ, nC);
        assertEquals("action ConstruireMerveille type :", ConstruireMerveille, a.getType());
        assertEquals("action ConstruireMerveille id joueur :", idJ, a.getIdJoueur());
        assertEquals("action ConstruireMerveille numero carte :", nC, a.getNumeroCarte());
        assertEquals("action ConstruireMerveille num voisin :", 0, a.getNumVoisin());
    }
    
    @Test
    public void testGetterAcheterRessource(){
        a = new Action(AcheterRessource, idJ, nV, nC);
        assertEquals("action AcheterRessource type :", AcheterRessource, a.getType());
        assertEquals("action AcheterRessource id joueur :", idJ, a.getIdJoueur());
        assertEquals("action AcheterRessource numero carte :", nC, a.getNumeroCarte());
        assertEquals("action AcheterRessource num voisin :", nV, a.getNumVoisin());
    }
}