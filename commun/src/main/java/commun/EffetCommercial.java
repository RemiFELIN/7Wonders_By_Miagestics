package commun;

/**
 * @author Pierre Saunders
 */
public enum EffetCommercial {
    //AGE 1
    ACHAT_MATIERE_DROITE ("achatMatiereDroite"),
    ACHAT_MATIERE_GAUCHE ("achatMatiereGauche"),
    ACHAT_PREMIERE ("achatPremiere"),
    BONUS_OR ("bonusOr"),
    //AGE 2
    OR_CARTE_MARRON ("orCarteMarron"),
    OR_CARTE_GRIS ("orCarteGris"),
    // AGE 3
    BONUS_ETAPE_MERVEILLE ("bonusEtapeMerveille"),
    BONUS_CARTE_MARRON ("bonusCarteMarron"),
    BONUS_CARTE_GRIS ("bonusCarteGris"),
    BONUS_CARTE_JAUNE ("bonusCarteJaune");

    private String nom = "";

    /**
     * @param nom nom
     */
    EffetCommercial(String nom){ this.nom = nom; }
    /**
     * @return la transcription en string de l'EffetCommercial
     */
    public final String toString(){ return nom; }
    /**
     * Permet de retrouver l'EffetCommercial à partir de sa valeur en string
     * @param nom nom de l'EffetCommercial
     * @return l'enum EffetCommercial correspondante
     */
    public final static EffetCommercial fromString(String nom){
        for(EffetCommercial type : EffetCommercial.values())
            if(type.toString().equalsIgnoreCase(nom))
                return type;

        return null;
    }
}