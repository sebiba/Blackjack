package reseaux;

import java.io.IOException;
import java.util.Scanner;

import blackjack.Carte;
import blackjack.Deck;
import blackjack.Game;
import blackjack.Player;
import reseaux.Chat;


public class ChatConsole {
	/**
	 * fonction lancant la communication reseaux
	 * @param joueur local dela partie
	 * @throws IOException 
	 */
	public static void ChatConsole(blackjack.Player joueur) throws IOException{
		String eberge = blackjack.Game.enter("heberger vous la partie?(y/n): ");
		boolean isServer=false;
		String ip = "localhost";
		switch(eberge.toLowerCase()){
			case"y":isServer = (true);
					System.out.println("en attente du client...");
				break;
			case"n":isServer = (false);
					ip = blackjack.Game.enter("entrez un nom de pc pour la connection: ");
				break;
			default:isServer=false;
				break;
		}
		Scanner sc = new Scanner(System.in);
		Chat chat = new Chat( isServer , 12345,ip);
		/*if(isServer){
			String msg = chat.waitForMessage();
			System.out.println(msg);
		}
		
		
		String sl = joueur.getNom();
		chat.sendMessage(sl);
		
		sl = chat.waitForMessage();
		System.out.println(sl);
		*/
		if(isServer){
			Deck deckHeberge = new Deck();
			launch(joueur, deckHeberge);
			String[] msg= {joueur.getNom(),Integer.toString(joueur.getMiseAct()),"","","","","","","",""};
			for(int i=0;i<joueur.main.getNbrCartes();i++){
				msg[msg.length+1+i]=tabToString(joueur.main.getCartes()[i]);
			}
			System.out.println("sending message");
			chat.sendMessage(msg[0]);
			System.out.println("serveur send");
			
		}else{
			Deck deckClient = new Deck();
			String[] msg = chat.waitForMessage();
			System.out.println("client recoit: "+msg);
			launch(joueur,deckClient);
		}
	}
	
	/**
	 * fonction jouant une partie en reseaux 
	 * @param joueur locale de la partie
	 * @param deck utiliser localemnet
	 */
	public static void launch(Player joueur, Deck deck){
		boolean isNum = false;
		joueur.main.ajouteCarte(deck);//carte de base
		joueur.main.ajouteCarte(deck);//carte de base
		do{
			String mise = blackjack.Game.enter(joueur.getNom()+" vous avez "+joueur.getMoney()+"\ncombien voulez-vous misez?(0 pour rien miser)");//demande la mise a chaque joueur
			isNum= blackjack.Game.isNumeric(mise);
			if(isNum){
				joueur.mise(Integer.parseInt(mise));
			}
		}while(isNum==false);
	}
	
	/**
	 * fonction convertant une carte en String
	 * @param carte a convertir en String
	 * @return String de carte
	 */
	public static String tabToString(Carte carte){
		String tabString = null;
		tabString=carte.toString();
		return tabString;
	}

}
