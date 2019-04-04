package client.strategie;

import moteur.Carte;
import moteur.Couleur;
import moteur.VisionJeu;
import moteur.action.AcheterRessource;
import moteur.action.Action;
import moteur.action.PoserCarte;

import java.util.ArrayList;
import java.util.Random;

public class StratScientifique extends Strategie {

    @Override
    protected Action getAction(VisionJeu j, boolean[] posSeul, boolean[] posGauche, boolean[] posDroite) {

        ArrayList<Carte> deck = j.getDeckMain();

        boolean trouveCarteScientifique = false;
        int carteN = 0, joueurAQuiPiocher=0;

        for (int i = 0; i < deck.size(); i++)
            if(posSeul[i]){
                Carte c = deck.get(i);
                if(c.getCouleur() == Couleur.VERT){
                    carteN = i;
                    trouveCarteScientifique = true;
                }
            }

        if(trouveCarteScientifique == false)
            for (int i = 0; i < deck.size(); i++)
                if(posGauche[i]){
                    Carte c = deck.get(i);
                    if(c.getCouleur() == Couleur.VERT){
                        carteN = i;
                        trouveCarteScientifique = true;
                        joueurAQuiPiocher = -1;
                    }
                }

        if(trouveCarteScientifique == false)
            for (int i = 0; i < deck.size(); i++)
                if(posDroite[i]){
                    Carte c = deck.get(i);
                    if(c.getCouleur() == Couleur.VERT){
                        carteN = i;
                        trouveCarteScientifique = true;
                        joueurAQuiPiocher = 1;
                    }
                }

        if(joueurAQuiPiocher != 0) return new AcheterRessource(j.getId(), joueurAQuiPiocher, carteN);

        if(carteN == 0 && trouveCarteScientifique) carteN = new Random().nextInt(deck.size());

        return new PoserCarte(j.getId(), carteN);
    }

    @Override
    public String toString(){
        return super.toString() + " scientifique";
    }
}
