package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import model.Carte;
import model.Deck;
import model.Game;
import model.Player;
import view.BlackjackVue;
import reseaux.Chat;

public class GameController {
	Game model; 
	BlackjackVue vue;
	public int etat=0;//etat 0= cr�e le joueur  1=doit miser 2=doit choisir carte
	BufferedReader keyboard;
	boolean reseau=false;
	
	/**
	 * constructeur du controller lian le model a une variable global et un bufferReader
	 * @param model utiliser dans le jeu
	 */
	public GameController(Game model) {
		this.model = model;
		keyboard = new BufferedReader(new InputStreamReader(System.in));
	}	
	
	/**
	 * fonction lian la vue a une variable global
	 * @param vue
	 */
	public void addView(BlackjackVue vue) {
		this.vue = vue;
	}
	
	/**
	 * fonction d�butant une partie de blackjack en solo.
	 * efface le contenu eventuel de la arrayList de joueurs.
	 * ajoute un joueur
	 * met l'�tat de la partie a 0 
	 * @param nom arraylist contenant un joueur
	 */
	public void solo(ArrayList<String> nom){
		model.ClearJoueur();
		model.addJoueur(nom);
		setEtat(0);
	}
	
	/**
	 * fonction d�butant une partie de blackjack en multijoueur local.
	 * efface le contenu eventuel de la arrayList de joueurs.
	 * ajoute les joueurs
	 * met l'�tat de la partie a 0 
	 * @param nom arraylist contenant des joueurs
	 */
	public void MultiLocal(ArrayList<String> nom) {
		model.ClearJoueur();
		model.addJoueur(nom);
		setEtat(0);
	}

	/**
	 * fonction jouant une partie avec quelqu'un en reseaux
	 * @param nbJoueurs nombre de joueurs sur la partie
	 * @throws IOException
	 */
	public void multi(int nbJoueurs) throws IOException{
		ArrayList<String> nom = new ArrayList();
		nom.add(input("entrer le nom du joueur: "));
		model.ClearJoueur();
		model.addJoueur(nom);//instantiation d'un joueur
		ChatConsole(model.getJoueur().get(0));//connection a l'autre
		
	}
	
	/**
	 * fonction calculant les score de chaque joueur de la partie
	 * @param arraylist de joueurs de la partie en cours
	 */
	public void result(ArrayList<Player> joueur){
		String rejoue="";
		for(int i=0;i<joueur.size();i++){
			vue.affiche("nom: "+joueur.get(i).getNom()+"\tscore: "+joueur.get(i).getHand().getTot());
			if(model.getScore()[0]<joueur.get(i).getHand().getTot()){
				model.getScore()[0]=joueur.get(i).getHand().getTot();
				model.getScore()[1]=i;
			}
		}
		vue.affiche("le gagnant est "+model.getJoueur().get(model.getScore()[1]).getNom());
		model.getJoueur().get(model.getScore()[1]).setMoney(model.getJoueur().get(model.getScore()[1]).getMoney()+model.getMise());
				
		rejoue=vue.input("\n\nVoulez vous jouer une autre partie (y/n) ");
		switch(rejoue.toLowerCase()){
		case "y":setEtat(0);
				vue.update(null, null);
				pioche();
			break;
		case "n":vue.menu();
			break;
		default:System.out.println("mauvais choix...");
				pioche();
			break;
		}
	}
	
	
	/**
	 * fonction verifiant que la mise entr�e par un joueur est un chiffre et que le joueur peu miser cette valeur
	 * @param valeur voulant �tre mis�e par le joueur
	 * @param joueur voulant miser
	 * @return true si le joueur peux miser la valeur voulue
	 * @return false si le joueur a rentr� une mise impossible
	 */
	public boolean ChecMise(String mise, Player joueur){
		if(isNumeric(mise)== true){
			if(joueur.getMoney()>=Integer.parseInt(mise)){
				model.addMise(Integer.parseInt(mise));
				joueur.setMoney(joueur.getMoney()-Integer.parseInt(mise));
				vue.affiche("Vous avez mis� "+mise+"�");
				return true;
			}else{
				vue.affiche("vous ne pouvez pas miser autant...");
				return false;
			}
		}else{
			vue.affiche("Vous n'avez pas rentr� un nombre");
			return false;
		}
	}
	
