package moteur;

public class ActionJoueur {
    private int id;
    private Action action;

    //Constructeur vide pour la sérialisation du JSONObject
    public ActionJoueur() {}

    public ActionJoueur(int id, Action action){
        this.id = id;
        this.action = action;
    }

    /**
     * @return the id
     */
    final public int getId(){
        return id;
    }

    /**
     * @return the action
     */
    final public int getAction() {
        return action;
    }
}