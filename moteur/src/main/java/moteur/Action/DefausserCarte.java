public class DefausserCarte implements Action {

    private int numeroCarte;

    public DefausserCarte(int numeroCarte){
        this.numeroCarte = numeroCarte;
    }

    public String getType(){
        return "DefausserCarte";
    }
}