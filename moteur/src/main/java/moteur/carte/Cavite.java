package moteur.carte;

import java.awt.Color;
import moteur.Carte;
import java.util.ArrayList;
import moteur.Ressources;

public class Cavite extends Carte {
    
    public Cavite() {
        super(Color.black, "Cavite", 1);
        ArrayList<Ressources> r = new ArrayList<Ressources>();
        r.add(Ressources.MINERAI);
        super.ressources = r;
    }
}