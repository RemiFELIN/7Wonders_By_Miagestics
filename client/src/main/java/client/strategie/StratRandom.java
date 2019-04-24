package client.strategie;

import commun.VisionJeu;
import commun.Action;
import static commun.TypeAction.*;

import java.util.Random;

/**
 * Choisit une Carte aléatoirement dans notre jeu
 * @authors Benoît Montorsi, Pierre Saunders
 */
public class StratRandom extends Strategie {

	public StratRandom(boolean log) {
		super(log);
	}

	/**
	 * Récupère une Action de manière aléatoire
	 * @param j vision de jeu actuelle
	 * @param posSeul possibilités de jeu soi-même
	 * @param posGauche celles de son voisin de gauche
	 * @param posDroite celles de son voisin de droite
	 * @return l'action à effectuer determinée
	 */
	@Override
	protected Action getAction(VisionJeu j, boolean[] posSeul, boolean[] posGauche, boolean[] posDroite) {
		return new Action(PoserCarte, j.getId(), new Random().nextInt(j.getDeckMain().size()));
	}

	/**
	 * Décrit la stratégie actuelle
	 * @see Strategie
	 * @return description
	 */
	@Override
	public final String toString() {
		return super.toString() + " random";
	}
}
