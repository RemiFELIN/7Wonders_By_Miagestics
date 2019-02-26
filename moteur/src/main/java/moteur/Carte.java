package moteur;

public class Carte {
    private int valeur;

    public Carte(int nb) {
        this.valeur = nb;
    }

    public int getValue() {
        return this.valeur;
    }

    String descriptionCarte() {
        return "Ma valeur est" + Integer.toString(valeur);
    }
}