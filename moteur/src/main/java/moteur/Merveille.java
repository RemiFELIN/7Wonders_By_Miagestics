package moteur;

import java.util.ArrayList;

import static moteur.Ressource.*;

public class Merveille {

    private char face;
    private String nom;
    private Ressource res;

    private Etape[] etapes;
    private int etapesConstruites = 0;

    public Merveille(String nom, char face, Ressource res, int nbEtapes) {
        this.nom = nom;
        this.face = face;
        this.res = res;
        etapes = new Etape[nbEtapes];
    }

    public final char getFace(){ return face; }
    public final String getNom(){ return nom; }
    public final Ressource getRessource(){ return res; }

    public void ajouterEtape(Etape etape, int indice){
        etapes[indice] = etape;
    }

    public final static ArrayList<Merveille> getPlateau(){
        ArrayList<Merveille> plateaux = new ArrayList<Merveille>();
        Merveille m;
        Etape etape;

        //Plateau: Le Colosse de Rhodes
        m = new Merveille("Le Colosse de Rhodes", 'A', MINERAI, 3);
            //etape 1
            etape = new Etape();
            etape.setCoup(new Ressource[]{BOIS, BOIS});
            etape.setBonus(3, 0, 0, new Ressource[]{}, "");
            m.ajouterEtape(etape, 1);
            //etape 2
            etape = new Etape();
            etape.setCoup(new Ressource[]{ARGILE, ARGILE, ARGILE});
            etape.setBonus(0, 2, 0, new Ressource[]{}, "");
            m.ajouterEtape(etape, 2);
            //etape 3
            etape = new Etape();
            etape.setCoup(new Ressource[]{MINERAI, MINERAI, MINERAI, MINERAI});
            etape.setBonus(7, 0, 0, new Ressource[]{}, "");
            m.ajouterEtape(etape, 3);
        plateaux.add(m);

        m = new Merveille("Le Colosse de Rhodes", 'B', MINERAI, 2);
            //etape 1
            etape = new Etape();
            etape.setCoup(new Ressource[]{PIERRE, PIERRE, PIERRE});
            etape.setBonus(3, 1, 3, new Ressource[]{}, "");
            m.ajouterEtape(etape, 1);
            //etape 2
            etape = new Etape();
            etape.setCoup(new Ressource[]{MINERAI, MINERAI, MINERAI, MINERAI});
            etape.setBonus(4, 1, 4, new Ressource[]{}, "");
            m.ajouterEtape(etape, 2);
        plateaux.add(m);

        //Plateau: Le phare d’Alexandrie
        m = new Merveille("Le phare d’Alexandrie", 'A', VERRE, 3);
            //etape 1
            etape = new Etape();
                    /*
        ressources = new Ressource[][]{new Ressource[]{PIERRE, PIERRE}
        ,new Ressource[]{MINERAI, MINERAI}
        ,new Ressource[]{VERRE, VERRE}};
        m.setCoupEtape(ressources);
        ressources = new Ressource[][]{new Ressource[]{ARGILE, BOIS, MINERAI, MINERAI}};
        //m.setBonusEtapeRes(ressources);
        plateaux.add(m);

        //Plateau: Le temple d'Artémis à Ephèse
        m = new Merveille("Le temple d’Artémis à Ephèse", 'A', PAPYRUS, 3);
        ressources = new Ressource[][]{new Ressource[]{PIERRE, PIERRE}
        ,new Ressource[]{BOIS, BOIS}
        ,new Ressource[]{PAPYRUS, PAPYRUS}};
        m.setCoupEtape(ressources);
        int[] piece = new int[] { 0,9,0 };
        //m.setBonusEtapePiece(piece);
        plateaux.add(m);

        //Plateau: Les jardins suspendus de Babylone
        m = new Merveille("Les jardins suspendus de Babylone", 'A', ARGILE, 3);
        ressources = new Ressource[][]{new Ressource[]{ARGILE, ARGILE}
        ,new Ressource[]{BOIS, BOIS, BOIS, BOIS}
        ,new Ressource[]{ARGILE, ARGILE, ARGILE, ARGILE}};
        m.setCoupEtape(ressources);
        plateaux.add(m);

        //Plateau: La statue de Zeus à Olympie
        m = new Merveille("La statue de Zeus à Olympie", 'A', BOIS, 3);
        ressources = new Ressource[][]{new Ressource[]{BOIS, BOIS}
        ,new Ressource[]{PIERRE, PIERRE}
        ,new Ressource[]{MINERAI, MINERAI}};
        m.setCoupEtape(ressources);
        plateaux.add(m);

        //Plateau: Le mausolée d’Halicarnasse
        m = new Merveille("Le mausolée d’Halicarnasse", 'A', TEXTILE, 3);
        ressources = new Ressource[][]{new Ressource[]{ARGILE, ARGILE}
        ,new Ressource[]{MINERAI, MINERAI, MINERAI}
        ,new Ressource[]{TEXTILE, TEXTILE}};
        m.setCoupEtape(ressources);
        plateaux.add(m);

        //Plateau: La grande pyramide de Gizeh
        m = new Merveille("La grande pyramide de Gizeh", 'A', PIERRE, 3);
        ressources = new Ressource[][]{new Ressource[]{PIERRE, PIERRE}
        ,new Ressource[]{BOIS, BOIS, BOIS}
        ,new Ressource[]{PIERRE, PIERRE,PIERRE,PIERRE}};
        m.setCoupEtape(ressources);
        int[] pv = new int[] { 3,5,7 };
        //m.setBonusEtapePv(pv);
        plateaux.add(m);
        
        return plateaux;*/
    }
}