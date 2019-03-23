package moteur;

import java.util.ArrayList;
import java.util.Collections;
import static moteur.Couleur.*;
import static moteur.SymboleScientifique.*;
import static moteur.Ressource.*;

public class Carte {

    private Couleur couleur;
    private String nom;
    private int age;

    private int coutPiece = 0;
    private int laurier = 0;
    private int puissanceMilitaire = 0;
    private int piece = 0;
    private SymboleScientifique symboleScientifique;
    private ArrayList<Ressource> coutRessources = new ArrayList<Ressource>();
    private ArrayList<Ressource> ressources = new ArrayList<Ressource>();
    private ArrayList<Ressource> ressourcesRecues = new ArrayList<Ressource>();

    //TODO Dans une future itération
    /*
    private String symboleScientifique; // TODO enum a rajouter par la suite
    private String batimentSuivant = ""; // TODO changer avec une carte ?
    private String effetSpecial = ""; // TODO changer string par un enum ?
    */

    public Carte(String nom, Couleur couleur, int age) {
        this.nom = nom;
        this.couleur = couleur;// couleur MARRON pour les ressources
        this.age = age;
    }

    public Carte(String nom, Couleur couleur, int age, int coutPiece, int laurier, int puissanceMilitaire, int piece){
        this(nom, couleur, age);
        this.coutPiece = coutPiece;
        this.laurier = laurier;
        this.puissanceMilitaire = puissanceMilitaire;
        this.piece = piece;
    }

    public Carte(String nom, Couleur couleur, int age, SymboleScientifique symboleScientifique){
        this(nom, couleur, age);
        this.symboleScientifique = symboleScientifique;
    }

    public void ajouterCoutRessource(Ressource res){ coutRessources.add(res); }



    /*************************/
    /* POUR LES GUILDES*/
    public void ajouterRessourcesRecues(Ressource res){ ressourcesRecues.add(res); }

    public void ajouterPieces(int res){ piece += res; }
    /*************************/



    public void ajouterRessource(Ressource res){ ressources.add(res); }

    public static ArrayList<Carte> getDeck(){
        ArrayList<Carte> deck = new ArrayList<Carte>();
        //Carte commercial
        //TODO ajouter toutes les cartes commercial + function addCartesCommercial
        deck.add(new Carte("Taverne", JAUNE, 1, 0, 0, 0, 5));

        addCartesVictoire(deck);
        addCartesMilitaires(deck);
        addCartesRessources(deck);
        addCartesScientifique(deck);
        return deck;
    }

    public static ArrayList<Carte> getDeckGuildes(int nombreDeJoueur){
        ArrayList<Carte> deckDeGuilde = genererCarteGuildes();
        ArrayList<Carte> deckUtilisé = new ArrayList<Carte>();
        //on melange
        Collections.shuffle(deckDeGuilde);
        //on constitue ...
        for(int i=0; i<nombreDeJoueur+2; i++)
            deckUtilisé.add(deckDeGuilde.get(i));
        //on retourne
        return deckUtilisé;
    }

    public final Couleur getCouleur() { return couleur; }
    public final String getNom() { return nom; }
    public final int getAge() { return age; }
    public final int getCoutPiece() { return coutPiece; }
    public final int getLaurier() { return laurier; }
    public final int getPuissanceMilitaire() { return puissanceMilitaire; }
    
    //TODO Dans une future itération
    /*
    public final String getSymboleScientifique() { return symboleScientifique; }
    public final String getDescription() { return description; }
    public final String getNextBuilding() { return nextBuilding; }
    public final String getSpecialEffect() { return specialEffect;}
    */

    public final int getPiece() { return piece; }
    public final ArrayList<Ressource> getRessources() { return ressources; }
    public final ArrayList<Ressource> getCoutRessources() { return coutRessources; }
    public final SymboleScientifique getSymboleScientifique(){ return symboleScientifique; }

