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
	Player model;
	ArrayList<Player> joueur = new ArrayList<Player>();
	private int mise;
	private int nbJoueurs;
	static int score[] ={0,0};//score[0]==score  score[1]=numéro du joueur gagnant;
	
	public Game(){
		menu();
	}
	
	/**
	 * fonction jouant une partie avec un seul joueur
	 */
 	public void solo(){
		Deck deck = new Deck();
		joueur.add(new Player()); //instantiation d'un joueur
		joueur.get(0).getMain().ajouteCarte(deck);//carte de base
		joueur.get(0).getMain().ajouteCarte(deck);//carte de base
		String mise;
		boolean test=false;
		do{
			mise = controller.enter(joueur.get(0).getNom()+" vous avez "+joueur.get(0).getMoney()+"\ncombien voulez-vous misez?(0 pour rien miser)");//demande la mise a chaque joueur
		}while(mise(mise, joueur.get(0)));
		setChanged();
		notifyObservers();
		//vue.affiche("le croupier distribue les cartes...");
		System.out.println(joueur.get(0).getMain().toString(joueur.get(0)));
		do{
			pioche(joueur.get(0), deck);
			System.out.println(joueur.get(0).toString(joueur.get(0)));
		}while(joueur.get(0).isFin()==false);
		
		System.out.println("checking result...");
		result(joueur);
		reset(joueur.get(0));
		
	}
	
	/**
	 * fonction jouant une partie avec quelqu'un en reseaux
	 * @param nbJoueurs nombre de joueurs sur la partie
	 * @throws IOException
	 */
	public void multi(int nbJoueurs) throws IOException{
		this.joueur.add(new Player()); //instantiation d'un joueur
		reseaux.ChatConsole.ChatConsole(joueur.get(0));//connection a l'autre
		
	}
	
	/**
	 * fonction jouant plusieur joueurs sur le meme pc
	 * @param nbJoueurs de joueurs dans la partie
	 */
	public void MultiLocal(int nbJoueurs){
		Deck deck = new Deck();
		for(int x=0;x<nbJoueurs;x++){
			this.joueur.add(new Player()); //instantiation d'un joueur
			String mise;
			do{
				mise = controller.enter(joueur.get(x).getNom()+" vous avez "+joueur.get(x).getMoney()+"\ncombien voulez-vous misez?(0 pour rien miser)");//demande la mise a chaque joueur
			}while(!(mise(mise,joueur.get(x))));
			joueur.get(x).main.ajouteCarte(deck);//carte de base
			joueur.get(x).main.ajouteCarte(deck);//carte de base
		}
		int cpt=0,nbfin = 0;
		
		setChanged();
		notifyObservers();
		//vue.affiche("le croupier distribue les cartes...");
		do{
			nbfin=0;
			System.out.println(joueur.get(cpt).toString(joueur.get(cpt)));
			System.out.println("mise en jeu :"+mise);
			pioche(joueur.get(cpt), deck);
			for(int i=0;i<nbJoueurs;i++){
				if(joueur.get(i).isFin()==true){
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
		result(joueur);
		System.out.println("le gagnant de cette partie est "+joueur.get(score[1]).getNom());
		joueur.get(score[1]).addMoney(mise);
	}

	/**
	 * reset les parametres afin de bien recommencer une nouvelle partie
	 * @param joueur
	 */
	public void reset(Player joueur){
		joueur.setFin(false);
		this.joueur.clear();
		setMise(0);
		joueur.main.setAsreturn(0);
	}
	
	/**
	 * fonction ajoutant la mise passée en parametre de chaque joueur a la mise de la partie 
	 * @param choix mise du joueur
	 */
	public void addMise(int choix){
		this.mise+=choix;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * fonction lisant et affichant le contenu d'un fichier texte
	 * @param nom du fichier texte que l'on veut lire
	 */
	public static void ReadFiles(String nom){
		try{
			InputStream flux= new FileInputStream(nom);
			InputStreamReader lecture= new InputStreamReader(flux);
			BufferedReader buff= new BufferedReader(lecture);
			String ligne;
			while((ligne=buff.readLine())!= null){
				System.out.println(ligne);
			}
			buff.close();
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
	/**
	 * gère le menu en mode console
	 */
	public void menu(){	 
		System.out.println("Bienvenu sur la table de blackjack...");
		int nbrJoueur=0;
		String menu =controller.enter("1)règles du jeux\n2)partie en solo\n3)partie a plusieurs sur le même pc\n4)multi joueur en réseaux");
		//System.out.println(menu);
		switch(menu){
			case "1":ReadFiles("rules.txt");
					controller.enter("appuyez sur une touche pour continuer");
					System.out.println("--------------------------------------");//a remplacer par un clear console
				break;
			case "2":solo();
				break;
			case "3":nbrJoueur = Integer.parseInt(controller.enter("combien de joueurs vont jouer?"));
					MultiLocal(nbrJoueur);
				break;
			case "4":nbrJoueur=2;
					try {
						multi(nbrJoueur);
					} catch (IOException e) {
						e.printStackTrace();
					}
				break;
			default:System.out.println("ce n'est pas un choix valable.");
				break;
		}
		menu();
	}
	
	
	/**
	 * fonction gerant la pioche de chaque joueur
	 * @param joueur devant piocher
	 * @param deck de la partie
	 */
	public void pioche(Player joueur, Deck deck){
		if(joueur.isFin()==false){
			String newCarte=controller.enter("voulez vous une carte? (y/n) ");
			switch(newCarte.toLowerCase()){
			case "y":System.out.println("OK voici une carte");
					joueur.main.ajouteCarte(deck);
				break;
			case "n":joueur.setFin(true);
				break;
			default:System.out.println("mauvais choix...");
					pioche(joueur, deck);
				break;
			}
		}
	}
	
	public boolean mise(String mise, Player joueur){
		if(isNumeric(mise)== true){
			if(joueur.getMoney()<Integer.parseInt(mise)){
				addMise(Integer.parseInt(mise));
				setChanged();
				notifyObservers();
				//vue.affiche("Vous avez misé "+mise+"€");
				return true;
			}else{
				setChanged();
				notifyObservers();
				//vue.affiche("vous ne pouvez pas miser autant...");
				return false;
			}
		}else{
			setChanged();
			notifyObservers();
			//vue.affiche("Vous n'avez pas rentré un nombre");
			return false;
		}
	}
	
	/**
	 * fonction calculant les score de chaque joueur de la partie
	 * @param tableau de joueur de la partie en cours
	 */
	public void result(ArrayList<Player> joueur){
		for(int i=0;i<joueur.size();i++){
			System.out.println("nom: "+joueur.get(i).getNom()+"\tscore: "+joueur.get(i).getMain().getTot());
			reset(joueur.get(i));
			if(score[0]<joueur.get(i).getMain().getTot()){
				score[0]=joueur.get(i).getMain().getTot();
				score[1]=i;
			}
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
		return joueur;
	}

	public void setJoueur(ArrayList<Player> joueur) {
		this.joueur = joueur;
	}
	
	
}
