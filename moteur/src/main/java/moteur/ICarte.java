package moteur;

import java.awt.Color;
import java.util.ArrayList;


interface ICarte{
    Color getCouleur();
    int getCoutPiece();
    int getLaurier();
    int getPuissanceMilitaire();
    int getAge();
    int getGold();
    ArrayList<Ressources> getRessources();
    ArrayList<Ressources> getCoutRessources();
    String getNom();
    String getSymboleScientifique();//enum a rajouter par la suite
    String getDescription();
    String getNextBuilding();
    String getSpecialEffect();

}