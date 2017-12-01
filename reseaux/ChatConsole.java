package reseaux;

import java.io.IOException;
import java.util.Scanner;

import blackjack.Carte;
import blackjack.Deck;
import blackjack.Game;
import blackjack.Player;
import reseaux.Chat;


public class ChatConsole {
	public static void ChatConsole(blackjack.Player joueur) throws IOException{
		String eberge = blackjack.Game.enter("heberger vous la partie?(y/n): ");
		boolean isServer=false;
		String ip = "localhost";
		switch(eberge.toLowerCase()){
			case"y":isServer = (true);
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
		System.out.println("server: "+isServer);
		if(isServer){
			System.out.println("serveur");
			Deck deckHeberge = new Deck();
			launch(joueur, deckHeberge);
			String[] msg= {joueur.getNom(),Integer.toString(joueur.getMiseAct())};
			for(int i=0;i<joueur.main.getNbrCartes();i++){
				msg[msg.length+1+i]=tabToString(joueur.main.getCartes()[i]);
			}
			chat.sendMessage(msg);
			System.out.println("serveur send");
			
		}else{
			System.out.println("client");
			Deck deckClient = new Deck();
			String[] msg = chat.waitForMessage();
			System.out.println("client recoit");
			System.out.println(msg);
			launch(joueur,deckClient);
		}
	}
	
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
	
	public static String tabToString(Carte carte){
		String tabString = null;
		tabString=carte.toString();
		return tabString;
	}

}
