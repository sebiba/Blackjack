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
	static int score[] ={0,0};//score[0]==score  score[1]=numéro du joueur gagnant;
	
	public Game(){
		//menu();
	}

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
		return joueurs.size();
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
