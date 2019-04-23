package commun;

import java.util.ArrayList;

/**
 * @author Pierre Saunders
 */
public class VisionJeu {

    //Vision de lui-même
    private int id, piece, jetonsDefaite;
    private int[] jetonsVictoire;
    private ArrayList<Carte> deckMain, deckPlateau;
    private Merveille plateau;

    //Vision du voisin de gauche
    private int gId, gPiece, gJetonsDefaite;
    private int[] gJetonsVictoire;
    private ArrayList<Carte> gDeckPlateau;
    private Merveille gPlateau;

    //Vision du voisin de droite
    private int dId, dPiece, dJetonsDefaite;
    private int[] dJetonsVictoire;
    private ArrayList<Carte> dDeckPlateau;
    private Merveille dPlateau;

    /**
     * Constructeur vision de soi-même
     * @param j un Joueur
     */
    public VisionJeu(Joueur j){
        this(j.getId(), j.getPiece(), j.getJetonsVictoire(), j.getJetonsDefaite(), j.getPlateau(), j.getDeckMain(), j.getDeckPlateau());
    }
    /**
     * Constructeur de la vision de soi-même
     * @param id id
     * @param piece nombre de piece
     * @param jetonsVictoire les jetons de victoire
     * @param jetonsDefaite les jetons de défaite
     * @param plateau merveille
     * @param deckMain deck en main
     * @param deckPlateau deck des cartes posées
     */
    public VisionJeu(int id, int piece, int[] jetonsVictoire, int jetonsDefaite, Merveille plateau, ArrayList<Carte> deckMain, ArrayList<Carte> deckPlateau){
        this(id, piece, jetonsVictoire, jetonsDefaite, plateau, deckPlateau);
        this.deckMain = deckMain;
    }
    /**
     * Constructeur de la vision des voisins
     * @param id id
     * @param piece nombre de piece
     * @param jetonsVictoire les jetons de victoire
     * @param jetonsDefaite les jetons de défaite
     * @param plateau merveille
     * @param deckPlateau deck des cartes posées
     */
    public VisionJeu(int id, int piece, int[] jetonsVictoire, int jetonsDefaite, Merveille plateau, ArrayList<Carte> deckPlateau){
        this.id = id;
        this.piece = piece;
        this.jetonsVictoire = jetonsVictoire;
        this.jetonsDefaite = jetonsDefaite;
        this.plateau = plateau;
        this.deckPlateau = deckPlateau;
    }

    //Vision de soi-même
    public final int getNbEtapes(){
        int i=0;
        for(Etape e : plateau.getEtapes())
            if(e.getEtat()){
                i++;
            }
        return i;
    }
    /**
     * @return le nom de la merveille
     */
    public final String getNomMerveille(){ return dPlateau.getNom(); }
    /**
     * @return notre id
     */
    public final int getId(){ return id; }
    /**
     * @return notre nombre de piece
     */
    public final int getPiece(){ return piece; }
    /**
     * @return nos jetons de victoire
     */
    public final int[] getJetonsVictoire(){ return jetonsVictoire; }
    /**
     * @return nos jetons de défaite
     */
    public final int getJetonsDefaite(){ return jetonsDefaite; }
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
     * @return les jetons de victoire du voisin de gauche
     */
    public final int[] getVoisinGaucheJetonsVictoire(){ return gJetonsVictoire; }
    /**
     * @return les jetons de défaite du voisin de gauche
     */
    public final int getVoisinGaucheJetonsDefaite(){ return gJetonsDefaite; }
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
     * @return les jetons de victoire du voisin de droite
     */
    public final int[] getVoisinDroiteJetonsVictoire(){ return dJetonsVictoire; }
    /**
     * @return les jetons de défaite du voisin de droite
     */
    public final int getVoisinDroiteJetonsDefaite(){ return dJetonsDefaite; }
    /**
     * @return la merveille du voisin de droite
     */
    public final Merveille getVoisinDroitePlateau(){ return dPlateau; }
    /**
     * @return le deck des cartes posées du voisin de droite
     */
    public final ArrayList<Carte> getVoisinDroiteDeckPlateau(){ return dDeckPlateau; }

    /**
     * @param droite VisionJeu du voisin de droite
     */
    public final void setVoisinDroite(VisionJeu droite){
        dId = droite.getId();
        dPiece = droite.getPiece();
        dJetonsVictoire = droite.getJetonsVictoire();
        dJetonsDefaite = droite.getJetonsDefaite();
        dDeckPlateau = droite.getDeckPlateau();
        dPlateau = droite.getPlateau();
    }
    /**
     * @param gauche VisionJeu du voisin de gauche
     */
    public final void setVoisinGauche(VisionJeu gauche){
        gId = gauche.getId();
        gPiece = gauche.getPiece();
        gJetonsVictoire = gauche.getJetonsVictoire();
        gJetonsDefaite = gauche.getJetonsDefaite();
        gDeckPlateau = gauche.getDeckPlateau();
        gPlateau = gauche.getPlateau();
    }
}