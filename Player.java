package blackjack;

import blackjack.Game;
import javax.swing.JOptionPane;
/**
 * groupe 16 Blackjack
 * @author Sébastien Blacks
 *cette classe représente un joueur de blackjack ayant de l'argent un nom et une mise
 */

public class Player{
	private int money;
	private boolean fin=false;
	private boolean win;
	private String nom;
	private int miseAct;
	Hand main = new Hand();
	/**
	 * constructeur sans argument de la classe
	 */
	public Player(){
		this.money=1500;
		this.win=false;
		this.nom=Game.enter("entrez le nom du joueur: ");
		this.miseAct=0;
	}
	/**
	 * constructeur avec comme argument
	 * @param money l'argent donné a la personne en début de partie
	 * @param nom du joueur
	 */
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
		if(choix<=money){
			Game.addMise(choix);
			this.money=this.money-choix;
			this.miseAct=choix;
		}else{
			System.out.println("vous ne pouvez pas misez ce montant...");
			int rechoix =Integer.parseInt(JOptionPane.showInputDialog("entrez une mise"));
			mise(rechoix);
		}
	}
	
	public void recoitCarte(Deck deck){
		this.main.ajouteCarte(deck);
	}
	/**
	 * retourne la valeur de money
	 * @return
	 */
	public int getMoney() {
		return money;
	}
	/**
	 * atribue une valeur a money
	 * @param money 
	 */
	public void setMoney(int money) {
		this.money = money;
	}
	/**
	 * retourne true si le joueur a gagné la manche false si il a perdu
	 * @return
	 */
	public boolean isWin() {
		return win;
	}
	/**
	 * atribue un valeur booléenne a win 
	 * @param win true si gagnant false si perdant
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
	
	public boolean isFin() {
		return fin;
	}
	public void setFin(boolean fin) {
		this.fin = fin;
	}
	public String toString(Player joueur){
		return(main.toString(joueur)+"\nnom: "+joueur.nom+"\nmoney: "+joueur.money);
	}
}
