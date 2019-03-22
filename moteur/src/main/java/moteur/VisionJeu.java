package moteur;

import java.util.ArrayList;

public class VisionJeu {

    //Vision de lui-même
    private int id;
    private int piece;
    private ArrayList<Carte> deckMain;
    private ArrayList<Carte> deckPlateau;
    private Merveille plateau;

    //Vision du voisin de gauche
    private int gId;
    private int gPiece;
    private ArrayList<Carte> gDeckPlateau;
    private Merveille gPlateau;

    //Vision du voisin de droite
    private int dId;
    private int dPiece;
    private ArrayList<Carte> dDeckPlateau;
    private Merveille dPlateau;

    //Vision de lui-même
    public VisionJeu(int id, int piece, Merveille plateau, ArrayList<Carte> deckMain, ArrayList<Carte> deckPlateau){
        this(id, piece, plateau, deckPlateau);
        this.deckMain = deckMain;
    }

    //Vision des voisins
    public VisionJeu(int id, int piece, Merveille plateau, ArrayList<Carte> deckPlateau){
        this.id = id;
        this.piece = piece;
        this.deckPlateau = deckPlateau;
        this.plateau = plateau;
    }

    //Vision de lui-même
    public final int getId(){ return id; }
    public final int getPiece(){ return piece; }
    public final Merveille getPlateau(){ return plateau; }
    public final ArrayList<Carte> getDeckMain(){ return deckMain; }
    public final ArrayList<Carte> getDeckPlateau(){ return deckPlateau; }
    //--------------------

    //Vision du voisin de gauche
    public final int getVoisinGaucheId(){ return gId; }
    public final int getVoisinGauchePiece(){ return gPiece; }
    public final Merveille getVoisinGauchePlateau(){ return gPlateau; }
    public final ArrayList<Carte> getVoisinGaucheDeckPlateau(){ return gDeckPlateau; }
    //----------------------

    //Vision du voisin de droite
    public final int getVoisinDroiteId(){ return dId; }
    public final int getVoisinDroitePiece(){ return dPiece; }
    public final Merveille getVoisinDroitePlateau(){ return dPlateau; }
    public final ArrayList<Carte> getVoisinDroiteDeckPlateau(){ return dDeckPlateau; }
    //--------------------------

    public final void setVoisinDroite(VisionJeu droite){
        dId = droite.getId();
        dPiece = droite.getPiece();
        dDeckPlateau = droite.getDeckPlateau();
        dPlateau = droite.getPlateau();
    }

    public final void setVoisinGauche(VisionJeu gauche){
        gId = gauche.getId();
        gPiece = gauche.getPiece();
        gDeckPlateau = gauche.getDeckPlateau();
        gPlateau = gauche.getPlateau();
    }
}