package view;

import java.util.Observer;

import model.Game;
import controller.GameController;

public abstract class BlackjackVue implements Observer{
	
	protected Game model;
	protected GameController controller;
	/**
	 * constructeur de vue liant un model passé en parametre au model global et liant un controller passé en parametre au controller global
	 * @param model
	 * @param controller
	 */
	public BlackjackVue(Game model,GameController controller) {
		this.model = model;
		this.controller = controller;
		// TODO : Connexion entre la vue et le modele
		this.model.addObserver(this);
	}
	
	public abstract void affiche(String string) ;
	public abstract void menu();
	public abstract String input(String texte);
}