package client.strategie;

import commun.VisionJeu;
import commun.Carte;
import commun.Ressource;
import commun.Action;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @authors Benoît Montorsi, Pierre Saunders
 */
public abstract class Strategie {
    
    private HashMap<Ressource, Integer> resSeul;

    /**
     * Récupère l'action à effectué selon la classe héritée
     * @see getAction de toutes les classe héritée de Strategie
     * @param la vision de jeu actuelle
     * @return l'action à effectuée determinée par une classe héritée
     */
	public final Action getAction(VisionJeu j){
        boolean[] posSeul = getPossibilitesSeul(j);
        boolean[] posGauche = getPossibilitesGauche(j, posSeul, resSeul);
        boolean[] posDroite = getPossibilitesDroite(j, posSeul, resSeul);
        return getAction(j, posSeul, posGauche, posDroite);
    }
    /**
     * Récupère l'action à effectué selon la classe héritée
     * @param la vision de jeu actuelle
     * @param les possibilité d'achat soi-même
     * @param les possibilité d'achat avec le voisin de gauche
     * @param les possibilité d'achat  avec le voisin de droite
     * @return l'action à effectué determinée
     */
    protected abstract Action getAction(VisionJeu j, boolean[] posSeul, boolean[] posGauche, boolean[] posDroite);
    /**
     * Détermine les possibilitées d'achat avec le voisin de gauche
     * @param la vision de jeu actuelle
     * @param les possibilité d'achat soi-même
     * @param les ressources disponibles
     * @return les possibilitées
     */
    protected final boolean[] getPossibilitesGauche(VisionJeu j, boolean[] posSeul, HashMap<Ressource, Integer> resSeul){
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
    /**
     * Détermine les possibilitées d'achat avec le voisin de droite
     * @param la vision de jeu actuelle
     * @param les possibilité d'achat soi-même
     * @param les ressources disponibles
     * @return les possibilitées
     */
    protected final boolean[] getPossibilitesDroite(VisionJeu j, boolean[] posSeul, HashMap<Ressource, Integer> resSeul){
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
    /**
     * Détermine les possibilité d'achat par soi-même
     * @param la vision de jeu actuelle
     * @return  les possibilitées
     */
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
    /**
     * Détermine le prix total d'une carte
     * @param une carte
     * @return le prix par ressource
     */
    private final HashMap<Ressource, Integer> calculPrixCarte(Carte c){
        HashMap<Ressource, Integer> prixCartes = new HashMap<Ressource, Integer>();
        for(Ressource r : Ressource.values())
            prixCartes.put(r, 0);

        for(Ressource r : c.getCoutRessources())
            prixCartes.put(r, prixCartes.get(r)+1);
    
        return prixCartes;
    }
    /**
     * Détermine les ressources disponibles dans un deck
     * @param un deck
     * @return nombre par ressources 
     */
	private final HashMap<Ressource, Integer> calculRessources(ArrayList<Carte> deck){
        HashMap<Ressource, Integer> res = new HashMap<Ressource, Integer>();

        for(Ressource r : Ressource.values())
            res.put(r, 0);

        for(Carte c : deck)
            for(Ressource r : c.getRessources())
                res.put(r, res.get(r)+1);
        
        return res;
    }
    /**
     * Décris la stratégie actuelle
     * @see toutes les classe héritée de Strategie
     * @return description
     */
	@Override
	public String toString(){ return "Strategie"; }
}