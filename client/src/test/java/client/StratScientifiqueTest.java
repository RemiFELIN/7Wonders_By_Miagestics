package client;

import client.strategie.StratScientifique;
import moteur.*;
import static moteur.TypeAction.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class StratScientifiqueTest {

    @Mock
    StratScientifique stratClient;
    VisionJeu j,jGauche,jDroite;
    Joueur central, gauche, droite;
    ArrayList<Carte> deckMain, deckPlateau;

    @Before
    public void setUp() {
        central=new Joueur(0);
        gauche=new Joueur(1);
        droite=new Joueur(2);

        deckMain = new ArrayList<Carte>();

        Carte c = new Carte("CarteTestRessource", Couleur.BLANC, 0,0,0,0,0);
        deckMain.add(c);

        c = new Carte("CarteTestScientifique", Couleur.VERT, 0, SymboleScientifique.COMPAS);
        c.ajouterRessource(Ressource.MINERAI);
        c.ajouterRessource(Ressource.MINERAI);
        c.ajouterRessource(Ressource.BOIS);
        deckMain.add(c);

        c = new Carte("CarteTestScientifique", Couleur.VERT, 0, SymboleScientifique.COMPAS);
        c.ajouterRessource(Ressource.MINERAI);
        c.ajouterRessource(Ressource.BOIS);
        deckMain.add(c);

        deckPlateau=new ArrayList<Carte>();
        c = new Carte("CarteTestRessource", Couleur.BLANC, 0);
        c.ajouterRessource(Ressource.BOIS);
        deckPlateau.add(c);

        c = new Carte("CarteTestRessource2", Couleur.BLANC, 0);
        c.ajouterRessource(Ressource.MINERAI);
        deckPlateau.add(c);

        j=new VisionJeu(0,0,new Merveille("test",'A',Ressource.BOIS,1),deckMain,deckPlateau);
        jGauche=new VisionJeu(1,0,new Merveille("test2",'A',Ressource.BOIS,1),deckMain,deckPlateau);
        jDroite=new VisionJeu(2,0,new Merveille("test3",'A',Ressource.BOIS,1),deckMain,deckPlateau);
        j.setVoisinDroite(jDroite);
        j.setVoisinGauche(jGauche);
    }

    @Test
    public void testStratScientifique() throws Exception {
        stratClient = new StratScientifique();

        Action pc = stratClient.getAction(j);
        assertEquals(2, pc.getNumeroCarte());
    }
}
