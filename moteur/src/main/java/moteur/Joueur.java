package moteur;

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;

public class Joueur {

    private int id;
    private int piece = 5;
    private Merveille plateau;
    private ArrayList<Carte> deckPlateau = new ArrayList<Carte>();
    private ArrayList<Carte> deckMain = new ArrayList<Carte>();

    //Constructeur vide pour la sérialisation du JSONObject
    public Joueur() {}

    public Joueur(int id) {
        this.id = id;
    }

    public final void setDeckMain(ArrayList<Carte> c) {
        this.deckMain = c;
    }

    public final void setPlateau(Merveille m){
        this.plateau = m;
    }

    public final ArrayList<Carte> getDeckMain() {
        return this.deckMain;
    }

    public final Carte getDerniereCarte(){
        return this.deckMain.remove(0);
    }

    public final Carte defausserCarte(int index){
        Carte c = deckMain.remove(index);
        piece += 3;
        return c;
    }

    public final Carte poserCarte(int index){
        Carte c = deckMain.remove(index);
        deckPlateau.add(c);
        return c;
    }

    public final int getScore() {
        int score = 0;
        for(int i=0; i<deckPlateau.size(); i++){
            Carte c = deckPlateau.get(i);
            //Calcul laurier
            score += c.getLaurier();
        }
        
        //Calcul piece
        score += piece;

        //Calcul scientifique
        HashMap<SymboleScientifique,Integer> scientifique = new HashMap<SymboleScientifique,Integer>();
        scientifique.put(SymboleScientifique.COMPAS,0);
        scientifique.put(SymboleScientifique.TABLETTE,0);
        scientifique.put(SymboleScientifique.ROUAGE,0);
        for(int i=0; i<deckPlateau.size(); i++){
            Carte c = deckPlateau.get(i);
            SymboleScientifique symb = c.getSymboleScientifique();
            if( symb != null){
                scientifique.put(symb,scientifique.get(symb)+1);
            }
        }
        score += Math.pow(scientifique.get(SymboleScientifique.COMPAS),2);
        score += Math.pow(scientifique.get(SymboleScientifique.TABLETTE),2);
        score += Math.pow(scientifique.get(SymboleScientifique.ROUAGE),2);
        int groupe = Math.min(Math.min(scientifique.get(SymboleScientifique.COMPAS),scientifique.get(SymboleScientifique.TABLETTE)),scientifique.get(SymboleScientifique.ROUAGE));
        score += 7 * groupe;

        return score;
    }

    public final int getPiece(){
        return piece;
    }

    @Override
    public final String toString(){
        return "Joueur "+id;
    }
}