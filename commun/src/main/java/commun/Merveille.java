package commun;

import java.util.ArrayList;

import static commun.Ressource.*;

public class Merveille {

    private char face;
    private String nom;
    private Ressource res;
    private Etape[] etapes;

    /**
     * @author Rémi Felin
     * Constructeur de merveille, il prend en argument :
     * @param nom (nom de la merveille)
     * @param face ('A' ou 'B')
     * @param res (La ressource que la merveille confère au joueur)
     * @param nbEtapes (le nombre d'étapes dont la merveille dispose)
     */
    public Merveille(String nom, char face, Ressource res, int nbEtapes) {
        this.nom = nom;
        this.face = face;
        this.res = res;
        etapes = new Etape[nbEtapes];
    }

    /**
     * @return la face de la merveille ('A' ou 'B')
     */
    public final char getFace(){ return face; }

    /**
     * @return le nom de la merveille
     */
    public final String getNom(){ return nom; }

    /**
     * @return la ressource que la merveille confère au joueur
     */
    public final Ressource getRessource(){ return res; }

    /**
     * @return le tableau d'étapes dont la merveille
     */
    public final Etape[] getEtapes(){ return etapes; }

    /**
     * @param indice (le numéro de l'étape que l'on veut retourner)
     * @return l'étape correspondant à l'indice fourni en argument
     */
    public Etape getEtape(int indice){ return etapes[indice - 1]; }

    /**
     * Cette méthode permet d'ajouter une étape à une merveille
     * @param etape (une instance d'étape)
     * @param indice (en d'autres termes, son positionnement dans le plateau)
     */
    public void ajouterEtape(Etape etape, int indice){
        etapes[indice - 1] = etape;
    }

