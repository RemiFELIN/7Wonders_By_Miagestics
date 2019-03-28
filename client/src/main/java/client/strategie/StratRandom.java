package client.strategie;

import moteur.VisionJeu;
import moteur.action.PoserCarte;
import moteur.action.Action;

import java.util.ArrayList;
import moteur.Carte;

import java.util.Random;

public class StratRandom extends Strategie {

	@Override
	public Action getAction(VisionJeu j) {
		Random r=new Random();
		return new PoserCarte(j.getId(), r.nextInt(j.getDeckMain().size()));
	}

	@Override
	int[] getPossibilitesGauche(VisionJeu j) {
		return new int[0];
	}

	@Override
	int[] getPossibilitesDroite(VisionJeu j) {
		return new int[0];
	}

	@Override
	int[] getPossibilitesSeul(VisionJeu j) {
		return new int[0];
	}

	@Override
	public String toString(){
		return super.toString() + " random";
	}
}
