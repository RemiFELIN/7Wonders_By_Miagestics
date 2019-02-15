package moteur;

public class Carte {
    private int valeur;

    public Carte(int nb){
        this.valeur = nb;
    }

    String descriptionCarte(){
        return "Ma valeur est" + Integer.toString(valeur);
    }
}