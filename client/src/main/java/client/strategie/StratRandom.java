package client.strategie;

import commun.VisionJeu;
import static commun.TypeAction.*;
import commun.Action;

import java.util.Random;

public class StratRandom extends Strategie {

	protected Action getAction(VisionJeu j, boolean[] posSeul, boolean[] posGauche, boolean[] posDroite) {
		return new Action(PoserCarte,j.getId(), new Random().nextInt(j.getDeckMain().size()));
	}
    /**
     * Décris la stratégie actuelle
     * @see classe Strategie
     * @return description
     */
	@Override
	public String toString(){ return super.toString() + " random"; }
}
