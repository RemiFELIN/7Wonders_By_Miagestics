package moteur.carte;

import java.awt.Color;
import moteur.Carte;
import java.util.ArrayList;
import moteur.Ressources;

public class Caserne extends Carte {

    public Caserne(){
        super(Color.red, "Caserne", 1);
        super.puissanceMilitaire = 1;
        ArrayList<Ressources> r = new ArrayList<Ressources>();
        r.add(Ressources.MINERAI);
        super.coutRessources = r;
    }
}