package moteur.action;

public class PoserCarte extends Action {

    private int numeroCarte;

    public PoserCarte(int idJoueur, int numeroCarte){
        super(idJoueur);
        this.numeroCarte = numeroCarte;
    }

    public int getNumeroCarte(){
        return numeroCarte;
    }

    public String getType(){
        return "PoserCarte";
    }
}