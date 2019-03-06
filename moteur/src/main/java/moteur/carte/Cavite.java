package moteur.carte;

import java.awt.Color;
import moteur.Carte;
import java.util.ArrayList;
import moteur.Ressources;

public class Cavite extends Carte {

    
    public Cavite() {
        super(Color.black, "Cavite", 1);
    }

    @Override
    public ArrayList<Ressources> getRessources() {
        ArrayList<Ressources> r = new ArrayList<Ressources>();
        r.add(Ressources.BOIS);
        return r;
    }

}