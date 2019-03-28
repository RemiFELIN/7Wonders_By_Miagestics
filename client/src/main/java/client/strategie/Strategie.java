package client.strategie;

import moteur.VisionJeu;
import moteur.action.Action;

import java.util.ArrayList;
import moteur.Carte;

public abstract class Strategie {

	Strategie() {}
	
	public abstract Action getAction(VisionJeu j);
	abstract int[] getPossibilitesGauche(VisionJeu j);
	abstract int[] getPossibilitesDroite(VisionJeu j);
	abstract int[] getPossibilitesSeul(VisionJeu j);
	
	@Override
	public String toString(){
		return "Strategie";
	}
}