package client;

import java.util.ArrayList;

import moteur.Carte;
import moteur.Coup;
import org.json.JSONArray;
import org.json.JSONException;

public abstract class Strategie {

	Strategie() {}
	
	public abstract Coup getCoup(JSONArray deck, int idJoueur) throws JSONException;
	
}
