package moteur;

import java.util.ArrayList;

public class Joueur {

    private int id;
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
        return deckMain;
    }

    public final Carte poserCarte(int index){
        Carte c = deckMain.remove(index);
        deckPlateau.add(c);
        return c;
    }

    public final int getScore() {
        int score = 0;
        for(int i=0; i<deckPlateau.size(); i++)
            score += deckPlateau.get(i).getValue();
        
        return score;
    }

    @Override
    public final String toString(){
        return "Joueur "+id;
    }
}