package moteur;

public enum Couleur {
    MARRON ("MARRON"),
    BLANC ("BLANC"),
    JAUNE ("JAUNE"),
    ROUGE ("ROUGE"),
    BLEU ("BLEU"),
    VERT ("VERT");

    private String nom = "";

    Couleur(String nom){
        this.nom = nom;
    }

    public String toString(){
        return nom;
    }

    public static Couleur fromString(String nom){
        for(Couleur type : Couleur.values())
            if(type.toString() == nom)
                return type;

        return null;
    }
}