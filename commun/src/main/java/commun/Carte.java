package commun;

import java.util.ArrayList;
import java.util.Collections;
import static commun.Couleur.*;
import static commun.SymboleScientifique.*;
import static commun.Ressource.*;

/**
 * @authors Yannick Cardini, Benoît Montorsi, Rémi Felin, Pierre Saunders
 */
public class Carte {

    private Couleur couleur;
    private String nom;
    private int age;

    private int coutPiece, laurier, puissanceMilitaire, piece;
    private SymboleScientifique symboleScientifique;
    private ArrayList<Ressource> coutRessources = new ArrayList<Ressource>();
    private ArrayList<Ressource> ressources = new ArrayList<Ressource>();
    private ArrayList<Ressource> ressourcesRecues = new ArrayList<Ressource>();

    //TODO Dans une future itération
    /*
    private String batimentSuivant = ""; // TODO changer avec une carte ?
    private String effetSpecial = ""; // TODO changer string par un enum ?
    */

    /**
     * Constructeur carte basique
     * @param un nom
     * @param une couleur 
     * @param un âge
     */
    public Carte(String nom, Couleur couleur, int age) {
        this.nom = nom;
        this.couleur = couleur;// couleur MARRON pour les ressources
        this.age = age;
    }
    /**
     * Constructeur carte classique
     * @param un nom
     * @param une couleur
     * @param un âge
     * @param le coût pièce pour pouvoir poser
     * @param le nombre de laurier bonus si posé
     * @param la puissance militaire si posé
     * @param le nombre de piece bonus si posé
     */
    public Carte(String nom, Couleur couleur, int age, int coutPiece, int laurier, int puissanceMilitaire, int piece){
        this(nom, couleur, age);
        this.coutPiece = coutPiece;
        this.laurier = laurier;
        this.puissanceMilitaire = puissanceMilitaire;
        this.piece = piece;
    }
    /**
     * Constructeur carte scientifique
     * @param un nom
     * @param une couleur
     * @param un âge
     * @param le symbole scientifique bonus si posé
     */
    public Carte(String nom, Couleur couleur, int age, SymboleScientifique symboleScientifique){
        this(nom, couleur, age);
        this.symboleScientifique = symboleScientifique;
    }

    /**
     * @return la couleur
     */
    public final Couleur getCouleur() { return couleur; }
    /**
     * @return le nom
     */
    public final String getNom() { return nom; }
    /**
     * @return l'âge
     */
    public final int getAge() { return age; }
    /**
     * @return le coût pièce pour pouvoir poser
     */
    public final int getCoutPiece() { return coutPiece; }
    /**
     * @return le nombre de laurier bonus si posé
     */
    public final int getLaurier() { return laurier; }
    /**
     * @return la puissance militaire si posé
     */
    public final int getPuissanceMilitaire() { return puissanceMilitaire; }
    /**
     * @return le nombre de piece bonus si posé
     */
    public final int getPiece() { return piece; }
    /**
     * @return les ressources bonus de la carte si posé
     */
    public final ArrayList<Ressource> getRessources() { return ressources; }
    /**
     * @return les ressources néccessaires de la carte pour pouvoir poser
     */
    public final ArrayList<Ressource> getCoutRessources() { return coutRessources; }
    /**
     * @return le symbole scientifique bonus si posé
     */
    public final SymboleScientifique getSymboleScientifique(){ return symboleScientifique; }
    
    //TODO Dans une future itération
    /*
    public final String getNextBuilding() { return nextBuilding; }
    public final String getSpecialEffect() { return specialEffect;}
    */

    /**
     * Complémentaire au constructeur
     * @param une ressource bonus à ajouter si posé
     */
    public final void ajouterRessource(Ressource res){ ressources.add(res); }
    /**
     * Complémentaire au constructeur
     * @param une ressource néccessaire à ajouter pour pouvoir poser
     */
    public final void ajouterCoutRessource(Ressource res){ coutRessources.add(res); }

