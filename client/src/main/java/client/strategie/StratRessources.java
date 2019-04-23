package client.strategie;

import commun.VisionJeu;
import commun.Action;
import commun.Carte;
import static commun.TypeAction.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Choisit la meilleure carte octroyant une ou des ressources, uniquement selon le jeu du Joueur
 * @authors Benoît Montorsi, Pierre Saunders
 */

public class StratRessources extends Strategie {

    public StratRessources(boolean log) {
        super(log);
    }

    /**
     * Récupère l'action la plus adéquate à effectuer
     * @param j vision de jeu actuelle
     * @param posSeul possibilités de jeu soi-même
     * @param posGauche possibilités d'achat avec le voisin de gauche
     * @param posDroite possibilités d'achat avec le voisin de droite
     * @return l'action à effectuer determinée
     */
    @Override
    protected Action getAction(VisionJeu j, boolean[] posSeul, boolean[] posGauche, boolean[] posDroite) {

        ArrayList<Carte> deck = j.getDeckMain();
        int carteN = 0, nbRessources = 0;

        for (int i = 0; i < deck.size(); i++)
            if (posSeul[i]) {
                int value = deck.get(i).getRessources().size();
                if (value > nbRessources) {
                    nbRessources = value;
                    carteN = i;
                }
            }

        if (carteN == 0 && nbRessources == 0)
            carteN = new Random().nextInt(deck.size());

        return new Action(PoserCarte, j.getId(), carteN);
    }

    /**
     * Décrit la stratégie actuelle
     * @see Strategie
     * @return description
     */
    @Override
    public String toString() {
        return super.toString() + " ressources";
    }
}