package model;

public class Hand{
	private int nbrCartes=0;
	private boolean depasse = false;
	private Carte cartes []=new Carte[12];
	private int as = 0;
	private int asreturn = 0;
	private int tot;
	
	/**
	 * fonction ajoutant une carte au joueur en appelant la fonction PiocheCarte de Deck
	 * @param object deck represantant le deck d'où on tire la carte
	 */
	public void ajouteCarte(Deck deck){
		cartes[nbrCartes]= deck.PiocheCarte();
		nbrCartes+=1;
	}
	
	/**
	 * fonction retournant un String avec les cartes du joueurs et calculant les as et total du joueur
	 * @param joueur
	 * @return String représantant la main
	 */
	public String toString(Player joueur){
		String string="";
		int tot=0;
		for(int cpt=0;cpt<nbrCartes;cpt++){
			string+=("cartes: "+cartes[cpt]+"\n");
			tot+=cartes[cpt].getValeur();
		}
		int as =isAs();
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
	
	/**
	 * fonction calculant si il y a un as en plus du tour précédant
	 * @return le nombre d'as en plus
	 */
	public int isAs(){
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
	
	
	/**
	 * retourne le nombre de carte que possede le joueur
	 * @return le nombre de carte que possede le joueur
	 */
	public int getNbrCartes() {
		return nbrCartes;
	}
	
	/**
	 * fonction settant a nbrCartes la valeur passé en parametre
	 * @param nouveau nbrCartes que l'on attribue a un joueurs 
	 */
	public void setNbrCartes(int nbrCartes) {
		this.nbrCartes = nbrCartes;
	}
	
	/**
	 * fonction retournant true si le joueur depasse 21 false si il est en dessous de 21
	 * @return true si le joueur depasse 21
	 * @return false si le joueur est en dessous de 21
	 */
	public boolean isDepasse() {
		return depasse;
	}
	
	/**
	 * fonction settant un boolean passé en paremetre a depasse
	 * @param depasse boolean
	 */
	public void setDepasse(boolean depasse) {
		this.depasse = depasse;
	}
	
	/**
	 *fonction retournant un tableau d'object carte 
	 * @return les cartes du joueurs
	 */
	public Carte[] getCartes() {
		return cartes;
	}
	
	/**
	 * fonction settant un tableau de cartes au joueur
	 * @param tableau de cartes a affecter au joueur
	 */
	public void setCartes(Carte[] cartes) {
		this.cartes = cartes;
	}
	
	/**
	 * fonction retournant la valeur de as
	 * @return la valeur de as
	 */
	public int getAs() {
		return as;
	}
	
	/**
	 * fonction settant a as la valeur passé en parametre
	 * @param valeur int as a atribué a as
	 */
	public void setAs(int as) {
		this.as = as;
	}
	
	/**
	 * fonction retournant le total des points du joueurs
	 * @return points du joueur
	 */
	public int getTot() {
		return tot;
	}
	
	/**
	 * fonction settant une valeur passée en parametre au total du joueur
	 * @param tot a attribue au jouer
	 */
	public void setTot(int tot) {
		this.tot = tot;
	}
	
	/**
	 * fonction retournant les as valant encore 11 et qui n'ont pas été reduit a 1
	 * @return la valeur de asreturn
	 */
	public int getAsReturn() {
		return asreturn;
	}
	
	/**
	 * fonction settant une valeur a asretun
	 * @param asreturn
	 */
	public void setAsReturn(int asReturn) {
		this.asreturn = asReturn;
	}
}
