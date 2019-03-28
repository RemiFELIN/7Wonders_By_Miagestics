package client.strategie;

import moteur.VisionJeu;
import moteur.action.PoserCarte;
import moteur.action.Action;

import java.util.Random;

public class StratRandom extends Strategie {

	protected Action getAction(VisionJeu j, boolean[] posSeul, boolean[] posGauche, boolean[] posDroite) {
		return new PoserCarte(j.getId(), new Random().nextInt(j.getDeckMain().size()));
	}

	@Override
	public String toString(){
		return super.toString() + " random";
	}
}
