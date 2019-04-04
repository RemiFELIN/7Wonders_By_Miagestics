package moteur.action;

public class PoserCarte extends Action {

    public PoserCarte(int idJoueur, int numeroCarte){
        super(idJoueur);
        super.numeroCarte = numeroCarte;
        super.type = "posercarte";
    }
}