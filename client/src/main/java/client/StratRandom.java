package client;

import moteur.Coup;

import org.json.JSONArray;
import java.util.Random;

public class StratRandom extends Strategie {

	@Override
	public Coup getCoup(int idJoueur, JSONArray deck) {
		Random r=new Random();
		return new Coup(idJoueur,r.nextInt(deck.length()));
	}
	
	@Override
	public String toString(){
		return super.toString() + " random";
	}
}
