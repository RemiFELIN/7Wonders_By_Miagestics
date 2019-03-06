package moteur.carte;

import java.awt.Color;
import moteur.Carte;
import java.util.ArrayList;
import moteur.Ressources;

public class Caserne extends Carte {

    
    public Caserne(){super(Color.red,"Caserne",1);}


    @Override
    public int getPuissanceMilitaire() {
        return 1;
    }


    @Override
    public ArrayList<Ressources> getCoutRessources() {
        ArrayList<Ressources> r = new ArrayList<Ressources>();
        r.add(Ressources.minerai);
        return r;
    }



    
}