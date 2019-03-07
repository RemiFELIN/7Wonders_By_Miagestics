package moteur;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Carte implements ICarte {

    protected Color couleur;
    protected int coutPiece = 0;
    protected int laurier = 0;
    protected int puissanceMilitaire = 0;
    protected int gold = 0;
    protected ArrayList<Ressources> coutRessources;
    protected ArrayList<Ressources> ressources;
    protected String nom;
    protected String symboleScientifique;//enum a rajouter par la suite
    protected String description;
    protected String nextBuilding;
    protected String getSpecialEffect;
    protected int age;
    
    public Carte(Color c, String n, int a){
        this.couleur = c;//couleur noir pour les ressources
        this.nom = n;
        this.age = a;
    }

    public final Color getCouleur() {
        return this.couleur;
    }
    
    public final int getValue() {
        return this.laurier;
    }

    public final String getNom() {
        return this.nom;
    }

    public final int getAge() {
        return this.age;
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

    public final String toJSON(){
        return "{ nom: '"+nom+"' }";
    }

    public final String descriptionCarte() {
        return "Ma valeur est " + Integer.toString(laurier);
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
        return getSpecialEffect;
    }

    public final int getGold() {
        return gold;
    }

    public final ArrayList<Ressources> getRessources() {
        return coutRessources;
    }

    public final ArrayList<Ressources> getCoutRessources() {
        return ressources;
    }
}