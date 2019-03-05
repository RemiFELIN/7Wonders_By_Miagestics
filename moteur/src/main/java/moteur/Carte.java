package moteur;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Carte implements ICarte {

    private Color couleur;
    private int coutPiece = 0;
    private int laurier = 0;
    private int puissanceMilitaire = 0;
    private ArrayList<Ressources> coutRessources;
    private ArrayList<Ressources> ressources;
    private String nom;
    private String symboleScientifique;//enum a rajouter par la suite
    private String description;
    private String nextBuilding;
    private String getSpecialEffect;
    private int age;

    public Carte(Color c,String n, int a){
        this.couleur = c;//dark pour les ressources
        this.nom = n;
        this.age = a;
    }

    public Color getCouleur() {
        return this.couleur;
    }

    public String getNom() {
        return this.nom;
    }

    public int getAge() {
        return this.age;
    }



}
