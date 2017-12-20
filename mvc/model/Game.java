package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import controller.GameController;

public class Game extends Observable{
	GameController controller;
	ArrayList<Player> joueurs = new ArrayList<Player>();
	Deck deck = new Deck();
	private int mise;
	private int nbJoueurs;
	static int score[] ={0,0};//score[0]==score  score[1]=numéro du joueur gagnant;
	
	public Game(){
		//menu();
	}
	
	/**
	 * fonction jouant plusieur joueurs sur le meme pc
	 * @param nbJoueurs de joueurs dans la partie
	 */
	/*public void MultiLocal(int nbJoueurs){
		this.joueurs.clear();
		for(int x=0;x<nbJoueurs;x++){
			//this.joueur.add(new Player(controller.input("entrez le nom du joueur: "))); //instantiation d'un joueur
			this.joueurs.get(x).toString(this.joueurs.get(x));
			String mise;
			do{
				mise = enter(joueur.get(x).getNom()+" vous avez "+joueur.get(x).getMoney()+"\ncombien voulez-vous misez?(0 pour rien miser)");//demande la mise a chaque joueur
			}while(!(mise(mise,joueur.get(x))));
			joueurs.get(x).main.ajouteCarte(deck);//carte de base
			joueurs.get(x).main.ajouteCarte(deck);//carte de base
		}
		int cpt=0,nbfin = 0;
		
		setChanged();
		notifyObservers();
		//vue.affiche("le croupier distribue les cartes...");
		do{
			nbfin=0;
			System.out.println(joueurs.get(cpt).toString(joueurs.get(cpt)));
			System.out.println("mise en jeu :"+mise);
			//pioche(joueurs.get(cpt), deck);
			for(int i=0;i<nbJoueurs;i++){
				if(joueurs.get(i).isFin()==true){
					nbfin+=1;
				}
			}
			if(cpt+1==nbJoueurs){
				cpt=0;
			}else{
				cpt++;
			}
		}while(nbfin!=nbJoueurs);
		
		System.out.println("checking result...");
		result(joueurs);
		System.out.println("le gagnant de cette partie est "+joueurs.get(score[1]).getNom());
		joueurs.get(score[1]).addMoney(mise);
	}*/

	/**
	 * reset les parametres afin de bien recommencer une nouvelle partie
	 * @param joueur
	 */
	public void reset(Player joueur){
		joueur.setFin(false);
		setMise(0);
		joueur.main.setAsreturn(0);
	}
	
	/**
	 * fonction ajoutant la mise passée en parametre de chaque joueur a la mise de la partie 
	 * @param choix mise du joueur
	 */
	public void addMise(int choix){
		this.mise+=choix;
	}		
	
	/**
	 * fonction retournant la mise de la partie
	 * @return la mise de la partie
	 */
	public int getMise() {
		return mise;
	}
	
	/**
	 * fonction settant a mise la valeur passé en parametre
	 * @param valeur que l'on veut afecter a la mise de la partie
	 */
	public void setMise(int mise) {
		this.mise = mise;
	}
	
	/**
	 * fonction retournant le nombre de joueur inscrit^a la partie en cours
	 * @return le nombre de joueur inscrit a la partie en cours
	 */
	public int getNbJoueurs() {
		return nbJoueurs;
	}
	
	/**
	 * fonction settant a nbJoueurs la valeur passé en parametre
	 * @param nombre de joueurs que l'on veut inscrire a la partie
	 */
	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}
	
	public ArrayList<Player> getJoueur() {
		return joueurs;
	}

	public void setJoueur(ArrayList<Player> joueur) {
		this.joueurs = joueur;
	}
	
	public void addJoueur(ArrayList<String> joueur){
		for(String indiv : joueur){
			this.joueurs.add(new Player(indiv));
			this.joueurs.get(joueur.indexOf(indiv)).getMain().ajouteCarte(getDeck());
			this.joueurs.get(joueur.indexOf(indiv)).getMain().ajouteCarte(getDeck());
		}
	}
	
	public void ClearJoueur(){
		this.joueurs.clear();
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public int[] getScore() {
		return score;
	}

	public void setScore(int[] score) {
		Game.score = score;
	}
	
}
