package commun;

import static commun.Couleur.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.lang.Math;

/**
 * @authors Yannick Cardini, Benoît Montorsi, Rémi Felin, Pierre Saunders, Thomas Gauci
 */
public class Joueur {

    private int id = -1, piece = 5, jetonsDefaite = 0;
    private Merveille plateau;
    private ArrayList<Carte> deckPlateau = new ArrayList<Carte>();
    private ArrayList<Carte> deckMain = new ArrayList<Carte>();
    private int[] jetonsVictoire = new int[3];

    /**
     * @param id id
     */
    public Joueur(int id) {
        this.id = id;
    }

    /* AJOUT FONCTIONNALITE 'CONSTRUIRE MERVEILLE' */

    /**
     * @author Rémi Felin
     * @param indice l'indice de l'étape a construire
     * @param index l'indice de la carte qui sert à construire
     * @return l'indice de l'étape construite
     */
    public final int construireEtape(int indice, int index){
        deckMain.remove(index);
        plateau.getEtape(indice).construire();
        return indice;
    }

    //Ancienne manière
    /**
     * @author Pierre Saunders
     * @deprecated
     * @param l'indice de l'étape a construire
     * @return l'indice de l'étape construite
    */
    /*public final int construireMerveille(int index){
        deckMain.remove(index);
        return plateau.construireEtape();
    }*/

    /*---------------------------------------------*/

