package reseaux;

import java.io.IOException;
import java.util.Scanner;

import model.Carte;
import model.Deck;
import model.Game;
import model.Player;
import controller.GameController;
import reseaux.Chat;
import view.BlackjackVueConsole;


public class ChatConsole {
	GameController controller;
	Game model;
	BlackjackVueConsole view;
	/**
	 * fonction lancant la communication reseaux
	 * @param joueur local dela partie
	 * @throws IOException 
	 */
	public void ChatConsole(model.Player joueur) throws IOException{
		String eberge = view.input("heberger vous la partie?(y/n): ");
		boolean isServer=false;
		String ip = "localhost";
		int nbfin = 0;
		boolean status = false;
		switch(eberge.toLowerCase()){
			case"y":isServer = (true);
					System.out.println("en attente du client...");
				break;
			case"n":isServer = (false);
					ip = view.input("entrez un nom de pc pour la connection: ");
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
			//-----------------------------------------serveur---------------------------------------------
			Deck deckHeberge = new Deck();
			launch(joueur, deckHeberge);
			do{
				String[] msg= {joueur.getNom(),Integer.toString(joueur.getMiseAct()),"","","","","","","",""};
				for(int i=0;i<joueur.main.getNbrCartes();i++){
					msg[2+i]=tabToString(joueur.main.getCartes()[i]);
				}
				chat.sendMessage(msg[0]);
				chat.sendMessage(msg[1]);
				chat.sendMessage(msg[2]);
				chat.sendMessage(msg[3]);
				for(int i=0;i<4;i++){
					String rcv = chat.waitForMessage();
					System.out.println("message du client: "+rcv);
				}
				System.out.println("\n\n");
				System.out.println(joueur.toString(joueur));
				controller.pioche();
			}while(nbfin!=model.getMise());
			
		}else{
			//-------------------------------------------client-------------------------------------------------
			Deck deckClient = new Deck();
			String rcv[]={"","","","","",""};
			do{
			for(int i=0;i<4;i++){
				rcv[i] = chat.waitForMessage();
				System.out.println("client recoit: "+rcv[i]);
			}
			if(status==false){
				launch(joueur,deckClient);
			}else{
				System.out.println("\n\n");
				System.out.println(joueur.toString(joueur));
				controller.pioche();
			}
			status=true;
			String[] msg= {joueur.getNom(),Integer.toString(joueur.getMiseAct()),"","","","","","","",""};
			for(int i=0;i<joueur.main.getNbrCartes();i++){
				msg[2+i]=tabToString(joueur.main.getCartes()[i]);
			}
			chat.sendMessage(msg[0]);
			chat.sendMessage(msg[1]);
			chat.sendMessage(msg[2]);
			chat.sendMessage(msg[3]);
			}while(rcv[0]!="1"); // a revoir
		}
	}
	
	/**
	 * fonction jouant une partie en reseaux 
	 * @param joueur locale de la partie
	 * @param deck utiliser localemnet
	 */
	public void launch(Player joueur, Deck deck){
		boolean isNum = false;
		joueur.main.ajouteCarte(deck);//carte de base
		joueur.main.ajouteCarte(deck);//carte de base
		do{
			String mise = view.input(joueur.getNom()+" vous avez "+joueur.getMoney()+"\ncombien voulez-vous misez?(0 pour rien miser)");//demande la mise a chaque joueur
			if(controller.isNumeric(mise)){
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
