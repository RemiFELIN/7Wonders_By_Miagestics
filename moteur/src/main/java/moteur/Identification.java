package commun;

public class Identification {

    private String nom ;
    private int niveau;

    public Identification() {}

    public Identification(String nom, int level) {
        this.nom = nom;
        niveau = level;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
}