    /**
     * Cette méthode permet de générer les plateaux (merveilles et les étapes liées à celles-ci)
     * @return la collection complète des plateaux du jeu 7Wonders
     */
    public final static ArrayList<Merveille> getPlateaux(){
        ArrayList<Merveille> plateaux = new ArrayList<Merveille>();
        Merveille m;
        Etape etape;

        ////////////////////////////////
        // Plateau: Le Colosse de Rhodes
        ////////////////////////////////

        m = new Merveille("Le Colosse de Rhodes", 'A', MINERAI, 3);
            //etape 1
            etape = new Etape(new Ressource[]{BOIS, BOIS}, 3, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 1);
            //etape 2
            etape = new Etape(new Ressource[]{ARGILE, ARGILE, ARGILE}, 0, 2, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 2);
            //etape 3
            etape = new Etape(new Ressource[]{MINERAI, MINERAI, MINERAI, MINERAI}, 7, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 3);
            //fin
        plateaux.add(m);

        m = new Merveille("Le Colosse de Rhodes", 'B', MINERAI, 2);
            //etape 1
            etape = new Etape(new Ressource[]{PIERRE, PIERRE, PIERRE}, 3, 1, 3, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 1);
            //etape 2
            etape = new Etape(new Ressource[]{MINERAI, MINERAI, MINERAI, MINERAI}, 4, 1, 4, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 2);
            //fin
        plateaux.add(m);

        ////////////////////////////////
        //Plateau: Le phare d’Alexandrie
        ////////////////////////////////

        m = new Merveille("Le phare d’Alexandrie", 'A', VERRE, 3);
            //etape 1
            etape = new Etape(new Ressource[]{PIERRE, PIERRE}, 3, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 1);
            //etape 2
            etape = new Etape(new Ressource[]{MINERAI, MINERAI}, 0, 0, 0, new Ressource[]{ARGILE, MINERAI, BOIS, PIERRE}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 2);
            //etape 3
            etape = new Etape(new Ressource[]{VERRE, VERRE}, 7, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 3);
            //fin
        plateaux.add(m);

        m = new Merveille("Le Phare d'Alexandrie", 'B', VERRE, 3);
            //etape 1
            etape = new Etape(new Ressource[]{ARGILE, ARGILE}, 0, 0, 0, new Ressource[]{BOIS, PIERRE, MINERAI, ARGILE}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 1);
            //etape 2
            etape = new Etape(new Ressource[]{BOIS, BOIS}, 0, 0, 0, new Ressource[]{VERRE, TEXTILE, PAPYRUS}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 2);
            //etape 3
            etape = new Etape(new Ressource[]{PIERRE, PIERRE, PIERRE}, 7, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 3);
            //fin
        plateaux.add(m);

        ///////////////////////////////////////
        //Plateau: Le temple d'Artémis à Ephèse
        ///////////////////////////////////////

        m = new Merveille("Le temple d’Artémis à Ephèse", 'A', PAPYRUS, 3);
            //etape 1
            etape = new Etape(new Ressource[]{PIERRE, PIERRE}, 3, 0, 0, new Ressource[]{}, new SymboleScientifique[]{},"");
            m.ajouterEtape(etape, 1);
            //etape 2
            etape = new Etape(new Ressource[]{BOIS, BOIS}, 0, 0, 9, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 2);
            //etape 3
            etape = new Etape(new Ressource[]{PAPYRUS, PAPYRUS}, 7, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 3);
            //fin
        plateaux.add(m);

        m = new Merveille("Le temple d’Artémis à Ephèse", 'B', PAPYRUS, 3);
            //etape 1
            etape = new Etape(new Ressource[]{PIERRE, PIERRE}, 2, 0, 4, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 1);
            //etape 2
            etape = new Etape(new Ressource[]{BOIS, BOIS}, 3, 0,4, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 2);
            //etape 3
            etape = new Etape(new Ressource[]{PAPYRUS, TEXTILE, VERRE}, 5, 0, 4, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 3);
            //fin
        plateaux.add(m);

        ////////////////////////////////////////////
        //Plateau: Les jardins suspendus de Babylone
        ////////////////////////////////////////////

        m = new Merveille("Les jardins suspendus de Babylone", 'A', ARGILE, 3);
            //etape 1
            etape = new Etape(new Ressource[]{ARGILE, ARGILE}, 3, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 1);
            //etape 2
            etape = new Etape(new Ressource[]{BOIS, BOIS, BOIS}, 0, 0, 0, new Ressource[]{}, new SymboleScientifique[]{SymboleScientifique.choisirSymbole()}, "");
            m.ajouterEtape(etape, 2);
            //etape 3
            etape = new Etape(new Ressource[]{ARGILE, ARGILE, ARGILE, ARGILE}, 7, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 3);
            //fin
        plateaux.add(m);

        m = new Merveille("Les jardins suspendus de Babylone", 'B', ARGILE, 3);
            //etape 1
            etape = new Etape(new Ressource[]{TEXTILE, ARGILE}, 3, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 1);
            //etape 2
            etape = new Etape(new Ressource[]{VERRE, BOIS, BOIS}, 0, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "Jouer une carte en plus");
            m.ajouterEtape(etape, 2);
            //etape 3
            etape = new Etape(new Ressource[]{ARGILE, ARGILE, ARGILE, PAPYRUS}, 0, 0, 0, new Ressource[]{}, new SymboleScientifique[]{SymboleScientifique.choisirSymbole()}, "");
            m.ajouterEtape(etape, 3);
            //fin
        plateaux.add(m);

        //////////////////////////////////////
        //Plateau: La statue de Zeus à Olympie
        //////////////////////////////////////

        m = new Merveille("La statue de Zeus à Olympie", 'A', BOIS, 3);
            //etape 1
            etape = new Etape(new Ressource[]{BOIS, BOIS}, 3, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 1);
            //etape 2
            etape = new Etape(new Ressource[]{PIERRE, PIERRE}, 0, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "construire un bâtiment de mon choix");
            m.ajouterEtape(etape, 2);
            //etape 3
            etape = new Etape(new Ressource[]{MINERAI, MINERAI}, 7, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 3);
            //fin
        plateaux.add(m);

        m = new Merveille("La statue de Zeus à Olympie", 'B', BOIS, 3);
            //etape 1
            etape = new Etape(new Ressource[]{BOIS, BOIS}, 0, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "Payer les ressources aux voisins pour 1 pièce");
                //cas particulier : le bonus modifie le prix des ressources que l'on peut acheter au voisin
            m.ajouterEtape(etape, 1);
            //etape 2
            etape = new Etape(new Ressource[]{PIERRE, PIERRE}, 5, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 2);
            //etape 3
            etape = new Etape(new Ressource[]{MINERAI, MINERAI, TEXTILE}, 0, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "Copier une guilde de mon choix");
            m.ajouterEtape(etape, 3);
            //fin
        plateaux.add(m);

        /////////////////////////////////////
        //Plateau: Le mausolée d’Halicarnasse
        /////////////////////////////////////

        m = new Merveille("Le mausolée d’Halicarnasse", 'A', TEXTILE, 3);
            //etape 1
            etape = new Etape(new Ressource[]{ARGILE, ARGILE}, 3, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 1);
            //etape 2
            etape = new Etape(new Ressource[]{MINERAI, MINERAI, MINERAI}, 0, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "piocher une carte dans la fosse");
            m.ajouterEtape(etape, 2);
            //etape 3
            etape = new Etape(new Ressource[]{TEXTILE, TEXTILE}, 7, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 3);
            //fin
        plateaux.add(m);

        m = new Merveille("Le mausolée d’Halicarnasse", 'B', TEXTILE, 3);
            //etape 1
            etape = new Etape(new Ressource[]{MINERAI, MINERAI}, 2, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "piocher une carte dans la fosse");
            m.ajouterEtape(etape, 1);
            //etape 2
            etape = new Etape(new Ressource[]{ARGILE, ARGILE, ARGILE}, 1, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "piocher une carte dans la fosse");
            m.ajouterEtape(etape, 2);
            //etape 3
            etape = new Etape(new Ressource[]{VERRE, PAPYRUS, TEXTILE}, 0, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "piocher une carte dans la fosse");
            m.ajouterEtape(etape, 3);
            //fin
        plateaux.add(m);

        //////////////////////////////////////
        //Plateau: La grande pyramide de Gizeh
        //////////////////////////////////////

        m = new Merveille("La grande pyramide de Gizeh", 'A', PIERRE, 3);
            //etape 1
            etape = new Etape(new Ressource[]{PIERRE, PIERRE}, 3, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 1);
            //etape 2
            etape = new Etape(new Ressource[]{BOIS, BOIS, BOIS}, 5, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 2);
            //etape 3
            etape = new Etape(new Ressource[]{PIERRE, PIERRE, PIERRE, PIERRE}, 7, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 3);
            //fin
        plateaux.add(m);

        m = new Merveille("La grande pyramide de Gizeh", 'B', PIERRE, 4);
            //etape 1
            etape = new Etape(new Ressource[]{BOIS, BOIS}, 3, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 1);
            //etape 2
            etape = new Etape(new Ressource[]{PIERRE, PIERRE, PIERRE}, 5, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 2);
            //etape 3
            etape = new Etape(new Ressource[]{ARGILE, ARGILE, ARGILE}, 5, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 3);
            //etape 4
            etape = new Etape(new Ressource[]{PIERRE, PIERRE, PIERRE, PIERRE, PAPYRUS}, 7, 0, 0, new Ressource[]{}, new SymboleScientifique[]{}, "");
            m.ajouterEtape(etape, 4);
            //fin
        plateaux.add(m);

        //fin merveille
        return plateaux;
    }
}