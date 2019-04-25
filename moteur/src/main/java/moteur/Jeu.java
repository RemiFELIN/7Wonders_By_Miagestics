package moteur;

import commun.Action;
import commun.Ressource;
import commun.Couleur;
import commun.Etape;
import commun.Carte;
import commun.Merveille;
import commun.Joueur;
import commun.VisionJeu;
import commun.EffetCommercial;
import static commun.ConsoleLogger.*;
import static commun.Couleur.*;
import static commun.EffetCommercial.*;

import java.net.BindException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.HashMap;

/**
 * @author Yannick Cardini, Benoît Montorsi, Rémi Felin, Pierre Saunders, Gauci Thomas
 */
public class Jeu {

    private ArrayList<Joueur> mesJoueurs;
    private final ArrayList<ArrayList<Carte>> tabDeck = new ArrayList<ArrayList<Carte>>(3);
    private int age = 1, tour = 1;

    public static final void main(String args[]) throws BindException {
        if (args.length == 2) {
            String adresse = args[0];
            int port = Integer.parseInt(args[1]);
            new wrapperJeu(adresse, port);
        }
    }

    /**
     * Constructeur qui permet d'initialiser le début de partie
     * @param nbJoueurs nombre de joueurs dans la partie
     */
    public Jeu(int nbJoueurs) {
        mesJoueurs = new ArrayList<Joueur>(nbJoueurs);
        for (int i = 0; i < nbJoueurs; i++)
            mesJoueurs.add(new Joueur(i));

        distributionPlateau();
        initCartes();
    }

    // GETTER
    /**
     * @author Pierre Saunders
     * @return le tour en cours
     */
    public final int getTour() {
        return tour;
    }

    /**
     * @return l'âge en cours
     */
    public final int getAge() {
        return age;
    }

    /**
     * @return tous les deck de la fosse
     */
    public final ArrayList<ArrayList<Carte>> getDecks() {
        return tabDeck;
    }

    /**
     * @return la taille du deck de l'âge en cours
     */
    public final int getTailleDeck() {
        return tabDeck.get(1).size();
    }

    /**
     * @return le deck de l'âge en cours
     */
    public final ArrayList<Carte> getDeckPrincipal() {
        return tabDeck.get(this.age - 1);
    }

    /**
     * @return les joueurs
     */
    public final ArrayList<Joueur> getJoueurs() {
        return mesJoueurs;
    }

    /**
     * Permet de passer au tour suivant
     * @author Pierre Saunders
     */
    public final void tourSuivant() {
        tour++;
    }

    /**
     * Permet d'initialiser les cartes dans les decks
     * @author Thomas Gauci
     */
    public final void initCartes() {
        for (int j = 0; j < 3; j++) {
            ArrayList<Carte> tabCarte = Carte.getDeck(j + 1, mesJoueurs.size());
            ArrayList<Carte> deckGuildes = new ArrayList<Carte>();

            if (j == 2) {
                deckGuildes = Carte.getDeckGuildes(mesJoueurs.size());
                tabCarte.addAll(deckGuildes);
            }
            Collections.shuffle(tabCarte);
            tabDeck.add(tabCarte);
        }
    }

    /**
     * Permet de faire tourner les cartes (horaire ou anti-horaire selon l'âge) parmi les joueurs
     * @author Pierre Saunders
     */
    public final void roulementCarte() {
        if (age % 2 == 1) { // Pour age 1 et 3 => rotation horaire
            ArrayList<Carte> first = mesJoueurs.get(0).getDeckMain();
            for (int i = 0; i < mesJoueurs.size() - 1; i++)
                mesJoueurs.get(i).setDeckMain(mesJoueurs.get(i + 1).getDeckMain());

            mesJoueurs.get(mesJoueurs.size() - 1).setDeckMain(first);
        } else { // Pour age 2 => rotation anti-hoiraire
            int size = mesJoueurs.size() - 1;
            ArrayList<Carte> last = mesJoueurs.get(size).getDeckMain();
            for (int i = size; i > 0; i--)
                mesJoueurs.get(i).setDeckMain(mesJoueurs.get(i - 1).getDeckMain());

            mesJoueurs.get(0).setDeckMain(last);
        }
    }

