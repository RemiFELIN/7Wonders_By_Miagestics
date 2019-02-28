package client;

import moteur.Carte;
import moteur.Coup;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Random;

public class StratRandom extends Strategie {

	@Override
	public Coup getCoup(JSONArray deck, int idJoueur) {
		Random r=new Random();
		return new Coup(idJoueur,r.nextInt(deck.length()));
	}

}
