package client;

import moteur.action.PoserCarte;
import moteur.action.Action;

import java.util.ArrayList;
import moteur.Carte;

public class StratMax extends Strategie {

    public Action getAction(int idJoueur, ArrayList<Carte> deck) {

        int carteN = -1, carteValue = -1;
        for (int i = 0; i < deck.size(); i++){
            int value = deck.get(i).getLaurier();
            if(value > carteValue){
                carteValue = value;
                carteN = i;
            }
        }
        
        return new PoserCarte(idJoueur, carteN);
	}
	
	@Override
	public String toString(){
		return super.toString() + " max";
	}
}
