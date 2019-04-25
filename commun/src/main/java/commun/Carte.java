package commun;

import java.util.ArrayList;
import java.util.Collections;

import static commun.Couleur.*;
import static commun.SymboleScientifique.*;
import static commun.Ressource.*;
import static commun.EffetGuilde.*;
import static commun.EffetCommercial.*;

/**
 * @author Yannick Cardini, Benoît Montorsi, Rémi Felin, Pierre Saunders, Gauci Thomas
 */
public class Carte {

    private Couleur couleur;
    private String nom;
    private int age;

    private int coutPiece, pointVictoire, puissanceMilitaire;
    private SymboleScientifique symboleScientifique;
    private ArrayList<Ressource> coutRessources = new ArrayList<Ressource>();
    private ArrayList<Ressource> ressources = new ArrayList<Ressource>();
    private String batSuiv = "";
    private EffetGuilde effetGuilde;
    private EffetCommercial effetCommercial;

    /**
     * Constructeur carte basique
     * @param nom nom
     * @param couleur couleur
     * @param age âge
     */
    public Carte(String nom, Couleur couleur, int age) {
        this.nom = nom;
        this.couleur = couleur;// couleur MARRON pour les ressources
        this.age = age;
    }
    /**
     * Constructeur carte classique
     * @param nom nom
     * @param couleur couleur
     * @param age âge
     * @param coutPiece coût pièce pour pouvoir poser
     * @param pointVictoire nombre de pointVictoire bonus si posé
     * @param puissanceMilitaire puissance militaire si posé
     */
    public Carte(String nom, Couleur couleur, int age, int coutPiece, int pointVictoire, int puissanceMilitaire){
        this(nom, couleur, age);
        this.coutPiece = coutPiece;
        this.pointVictoire = pointVictoire;
        this.puissanceMilitaire = puissanceMilitaire;
    }
    /**
     * Constructeur carte à effet special commercial
     * @param nom nom
     * @param couleur couleur
     * @param age âge
     * @param EffetCommercial l'effet spécial
     */
    public Carte(String nom, Couleur couleur, int age, EffetCommercial effetCommercial){
        this(nom, couleur, age);
        this.effetCommercial = effetCommercial;
    }
    /**
     * Constructeur carte à effet special commercial avec batîment suivant
     * @param nom nom
     * @param couleur couleur
     * @param age âge
     * @param EffetCommercial l'effet spécial
     * @batSuiv batiment suivant gratuit
     */
    public Carte(String nom, Couleur couleur, int age, EffetCommercial effetCommercial, String batSuiv){
        this(nom, couleur, age);
        this.effetCommercial = effetCommercial;
    }
    /**
     * Constructeur carte scientifique
     * @param nom nom
     * @param couleur couleur
     * @param age âge
     * @param symboleScientifique symbole scientifique bonus si posé
     */
    public Carte(String nom, Couleur couleur, int age, SymboleScientifique symboleScientifique){
        this(nom, couleur, age);
        this.symboleScientifique = symboleScientifique;
    }
    /**
     * Constructeur carte scientifique avec batîment suivant
     * @param nom nom
     * @param couleur couleur
     * @param age âge
     * @param symboleScientifique symbole scientifique bonus si posé
     * @param batSuiv batiment suivant gratuit
     */
    public Carte(String nom, Couleur couleur, int age, SymboleScientifique symboleScientifique, String batSuiv){
        this(nom, couleur, age);
        this.symboleScientifique = symboleScientifique;
        this.batSuiv = batSuiv;
    }
    /**
     * Constructeur carte complete
     * @param nom nom
     * @param couleur couleur
     * @param age âge
     * @param coutPiece coût pièce pour pouvoir poser
     * @param pointVictoire nombre de point de victoire bonus si posé
     * @param puissanceMilitaire puissance militaire si posé
     * @param batsuiv batiment suivant gratuit
     */
    public Carte(String nom, Couleur couleur, int age, int coutPiece, int pointVictoire, int puissanceMilitaire, String batSuiv){
        this(nom, couleur, age, coutPiece, 0, puissanceMilitaire);
        this.pointVictoire = pointVictoire;
        this.batSuiv = batSuiv;
    }
    /**
     * Constructeur carte guilde
     * @param effetGuilde l'effet spécial lier à la guilde
     */
    public Carte(EffetGuilde effetGuilde){
        this("Guilde des " + effetGuilde.toString(), VIOLET, 3);
        this.effetGuilde = effetGuilde;
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
     * @return le nombre de pointVictoire bonus si posé
     */
    public final int getPointVictoire() { return pointVictoire; }
    /**
     * @return la puissance militaire si posé
     */
    public final int getPuissanceMilitaire() { return puissanceMilitaire; }
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
    /**
     * @return le batiment suivant gratuit
     */
    public final String getBatimentSuivant(){ return batSuiv; }
    /**
     * @return l'effet de guilde
     */
    public final EffetGuilde getEffetGuilde(){ return effetGuilde; }
    /**
     * @return l'effet special
     */
    public final EffetCommercial getEffetCommercial(){ return effetCommercial; }

    //TODO Dans une future itération
    /*
    public final String getSpecialEffect() { return specialEffect;}
    */

    /**
     * Complémentaire au constructeur
     * @param res ressource bonus à ajouter si posé
     */
    public final void ajouterRessource(Ressource res){ ressources.add(res); }
    /**
     * Complémentaire au constructeur
     * @param res ressource néccessaire à ajouter pour pouvoir poser
     */
    public final void ajouterCoutRessource(Ressource res){ coutRessources.add(res); }

    /*************************/

    /**
     * Parseur de cartes
     * @param age l'âge de la partie
     * @param nbJoueurs nombre de joueurs de la partie
     * @return le deck de cartes adapté
     */
    public final static ArrayList<Carte> getDeck(int age, int nbJoueurs){
        ArrayList<Carte> deck = new ArrayList<Carte>();

        addCarteCivils(deck, age, nbJoueurs);
        addCartesMilitaires(deck, age, nbJoueurs);
        addCartesRessources(deck, age, nbJoueurs);
        addCartesMarchandes(deck, age, nbJoueurs);
        addCartesScientifique(deck, age, nbJoueurs);
        return deck;
    }
    /**
     * Parseur de cartes de guildes
     * @param nombreDeJoueur nombre de joueurs de la partie
     * @return le deck de cartes guilde adapté
     */
    public final static ArrayList<Carte> getDeckGuildes(int nombreDeJoueur){
        ArrayList<Carte> deckDeGuilde = genererCarteGuildes();
        ArrayList<Carte> deckUtilise = new ArrayList<Carte>();
        //on melange
        Collections.shuffle(deckDeGuilde);
        //on constitue ...
        for(int i=0; i<nombreDeJoueur+2; i++)
            deckUtilise.add(deckDeGuilde.get(i));
        //on retourne
        return deckUtilise;
    }
    /**
     * Parseur de carte guildes
     * @return le deck de cartes guilde
     */
    private final static ArrayList<Carte> genererCarteGuildes(){
        ArrayList<Carte> deck = new ArrayList<Carte>();
        /* 10 cartes Guildes au total: en fct du nombre de joueur, le nb de guildes equivaut à joueur + 2*/

        //Guilde des travailleurs
        Carte c = new Carte(TRAVAILLEURS);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(BOIS);
        //Pour chaques cartes MARRON que j'ai dans mon deck, je gagne un point de victoire

        deck.add(c);


        //Guilde des artisans
        c = new Carte(ARTISANS);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(PIERRE);
        //pour chaques cartes grises que j'ai dans mon deck, je gagne deux points de victoire

        deck.add(c);

        //Guilde des commerçants
        c = new Carte(COMMERCANTS);
        c.ajouterCoutRessource(TEXTILE);
        c.ajouterCoutRessource(PAPYRUS);
        c.ajouterCoutRessource(VERRE);
        //Pour chaques cartes jaunes que j'ai dans mon deck, je gagne un point de victoire

        deck.add(c);

        //Guilde des philosophes
        c = new Carte(PHILOSOPHES);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(TEXTILE);
        c.ajouterCoutRessource(PAPYRUS);
        //Pour chaques cartes scientifiques que j'ai dans mon deck, je gagne un point de victoire

        deck.add(c);

        //Guilde des espions
        c = new Carte(ESPIONS);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(ARGILE);
        c.ajouterCoutRessource(VERRE);
        //Pour chaques cartes conflits que j'ai dans mon deck, je gagne un point de victoire

        deck.add(c);

        //Guilde des stratèges
        c = new Carte(STRATEGES);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(PAPYRUS);
        //Pour chaques malus de conflits que j'ai reçu, je reçoit un point de victoire

        deck.add(c);

        //Guilde des armateurs
        c = new Carte(ARMATEURS);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(PAPYRUS);
        c.ajouterCoutRessource(VERRE);
        //Pour chaques cartes marrons, grises et violettes, je gagne un point de victoire

        deck.add(c);

        //Guilde des scientifiques
        c = new Carte(SCIENTIFIQUES);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(MINERAI);
        c.ajouterCoutRessource(PAPYRUS);
        //Je recoit une carte compas, rouage et tablette

        deck.add(c);

        //Guilde des magistrats
        c = new Carte(MAGISTRATS);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(BOIS);
        c.ajouterCoutRessource(PIERRE);
        c.ajouterCoutRessource(TEXTILE);
        //Pour chaques cartes bleues que j'ai dans mon deck, je gagne un point de victoire

        deck.add(c);

        //Guilde des bâtisseurs
        c = new Carte(BATISSEURS);
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
     * @param deck deck de carte ou les cartes seront ajoutés
     * @param age l'âge de la partie
     * @param nbJoueurs le nombre de joueurs dans la partie
     * @author Thomas Gauci
     */
    private final static void addCartesScientifique(ArrayList<Carte> deck, int age, int nbJoueurs){

        //-----------Cartes Scientifique------------------
        switch(age) {
            // Cartes scientifiques age 1
            case 1:
                Carte c = new Carte("Officine",VERT,1,COMPAS,"Dispensaire");
                c.ajouterRessource(TEXTILE);
                deck.add(c);

                c = new Carte("Scriptorium",VERT,1,TABLETTE,"Bibliotheque");
                c.ajouterCoutRessource(PAPYRUS);
                deck.add(c);

                c = new Carte("Atelier",VERT,1,ROUAGE,"Laboratoire");
                c.ajouterCoutRessource(VERRE);
                deck.add(c);
                if(nbJoueurs >= 4){
                    c = new Carte("Scriptorium",VERT,1,TABLETTE,"Bibliotheque");
                    c.ajouterCoutRessource(PAPYRUS);
                    deck.add(c);
                }
                if(nbJoueurs >= 5){
                    c = new Carte("Officine",VERT,1,COMPAS,"Dispensaire");
                    c.ajouterRessource(TEXTILE);
                    deck.add(c);
                }
                if(nbJoueurs >= 7){
                    c = new Carte("Atelier",VERT,1,ROUAGE,"Laboratoire");
                    c.ajouterCoutRessource(VERRE);
                    deck.add(c);
                }
                break;

            // Cartes scientifiques age 2      
            case 2:
                c = new Carte("Laboratoire",VERT,2,ROUAGE,"Observatoire");
                c.ajouterCoutRessource(ARGILE);
                c.ajouterCoutRessource(ARGILE);
                c.ajouterCoutRessource(PAPYRUS);
                deck.add(c);

                if(nbJoueurs >= 5){
                    c = new Carte("Laboratoire",VERT,2,ROUAGE,"Observatoire");
                    c.ajouterCoutRessource(ARGILE);
                    c.ajouterCoutRessource(ARGILE);
                    c.ajouterCoutRessource(PAPYRUS);
                    deck.add(c);
                }

                c = new Carte("Ecole",VERT,2,TABLETTE,"Etude");
                c.ajouterCoutRessource(BOIS);
                c.ajouterCoutRessource(PAPYRUS);
                deck.add(c);

                if(nbJoueurs >= 7){
                    c = new Carte("Ecole",VERT,2,TABLETTE,"Etude");
                    c.ajouterCoutRessource(BOIS);
                    c.ajouterCoutRessource(PAPYRUS);
                    deck.add(c);
                }
                c = new Carte("Dispensaire",VERT,2,COMPAS,"Loge");
                c.ajouterCoutRessource(MINERAI);
                c.ajouterCoutRessource(MINERAI);
                c.ajouterCoutRessource(VERRE);
                deck.add(c);

                if(nbJoueurs >= 4){
                    c = new Carte("Dispensaire",VERT,2,COMPAS,"Loge");
                    c.ajouterCoutRessource(MINERAI);
                    c.ajouterCoutRessource(MINERAI);
                    c.ajouterCoutRessource(VERRE);
                    deck.add(c);
                }
                c = new Carte("Bibliotheque",VERT,2,TABLETTE,"Universite");
                c.ajouterCoutRessource(PIERRE);
                c.ajouterCoutRessource(PIERRE);
                c.ajouterCoutRessource(TEXTILE);
                deck.add(c);
                if(nbJoueurs >= 6){
                    c = new Carte("Bibliotheque",VERT,2,TABLETTE,"Universite");
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(TEXTILE);
                    deck.add(c);
                }
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
                if(nbJoueurs >= 4){
                    c = new Carte("Universite",VERT,3,TABLETTE);
                    c.ajouterCoutRessource(BOIS);
                    c.ajouterCoutRessource(BOIS);
                    c.ajouterCoutRessource(PAPYRUS);
                    c.ajouterCoutRessource(VERRE);
                    deck.add(c);
                }
                c = new Carte("Loge",VERT,3,COMPAS);
                c.ajouterCoutRessource(ARGILE);
                c.ajouterCoutRessource(ARGILE);
                c.ajouterCoutRessource(PAPYRUS);
                c.ajouterCoutRessource(TEXTILE);
                deck.add(c);
                if(nbJoueurs >= 6){
                    c = new Carte("Loge",VERT,3,COMPAS);
                    c.ajouterCoutRessource(ARGILE);
                    c.ajouterCoutRessource(ARGILE);
                    c.ajouterCoutRessource(PAPYRUS);
                    c.ajouterCoutRessource(TEXTILE);
                    deck.add(c);
                }
                c = new Carte("Etude",VERT,3,ROUAGE);
                c.ajouterCoutRessource(ARGILE);
                c.ajouterCoutRessource(PAPYRUS);
                c.ajouterCoutRessource(TEXTILE);
                deck.add(c);
                if(nbJoueurs >= 5){
                    c = new Carte("Etude",VERT,3,ROUAGE);
                    c.ajouterCoutRessource(ARGILE);
                    c.ajouterCoutRessource(PAPYRUS);
                    c.ajouterCoutRessource(TEXTILE);
                    deck.add(c); 
                }
                c = new Carte("Observatoire",VERT,3,ROUAGE);
                c.ajouterCoutRessource(MINERAI);
                c.ajouterCoutRessource(MINERAI);
                c.ajouterCoutRessource(VERRE);
                c.ajouterCoutRessource(TEXTILE);
                deck.add(c);
                if(nbJoueurs >= 7){
                    c = new Carte("Observatoire",VERT,3,ROUAGE);
                    c.ajouterCoutRessource(MINERAI);
                    c.ajouterCoutRessource(MINERAI);
                    c.ajouterCoutRessource(VERRE);
                    c.ajouterCoutRessource(TEXTILE);
                    deck.add(c);
                }
                break;
        }

    }
    /**
     * Parseur de carte miliaires
     * @param deck deck de carte ou les cartes seront ajoutés
     * @param age l'âge de la partie
     * @param nbJoueurs nombre de joueurs de la partie
     * @author Thomas Gauci
     */
    private final static void addCartesMilitaires(ArrayList<Carte> deck, int age, int nbJoueurs){   
        switch(age) {
            //cartes militaires de l'âge 1
            case 1:
                Carte c = new Carte("Caserne", ROUGE, 1, 0, 0, 1);
                c.ajouterCoutRessource(MINERAI);
                deck.add(c);

                c = new Carte("Palissade", ROUGE, 1, 0, 0, 1);
                c.ajouterCoutRessource(BOIS);
                deck.add(c);

                c = new Carte("Tour de garde", ROUGE, 1, 0, 0, 1);
                c.ajouterCoutRessource(ARGILE);
                deck.add(c);
                if(nbJoueurs >=4){
                    c = new Carte("Tour de garde", ROUGE, 1, 0, 0, 1);
                    c.ajouterCoutRessource(ARGILE);
                    deck.add(c);
                }
                if(nbJoueurs >=5){
                    c = new Carte("Caserne", ROUGE, 1, 0, 0, 1);
                    c.ajouterCoutRessource(MINERAI);
                    deck.add(c);
                }
                if(nbJoueurs >=7){
                    c = new Carte("Palissade", ROUGE, 1, 0, 0, 1);
                    c.ajouterCoutRessource(BOIS);
                    deck.add(c);
                }

                break;

            //cartes militaires de l'âge 2
            case 2:
                c = new Carte("Ecuries", ROUGE, 2, 0, 0, 1);
                c.ajouterCoutRessource(ARGILE);
                c.ajouterCoutRessource(BOIS);
                c.ajouterCoutRessource(MINERAI);
                deck.add(c);

                c = new Carte("Murs", ROUGE, 2, 0, 0, 1);
                c.ajouterCoutRessource(PIERRE);
                c.ajouterCoutRessource(PIERRE);
                c.ajouterCoutRessource(PIERRE);
                deck.add(c);

                c = new Carte("Tir à l'arc", ROUGE, 2, 0, 0, 1);
                c.ajouterCoutRessource(BOIS);
                c.ajouterCoutRessource(BOIS);
                c.ajouterCoutRessource(PIERRE);
                deck.add(c);

                if(nbJoueurs >= 4){
                    c = new Carte("Terrain d'entraînement", ROUGE, 2, 0, 0, 1);
                    c.ajouterCoutRessource(MINERAI);
                    c.ajouterCoutRessource(MINERAI);
                    c.ajouterCoutRessource(BOIS);
                    deck.add(c);
                }
                if(nbJoueurs >= 5){
                    c = new Carte("Ecuries", ROUGE, 2, 0, 0, 1);
                    c.ajouterCoutRessource(ARGILE);
                    c.ajouterCoutRessource(BOIS);
                    c.ajouterCoutRessource(MINERAI);
                    deck.add(c);
                }
                if(nbJoueurs >= 6){
                    c = new Carte("Terrain d'entraînement", ROUGE, 2, 0, 0, 1);
                    c.ajouterCoutRessource(MINERAI);
                    c.ajouterCoutRessource(MINERAI);
                    c.ajouterCoutRessource(BOIS);
                    deck.add(c);

                    c = new Carte("Tir à l'arc", ROUGE, 2, 0, 0, 1);
                    c.ajouterCoutRessource(BOIS);
                    c.ajouterCoutRessource(BOIS);
                    c.ajouterCoutRessource(PIERRE);
                    deck.add(c);
                }
                if(nbJoueurs >= 7){
                    c = new Carte("Terrain d'entraînement", ROUGE, 2, 0, 0, 1);
                    c.ajouterCoutRessource(MINERAI);
                    c.ajouterCoutRessource(MINERAI);
                    c.ajouterCoutRessource(BOIS);
                    deck.add(c);

                    c = new Carte("Murs", ROUGE, 2, 0, 0, 1);
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(PIERRE);
                    deck.add(c);
                }
                break;

            //cartes militaires de l'âge 3
            case 3:
                if(nbJoueurs >= 4){
                    c = new Carte("Cirque", ROUGE, 3, 0, 0, 1);
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(MINERAI);
                    deck.add(c);

                    c = new Carte("Arsenal", ROUGE, 3, 0, 0, 1);
                    c.ajouterCoutRessource(BOIS);
                    c.ajouterCoutRessource(BOIS);
                    c.ajouterCoutRessource(MINERAI);
                    c.ajouterCoutRessource(TEXTILE);
                    deck.add(c);
                }
                if(nbJoueurs >= 5){
                    c = new Carte("Cirque", ROUGE, 3, 0, 0, 1);
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(MINERAI);
                    deck.add(c);

                    c = new Carte("Atelier de siège", ROUGE, 3, 0, 0, 1);
                    c.ajouterCoutRessource(ARGILE);
                    c.ajouterCoutRessource(ARGILE);
                    c.ajouterCoutRessource(ARGILE);
                    c.ajouterCoutRessource(BOIS);
                    deck.add(c);
                }
                if(nbJoueurs >= 6){
                    c = new Carte("Cirque", ROUGE, 3, 0, 0, 1);
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(MINERAI);
                    deck.add(c);
                }
                if(nbJoueurs >= 7){
                    c = new Carte("Arsenal", ROUGE, 3, 0, 0, 1);
                    c.ajouterCoutRessource(BOIS);
                    c.ajouterCoutRessource(BOIS);
                    c.ajouterCoutRessource(MINERAI);
                    c.ajouterCoutRessource(TEXTILE);
                    deck.add(c);

                    c = new Carte("Fortifications", ROUGE, 3, 0, 0, 1);
                    c.ajouterCoutRessource(MINERAI);
                    c.ajouterCoutRessource(MINERAI);
                    c.ajouterCoutRessource(MINERAI);
                    c.ajouterCoutRessource(PIERRE);
                    deck.add(c);
                }

                c = new Carte("Arsenal", ROUGE, 3, 0, 0, 1);
                c.ajouterCoutRessource(BOIS);
                c.ajouterCoutRessource(BOIS);
                c.ajouterCoutRessource(MINERAI);
                c.ajouterCoutRessource(TEXTILE);
                deck.add(c);

                c = new Carte("Fortifications", ROUGE, 3, 0, 0, 1);
                c.ajouterCoutRessource(MINERAI);
                c.ajouterCoutRessource(MINERAI);
                c.ajouterCoutRessource(MINERAI);
                c.ajouterCoutRessource(PIERRE);
                deck.add(c);

                c = new Carte("Atelier de siège", ROUGE, 3, 0, 0, 1);
                c.ajouterCoutRessource(ARGILE);
                c.ajouterCoutRessource(ARGILE);
                c.ajouterCoutRessource(ARGILE);
                c.ajouterCoutRessource(BOIS);
                deck.add(c);
                break;
        }
    }
    /**
     * Parseur de carte civils
     * @param deck deck de carte ou les cartes seront ajoutés
     * @param age l'âge de la partie
     * @param nbJoueurs nombre de joueurs de la partie
     * @author Thomas Gauci
     */
    private final static void addCarteCivils(ArrayList<Carte> deck, int age, int nbJoueurs){

        Carte c;
        switch(age){
            //cartes victoire de l'âge 1
            case 1:
                
                deck.add(new Carte("Theatre", BLEU, 1, 0, 2, 0,"Statue"));

                if(nbJoueurs >= 4){
                    c =new Carte("Prêteur sur gage", BLEU, 1, 0, 2, 0);
                    deck.add(c);
                }
                if(nbJoueurs >= 5){
                    c = new Carte("Autel", BLEU, 1, 0, 2, 0,"Temple");
                    deck.add(c);
                }
                if(nbJoueurs >= 6){
                    deck.add(new Carte("Theatre", BLEU, 1, 0, 2, 0,"Statue"));
                }
                if(nbJoueurs >= 7){
                    c =new Carte("Prêteur sur gage", BLEU, 1, 0, 2, 0);
                    deck.add(c);

                    c = new Carte("Bains", BLEU, 1, 0, 3, 0,"Aqueduc");
                    c.ajouterCoutRessource(PIERRE);
                    deck.add(c);
                }
                c = new Carte("Autel", BLEU, 1, 0, 2, 0,"Temple");
                deck.add(c);

                c = new Carte("Bains", BLEU, 1, 0, 3, 0,"Aqueduc");
                c.ajouterCoutRessource(PIERRE);
                deck.add(c);
                break;

            //cartes victoire de l'âge 2
            case 2:
                c = new Carte("Tribunal", BLEU, 2, 0, 4, 0);
                c.ajouterCoutRessource(ARGILE);
                c.ajouterCoutRessource(ARGILE);
                c.ajouterCoutRessource(TEXTILE);
                deck.add(c);
                if(nbJoueurs >=5){
                    c = new Carte("Tribunal", BLEU, 2, 0, 4, 0);
                    c.ajouterCoutRessource(ARGILE);
                    c.ajouterCoutRessource(ARGILE);
                    c.ajouterCoutRessource(TEXTILE);
                    deck.add(c);
                }
                if(nbJoueurs >=6){
                    c = new Carte("Temple", BLEU, 2, 0, 3, 0,"Pantheon");
                    c.ajouterCoutRessource(BOIS);
                    c.ajouterCoutRessource(ARGILE);
                    c.ajouterCoutRessource(VERRE);
                    deck.add(c);
                }
                if(nbJoueurs >=7){
                    c = new Carte("Statue", BLEU, 2, 0, 4, 0,"Jardins");
                    c.ajouterCoutRessource(MINERAI);
                    c.ajouterCoutRessource(MINERAI);
                    c.ajouterCoutRessource(BOIS);
                    deck.add(c);

                    c = new Carte("Aqueduc", BLEU, 2, 0, 5, 0);
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(PIERRE);
                    deck.add(c);
                }
                c = new Carte("Statue", BLEU, 2, 0, 4, 0,"Jardins");
                c.ajouterCoutRessource(MINERAI);
                c.ajouterCoutRessource(MINERAI);
                c.ajouterCoutRessource(BOIS);
                deck.add(c);

                c = new Carte("Temple", BLEU, 2, 0, 3, 0,"Pantheon");
                c.ajouterCoutRessource(BOIS);
                c.ajouterCoutRessource(ARGILE);
                c.ajouterCoutRessource(VERRE);
                deck.add(c);

                c = new Carte("Aqueduc", BLEU, 2, 0, 5, 0);
                c.ajouterCoutRessource(PIERRE);
                c.ajouterCoutRessource(PIERRE);
                c.ajouterCoutRessource(PIERRE);
                deck.add(c);
                break;

            //cartes victoire de l'âge 3
            case 3:
                c = new Carte("Palais", BLEU, 3, 0, 8, 0);
                c.ajouterCoutRessource(PIERRE);
                c.ajouterCoutRessource(MINERAI);
                c.ajouterCoutRessource(BOIS);
                c.ajouterCoutRessource(ARGILE);
                c.ajouterCoutRessource(VERRE);
                c.ajouterCoutRessource(PAPYRUS);
                c.ajouterCoutRessource(TEXTILE);
                deck.add(c);

                c = new Carte("Jardins", BLEU, 3, 0, 5, 0);
                c.ajouterCoutRessource(ARGILE);
                c.ajouterCoutRessource(ARGILE);
                c.ajouterCoutRessource(BOIS);
                deck.add(c);

                c = new Carte("Pantheon", BLEU, 3, 0, 7, 0);
                c.ajouterCoutRessource(ARGILE);
                c.ajouterCoutRessource(ARGILE);
                c.ajouterCoutRessource(MINERAI);
                c.ajouterCoutRessource(VERRE);
                c.ajouterCoutRessource(PAPYRUS);
                c.ajouterCoutRessource(TEXTILE);
                deck.add(c);

                c = new Carte("Mairie", BLEU, 3, 0, 6, 0);
                c.ajouterCoutRessource(PIERRE);
                c.ajouterCoutRessource(PIERRE);
                c.ajouterCoutRessource(MINERAI);
                c.ajouterCoutRessource(VERRE);
                deck.add(c);

                c = new Carte("Senat", BLEU, 3, 0, 6, 0);
                c.ajouterCoutRessource(BOIS);
                c.ajouterCoutRessource(BOIS);
                c.ajouterCoutRessource(PIERRE);
                c.ajouterCoutRessource(MINERAI);
                deck.add(c);
                if(nbJoueurs >= 4){
                    c = new Carte("Jardins", BLEU, 3, 0, 5, 0);
                    c.ajouterCoutRessource(ARGILE);
                    c.ajouterCoutRessource(ARGILE);
                    c.ajouterCoutRessource(BOIS);
                    deck.add(c);
                }
                if(nbJoueurs >= 5){
                    c = new Carte("Mairie", BLEU, 3, 0, 6, 0);
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(MINERAI);
                    c.ajouterCoutRessource(VERRE);
                    deck.add(c);

                    c = new Carte("Senat", BLEU, 3, 0, 6, 0);
                    c.ajouterCoutRessource(BOIS);
                    c.ajouterCoutRessource(BOIS);
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(MINERAI);
                    deck.add(c);
                }
                if(nbJoueurs >= 6){
                    c = new Carte("Pantheon", BLEU, 3, 0, 7, 0);
                    c.ajouterCoutRessource(ARGILE);
                    c.ajouterCoutRessource(ARGILE);
                    c.ajouterCoutRessource(MINERAI);
                    c.ajouterCoutRessource(VERRE);
                    c.ajouterCoutRessource(PAPYRUS);
                    c.ajouterCoutRessource(TEXTILE);
                    deck.add(c);

                    c = new Carte("Mairie", BLEU, 3, 0, 6, 0);
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(MINERAI);
                    c.ajouterCoutRessource(VERRE);
                    deck.add(c);
                }
                if(nbJoueurs >= 7){
                    c = new Carte("Palais", BLEU, 3, 0, 8, 0);
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(MINERAI);
                    c.ajouterCoutRessource(BOIS);
                    c.ajouterCoutRessource(ARGILE);
                    c.ajouterCoutRessource(VERRE);
                    c.ajouterCoutRessource(PAPYRUS);
                    c.ajouterCoutRessource(TEXTILE);
                    deck.add(c);
                }
                break;
        }

    }
    /**
     * Parseur de carte ressources
     * @param deck deck de carte ou les cartes seront ajoutés
     * @param age l'âge de la partie
     * @param nbJoueurs nombre de joueurs de la partie
     * @author Thomas Gauci
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

                c = new Carte("Cour à bois", MARRON, 1, 1,0,0);
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

                c = new Carte("Fosse d'argile", MARRON, 1, 1,0,0);
                c.ajouterRessource(ARGILE);
                c.ajouterRessource(MINERAI);
                deck.add(c);

                if(nbJoueurs >= 4){
                    c = new Carte("Excavation", MARRON, 1, 1,0,0);
                    c.ajouterRessource(PIERRE);
                    c.ajouterRessource(ARGILE);
                    deck.add(c);  

                    c = new Carte("Cour à bois bis", MARRON, 1, 1,0,0);
                    c.ajouterRessource(PIERRE);
                    c.ajouterRessource(BOIS);
                    deck.add(c);

                    c = new Carte("Cavite", MARRON, 1);
                    c.ajouterRessource(MINERAI);
                    deck.add(c);

                    c = new Carte("Cour à bois", MARRON, 1, 1,0,0);
                    c.ajouterRessource(BOIS);
                    deck.add(c);
                }


                if(nbJoueurs >= 5){
                    c = new Carte("Cave forestière", MARRON, 1, 1,0,0);
                    c.ajouterRessource(BOIS);
                    c.ajouterRessource(MINERAI);
                    deck.add(c);

                    c = new Carte("Piscine d'argile", MARRON, 1);
                    c.ajouterRessource(ARGILE);
                    deck.add(c);

                    c = new Carte("Cavite", MARRON, 1);
                    c.ajouterRessource(MINERAI);
                    deck.add(c);
                }
                    
                if(nbJoueurs >= 6){
                    c = new Carte("Mine", MARRON, 1, 1,0,0);
                    c.ajouterRessource(PIERRE);
                    c.ajouterRessource(MINERAI);
                    deck.add(c);

                    c = new Carte("Forêt", MARRON, 1, 1,0,0);
                    c.ajouterRessource(BOIS);
                    c.ajouterRessource(ARGILE);
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
                    
                }
                break;
        
            //cartes ressources de l'âge 2
            case 2:
                c = new Carte("Scierie", MARRON, 2, 1,0,0);
                c.ajouterRessource(BOIS);
                c.ajouterRessource(BOIS);
                deck.add(c);

                c = new Carte("Carrière", MARRON, 2, 1,0,0);
                c.ajouterRessource(PIERRE);
                c.ajouterRessource(PIERRE);
                deck.add(c);

                c = new Carte("Briquetterie", MARRON, 2, 1,0,0);
                c.ajouterRessource(ARGILE);
                c.ajouterRessource(ARGILE);
                deck.add(c);

                c = new Carte("Fonderie", MARRON, 2, 1,0,0);
                c.ajouterRessource(MINERAI);
                c.ajouterRessource(MINERAI);
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
                

                if(nbJoueurs >=4){
                    c = new Carte("Scierie", MARRON, 2, 1,0,0);
                    c.ajouterRessource(BOIS);
                    c.ajouterRessource(BOIS);
                    deck.add(c);

                    c = new Carte("Carrière", MARRON, 2, 1,0,0);
                    c.ajouterRessource(PIERRE);
                    c.ajouterRessource(PIERRE);
                    deck.add(c);

                    c = new Carte("Briquetterie", MARRON, 2, 1,0,0);
                    c.ajouterRessource(ARGILE);
                    c.ajouterRessource(ARGILE);
                    deck.add(c);

                    c = new Carte("Fonderie", MARRON, 2, 1,0,0);
                    c.ajouterRessource(MINERAI);
                    c.ajouterRessource(MINERAI);
                    deck.add(c);
                }
                if(nbJoueurs >= 5){
                    c = new Carte("Metier à tisser", GRIS, 1);
                    c.ajouterRessource(TEXTILE);
                    deck.add(c);

                    c = new Carte("Travail en classe", GRIS, 1);
                    c.ajouterRessource(VERRE);
                    deck.add(c);

                    c = new Carte("Presse", GRIS, 1);
                    c.ajouterRessource(PAPYRUS);
                    deck.add(c);
                    
                }
                break;
        }
    }
    /**
     * Parseur de carte marchandes/commerciales
     * @param deck deck de carte ou les cartes seront ajoutés
     * @param age l'âge de la partie
     * @param nbJoueurs nombre de joueurs de la partie
     * @author Thomas Gauci
     */
    private final static void addCartesMarchandes(ArrayList<Carte> deck, int age, int nbJoueurs){
        switch(age){
            case 1:
                    Carte c = new Carte("Commerce Est", JAUNE, 1, ACHAT_MATIERE_DROITE, "Forum");
                    deck.add(c);

                    c = new Carte("Commerce Ouest", JAUNE, 1, ACHAT_MATIERE_GAUCHE, "Forum");
                    deck.add(c);

                    c = new Carte("Marche", JAUNE, 1, ACHAT_PREMIERE, "Caranvaserail");
                    deck.add(c);

                    if(nbJoueurs >= 4){
                        c = new Carte("Taverne", JAUNE, 1, BONUS_OR);
                        deck.add(c);
                    }
                    if(nbJoueurs >= 5){
                        c = new Carte("Taverne", JAUNE, 1, BONUS_OR);
                        deck.add(c);
                    }
                    if(nbJoueurs >= 6){
                        c = new Carte("Marche", JAUNE, 1, ACHAT_PREMIERE, "Caranvaserail");
                        deck.add(c);
                    }

                    if(nbJoueurs >= 7){
                        c = new Carte("Taverne", JAUNE, 1, BONUS_OR);
                        deck.add(c);

                        c = new Carte("Commerce Ouest", JAUNE, 1, ACHAT_MATIERE_GAUCHE, "Forum");
                        deck.add(c);

                        c = new Carte("Commerce Est", JAUNE, 1, ACHAT_MATIERE_DROITE, "Forum");
                        deck.add(c);

                    }
                break;
            case 2:
                c = new Carte("Vignoble", JAUNE, 2, OR_CARTE_MARRON);
                deck.add(c);

                c = new Carte("Forum", JAUNE, 2, 0, 0, 0,"Port");
                c.ajouterCoutRessource(ARGILE);
                c.ajouterCoutRessource(ARGILE);
                deck.add(c);

                c = new Carte("Caranvaserail", JAUNE, 2, 0, 0, 0,"Phare");
                c.ajouterCoutRessource(BOIS);
                c.ajouterCoutRessource(BOIS);
                deck.add(c);

                if(nbJoueurs >= 4){
                    c = new Carte("Bazar", JAUNE, 2, OR_CARTE_GRIS);
                    deck.add(c);
                }
                if(nbJoueurs >= 5){
                    c = new Carte("Caranvaserail", JAUNE, 2, 0, 0, 0,"Phare");
                    c.ajouterCoutRessource(BOIS);
                    c.ajouterCoutRessource(BOIS);
                    deck.add(c);
                }
                if(nbJoueurs >= 6){
                    c = new Carte("Caranvaserail", JAUNE, 2, 0, 0, 0,"Phare");
                    c.ajouterCoutRessource(BOIS);
                    c.ajouterCoutRessource(BOIS);
                    deck.add(c);

                    c = new Carte("Forum", JAUNE, 2, 0, 0, 0,"Port");
                    c.ajouterCoutRessource(ARGILE);
                    c.ajouterCoutRessource(ARGILE);
                    deck.add(c);

                    c = new Carte("Vignoble", JAUNE, 2, OR_CARTE_MARRON);
                    deck.add(c);
                }
                if(nbJoueurs >= 7){
                    c = new Carte("Bazar", JAUNE, 2, OR_CARTE_GRIS);
                    deck.add(c);
                }
                break;
            case 3:
                c = new Carte("Arene", JAUNE, 3, BONUS_ETAPE_MERVEILLE);
                c.ajouterCoutRessource(PIERRE);
                c.ajouterCoutRessource(PIERRE);
                c.ajouterCoutRessource(MINERAI);
                deck.add(c);

                c = new Carte("Havre", JAUNE, 3, BONUS_CARTE_MARRON);
                c.ajouterCoutRessource(BOIS);
                c.ajouterCoutRessource(MINERAI);
                c.ajouterCoutRessource(TEXTILE);
                deck.add(c);

                c = new Carte("Phare", JAUNE, 3, BONUS_CARTE_JAUNE);
                c.ajouterCoutRessource(PIERRE);
                c.ajouterCoutRessource(VERRE);
                deck.add(c);

                if(nbJoueurs >= 4){
                    c = new Carte("Chambre du commerce", JAUNE, 3, BONUS_CARTE_GRIS);
                    c.ajouterCoutRessource(ARGILE);
                    c.ajouterCoutRessource(ARGILE);
                    c.ajouterCoutRessource(PAPYRUS);
                    deck.add(c);

                    c = new Carte("Havre", JAUNE, 3, BONUS_CARTE_MARRON);
                    c.ajouterCoutRessource(BOIS);
                    c.ajouterCoutRessource(MINERAI);
                    c.ajouterCoutRessource(TEXTILE);
                    deck.add(c);
                }

                if(nbJoueurs >= 6){
                    c = new Carte("Chambre du commerce", JAUNE, 3, BONUS_CARTE_GRIS);
                    c.ajouterCoutRessource(ARGILE);
                    c.ajouterCoutRessource(ARGILE);
                    c.ajouterCoutRessource(PAPYRUS);
                    deck.add(c);

                    c = new Carte("Phare", JAUNE, 3, BONUS_CARTE_JAUNE);
                    c.ajouterCoutRessource(PIERRE);
                    c.ajouterCoutRessource(VERRE);
                    deck.add(c);
                }
                break;
        }
    }
}
