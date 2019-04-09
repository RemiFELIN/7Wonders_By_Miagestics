package commun;

/**
 * @authors Rémi Felin, Yannick Cardini
 */
public class Etape {

    private int pointVictoire;
    private int pointMilitaire;
    private int piece;
    private Ressource[] ressourcesCout;
    private Ressource[] ressourcesBonus;
    private String effet;
    private SymboleScientifique[] symboleScientifique;
    private boolean etat = false;

    /**
     * @param ressourcesCout (Le coût en ressources de l'étape)
     * @param pointVictoire (Les points de victoires que l'étape peut confèrer au joueur)
     * @param pointMilitaire (Les points militaires que l'étape peut confèrer au joueur)
     * @param piece (Les pièces que l'étape peut confèrer au joueur)
     * @param ressourcesBonus (Le tableau de ressources bonus que l'étape peut confèrer au joueur)
     * @param symboleScientifique (Le tableau des symbôles scientifique que l'étape peut confèrer au joueur)
     * @param effet (L'effet que l'étape peut confèrer au joueur (cet effet est valable uniquement pour l'étape concerné) )
     */
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

    /**
     * Cela permet de modifier l'état de l'étape, permettant de signaler que l'etape est 'construite' ('etat' passe de 'false' à 'true')
     */
    public void construire(){
        etat = true;
    }

    /**
     * @return l'état de l'étape
     */
    public final boolean getEtat(){
        return etat;
    }

    /**
     * @return les points de victoires que l'étape confère au joueur
     */
    public final int getPointVictoire(){ return pointVictoire; }

    /**
     * @return les points militaires que l'étape confère au joueur
     */
    public final int getPointMilitaire(){ return pointMilitaire; }

    /**
     * @return les pièces que l'étape confère au joueur
     */
    public final int getPiece(){ return piece; }

    /**
     * @return le coup en ressources permettant de construire la merveille
     */
    public final Ressource[] getRessourcesCout(){ return ressourcesCout; }

    /**
     * @return le tableau de ressources que l'étape confère au joueur
     */
    public final Ressource[] getRessourcesBonus(){ return ressourcesBonus; }

    /**
     * @return l'effet (autrement dit une action de jeu) que l'étape confère au joueur
     */
    public final String getEffet(){ return effet; }

    /**
     * @return le tableau des symboles scientifiques que l'étape confère au joueur
     */
    public final SymboleScientifique[] getSymboleScientifique(){ return symboleScientifique; }

}
