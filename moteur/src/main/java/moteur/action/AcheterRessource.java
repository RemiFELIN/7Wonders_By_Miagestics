package moteur.action;

public class AcheterRessource extends PoserCarte {



    public AcheterRessource(int id, int numVoisin, int numCarte)
    {
        super(id,numCarte);
        super.numVoisin=numVoisin;
        super.type="acheterressource";
    }


}
