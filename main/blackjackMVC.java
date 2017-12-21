package main;

import java.awt.EventQueue;

import controller.GameController;
import model.Game;
import view.BlackjackVue;
import view.BlackjackVueConsole;
import view.BlackjackVueGui;

public class blackjackMVC {
	/**
	 * constructeur initialisant 
	 * un model
	 * deux controlleurs pour chaque vues
	 * une vue console prenant en parametre le model et un des controlleurs
	 * une vue graphique prenant en parametre le model et un des controlleurs
	 * lie les deux controlleurs a leur vue respective
	 */
	public blackjackMVC() {
		Game model = new Game();
		
		GameController controlCli = new GameController(model);
		GameController controlGui = new GameController(model);
		
		BlackjackVue cli = new BlackjackVueConsole(model, controlCli);
		BlackjackVue gui = new BlackjackVueGui(model, controlGui);
		
		controlCli.addView(cli);
		controlGui.addView(gui);
		
		gui.menu();
		cli.menu();
	}
	
	/**
	 * fonction principale du jeux
	 * @param args arguments donnés au programme
	 */
	public static void main(String args[]) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.

		new blackjackMVC();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new blackjackMVC();
			}
		});
	}
}
