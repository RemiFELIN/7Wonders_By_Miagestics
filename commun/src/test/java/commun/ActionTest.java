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
    public final void testGetterInitial(){
        a = new Action();
        assertNull("action inital type :", a.getType());
        assertEquals("action inital id joueur :", -1, a.getIdJoueur());
        assertEquals("action inital numero carte :", -1, a.getNumeroCarte());
        assertEquals("action inital num voisin :", 0, a.getNumVoisin());
    } 
    
    @Test
    public final void testGetterPoserCarte(){
        a = new Action(PoserCarte, idJ, nC);
        testAction(a, PoserCarte, idJ, nC, 0);
    }
    
    @Test
    public final void testGetterDefausserCarte(){
        a = new Action(DefausserCarte, idJ, nC);
        testAction(a, DefausserCarte, idJ, nC, 0);
    }
    
    @Test
    public final void testGetterConstruireMerveille(){
        a = new Action(ConstruireMerveille, idJ, nC);
        testAction(a, ConstruireMerveille, idJ, nC, 0);
    }
    
    @Test
    public final void testGetterAcheterRessource(){
        a = new Action(AcheterRessource, idJ, nV, nC);
        testAction(a, AcheterRessource, idJ, nC, nV);
    }

    private final void testAction(Action a, TypeAction ta, int id, int nc, int nv){
        assertEquals("action "+ta.toString()+" type :", ta, a.getType());
        assertEquals("action "+ta.toString()+" id joueur :", id, a.getIdJoueur());
        assertEquals("action "+ta.toString()+" numero carte :", nc, a.getNumeroCarte());
        assertEquals("action "+ta.toString()+" num voisin :", nv, a.getNumVoisin());
    }
}