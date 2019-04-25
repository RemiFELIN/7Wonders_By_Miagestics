package commun;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import commun.Carte;
import commun.Joueur;
import commun.Merveille;
import commun.VisionJeu;
import static commun.Couleur.*;
import static commun.Ressource.*;
import static commun.EffetGuilde.*;
import static commun.SymboleScientifique.*;

import java.util.ArrayList;

/**
 * Fichier de test unitaire pour tester la classe Joueur
 */
public class JoueurTest {

    private Joueur joueur;
    private VisionJeu vGauche, vDroite;

    @Before
    public final void setUp(){
        joueur = new Joueur(1);
        Merveille m = new Merveille("MerveilleTest", 'A', MINERAI, 1);
        m.ajouterEtape(new Etape(new Ressource[]{BOIS, BOIS}, 3, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, ""), 1);
        joueur.setPlateau(m);
        vGauche = new VisionJeu(0, 0, new int[]{0,0,0}, 0, m, new ArrayList<Carte>());
        vDroite = new VisionJeu(2, 0, new int[]{0,0,0}, 0, m, new ArrayList<Carte>());
    }

    @Test
    public final void testGetterInitial(){
        assertEquals("joueur id inital :", 1, joueur.getId());
        assertEquals("joueur score inital :", 1, joueur.getScore(vGauche, vDroite));
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
            deck.add(new Carte("CarteTest", BLANC, 1, 0, 2, 0));
     
        joueur.setDeckMain(deck);
        for(byte i = 0; i<nbCarte; i++)
            joueur.poserCarte(0);

        //2 point d'or + 10 Score pointVictoire
        assertEquals("joueur score avec les pointVictoires :", 11, joueur.getScore(vGauche, vDroite));
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
        assertEquals("joueur score aprés pose :", 26, joueur.getScore(vGauche, vDroite));
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
        assertEquals("joueur score aprés pose :", 11, joueur.getScore(vGauche, vDroite));
    }

    @Test
    public final void testGetForceMilitaire(){
        ArrayList<Carte> deck = new ArrayList<Carte>();

        deck.add(new Carte("CarteMilitaireTest", ROUGE, 1, 0, 0, 1));
        joueur.setDeckMain(deck);
        joueur.poserCarte(0);
        deck.clear();

        assertEquals("force militaire avec 1 carte :", 1, joueur.getForceMilitaire());

        deck.add(new Carte("CarteMilitaireTest", ROUGE, 1, 0, 0, 5));
        deck.add(new Carte("CarteMilitaireTest", ROUGE, 1, 0, 0, 4));
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
        assertEquals("joueur score aprés ajout 1 :", 2, joueur.getScore(vGauche, vDroite));

        //Jeton victoire age 2
        joueur.ajouterJetonVictoire(2); //Valeur jeton 3 donc 2 + 3
        assertArrayEquals("joueur jetons victoire aprés ajout 2 :", joueur.getJetonsVictoire(), new int[]{1, 1, 0});
        assertEquals("joueur jetons défaite aprés ajout 2 :", joueur.getJetonsDefaite(), 0);
        assertEquals("joueur score aprés ajout 2 :", 5, joueur.getScore(vGauche, vDroite));

        //Jeton victoire age 3
        joueur.ajouterJetonVictoire(3); //Valeur jeton 5 donc 5 + 5
        assertArrayEquals("joueur jetons victoire aprés ajout 3 :", joueur.getJetonsVictoire(), new int[]{1, 1, 1});
        assertEquals("joueur jetons défaite aprés ajout 3 :", joueur.getJetonsDefaite(), 0);
        assertEquals("joueur score aprés ajout 3 :", 10, joueur.getScore(vGauche, vDroite));

        //Jeton age 3
        joueur.ajouterJetonDefaite(); //Valeur jeton -1 donc 10 - 1
        assertArrayEquals("joueur jetons victoire aprés ajout 4 :", joueur.getJetonsVictoire(), new int[]{1, 1, 1});
        assertEquals("joueur jetons défaite aprés ajout 4 :", joueur.getJetonsDefaite(), 1);
        assertEquals("joueur score aprés ajout 4 :", 9, joueur.getScore(vGauche, vDroite));
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
        assertEquals("carte equal symbole scientifique :", c.getSymboleScientifique(), d.getSymboleScientifique());
        assertEquals("carte equal cout ressources :", c.getCoutRessources(), d.getCoutRessources());
        assertEquals("carte equal ressources :", c.getRessources(), d.getRessources());
        assertEquals("carte equal batiment suivant :", c.getBatimentSuivant(), d.getBatimentSuivant());
        assertEquals("carte equal effet guilde :", c.getEffetGuilde(), d.getEffetGuilde());
    }

