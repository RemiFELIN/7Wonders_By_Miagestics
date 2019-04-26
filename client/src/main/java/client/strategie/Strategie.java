package client.strategie;

import commun.VisionJeu;
import commun.Carte;
import commun.Merveille;
import commun.Etape;
import commun.Ressource;
import commun.Action;

import java.util.ArrayList;
import java.util.HashMap;

import static commun.ConsoleLogger.*;

/**
 * Permet au Client de faire l'action la plus adéquate à sa situation
 * @authors Benoît Montorsi, Pierre Saunders
 */
public abstract class Strategie {

    private HashMap<Ressource, Integer> resSeul;
    private boolean log;

    /**
     * Constructeur d'une stratégie
     * @see Strategie les classes hérités / filles
     * @param log si on affiche à la console les informations d'actions
     */
    public Strategie(boolean log) {
        this.log = log;
    }

    /**
     * Récupère l'action à effectuer selon la classe héritée
     * @see #getAction(VisionJeu) de toutes les classe héritées de Strategie
     * @param j vision de jeu actuelle
     * @return l'action à effectuer determinée par une classe héritée
     */
    public final Action getAction(VisionJeu j) {
        boolean[] posSeul = getPossibilitesSeul(j);
        if (log) {
            int jetonVic[] = j.getJetonsVictoire();
            StringBuilder textClas = new StringBuilder();
            textClas.append(PURPLE_BOLD_BRIGHT + "Joueur " + j.getId() + " : \n");
            textClas.append("Pieces : " + j.getPiece() + "\n");
            Merveille plateau = j.getPlateau();
            int nbEtapes = 0;
            for (Etape e : plateau.getEtapes())
                if (e.getEtat())
                    nbEtapes++;
                else
                    break;
            textClas.append("Nom de la merveille : " + plateau.getNom() + "\n");
            textClas.append("Nombre d'étapes construite : " + nbEtapes + "\n");
            textClas.append("Jetons victoire : ");
            for (byte i = 1; i <= 3; i++)
                textClas.append("Age " + i + " : " + jetonVic[i - 1] + " | ");
            textClas.setLength(textClas.length() - 3);

            textClas.append("\nJetons défaite : " + j.getJetonsDefaite() + "\n");
            textClas.append("Ressources  : \n");
            for (Ressource r : resSeul.keySet())
                textClas.append(r.toString() + " : " + resSeul.get(r) + " | ");
            textClas.setLength(textClas.length() - 3);

            textClas.append("\n-----------------------------------------------------------------------------------------\n");
            log(textClas.toString());
        }

        boolean[] posGauche = getPossibilitesGauche(j, posSeul, resSeul);
        boolean[] posDroite = getPossibilitesDroite(j, posSeul, resSeul);
        return getAction(j, posSeul, posGauche, posDroite);
    }

    /**
     * Récupère l'action à effectuer selon la classe héritée
     * @param j vision de jeu actuelle
     * @param posSeul possibilité de jeu soi-même
     * @param posGauche possibilité d'achat avec le voisin de gauche
     * @param posDroite possibilité d'achat  avec le voisin de droite
     * @return l'action à effectué determinée
     */
    protected abstract Action getAction(VisionJeu j, boolean[] posSeul, boolean[] posGauche, boolean[] posDroite);

