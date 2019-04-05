package moteur;

/**
 * @authors Yannick Cardini, Pierre Saunders
 */
public enum Ressource {
    PIERRE ("PIERRE"),
    ARGILE ("ARGILE"),
    BOIS ("BOIS"),
    MINERAI ("MINERAI"),
    VERRE ("VERRE"),
    TEXTILE ("TEXTILE"),
    PAPYRUS ("PAPYRUS");

    private String nom = "";

    /**
     * @param un nom
     */
    Ressource(String nom){ this.nom = nom; }
    /**
     * @return la transcription en string de la Ressource
     */
    public final String toString(){ return nom; }
    /**
     * Permet de retrouver la Ressource à partir de sa valeur en string
     * @param le nom de la Ressource
     * @return l'enum Ressource correspondante
     */
    public static Ressource fromString(String nom){
        for(Ressource type : Ressource.values())
            if(type.toString().equals(nom))
                return type;

        return null;
    }
}