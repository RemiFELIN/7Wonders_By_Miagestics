package commun;

/**
 * @author Pierre Saunders
 */
public enum EffetGuilde {
    TRAVAILLEURS ("Travailleurs"),
    ARTISANS ("Artisans"),
    COMMERCANTS ("Commerçants"),
    PHILOSOPHES ("Philosophes"),
    ESPIONS ("Espions"),
    STRATEGES ("Stratèges"),
    ARMATEURS ("Armateurs"),
    SCIENTIFIQUES ("Scientifiques"),
    MAGISTRATS ("Magistrats"),
    BATISSEURS ("Bâtisseurs");

    private String nom = "";

    /**
     * @param nom nom
     */
    EffetGuilde(String nom){ this.nom = nom; }
    /**
     * @return la transcription en string de l'EffetGuilde
     */
    public final String toString(){ return nom; }
    /**
     * Permet de retrouver l'EffetGuilde à partir de sa valeur en string
     * @param nom nom de l'EffetGuilde
     * @return l'enum EffetGuilde correspondante
     */
    public static EffetGuilde fromString(String nom){
        for(EffetGuilde type : EffetGuilde.values())
            if(type.toString().equals(nom))
                return type;

        return null;
    }
}