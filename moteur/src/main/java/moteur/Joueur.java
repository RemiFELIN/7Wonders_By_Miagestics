package moteur;

public class Joueur {

	private int scoreJoueur;
	private Carte[] deck;

	
	public Joueur(Carte[] c) {
		this.scoreJoueur = 0;
		this.deck = c;

	}
	public int getScore() {
		for(int i = 0; i < this.deck.length; i++)
			this.scoreJoueur = this.scoreJoueur + this.deck[i].getValue();
		return this.scoreJoueur;
	}
}

