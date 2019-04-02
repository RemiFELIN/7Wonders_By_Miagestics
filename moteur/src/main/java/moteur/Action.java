package moteur;

import moteur.TypeAction;

public class Action {

    private int idJoueur = -1;
    protected int numVoisin = 0;
    protected int numeroCarte = -1;
    protected TypeAction type;

    //Constructeur vide pour la serialization de JSON
    public Action(){}

    //Poser carte | défausser carte | construire merveille 
    public Action(TypeAction ac, int i, int n){ type = ac; idJoueur = i; numeroCarte = n; }

    //Acheter ressource
    public Action(TypeAction ac, int i, int nv, int n){ this(ac, i, n); numVoisin = nv; }

    public final int getIdJoueur(){ return idJoueur; }
    public final TypeAction getType(){ return type; }

    public final int getNumeroCarte(){ return numeroCarte; }
    public final int getNumVoisin(){ return numVoisin; }
}