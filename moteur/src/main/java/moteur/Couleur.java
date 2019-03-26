package moteur;

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

    public static String consoleColor(Couleur c){
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