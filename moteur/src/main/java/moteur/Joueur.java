package moteur;

import java.util.ArrayList;

public class Joueur {

    private int id;
    private int gold = 5;
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

    public final ArrayList<Carte> getDeckMain() {
        return this.deckMain;
    }

    public final Carte getDerniereCarte(){
        return this.deckMain.remove(0);
    }

    public final Carte poserCarte(int index){
        Carte c = deckMain.remove(index);
        deckPlateau.add(c);
        return c;
    }

    public final int getScore() {
        int score = 0;
        for(int i=0; i<deckPlateau.size(); i++)
            score += deckPlateau.get(i).getLaurier();
        
        return score;
    }

    public final int getGold(){
        return this.gold;
    }

    @Override
    public final String toString(){
        return "Joueur "+id;
    }
}