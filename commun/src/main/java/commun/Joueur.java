package commun;

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;

public class Joueur {

    private int id, piece = 5;
    private Merveille plateau;
    private ArrayList<Carte> deckPlateau = new ArrayList<Carte>();
    private ArrayList<Carte> deckMain = new ArrayList<Carte>();
    private int[] jetonsDefaite = new int[3], jetonsVictoire = new int[3];

    //Constructeur vide pour la sérialisation du JSONObject
    //public Joueur() {}

    /**
     * @param un id
     */
    public Joueur(int id) {
        this.id = id;
    }

    /* AJOUT FONCTIONNALITE 'CONSTRUIRE MERVEILLE' */

    /**
     * @author Rémi Felin
     * @param l'indice de l'étape a construire
     * @param l'indice de la carte qui sert à construire
     * @return l'indice de l'étape construite
     */
    public final int construireEtape(int indice, int index){
        deckMain.remove(index);
        this.getPlateau().getEtape(indice).construire();
        return indice;
    }

    //Ancienne manière
    /**
     * @author Pierre Saunders
     * @deprecated
     * @param l'indice de l'étape a construire
     * @return l'indice de l'étape construite
    */
    /*public final int construireMerveille(int index){
        deckMain.remove(index);
        return plateau.construireEtape();
    }*/

    /*---------------------------------------------*/

    //GETTER
    /**
     * @author Pierre Saunders
     * @return les jetons victoire
     */
    public final int[] getJetonsVictoire(){ return jetonsVictoire; }
    /**
     * @author Pierre Saunders
     * @return les jetons défaite
     */
    public final int[] getJetonsDefaite(){ return jetonsDefaite; }
    /**
     * @return le plateau/merveille
     */
    public final Merveille getPlateau(){ return plateau; }
    /**
     * @return le deck des cartes posés
     */
    public final ArrayList<Carte> getDeckPlateau(){ return deckPlateau; }
    /**
     * @return le deck en main
     */
    public final ArrayList<Carte> getDeckMain(){ return deckMain; }
    /**
     * @return les pieces
     */
    public final int getPiece(){ return piece; }
    /**
     * @return l'id
     */
    public int getId() { return id; }
    /**
     * @author Pierre Saunders
     * @param l'indice age du jeton a ajouter
     */
    public final void ajouterJetonVictoire(int age){ jetonsVictoire[age-1]++; }
    /**
     * @author Pierre Saunders
     * @param l'indice age du jeton a ajouter
     */
    public final void ajouterJetonDefaite(int age){ jetonsDefaite[age-1]++; }
    /**
     * @author Pierre Saunders
     * @return the la force militaire totale
     */
    public final int getForceMilitaire(){
        int r = 0;

        for(Carte c : deckPlateau)
            r += c.getPuissanceMilitaire();

        return r;
    }    
    /**
     * @param le deck en main
     */
    public final void setDeckMain(ArrayList<Carte> c){ deckMain = c; }
    /**
     * @param la merveille
     */
    public final void setPlateau(Merveille m){ plateau = m; }
    /**
     * @author Pierre Saunders
     * @param l'indice de la carte à défausser
     * @return la carte défausser
     */
    public final Carte defausserCarte(int index){
        Carte c = deckMain.remove(index);
        piece += 3;
        return c;
    }
    /**
     * @author Pierre Saunders
     * @param the id
     * @return la carte posé
     */
    public final Carte poserCarte(int index){
        Carte c = deckMain.remove(index);
        deckPlateau.add(c);
        return c;
    }
    /**
     * @return le score total
     */
    public final int getScore() {
        int score = 0;
        
        //Calcul laurier
        for(Carte c : deckPlateau)
            score += c.getLaurier();
        
        //Calcul piece
        score += piece;

        //Calcul scientifique
        HashMap<SymboleScientifique, Integer> scientifique = new HashMap<SymboleScientifique,Integer>();
        for(SymboleScientifique s : SymboleScientifique.values())
            scientifique.put(s, 0);

        for(Carte c : deckPlateau){
            SymboleScientifique symb = c.getSymboleScientifique();
            if( symb != null)
                scientifique.put(symb, scientifique.get(symb)+1);
        }

        int groupe = 99;
        for(SymboleScientifique s : SymboleScientifique.values()){
            int val = scientifique.get(s);
            score += Math.pow(val, 2);
            groupe = Math.min(groupe, val);
        }
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
    /**
     * @author Benoît Montorsi
     * @param le cout à payer
     * @return la cout payer
     */
    public final int payer(int cout){
        piece -= cout;
        return cout;
    }
    /**
     * @author Benoît Montorsi
     * @param le total de piece à recevoir
     */
    public final void recevoirPaiement(int montant){ piece += montant; }
    /**
     * @return description de l'objet
     */
    @Override
    public final String toString(){ return "Joueur "+id; }
}