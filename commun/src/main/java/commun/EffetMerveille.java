package commun;

/**
 * @author Pierre Saunders
 */
public enum EffetMerveille {
    CONSTRUCTION_GRATUITE ("constructionGratuite"),
    JOUER_DEFAUSSER ("jouerDefausser"),
    DEFAUSSAGE ("defaussage"),
    ACHAT_MATIERE ("achatMatiere"),
    COPIE_GUILDE ("copieGuilde");

    private String nom = "";

    /**
     * @param nom nom
     */
    EffetMerveille(String nom){ this.nom = nom; }
    /**
     * @return la transcription en string de l'EffetMerveille
     */
    public final String toString(){ return nom; }
    /**
     * Permet de retrouver l'EffetMerveille à partir de sa valeur en string
     * @param nom nom de l'EffetMerveille
     * @return l'enum EffetMerveille correspondante
     */
    public final static EffetMerveille fromString(String nom){
        for(EffetMerveille type : EffetMerveille.values())
            if(type.toString().equalsIgnoreCase(nom))
                return type;

        return null;
    }
}