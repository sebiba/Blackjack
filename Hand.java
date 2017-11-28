package blackjack;

public class Hand{
	private int nbrCartes=0;
	private boolean depasse = false;
	private Carte cartes [];
	public Hand(){
		
	}
	public void ajouteCarte(Deck deck){
		cartes[1]=deck.PiocheCarte();
		nbrCartes+=1;
	}
	public String toString(){
		return ("cartes:\n"+cartes[1]);
	}
}
