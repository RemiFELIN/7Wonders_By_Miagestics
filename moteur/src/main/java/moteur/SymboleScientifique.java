package moteur;
/**
 * @authors Thomas Gauci, Pierre Saunders
 */
public enum SymboleScientifique {
    TABLETTE ("TABLETTE"),
    COMPAS ("COMPAS"),
    ROUAGE ("ROUAGE");

    private String nom = "";

    /**
     * @param un nom
     */
    SymboleScientifique(String nom){ this.nom = nom; }
    /**
     * @return la transcription en string du SymboleScientifique
     */
    public final String toString(){ return nom; }
    /**
     * On choisit aléatoirement un des trois symboles
     * @author Rémi Felin
     * @deprecated
     * @return un SymboleScientifique
     */
    public static SymboleScientifique choisirSymbole(){
        //todo : Temporaire : On choisit aléatoirement un des trois symboles
        switch((int) (Math.random()*3)){
            case 0:
                return TABLETTE;
            case 1:
                return COMPAS;
            case 2:
                return ROUAGE;
        }
        return null;
    }
    /**
     * Permet de retrouver le SymboleScientifique à partir de sa valeur en string
     * @param le nom du SymboleScientifique
     * @return l'enum SymboleScientifique correspondante
     */
    public static SymboleScientifique fromString(String nom){
        for(SymboleScientifique type : SymboleScientifique.values())
            if(type.toString().equals(nom))
                return type;

        return null;
    }
}