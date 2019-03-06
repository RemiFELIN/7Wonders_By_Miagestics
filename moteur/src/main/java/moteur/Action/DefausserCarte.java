package moteur.action;

public class DefausserCarte extends Action {

    private int numeroCarte;

    public DefausserCarte(int idJoueur, int numeroCarte){
        super(idJoueur);
        this.numeroCarte = numeroCarte;
    }

    public int getNumeroCarte(){
        return numeroCarte;
    }

    public String getType(){
        return "DefausserCarte";
    }
}