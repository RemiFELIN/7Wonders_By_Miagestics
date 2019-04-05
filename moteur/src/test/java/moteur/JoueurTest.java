package moteur;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static moteur.Couleur.*;
import static moteur.SymboleScientifique.*;

import java.util.ArrayList;

/**
 * Fichier de test unitaire pour tester la classe Joueur
 */
public class JoueurTest {

    public Joueur joueur;

    @Before
    public void setUp(){
        joueur = new Joueur(1);
    }

    @Test
    public void testCalculScore() {
        ArrayList<Carte> deck = new ArrayList<Carte>(5);
        int nbCarte = 5;
        for(byte i = 0; i<nbCarte; i++)
            deck.add(new Carte("CarteTest", BLANC, 1, 0, 2, 0, 0));
     
        joueur.setDeckMain(deck);
        for(byte i = 0; i<nbCarte; i++)
            joueur.poserCarte(0);

        //5 gold + 10 Score laurier
        assertEquals(15, joueur.getScore());
    }

    @Test
    public void testCalculScoreScientifiqueCarre(){
        ArrayList<Carte> deck = new ArrayList<Carte>(5);
        
        int nbCarte = 5;
        
        for(byte i = 0; i<nbCarte; i++)
            deck.add(new Carte("CarteTest", VERT, 0, COMPAS));
     
        joueur.setDeckMain(deck);
        for(byte i = 0; i<nbCarte; i++)
            joueur.poserCarte(0);

        // Le joueur joue 5 cartes donc score = 5*5 + les 5 golds donc score = 30
        assertEquals(30, joueur.getScore());
    }

    @Test
    public void testCalculScoreScientifiqueGroupe(){
        ArrayList<Carte> deck = new ArrayList<Carte>(5);     
        int nbCarte = 3;
        deck.add(new Carte("CarteTest", VERT, 0, COMPAS));
        deck.add(new Carte("CarteTest", VERT, 0, TABLETTE));
        deck.add(new Carte("CarteTest", VERT, 0, ROUAGE));

        joueur.setDeckMain(deck);
        for(byte i = 0; i<nbCarte; i++)
            joueur.poserCarte(0);

        // Le joueur joue 3 cartes scientifique différentes donc score = 7 + 3 + 5 
        assertEquals(15, joueur.getScore());
    }

    @Test
    public void testCalculConflitsMilitaire(){
        assertEquals(5, joueur.getScore());

        //Jeton age 1
        joueur.ajouterJetonVictoire(1); //Valeur jeton 1 donc 5+1
        assertEquals(6, joueur.getScore());

        //Jeton age 2
        joueur.ajouterJetonVictoire(2); //Valeur jeton 3 donc 6 + 3
        assertEquals(9, joueur.getScore());

        //Jeton age 3
        joueur.ajouterJetonDefaite(3); //Valeur jeton -5 donc 9-5
        assertEquals(4, joueur.getScore());

        joueur.ajouterJetonDefaite(2); //Valeur jeton -3 donc 4-3
        assertEquals(1, joueur.getScore());
    }
}