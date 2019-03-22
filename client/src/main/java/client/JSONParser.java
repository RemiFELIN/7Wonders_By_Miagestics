package client;

import moteur.Ressource;
import moteur.Carte;
import moteur.Couleur;

import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;

public class JSONParser {
    
    public static final Ressource[][] parseJSONRessource(JSONArray ja){
        Ressource[][] r = new Ressource[ja.length()][];
        for(int i=0; i<ja.length(); i++){
            if(ja.isNull(i) == false){
                JSONArray jb = ja.getJSONArray(i);
                r[i] = new Ressource[jb.length()];
                for(int j=0; j<jb.length(); j++)
                    r[i][j] = Ressource.fromString(jb.getString(j));
            }
        }
        return r;
    }

    public static final int[] parseJSONArrayInt(JSONArray ja){
        int[] r = new int[ja.length()];
        for(int i=0; i<ja.length(); i++)
            r[i] = ja.getInt(i);
        return r;
    }
    
    public static final String[] parseJSONArrayString(JSONArray ja){
        String[] r = new String[ja.length()];
        for(int i=0; i<ja.length(); i++)
            r[i] = ja.getString(i);
        return r;
    }

    public static final ArrayList<Carte> parseJSONArray(JSONArray ja){
        ArrayList<Carte> deck = new ArrayList<Carte>();
        for(int i = 0; i < ja.length(); i++){
            JSONObject obj = ja.getJSONObject(i);
            Carte c = new Carte(
                obj.getString("nom"),
                Couleur.fromString(obj.getString("couleur")),
                obj.getInt("age"),
                obj.getInt("coutPiece"),
                obj.getInt("laurier"),
                obj.getInt("puissanceMilitaire"),
                obj.getInt("piece")
            );
            JSONArray coutRessources = (JSONArray) obj.get("coutRessources");
            for(int j=0; i<coutRessources.length(); i++)
                c.ajouterCoutRessource(Ressource.fromString(coutRessources.getString(j)));

            JSONArray ressources = (JSONArray) obj.get("ressources");
            for(int j=0; i<ressources.length(); i++)
                c.ajouterRessource(Ressource.fromString(ressources.getString(j)));
            deck.add(c);
        }
        return deck;
    }
}