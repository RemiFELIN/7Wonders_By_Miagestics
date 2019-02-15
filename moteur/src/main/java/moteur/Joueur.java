package moteur;

import moteur.Jeu;

public class Joueur {

	private int scoreJoueur;
	private Carte[] deck;
	
	public Joueur() {
		this.scoreJoueur = 0;
    }
    
    public void setCartes(Carte[] c){
        this.deck = c;
    }

    public Carte[] getDeck(){
        for(int i=0; i<deck.length; i++)
            Jeu.log(deck[i].descriptionCarte());

        return deck;
    }

	public int getScore() {
		for(int i = 0; i < this.deck.length; i++)
			this.scoreJoueur = this.scoreJoueur + this.deck[i].getValue();
		return this.scoreJoueur;
	}
}

