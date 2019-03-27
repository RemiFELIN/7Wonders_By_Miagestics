package moteur;

public class Etape {

    private int pointVictoire;
    private int pointMilitaire;
    private int piece;
    private Ressource[] ressources;
    private String effet;
    private SymboleScientifique[] symboleScientifique;

    Etape(){}

    public void setCoup(Ressource[] ressources){
        this.ressources = ressources;
    }

    public void setBonus(int pointVictoire, int pointMilitaire, int piece, Ressource[] ressources, SymboleScientifique[] symboleScientifique, String effet){
        this.pointVictoire = pointVictoire;
        this.pointMilitaire = pointMilitaire;
        this.piece = piece;
        this.ressources = ressources;
        this.effet = effet;
        this.symboleScientifique = symboleScientifique;
    }

}
