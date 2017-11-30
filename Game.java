package blackjack;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Game{
	private static int mise;
	private int nbJoueurs;
	public static void main(String[] args) {
		menu();
	}
	public static void solo(){
		Player joueur = new Player(); //instantiation d'un joueur
		Deck deck = new Deck();
		System.out.println("le croupier distribue les cartes...");
		joueur.main.ajouteCarte(deck);//carte de base
		joueur.main.ajouteCarte(deck);//carte de base
		System.out.println(joueur.main.toString(joueur));
		do{
			pioche(joueur, deck);
			System.out.println(joueur.toString(joueur));
		}while(joueur.isFin()==false);
		
		System.out.println("checking result...");
		reset(joueur);
		
	}
	public void multi(int nbJoueurs){

	}
	public static void MultiLocal(int nbJoueurs){
		Player joueur[] = new Player[nbJoueurs]; //instantiation d'un joueur
		Deck deck = new Deck();
		int cpt=0,nbfin = 0;
		for(int i=0;i<nbJoueurs;i++){
			joueur[i] = new Player();
			joueur[i].main.ajouteCarte(deck);//carte de base
			joueur[i].main.ajouteCarte(deck);//carte de base
		}
		System.out.println("le croupier distribue les cartes...");
		do{
			nbfin=0;
			System.out.println(joueur[cpt].toString(joueur[cpt]));
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
		for(int i=0;i<nbJoueurs;i++){
			System.out.println("nom: "+joueur[i].getNom()+"\tscore: "+joueur[i].main.getTot());
		}
	}
	public static void reset(Player joueur){
		joueur.setFin(false);
	}
	/**
	 * fonction
	 * @param choix
	 */
	public static void addMise(int choix){
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
		String menu = enter("1)règles du jeux\n2)partie en solo\n3)partie a plusieurs sur le même pc\n4)multi joueur en réseaux");
		//System.out.println(menu);
		switch(menu){
			case "1":ReadFiles("rules.txt");
					enter("appuyez sur une touche pour continuer");
					System.out.println("--------------------------------------");//a remplacer par un clear console
				break;
			case "2":solo();
				break;
			case "3":int nbrJoueur = Integer.parseInt(enter("combien de joueurs vont jouer?"));
					MultiLocal(nbrJoueur);
				break;
			case "4":
				break;
			default:System.out.println("ce n'est pas un choix valable.");
				break;
		}
		menu();
	}
	/**
	 * fonction lisant les entrées dans la console
	 * @return String de ce que l'utilisateur à entré
	 */
	public static String enter(String texte){
		System.out.println(texte);
		String entre = "";
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		try {
			entre = keyboard.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entre;
	}
	
	public static void pioche(Player joueur, Deck deck){
		String newCarte=enter("voulez vous une carte? (y/n) ");
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
	public static int getMise() {
		return mise;
	}
	public static void setMise(int mise) {
		Game.mise = mise;
	}
	public int getNbJoueurs() {
		return nbJoueurs;
	}
	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}
	
	
}
