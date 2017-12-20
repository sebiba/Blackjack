package main;

import java.awt.EventQueue;

import controller.GameController;
import model.Game;
import view.BlackjackVue;
import view.BlackjackVueConsole;

public class blackjackMVC {
	BlackjackVueConsole view;
	public blackjackMVC() {
		Game model = new Game();
		
		GameController controlCli = new GameController(model);
		
		BlackjackVue cli = new BlackjackVueConsole(model, controlCli);
		controlCli.addView(cli);
		cli.menu();
	}
	
	public static void main(String args[]) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.

		new blackjackMVC();
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				new blackjackMVC();
			}
		});*/
	}
}
