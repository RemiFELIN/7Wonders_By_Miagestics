package moteur;

import java.util.ArrayList;

public class Carte {

    private Couleur couleur;
    private String nom;
    private int age;

    private int coutPiece = 0;
    private int laurier = 0;
    private int puissanceMilitaire = 0;
    private int piece = 0;
    private ArrayList<Ressource> coutRessources = new ArrayList<Ressource>();
    private ArrayList<Ressource> ressources = new ArrayList<Ressource>();

    //TODO Dans une future itération
    /*
    protected String symboleScientifique; // TODO enum a rajouter par la suite
    protected String description = ""; // TODO remplir avec ???
    protected String nextBuilding = ""; // TODO changer avec ???
    protected String specialEffect = ""; // TODO changer string par un enum ?
    */

    public Carte(String nom, Couleur couleur, int age) {
        this.nom = nom;
        this.couleur = couleur;// couleur noir pour les ressources
        this.age = age;
    }

    public Carte(String nom, Couleur couleur, int age, int coutPiece, int laurier, int puissanceMilitaire, int piece){
        this(nom, couleur, age);
        this.coutPiece = coutPiece;
        this.laurier = laurier;
        this.puissanceMilitaire = puissanceMilitaire;
        this.piece = piece;
    }

    public void ajouterCoutRessource(Ressource res){
        coutRessources.add(res);
    }

    public void ajouterRessource(Ressource res){
        ressources.add(res);
    }

    public static ArrayList<Carte> getDeck(){
        ArrayList<Carte> deck = new ArrayList<Carte>();
        Carte c;

        c = new Carte("Cavite", Couleur.NOIR, 1);
        c.ajouterRessource(Ressource.MINERAI);
        deck.add(c);

        deck.add(new Carte("Taverne", Couleur.JAUNE, 1, 0, 0, 0, 5));

        addCartesVictoire(deck);
        addCartesMilitaires(deck);
        addCartesRessources(deck);

        return deck;
    }

    public final Couleur getCouleur() {
        return couleur;
    }

    public final String getNom() {
        return nom;
    }

    public final int getAge() {
        return age;
    }

    public final int getCoutPiece() {
        return coutPiece;
    }

    public final int getLaurier() {
        return laurier;
    }

    public final int getPuissanceMilitaire() {
        return puissanceMilitaire;
    }
    
    //TODO Dans une future itération
    /*
    public final String getSymboleScientifique() {
        return symboleScientifique;
    }

    public final String getDescription() {
        return description;
    }

    public final String getNextBuilding() {
        return nextBuilding;
    }

    public final String getSpecialEffect() {
        return specialEffect;
    }*/

    public final int getPiece() {
        return piece;
    }

    public final ArrayList<Ressource> getRessources() {
        return ressources;
    }

    public final ArrayList<Ressource> getCoutRessources() {
        return coutRessources;
    }

