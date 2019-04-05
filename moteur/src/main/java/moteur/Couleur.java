package moteur;

/**
 * @author Pierre Saunders
 */
public enum Couleur {
    MARRON ("MARRON"),
    BLANC ("BLANC"),
    JAUNE ("JAUNE"),
    ROUGE ("ROUGE"),
    BLEU ("BLEU"),
    VIOLET ("VIOLET"),
    VERT ("VERT"),
    GRIS ("GRIS");

    private String nom = "";

    /**
     * @param un nom
     */
    Couleur(String nom){ this.nom = nom; }
    /**
     * @return la transcription en string de la Couleur
     */
    public final String toString(){ return nom; }
    /**
     * Permet de retrouver la Couleur à partir de sa valeur en string
     * @param le nom de la couleur
     * @return l'enum Couleur correspondante
     */
    public static final Couleur fromString(String nom){
        for(Couleur type : Couleur.values())
            if(type.toString().equals(nom))
                return type;

        return null;
    }
    /**
     * Permet de traduire la Couleur en couleur console/shell
     * @param une Couleur
     * @return la couleur correspondante à la console
     */
    public static final String consoleColor(Couleur c){
        switch(c){
            case MARRON:
                return ConsoleLogger.BLACK_BOLD_BRIGHT;
            case BLANC:
                return ConsoleLogger.WHITE_BOLD_BRIGHT;
            case JAUNE:
                return ConsoleLogger.YELLOW_BOLD_BRIGHT;
            case ROUGE:
                return ConsoleLogger.RED_BOLD_BRIGHT;
            case BLEU:
                return ConsoleLogger.BLUE_BOLD_BRIGHT;
            case VIOLET:
                return ConsoleLogger.PURPLE_BOLD_BRIGHT;
            case VERT:
                return ConsoleLogger.GREEN_BOLD_BRIGHT;
            case GRIS:
                return ConsoleLogger.WHITE_BOLD_BRIGHT;
            default:
                return ConsoleLogger.WHITE_UNDERLINED;
        }
    }
}