package client.strategie;

import moteur.Carte;
import moteur.VisionJeu;
import moteur.action.Action;
import moteur.action.PoserCarte;

import java.util.ArrayList;
import java.util.Random;

public class StratMilitaire extends Strategie {

    @Override
    public Action getAction(VisionJeu j) {

        ArrayList<Carte> deck= j.getDeckMain();
        int pMilitaire=0, carteN=0;

        for (int i = 0; i < deck.size(); i++){
            int value = deck.get(i).getPuissanceMilitaire();
            if(value > pMilitaire){
                carteN=i;
                pMilitaire=value;
            }
        }
        return new PoserCarte(j.getId(), carteN);
    }

    @Override
    int[] getPossibilitesGauche(VisionJeu j) {
       return new int[0];
    }

    @Override
    int[] getPossibilitesDroite(VisionJeu j) {
        return new int[0];
    }

    @Override
    int[] getPossibilitesSeul(VisionJeu j) {
        return new int[0];
    }

    @Override
    public String toString(){
        return super.toString() + " militaire";
    }
}