	/**
	 * verifie si la String pass�e en parametre est num�rique
	 * @param str String que l'on veut passer en parametre
	 * @return true si la String est constitu� que de chiffre
	 * @return false si la String a un seul charactere non num�rique
	 */
	public boolean isNumeric(String str){
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
	
	/**
	 * fonction gerant la pioche de chaque joueur
	 * @param deck de la partie
	 */
	public void pioche(){
		ArrayList<Player> joueurs = model.getJoueur();
		Deck deck = model.getDeck();
		for(Player joueur : joueurs){
			if(joueur.isFin()==false){
				String newCarte=input(joueur.getNom()+" voulez vous une carte? (y/n) ");
				switch(newCarte.toLowerCase()){
				case "y":System.out.println("OK voici une carte");
						joueur.getHand().ajouteCarte(deck);
						if(reseau==false){
							setEtat(3);
							vue.update(null, null);
						}
					break;
				case "n":joueur.setFin(true);
					break;
				default:System.out.println("mauvais choix...");
						pioche();
					break;
				}
			}
		}
	}
	
	/**
	 * fonction lisant et affichant le contenu d'un fichier texte
	 * @param nom du fichier texte que l'on veut lire
	 */
	public void ReadFiles(String nom){
		try{
			InputStream flux= new FileInputStream(nom);
			InputStreamReader lecture= new InputStreamReader(flux);
			BufferedReader buff= new BufferedReader(lecture);
			String ligne;
			String rules = "";
			while((ligne=buff.readLine())!= null){
				rules+=ligne;
				rules+="\n";
			}
			vue.affiche(rules);
			buff.close();
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
	/**
	 * fonction lisant les entr�es dans la console
	 * @return String de ce que l'utilisateur � entr�
	 */
	public String input(String texte){
		vue.affiche(texte);
		String entre = "";
		try {
			entre = keyboard.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entre;
	}

	/**
	 * fonction retournant la valeur de l'�tat de la partie
	 * @return 0= cr�e le joueur  
	 * @return 1=doit miser 
	 * @return 2=doit choisir carte
	 */
	public int getEtat() {
		return etat;
	}

	/**
	 * fonction permetant de donner une valeur pour l'�tat de la partie
	 * @param etat 0= cr�e le joueur  1=doit miser 2=doit choisir carte
	 */
	public void setEtat(int etat) {
		this.etat = etat;
	}
	
	
	/**
	 * fonction lancant la communication reseaux
	 * @param joueur local de la partie
	 * @throws IOException 
	 */
	public void ChatConsole(Player joueur) throws IOException{
		reseau = true;
		String eberge = vue.input("heberger vous la partie?(y/n): ");
		boolean isServer=false;
		String ip = "localhost";
		int nbfin = 0;
		switch(eberge.toLowerCase()){
			case"y":isServer = (true);
					vue.affiche("en attente du client...");
				break;
			case"n":isServer = (false);
					ip = vue.input("entrez un nom de pc pour la connection: ");
				break;
			default:isServer=false;
				break;
		}
		Chat chat = new Chat( isServer , 12345,ip);
		if(isServer){//-----------------------------------------serveur---------------------------------------------
			launch(joueur, model.getDeck());
			do{
				chat.sendMessage(joueur.toString(joueur));
				
				String rcv = chat.waitForMessage();
					
				vue.affiche("message du client: "+rcv);
					
				vue.affiche("\n\n"+joueur.toString(joueur));
				pioche();
			}while(nbfin!=model.getMise());
			
		}else{//-------------------------------------------client-------------------------------------------------
			String rcv="";
			String data[]={"nom: ","argent: ","carte: "};
			int cpt=0;
			launch(joueur,model.getDeck());
			do{
			rcv = chat.waitForMessage();
			vue.affiche(rcv);
			vue.affiche("\n\n"+joueur.toString(joueur));
			pioche();
			chat.sendMessage(joueur.toString(joueur));
			
			}while(rcv!="1"); // a revoir
		}
	}
	
	/**
	 * fonction jouant une partie en reseaux 
	 * @param joueur locale de la partie
	 * @param deck utiliser localemnet
	 */
	public void launch(Player joueur, Deck deck){
		boolean isNum = false;
		do{
			String mise = vue.input(joueur.getNom()+" vous avez "+joueur.getMoney()+"\ncombien voulez-vous misez?(0 pour rien miser)");//demande la mise a chaque joueur
			if(isNumeric(mise)){
				joueur.mise(Integer.parseInt(mise));
				isNum=true;
				break;
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
