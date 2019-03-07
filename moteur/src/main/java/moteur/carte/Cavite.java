package moteur.carte;

import java.awt.Color;
import moteur.Carte;
import moteur.Ressources;

public class Cavite extends Carte {
    
    public Cavite() {
        super(Color.black, "Cavite", 1);
        super.ressources.add(Ressources.MINERAI);
    }
}