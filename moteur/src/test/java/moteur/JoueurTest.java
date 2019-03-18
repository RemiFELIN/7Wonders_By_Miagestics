package moteur;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
            deck.add(new Carte("CarteTest", Couleur.BLANC, 1, 0, 2, 0, 0));
     
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
            deck.add(new Carte("CarteTest", Couleur.VERT, 0, 0, 0, 0, 0,SymboleScientifique.COMPAS));
     
        joueur.setDeckMain(deck);
        for(byte i = 0; i<nbCarte; i++)
            joueur.poserCarte(0);

        // Le joueur joue 5 cartes donc score = 5*5 + les 5 golds donc score = 30
        assertEquals(30, joueur.getScore());
    }
    public void testCalculScoreScientifiqueGroupe(){
        ArrayList<Carte> deck = new ArrayList<Carte>(5);     
        int nbCarte = 3;
        deck.add(new Carte("CarteTest", Couleur.VERT, 0, 0, 0, 0, 0,SymboleScientifique.COMPAS));
        deck.add(new Carte("CarteTest", Couleur.VERT, 0, 0, 0, 0, 0,SymboleScientifique.TABLETTE));
        deck.add(new Carte("CarteTest", Couleur.VERT, 0, 0, 0, 0, 0,SymboleScientifique.ROUAGE));

        joueur.setDeckMain(deck);
        for(byte i = 0; i<nbCarte; i++)
            joueur.poserCarte(0);

        // Le joueur joue 3 cartes scientifique différentes donc score = 7 + 3 + 5 
        assertEquals(15, joueur.getScore());
    }
}