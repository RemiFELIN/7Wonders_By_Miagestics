package commun;

import commun.TypeAction;

/**
 * @author Pierre Saunders, Benoît Montorsi
 */
public class Action {

    private int idJoueur = -1, numVoisin = 0, numeroCarte = -1;
    protected TypeAction type;

    /**
     * Constructeur vide pour la serialization de JSON
     */
    public Action(){}
    /**
     * Constructeur pour Poser carte, défausser carte et construire merveille
     * @param ac TypeAction
     * @param i l'indice du joueur effectuant l'action
     * @param n l'indice de la carte affecter par l'action
     */
    public Action(TypeAction ac, int i, int n){ type = ac; idJoueur = i; numeroCarte = n; }
    /**
     * Constructeur pour Acheter ressource
     * @param ac TypeAction
     * @param i l'indice du joueur effectuant l'action
     * @param nv l'indice de la carte affecter par l'action
     * @param n l'indice du voisin à qui on achète des ressources
     */
    public Action(TypeAction ac, int i, int nv, int n){ this(ac, i, n); numVoisin = nv; }

    /**
     * @return l'indice du joueur effectuant l'action
     */
    public final int getIdJoueur(){ return idJoueur; }
    /**
     * @return le TypeAction
     */
    public final TypeAction getType(){ return type; }
    /**
     * @return l'indice de la carte affecter par l'action
     */
    public final int getNumeroCarte(){ return numeroCarte; }
    /**
     * @see Action constructeur Action pour Acheter ressource
     * @return l'indice du voisin à qui on achète des ressources
     */
    public final int getNumVoisin(){ return numVoisin; }
}