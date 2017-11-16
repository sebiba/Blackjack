package blackjack;

import javax.swing.JOptionPane;

public class Player extends Game{
	private int money;
	private boolean win;
	private String nom;
	private int miseAct;
	
	public Player(){
		this.money=1500;
		this.win=false;
		this.nom=JOptionPane.showInputDialog("entrez un nom pour le joueur");
		this.miseAct=0;
	}
	public Player(int money, String nom){
		this.money=money;
		this.win=false;
		this.nom=nom;
		this.miseAct=0;
	}
	public void mise(int choix){
		if(choix<=money){
			super.addMise();
			this.money=this.money-choix;
			this.miseAct=choix;
		}else{
			System.out.println("vous ne pouvez pas misez ce montant...");
			int rechoix =Integer.parseInt(JOptionPane.showInputDialog("entrez une mise"));
			mise(rechoix);
		}
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public boolean isWin() {
		return win;
	}
	public void setWin(boolean win) {
		this.win = win;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
}
