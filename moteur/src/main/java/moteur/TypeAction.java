package moteur;

public enum TypeAction {
    PoserCarte(0),
    DefausserCarte(1),
    AcheterRessource(2),
    ConstruireMerveille(3);

    TypeAction(int v){ value = v; }
    private int value = -1;
    public final int getValue(){ return value; }

    public static final TypeAction fromInteger(int n){
        for(TypeAction type : TypeAction.values())
            if(type.getValue() == n)
                return type;

        return null;
    }
}