    /**
     * Détermine les possibilitées d'achat avec le voisin de gauche
     * @param j vision de jeu actuelle
     * @param posSeul possibilité de jeu soi-même
     * @param resSeul ressources disponibles
     * @return les possibilitées
     */
    protected final boolean[] getPossibilitesGauche(VisionJeu j, boolean[] posSeul, HashMap<Ressource, Integer> resSeul) {
        ArrayList<Carte> deckMain = j.getDeckMain();
        boolean[] possibilites = new boolean[deckMain.size()];

        HashMap<Ressource, Integer> ressourcesJ = calculRessources(j.getVoisinGaucheDeckPlateau());

        for (int i = 0; i < deckMain.size(); i++) {
            if (posSeul[i] == false) {
                HashMap<Ressource, Integer> prixCarte = calculPrixCarte(deckMain.get(i));
                boolean achetable = true;
                for (Ressource r : Ressource.values())
                    if (resSeul.get(r) + ressourcesJ.get(r) - prixCarte.get(r) < 0 && j.getPiece() < 2) {
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
     * @param j vision de jeu actuelle
     * @param posSeul possibilités de jeu soi-même
     * @param resSeul ressources disponibles
     * @return les possibilitées
     */
    protected final boolean[] getPossibilitesDroite(VisionJeu j, boolean[] posSeul, HashMap<Ressource, Integer> resSeul) {
        ArrayList<Carte> deckMain = j.getDeckMain();
        boolean[] possibilites = new boolean[deckMain.size()];

        HashMap<Ressource, Integer> ressourcesJ = calculRessources(j.getVoisinDroiteDeckPlateau());

        for (int i = 0; i < deckMain.size(); i++) {
            if (posSeul[i] == false) {
                HashMap<Ressource, Integer> prixCarte = calculPrixCarte(deckMain.get(i));
                boolean achetable = true;
                for (Ressource r : Ressource.values())
                    if (resSeul.get(r) + ressourcesJ.get(r) - prixCarte.get(r) < 0 && j.getPiece() < 2) {
                        achetable = false;
                        break;
                    }
                possibilites[i] = achetable;
            }
        }
        return possibilites;
    }

    /**
     * Détermine les possibilité de jeu par soi-même
     * @param j vision de jeu actuelle
     * @return  les possibilités
     */
    protected final boolean[] getPossibilitesSeul(VisionJeu j) {
        ArrayList<Carte> deckMain = j.getDeckMain();
        boolean[] possibilites = new boolean[deckMain.size()];
        resSeul = calculRessources(j.getDeckPlateau());

        for (int i = 0; i < deckMain.size(); i++) {

            for (int k = 0; k < j.getDeckPlateau().size(); k++) {
                if (j.getDeckPlateau().get(k).getBatimentSuivant() == deckMain.get(i).getNom())
                    possibilites[i] = true;
            }
            if (possibilites[i] == false) {
                HashMap<Ressource, Integer> prixCarte = calculPrixCarte(deckMain.get(i));
                boolean achetable = true;

                for (Ressource r : Ressource.values())
                    if (resSeul.get(r) - prixCarte.get(r) < 0) {
                        achetable = false;
                        break;

                    }
                possibilites[i] = achetable;
            }

        }
        return possibilites;
    }

    /**
     * Détermine le prix total d'une carte
     * @param c carte
     * @return le prix par ressource
     */
    private final HashMap<Ressource, Integer> calculPrixCarte(Carte c) {
        HashMap<Ressource, Integer> prixCartes = new HashMap<Ressource, Integer>();
        for (Ressource r : Ressource.values())
            prixCartes.put(r, 0);

        for (Ressource r : c.getCoutRessources())
            prixCartes.put(r, prixCartes.get(r) + 1);

        return prixCartes;
    }

    /**
     * Détermine les ressources disponibles dans un deck
     * @param deck deck
     * @return nombre par ressources 
     */
    private final HashMap<Ressource, Integer> calculRessources(ArrayList<Carte> deck) {
        HashMap<Ressource, Integer> res = new HashMap<Ressource, Integer>();

        for (Ressource r : Ressource.values())
            res.put(r, 0);

        for (Carte c : deck)
            for (Ressource r : c.getRessources())
                res.put(r, res.get(r) + 1);

        return res;
    }

    /**
     * Décrit la stratégie actuelle
     * @see Strategie toutes les classes héritées de Strategie
     * @return description
     */
    @Override
    public String toString() {
        return "Strategie";
    }
}