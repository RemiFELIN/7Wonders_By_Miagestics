package client.strategie;

import moteur.VisionJeu;
import moteur.action.AcheterRessource;
import moteur.action.PoserCarte;
import moteur.action.Action;

import java.util.ArrayList;
import java.util.Random;

import moteur.Carte;

public class StratLaurier extends Strategie {

    protected Action getAction(VisionJeu j, boolean[] posSeul, boolean[] posGauche, boolean[] posDroite) {

        ArrayList<Carte> deck = j.getDeckMain();

        int carteN = 0, nbLaurier = 0, joueurAQuiPiocher = 0;
        for (int i = 0; i < deck.size(); i++)
            if(posSeul[i]){
                int value = deck.get(i).getLaurier();
                if(value > nbLaurier){
                    nbLaurier = value;
                    carteN = i;
                }
            }

        for (int i = 0; i < deck.size(); i++)
            if(posGauche[i]){
                int value = deck.get(i).getLaurier();
                if(value > nbLaurier){
                    nbLaurier = value;
                    carteN = i;
                    joueurAQuiPiocher = -1;
                }
            }

        for (int i = 0; i < deck.size(); i++)
            if(posDroite[i]){
                int value = deck.get(i).getLaurier();
                if(value > nbLaurier){
                    nbLaurier = value;
                    carteN = i;
                    joueurAQuiPiocher = 1;
                }
            }

        if(joueurAQuiPiocher != 0) return new AcheterRessource(j.getId(), joueurAQuiPiocher, carteN);

        if(carteN == 0 && nbLaurier == 0) carteN = new Random().nextInt(deck.size());

        return new PoserCarte(j.getId(), carteN);
	}
	
	@Override
	public String toString(){
		return super.toString() + " laurier";
	}
}
