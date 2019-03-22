package moteur.action;

public class DefausserCarte extends Action {

    public DefausserCarte(int idJoueur, int numeroCarte){
        super(idJoueur);
        super.numeroCarte = numeroCarte;
        super.type = "defaussercarte";
    }
}