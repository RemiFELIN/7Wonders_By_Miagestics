package client;

import moteur.action.PoserCarte;
import moteur.action.Action;

import java.util.ArrayList;
import moteur.Carte;

import java.util.Random;

public class StratRandom extends Strategie {

	public Action getAction(int idJoueur, ArrayList<Carte> deck) {
		Random r=new Random();
		return new PoserCarte(idJoueur, r.nextInt(deck.size()));
	}
	
	@Override
	public String toString(){
		return super.toString() + " random";
	}
}
