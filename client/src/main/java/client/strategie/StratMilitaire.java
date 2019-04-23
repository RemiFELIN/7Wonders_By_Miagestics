package client.strategie;

import commun.Carte;
import commun.VisionJeu;
import commun.Action;
import static commun.TypeAction.*;

import java.util.ArrayList;

/**
 * Choisit la meilleure carte octroyant de la puissance militaire, uniquement selon le jeu du Joueur
 * @authors Benoît Montorsi, Pierre Saunders
 */

public class StratMilitaire extends Strategie {

    public StratMilitaire(boolean log){
        super(log);
    }
    /**
     * Récupère l'action la plus adéquate à effectuer
     * @param j vision de jeu actuelle
     * @param posSeul possibilités de jeu soi-même
     * @param posGauche celles de son voisin de gauche
     * @param posDroite celles de son voisin de droite
     * @return l'action à effectuer determinée
     */
    @Override
    protected Action getAction(VisionJeu j, boolean[] posSeul, boolean[] posGauche, boolean[] posDroite ) {

        ArrayList<Carte> deck= j.getDeckMain();
        int pMilitaire = 0, carteN = 0;

        for (int i = 0; i < deck.size(); i++){
            int value = deck.get(i).getPuissanceMilitaire();
            if(value > pMilitaire){
                carteN=i;
                pMilitaire=value;
            }
        }
        return new Action(PoserCarte,j.getId(), carteN);
    }
    /**
     * Décris la stratégie actuelle
     * @see Strategie
     * @return description
     */
    @Override
    public String toString(){ return super.toString() + " militaire"; }
}
