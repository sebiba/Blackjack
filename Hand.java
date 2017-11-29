package blackjack;

public class Hand{
	private int nbrCartes=0;
	private boolean depasse = false;
	private Carte cartes []=new Carte[12];
	private int as = 0;
	public Hand(){
		
	}
	public void ajouteCarte(Deck deck){
		cartes[nbrCartes]= deck.PiocheCarte();
		nbrCartes+=1;
	}
	public String toString(){
		String string="";
		int tot=0;
		for(int cpt=0;cpt<nbrCartes;cpt++){
			string+=("cartes: "+cartes[cpt]+"\n");
			tot+=cartes[cpt].getValeur();
		}
		int as =isas();
		if(as>this.as){this.as = as;}
		while(as>0 && tot>21){//remplace la valeur des as si le tot depasse 21 par palier
			tot-=10;
			as-=1;	
		}
		if(tot>21){
			System.out.println("FINIS");
		}
		string+=("total: "+tot+"\n");
		return string;
	}
	
	public int isas(){
		int cptas=0;
		for(int cpt=0;cpt<nbrCartes;cpt++){
			if(cartes[cpt].getValeur()==1){
				cptas+=1;
			}
		}
		if(cptas>this.as){
			cptas=cptas-this.as;
			return cptas;
		}
		return 0;
	}
}
