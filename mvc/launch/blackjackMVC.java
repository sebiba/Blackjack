package test;

import controller.GameController;
import model.Game;
import view.blackjackVue;
import view.blackjackVueConsole;
import view.blackjackVueGUI;

public class blackjackMVC {
	public blackjackMVC() {
		//Création du modèle
		Game model = new Game();
		//TODO

		//Création des contrôleurs : Un pour chaque vue
		//Chaque contrôleur doit avoir une référence vers le modèle pour pouvoir le commander
		GameController ctrlGUI = new GameController(model);
		GameController ctrlConsole = new GameController(model);
		//TODO
		
		//Création des vues.
		//Chaque vue doit connaître son contrôleur et avoir une référence vers le modèle pour pouvoir l'observer
		blackjackVue gui = new blackjackVueGUI(model, ctrlGUI, 200, 200);
		blackjackVue console = new blackjackVueConsole(model, ctrlConsole);
		//TODO
		
		//On donne la référence à la vue pour chaque contrôleur
		ctrlGUI.addView(gui);
		ctrlConsole.addView(console);
		//TODO
		
		
	}
	
	public static void main(String args[]) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new blackjackMVC();
			}
		});
	}
}