    @Test
    public final void testScoreGuilde(){

        ArrayList<Carte> deckG = new ArrayList<Carte>(3);
        deckG.add(new Carte("CarteRougeTest", ROUGE, 1));
        deckG.add(new Carte("CarteBleuTest", BLEU, 1));
        deckG.add(new Carte("CarteMarronTest", MARRON, 1));
        Merveille mG = new Merveille("MerveilleTestGauche", 'A', MINERAI, 2);
        Etape e = new Etape(new Ressource[]{}, 0, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
        e.construire();
        mG.ajouterEtape(e, 1);
        mG.ajouterEtape(new Etape(new Ressource[]{}, 0, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, ""), 2);
        vGauche = new VisionJeu(0, 0, new int[]{0,0,0}, 2, mG, deckG);

        ArrayList<Carte> deckD = new ArrayList<Carte>(3);
        deckD.add(new Carte("CarteGrisTest", GRIS, 1));
        deckD.add(new Carte("CarteJauneTest", JAUNE, 1));
        deckD.add(new Carte("CarteVertTest", VERT, 1));
        Merveille mD = new Merveille("MerveilleTestDroite", 'B', ARGILE, 3);
        e = new Etape(new Ressource[]{}, 0, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
        e.construire();
        mD.ajouterEtape(e, 1);
        e = new Etape(new Ressource[]{}, 0, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
        e.construire();
        mD.ajouterEtape(e, 2);
        mD.ajouterEtape(new Etape(new Ressource[]{}, 0, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, ""), 3);
        vDroite = new VisionJeu(2, 0, new int[]{0,0,0}, 1, mD, deckD);
        
        ArrayList<Carte> deck = new ArrayList<Carte>(10);
        deck.add(new Carte(ESPIONS));
        deck.add(new Carte(MAGISTRATS));
        deck.add(new Carte(TRAVAILLEURS));
        deck.add(new Carte(ARTISANS));
        deck.add(new Carte(COMMERCANTS));
        deck.add(new Carte(PHILOSOPHES));
        deck.add(new Carte(BATISSEURS));
        deck.add(new Carte(ARMATEURS));
        deck.add(new Carte(STRATEGES));
        deck.add(new Carte(SCIENTIFIQUES));
        joueur.setDeckMain(deck);

        joueur.poserCarte(0);
        assertEquals("score guilde ESPIONS", 2, joueur.getScore(vGauche, vDroite));
        joueur.poserCarte(0);
        assertEquals("score guilde MAGISTRATS", 3, joueur.getScore(vGauche, vDroite));
        joueur.poserCarte(0);
        assertEquals("score guilde TRAVAILLEURS", 4, joueur.getScore(vGauche, vDroite));
        joueur.poserCarte(0);
        assertEquals("score guilde ARTISANS", 6, joueur.getScore(vGauche, vDroite));
        joueur.poserCarte(0);
        assertEquals("score guilde COMMERCANTS", 7, joueur.getScore(vGauche, vDroite));
        joueur.poserCarte(0);
        assertEquals("score guilde PHILOSOPHES", 8, joueur.getScore(vGauche, vDroite));
        joueur.poserCarte(0);
        assertEquals("score guilde BATISSEURS", 11, joueur.getScore(vGauche, vDroite));
        joueur.poserCarte(0);
        assertEquals("score guilde ARMATEURS", 20, joueur.getScore(vGauche, vDroite));
        joueur.poserCarte(0);
        assertEquals("score guilde STRATEGES", 24, joueur.getScore(vGauche, vDroite));
        joueur.poserCarte(0);
        assertEquals("score guilde SCIENTIFIQUES", 25, joueur.getScore(vGauche, vDroite));
    }
}