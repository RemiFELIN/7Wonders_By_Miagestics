package client.strategie;

import moteur.Carte;
import moteur.Couleur;
import moteur.Jeu;
import moteur.VisionJeu;
import moteur.action.AcheterRessource;
import moteur.action.Action;
import moteur.action.PoserCarte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

@SuppressWarnings("ALL")
public class StratScientifique extends Strategie {

    ArrayList<HashMap<String, Integer>> ressourcesJ=null;


    public Action getAction(int idJoueur, ArrayList<Carte> deck) {

        int carteN = 0;
        boolean aTrouve=false;

        for (int i = 0; i < deck.size(); i++) {
            Carte c = deck.get(i);
           Couleur cl = c.getCouleur();
            if (cl==Couleur.VERT) {
                carteN = i;
                aTrouve=true;
            }
        }

        if(carteN==0 && aTrouve==false) carteN=new Random().nextInt(deck.size());

        return new PoserCarte(idJoueur, carteN);
    }

    @Override
    public Action getAction(VisionJeu j) {
        int[] possibiliteSeul=getPossibilitesSeul(j);
        int[] possibilitesGauche=getPossibilitesGauche(j);
        int[] possibilitesDroite=getPossibilitesDroite(j);


        boolean trouveCarteScientifique=false;
        ArrayList<Carte> deck= j.getDeckMain();

        int carteN = 0, coutRes = 10, joueurAQuiPiocher=0;
        for (int i = 0; i < deck.size(); i++){

            if(Jeu.indexOf(possibiliteSeul,i)!=-1)
            {
                if(deck.get(i).getCouleur()==Couleur.VERT && deck.get(i).getCoutRessources().size()<coutRes){
                    carteN = i;
                    trouveCarteScientifique=true;
                }
            }

        }

        for (int i = 0; i < deck.size(); i++){

            if(Jeu.indexOf(possibilitesGauche,i)!=-1)
            {
                if(deck.get(i).getCouleur()==Couleur.VERT && deck.get(i).getCoutRessources().size()<coutRes){
                    carteN = i;
                    joueurAQuiPiocher=-1;
                    trouveCarteScientifique=true;
                }
            }

        }

        for (int i = 0; i < deck.size(); i++){

            if(Jeu.indexOf(possibilitesDroite,i)!=-1)
            {
                if(deck.get(i).getCouleur()==Couleur.VERT && deck.get(i).getCoutRessources().size()<coutRes){
                    carteN = i;
                    joueurAQuiPiocher=1;
                    trouveCarteScientifique=true;
                }
            }

        }

        if(joueurAQuiPiocher!=0) return new AcheterRessource(j.getId(),joueurAQuiPiocher, carteN);

        if(carteN==0 && trouveCarteScientifique) carteN=new Random().nextInt(deck.size());

        return new PoserCarte(j.getId(), carteN);
    }

    @Override
    int[] getPossibilitesGauche(VisionJeu j) {
        int[] possibilites=new int[j.getDeckMain().size()];
        ArrayList<Carte> deck= j.getVoisinGaucheDeckPlateau();
        ArrayList<HashMap<String, Integer>> ressourcesGauche=j.calculRessources(deck);
        int index = 0;
        for (int i = 0; i < j.getDeckMain().size(); i++){
            Carte c = j.getDeckMain().get(i);

            if(ressourcesJ.get(0).get("Bois")<c.getCoutBois() && (ressourcesJ.get(0).get("Bois")+ressourcesGauche.get(0).get("Bois"))>=c.getCoutBois())  possibilites[index]=++i;
            if(ressourcesJ.get(1).get("Minerais")<c.getCoutMinerai() && (ressourcesJ.get(1).get("Minerais")+ressourcesGauche.get(1).get("Minerais"))>=c.getCoutMinerai())  possibilites[index]=++i;
            if(ressourcesJ.get(2).get("Argile")<c.getCoutArgile() && (ressourcesJ.get(2).get("Argile")+ressourcesGauche.get(2).get("Argile"))>=c.getCoutArgile())  possibilites[index]=++i;
            if(ressourcesJ.get(3).get("Pierre")<c.getCoutPierre() && (ressourcesJ.get(3).get("Pierre")+ressourcesGauche.get(3).get("Pierre"))>=c.getCoutPierre())  possibilites[index]=++i;
            if(ressourcesJ.get(4).get("Verre")<c.getCoutVerre() && (ressourcesJ.get(4).get("Verre")+ressourcesGauche.get(4).get("Verre"))>=c.getCoutVerre())  possibilites[index]=++i;
            if(ressourcesJ.get(5).get("Textile")<c.getCoutTextile() && (ressourcesJ.get(5).get("Textile")+ressourcesGauche.get(5).get("Textile"))>=c.getCoutTextile())  possibilites[index]=++i;
            if(ressourcesJ.get(6).get("Papyrus")<c.getCoutPapyrus() && (ressourcesJ.get(6).get("Papyrus")+ressourcesGauche.get(6).get("Papyrus"))>=c.getCoutPapyrus())  possibilites[index]=++i;
        }
        return possibilites;
    }

