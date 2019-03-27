package moteur;

public enum SymboleScientifique {
    TABLETTE ("TABLETTE"),
    COMPAS ("COMPAS"),
    ROUAGE ("ROUAGE");

    private String nom = "";

    SymboleScientifique(String nom){
        this.nom = nom;
    }

    public static SymboleScientifique choisirSymbole(){
        //todo : Temporaire : On choisit aléatoirement un des trois symboles
        switch((int)Math.random()*2){
            case 0:
                return TABLETTE;
            case 1:
                return COMPAS;
            case 2:
                return ROUAGE;
        }
        return null;
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