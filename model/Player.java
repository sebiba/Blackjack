package model;

import javax.swing.JOptionPane;
/**
 * groupe 16 Blackjack
 * @author S�bastien Blacks
 *cette classe repr�sente un joueur de blackjack ayant de l'argent un nom et une mise
 */

public class Player{
	private int money;
	private boolean fin=false;
	private boolean win;
	private String nom;
	private int miseAct;
	public Hand hand = new Hand();

	/**
	 * constructeur de la classe prenant en argument le nom du joueur � cr�er
	 */
	public Player(String nom){
		this.money=1500;
		this.win=false;
		this.nom=nom;
		this.miseAct=0;	
	}
	
	public Player(int money, String nom){
		this.money=money;
		this.win=false;
		this.nom=nom;
		this.miseAct=0;	
	}
	
	
	/**
	 * fonction verifiant si la mise voulue est plus grande que l'argent du joueur et traite la mise du joueur
	 * @param choix montant voulu de mise
	 */
	public void mise(int choix){
		if(choix<=this.money){
			//model.addMise(choix);
			this.money=this.money-choix;
			this.miseAct=choix;
		}else{
			System.out.println("vous ne pouvez pas misez ce montant...");
			int rechoix =Integer.parseInt(JOptionPane.showInputDialog("entrez une mise"));
			mise(rechoix);
		}
	}
	
	/**
	 * fonction permetant au joueur de piocher une carte du deck pass� en parametre
	 * @param deck avec lequel joue le joueur
	 */
	public void recoitCarte(Deck deck){
		this.hand.ajouteCarte(deck);
	}
	
	/**
	 * retourne combien possede le joueur
	 * @return
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * atribue un montant d'argent au joueur
	 * @param money 
	 */
	public void addMoney(int money) {
		this.money += money;
	}
	
	/**
	 * fonction attribuant une valeur au portefeuille du joueur
	 * @param money int repr�centant une valeur pour le portefeuille du joueur
	 */
	public void setMoney(int money) {
		this.money = money;
	}

	/**
	 * @return true si le joueur a gagn� la manche 
	 * @return false si le joueur a perdu la manche
	 */
	public boolean isWin() {
		return win;
	}

	/**
	 * atribue un valeur bool�enne a win 
	 * @param win true si le joueur gagne false le joueur a perdu
	 */
	public void setWin(boolean win) {
		this.win = win;
	}
	
	/**
	 * retourne le nom du joueur
	 * @return
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * atribue un nom au joueur
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * fonction retournant true si le joueur a finis la partie false si le joueur est encore en train de jouer
	 * @return true si le joueur a finis la manch
	 * @return false si le joueur est encore en train de jouer la manche
	 */
	public boolean isFin() {
		return fin;
	}
	
	/**
	 * fonction attribuant une valeur booleenne a fin
	 * lorsque tt les joueurs on la valeur true la partie s'arrete
	 * @param fin boolean a attribuer a fin
	 */
	public void setFin(boolean fin) {
		this.fin = fin;
	}
	
	/**
	 * fonction retournant la mise actuel du joueur
	 * @return int mise du joueur
	 */
	public int getMiseAct() {
		return miseAct;
	}
	
	/**
	 * fonction attribuant une valeur a la mise actuel du joueur
	 * @param miseAct
	 */
	public void setMiseAct(int miseAct) {
		this.miseAct = miseAct;
	}
	
	/**
	 * fonction retournant l'object main du joueur
	 * @return object main associer au joueur
	 */
	public Hand getHand() {
		return hand;
	}
	
	/**
	 * fonction attribuant une main au joueur
	 * @param main
	 */
	public void setHand(Hand main) {
		this.hand = main;
	}
	
	/**
	 * fonction ressetant une main au joueur
	 * @param main
	 */
	public void resetHand() {
		this.hand = null;
		this.hand = new Hand();
	}
	
	/**
	 * fonction retournant un string avec les attribus du joueur
	 * @param joueur object joueur que l'on veut detailler
	 * @return String represantant le joueur
	 */
	public String toString(Player joueur){
		return(getHand().toString(joueur)+"\nnom: "+joueur.nom+"\nmoney: "+joueur.money);
	}
}
