package moteur;

import java.util.ArrayList;
import java.util.HashMap;

public class VisionJeu {

    //Vision de lui-même
    private int id;
    private Joueur jr;
    private int piece;
    private ArrayList<Carte> deckMain;
    private ArrayList<Carte> deckPlateau;
    private Merveille plateau;

    //Vision du voisin de gauche
    private int gId;
    private Joueur jGauche;
    private int gPiece;
    private ArrayList<Carte> gDeckPlateau;
    private ArrayList<HashMap<String, Integer>> gRessourcesPlateau;
    private Merveille gPlateau;

    //Vision du voisin de droite
    private int dId;
    private Joueur jDroite;
    private int dPiece;
    private ArrayList<Carte> dDeckPlateau;
    private ArrayList<HashMap<String, Integer>> dRessourcesPlateau;
    private Merveille dPlateau;

    //Vision de lui-même
    public VisionJeu(int id, int piece, Merveille plateau, ArrayList<Carte> deckMain, ArrayList<Carte> deckPlateau){
        this(id, piece, plateau, deckPlateau);
        this.deckMain = deckMain;
    }

    //Vision des voisins
    public VisionJeu( int id, int piece, Merveille plateau, ArrayList<Carte> deckPlateau){
        this.id = id;
        this.piece = piece;
        this.deckPlateau = deckPlateau;
        this.plateau = plateau;
    }

    //Vision de lui-même
    public final int getId(){ return id; }
    public final Joueur getJoueur(){ return jr; }
    public final void setJoueur(Joueur j) { this.jr=j;}
    public final int getPiece(){ return piece; }
    public final Merveille getPlateau(){ return plateau; }
    public final ArrayList<Carte> getDeckMain(){ return deckMain; }
    public final ArrayList<Carte> getDeckPlateau(){ return deckPlateau; }
    //--------------------

    //Vision du voisin de gauche
    public final int getVoisinGaucheId(){ return gId; }
    public final Joueur getGJoueur(){ return jGauche; }
    public final int getVoisinGauchePiece(){ return gPiece; }
    public final Merveille getVoisinGauchePlateau(){ return gPlateau; }
    public final ArrayList<Carte> getVoisinGaucheDeckPlateau(){ return gDeckPlateau; }
    //----------------------

    //Vision du voisin de droite
    public final int getVoisinDroiteId(){ return dId; }
    public final Joueur getDJoueur(){ return jDroite; }
    public final int getVoisinDroitePiece(){ return dPiece; }
    public final Merveille getVoisinDroitePlateau(){ return dPlateau; }
    public final ArrayList<Carte> getVoisinDroiteDeckPlateau(){ return dDeckPlateau; }
    //--------------------------

    public final void setVoisinDroite(VisionJeu droite){
        jDroite=droite.getJoueur();
        dId = droite.getId();
        dPiece = droite.getPiece();
        dDeckPlateau = droite.getDeckPlateau();
        dPlateau = droite.getPlateau();
    }

    public final void setVoisinGauche(VisionJeu gauche){
        jGauche=gauche.getJoueur();
        gId = gauche.getId();
        gPiece = gauche.getPiece();
        gDeckPlateau = gauche.getDeckPlateau();
        gPlateau = gauche.getPlateau();
    }

    public ArrayList<HashMap<String, Integer>> calculRessources(ArrayList<Carte> deck)
    {  int nbBois = 0, nbMinerais = 0, nbArgile = 0, nbPierre = 0, nbVerre = 0, nbTextile = 0, nbPapyrus = 0;
        ArrayList<HashMap<String, Integer>> tmp=new ArrayList<>();

        for(int i=0; i<deck.size();i++)
        {
            Carte c=deck.get(i);
            ArrayList<Ressource> res=c.getRessources();

            for(int j=0;j<res.size();j++)
            {
                if(res.get(j)==Ressource.BOIS) nbBois++;
                if(res.get(j)==Ressource.MINERAI) nbMinerais++;
                if(res.get(j)==Ressource.ARGILE) nbArgile++;
                if(res.get(j)==Ressource.PIERRE) nbPierre++;
                if(res.get(j)==Ressource.VERRE) nbVerre++;
                if(res.get(j)==Ressource.TEXTILE) nbTextile++;
                if(res.get(j)==Ressource.PAPYRUS) nbPapyrus++;

            }
        }

        tmp.add(new HashMap<String, Integer>());
        tmp.add(new HashMap<String, Integer>());
        tmp.add(new HashMap<String, Integer>());
        tmp.add(new HashMap<String, Integer>());
        tmp.add(new HashMap<String, Integer>());
        tmp.add(new HashMap<String, Integer>());
        tmp.add(new HashMap<String, Integer>());

        tmp.get(0).put("Bois",nbBois);
        tmp.get(1).put("Minerais",nbMinerais);
        tmp.get(2).put("Argile",nbArgile);
        tmp.get(3).put("Pierre",nbPierre);
        tmp.get(4).put("Verre",nbVerre);
        tmp.get(5).put("Textile",nbTextile);
        tmp.get(6).put("Papyrus",nbPapyrus);

        return tmp;
    }
}