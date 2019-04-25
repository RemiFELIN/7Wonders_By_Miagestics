package client;

import commun.Carte;
import commun.Merveille;
import commun.Etape;
import commun.Couleur;
import commun.SymboleScientifique;
import commun.Ressource;
import commun.EffetGuilde;
import commun.EffetCommercial;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Classe statique permettant de instancier des objets JSON en leurs équivalent Object
 * @author Pierre Saunders
 */
public class JSONParser {

    /**
     * Permet d'instancier un JSONArray de Ressource
     * @param ja JSONArray de Ressource
     * @return les object instanciés de Ressource
     * @throws JSONException
     */
    public static final Ressource[] parseJSONArrayRessource(JSONArray ja) throws JSONException {
        Ressource[] r = new Ressource[ja.length()];
        for (int i = 0; i < ja.length(); i++)
            r[i] = Ressource.fromString(ja.getString(i));
        return r;
    }

    /**
     * Permet d'instancier un JSONArray de SymboleScientifique
     * @param ja JSONArray de SymboleScientifique
     * @return les object instanciés de SymboleScientifique
     * @throws JSONException
     */
    public static final SymboleScientifique[] parseJSONArraySymbole(JSONArray ja) throws JSONException {
        SymboleScientifique[] ssc = new SymboleScientifique[ja.length()];
        for (int i = 0; i < ja.length(); i++)
            ssc[i] = SymboleScientifique.fromString(ja.getString(i));
        return ssc;
    }

    /**
     * Permet d'instancier un JSONArray de Integer
     * @param ja JSONArray de Integer
     * @return les object instanciés de Integer
     * @throws JSONException
     */
    public static final int[] parseJSONArrayInt(JSONArray ja) throws JSONException {
        int[] r = new int[ja.length()];
        for (int i = 0; i < ja.length(); i++)
            r[i] = ja.getInt(i);
        return r;
    }

    /**
     * Permet d'instancier un JSONArray de String
     * @param ja JSONArray de String
     * @return les object instanciés de String
     * @throws JSONException
     */
    public static final String[] parseJSONArrayString(JSONArray ja) throws JSONException {
        String[] r = new String[ja.length()];
        for (int i = 0; i < ja.length(); i++)
            r[i] = ja.getString(i);
        return r;
    }

    /**
     * Permet d'instancier un JSONArray de Carte
     * @param ja JSONArray de Carte
     * @return les object instanciés de Carte
     * @throws JSONException
     */
    public static final ArrayList<Carte> parseJSONArrayCarte(JSONArray ja) throws JSONException {
        ArrayList<Carte> deck = new ArrayList<Carte>();
        for (int i = 0; i < ja.length(); i++) {
            JSONObject obj = ja.getJSONObject(i);
            Carte c;
            if (obj.has("effetGuilde")) {
                // Si carte guilde
                c = new Carte(EffetGuilde.fromString(obj.getString("effetGuilde")));
            } else if (obj.has("effetCommercial")) {
                if (obj.has("batimentSuivant"))
                    c = new Carte(obj.getString("nom"), Couleur.fromString(obj.getString("couleur")), obj.getInt("age"), EffetCommercial.fromString(obj.getString("effetCommercial")),
                            obj.getString("batimentSuivant"));
                else
                    c = new Carte(obj.getString("nom"), Couleur.fromString(obj.getString("couleur")), obj.getInt("age"), EffetCommercial.fromString(obj.getString("effetCommercial")));

            } else {
                if (obj.has("symboleScientifique")) {
                    // si carte scientifique
                    c = new Carte(obj.getString("nom"), Couleur.fromString(obj.getString("couleur")), obj.getInt("age"), SymboleScientifique.fromString(obj.getString("symboleScientifique")),
                            obj.getString("batimentSuivant"));
                } else {
                    // Toutes les autres cartes
                    c = new Carte(obj.getString("nom"), Couleur.fromString(obj.getString("couleur")), obj.getInt("age"), obj.getInt("coutPiece"), obj.getInt("pointVictoire"),
                            obj.getInt("puissanceMilitaire"), obj.getString("batimentSuivant"));
                }
            }

            Ressource[] cr = parseJSONArrayRessource(obj.getJSONArray("coutRessources"));
            for (int j = 0; j < cr.length; j++)
                c.ajouterCoutRessource(cr[j]);

            Ressource[] r = parseJSONArrayRessource(obj.getJSONArray("ressources"));
            for (int j = 0; j < r.length; j++)
                c.ajouterRessource(r[j]);
            deck.add(c);
        }
        return deck;
    }

    /**
     * Permet d'instancier un JSONObject de Merveille
     * @param jm JSONObject de Merveille
     * @return la Merveille instanciée
     * @throws JSONException
     */
    public static final Merveille parseJSONMerveille(JSONObject jm) throws JSONException {
        JSONArray et = jm.getJSONArray("etapes");
        Merveille m = new Merveille(jm.getString("nom"), (jm.getString("face")).charAt(0), Ressource.fromString(jm.getString("ressource")), et.length());

        for (byte i = 0; i < et.length(); i++) {
            JSONObject je = et.getJSONObject(i);
            Etape e = new Etape(parseJSONArrayRessource(je.getJSONArray("ressourcesCout")), je.getInt("pointVictoire"), je.getInt("pointMilitaire"), je.getInt("piece"),
                    parseJSONArrayRessource(je.getJSONArray("ressourcesBonus")), parseJSONArraySymbole(je.getJSONArray("symboleScientifique")), je.getString("effet"));
            m.ajouterEtape(e, i + 1);
        }
        return m;
    }
}