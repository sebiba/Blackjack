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
import reseaux.ChatConsole;

public class GameController {
	Game model; 
	BlackjackVue vue;
	ChatConsole reseaux;
	public int etat=0;//etat 0= crée le joueur  1=doit miser 2=doit choisir carte
	BufferedReader keyboard;
	
	public GameController(Game model) {
		this.model = model;
		keyboard = new BufferedReader(new InputStreamReader(System.in));
	}	
	
	public void addView(BlackjackVue vue) {
		this.vue = vue;
	}
	
	public void solo(ArrayList<String> nom){
		model.ClearJoueur();
		model.addJoueur(nom);
		setEtat(0);
	}
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
	 * @param tableau de joueur de la partie en cours
	 */
	public void result(ArrayList<Player> joueur){
		String rejoue="";
		for(int i=0;i<joueur.size();i++){
			vue.affiche("nom: "+joueur.get(i).getNom()+"\tscore: "+joueur.get(i).getMain().getTot());
			if(model.getScore()[0]<joueur.get(i).getMain().getTot()){
				model.getScore()[0]=joueur.get(i).getMain().getTot();
				model.getScore()[1]=i;
			}
			model.reset(joueur.get(i));
		}
		vue.affiche("le gagnant est "+model.getJoueur().get(model.getScore()[1]).getNom());
		model.getJoueur().get(model.getScore()[1]).setMoney(model.getJoueur().get(model.getScore()[1]).getMoney()+model.getMise());
				
		rejoue=vue.input("\n\nVoulez vous jouer une autre partie (y/n) ");
		switch(rejoue.toLowerCase()){
		case "y":setEtat(0);
				vue.update(null, null);
			break;
		case "n":vue.menu();
			break;
		default:System.out.println("mauvais choix...");
				pioche();
			break;
		}
	}
	
	public boolean ChecMise(String mise, Player joueur){
		if(isNumeric(mise)== true){
			if(joueur.getMoney()>=Integer.parseInt(mise)){
				model.addMise(Integer.parseInt(mise));
				joueur.setMoney(joueur.getMoney()-Integer.parseInt(mise));
				vue.affiche("Vous avez misé "+mise+"€");
				return true;
			}else{
				vue.affiche("vous ne pouvez pas miser autant...");
				return false;
			}
		}else{
			vue.affiche("Vous n'avez pas rentré un nombre");
			return false;
		}
	}
	
	/**
	 * verifie si la String passée en parametre est numérique
	 * @param str String que l'on veut passer en parametre
	 * @return true si la String est constitué que de chiffre
	 * @return false si la String a un seul charactere non numérique
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
	 * @param joueur devant piocher
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
						joueur.main.ajouteCarte(deck);
						setEtat(3);
						vue.update(null, null);
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
			while((ligne=buff.readLine())!= null){
				vue.affiche(ligne);
			}
			buff.close();
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
	/**
	 * fonction lisant les entrées dans la console
	 * @return String de ce que l'utilisateur à entré
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

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}
	
	
	/**
	 * fonction lancant la communication reseaux
	 * @param joueur local dela partie
	 * @throws IOException 
	 */
	public void ChatConsole(Player joueur) throws IOException{
		String eberge = vue.input("heberger vous la partie?(y/n): ");
		boolean isServer=false;
		String ip = "localhost";
		int nbfin = 0;
		boolean status = false;
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
		Scanner sc = new Scanner(System.in);
		Chat chat = new Chat( isServer , 12345,ip);
		if(isServer){
			//-----------------------------------------serveur---------------------------------------------
			launch(joueur, model.getDeck());
			do{
				for(int i=0;i<joueur.getMain().getNbrCartes();i++){
					chat.sendMessage(joueur.toString(joueur));
				}
				for(int i=0;i<4;i++){
					String rcv = chat.waitForMessage();
					System.out.println("message du client: "+rcv);
				}
				System.out.println("\n\n");
				System.out.println(joueur.toString(joueur));
				pioche();
			}while(nbfin!=model.getMise());
			
		}else{
			//-------------------------------------------client-------------------------------------------------
			Deck deckClient = new Deck();
			String rcv[]={"","","","","",""};
			String data[]={"nom: ","argent: ","carte: "};
			int cpt=0;
			do{
				rcv[0] = chat.waitForMessage();
				System.out.println(rcv[0]);
			if(status==false){
				launch(joueur,deckClient);
			}else{
				System.out.println("\n\n");
				System.out.println(joueur.toString(joueur));
				pioche();
			}
			status=true;
			String[] msg= {joueur.getNom(),Integer.toString(joueur.getMiseAct()),"","","","","","","",""};
			for(int i=0;i<joueur.getMain().getNbrCartes();i++){
				msg[2+i]=tabToString(joueur.getMain().getCartes()[i]);
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
		do{
			String mise = vue.input(joueur.getNom()+" vous avez "+joueur.getMoney()+"\ncombien voulez-vous misez?(0 pour rien miser)");//demande la mise a chaque joueur
			if(isNumeric(mise)){
				joueur.mise(Integer.parseInt(mise));
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
