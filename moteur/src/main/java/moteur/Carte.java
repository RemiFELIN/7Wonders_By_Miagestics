package moteur;

public class Carte {
    private int valeur;
    private int age;

    public Carte(int val, int age) {
        this.valeur = val;
        this.age = age;

    }

    public int getValue() {
        return this.valeur * this.age;
    }

    String descriptionCarte() {
        return "Ma valeur est" + Integer.toString(valeur);
    }
}