    @Override
    int[] getPossibilitesDroite(VisionJeu j) {
        int[] possibilites=new int[j.getDeckMain().size()];
        ArrayList<Carte> deck= j.getVoisinDroiteDeckPlateau();
        ArrayList<HashMap<String, Integer>> ressourcesDroite=j.calculRessources(deck);
        int index = 0;
        for (int i = 0; i < j.getDeckMain().size(); i++){
            Carte c = j.getDeckMain().get(i);

            if(ressourcesJ.get(0).get("Bois")<c.getCoutBois() && (ressourcesJ.get(0).get("Bois")+ressourcesDroite.get(0).get("Bois"))>=c.getCoutBois())  possibilites[index]=++i;
            if(ressourcesJ.get(1).get("Minerais")<c.getCoutMinerai() && (ressourcesJ.get(1).get("Minerais")+ressourcesDroite.get(1).get("Minerais"))>=c.getCoutMinerai())  possibilites[index]=++i;
            if(ressourcesJ.get(2).get("Argile")<c.getCoutArgile() && (ressourcesJ.get(2).get("Argile")+ressourcesDroite.get(2).get("Argile"))>=c.getCoutArgile())  possibilites[index]=++i;
            if(ressourcesJ.get(3).get("Pierre")<c.getCoutPierre() && (ressourcesJ.get(3).get("Pierre")+ressourcesDroite.get(3).get("Pierre"))>=c.getCoutPierre())  possibilites[index]=++i;
            if(ressourcesJ.get(4).get("Verre")<c.getCoutVerre() && (ressourcesJ.get(4).get("Verre")+ressourcesDroite.get(4).get("Verre"))>=c.getCoutVerre())  possibilites[index]=++i;
            if(ressourcesJ.get(5).get("Textile")<c.getCoutTextile() && (ressourcesJ.get(5).get("Textile")+ressourcesDroite.get(5).get("Textile"))>=c.getCoutTextile())  possibilites[index]=++i;
            if(ressourcesJ.get(6).get("Papyrus")<c.getCoutPapyrus() && (ressourcesJ.get(6).get("Papyrus")+ressourcesDroite.get(6).get("Papyrus"))>=c.getCoutPapyrus())  possibilites[index]=++i;
        }
        return possibilites;
    }

    @Override
    int[] getPossibilitesSeul(VisionJeu j) {
        int[] possibilites=new int[j.getDeckMain().size()];
        ArrayList<Carte> deck= j.getDeckPlateau();
        ressourcesJ=j.calculRessources(deck);

        int index = 0;
        for (int i = 0; i < j.getDeckMain().size(); i++){
            Carte c = j.getDeckMain().get(i);

            if(ressourcesJ.get(0).get("Bois")<c.getCoutBois())  possibilites[index]=++i;
            if(ressourcesJ.get(1).get("Minerais")<c.getCoutMinerai())  possibilites[index]=++i;
            if(ressourcesJ.get(2).get("Argile")<c.getCoutArgile())  possibilites[index]=++i;
            if(ressourcesJ.get(3).get("Pierre")<c.getCoutPierre())  possibilites[index]=++i;
            if(ressourcesJ.get(4).get("Verre")<c.getCoutVerre())  possibilites[index]=++i;
            if(ressourcesJ.get(5).get("Textile")<c.getCoutTextile())  possibilites[index]=++i;
            if(ressourcesJ.get(6).get("Papyrus")<c.getCoutPapyrus())  possibilites[index]=++i;
        }
        return possibilites;
    }

    @Override
    public String toString(){
        return super.toString() + " scientifique";
    }
}
