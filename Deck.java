package blackjack;

import java.util.Random;

public class Deck{
	private int nbrCarteDepart;
	Carte[] deck = new Carte[52];
	
	/**constructeur du deck de 52 object cartes
	 * 
	 */
	public Deck(){
		int i = 0;
		for (int cpt=0;cpt<4;cpt++){//boucle 4 familles
			for(int cpt2=1;cpt2<14;cpt2++){//boucle 13 cartes de chaque familles
				deck[i] = new Carte(cpt2,cpt);
				i++;
			}
		}
	}
	/**
	 * fonction simulant la pioche d'une carte dans le deck
	 * affecte la valeur true a la var isUsed de la carte pour eviter qu'elle resorte
	 * @return la carte tirée
	 */
	public Carte PiocheCarte(){
		Random r = new Random();
		Carte carte = deck[r.nextInt(51)+1];
		while(carte.isUsed()==true){
			r = new Random();
			carte = deck[r.nextInt(51)+1];
		}
		if(carte.getValeur()>10){
			carte.setValeur(10);
		}
		carte.setUsed();
		//System.out.println("\ncarte: "+carte.getValeurString()+"\nfamille: "+carte.getFamilleString()+"\nis used: "+carte.isUsed());
		return carte;
		/*for(int i=0;i<52;i++){
			System.out.println(this.deck[i]);
		}*/
	}
	
	/**
	 * renvois une chaire de Charactere decrivant les cartes du jeux
	 */
	public String toString(){
		String string = "";
		for(int i=0;i<52;i++){
			string+=("\ncarte: "+deck[i].getValeurString()+"\nfamille: "+deck[i].getFamilleString()+"\nis used: "+deck[i].isUsed());
		}
		return string;
	}
}