    //GETTER
    /**
     * @author Pierre Saunders
     * @return les jetons victoire
     */
    public final int[] getJetonsVictoire(){ return jetonsVictoire; }
    /**
     * @author Pierre Saunders
     * @return les jetons défaite
     */
    public final int getJetonsDefaite(){ return jetonsDefaite; }
    /**
     * @return le plateau/merveille
     */
    public final Merveille getPlateau(){ return plateau; }
    /**
     * @return le deck des cartes posés
     */
    public final ArrayList<Carte> getDeckPlateau(){ return deckPlateau; }
    /**
     * @return le deck en main
     */
    public final ArrayList<Carte> getDeckMain(){ return deckMain; }
    /**
     * @return les pieces
     */
    public final int getPiece(){ return piece; }
    /**
     * @return l'id
     */
    public final int getId() { return id; }
    /**
     * @author Pierre Saunders
     * @param age l'indice age du jeton a ajouter
     */
    public final void ajouterJetonVictoire(int age){ jetonsVictoire[age-1]++; }
    /**
     * @author Pierre Saunders
     */
    public final void ajouterJetonDefaite(){ jetonsDefaite++; }
    /**
     * @author Pierre Saunders
     * @see #compareConfiltsJoueur(Joueur j1, Joueur j2) dans Jeu
     * @return the la force militaire totale
     */
    public final int getForceMilitaire(){
        int r = 0;

        for(Carte c : deckPlateau)
            r += c.getPuissanceMilitaire();

        return r;
    }    
    /**
     * @param c le deck en main
     */
    public final void setDeckMain(ArrayList<Carte> c){ deckMain = c; }
    /**
     * @param m la merveille
     */
    public final void setPlateau(Merveille m){ plateau = m; }
    /**
     * @author Pierre Saunders
     * @param index l'indice de la carte à défausser
     * @return la carte défausser
     */
    public final Carte defausserCarte(int index){
        Carte c = deckMain.remove(index);
        piece += 3;
        return c;
    }
    /**
     * @author Pierre Saunders
     * @param index id
     * @return la carte posé
     */
    public final Carte poserCarte(int index){
        Carte c = deckMain.remove(index);
        deckPlateau.add(c);
        return c;
    }
    /**
     * @return le score total
     * @author Rémi Felin, Pierre Saunders, Thomas Gauci
     */
    public final int getScore(VisionJeu vGauche, VisionJeu vDroite) {

        int score = 0;

        //1. Confilts militaire
        for(int i=1; i<jetonsVictoire.length+1; i++)
            score += jetonsVictoire[i-1] * (i * 2 - 1);

        score -= jetonsDefaite;

        //2. Contenu du trésor
        //chaque lot de 3 piece => + 1 de score
        score += (piece - piece % 3)/3;
        
        //3. Merveilles
        for(Etape e : plateau.getEtapes())
            if(e.getEtat()){
                score += e.getPointVictoire();
            } else break;

        //4. Bâtiments civils
        for(Carte c : deckPlateau)
            score += c.getPointVictoire();

        //5. Bâtiments scientifiques
        // Famille de symboles identiques
        HashMap<SymboleScientifique, Integer> scientifique = new HashMap<SymboleScientifique, Integer>();
        for(SymboleScientifique s : SymboleScientifique.values())
            scientifique.put(s, 0);

        for(Carte c : deckPlateau){
            SymboleScientifique symb = c.getSymboleScientifique();
            if(symb != null)
                scientifique.put(symb, scientifique.get(symb)+1);
        }

        //Groupe de 3 symboles différents
        int groupe = 99;
        for(SymboleScientifique s : SymboleScientifique.values()){
            int val = scientifique.get(s);
            score += Math.pow(val, 2);
            groupe = Math.min(groupe, val);
        }
        score += 7 * groupe;

        //6. Baîments commerciaux
        //Voir score bonus fin de partie

        //7. Guildes
        for(Carte c : deckPlateau){
            EffetGuilde eg = c.getEffetGuilde();
            Couleur cl = BLANC;
            int val = 1;
            if(eg != null)
                switch(eg){
                    case ESPIONS:
                        if(cl == BLANC)
                            cl = ROUGE;
                    case MAGISTRATS:
                        if(cl == BLANC)
                            cl = BLEU;
                    case TRAVAILLEURS:
                        if(cl == BLANC)
                            cl = MARRON;
                    case ARTISANS:
                        if(cl == BLANC){
                            cl = GRIS;
                            val = 2;
                        }
                    case COMMERCANTS:
                        if(cl == BLANC)
                            cl = JAUNE;
                    case PHILOSOPHES:
                        if(cl == BLANC)
                            cl = VERT;

                        for(Carte cg : vGauche.getDeckPlateau())
                            if(cg.getCouleur() == cl)
                                score += val;

                        for(Carte cd : vDroite.getDeckPlateau())
                            if(cd.getCouleur() == cl)
                                score += val;
                    break;

                    case BATISSEURS:
                        for(Etape e : plateau.getEtapes())
                            if(e.getEtat())
                                score++;
                            else
                                break;
                        for(Etape e : vGauche.getPlateau().getEtapes())
                            if(e.getEtat())
                                score++;
                            else
                                break;
                        for(Etape e : vDroite.getPlateau().getEtapes())
                            if(e.getEtat())
                                score++;
                            else
                                break;
                    break;
                    
                    case ARMATEURS:
                        score++; //La carte se compte elle-même
                        for(Carte cp : deckPlateau){
                            Couleur clp = cp.getCouleur();
                            if(clp == MARRON || clp == GRIS || clp == VIOLET)
                                score++;
                        }
                    break;

                    case STRATEGES:
                        score += vGauche.getJetonsDefaite();
                        score += vDroite.getJetonsDefaite();
                    break;
                }
        }

        return score < 0 ? 0 : score;
    }
    /**
     * @return le score bonus lors de la fin de partie (voir régles page 6, Batîments commerciaux)
     * @author Pierre Saunders
     */
    public final int getScoreBonusFinPartie(VisionJeu vGauche, VisionJeu vDroite) {
        
        int score = 0;

        //case SCIENTIFIQUES:
            //TODO ajouter EventConnection choisir SymboleScientifique
            SymboleScientifique.choisirSymbole();
        //break;

        //5. Bâtiments scientifiques
        // Famille de symboles identiques
        HashMap<SymboleScientifique, Integer> scientifique = new HashMap<SymboleScientifique, Integer>();
        for(SymboleScientifique s : SymboleScientifique.values())
            scientifique.put(s, 0);

        for(Carte c : deckPlateau){
            SymboleScientifique symb = c.getSymboleScientifique();
            if(symb != null)
                scientifique.put(symb, scientifique.get(symb)+1);
        }

        //Groupe de 3 symboles différents
        int groupe = 99;
        for(SymboleScientifique s : SymboleScientifique.values()){
            int val = scientifique.get(s);
            score += Math.pow(val, 2);
            groupe = Math.min(groupe, val);
        }
        score += 7 * groupe;
        
        //6. Bâtiments commerciaux
        //Voir fin de partie

        return score;
    }
    /**
     * @author Benoît Montorsi
     * @param cout cout à payer
     * @return la cout payer
     */
    public final int payer(int cout){
        piece -= cout;
        return cout;
    }
    /**
     * @author Benoît Montorsi
     * @param montant total de piece à recevoir
     */
    public final void recevoirPaiement(int montant){ piece += montant; }
    /**
     * @return description de l'objet
     */
    
    @Override
    public final String toString(){ return "Joueur "+id; }
}