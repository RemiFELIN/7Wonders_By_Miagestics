package moteur;

public class Jeu {
    private final int NBCARTES = 4;
    private final int TAILLE_DECK = 2;
    private Carte tabCarte[];
    private int index[];


    public Jeu(int nbJoueurs){
        tabCarte = new Carte[NBCARTES];
        index = new int[NBCARTES];

        for( int i = 0; i<NBCARTES ;i++){
            tabCarte[i]= new Carte(i);
            index[i]=-1;
        }
        
        
    }
    
    public Carte distributionCarte(){
        boolean flag = false;
        int rand =0;
        int j =0;
        do{
            rand = (int)(Math.random()*(NBCARTES));
            for( int i = 0; i<NBCARTES; i++){
                if(index[i]==rand){
                    flag = true;
                }
            }
        }while(flag == true);
        while(index[j]!=-1){
            j++;
        }
        index[j]= rand;
        return  tabCarte[rand];
    }

    public Carte[] creationDeck(){
        Carte deckJoueur[];
        deckJoueur = new Carte[TAILLE_DECK];
        for(int i = 0; i<TAILLE_DECK; i++){
            deckJoueur[i] = distributionCarte();
        }
        return deckJoueur;
    }
    public final static void log(String s){
        System.out.println(s);
    }


}