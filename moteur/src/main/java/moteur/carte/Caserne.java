package moteur.carte;

import java.awt.Color;
import moteur.Carte;
import java.util.ArrayList;
import moteur.Ressources;

public class Caserne extends Carte {

    
    public Caserne(){super(Color.red,"Caserne",1);}

    @Override
    public int getCoutPiece() {
        return 0;
    }

    @Override
    public int getLaurier() {
        return 0;
    }

    @Override
    public int getPuissanceMilitaire() {
        return 1;
    }

    @Override
    public String getSymboleScientifique() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getNextBuilding() {
        return null;
    }

    @Override
    public String getSpecialEffect() {
        return null;
    }

    @Override
    public int getGold() {
        return 0;
    }

    @Override
    public ArrayList<Ressources> getRessources() {
        return null;
    }

    @Override
    public ArrayList<Ressources> getCoutRessources() {
        ArrayList<Ressources> r = new ArrayList<Ressources>();
        r.add(Ressources.minerai);
        return r;
    }



    
}