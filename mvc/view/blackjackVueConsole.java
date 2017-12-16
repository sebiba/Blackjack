package view;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import controller.GameController;
import model.Game;
import model.Player;



public class blackjackVueConsole extends blackjackVue implements Observer {
	protected Scanner sc;
	
	public blackjackVueConsole(Game model,
			GameController controller) {
		super(model, controller);
		update(null, null);
		sc = new Scanner(System.in);
		new Thread (new enter()).start();	
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(model);
		printJoueur();
		
	}
	

	private void printJoueur(){
		//affiche(model1.toString());
	}
	
	private class enter implements Runnable{
		public void run(){
			String entre = "";
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
			try {
				entre = keyboard.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/*private class ReadInput implements Runnable{
		public void run() {
			while(true){
				try{
					String c = sc.next();
					if(c.length()!=1){
						affiche("Format d'input incorrect");
						//printHelp();
					}
						
					int i = sc.nextInt();
					if(i<0 || i> 9){
						affiche("Numéro du livre incorrect");
						//printHelp(); 
					}
					switch(c){
						case "R" :
							//controller.rendreLivre(i);
							break;
						case "E" : 
							//controller.emprunteLivre(i);
							break;
						default : 
							affiche("Opération incorrecte");
							//printHelp();
					}
				}
				catch(InputMismatchException e){
					affiche("Format d'input incorrect");
					//printHelp();
				}
			}
		}
	}
*/

	@Override
	public void affiche(String string) {
		System.out.println(string);
		
	}

}