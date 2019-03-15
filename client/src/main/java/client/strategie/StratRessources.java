package client.strategie;

import moteur.action.PoserCarte;
import moteur.action.Action;

import java.util.ArrayList;
import moteur.Carte;

public class StratRessources extends Strategie {

    public Action getAction(int idJoueur, ArrayList<Carte> deck) {

        int carteN = 0, nbRessources = 0;
        for (int i = 0; i < deck.size(); i++) {
            Carte c = deck.get(i);
            int rs = c.getRessources().size();
            if (rs > nbRessources) {
                carteN = i;
                nbRessources = rs;
            }
        }

        return new PoserCarte(idJoueur, carteN);
    }
}