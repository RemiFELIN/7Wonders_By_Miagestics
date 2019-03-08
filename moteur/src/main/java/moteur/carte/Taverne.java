package moteur.carte;

import java.awt.Color;
import moteur.Carte;
import java.util.ArrayList;
import moteur.Ressources;

public class Taverne extends Carte {

    public Taverne(){
    	super(Color.YELLOW,"Taverne",1);
    	super.piece = 5;
    }
}