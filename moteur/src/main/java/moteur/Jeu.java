package moteur;

import moteur.action.Action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.HashMap;

public class Jeu {

    private final ArrayList<ArrayList<Carte>> tabDeck = new ArrayList<ArrayList<Carte>>(3);
    private int age = 1;
    private int tour = 1;

    private ArrayList<Joueur> mesJoueurs;

    public Jeu(int nbJoueurs) {
        mesJoueurs = new ArrayList<Joueur>(nbJoueurs);
        for (int i = 0; i < nbJoueurs; i++)
            mesJoueurs.add(new Joueur(i));

        distributionPlateau();
        initCartes();
    }
    
    //GETTER
    public final int getTour(){ return tour; }
    public final int getAge(){ return age; }
    public final ArrayList<ArrayList<Carte>> getDecks(){ return tabDeck; }
    public final int getTailleDeck(){ return tabDeck.get(1).size(); }
    public final ArrayList<Carte> getDeckPrincipal(){ return tabDeck.get(this.age-1); }
    public final ArrayList<Joueur> getJoueurs(){ return mesJoueurs; }

    public final void tourSuivant(){ tour++; }

    public final void initCartes() {
        for (int j = 0; j < 3; j++){
            ArrayList<Carte> tabCarte = Carte.getDeck(j+1,mesJoueurs.size());
            ArrayList<Carte> deckGuildes = new ArrayList<Carte>();

            if(j == 2){
                deckGuildes = Carte.getDeckGuildes(mesJoueurs.size());
                tabCarte.addAll(deckGuildes);
            }

            Collections.shuffle(tabCarte);
            tabDeck.add(tabCarte);
        }    
    }

    public final void roulementCarte(){
        if(age%2 == 1){ //Pour age 1 et 3
            ArrayList<Carte> first = mesJoueurs.get(0).getDeckMain();
            for(int i=0; i<mesJoueurs.size()-1; i++)
                mesJoueurs.get(i).setDeckMain(mesJoueurs.get(i+1).getDeckMain());
            
            mesJoueurs.get(mesJoueurs.size()-1).setDeckMain(first);
        } else { //Pour age 2
            int size = mesJoueurs.size()-1;
            ArrayList<Carte> last = mesJoueurs.get(size).getDeckMain();
            for(int i=size; i>0; i--)
                mesJoueurs.get(i).setDeckMain(mesJoueurs.get(i-1).getDeckMain());
            
            mesJoueurs.get(0).setDeckMain(last);
        }
    }

    public final void distributionCarte() {
        int nbCartes = tabDeck.get(this.age-1).size()/mesJoueurs.size();
        for (int i=0; i<mesJoueurs.size(); i++) {
            ArrayList<Carte> carteJoueur = new ArrayList<Carte>();

            for (int j = 0; j < nbCartes; j++) {
                Carte c = tabDeck.get(this.age-1).get(0);
                tabDeck.get(this.age-1).remove(0);
                carteJoueur.add(c);
            }
            mesJoueurs.get(i).setDeckMain(carteJoueur);

        }
    }

    public final void distributionPlateau() {
        ArrayList<Merveille> plateaux = Merveille.getPlateau();
        Merveille m;
        for (int i=0; i<mesJoueurs.size(); i++) {
            Random random = new Random();
            m = plateaux.remove(random.nextInt(plateaux.size()));
            mesJoueurs.get(i).setPlateau(m);
        }
    }

