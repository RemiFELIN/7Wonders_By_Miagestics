package moteur;


import java.awt.Color;
import java.util.ArrayList;

public abstract class Carte implements ICarte {

    protected Color couleur;
    protected int coutPiece = 0;
    protected int laurier = 0;
    protected int puissanceMilitaire = 0;
    protected int piece = 0;
    protected ArrayList<Ressources> coutRessources;
    protected ArrayList<Ressources> ressources;
    protected String nom;
    protected String symboleScientifique;//enum a rajouter par la suite
    protected String description;
    protected String nextBuilding;
    protected String specialEffect;
    protected int age;
    

    public Carte(Color c,String n, int a){
        this.couleur = c;//couleur noir pour les ressources
        this.nom = n;
        this.age = a;
    }

    public Color getCouleur() {
        return this.couleur;
    }
    
    public int getValue() {
        return this.laurier;
	}

    public String getNom() {
        return this.nom;
    }

    public int getAge() {
        return this.age;
    }

    public int getCoutPiece() {
        return this.coutPiece;
    }

    public int getLaurier() {
        return this.laurier;
    }

    public int getPuissanceMilitaire() {
        return this.puissanceMilitaire;
    }

    public String getSymboleScientifique() {
        return this.symboleScientifique;
    }

    public String getDescription() {
        return this.description;
    }

    public String getNextBuilding() {
        return this.nextBuilding;
    }

    public String getSpecialEffect() {
        return this.specialEffect;
    }

    public int getPiece() {
        return this.piece;
    }

    public ArrayList<Ressources> getRessources() {
        return this.ressources;
    }

    public ArrayList<Ressources> getCoutRessources() {
        return this.coutRessources;
    }
}
