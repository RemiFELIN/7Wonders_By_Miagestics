package moteur;

import java.util.ArrayList;

/**
 * @author Pierre Saunders
 */
public class VisionJeu {

    //Vision de lui-même
    private int id, piece;
    private ArrayList<Carte> deckMain, deckPlateau;
    private Merveille plateau;

    //Vision du voisin de gauche
    private int gId, gPiece;
    private ArrayList<Carte> gDeckPlateau;
    private Merveille gPlateau;

    //Vision du voisin de droite
    private int dId, dPiece;
    private ArrayList<Carte> dDeckPlateau;
    private Merveille dPlateau;

    /**
     * Constructeur vision de soi-même
     * @param 
     */
    public VisionJeu(Joueur j){
        this(j.getId(), j.getPiece(), j.getPlateau(), j.getDeckMain(), j.getDeckPlateau());
    }
    /**
     * Constructeur de la vision de soi-même
     * @param notre id
     * @param notre nombre de piece
     * @param notre merveille
     * @param notre deck en main
     * @param notre deck des cartes posées
     */
    public VisionJeu(int id, int piece, Merveille plateau, ArrayList<Carte> deckMain, ArrayList<Carte> deckPlateau){
        this(id, piece, plateau, deckPlateau);
        this.deckMain = deckMain;
    }
    /**
     * Constructeur de la vision des voisins
     * @param son id
     * @param son nombre de piece
     * @param sa merveille
     * @param son deck des cartes posées
     */
    public VisionJeu( int id, int piece, Merveille plateau, ArrayList<Carte> deckPlateau){
        this.id = id;
        this.piece = piece;
        this.deckPlateau = deckPlateau;
        this.plateau = plateau;
    }

    //Vision de soi-même
    /**
     * @return notre id
     */
    public final int getId(){ return id; }
    /**
     * @return notre nombre de piece
     */
    public final int getPiece(){ return piece; }
    /**
     * @return notre merveille
     */
    public final Merveille getPlateau(){ return plateau; }
    /**
     * @return notre deck en main
     */
    public final ArrayList<Carte> getDeckMain(){ return deckMain; }
    /**
     * @return notre deck des cartes posées
     */
    public final ArrayList<Carte> getDeckPlateau(){ return deckPlateau; }

    //Vision du voisin de gauche
    /**
     * @return l'id du voisin de gauche
     */
    public final int getVoisinGaucheId(){ return gId; }
    /**
     * @return le nombre de piece du voisin de gauche
     */
    public final int getVoisinGauchePiece(){ return gPiece; }
    /**
     * @return la merveille du voisin de gauche
     */
    public final Merveille getVoisinGauchePlateau(){ return gPlateau; }
    /**
     * @return le deck des cartes posées du voisin de gauche
     */
    public final ArrayList<Carte> getVoisinGaucheDeckPlateau(){ return gDeckPlateau; }

    //Vision du voisin de droite
    /**
     * @return l'id du voisin de droite
     */
    public final int getVoisinDroiteId(){ return dId; }
    /**
     * @return le nombre de piece du voisin de droite
     */
    public final int getVoisinDroitePiece(){ return dPiece; }
    /**
     * @return la merveille du voisin de droite
     */
    public final Merveille getVoisinDroitePlateau(){ return dPlateau; }
    /**
     * @return le deck des cartes posées du voisin de droite
     */
    public final ArrayList<Carte> getVoisinDroiteDeckPlateau(){ return dDeckPlateau; }

    /**
     * @param la VisionJeu du voisin de droite
     */
    public final void setVoisinDroite(VisionJeu droite){
        dId = droite.getId();
        dPiece = droite.getPiece();
        dDeckPlateau = droite.getDeckPlateau();
        dPlateau = droite.getPlateau();
    }
    /**
     * @param la VisionJeu du voisin de gauche
     */
    public final void setVoisinGauche(VisionJeu gauche){
        gId = gauche.getId();
        gPiece = gauche.getPiece();
        gDeckPlateau = gauche.getDeckPlateau();
        gPlateau = gauche.getPlateau();
    }
}