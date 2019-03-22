package moteur.action;

public class Action {

    private int idJoueur;

    protected int numeroCarte;
    protected String type = "Action";

    //Constructeur vide pour la serialization de JSON
    public Action(){}
    public Action(int idJoueur){ this.idJoueur = idJoueur; }
    public final int getIdJoueur(){ return idJoueur; }
    public final String getType(){ return type; }
    public final int getNumeroCarte(){ return numeroCarte; }
}