public class PoserCarte implements Action {

    private int numeroCarte;

    public PoserCarte(int numeroCarte){
        this.numeroCarte = numeroCarte;
    }

    public String getType(){
        return "PoserCarte";
    }
}