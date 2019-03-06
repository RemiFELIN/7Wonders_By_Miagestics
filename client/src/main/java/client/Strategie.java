package client;

import moteur.action.Action;

import java.util.ArrayList;
import moteur.Carte;

public abstract class Strategie {

	Strategie() {}
	
	public abstract Action getAction(int idJoueur, ArrayList<Carte> deck);
	
	@Override
	public String toString(){
		return "Strategie";
	}
}
