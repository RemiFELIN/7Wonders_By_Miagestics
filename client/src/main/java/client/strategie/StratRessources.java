package client.strategie;

import moteur.VisionJeu;
import moteur.action.PoserCarte;
import moteur.action.Action;

import java.util.ArrayList;
import java.util.Random;

import moteur.Carte;

@SuppressWarnings("ALL")
public class StratRessources extends Strategie {

    @Override
    public Action getAction(VisionJeu j) {
        int carteN = 0, nbRessources = 0;
        for (int i = 0; i < j.getDeckMain().size(); i++) {
            Carte c = j.getDeckMain().get(i);
            int rs = c.getRessources().size();
            if (rs > nbRessources) {
                carteN = i;
                nbRessources = rs;
            }
        }

        if(carteN==0 && nbRessources==0) carteN=new Random().nextInt(j.getDeckMain().size());

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
        return super.toString() + " ressources";
    }
}