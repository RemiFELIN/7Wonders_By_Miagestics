package moteur;

import java.util.ArrayList;

public class Carte {

    private Couleur couleur;
    private String nom;
    private int age;

    private int coutPiece = 0;
    private int laurier = 0;
    private int puissanceMilitaire = 0;
    private int piece = 0;
    private ArrayList<Ressource> coutRessources = new ArrayList<Ressource>();
    private ArrayList<Ressource> ressources = new ArrayList<Ressource>();

    //TODO Dans une future itération
    /*
    protected String symboleScientifique; // TODO enum a rajouter par la suite
    protected String description = ""; // TODO remplir avec ???
    protected String nextBuilding = ""; // TODO changer avec ???
    protected String specialEffect = ""; // TODO changer string par un enum ?
    */

    public Carte(String nom, Couleur couleur, int age) {
        this.nom = nom;
        this.couleur = couleur;// couleur noir pour les ressources
        this.age = age;
    }

    public Carte(String nom, Couleur couleur, int age, int coutPiece, int laurier, int puissanceMilitaire, int piece){
        this(nom, couleur, age);
        this.coutPiece = coutPiece;
        this.laurier = laurier;
        this.puissanceMilitaire = puissanceMilitaire;
        this.piece = piece;
    }

    public void ajouterCoutRessource(Ressource res){
        coutRessources.add(res);
    }

    public void ajouterRessource(Ressource res){
        ressources.add(res);
    }

    public static ArrayList<Carte> getDeck(){
        ArrayList<Carte> deck = new ArrayList<Carte>();
        Carte c;

        c = new Carte("Caserne", Couleur.ROUGE, 1, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.MINERAI);        
        deck.add(c);

        c = new Carte("Cavite", Couleur.NOIR, 1);
        c.ajouterRessource(Ressource.MINERAI);
        deck.add(c);

        deck.add(new Carte("Taverne", Couleur.JAUNE, 1, 0, 0, 0, 5));
        deck.add(new Carte("Theatre", Couleur.BLEU, 1, 0, 2, 0, 0));

        return deck;
    }

    public final Couleur getCouleur() {
        return couleur;
    }

    public final String getNom() {
        return nom;
    }

    public final int getAge() {
        return age;
    }

    public final int getCoutPiece() {
        return coutPiece;
    }

    public final int getLaurier() {
        return laurier;
    }

    public final int getPuissanceMilitaire() {
        return puissanceMilitaire;
    }
    
    //TODO Dans une future itération
    /*
    public final String getSymboleScientifique() {
        return symboleScientifique;
    }

    public final String getDescription() {
        return description;
    }

    public final String getNextBuilding() {
        return nextBuilding;
    }

    public final String getSpecialEffect() {
        return specialEffect;
    }*/

    public final int getPiece() {
        return piece;
    }

    public final ArrayList<Ressource> getRessources() {
        return ressources;
    }

    public final ArrayList<Ressource> getCoutRessources() {
        return coutRessources;
    }
}
