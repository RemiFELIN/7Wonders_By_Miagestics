package commun;

public class Coup {
    private int coup ;
    private boolean plusGrand;

    public int getCoup() {
        return coup;
    }

    public void setCoup(int coup) {
        this.coup = coup;
    }

    public boolean isPlusGrand() {
        return plusGrand;
    }

    public void setPlusGrand(boolean plusGrand) {
        this.plusGrand = plusGrand;
    }

    /**
     *  constructeur par défaut pour le mapping JSOn - Objet java
     */
    public Coup() {}

    /**
     * constructeur pour créer un coup joué avec la réponse (si ce n'est pas la bonne)
     * @param val la valeur qui a été proposée par le.a joueur.se
     * @param sup indique si la valeur est plus grande que le nombre recherché
     */
    public Coup(int val, boolean sup) {
        setCoup(val);
        setPlusGrand(sup);
    }

    @Override
    public String toString() {
        return ""+getCoup()+"/"+(isPlusGrand()?">":"<");
    }
}
