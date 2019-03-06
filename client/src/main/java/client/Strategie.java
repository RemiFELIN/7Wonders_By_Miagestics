package client;

import moteur.Coup;

import org.json.JSONArray;
import org.json.JSONException;

public abstract class Strategie {

	Strategie() {}
	
	public abstract Coup getCoup(int idJoueur, JSONArray deck) throws JSONException;
	
	@Override
	public String toString(){
		return "Strategie";
	}
}
