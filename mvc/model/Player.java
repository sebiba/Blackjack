package model;

import javax.swing.JOptionPane;
import controller.GameController;
import model.Game;
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
	public Hand main = new Hand();
	/**
	 * constructeur sans argument de la classe
	 */
	public Player(String nom){
		this.money=1500;
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
	 * fonction permetant au joueur de piocher une carte du deck passé en parametre
	 * @param deck avec lequel joue le joueur
	 */
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
	public void addMoney(int money) {
		this.money += money;
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
	
	/**
	 * fonction retournant true si le joueur a finis la partie false si le joueur est encre en train de jouer
	 * @return true si le joueur a finis la manch
	 * @return false si le joueur est encore en train de jouer la manche
	 */
	public boolean isFin() {
		return fin;
	}
	
	/**
	 * fonction attribuant une valeur booleenne a fin
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
	public Hand getMain() {
		return main;
	}
	
	/**
	 * fonction attribuant une main au joueur
	 * @param main
	 */
	public void setMain(Hand main) {
		this.main = main;
	}
	
	/**
	 * fonction attribuant une valeur au portefeuille du joueur
	 * @param money int reprécentant une valeur pour le portefeuille du joueur
	 */
	public void setMoney(int money) {
		this.money = money;
	}
	
	/**
	 * fonction retournant un string avec les attribus du joueur
	 * @param joueur object joueur que l'on veut detailler
	 * @return String represantant le joueur
	 */
	public String toString(Player joueur){
		return(main.toString(joueur)+"\nnom: "+joueur.nom+"\nmoney: "+joueur.money);
	}
}
