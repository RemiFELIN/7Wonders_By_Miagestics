package client.strategie;

import commun.VisionJeu;
import static commun.TypeAction.*;
import commun.Action;
import commun.Carte;

import java.util.ArrayList;
import java.util.Random;

/**
 * Choisit la meilleure carte octroyant une ou des ressources, selon le jeu du Joueur et celui des voisins
 * @authors Benoît Montorsi, Pierre Saunders
 */

public class StratRessources extends Strategie {

    /**
     * Récupère l'action la plus adéquate à effectuer
     * @param la vision de jeu actuelle
     * @param les possibilités de jeu soi-même
     * @param les possibilités d'achat avec le voisin de gauche
     * @param les possibilités d'achat avec le voisin de droite
     * @return l'action à effectuer determinée
     */

    @Override
    protected Action getAction(VisionJeu j, boolean[] posSeul, boolean[] posGauche, boolean[] posDroite) {

        ArrayList<Carte> deck = j.getDeckMain();
        int carteN = 0, nbRessources = 0, joueurAQuiPiocher = 0;
        
        for (int i = 0; i < deck.size(); i++)
            if(posSeul[i]){
                int value = deck.get(i).getRessources().size();
                if(value > nbRessources){
                    nbRessources = value;
                    carteN = i;
                }
            }

        for (int i = 0; i < deck.size(); i++)
            if(posGauche[i]){
                int value = deck.get(i).getRessources().size();
                if(value > nbRessources){
                    nbRessources = value;
                    carteN = i;
                    joueurAQuiPiocher = -1;
                }
            }

        for (int i = 0; i < deck.size(); i++)
            if(posDroite[i]){
                int value = deck.get(i).getRessources().size();
                if(value > nbRessources){
                    nbRessources = value;
                    carteN = i;
                    joueurAQuiPiocher = 1;
                }
            }

        if(joueurAQuiPiocher != 0) return new Action(AcheterRessource, j.getId(), joueurAQuiPiocher, carteN);

        if(carteN == 0 && nbRessources == 0) carteN = new Random().nextInt(deck.size());

        return new Action(PoserCarte,j.getId(), carteN);
    }
    /**
     * Décrit la stratégie actuelle
     * @see classe Strategie
     * @return description
     */
    @Override
    public String toString(){ return super.toString() + " ressources"; }
}