    /**
     * Distribue les cartes des deck de l'âge en cours aux joueurs
     * @author Thomas Gauci
     */
    public final void distributionCarte() {
        int nbCartes = tabDeck.get(this.age - 1).size() / mesJoueurs.size();
        for (int i = 0; i < mesJoueurs.size(); i++) {
            ArrayList<Carte> carteJoueur = new ArrayList<Carte>();

            for (int j = 0; j < nbCartes; j++) {
                Carte c = tabDeck.get(this.age - 1).get(0);
                tabDeck.get(this.age - 1).remove(0);
                carteJoueur.add(c);
            }
            mesJoueurs.get(i).setDeckMain(carteJoueur);

        }
    }

    /**
     * Distribue les plateaux aux joueurs
     * @author Yannick Cardini
     */
    public final void distributionPlateau() {
        ArrayList<Merveille> plateaux = Merveille.getPlateaux();
        Merveille m;
        for (int i = 0; i < mesJoueurs.size(); i++) {
            Random random = new Random();
            m = plateaux.remove(random.nextInt(plateaux.size()));
            mesJoueurs.get(i).setPlateau(m);
        }
    }

    /**
     * Permet d'effecter une action donnée
     * @param ja action à effectuer
     * @return description de l'action effectuée
     */
    public final String jouerAction(Action ja) {
        Joueur j = mesJoueurs.get(ja.getIdJoueur());
        Carte c = j.getDeckMain().get(ja.getNumeroCarte());
        StringBuilder desc = new StringBuilder();
        boolean gratuit = false;
        Carte carteGratuite = c;
        for (int k = 0; k < (j.getDeckPlateau().size() - 1); k++) {
            if (j.getDeckPlateau().get(k).getBatimentSuivant() == c.getNom()) {
                carteGratuite = j.getDeckPlateau().get(k);
                gratuit = true;
            }
        }
        switch (ja.getType()) {

        case DefausserCarte:
            c = j.defausserCarte(ja.getNumeroCarte());
            desc.append("Le joueur " + ja.getIdJoueur() + " a défaussé la carte " + Couleur.consoleColor(c.getCouleur()) + c.getNom());
            tabDeck.get(this.age - 1).add(c);
            break;

        case AcheterRessource:
            if (!gratuit) {
                int idJoueurAPayer = (ja.getIdJoueur() + ja.getNumVoisin()) % mesJoueurs.size();

                if (idJoueurAPayer < 0)
                    idJoueurAPayer = mesJoueurs.size() - 1;

                int montantPaiement = 2;
                EffetCommercial ec = c.getEffetCommercial();
                if (ec != null && ((ec == ACHAT_MATIERE_DROITE && ja.getNumVoisin() == 1) || (ec == ACHAT_MATIERE_GAUCHE && ja.getNumVoisin() == -1) || (ec == ACHAT_PREMIERE))) {
                    montantPaiement = 1;
                    desc.append("Grâce à un effet commercial, le joueur " + ja.getIdJoueur() + " a acheté des ressources à coût réduits ");
                } else {
                    desc.append("Le joueur " + ja.getIdJoueur() + " a acheté des ressources");
                }
                desc.append(" au joueur " + idJoueurAPayer + "\n");
                mesJoueurs.get(idJoueurAPayer).recevoirPaiement(j.payer(montantPaiement));
            }

        case PoserCarte:
            c = j.poserCarte(ja.getNumeroCarte());
            desc.append("Le joueur " + ja.getIdJoueur() + " a posé la carte " + Couleur.consoleColor(c.getCouleur()) + c.getNom());
            if (gratuit) {
                desc.append(WHITE + " gratuitement grâce à la carte ");
                desc.append(Couleur.consoleColor(carteGratuite.getCouleur()) + carteGratuite.getNom());
            } else {
                ArrayList<Ressource> cr = c.getCoutRessources();
                if (cr.size() > 0) {
                    desc.append(WHITE + " qui coûte ");
                    HashMap<Ressource, Integer> hr = new HashMap<Ressource, Integer>();

                    for (Ressource r : cr)
                        hr.put(r, hr.get(r) == null ? 1 : hr.get(r) + 1);

                    for (Ressource r : hr.keySet())
                        desc.append(hr.get(r) + " de " + r.toString() + ", ");

                    desc.setLength(desc.length() - 2);
                }

                EffetCommercial ec = c.getEffetCommercial();
                int pieceBonus = 0;
                if (ec != null) {
                    desc.append("\n Grâce à un effet commercial, le joueur reçoit ");
                    switch (ec) {
                    case BONUS_OR:
                        j.recevoirPaiement(5);
                        desc.append("5 pièces d'or\n");
                        break;

                    case OR_CARTE_MARRON:
                        for (Carte cj : j.getDeckPlateau())
                            if (cj.getCouleur() == MARRON)
                                pieceBonus++;

                        for (Carte cj : mesJoueurs.get((ja.getIdJoueur() + 1) % mesJoueurs.size()).getDeckPlateau())
                            if (cj.getCouleur() == MARRON)
                                pieceBonus++;

                        for (Carte cj : mesJoueurs.get(ja.getIdJoueur() - 1 < 0 ? mesJoueurs.size() - 1 : ja.getIdJoueur() - 1).getDeckPlateau())
                            if (cj.getCouleur() == MARRON)
                                pieceBonus++;

                        j.recevoirPaiement(pieceBonus);
                        desc.append("1 pièce d'or par carte marron dans son deck et celui de ses voisins direct pour un total de +" + pieceBonus + "\n");
                        break;

                    case OR_CARTE_GRIS:
                        for (Carte cj : j.getDeckPlateau())
                            if (cj.getCouleur() == GRIS)
                                pieceBonus += 2;

                        for (Carte cj : mesJoueurs.get((ja.getIdJoueur() + 1) % mesJoueurs.size()).getDeckPlateau())
                            if (cj.getCouleur() == GRIS)
                                pieceBonus += 2;

                        for (Carte cj : mesJoueurs.get(ja.getIdJoueur() - 1 < 0 ? mesJoueurs.size() - 1 : ja.getIdJoueur() - 1).getDeckPlateau())
                            if (cj.getCouleur() == GRIS)
                                pieceBonus += 2;

                        j.recevoirPaiement(pieceBonus);
                        desc.append("2 pièces d'or par carte grise dans son deck et celui de ses voisins direct pour un total de +" + pieceBonus + "\n");
                        break;

                    case BONUS_ETAPE_MERVEILLE:
                        for (Etape e : j.getPlateau().getEtapes())
                            if (e.getEtat())
                                pieceBonus += 3;

                        j.recevoirPaiement(pieceBonus);
                        desc.append("3 pièces d'or par étapes construite de sa merveille pour un total de +" + pieceBonus + "\n");
                        break;

                    case BONUS_CARTE_MARRON:
                        for (Carte cj : j.getDeckPlateau())
                            if (cj.getCouleur() == MARRON)
                                pieceBonus++;

                        j.recevoirPaiement(pieceBonus);
                        desc.append("1 pièce d'or par carte marron dans son deck pour un total de +" + pieceBonus + "\n");
                        break;

                    case BONUS_CARTE_GRIS:
                        for (Carte cj : j.getDeckPlateau())
                            if (cj.getCouleur() == GRIS)
                                pieceBonus += 2;

                        j.recevoirPaiement(pieceBonus);
                        desc.append("2 pièce d'or par carte grise dans son deck pour un total de +" + pieceBonus + "\n");
                        break;

                    case BONUS_CARTE_JAUNE:
                        for (Carte cj : j.getDeckPlateau())
                            if (cj.getCouleur() == JAUNE)
                                pieceBonus++;

                        j.recevoirPaiement(pieceBonus);
                        desc.append("1 pièce d'or par carte jaune dans son deck pour un total de +" + pieceBonus + "\n");
                        break;
                    }
                }
            }
            break;

        case ConstruireMerveille:
            if (age >= 2) {
                if (j.getPlateau().getEtape(age - 1).getEtat() == true)
                    j.getPlateau().getEtape(age).construire();
            } else
                j.getPlateau().getEtape(age).construire();
            int etape = j.construireEtape(age, ja.getNumeroCarte());
            desc.append("Le joueur " + ja.getIdJoueur() + " a construit l'étape " + etape + " de sa merveille " + j.getPlateau().getNom());
            break;
        }
        return desc.toString();
    }

