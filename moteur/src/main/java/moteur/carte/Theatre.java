package moteur.carte;

import java.awt.Color;
import moteur.Carte;
import java.util.ArrayList;
import moteur.Ressources;

public class Theatre extends Carte {

    
    public Theatre(){super(Color.blue,"Theatre",1);}

    @Override
    public int getLaurier() {
        return 2;
    }

    
}