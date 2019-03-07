package moteur.jsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import moteur.Carte;
import moteur.carte.*;

import static moteur.Jeu.error;

public class JSONParser {
    public static ArrayList<Carte> JSONToDeck(Object arg){
        try  {
            JSONArray deck = new JSONArray((String) arg);
            ArrayList<Carte> al = new ArrayList<Carte>(deck.length());
            for (int i = 0; i < deck.length(); i++){
                JSONObject oc = deck.getJSONObject(i);
                Carte c = null;
                switch(oc.getString("nom")){
                    case "Taverne":
                        c = new Taverne();
                    break;

                    case "Cavite":
                        c = new Cavite();
                    break;

                    case "Theatre":
                        c = new Theatre();
                    break;

                    case "Caserne":
                        c = new Caserne();
                    break;
                }
                if(c != null)
                    al.add(c);
            }
            
            return al;
        } catch (JSONException e){
            error("JSON error", e);
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