package commun;

/**
 * @author Pierre Saunders
 */
public abstract class EventConnection {

    public static final String CONNEXION = "connect";
    public static final String DECONNEXION = "disconnect";
    public static final String DEBUT_TOUR = "debutTour";
    public static final String JOUER_ACTION = "jouerAction";
    public static final String RECU_CARTE = "recuCarte";
    public static final String REJOINDRE_JEU = "rejoindreJeu";

    /**
     * Permet de récupèrer l'évenement de la réception de la VisionJeu
     * @param id id du joueur qui reçoit sa vision de jeu
     */
    public static final String GET_VISIONJEU(int id){
        return "getVisionJeu" + id;
    }

    /**
     * Permet de récupèrer l'évenement de la fin de jeu selon l'id du joueur
     * @param id id du joueur qui reçoit l'évenement
     */
    public static final String FIN_JEU(int id){
        return "finJeu" + id;
    }
}