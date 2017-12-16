package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Game;
import model.Player;
import view.blackjackVue;

public class GameController {
	Game model1; 
	Player model;
	blackjackVue vue;
	public GameController(Game model) {
		this.model1 = model;
	}


/*	public void emprunteLivre(int numLivre) {
		if(model1.emprunte(numLivre)){
			vue.affiche("Emprunt Livre "+numLivre+" OK");
		}else vue.affiche("Emprunt Livre "+numLivre+" impossible");
		//TODO 
	}

	public void rendreLivre(int numLivre) {
		//TODO
		model1.rendre(numLivre);
		vue.affiche("Livre "+numLivre+" disponible");
	}*/
	
	
	public boolean mise(String mise){
		System.out.println("test test");
		if(isNumeric(mise)){
			if(model.getMoney()<Integer.parseInt(mise)){
				model1.addMise(Integer.parseInt(mise));
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
	 * fonction lisant les entrées dans la console
	 * @return String de ce que l'utilisateur à entré
	 */
	public static String enter(String texte){
		System.out.print(texte);
		String entre = "";
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		try {
			entre = keyboard.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entre;
	}
	
	/**
	 * verifie si la String passée en parametre est numérique
	 * @param str String que l'on veut passer en parametre
	 * @return true si la String est constitué que de chiffre
	 * @return false si la String a un seul charactere non numérique
	 */
	public static boolean isNumeric(String str){
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


	public void addView(blackjackVue vue) {
		this.vue = vue;
		
	}

}
