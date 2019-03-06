package moteur.jsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import moteur.Carte;
import moteur.Jeu;

public class JSONParser {
    public static ArrayList<Carte> JSONToDeck(Object arg){
        try  {
            JSONArray deck = new JSONArray((String) arg);
            ArrayList<Carte> al = new ArrayList<Carte>(deck.length());
            for (int i = 0; i < deck.length(); i++){
                JSONObject oc = deck.getJSONObject(i);
                al.add(new Carte(oc.getInt("valeur"), oc.getInt("age")));
            }
            
            return al;
        } catch (JSONException e){
            Jeu.error("JSON error", e);
            return null;
        }
    }

    public static String deckToJSON(ArrayList<Carte> deck){
        String res = "[";

        for(int i=0; i<deck.size(); i++)
            res += (deck.get(i).toJSON())+",";

        return res+"]";
    }
}