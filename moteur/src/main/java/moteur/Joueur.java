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
    private int[] jetonsDefaite = new int[3];
    private int[] jetonsVictoire = new int[3];

    //Constructeur vide pour la sérialisation du JSONObject
    public Joueur() {}

    public Joueur(int id) {
        this.id = id;
    }

    /* AJOUT FONCTIONNALITE 'CONSTRUIRE MERVEILLE' */

    public int construireEtape(int indice, int index){
        deckMain.remove(index);
        this.getPlateau().getEtape(indice).construire();
        return indice;
    }

    /*---------------------------------------------*/

    public final int[] getJetonsVictoire(){
        return jetonsVictoire;
    }

    public final int[] getJetonsDefaite(){
        return jetonsDefaite;
    }

    public final void ajouterJetonVictoire(int age){
        jetonsVictoire[age-1]++;
    }

    public final void ajouterJetonDefaite(int age){
        jetonsDefaite[age-1]++;
    }

    public final int getForceMilitaire(){
        int r = 0;

        for(Carte c : deckPlateau)
            r += c.getPuissanceMilitaire();

        return r;
    }

    public final void setDeckMain(ArrayList<Carte> c) {
        this.deckMain = c;
    }

    public final void setPlateau(Merveille m){
        this.plateau = m;
    }

    public final Merveille getPlateau(){
        return plateau;
    }

    public final ArrayList<Carte> getDeckPlateau(){
        return deckPlateau;
    }

    public final ArrayList<Carte> getDeckMain(){
        return deckMain;
    }

    /*public final int construireMerveille(int index){
        deckMain.remove(index);
        return plateau.construireEtape();
    }*/

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

        //Calcul confilts militaire
        for(int i=1; i<jetonsVictoire.length+1; i++)
            score += jetonsVictoire[i-1] * (i * 2 - 1);

        for(int i=1; i<jetonsDefaite.length+1; i++)
            score -= jetonsDefaite[i-1] * (i * 2 - 1);

        if(score < 0)
            score = 0;
        
        return score;
    }

    public final int getPiece(){
        return piece;
    }

    public final int payer(int cout)
    {
        piece-=cout;
        return cout;
    }

    public final void recevoirPaiement(int montant)
    {
        piece+=montant;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    @Override
    public final String toString(){
        return "Joueur "+id;
    }
}