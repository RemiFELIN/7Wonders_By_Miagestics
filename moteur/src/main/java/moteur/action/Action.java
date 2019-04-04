package moteur.action;

public class Action {

    private int idJoueur = -1;
    protected int numVoisin=0;
    protected int numeroCarte = -1;
    protected String type = "Action";

    //Constructeur vide pour la serialization de JSON
    public Action(){}
    public Action(int idJoueur){ this.idJoueur = idJoueur; }
    public final int getIdJoueur(){ return idJoueur; }
    public final String getType(){ return type; }
    public final int getNumeroCarte(){ return numeroCarte; }
    public final int getNumVoisin(){ return numVoisin; }


}