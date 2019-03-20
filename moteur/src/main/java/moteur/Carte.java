package moteur;

import java.util.ArrayList;
import java.util.Collections;

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
    private String batimentSuivant = ""; // TODO changer avec ???
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

    public Carte(String nom, Couleur couleur, int age, int coutPiece, int laurier, int puissanceMilitaire, int piece, SymboleScientifique symboleScientifique){
        this(nom, couleur, age, coutPiece, laurier, puissanceMilitaire, piece);
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
        deck.add(new Carte("Taverne", Couleur.JAUNE, 1, 0, 0, 0, 5));

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
        Carte c = new Carte("Guilde des travailleurs", Couleur.VIOLET, 3);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.BOIS);
        //Pour chaques cartes MARRON que j'ai dans mon deck, je gagne un point de victoire

        deck.add(c);


        //Guilde des artisans
        c = new Carte("Guilde des artisans", Couleur.VIOLET, 3);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.PIERRE);
        //pour chaques cartes grises que j'ai dans mon deck, je gagne deux points de victoire

        deck.add(c);

        //Guilde des commerçants
        c = new Carte("Guilde des commerçants", Couleur.VIOLET, 3);
        c.ajouterCoutRessource(Ressource.TEXTILE);
        c.ajouterCoutRessource(Ressource.PAPYRUS);
        c.ajouterCoutRessource(Ressource.VERRE);
        //Pour chaques cartes jaunes que j'ai dans mon deck, je gagne un point de victoire

        deck.add(c);

        //Guilde des philosophes
        c = new Carte("Guilde des philosophes", Couleur.VIOLET, 3);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.TEXTILE);
        c.ajouterCoutRessource(Ressource.PAPYRUS);
        //Pour chaques cartes scientifiques que j'ai dans mon deck, je gagne un point de victoire

        deck.add(c);

        //Guilde des espions
        c = new Carte("Guilde des espions", Couleur.VIOLET, 3);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.VERRE);
        //Pour chaques cartes conflits que j'ai dans mon deck, je gagne un point de victoire

        deck.add(c);

        //Guilde des stratèges
        c = new Carte("Guilde des stratèges", Couleur.VIOLET, 3);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.PAPYRUS);
        //Pour chaques malus de conflits que j'ai reçu, je reçoit un point de victoire

        deck.add(c);

        //Guilde des armateurs
        c = new Carte("Guilde des armateurs", Couleur.VIOLET, 3);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.PAPYRUS);
        c.ajouterCoutRessource(Ressource.VERRE);
        //Pour chaques cartes marrons, grises et violettes, je gagne un point de victoire

        deck.add(c);

        //Guilde des scientifiques
        c = new Carte("Guilde des scientifiques", Couleur.VIOLET, 3);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.PAPYRUS);
        //Je recoit une carte compas, rouage et tablette

        deck.add(c);

        //Guilde des magistrats
        c = new Carte("Guilde des magistrats", Couleur.VIOLET, 3);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.TEXTILE);
        //Pour chaques cartes bleues que j'ai dans mon deck, je gagne un point de victoire

        deck.add(c);

        //Guilde des bâtisseurs
        c = new Carte("Guilde des bâtisseurs", Couleur.VIOLET, 3);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.VERRE);
        //Pour chaques merveilles construites, je gagne un point de victoire

        deck.add(c);

        // On retourne notre deck contenant 10 guildes
        return deck;
    }


    private final static void addCartesScientifique(ArrayList<Carte> deck){

        //-----------Cartes Scientifique------------------
        Carte c = new Carte("Apothicaire",Couleur.VERT,0,0,0,0,0,SymboleScientifique.COMPAS);
        c.ajouterRessource(Ressource.TEXTILE);
        deck.add(c);

        c = new Carte("Scriptorium",Couleur.VERT,0,0,0,0,0,SymboleScientifique.TABLETTE);
        c.ajouterCoutRessource(Ressource.PAPYRUS);
        deck.add(c);

        c = new Carte("Atelier",Couleur.VERT,0,0,0,0,0,SymboleScientifique.ROUAGE);
        c.ajouterCoutRessource(Ressource.VERRE);
        deck.add(c);

        c = new Carte("Laboratoire",Couleur.VERT,0,0,0,0,0,SymboleScientifique.ROUAGE);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.PAPYRUS);
        deck.add(c);

        c = new Carte("Ecole",Couleur.VERT,0,0,0,0,0,SymboleScientifique.TABLETTE);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.PAPYRUS);
        deck.add(c);

        c = new Carte("Dispensaire",Couleur.VERT,0,0,0,0,0,SymboleScientifique.COMPAS);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.VERRE);
        deck.add(c);

        c = new Carte("Librairie",Couleur.VERT,0,0,0,0,0,SymboleScientifique.TABLETTE);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.TEXTILE);
        deck.add(c);

        c = new Carte("Academie",Couleur.VERT,0,0,0,0,0,SymboleScientifique.COMPAS);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.VERRE);
        deck.add(c);

        c = new Carte("Universite",Couleur.VERT,0,0,0,0,0,SymboleScientifique.TABLETTE);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.PAPYRUS);
        c.ajouterCoutRessource(Ressource.VERRE);
        deck.add(c);

        c = new Carte("Loge",Couleur.VERT,0,0,0,0,0,SymboleScientifique.COMPAS);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.PAPYRUS);
        c.ajouterCoutRessource(Ressource.TEXTILE);
        deck.add(c);

        c = new Carte("Salle d'etude",Couleur.VERT,0,0,0,0,0,SymboleScientifique.ROUAGE);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.PAPYRUS);
        c.ajouterCoutRessource(Ressource.TEXTILE);
        deck.add(c);

    }

    private static void addCartesMilitaires(ArrayList<Carte> deck){   
        //cartes militaires de l'âge 1

        Carte c = new Carte("Caserne", Couleur.ROUGE, 1, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.MINERAI);
        deck.add(c);

        c = new Carte("Palissade", Couleur.ROUGE, 1, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.BOIS);
        deck.add(c);

        c = new Carte("Tour de garde", Couleur.ROUGE, 1, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.ARGILE);
        deck.add(c);

        c = new Carte("Tour de garde", Couleur.ROUGE, 1, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.ARGILE);
        deck.add(c);

        //cartes militaires de l'âge 2

        c = new Carte("Ecuries", Couleur.ROUGE, 2, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.MINERAI);
        deck.add(c);

        c = new Carte("Murs", Couleur.ROUGE, 2, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.PIERRE);
        deck.add(c);

        c = new Carte("Tir à l'arc", Couleur.ROUGE, 2, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.PIERRE);
        deck.add(c);

        c = new Carte("Terrain d'entraînement", Couleur.ROUGE, 2, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.BOIS);
        deck.add(c);

        //cartes militaires de l'âge 3

        c = new Carte("Cirque", Couleur.ROUGE, 3, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.MINERAI);
        deck.add(c);

        c = new Carte("Arsenal", Couleur.ROUGE, 3, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.TEXTILE);
        deck.add(c);

        c = new Carte("Fortifications", Couleur.ROUGE, 3, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.PIERRE);
        deck.add(c);

        c = new Carte("Atelier de siège", Couleur.ROUGE, 3, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.BOIS);
        deck.add(c);
    }

    private static void addCartesVictoire(ArrayList<Carte> deck){
        //cartes victoire de l'âge 1

        deck.add(new Carte("Theatre", Couleur.BLEU, 1, 0, 2, 0, 0));

        Carte c = new Carte("Prêteur sur gage", Couleur.BLEU, 1, 0, 2, 0, 0);
        deck.add(c);

        c = new Carte("Autel", Couleur.BLEU, 1, 0, 2, 0, 0);
        deck.add(c);

        c = new Carte("Bains", Couleur.BLEU, 1, 0, 3, 0, 0);
        c.ajouterCoutRessource(Ressource.PIERRE);
        deck.add(c);

        //cartes victoire de l'âge 2

        c = new Carte("Tribunal", Couleur.BLEU, 2, 0, 4, 0, 0);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.TEXTILE);
        deck.add(c);

        c = new Carte("Statue", Couleur.BLEU, 2, 0, 4, 0, 0);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.BOIS);
        deck.add(c);

        c = new Carte("Temple", Couleur.BLEU, 2, 0, 3, 0, 0);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.VERRE);
        deck.add(c);

        c = new Carte("Aqueduc", Couleur.BLEU, 2, 0, 5, 0, 0);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.PIERRE);
        deck.add(c);

        //cartes victoire de l'âge 3

        c = new Carte("Palais", Couleur.BLEU, 3, 0, 8, 0, 0);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.VERRE);
        c.ajouterCoutRessource(Ressource.PAPYRUS);
        c.ajouterCoutRessource(Ressource.TEXTILE);
        deck.add(c);

        c = new Carte("Jardins", Couleur.BLEU, 3, 0, 5, 0, 0);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.BOIS);
        deck.add(c);

        c = new Carte("Panthéon", Couleur.BLEU, 3, 0, 7, 0, 0);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.VERRE);
        c.ajouterCoutRessource(Ressource.PAPYRUS);
        c.ajouterCoutRessource(Ressource.TEXTILE);
        deck.add(c);

        c = new Carte("Mairie", Couleur.BLEU, 3, 0, 6, 0, 0);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.VERRE);
        deck.add(c);

        c = new Carte("Sénat", Couleur.BLEU, 3, 0, 6, 0, 0);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.MINERAI);
        deck.add(c);

    }

    private static void addCartesRessources(ArrayList<Carte> deck){ 
        //cartes ressources de l'âge 1

        Carte c = new Carte("Fosse", Couleur.MARRON, 1);
        c.ajouterRessource(Ressource.PIERRE);
        deck.add(c);

        c = new Carte("Cour à bois", Couleur.MARRON, 1);
        c.ajouterRessource(Ressource.BOIS);
        deck.add(c);

        c = new Carte("Piscine d'argile", Couleur.MARRON, 1);
        c.ajouterRessource(Ressource.ARGILE);
        deck.add(c);

        c = new Carte("Cavite", Couleur.MARRON, 1);
        c.ajouterRessource(Ressource.MINERAI);
        deck.add(c);

        c = new Carte("Forêt", Couleur.MARRON, 1, 1,0,0,0);
        c.ajouterRessource(Ressource.BOIS);
        c.ajouterRessource(Ressource.ARGILE);
        deck.add(c);

        c = new Carte("Excavation", Couleur.MARRON, 1, 1,0,0,0);
        c.ajouterRessource(Ressource.PIERRE);
        c.ajouterRessource(Ressource.ARGILE);
        deck.add(c);

        c = new Carte("Fosse d'argile", Couleur.MARRON, 1, 1,0,0,0);
        c.ajouterRessource(Ressource.ARGILE);
        c.ajouterRessource(Ressource.MINERAI);
        deck.add(c);

        c = new Carte("Cour à bois bis", Couleur.MARRON, 1, 1,0,0,0);
        c.ajouterRessource(Ressource.PIERRE);
        c.ajouterRessource(Ressource.BOIS);
        deck.add(c);

        c = new Carte("Cave forestière", Couleur.MARRON, 1, 1,0,0,0);
        c.ajouterRessource(Ressource.BOIS);
        c.ajouterRessource(Ressource.MINERAI);
        deck.add(c);

        c = new Carte("Mine", Couleur.MARRON, 1, 1,0,0,0);
        c.ajouterRessource(Ressource.PIERRE);
        c.ajouterRessource(Ressource.MINERAI);
        deck.add(c);

        //cartes ressources de l'âge 2

        c = new Carte("Scierie", Couleur.MARRON, 2, 1,0,0,0);
        c.ajouterRessource(Ressource.BOIS);
        c.ajouterRessource(Ressource.BOIS);
        deck.add(c);

        c = new Carte("Carrière", Couleur.MARRON, 2, 1,0,0,0);
        c.ajouterRessource(Ressource.PIERRE);
        c.ajouterRessource(Ressource.PIERRE);
        deck.add(c);

        c = new Carte("Briquetterie", Couleur.MARRON, 2, 1,0,0,0);
        c.ajouterRessource(Ressource.ARGILE);
        c.ajouterRessource(Ressource.ARGILE);
        deck.add(c);

        c = new Carte("Fonderie", Couleur.MARRON, 2, 1,0,0,0);
        c.ajouterRessource(Ressource.MINERAI);
        c.ajouterRessource(Ressource.MINERAI);
        deck.add(c);
    }
}
