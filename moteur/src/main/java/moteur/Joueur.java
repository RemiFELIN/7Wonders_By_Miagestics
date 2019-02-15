package moteur;

public class Joueur {

    private int point = 0;
    private Carte[] carteEnMain;

    public Joueur(){

    }

    public void setCarteEnMain(Carte[] cartes){
        carteEnMain = cartes;
    }

    public int getPoint(){
        return point;
    }
}