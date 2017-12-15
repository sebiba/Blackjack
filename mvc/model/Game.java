package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Observable;

import controller.GameController;
import view.blackjackVue;

public class Game extends Observable{
	static GameController controller;
	static blackjackVue vue;
	private static int mise;
	private static int nbJoueurs;
	static int score[] ={0,0};//score[0]==score  score[1]=numéro du joueur gagnant;
	public static void main(String[] args) {
		menu();
	}
	
	/**
	 * fonction jouant une partie avec un seul joueur
	 */
	public static void solo(){
		Player joueur = new Player(); //instantiation d'un joueur
		Deck deck = new Deck();
		joueur.main.ajouteCarte(deck);//carte de base
		joueur.main.ajouteCarte(deck);//carte de base
		String mise;
		do{
			mise = controller.enter(joueur.getNom()+" vous avez "+joueur.getMoney()+"\ncombien voulez-vous misez?(0 pour rien miser)");//demande la mise a chaque joueur
			controller.mise(mise);
		}while(!(controller.mise(mise)));
		vue.affiche("le croupier distribue les cartes...");
		System.out.println(joueur.main.toString(joueur));
		do{
			pioche(joueur, deck);
			System.out.println(joueur.toString(joueur));
		}while(joueur.isFin()==false);
		
		vue.affiche("checking result...");
		//result(joueur);
		reset(joueur);
		
	}
	
	/**
	 * fonction jouant une partie avec quelqu'un en reseaux
	 * @param nbJoueurs nombre de joueurs sur la partie
	 * @throws IOException
	 */
	public static void multi(int nbJoueurs) throws IOException{
		Player joueur= new Player();
		reseaux.ChatConsole.ChatConsole(joueur);//connection a l'autre
		
	}
	
	/**
	 * fonction jouant plusieur joueurs sur le meme pc
	 * @param nbJoueurs de joueurs dans la partie
	 */
	public static void MultiLocal(int nbJoueurs){
		Player joueur[] = new Player[nbJoueurs]; //instantiation d'un joueur
		Deck deck = new Deck();
		int cpt=0,nbfin = 0;
		for(int i=0;i<nbJoueurs;i++){
			joueur[i] = new Player();//instantiation de nbJoueurs joueur
			String mise;
			do{
				mise = controller.enter(joueur[i].getNom()+" vous avez "+joueur[i].getMoney()+"\ncombien voulez-vous misez?(0 pour rien miser)");//demande la mise a chaque joueur
				controller.mise(mise);
			}while(!(controller.mise(mise)));
			joueur[i].main.ajouteCarte(deck);//carte de base
			joueur[i].main.ajouteCarte(deck);//carte de base
		}
		vue.affiche("le croupier distribue les cartes...");
		do{
			nbfin=0;
			System.out.println(joueur[cpt].toString(joueur[cpt]));
			System.out.println("mise en jeu :"+mise);
			pioche(joueur[cpt], deck);
			for(int i=0;i<nbJoueurs;i++){
				if(joueur[i].isFin()==true){
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
		System.out.println("le gagnant de cette partie est "+joueur[score[1]].getNom());
		joueur[score[1]].addMoney(mise);
	}
	
	/**
	 * reset les parametres afin de bien recommencer une nouvelle partie
	 * @param joueur
	 */
	public static void reset(Player joueur){
		joueur.setFin(false);
		setMise(0);
		joueur.main.setAsreturn(0);
	}
	
	/**
	 * fonction ajoutant la mise passée en parametre de chaque joueur a la mise de la partie 
	 * @param choix mise du joueur
	 */
	public void addMise(int choix){
		mise+=choix;
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
	public static void menu(){	 
		System.out.println("Bienvenu sur la table de blackjack...");
		int nbrJoueur=0;
		String menu = controller.enter("1)règles du jeux\n2)partie en solo\n3)partie a plusieurs sur le même pc\n4)multi joueur en réseaux");
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
	public static void pioche(Player joueur, Deck deck){
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
	
	/**
	 * fonction calculant les score de chaque joueur de la partie
	 * @param tableau de joueur de la partie en cours
	 */
	public static void result(Player joueur[]){
		for(int i=0;i<nbJoueurs;i++){
			System.out.println("nom: "+joueur[i].getNom()+"\tscore: "+joueur[i].main.getTot());
			reset(joueur[i]);
			if(score[0]<joueur[i].main.getTot()){
				score[0]=joueur[i].main.getTot();
				score[1]=i;
			}
		}
	}
	
	
	/**
	 * fonction retournant la mise de la partie
	 * @return la mise de la partie
	 */
	public static int getMise() {
		return mise;
	}
	
	/**
	 * fonction settant a mise la valeur passé en parametre
	 * @param valeur que l'on veut afecter a la mise de la partie
	 */
	public static void setMise(int mise) {
		Game.mise = mise;
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
	
	
}
