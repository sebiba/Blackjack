package view;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import controller.GameController;
import model.Game;
import model.Player;



public class BlackjackVueConsole extends BlackjackVue implements Observer {
	BufferedReader keyboard;
	
	/**
	 * contructeur de la vue console
	 * @param model de la partie
	 * @param controller de la partie
	 */
	public BlackjackVueConsole(Game model, GameController controller) {
		super(model, controller);
		keyboard = new BufferedReader(new InputStreamReader(System.in));
		update(null, null);
	}
	
	/**
	 * fonction affichant le menu en mode console
	 */
	@Override
	public void menu(){	 
		String menu = input("1)règles du jeux\n2)partie en solo\n3)partie a plusieurs sur le même pc\n4)multi joueur en réseaux");
		//affiche(menu);
		int nbrJoueur=0;
		ArrayList<String> joueurNom =new ArrayList<String>();
		joueurNom.clear();
		switch(menu){
			case "1":controller.ReadFiles("rules.txt");
					input("appuyez sur une touche pour continuer");
					affiche("--------------------------------------");//a remplacer par un clear console
				break;
			case "2":joueurNom.add(input("entrer le nom du joueur: "));
					controller.solo(joueurNom);
					update(null, null);
					controller.pioche();
				break;
			case "3":ArrayList<String> nom = new ArrayList<String>();
					nbrJoueur =Integer.parseInt(input("combien de joueurs vont jouer?"));
					for(int cpt=0;cpt<nbrJoueur;cpt++){
						nom.add(input("entrer le nom du "+(cpt+1)+" joueur"));
					}
					controller.MultiLocal(nom);
					update(null, null);
					controller.pioche();
				break;
			case "4":nbrJoueur=2;
					try {
						controller.multi(nbrJoueur);
					} catch (IOException e) {
						e.printStackTrace();
					}
				break;
			default:affiche("ce n'est pas un choix valable.");
				break;
		}
		menu();
	}
	
	/**
	 * fonction lisant les entrées dans la console
	 * @param text a afficher en demandant une valeur
	 * @return String de ce que l'utilisateur à entré
	 */
	public String input(String texte){
		affiche(texte);
		String entre = "";
		try {
			entre = keyboard.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entre;
	}

	/**
	 * fonction gerant les objects a afficher a la console
	 * @param o 
	 * @param arg
	 */
	@Override
	public void update(Observable o, Object arg) {
		int nbfin =0;
		//System.out.println(controller.getEtat());
		switch (controller.getEtat()){
			case 0: for(Player joueur : model.getJoueur()){
						printJoueur(joueur);
					}
					controller.setEtat(1);
					update(null, null);
				break;
			case 1:String mise;
					for(Player joueur : model.getJoueur()){
						do{
							mise = input(joueur.getNom()+" vous avez "+joueur.getMoney()+"\ncombien voulez-vous misez?(0 pour rien miser)");//demande la mise a chaque joueur
						}while(!(controller.ChecMise(mise, joueur)));
					}
				break;
			case 2:	controller.pioche();
					for(int i=0;i<model.getNbJoueurs();i++){
						if(model.getJoueur().get(i).isFin()==true){
							nbfin+=1;
						}
					}
					if(nbfin>=model.getNbJoueurs()){
						controller.result(model.getJoueur());
					}
				break;
			case 3:for(Player joueur : model.getJoueur()){
						printJoueur(joueur);
					}
					controller.setEtat(2);
					update(null, null);
				break;
		}
		//this.affiche(this.model.toString());
		
	}
	
	/**
	 * fonction affichant les données des joueurs
	 * @param joueur dont on veut les données
	 */
	private void printJoueur(Player joueur){
		affiche(joueur.toString(joueur));
	}
	
	/**
	 * fonction affichant une String a l'écran
	 * @param texte que l'on affiche a la console
	 */
	@Override
	public void affiche(String string) {
		System.out.println(string);
		
	}

}