    public final String jouerAction(Action ja){
        Carte c;
        String desc = "";
        switch(ja.getType()){

            case "defaussercarte":
                c = mesJoueurs.get(ja.getIdJoueur()).defausserCarte(ja.getNumeroCarte());
                desc = "Le joueur "+ja.getIdJoueur()+" à défausser la carte "+Couleur.consoleColor(c.getCouleur())+c.getNom();
                tabDeck.get(this.age-1).add(c);
            break;

            case "acheterressource":
                int idJoueurAPayer=ja.getIdJoueur()+ja.getNumVoisin();

                if(idJoueurAPayer<0)
                {
                    idJoueurAPayer=mesJoueurs.size()-1;
                }
                else if(idJoueurAPayer>mesJoueurs.size()-1)
                {
                    idJoueurAPayer=0;
                }

                mesJoueurs.get(idJoueurAPayer).recevoirPaiement(mesJoueurs.get(ja.getIdJoueur()).payer(2));
                desc = "Le joueur "+ja.getIdJoueur()+" a acheter des ressources au joueur "+ idJoueurAPayer + "\n";

            case "posercarte":
                c = mesJoueurs.get(ja.getIdJoueur()).poserCarte(ja.getNumeroCarte());
                desc += "Le joueur "+ja.getIdJoueur()+" a posé la carte "+Couleur.consoleColor(c.getCouleur())+c.getNom();
                ArrayList<Ressource> cr = c.getCoutRessources();
                if(cr.size() > 0){
                    desc += ConsoleLogger.WHITE + " qui coute ";
                    HashMap<Ressource, Integer> hr = new HashMap<Ressource, Integer>();

                    for(Ressource r : cr)
                        hr.put(r, hr.get(r) == null ? 1 : hr.get(r)+1);

                    for(Ressource r : hr.keySet())
                            desc += hr.get(r) + " de "+r.toString()+", ";
                    
                    desc = desc.substring(0, desc.length()-2);
                }
                break;

        }
        return desc;
    }
    
    public final boolean finAge(){
        if(tour > 5)
            return true;
        
        Boolean isFin = true;
        for(byte i=0; i<mesJoueurs.size(); i++){
            if(mesJoueurs.get(i).getDeckMain().size() != 1){
                isFin = false;
                break;
            }
        }
        return isFin;
    }

    private final void compareConfiltsJoueur(Joueur j1, Joueur j2){
        int r1 = j1.getForceMilitaire();
        int r2 = j2.getForceMilitaire();
        if(r1 != r2){
            if(r1 > r2){
                j1.ajouterJetonVictoire(age);
                j2.ajouterJetonDefaite(age);
            //r1 < r2
            } else {
                j2.ajouterJetonVictoire(age);
                j1.ajouterJetonDefaite(age);
            }
        }
    }

    public final void ageSuivant(){

        //Calcul confilts militaire
        for(byte i=0; i<mesJoueurs.size()-1; i++)
            compareConfiltsJoueur(mesJoueurs.get(i), mesJoueurs.get(i+1));

        compareConfiltsJoueur(mesJoueurs.get(mesJoueurs.size()-1), mesJoueurs.get(0));

        age++;
        tour = 1;
    }

    public final boolean finJeu(){ return  age >= 3; }
    
    public final ArrayList<Joueur> getClassement(){
        mesJoueurs.sort(new Comparator<Joueur>(){
            public int compare(Joueur j1, Joueur j2) {
                // j2 > j1 ?  j2,j1 : j1,j2
                return Integer.compare(j2.getScore(), j1.getScore());
            }
        });
        return mesJoueurs;
    }

    public final ArrayList<VisionJeu> getVisionsJeu(){
        ArrayList<VisionJeu> visions = new ArrayList<VisionJeu>(mesJoueurs.size());

        for(int i=0; i<mesJoueurs.size(); i++){
            Joueur j = mesJoueurs.get(i);
            VisionJeu vj= new VisionJeu(i, j.getPiece(), j.getPlateau(), j.getDeckMain(), j.getDeckPlateau());
            visions.add(vj);
        }

        visions.get(0).setVoisinGauche(visions.get(visions.size()-1));
        visions.get(0).setVoisinDroite(visions.get(1));

        for(int i=1; i<visions.size()-1; i++){
            visions.get(i).setVoisinGauche(visions.get(i-1));
            visions.get(i).setVoisinDroite(visions.get(i+1));
        }

        visions.get(visions.size()-1).setVoisinGauche(visions.get(visions.size()-2));
        visions.get(visions.size()-1).setVoisinDroite(visions.get(0));

        return visions;
    }

    public final static int indexOf(int[] tab, int n) {
        for (int i = 0; i < tab.length; i++)
            if (tab[i] == n)
                return i;

        return -1;
    }
}