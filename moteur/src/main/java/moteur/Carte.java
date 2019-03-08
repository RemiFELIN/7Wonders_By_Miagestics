package moteur;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Carte implements ICarte {

    private Color couleur;
    private String nom;
    private int age;

    protected int coutPiece = 0;
    protected int laurier = 0;
    protected int puissanceMilitaire = 0;
    protected int piece = 0;
    protected ArrayList<Ressources> coutRessources = new ArrayList<Ressources>();
    protected ArrayList<Ressources> ressources = new ArrayList<Ressources>();
    protected String symboleScientifique; // TODO enum a rajouter par la suite
    protected String description = ""; // TODO remplir avec ???
    protected String nextBuilding = ""; // TODO changer avec ???
    protected String specialEffect = ""; // TODO changer string par un enum ?

    public Carte(Color c, String n, int a) {
        this.couleur = c;// couleur noir pour les ressources
        this.nom = n;
        this.age = a;
    }

    public final Color getCouleur() {
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
    }

    public final int getPiece() {
        return piece;
    }

    public final ArrayList<Ressources> getRessources() {
        return ressources;
    }

    public final ArrayList<Ressources> getCoutRessources() {
        return coutRessources;
    }

    public final String toJSON() {
        return "{ nom: '" + nom + "' }";
    }
}
