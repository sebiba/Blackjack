package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import model.Deck;
import model.Game;
import model.Player;
import view.BlackjackVue;
import reseaux.ChatConsole;

public class GameController {
	Game model; 
	BlackjackVue vue;
	ChatConsole reseaux;
	public int etat=0;//etat 0= crée le joueur  1=doit miser 2=doit choisir carte
	BufferedReader keyboard;
	
	public GameController(Game model) {
		this.model = model;
		keyboard = new BufferedReader(new InputStreamReader(System.in));
	}	
	
	public void addView(BlackjackVue vue) {
		this.vue = vue;
	}
	
	public void solo(ArrayList<String> nom){
		model.ClearJoueur();
		model.addJoueur(nom);
		setEtat(0);
	}
	public void MultiLocal(ArrayList<String> nom) {
		model.ClearJoueur();
		model.addJoueur(nom);
		setEtat(0);
	}

	/**
	 * fonction jouant une partie avec quelqu'un en reseaux
	 * @param nbJoueurs nombre de joueurs sur la partie
	 * @throws IOException
	 */
	public void multi(int nbJoueurs) throws IOException{
		ArrayList<String> nom = new ArrayList();
		nom.add(input("entrer le nom du joueur: "));
		model.ClearJoueur();
		model.addJoueur(nom);//instantiation d'un joueur
		reseaux.ChatConsole(model.getJoueur().get(0));//connection a l'autre
		
	}
	
	/**
	 * fonction calculant les score de chaque joueur de la partie
	 * @param tableau de joueur de la partie en cours
	 */
	public void result(ArrayList<Player> joueur){
		String rejoue="";
		for(int i=0;i<joueur.size();i++){
			System.out.println("nom: "+joueur.get(i).getNom()+"\tscore: "+joueur.get(i).getMain().getTot());
			if(model.getScore()[0]<joueur.get(i).getMain().getTot()){
				model.getScore()[0]=joueur.get(i).getMain().getTot();
				model.getScore()[1]=i;
			}
			model.reset(joueur.get(i));
		}
		vue.affiche("le gagnant est "+model.getJoueur().get(model.getScore()[1]).getNom());
		
		rejoue=input("\n\nVoulez vous jouer une autre partie (y/n) ");
		switch(rejoue.toLowerCase()){
		case "y":setEtat(0);
			break;
		case "n":vue.menu();
			break;
		default:System.out.println("mauvais choix...");
				pioche();
			break;
		}
	}
	
	public boolean ChecMise(String mise, Player joueur){
		if(isNumeric(mise)== true){
			if(joueur.getMoney()>=Integer.parseInt(mise)){
				model.addMise(Integer.parseInt(mise));
				vue.affiche("Vous avez misé "+mise+"€");
				return true;
			}else{
				vue.affiche("vous ne pouvez pas miser autant...");
				return false;
			}
		}else{
			vue.affiche("Vous n'avez pas rentré un nombre");
			return false;
		}
	}
	
	/**
	 * verifie si la String passée en parametre est numérique
	 * @param str String que l'on veut passer en parametre
	 * @return true si la String est constitué que de chiffre
	 * @return false si la String a un seul charactere non numérique
	 */
	public boolean isNumeric(String str){
		int isnum = 0;
		for(int i=0;i<str.length();i++){
			if(Character.isDigit(str.charAt(i))){
				isnum+=1;
			}
		}
		if(isnum==str.length()){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * fonction gerant la pioche de chaque joueur
	 * @param joueur devant piocher
	 * @param deck de la partie
	 */
	public void pioche(){
		ArrayList<Player> joueurs = model.getJoueur();
		Deck deck = model.getDeck();
		for(Player joueur : joueurs){
			if(joueur.isFin()==false){
				String newCarte=input(joueur.getNom()+" voulez vous une carte? (y/n) ");
				switch(newCarte.toLowerCase()){
				case "y":System.out.println("OK voici une carte");
						joueur.main.ajouteCarte(deck);
						setEtat(3);
						vue.update(null, null);
					break;
				case "n":joueur.setFin(true);
					break;
				default:System.out.println("mauvais choix...");
						pioche();
					break;
				}
			}
		}
	}
	
	/**
	 * fonction lisant les entrées dans la console
	 * @return String de ce que l'utilisateur à entré
	 */
	public String input(String texte){
		vue.affiche(texte);
		String entre = "";
		try {
			entre = keyboard.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entre;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}
}
