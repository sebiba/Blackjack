package blackjack;

public class Hand{
	private int nbrCartes=0;
	private boolean depasse = false;
	private Carte cartes []=new Carte[12];
	private int as = 0;
	private int asreturn = 0;
	private int tot;
	public Hand(){
		
	}
	public void ajouteCarte(Deck deck){
		cartes[nbrCartes]= deck.PiocheCarte();
		nbrCartes+=1;
	}
	public String toString(Player joueur){
		String string="";
		int tot=0;
		for(int cpt=0;cpt<nbrCartes;cpt++){
			string+=("cartes: "+cartes[cpt]+"\n");
			tot+=cartes[cpt].getValeur();
		}
		int as =isas();
		as+=asreturn;
		tot+=10*as;
		while(as>0 && tot>21){//remplace la valeur des as si le tot depasse 21 par palier
			tot-=10;
			as-=1;	
		}
		if(as<0){
			this.asreturn = as;
		}
		if(as>this.as){
			this.as = as;
		}
		if(tot>21){
			System.out.println("FINIS");
			joueur.setFin(true);
		}
		string+=("total: "+tot+"\n");
		this.tot=tot;
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
	
	public int getNbrCartes() {
		return nbrCartes;
	}
	public void setNbrCartes(int nbrCartes) {
		this.nbrCartes = nbrCartes;
	}
	public boolean isDepasse() {
		return depasse;
	}
	public void setDepasse(boolean depasse) {
		this.depasse = depasse;
	}
	public Carte[] getCartes() {
		return cartes;
	}
	public void setCartes(Carte[] cartes) {
		this.cartes = cartes;
	}
	public int getAs() {
		return as;
	}
	public void setAs(int as) {
		this.as = as;
	}
	public int getTot() {
		return tot;
	}
	public void setTot(int tot) {
		this.tot = tot;
	}
	public int getAsreturn() {
		return asreturn;
	}
	public void setAsreturn(int asreturn) {
		this.asreturn = asreturn;
	}
}
