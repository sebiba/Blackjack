package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Game;
import model.Player;
import view.blackjackVue;

public class GameController {
	Game model; 
	blackjackVue vue;
	public GameController(Game model) {
		this.model = model;
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
	public void addView(blackjackVue vue) {
		this.vue = vue;
		
	}

}
