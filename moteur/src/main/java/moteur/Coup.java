package moteur;

public class Coup {
    private int id;
    private int numeroCarte;

    //Constructeur vide pour la sérialisation du JSONObject
    public Coup() {}

    public Coup(int id, int numeroCarte){
        this.id = id;
        this.numeroCarte = numeroCarte;
    }

    /**
     * @return the id
     */
    final public int getId(){
        return id;
    }

    /**
     * @return the numeroCarte
     */
    final public int getNumeroCarte() {
        return numeroCarte;
    }

    @Override
    public String toString(){
        return "Le joueur "+id+" à jouer la carte "+numeroCarte;
    }
}