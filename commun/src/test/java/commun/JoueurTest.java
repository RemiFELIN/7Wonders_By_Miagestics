package commun;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import commun.Carte;
import commun.Joueur;
import commun.Merveille;
import static commun.Couleur.*;
import static commun.Ressource.*;
import static commun.SymboleScientifique.*;

import java.util.ArrayList;

/**
 * Fichier de test unitaire pour tester la classe Joueur
 */
public class JoueurTest {

    private Joueur joueur;

    @Before
    public final void setUp(){
        joueur = new Joueur(1);
        Merveille m = new Merveille("MerveilleTest", 'A', MINERAI, 3);
        m.ajouterEtape(new Etape(new Ressource[]{BOIS, BOIS}, 3, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, ""), 1);
        joueur.setPlateau(m);
    }

    @Test
    public final void testGetterInitial(){
        assertEquals("joueur id inital :", 1, joueur.getId());
        assertEquals("joueur nombre de piece inital :", 5, joueur.getPiece());
        assertEquals("joueur score inital :", 1, joueur.getScore());
        assertEquals("joueur taille deck main inital :", 0, joueur.getDeckMain().size());
        assertEquals("joueur taille deck plateau inital :", 0, joueur.getDeckPlateau().size());
        assertArrayEquals("joueur jetons victoire initial :", joueur.getJetonsVictoire(), new int[]{0, 0, 0});
        assertEquals("joueur jetons défaite initial :", joueur.getJetonsDefaite(), 0);
        assertEquals("joueur force militaire inital :", 0, joueur.getForceMilitaire());
        assertEquals("joueur test ToString :", "Joueur 1", joueur.toString());
    }

    @Test
    public final void testCalculScorePointVictoire() {
        ArrayList<Carte> deck = new ArrayList<Carte>(5);

        final int nbCarte = 5;
        for(byte i = 0; i<nbCarte; i++)
            deck.add(new Carte("CarteTest", BLANC, 1, 0, 2, 0, 0));
     
        joueur.setDeckMain(deck);
        for(byte i = 0; i<nbCarte; i++)
            joueur.poserCarte(0);

        //2 point d'or + 10 Score pointVictoire
        assertEquals("joueur score avec les pointVictoires :", 11, joueur.getScore());
    }

    @Test
    public final void testCalculScoreScientifiqueUnique(){
        ArrayList<Carte> deck = new ArrayList<Carte>(5);

        final int nbCarte = 5;
        
        for(byte i = 0; i<nbCarte; i++)
            deck.add(new Carte("CarteTest", VERT, 0, COMPAS,"test"));
        
        joueur.setDeckMain(deck);
        assertEquals("joueur taille deck aprés ajout cartes :", nbCarte, joueur.getDeckMain().size());
     
        for(byte i = 0; i<nbCarte; i++)
            joueur.poserCarte(0);

        assertEquals("joueur taille deck aprés pose :", 0, joueur.getDeckMain().size());

        // Le joueur joue 5 cartes donc score = 5*5 + les 2 point d'or donc score = 27
        assertEquals("joueur score aprés pose :", 26, joueur.getScore());
    }

    @Test
    public final void testCalculScoreScientifiqueGroupe(){
        final int nbCarte = 3;
        ArrayList<Carte> deck = new ArrayList<Carte>(nbCarte);

        deck.add(new Carte("CarteTest", VERT, 0, COMPAS,"test"));
        deck.add(new Carte("CarteTest", VERT, 0, TABLETTE,"test"));
        deck.add(new Carte("CarteTest", VERT, 0, ROUAGE,"test"));

        joueur.setDeckMain(deck);
        assertEquals("joueur taille deck aprés ajout cartes :", nbCarte, joueur.getDeckMain().size());
     
        for(byte i = 0; i<nbCarte; i++)
            joueur.poserCarte(0);

        assertEquals("joueur taille deck aprés pose :", 0, joueur.getDeckMain().size());
     
        // Le joueur joue 3 cartes scientifique différentes donc score = 7 + 3 + 1
        assertEquals("joueur score aprés pose :", 11, joueur.getScore());
    }

    @Test
    public final void testGetForceMilitaire(){
        ArrayList<Carte> deck = new ArrayList<Carte>();

        deck.add(new Carte("CarteMilitaireTest", ROUGE, 1, 0, 0, 1, 0));
        joueur.setDeckMain(deck);
        joueur.poserCarte(0);
        deck.clear();

        assertEquals("force militaire avec 1 carte :", 1, joueur.getForceMilitaire());

        deck.add(new Carte("CarteMilitaireTest", ROUGE, 1, 0, 0, 5, 0));
        deck.add(new Carte("CarteMilitaireTest", ROUGE, 1, 0, 0, 4, 0));
        joueur.setDeckMain(deck);
        joueur.poserCarte(0);
        joueur.poserCarte(0);

        assertEquals("joueur force militaire avec 3 carte :", 10, joueur.getForceMilitaire());
    }