    private static void addCartesMilitaires(ArrayList<Carte> deck)
    {   Carte c;
        //cartes militaires de l'âge 1

        c = new Carte("Caserne", Couleur.ROUGE, 1, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.MINERAI);
        deck.add(c);

        c = new Carte("Palissade", Couleur.ROUGE, 1, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.BOIS);
        deck.add(c);

        c = new Carte("Tour de garde", Couleur.ROUGE, 1, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.ARGILE);
        deck.add(c);

        c = new Carte("Tour de garde", Couleur.ROUGE, 1, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.ARGILE);
        deck.add(c);

        //cartes militaires de l'âge 2

        c = new Carte("Ecuries", Couleur.ROUGE, 2, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.MINERAI);
        deck.add(c);

        c = new Carte("Murs", Couleur.ROUGE, 2, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.PIERRE);
        deck.add(c);

        c = new Carte("Tir à l'arc", Couleur.ROUGE, 2, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.PIERRE);
        deck.add(c);

        c = new Carte("Terrain d'entraînement", Couleur.ROUGE, 2, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.BOIS);
        deck.add(c);

        //cartes militaires de l'âge 3

        c = new Carte("Cirque", Couleur.ROUGE, 3, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.MINERAI);
        deck.add(c);

        c = new Carte("Arsenal", Couleur.ROUGE, 3, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.TEXTILE);
        deck.add(c);

        c = new Carte("Fortifications", Couleur.ROUGE, 3, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.PIERRE);
        deck.add(c);

        c = new Carte("Atelier de siège", Couleur.ROUGE, 3, 0, 0, 1, 0);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.BOIS);
        deck.add(c);
    }

    private static void addCartesVictoire(ArrayList<Carte> deck)
    {
        Carte c;

        //cartes victoire de l'âge 1

        deck.add(new Carte("Theatre", Couleur.BLEU, 1, 0, 2, 0, 0));

        c = new Carte("Prêteur sur gage", Couleur.BLEU, 1, 0, 2, 0, 0);
        deck.add(c);

        c = new Carte("Autel", Couleur.BLEU, 1, 0, 2, 0, 0);
        deck.add(c);

        c = new Carte("Bains", Couleur.BLEU, 1, 0, 3, 0, 0);
        c.ajouterCoutRessource(Ressource.PIERRE);
        deck.add(c);

        //cartes victoire de l'âge 2

        c = new Carte("Tribunal", Couleur.BLEU, 2, 0, 4, 0, 0);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.TEXTILE);
        deck.add(c);

        c = new Carte("Statue", Couleur.BLEU, 2, 0, 4, 0, 0);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.BOIS);
        deck.add(c);

        c = new Carte("Temple", Couleur.BLEU, 2, 0, 3, 0, 0);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.VERRE);
        deck.add(c);

        c = new Carte("Aqueduc", Couleur.BLEU, 2, 0, 5, 0, 0);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.PIERRE);
        deck.add(c);

        //cartes victoire de l'âge 3

        c = new Carte("Palais", Couleur.BLEU, 3, 0, 8, 0, 0);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.VERRE);
        c.ajouterCoutRessource(Ressource.PAPYRUS);
        c.ajouterCoutRessource(Ressource.TEXTILE);
        deck.add(c);

        c = new Carte("Jardins", Couleur.BLEU, 3, 0, 5, 0, 0);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.BOIS);
        deck.add(c);

        c = new Carte("Panthéon", Couleur.BLEU, 3, 0, 7, 0, 0);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.ARGILE);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.VERRE);
        c.ajouterCoutRessource(Ressource.PAPYRUS);
        c.ajouterCoutRessource(Ressource.TEXTILE);
        deck.add(c);

        c = new Carte("Mairie", Couleur.BLEU, 3, 0, 6, 0, 0);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.MINERAI);
        c.ajouterCoutRessource(Ressource.VERRE);
        deck.add(c);

        c = new Carte("Sénat", Couleur.BLEU, 3, 0, 6, 0, 0);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.BOIS);
        c.ajouterCoutRessource(Ressource.PIERRE);
        c.ajouterCoutRessource(Ressource.MINERAI);
        deck.add(c);

    }

    private static void addCartesRessources(ArrayList<Carte> deck)
    {   Carte c;
        //cartes ressources de l'âge 1

        c = new Carte("Fosse", Couleur.NOIR, 1);
        c.ajouterRessource(Ressource.PIERRE);
        deck.add(c);

        c = new Carte("Cour à bois", Couleur.NOIR, 1);
        c.ajouterRessource(Ressource.BOIS);
        deck.add(c);

        c = new Carte("Piscine d'argile", Couleur.NOIR, 1);
        c.ajouterRessource(Ressource.ARGILE);
        deck.add(c);

        c = new Carte("Cavite", Couleur.NOIR, 1);
        c.ajouterRessource(Ressource.MINERAI);
        deck.add(c);

        c = new Carte("Forêt", Couleur.NOIR, 1, 1,0,0,0);
        c.ajouterRessource(Ressource.BOIS);
        c.ajouterRessource(Ressource.ARGILE);
        deck.add(c);

        c = new Carte("Excavation", Couleur.NOIR, 1, 1,0,0,0);
        c.ajouterRessource(Ressource.PIERRE);
        c.ajouterRessource(Ressource.ARGILE);
        deck.add(c);

        c = new Carte("Fosse d'argile", Couleur.NOIR, 1, 1,0,0,0);
        c.ajouterRessource(Ressource.ARGILE);
        c.ajouterRessource(Ressource.MINERAI);
        deck.add(c);

        c = new Carte("Cour à bois bis", Couleur.NOIR, 1, 1,0,0,0);
        c.ajouterRessource(Ressource.PIERRE);
        c.ajouterRessource(Ressource.BOIS);
        deck.add(c);

        c = new Carte("Cave forestière", Couleur.NOIR, 1, 1,0,0,0);
        c.ajouterRessource(Ressource.BOIS);
        c.ajouterRessource(Ressource.MINERAI);
        deck.add(c);

        c = new Carte("Mine", Couleur.NOIR, 1, 1,0,0,0);
        c.ajouterRessource(Ressource.PIERRE);
        c.ajouterRessource(Ressource.MINERAI);
        deck.add(c);

        //cartes ressources de l'âge 2

        c = new Carte("Scierie", Couleur.NOIR, 2, 1,0,0,0);
        c.ajouterRessource(Ressource.BOIS);
        c.ajouterRessource(Ressource.BOIS);
        deck.add(c);

        c = new Carte("Carrière", Couleur.NOIR, 2, 1,0,0,0);
        c.ajouterRessource(Ressource.PIERRE);
        c.ajouterRessource(Ressource.PIERRE);
        deck.add(c);

        c = new Carte("Briquetterie", Couleur.NOIR, 2, 1,0,0,0);
        c.ajouterRessource(Ressource.ARGILE);
        c.ajouterRessource(Ressource.ARGILE);
        deck.add(c);

        c = new Carte("Fonderie", Couleur.NOIR, 2, 1,0,0,0);
        c.ajouterRessource(Ressource.MINERAI);
        c.ajouterRessource(Ressource.MINERAI);
        deck.add(c);
    }
}
