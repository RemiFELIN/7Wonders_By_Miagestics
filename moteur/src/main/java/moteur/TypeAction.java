package moteur;

/**
 * @author Pierre Saunders
 */
public enum TypeAction {
    PoserCarte (0),
    DefausserCarte (1),
    AcheterRessource (2),
    ConstruireMerveille (3);

    private int value = -1;

    /**
     * @param un indice
     */
    TypeAction(int v){ value = v; }
    /**
     * @return la transcription en int de la TypeAction
     */
    public final int getValue(){ return value; }

    /**
     * Permet de retrouver le TypeAction à partir de sa valeur en int
     * @param l'indice de le TypeAction
     * @return l'enum TypeAction correspondante
     */
    public static final TypeAction fromInteger(int n){
        for(TypeAction type : TypeAction.values())
            if(type.getValue() == n)
                return type;

        return null;
    }
}