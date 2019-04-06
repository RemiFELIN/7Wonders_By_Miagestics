package client.strategie;

import commun.Carte;
import commun.VisionJeu;
import commun.Action;
import static commun.TypeAction.*;

import java.util.ArrayList;

public class StratMilitaire extends Strategie {

    protected Action getAction(VisionJeu j, boolean[] posSeul, boolean[] posGauche, boolean[] posDroite ) {

        ArrayList<Carte> deck= j.getDeckMain();
        int pMilitaire = 0, carteN = 0;

        for (int i = 0; i < deck.size(); i++){
            int value = deck.get(i).getPuissanceMilitaire();
            if(value > pMilitaire){
                carteN=i;
                pMilitaire=value;
            }
        }
        return new Action(PoserCarte,j.getId(), carteN);
    }

    @Override
    public String toString(){
        return super.toString() + " militaire";
    }
}
