package moteur;

public class Etape {

    private int pointVictoire;
    private int pointMilitaire;
    private int piece;
    private Ressource[] ressourcesCout;
    private Ressource[] ressourcesBonus;
    private String effet;
    private SymboleScientifique[] symboleScientifique;
    private boolean etat = false;

    public Etape(Ressource[] ressourcesCout, int pointVictoire, int pointMilitaire, int piece,
        Ressource[] ressourcesBonus, SymboleScientifique[] symboleScientifique, String effet){
            this.ressourcesCout = ressourcesCout;
            this.pointVictoire = pointVictoire;
            this.pointMilitaire = pointMilitaire;
            this.piece = piece;
            this.ressourcesBonus = ressourcesBonus;
            this.effet = effet;
            this.symboleScientifique = symboleScientifique;
    }

    public void construire(){
        etat = true;
    }

    public final boolean getEtat(){
        return etat;
    }

    public final int getPointVictoire(){ return pointVictoire; }
    public final int getPointMilitaire(){ return pointMilitaire; }
    public final int getPiece(){ return piece; }
    public final Ressource[] getRessourcesCout(){ return ressourcesCout; }
    public final Ressource[] getRessourcesBonus(){ return ressourcesBonus; }
    public final String getEffet(){ return effet; }
    public final SymboleScientifique[] getSymboleScientifique(){ return symboleScientifique; }

}
