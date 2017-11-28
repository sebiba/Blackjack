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
		System.out.print("le croupier distribue les cartes...");
		pioche(joueur, deck);
		joueur.toString();
		
	}
	public void multi(int nbJoueurs){
		
	}
	public void MultiLocal(int nbJoueurs){
		
	}
	public void reset(){
		
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
		System.out.println("1)règles du jeux\n2)partie en solo\n3)partie a plusieurs sur le même pc\n4)multi joueur en réseaux");
		String menu = enter();
		//System.out.println(menu);
		switch(menu){
			case "1":ReadFiles("rules.txt");
					System.out.println("appuyez sur une touche pour continuer");
					enter();
					System.out.println("--------------------------------------");//a remplacer par un clear console
				break;
			case "2":solo();
				break;
			case "3":
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
	public static String enter(){
		String entre = "";
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		try {
			entre = keyboard.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entre;
	}
	
	public static void pioche(Player joueur, Deck deck){
		System.out.println("voulez vous une carte? (y/n)");
		String newCarte=enter();
		switch(newCarte.toLowerCase()){
		case "y":System.out.println("OK voici une carte");
				joueur.main.ajouteCarte(deck);
			break;
		case "n":System.out.println("check result");
			break;
		default:System.out.println("mauvais choix...");
				pioche(joueur, deck);
			break;
		}
	}
}