    @Test
    public final void testCalculConflitsMilitaire(){

        //Jeton victoire age 1
        joueur.ajouterJetonVictoire(1); //Valeur jeton 1 donc 1 + 1
        assertArrayEquals("joueur jetons victoire aprés ajout 1 :", joueur.getJetonsVictoire(), new int[]{1, 0, 0});
        assertEquals("joueur jetons défaite aprés ajout 1 :", joueur.getJetonsDefaite(), 0);
        assertEquals("joueur score aprés ajout 1 :", 2, joueur.getScore());

        //Jeton victoire age 2
        joueur.ajouterJetonVictoire(2); //Valeur jeton 3 donc 2 + 3
        assertArrayEquals("joueur jetons victoire aprés ajout 2 :", joueur.getJetonsVictoire(), new int[]{1, 1, 0});
        assertEquals("joueur jetons défaite aprés ajout 2 :", joueur.getJetonsDefaite(), 0);
        assertEquals("joueur score aprés ajout 2 :", 5, joueur.getScore());

        //Jeton victoire age 3
        joueur.ajouterJetonVictoire(3); //Valeur jeton 5 donc 5 + 5
        assertArrayEquals("joueur jetons victoire aprés ajout 3 :", joueur.getJetonsVictoire(), new int[]{1, 1, 1});
        assertEquals("joueur jetons défaite aprés ajout 3 :", joueur.getJetonsDefaite(), 0);
        assertEquals("joueur score aprés ajout 3 :", 10, joueur.getScore());

        //Jeton age 3
        joueur.ajouterJetonDefaite(); //Valeur jeton -1 donc 10 - 1
        assertArrayEquals("joueur jetons victoire aprés ajout 4 :", joueur.getJetonsVictoire(), new int[]{1, 1, 1});
        assertEquals("joueur jetons défaite aprés ajout 4 :", joueur.getJetonsDefaite(), 1);
        assertEquals("joueur score aprés ajout 4 :", 9, joueur.getScore());
    }

    @Test
    public final void testPayer(){
        final int paiement = 3;
        assertEquals("joueur envoi paiement :", paiement, joueur.payer(paiement));
        assertEquals("joueur paiement envoyée :", 2, joueur.getPiece());
    }

    @Test
    public final void testRecevoirPaiement(){
        final int paiement = 3;
        joueur.recevoirPaiement(paiement);
        assertEquals("joueur paiement reçu :", 8, joueur.getPiece());
    }

    @Test
    public final void testDefausserCarte(){
        ArrayList<Carte> deck = new ArrayList<Carte>(1);
        Carte c = new Carte("CarteTest", BLANC, 1); 
        deck.add(c);
        joueur.setDeckMain(deck);

        Carte d = joueur.defausserCarte(0);
        testEqualCarte(c, d);
        assertEquals("joueur nombre piece aprés défausse :", 8, joueur.getPiece());
    }

    private final void testEqualCarte(Carte c, Carte d){
        assertEquals("carte equal couleur :", c.getCouleur(), d.getCouleur());
        assertEquals("carte equal nom :", c.getNom(), d.getNom());
        assertEquals("carte equal age :", c.getAge(), d.getAge());
        assertEquals("carte equal cout piece :", c.getCoutPiece(), d.getCoutPiece());
        assertEquals("carte equal pointVictoire :", c.getPointVictoire(), d.getPointVictoire());
        assertEquals("carte equal puissance militaire :", c.getPuissanceMilitaire(), d.getPuissanceMilitaire());
        assertEquals("carte equal piece :", c.getPiece(), d.getPiece());
        assertEquals("carte equal symbole scientifique :", c.getSymboleScientifique(), d.getSymboleScientifique());
        assertEquals("carte equal cout ressources :", c.getCoutRessources(), d.getCoutRessources());
        assertEquals("carte equal ressources :", c.getRessources(), d.getRessources());
        assertEquals("carte equal ressources reçues :", c.getRessourcesRecues(), d.getRessourcesRecues());
        assertEquals("carte equal batiment suivant :", c.getBatimentSuivant(),d.getBatimentSuivant());
    }
}