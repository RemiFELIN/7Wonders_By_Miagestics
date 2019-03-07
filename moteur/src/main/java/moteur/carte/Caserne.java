package moteur.carte;

import java.awt.Color;
import moteur.Carte;
import moteur.Ressources;

public class Caserne extends Carte {

    public Caserne(){
        super(Color.red, "Caserne", 1);
        super.puissanceMilitaire = 1;
        super.coutRessources.add(Ressources.MINERAI);
    }
}