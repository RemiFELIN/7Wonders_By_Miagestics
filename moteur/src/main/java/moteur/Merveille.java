package moteur;

import java.util.ArrayList;

public class Merveille {

    protected char face;
    protected String nom;
    protected Ressource res;

    protected Ressource[][] coutEtape;
    protected Ressource[][] bonusEtapeRes;
    protected int[] bonusEtapePv;
    protected int[] bonusEtapeMilitaire;
    protected int[] bonusEtapePiece;
    protected String[] bonusEtapeEffect;

    public Merveille(String nom,char face,Ressource res) {
        this.nom = nom;
        this.face = face;
        this.res = res;
        if(face == 'A'){
            int[] array = new int[] { 3,0,7 };
            setBonusEtapePv(array);
        }
    }

    public void setCoupEtape(Ressource[][] res){
        this.coutEtape = res;
    }

    public void setBonusEtapePv(int[] pv){
        this.bonusEtapePv = pv;
    }

    public void setBonusEtapeMilitaire(int[] militaire){
        this.bonusEtapeMilitaire = militaire;
    }

    public void setBonusEtapePiece(int[] Piece){
        this.bonusEtapePiece = Piece;
    }

    public void setBonusEtapeEffect(String[] Effect){
        this.bonusEtapeEffect = Effect;
    }

    public void setBonusEtapeRes(Ressource[][] res){
        this.bonusEtapeRes = res;
    }


    public static ArrayList<Merveille> getPlateau(){
        ArrayList<Merveille> plateaux = new ArrayList<Merveille>();
        Merveille m;

        //Plateau: Le Colosse de Rhodes
        m = new Merveille("Le Colosse de Rhodes", 'A', Ressource.MINERAI);
        Ressource[][] ressources = new Ressource[][]{new Ressource[]{Ressource.BOIS,Ressource.BOIS}
        ,new Ressource[]{Ressource.ARGILE, Ressource.ARGILE,Ressource.ARGILE}
        ,new Ressource[]{Ressource.MINERAI, Ressource.MINERAI,Ressource.MINERAI,Ressource.MINERAI}};
        m.setCoupEtape(ressources);
        int[] mil = new int[]{0,2,0};
        m.setBonusEtapeMilitaire(mil);
        plateaux.add(m);

        //Plateau: Le phare d’Alexandrie
        m = new Merveille("Le phare d’Alexandrie", 'A', Ressource.VERRE);
        ressources = new Ressource[][]{new Ressource[]{Ressource.PIERRE, Ressource.PIERRE}
        ,new Ressource[]{Ressource.MINERAI, Ressource.MINERAI}
        ,new Ressource[]{Ressource.VERRE, Ressource.VERRE}};
        m.setCoupEtape(ressources);
        ressources = new Ressource[][]{null,new Ressource[]{Ressource.ARGILE, Ressource.BOIS,Ressource.MINERAI,Ressource.MINERAI},null};
        m.setBonusEtapeRes(ressources);
        plateaux.add(m);

        //Plateau: Le temple d'Artémis à Ephèse
        m = new Merveille("Le temple d’Artémis à Ephèse", 'A', Ressource.PAPYRUS);
        ressources = new Ressource[][]{new Ressource[]{Ressource.PIERRE, Ressource.PIERRE}
        ,new Ressource[]{Ressource.BOIS, Ressource.BOIS}
        ,new Ressource[]{Ressource.PAPYRUS, Ressource.PAPYRUS}};
        m.setCoupEtape(ressources);
        int[] piece = new int[] { 0,9,0 };
        m.setBonusEtapePiece(piece);
        plateaux.add(m);

        //Plateau: Les jardins suspendus de Babylone
        m = new Merveille("Les jardins suspendus de Babylone", 'A', Ressource.ARGILE);
        ressources = new Ressource[][]{new Ressource[]{Ressource.ARGILE, Ressource.ARGILE}
        ,new Ressource[]{Ressource.BOIS, Ressource.BOIS,Ressource.BOIS, Ressource.BOIS}
        ,new Ressource[]{Ressource.ARGILE, Ressource.ARGILE,Ressource.ARGILE, Ressource.ARGILE}};
        m.setCoupEtape(ressources);
        plateaux.add(m);

        //Plateau: La statue de Zeus à Olympie
        m = new Merveille("La statue de Zeus à Olympie", 'A', Ressource.BOIS);
        ressources = new Ressource[][]{new Ressource[]{Ressource.BOIS, Ressource.BOIS}
        ,new Ressource[]{Ressource.PIERRE, Ressource.PIERRE}
        ,new Ressource[]{Ressource.MINERAI, Ressource.MINERAI}};
        m.setCoupEtape(ressources);
        plateaux.add(m);

        //Plateau: Le mausolée d’Halicarnasse
        m = new Merveille("Le mausolée d’Halicarnasse", 'A', Ressource.TEXTILE);
        ressources = new Ressource[][]{new Ressource[]{Ressource.ARGILE, Ressource.ARGILE}
        ,new Ressource[]{Ressource.MINERAI, Ressource.MINERAI,Ressource.MINERAI}
        ,new Ressource[]{Ressource.TEXTILE, Ressource.TEXTILE}};
        m.setCoupEtape(ressources);
        plateaux.add(m);

        //Plateau: La grande pyramide de Gizeh
        m = new Merveille("La grande pyramide de Gizeh", 'A', Ressource.PIERRE);
        ressources = new Ressource[][]{new Ressource[]{Ressource.PIERRE, Ressource.PIERRE}
        ,new Ressource[]{Ressource.BOIS, Ressource.BOIS,Ressource.BOIS}
        ,new Ressource[]{Ressource.PIERRE, Ressource.PIERRE,Ressource.PIERRE,Ressource.PIERRE}};
        m.setCoupEtape(ressources);
        int[] pv = new int[] { 3,5,7 };
        m.setBonusEtapePv(pv);
        plateaux.add(m);
        
        return plateaux;
    }

}