    /**
     * Test si l'âge en cours est terminé
     * @author Pierre Saunders, Thomas Gauci
     * @return vrai si âge terminé sinon faux
     */
    public final boolean finAge() {
        if (tour > 7)
            return true;

        Boolean isFin = true;
        for (Joueur j : mesJoueurs) {
            if (j.getDeckMain().size() != 1) {
                isFin = false;
                break;
            }
        }
        return isFin;
    }

    /**
     * Permet d'appliquer les confilts miltaires entre 2 joueurs lors de la fin d'un âge
     * @author Pierre Saunders
     * @param j1 joueur 1
     * @param j2 joueur 2
     */
    private final void compareConfiltsJoueur(Joueur j1, Joueur j2) {
        int r1 = j1.getForceMilitaire();
        int r2 = j2.getForceMilitaire();
        if (r1 != r2) {
            if (r1 > r2) {
                j1.ajouterJetonVictoire(age);
                j2.ajouterJetonDefaite();
                // r1 <= r2
            } else {
                j2.ajouterJetonVictoire(age);
                j1.ajouterJetonDefaite();
            }
        }
    }

    /**
     * Permet de passer à l'âge suivant
     * @author Pierre Saunders
     */
    public final void ageSuivant() {

        // Calcul confilts militaire
        for (byte i = 0; i < mesJoueurs.size(); i++)
            compareConfiltsJoueur(mesJoueurs.get(i), mesJoueurs.get((i + 1) % mesJoueurs.size()));

        age++;
        tour = 1; // reset tour
    }