    /*************************/
    /* POUR LES GUILDES*/
    /**
     * Complémentaire au constructeur de carte guilde
     * @param une ressource bonus de guilde à ajouter si posé
     */
    public final void ajouterRessourcesRecues(Ressource res){ ressourcesRecues.add(res); }
    /**
     * Complémentaire au constructeur de carte guilde
     * @param le nombre de pièce de guilde à ajouter si posé
     */
    public final void ajouterPieces(int res){ piece += res; }
    /*************************/

    /**
     * Parseur de cartes
     * @param l'âge de la partie
     * @param le nombre de joueurs de la partie
     * @return le deck de cartes adapté
     */
    public final static ArrayList<Carte> getDeck(int age, int nbJoueurs){
        ArrayList<Carte> deck = new ArrayList<Carte>();
        //Carte commercial
        //TODO ajouter toutes les cartes commercial + function addCartesCommercial
        // Créer des deck en fonction de l'âge et du nombre de joueurs

        //deck.add(new Carte("Taverne", JAUNE, 1, 0, 0, 0, 5));
        
        addCartesVictoire(deck,age,nbJoueurs);
        addCartesMilitaires(deck,age,nbJoueurs);
        addCartesRessources(deck,age,nbJoueurs);
        addCartesMarchandes(deck,age,nbJoueurs);
        addCartesScientifique(deck,age);
        return deck;
    }
    /**
     * Parseur de cartes de guildes
     * @param le nombre de joueurs de la partie
     * @return le deck de cartes guilde adapté
     */
    public final static ArrayList<Carte> getDeckGuildes(int nombreDeJoueur){
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
    /**
     * Parseur de carte guildes
     * @return le deck de cartes guilde
     */
    private final static ArrayList<Carte> genererCarteGuildes(){
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
    /**
     * Parseur de carte scientifiques
     * @param le deck de carte ou les cartes seront ajoutés
     * @param l'âge de la partie
     */
    private final static void addCartesScientifique(ArrayList<Carte> deck, int age){

        //-----------Cartes Scientifique------------------
        switch(age) {
            // Cartes scientifiques age 1
            case 1:
                Carte c = new Carte("Apothicaire",VERT,1,COMPAS);
                c.ajouterRessource(TEXTILE);
                deck.add(c);

                c = new Carte("Scriptorium",VERT,1,TABLETTE);
                c.ajouterCoutRessource(PAPYRUS);
                deck.add(c);

                c = new Carte("Atelier",VERT,1,ROUAGE);
                c.ajouterCoutRessource(VERRE);
                deck.add(c);
                break;

            // Cartes scientifiques age 2      
            case 2:
                c = new Carte("Laboratoire",VERT,2,ROUAGE);
                c.ajouterCoutRessource(ARGILE);
                c.ajouterCoutRessource(ARGILE);
                c.ajouterCoutRessource(PAPYRUS);
                deck.add(c);

                c = new Carte("Ecole",VERT,2,TABLETTE);
                c.ajouterCoutRessource(BOIS);
                c.ajouterCoutRessource(PAPYRUS);
                deck.add(c);

                c = new Carte("Dispensaire",VERT,2,COMPAS);
                c.ajouterCoutRessource(MINERAI);
                c.ajouterCoutRessource(MINERAI);
                c.ajouterCoutRessource(VERRE);
                deck.add(c);

                c = new Carte("Librairie",VERT,2,TABLETTE);
                c.ajouterCoutRessource(PIERRE);
                c.ajouterCoutRessource(PIERRE);
                c.ajouterCoutRessource(TEXTILE);
                deck.add(c);
                break;

            // Cartes scientifiques age 3
            case 3:
                c = new Carte("Academie",VERT,3,COMPAS);
                c.ajouterCoutRessource(PIERRE);
                c.ajouterCoutRessource(PIERRE);
                c.ajouterCoutRessource(PIERRE);
                c.ajouterCoutRessource(VERRE);
                deck.add(c);

                c = new Carte("Universite",VERT,3,TABLETTE);
                c.ajouterCoutRessource(BOIS);
                c.ajouterCoutRessource(BOIS);
                c.ajouterCoutRessource(PAPYRUS);
                c.ajouterCoutRessource(VERRE);
                deck.add(c);

                c = new Carte("Loge",VERT,3,COMPAS);
                c.ajouterCoutRessource(ARGILE);
                c.ajouterCoutRessource(ARGILE);
                c.ajouterCoutRessource(PAPYRUS);
                c.ajouterCoutRessource(TEXTILE);
                deck.add(c);

                c = new Carte("Salle d'etude",VERT,3,ROUAGE);
                c.ajouterCoutRessource(ARGILE);
                c.ajouterCoutRessource(PAPYRUS);
                c.ajouterCoutRessource(TEXTILE);
                deck.add(c);
                break;
        }

    }
    /**
     * Parseur de carte miliaires
     * @param le deck de carte ou les cartes seront ajoutés
     * @param l'âge de la partie
     * @param le nombre de joueurs de la partie
     */
    private final static void addCartesMilitaires(ArrayList<Carte> deck, int age, int nbJoueurs){   
        switch(age) {
            //cartes militaires de l'âge 1
            case 1:
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
                break;

            //cartes militaires de l'âge 2
            case 2:
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

                if(nbJoueurs >= 4){
                    c = new Carte("Terrain d'entraînement", ROUGE, 2, 0, 0, 1, 0);
                    c.ajouterCoutRessource(MINERAI);
                    c.ajouterCoutRessource(MINERAI);
                    c.ajouterCoutRessource(BOIS);
                    deck.add(c);
                }
                break;

            //cartes militaires de l'âge 3
            case 3:
                if(nbJoueurs >= 4){
                    c = new Carte("Cirque", ROUGE, 3, 0, 0, 1, 0);
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(MINERAI);
                    deck.add(c);
                }

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
                break;
        }
    }
    /**
     * Parseur de carte victoires (lauriers)
     * @param le deck de carte ou les cartes seront ajoutés
     * @param l'âge de la partie
     * @param le nombre de joueurs de la partie
     */
    private final static void addCartesVictoire(ArrayList<Carte> deck, int age, int nbJoueurs){

        Carte c;
        switch(age){
            //cartes victoire de l'âge 1
            case 1:
                
                deck.add(new Carte("Theatre", BLEU, 1, 0, 2, 0, 0));

                if(nbJoueurs >= 6){
                    c =new Carte("Prêteur sur gage", BLEU, 1, 0, 2, 0, 0);
                    deck.add(c);
                }
                
                c = new Carte("Autel", BLEU, 1, 0, 2, 0, 0);
                deck.add(c);

                c = new Carte("Bains", BLEU, 1, 0, 3, 0, 0);
                c.ajouterCoutRessource(PIERRE);
                deck.add(c);
                break;

            //cartes victoire de l'âge 2
            case 2:
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
                break;

            //cartes victoire de l'âge 3
            case 3:
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
                break;
        }

    }
    /**
     * Parseur de carte ressources
     * @param le deck de carte ou les cartes seront ajoutés
     * @param l'âge de la partie
     * @param le nombre de joueurs de la partie
     */
    private final static void addCartesRessources(ArrayList<Carte> deck, int age, int nbJoueurs){ 
        switch(age){
            //cartes ressources de l'âge 1
            case 1:
                Carte c = new Carte("Fosse", MARRON, 1);
                c.ajouterRessource(PIERRE);
                deck.add(c);


                c = new Carte("Piscine d'argile", MARRON, 1);
                c.ajouterRessource(ARGILE);
                deck.add(c);

                c = new Carte("Cavite", MARRON, 1);
                c.ajouterRessource(MINERAI);
                deck.add(c);

                c = new Carte("Cour à bois", MARRON, 1, 1,0,0,0);
                c.ajouterRessource(BOIS);
                deck.add(c);



                c = new Carte("Metier à tisser", GRIS, 1);
                c.ajouterRessource(TEXTILE);
                deck.add(c);

                c = new Carte("Travail en classe", GRIS, 1);
                c.ajouterRessource(VERRE);
                deck.add(c);

                c = new Carte("Presse", GRIS, 1);
                c.ajouterRessource(PAPYRUS);
                deck.add(c);

                if(nbJoueurs >= 4){
                    c = new Carte("Excavation", MARRON, 1, 1,0,0,0);
                    c.ajouterRessource(PIERRE);
                    c.ajouterRessource(ARGILE);
                    deck.add(c);  

                    c = new Carte("Cour à bois bis", MARRON, 1, 1,0,0,0);
                    c.ajouterRessource(PIERRE);
                    c.ajouterRessource(BOIS);
                    deck.add(c);
                }

                c = new Carte("Fosse d'argile", MARRON, 1, 1,0,0,0);
                c.ajouterRessource(ARGILE);
                c.ajouterRessource(MINERAI);
                deck.add(c);

                if(nbJoueurs >= 5){
                    c = new Carte("Cave forestière", MARRON, 1, 1,0,0,0);
                    c.ajouterRessource(BOIS);
                    c.ajouterRessource(MINERAI);
                    deck.add(c);
                }
                    
                if(nbJoueurs >= 6){
                    c = new Carte("Mine", MARRON, 1, 1,0,0,0);
                    c.ajouterRessource(PIERRE);
                    c.ajouterRessource(MINERAI);
                    deck.add(c);

                    c = new Carte("Forêt", MARRON, 1, 1,0,0,0);
                    c.ajouterRessource(BOIS);
                    c.ajouterRessource(ARGILE);
                    deck.add(c);

                    
                }
                break;
        
            //cartes ressources de l'âge 2
            case 2:
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
                break;
        }
    }
    /**
     * Parseur de carte marchandes/commerciales
     * @param le deck de carte ou les cartes seront ajoutés
     * @param l'âge de la partie
     * @param le nombre de joueurs de la partie
     */
    private final static void addCartesMarchandes(ArrayList<Carte> deck, int age, int nbJoueurs){
        switch(age){
            case 1:
                    Carte c = new Carte("Commerce Est", JAUNE, 1, 0, 0, 0, 0);
                    deck.add(c);

                    c = new Carte("Commerce Ouest", JAUNE, 1, 0, 0, 0, 0);
                    deck.add(c);

                    c = new Carte("Marché", JAUNE, 1, 0, 0, 0, 0);
                    deck.add(c);

                    if(nbJoueurs >= 4){
                        c = new Carte("Taverne", JAUNE, 1, 0, 0, 0, 5);
                        deck.add(c);
                    }
                break;
            case 2:
                c = new Carte("Vignoble", JAUNE, 2, 0, 0, 0, 0);
                deck.add(c);

                c = new Carte("Forum", JAUNE, 2, 0, 0, 0, 0);
                c.ajouterCoutRessource(ARGILE);
                c.ajouterCoutRessource(ARGILE);
                deck.add(c);

                c = new Carte("Caravanserie", JAUNE, 2, 0, 0, 0, 0);
                c.ajouterCoutRessource(BOIS);
                c.ajouterCoutRessource(BOIS);
                deck.add(c);

                if(nbJoueurs >= 4){
                    c = new Carte("Bazar", JAUNE, 2, 0, 0, 0, 0);
                    deck.add(c);
                }
                break;
            case 3:
                c = new Carte("Arene", JAUNE, 3, 0, 0, 0, 0);
                c.ajouterCoutRessource(PIERRE);
                c.ajouterCoutRessource(PIERRE);
                c.ajouterCoutRessource(MINERAI);
                deck.add(c);

                c = new Carte("Havre", JAUNE, 3, 0, 0, 0, 0);
                c.ajouterCoutRessource(BOIS);
                c.ajouterCoutRessource(MINERAI);
                c.ajouterCoutRessource(TEXTILE);
                deck.add(c);

                c = new Carte("Phare", JAUNE, 3, 0, 0, 0, 0);
                c.ajouterCoutRessource(PIERRE);
                c.ajouterCoutRessource(VERRE);
                deck.add(c);

                if(nbJoueurs >= 4){
                    c = new Carte("Chambre du commerce", JAUNE, 3, 0, 0, 0, 0);
                    c.ajouterCoutRessource(ARGILE);
                    c.ajouterCoutRessource(ARGILE);
                    c.ajouterCoutRessource(PAPYRUS);
                    deck.add(c);
                }
                break;
        }
    }
}
