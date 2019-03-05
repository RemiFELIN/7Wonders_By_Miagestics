package moteur.carte;

import java.awt.Color;
import moteur.Carte;
import java.util.ArrayList;
import moteur.Ressources;

public class Theatre extends Carte {

    
    public Theatre(){super(Color.blue,"Theatre",1);}

    @Override
    public int getCoutPiece() {
        return 0;
    }

    @Override
    public int getLaurier() {
        return 2;
    }

    @Override
    public int getPuissanceMilitaire() {
        return 0;
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
        return null;
    }



    
}