package moteur;

public enum SymboleScientifique {
    TABLETTE ("TABLETTE"),
    COMPAS ("COMPAS"),
    ROUAGE ("ROUAGE");

    private String nom = "";

    SymboleScientifique(String nom){
        this.nom = nom;
    }

    public String toString(){
        return nom;
    }

    public static SymboleScientifique fromString(String nom){
        for(SymboleScientifique type : SymboleScientifique.values())
            if(type.toString() == nom)
                return type;

        return null;
    }
}