package moteur;

import java.util.ArrayList;

public class VisionJeu {

    private int id;
    private int piece;
    private ArrayList<Carte> deckMain;
    private ArrayList<Carte> deckPlateau;

    //Voisin gauche
    private int gId;
    private int gPiece;
    private ArrayList<Carte> gDeckPlateau;

    //Voisin droite
    private int dId;
    private int dPiece;
    private ArrayList<Carte> dDeckPlateau;

    public VisionJeu(int id, int piece, ArrayList<Carte> deckPlateau){
        this.id = id;
        this.piece = piece;
        this.deckPlateau = deckPlateau;
    }

    public VisionJeu(int id, int piece, ArrayList<Carte> deckMain, ArrayList<Carte> deckPlateau){
        this(id, piece, deckPlateau);
        this.deckMain = deckMain;
    }

    public int getVoisinGaucheId(){
        return gId;
    }

    public int getVoisinGauchePiece(){
        return gPiece;
    }

    public ArrayList<Carte> getVoisinGaucheDeckPlateau(){
        return gDeckPlateau;
    }
    
    public int getVoisinDroiteId(){
        return dId;
    }

    public int getVoisinDroitePiece(){
        return dPiece;
    }

    public ArrayList<Carte> getVoisinDroiteDeckPlateau(){
        return dDeckPlateau;
    }

    public final void setVoisinDroite(VisionJeu droite){
        dId = droite.getId();
        dPiece = droite.getPiece();
        dDeckPlateau = droite.getDeckPlateau();
    }

    public final void setVoisinGauche(VisionJeu gauche){
        gId = gauche.getId();
        gPiece = gauche.getPiece();
        gDeckPlateau = gauche.getDeckPlateau();
    }

    public final int getId(){
        return id;
    }

    public final int getPiece(){
        return piece;
    }

    public final ArrayList<Carte> getDeckMain(){
        return deckMain;
    }

    public final ArrayList<Carte> getDeckPlateau(){
        return deckPlateau;
    }

    @Override
    public final String toString(){
        return "VJ => id:"+id+" p:"+piece+" dm:"+deckMain.size()+" dp:"+deckPlateau.size();
    }
}