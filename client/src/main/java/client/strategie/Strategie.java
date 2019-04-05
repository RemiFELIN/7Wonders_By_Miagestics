package client.strategie;

import moteur.VisionJeu;
import moteur.Action;
import moteur.Carte;
import moteur.Ressource;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Strategie {

	//Strategie() {}
    
    private HashMap<Ressource, Integer> resSeul;

	public final Action getAction(VisionJeu j){
        boolean[] posSeul = getPossibilitesSeul(j);
        boolean[] posGauche = getPossibilitesGauche(posSeul, resSeul, j);
        boolean[] posDroite = getPossibilitesDroite(posSeul, resSeul, j);
        return getAction(j, posSeul, posGauche, posDroite);
    }

    protected abstract Action getAction(VisionJeu j, boolean[] posSeul, boolean[] posGauche, boolean[] posDroite);

    protected final boolean[] getPossibilitesGauche(boolean[] posSeul, HashMap<Ressource, Integer> resSeul, VisionJeu j){
        ArrayList<Carte> deckMain = j.getDeckMain();
        boolean[] possibilites = new boolean[deckMain.size()];

        HashMap<Ressource, Integer> ressourcesJ = calculRessources(j.getVoisinGaucheDeckPlateau());

        for (int i = 0; i < deckMain.size(); i++){
            if(posSeul[i] == false){
                HashMap<Ressource, Integer> prixCarte = calculPrixCarte(deckMain.get(i));
                boolean achetable = true;
                for(Ressource r : Ressource.values())
                    if(resSeul.get(r) + ressourcesJ.get(r) - prixCarte.get(r) < 0){
                        achetable = false;
                        break;
                    }
                possibilites[i] = achetable;
            }
        }
        return possibilites;
    }

    protected final boolean[] getPossibilitesDroite(boolean[] posSeul, HashMap<Ressource, Integer> resSeul, VisionJeu j){
        ArrayList<Carte> deckMain = j.getDeckMain();
        boolean[] possibilites = new boolean[deckMain.size()];

        HashMap<Ressource, Integer> ressourcesJ = calculRessources(j.getVoisinDroiteDeckPlateau());

        for (int i = 0; i < deckMain.size(); i++){
            if(posSeul[i] == false){
                HashMap<Ressource, Integer> prixCarte = calculPrixCarte(deckMain.get(i));
                boolean achetable = true;
                for(Ressource r : Ressource.values())
                    if(resSeul.get(r) + ressourcesJ.get(r) - prixCarte.get(r) < 0){
                        achetable = false;
                        break;
                    }
                possibilites[i] = achetable;
            }
        }
        return possibilites;
    }

    protected final boolean[] getPossibilitesSeul(VisionJeu j){
        ArrayList<Carte> deckMain = j.getDeckMain();
        boolean[] possibilites = new boolean[deckMain.size()];

        resSeul = calculRessources(j.getDeckPlateau());

        for (int i = 0; i < deckMain.size(); i++){

            HashMap<Ressource, Integer> prixCarte = calculPrixCarte(deckMain.get(i));
            boolean achetable = true;

            for(Ressource r : Ressource.values())
                if(resSeul.get(r) - prixCarte.get(r) < 0){
                    achetable = false;
                    break;
                }
            possibilites[i] = achetable;
        }
        return possibilites;
    }

    private final HashMap<Ressource, Integer> calculPrixCarte(Carte c){
        HashMap<Ressource, Integer> prixCartes = new HashMap<Ressource, Integer>();
        for(Ressource r : Ressource.values())
            prixCartes.put(r, 0);

        for(Ressource r : c.getCoutRessources())
            prixCartes.put(r, prixCartes.get(r)+1);
    
        return prixCartes;
    }

	private final HashMap<Ressource, Integer> calculRessources(ArrayList<Carte> deck){
        HashMap<Ressource, Integer> res = new HashMap<Ressource, Integer>();

        for(Ressource r : Ressource.values())
            res.put(r, 0);

        for(Carte c : deck)
            for(Ressource r : c.getRessources())
                res.put(r, res.get(r)+1);
        
        return res;
    }

	@Override
	public String toString(){ return "Strategie"; }
}