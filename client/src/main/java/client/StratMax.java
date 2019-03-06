package client;

import moteur.Coup;
import moteur.Jeu;

import org.json.JSONArray;
import org.json.JSONException;

public class StratMax extends Strategie {

	@Override
	public Coup getCoup(int idJoueur, JSONArray deck) throws JSONException {

        int carteN = -1, carteValue = -1;
        try
        {
            for (int i = 0; i < deck.length(); i++){
                int value = deck.getJSONObject(i).getInt("value");
                if(value > carteValue){
                    carteValue = value;
                    carteN = i;
                }
            }
        }
        catch(JSONException e)
        {
            Jeu.error("Error JSON !", e);
        }
        return new Coup(idJoueur,carteN);
	}
	
	@Override
	public String toString(){
		return super.toString() + " max";
	}
}
