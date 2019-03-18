package client.strategie;

import moteur.Carte;
import moteur.action.Action;
import moteur.action.PoserCarte;

import java.util.ArrayList;
import java.util.Random;

public class StratMilitaire extends Strategie {

    public Action getAction(int idJoueur, ArrayList<Carte> deck) {

        int carteN = 0, pMilitaire = 0;
        for (int i = 0; i < deck.size(); i++) {
            Carte c = deck.get(i);
            int pm = c.getPuissanceMilitaire();
            if (pm > pMilitaire) {
                carteN = i;
                pMilitaire = pm;
            }
        }

        if(carteN==0 && pMilitaire==0) carteN=new Random().nextInt(deck.size());

        return new PoserCarte(idJoueur, carteN);
    }

    @Override
    public String toString(){
        return super.toString() + " militaire";
    }
}
