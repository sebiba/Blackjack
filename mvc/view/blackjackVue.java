package view;

import java.util.Observer;

import model.Game;
import controller.GameController;

public abstract class blackjackVue implements Observer{
	
	protected Game model;
	protected GameController controller;
	
	blackjackVue(Game model,
			GameController controller) {
		this.model = model;
		this.controller = controller;
		// TODO : Connexion entre la vue et le modele
		model.addObserver(this);
	}

	public abstract void affiche(String string) ;
}