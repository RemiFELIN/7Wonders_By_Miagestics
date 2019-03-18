package client.strategie;

import moteur.action.PoserCarte;
import moteur.action.Action;

import java.util.ArrayList;
import java.util.Random;

import moteur.Carte;

public class StratLaurier extends Strategie {

    public Action getAction(int idJoueur, ArrayList<Carte> deck) {

        int carteN = 0, nbLaurier = 0;
        for (int i = 0; i < deck.size(); i++){
            int value = deck.get(i).getLaurier();
            if(value > nbLaurier){
                nbLaurier = value;
                carteN = i;
            }
        }
        if(carteN==0 && nbLaurier==0) carteN=new Random().nextInt(deck.size());

        return new PoserCarte(idJoueur, carteN);
	}
	
	@Override
	public String toString(){
		return super.toString() + " laurier";
	}
}
