package model;

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
	static int score[] ={0,0};//score[0]==score  score[1]=numéro du joueur gagnant;

	/**
	 * reset les parametres afin de bien recommencer une nouvelle partie
	 * @param joueur a remettre a 0
	 */
	public void reset(Player joueur){
		joueur.setFin(false);
		setMise(0);
		joueur.getHand().setAsReturn(0);
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
	 * fonction settant a mise (valeur de la mise tot de la partie) la valeur passé en parametre
	 * @param valeur que l'on veut afecter a la mise de la partie
	 */
	public void setMise(int mise) {
		this.mise = mise;
	}
	
	/**
	 * fonction retournant le nombre de joueur inscrit â la partie en cours
	 * @return le nombre de joueur inscrit a la partie en cours
	 */
	public int getNbJoueurs() {
		return joueurs.size();
	}
	

	/**
	 * fonction retournant l'arraylist contenant les joueurs
	 * @return
	 */
	public ArrayList<Player> getJoueur() {
		return joueurs;
	}

	/**
	 * fonctionattribuant un arraylist a l'arraylist de joueurs
	 * @param joueur a mettre dans l'arraylist des joueurs inscrit
	 */
	public void setJoueur(ArrayList<Player> joueur) {
		this.joueurs = joueur;
	}
	
	/**
	 * fonction ajoutant un joueur dans l'arraylist des joueurs et donne deux cartes pour commencer la partie
	 * @param name arraylist des noms des nouveaux joueurs
	 */
	public void addJoueur(ArrayList<String> name){
		for(String indiv : name){
			this.joueurs.add(new Player(indiv));
			this.joueurs.get(name.indexOf(indiv)).getHand().ajouteCarte(getDeck());
			this.joueurs.get(name.indexOf(indiv)).getHand().ajouteCarte(getDeck());
		}
	}
	
	/**
	 * fonction remetant a 0 l'arraylist de joueurs
	 */
	public void ClearJoueur(){
		this.joueurs.clear();
	}

	/**
	 * fonction retournant l'objet deck
	 * @return le deck de la partie
	 */
	public Deck getDeck() {
		return deck;
	}

	/**
	 * fonction lian deck au deck passé en parametre
	 * @param deck
	 */
	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	/**
	 * fonction retournant le gagnant de la partie score[1] numéro du joueur score[0] score obtenu
	 * @return tableau du vainqueur
	 */
	public int[] getScore() {
		return score;
	}

	/**
	 * fonction settant un tableau au tableau de vainqueur
	 * @param score tableau que l'on veut attribuer au tableau de vainqueur
	 */
	public void setScore(int[] score) {
		Game.score = score;
	}
	
}