    private static ArrayList<Carte> genererCarteGuildes(){
        ArrayList<Carte> deck = new ArrayList<Carte>();
        /* 10 cartes Guildes au total: en fct du nombre de joueur, le nb de guildes equivaut à joueur + 2*/

        //Guilde des travailleurs
        Carte c = new Carte("Guilde des travailleurs", VIOLET, 3);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(BOIS);
        //Pour chaques cartes MARRON que j'ai dans mon deck, je gagne un point de victoire

        deck.add(c);


        //Guilde des artisans
        c = new Carte("Guilde des artisans", VIOLET, 3);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(PIERRE);
        //pour chaques cartes grises que j'ai dans mon deck, je gagne deux points de victoire

        deck.add(c);

        //Guilde des commerçants
        c = new Carte("Guilde des commerçants", VIOLET, 3);
        c.ajouterCoutRessource(TEXTILE);
        c.ajouterCoutRessource(PAPYRUS);
        c.ajouterCoutRessource(VERRE);
        //Pour chaques cartes jaunes que j'ai dans mon deck, je gagne un point de victoire

        deck.add(c);

        //Guilde des philosophes
        c = new Carte("Guilde des philosophes", VIOLET, 3);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(TEXTILE);
        c.ajouterCoutRessource(PAPYRUS);
        //Pour chaques cartes scientifiques que j'ai dans mon deck, je gagne un point de victoire

        deck.add(c);

        //Guilde des espions
        c = new Carte("Guilde des espions", VIOLET, 3);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(VERRE);
        //Pour chaques cartes conflits que j'ai dans mon deck, je gagne un point de victoire

        deck.add(c);

        //Guilde des stratèges
        c = new Carte("Guilde des stratèges", VIOLET, 3);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(PAPYRUS);
        //Pour chaques malus de conflits que j'ai reçu, je reçoit un point de victoire

        deck.add(c);

        //Guilde des armateurs
        c = new Carte("Guilde des armateurs", VIOLET, 3);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(PAPYRUS);
        c.ajouterCoutRessource(VERRE);
        //Pour chaques cartes marrons, grises et violettes, je gagne un point de victoire

        deck.add(c);

        //Guilde des scientifiques
        c = new Carte("Guilde des scientifiques", VIOLET, 3);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(PAPYRUS);
        //Je recoit une carte compas, rouage et tablette

        deck.add(c);

        //Guilde des magistrats
        c = new Carte("Guilde des magistrats", VIOLET, 3);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(TEXTILE);
        //Pour chaques cartes bleues que j'ai dans mon deck, je gagne un point de victoire

        deck.add(c);

        //Guilde des bâtisseurs
        c = new Carte("Guilde des bâtisseurs", VIOLET, 3);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(VERRE);
        //Pour chaques merveilles construites, je gagne un point de victoire

        deck.add(c);

        // On retourne notre deck contenant 10 guildes
        return deck;
    }

    private final static void addCartesScientifique(ArrayList<Carte> deck){

        //-----------Cartes Scientifique------------------
        Carte c = new Carte("Apothicaire",VERT,1,COMPAS);
        c.ajouterRessource(TEXTILE);
        deck.add(c);

        c = new Carte("Scriptorium",VERT,1,TABLETTE);
        c.ajouterCoutRessource(PAPYRUS);
        deck.add(c);

        c = new Carte("Atelier",VERT,1,ROUAGE);
        c.ajouterCoutRessource(VERRE);
        deck.add(c);

        c = new Carte("Laboratoire",VERT,1,ROUAGE);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(PAPYRUS);
        deck.add(c);

        c = new Carte("Ecole",VERT,1,TABLETTE);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(PAPYRUS);
        deck.add(c);

        c = new Carte("Dispensaire",VERT,1,COMPAS);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(VERRE);
        deck.add(c);

        c = new Carte("Librairie",VERT,1,TABLETTE);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(TEXTILE);
        deck.add(c);

        c = new Carte("Academie",VERT,1,COMPAS);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(VERRE);
        deck.add(c);

        c = new Carte("Universite",VERT,1,TABLETTE);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(PAPYRUS);
        c.ajouterCoutRessource(VERRE);
        deck.add(c);

        c = new Carte("Loge",VERT,1,COMPAS);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(PAPYRUS);
        c.ajouterCoutRessource(TEXTILE);
        deck.add(c);

        c = new Carte("Salle d'etude",VERT,1,ROUAGE);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(PAPYRUS);
        c.ajouterCoutRessource(TEXTILE);
        deck.add(c);

    }

    private static void addCartesMilitaires(ArrayList<Carte> deck){   
        //cartes militaires de l'âge 1

        Carte c = new Carte("Caserne", ROUGE, 1, 0, 0, 1, 0);
        c.ajouterCoutRessource(MINERAI);
        deck.add(c);

        c = new Carte("Palissade", ROUGE, 1, 0, 0, 1, 0);
        c.ajouterCoutRessource(BOIS);
        deck.add(c);

        c = new Carte("Tour de garde", ROUGE, 1, 0, 0, 1, 0);
        c.ajouterCoutRessource(ARGILE);
        deck.add(c);

        c = new Carte("Tour de garde", ROUGE, 1, 0, 0, 1, 0);
        c.ajouterCoutRessource(ARGILE);
        deck.add(c);

        //cartes militaires de l'âge 2

        c = new Carte("Ecuries", ROUGE, 2, 0, 0, 1, 0);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(MINERAI);
        deck.add(c);

        c = new Carte("Murs", ROUGE, 2, 0, 0, 1, 0);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(PIERRE);
        deck.add(c);

        c = new Carte("Tir à l'arc", ROUGE, 2, 0, 0, 1, 0);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(PIERRE);
        deck.add(c);

        c = new Carte("Terrain d'entraînement", ROUGE, 2, 0, 0, 1, 0);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(BOIS);
        deck.add(c);

        //cartes militaires de l'âge 3

        c = new Carte("Cirque", ROUGE, 3, 0, 0, 1, 0);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(MINERAI);
        deck.add(c);

        c = new Carte("Arsenal", ROUGE, 3, 0, 0, 1, 0);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(TEXTILE);
        deck.add(c);

        c = new Carte("Fortifications", ROUGE, 3, 0, 0, 1, 0);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(PIERRE);
        deck.add(c);

        c = new Carte("Atelier de siège", ROUGE, 3, 0, 0, 1, 0);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(BOIS);
        deck.add(c);
    }

