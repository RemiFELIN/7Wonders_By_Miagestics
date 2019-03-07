package moteur.carte;

import java.awt.Color;
import moteur.Carte;

public class Taverne extends Carte {

    public Taverne(){
        super(Color.YELLOW, "Taverne", 1);
        super.gold = 5;
    }
}