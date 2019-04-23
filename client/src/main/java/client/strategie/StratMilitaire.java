package client.strategie;

import commun.Carte;
import commun.VisionJeu;
import commun.Action;
import static commun.TypeAction.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Choisit la meilleure carte octroyant de la puissance militaire, selon le jeu du Joueur et celui des voisins
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
        int pMilitaire = 0, carteN = 0, joueurAQuiPiocher = 0;
        int prixAchat=2;

        for (int i = 0; i < deck.size(); i++){
            if(posSeul[i])
            {
                int value = deck.get(i).getPuissanceMilitaire();
                if(value > pMilitaire){
                    carteN=i;
                    pMilitaire=value;
                }
            }

        }

        for (int i = 0; i < deck.size(); i++){
            if(posGauche[i])
            {
                int value = deck.get(i).getPuissanceMilitaire();
                if(value > pMilitaire && j.getPiece() > prixAchat){
                    carteN=i;
                    pMilitaire=value;
                }
            }

        }

        for (int i = 0; i < deck.size(); i++){
            if(posDroite[i])
            {
                int value = deck.get(i).getPuissanceMilitaire();
                if(value > pMilitaire && j.getPiece() > prixAchat){
                    carteN=i;
                    pMilitaire=value;
                }
            }

        }

        if(joueurAQuiPiocher != 0) return new Action(AcheterRessource, j.getId(), joueurAQuiPiocher, carteN);

        if(carteN == 0 && pMilitaire == 0) carteN = new Random().nextInt(deck.size());

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