    /**
     * Test si le jeu est terminée
     * @return vrai si le jeu est terminée sinon faux
     * @author Thomas Gauci
     */
    public final boolean finJeu() {
        return age >= 3;
    }

    /**
     * Permet d'obtenir le classement final des joueurs
     * @author Pierre Saunders
     * @return liste classée des joueurs
     */
    public final ArrayList<int[]> getClassement() {
        ArrayList<Joueur> classé = new ArrayList<Joueur>(mesJoueurs);

        ArrayList<int[]> classement = new ArrayList<int[]>(classé.size());

        for (byte i = 0; i < classé.size(); i++) {
            Joueur j = classé.get(i);
            int score = j.getScoreFinPartie(new VisionJeu(classé.get(i - 1 < 0 ? classé.size() - 1 : i - 1)), new VisionJeu(classé.get((i + 1) % classé.size())));
            int id = j.getId();

            classement.add(new int[] { id, score });
        }

        classement.sort(new Comparator<int[]>() {
            public final int compare(int[] j1, int[] j2) {
                // j2 > j1 ? j2,j1 : j1,j2
                return Integer.compare(j2[1], j1[1]);
            }
        });
        return classement;
    }

    /**
     * Permet d'obtenir les VisionJeu de tous les joueurs
     * @author Pierre Saunders
     * @return les VisionJeu
     */
    public final ArrayList<VisionJeu> getVisionsJeu() {
        ArrayList<VisionJeu> v = new ArrayList<VisionJeu>(mesJoueurs.size());

        for (Joueur j : mesJoueurs)
            v.add(new VisionJeu(j));

        for (int i = 0; i < v.size(); i++) {
            v.get(i).setVoisinGauche(v.get(i - 1 < 0 ? v.size() - 1 : i - 1));
            v.get(i).setVoisinDroite(v.get((i + 1) % v.size()));
        }

        return v;
    }

}