package view;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.GameController;
import model.Game;


public class blackjackVueGUI extends blackjackVue implements ActionListener{

	private JFrame biblioJFrame;
	

	public blackjackVueGUI(Game model,GameController controller, int posX, int posY) {
		super(model, controller);
		
		//Construction de la fenetre
		biblioJFrame = new JFrame("Blackjack");	
		biblioJFrame.setSize(posX,posY);
		biblioJFrame.setVisible(true);
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void affiche(String string) {
		// TODO Auto-generated method stub
		
	}
}
