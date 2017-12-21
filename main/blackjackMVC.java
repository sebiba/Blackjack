package main;

import java.awt.EventQueue;

import controller.GameController;
import model.Game;
import view.BlackjackVue;
import view.BlackjackVueConsole;
import view.BlackjackVueGui;

public class blackjackMVC {
	public blackjackMVC() {
		Game model = new Game();
		
		GameController controlCli = new GameController(model);
		GameController controlGui = new GameController(model);
		
		BlackjackVue cli = new BlackjackVueConsole(model, controlCli);
		BlackjackVue gui = new BlackjackVueGui(model, controlGui);
		
		controlCli.addView(cli);
		controlGui.addView(gui);
		
		cli.menu();
	}
	
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