    private static void addCartesVictoire(ArrayList<Carte> deck){
        //cartes victoire de l'âge 1

        deck.add(new Carte("Theatre", BLEU, 1, 0, 2, 0, 0));

        Carte c = new Carte("Prêteur sur gage", BLEU, 1, 0, 2, 0, 0);
        deck.add(c);

        c = new Carte("Autel", BLEU, 1, 0, 2, 0, 0);
        deck.add(c);

        c = new Carte("Bains", BLEU, 1, 0, 3, 0, 0);
        c.ajouterCoutRessource(PIERRE);
        deck.add(c);

        //cartes victoire de l'âge 2

        c = new Carte("Tribunal", BLEU, 2, 0, 4, 0, 0);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(TEXTILE);
        deck.add(c);

        c = new Carte("Statue", BLEU, 2, 0, 4, 0, 0);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(BOIS);
        deck.add(c);

        c = new Carte("Temple", BLEU, 2, 0, 3, 0, 0);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(VERRE);
        deck.add(c);

        c = new Carte("Aqueduc", BLEU, 2, 0, 5, 0, 0);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(PIERRE);
        deck.add(c);

        //cartes victoire de l'âge 3

        c = new Carte("Palais", BLEU, 3, 0, 8, 0, 0);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(VERRE);
        c.ajouterCoutRessource(PAPYRUS);
        c.ajouterCoutRessource(TEXTILE);
        deck.add(c);

        c = new Carte("Jardins", BLEU, 3, 0, 5, 0, 0);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(BOIS);
        deck.add(c);

        c = new Carte("Panthéon", BLEU, 3, 0, 7, 0, 0);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(VERRE);
        c.ajouterCoutRessource(PAPYRUS);
        c.ajouterCoutRessource(TEXTILE);
        deck.add(c);

        c = new Carte("Mairie", BLEU, 3, 0, 6, 0, 0);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(VERRE);
        deck.add(c);

        c = new Carte("Sénat", BLEU, 3, 0, 6, 0, 0);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(MINERAI);
        deck.add(c);

    }

    private static void addCartesRessources(ArrayList<Carte> deck){ 
        //cartes ressources de l'âge 1

        Carte c = new Carte("Fosse", MARRON, 1);
        c.ajouterRessource(PIERRE);
        deck.add(c);

        c = new Carte("Cour à bois", MARRON, 1);
        c.ajouterRessource(BOIS);
        deck.add(c);

        c = new Carte("Piscine d'argile", MARRON, 1);
        c.ajouterRessource(ARGILE);
        deck.add(c);

        c = new Carte("Cavite", MARRON, 1);
        c.ajouterRessource(MINERAI);
        deck.add(c);

        c = new Carte("Forêt", MARRON, 1, 1,0,0,0);
        c.ajouterRessource(BOIS);
        c.ajouterRessource(ARGILE);
        deck.add(c);

        c = new Carte("Excavation", MARRON, 1, 1,0,0,0);
        c.ajouterRessource(PIERRE);
        c.ajouterRessource(ARGILE);
        deck.add(c);

        c = new Carte("Fosse d'argile", MARRON, 1, 1,0,0,0);
        c.ajouterRessource(ARGILE);
        c.ajouterRessource(MINERAI);
        deck.add(c);

        c = new Carte("Cour à bois bis", MARRON, 1, 1,0,0,0);
        c.ajouterRessource(PIERRE);
        c.ajouterRessource(BOIS);
        deck.add(c);

        c = new Carte("Cave forestière", MARRON, 1, 1,0,0,0);
        c.ajouterRessource(BOIS);
        c.ajouterRessource(MINERAI);
        deck.add(c);

        c = new Carte("Mine", MARRON, 1, 1,0,0,0);
        c.ajouterRessource(PIERRE);
        c.ajouterRessource(MINERAI);
        deck.add(c);

        //cartes ressources de l'âge 2

        c = new Carte("Scierie", MARRON, 2, 1,0,0,0);
        c.ajouterRessource(BOIS);
        c.ajouterRessource(BOIS);
        deck.add(c);

        c = new Carte("Carrière", MARRON, 2, 1,0,0,0);
        c.ajouterRessource(PIERRE);
        c.ajouterRessource(PIERRE);
        deck.add(c);

        c = new Carte("Briquetterie", MARRON, 2, 1,0,0,0);
        c.ajouterRessource(ARGILE);
        c.ajouterRessource(ARGILE);
        deck.add(c);

        c = new Carte("Fonderie", MARRON, 2, 1,0,0,0);
        c.ajouterRessource(MINERAI);
        c.ajouterRessource(MINERAI);
        deck.add(c);
    }
}
