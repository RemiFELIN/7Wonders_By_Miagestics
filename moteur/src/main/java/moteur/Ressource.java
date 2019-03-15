package moteur;

public enum Ressource {
    PIERRE ("PIERRE"),
    ARGILE ("ARGILE"),
    BOIS ("BOIS"),
    MINERAI ("MINERAI"),
    VERRE ("VERRE"),
    PAPYRUS ("PAPYRUS"),
    TEXTILE ("TEXTILE");

    private String nom = "";

    Ressource(String nom){
        this.nom = nom;
    }

    public String toString(){
        return nom;
    }

    public static Ressource fromString(String nom){
        for(Ressource type : Ressource.values())
            if(type.toString() == nom)
                return type;

        return null;
    }
}