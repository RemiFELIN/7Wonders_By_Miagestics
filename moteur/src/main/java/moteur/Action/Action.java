package moteur.action;

public abstract class Action {

    private int idJoueur;

    public Action(int idJoueur){
        this.idJoueur = idJoueur;
    }

    /**
     * @return the id
     */
    final public int getIdJoueur(){
        return idJoueur;
    }

    public abstract String getType();
}