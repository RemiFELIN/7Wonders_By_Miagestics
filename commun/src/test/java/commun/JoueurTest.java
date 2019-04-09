package commun;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import commun.Carte;
import commun.Joueur;
import static commun.Couleur.*;
import static commun.SymboleScientifique.*;

import java.util.ArrayList;

/**
 * Fichier de test unitaire pour tester la classe Joueur
 */
public class JoueurTest {

    public Joueur joueur;

    @Before
    public final void setUp(){
        joueur = new Joueur(1);
        assertEquals("score inital", 5, joueur.getScore());
        assertEquals("taille deck inital", 0, joueur.getDeckMain().size());
        assertArrayEquals("jetons victoire initial", joueur.getJetonsVictoire(), new int[]{0, 0, 0});
        assertArrayEquals("jetons défaite initial", joueur.getJetonsDefaite(), new int[]{0, 0, 0});
    }

    @Test
    public final void testCalculScoreLaurier() {
        ArrayList<Carte> deck = new ArrayList<Carte>(5);

        final int nbCarte = 5;
        for(byte i = 0; i<nbCarte; i++)
            deck.add(new Carte("CarteTest", BLANC, 1, 0, 2, 0, 0));
     
        joueur.setDeckMain(deck);
        for(byte i = 0; i<nbCarte; i++)
            joueur.poserCarte(0);

        //5 gold + 10 Score laurier
        assertEquals("score avec les lauriers", 15, joueur.getScore());
    }

    @Test
    public final void testCalculScoreScientifiqueUnique(){
        ArrayList<Carte> deck = new ArrayList<Carte>(5);

        final int nbCarte = 5;
        
        for(byte i = 0; i<nbCarte; i++)
            deck.add(new Carte("CarteTest", VERT, 0, COMPAS));
        
        joueur.setDeckMain(deck);
        assertEquals("taille deck aprés ajout cartes", nbCarte, joueur.getDeckMain().size());
     
        for(byte i = 0; i<nbCarte; i++)
            joueur.poserCarte(0);

        assertEquals("taille deck aprés pose", 0, joueur.getDeckMain().size());

        // Le joueur joue 5 cartes donc score = 5*5 + les 5 golds donc score = 30
        assertEquals("score aprés pose", 30, joueur.getScore());
    }

    @Test
    public final void testCalculScoreScientifiqueGroupe(){
        final int nbCarte = 3;
        ArrayList<Carte> deck = new ArrayList<Carte>(nbCarte);

        deck.add(new Carte("CarteTest", VERT, 0, COMPAS));
        deck.add(new Carte("CarteTest", VERT, 0, TABLETTE));
        deck.add(new Carte("CarteTest", VERT, 0, ROUAGE));

        joueur.setDeckMain(deck);
        assertEquals("taille deck aprés ajout cartes", nbCarte, joueur.getDeckMain().size());
     
        for(byte i = 0; i<nbCarte; i++)
            joueur.poserCarte(0);

        assertEquals("taille deck aprés pose", 0, joueur.getDeckMain().size());
     
        // Le joueur joue 3 cartes scientifique différentes donc score = 7 + 3 + 5 
        assertEquals("score aprés pose", 15, joueur.getScore());
    }

    @Test
    public final void testCalculConflitsMilitaire(){

        //Jeton age 1
        joueur.ajouterJetonVictoire(1); //Valeur jeton 1 donc 5+1
        assertArrayEquals("jetons victoire aprés ajout 1", joueur.getJetonsVictoire(), new int[]{1, 0, 0});
        assertArrayEquals("jetons défaite aprés ajout 1", joueur.getJetonsDefaite(), new int[]{0, 0, 0});
        assertEquals("score aprés ajout 1", 6, joueur.getScore());

        //Jeton age 2
        joueur.ajouterJetonVictoire(2); //Valeur jeton 3 donc 6 + 3
        assertArrayEquals("jetons victoire aprés ajout 2", joueur.getJetonsVictoire(), new int[]{1, 1, 0});
        assertArrayEquals("jetons victoire aprés ajout 2", joueur.getJetonsDefaite(), new int[]{0, 0, 0});
        assertEquals("score aprés ajout 2", 9, joueur.getScore());

        //Jeton age 3
        joueur.ajouterJetonDefaite(3); //Valeur jeton -5 donc 9-5
        assertArrayEquals("jetons victoire aprés ajout 3", joueur.getJetonsVictoire(), new int[]{1, 1, 0});
        assertArrayEquals("jetons victoire aprés ajout 3", joueur.getJetonsDefaite(), new int[]{0, 0, 1});
        assertEquals("score aprés ajout 3", 4, joueur.getScore());

        joueur.ajouterJetonDefaite(2); //Valeur jeton -3 donc 4-3
        assertArrayEquals("jetons victoire aprés ajout 4", joueur.getJetonsVictoire(), new int[]{1, 1, 0});
        assertArrayEquals("jetons victoire aprés ajout 4", joueur.getJetonsDefaite(), new int[]{0, 1, 1});
        assertEquals("score aprés ajout 4", 1, joueur.getScore());
    }

    @Test
    public final void testPayer(){
        final int paiement = 3;
        assertEquals("envoi paiement", paiement, joueur.payer(paiement));
        assertEquals("paiement envoyée", 2, joueur.getPiece());
    }

    @Test
    public final void testRecevoirPaiement(){
        final int paiement = 3;
        joueur.recevoirPaiement(paiement);
        assertEquals("paiement reçu", 8, joueur.getPiece());
    }    

    @Test
    public final void testToString(){
        assertEquals("test ToString", "Joueur 1", joueur.toString());
    }

    @Test
    public final void testDefausserCarte(){
        ArrayList<Carte> deck = new ArrayList<Carte>(1);
        Carte c = new Carte("CarteTest", BLANC, 1); 
        deck.add(c);
        joueur.setDeckMain(deck);

        Carte d = joueur.defausserCarte(0);
        testEqualCarte(c, d);
        assertEquals("nombre piece aprés défausse", 8, joueur.getPiece());
    }

    private final void testEqualCarte(Carte c, Carte d){
        assertEquals("carte nom", c.getNom(), d.getNom());
        assertEquals("carte age", c.getAge(), d.getAge());
        assertEquals("carte couleur", c.getCouleur(), d.getCouleur());
        assertEquals("carte cout piece", c.getCoutPiece(), d.getCoutPiece());
        assertEquals("carte laurier", c.getLaurier(), d.getLaurier());
        assertEquals("carte puissance militaire", c.getPuissanceMilitaire(), d.getPuissanceMilitaire());
        assertEquals("carte piece", c.getPiece(), d.getPiece());
        assertEquals("carte ressources", c.getRessources(), d.getRessources());
        assertEquals("carte cout ressources", c.getCoutRessources(), d.getCoutRessources());
        assertEquals("carte symbole scientifique", c.getSymboleScientifique(), d.getSymboleScientifique());
    }
}