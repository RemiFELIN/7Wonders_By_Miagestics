package client.strategie;

import commun.Carte;
import commun.Couleur;
import commun.VisionJeu;
import commun.Action;
import static commun.TypeAction.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Choisit la meilleure carte octroyant une ressource scientifique, selon le jeu du Joueur et celui des voisins
 * @authors Benoît Montorsi, Pierre Saunders
 */

public class StratScientifique extends Strategie {

    public StratScientifique(boolean log) {
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
        int carteN = 0, joueurAQuiPiocher = 0, prixAchat = 2;

        for (int i = 0; i < deck.size(); i++)
            if (posSeul[i]) {
                Carte c = deck.get(i);
                if (c.getCouleur() == Couleur.VERT) {
                    carteN = i;
                }
            }

        for (int i = 0; i < deck.size(); i++)
            if (posGauche[i]) {
                Carte c = deck.get(i);
                if (c.getCouleur() == Couleur.VERT && j.getPiece() > prixAchat) {
                    carteN = i;
                    joueurAQuiPiocher = -1;
                }
            }

        for (int i = 0; i < deck.size(); i++)
            if (posDroite[i]) {
                Carte c = deck.get(i);
                if (c.getCouleur() == Couleur.VERT && j.getPiece() > prixAchat) {
                    carteN = i;
                    joueurAQuiPiocher = 1;
                }
            }

        if (joueurAQuiPiocher != 0)
            return new Action(AcheterRessource, j.getId(), joueurAQuiPiocher, carteN);

        if (carteN == 0)
            carteN = new Random().nextInt(deck.size());

        return new Action(PoserCarte, j.getId(), carteN);
    }

    /**
     * Décris la stratégie actuelle
     * @see Strategie
     * @return description
     */
    @Override
    public final String toString() {
        return super.toString() + " scientifique";
    }
}
