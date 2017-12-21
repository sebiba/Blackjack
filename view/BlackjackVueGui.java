package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import controller.GameController;
import model.Game;
import model.Player;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.SystemColor;

public class BlackjackVueGui extends BlackjackVue implements Observer{
	JFrame f = new JFrame("blackjack");
	JPanel panel;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem menuItem;
	JRadioButtonMenuItem rbMenuItem;
	private JRadioButtonMenuItem rbMenuItem_2;
	private JRadioButtonMenuItem rbMenuItem_1;
	JCheckBoxMenuItem cbMenuItem;
	JLabel NomJoueur;
	JLabel Argent;
	JTextArea txtrCarte;
	JButton btnMise;
	JButton btnFin;
	JButton btnCarte;
	ArrayList<String> joueurNom =new ArrayList<String>();
	
	public BlackjackVueGui(Game model, GameController controller){
		super(model, controller);
		f.setSize(400, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().setLayout(null);
		panel = new JPanel();
		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu);
		ButtonGroup group = new ButtonGroup();
		rbMenuItem = new JRadioButtonMenuItem("menu");
		rbMenuItem.setSelected(true);
		rbMenuItem.setMnemonic(KeyEvent.VK_R);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);
		
		rbMenuItem_1 = new JRadioButtonMenuItem("règles");
		rbMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.ReadFiles("rules.txt");
			}
		});
		rbMenuItem_1.setMnemonic(KeyEvent.VK_O);
		group.add(rbMenuItem_1);
		menu.add(rbMenuItem_1);

		rbMenuItem_2 = new JRadioButtonMenuItem("solo");
		rbMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				joueurNom.add(JOptionPane.showInputDialog("entrez un nom de joueur"));
				controller.solo(joueurNom);
				controller.setEtat(0);

				btnFin.setEnabled(true);
				btnMise.setEnabled(true);
				btnCarte.setEnabled(true);
				update(null, null);
			}
		});
		rbMenuItem_2.setMnemonic(KeyEvent.VK_O);
		group.add(rbMenuItem_2);
		menu.add(rbMenuItem_2);
		
		rbMenuItem_1 = new JRadioButtonMenuItem("multi local");
		rbMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				joueurNom.add(JOptionPane.showInputDialog("entrez un nom de joueur"));
				controller.solo(joueurNom);
				NomJoueur.setText(model.getJoueur().get(0).getNom());
			}
		});
		rbMenuItem_1.setMnemonic(KeyEvent.VK_O);
		group.add(rbMenuItem_1);
		menu.add(rbMenuItem_1);
		
		rbMenuItem_1 = new JRadioButtonMenuItem("multi");
		rbMenuItem_1.setMnemonic(KeyEvent.VK_O);
		group.add(rbMenuItem_1);
		menu.add(rbMenuItem_1);

		f.setJMenuBar(menuBar);
		menuGui();
		

	}
	
	public void menuGui(){
		ArrayList<String> joueurNom =new ArrayList<String>();
		panel.setBounds(0, 40, 382, 287);
		f.getContentPane().add(panel);
		panel.setLayout(null);
		
		btnFin = new JButton("fin");
		btnFin.setEnabled(false);
		btnFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.getJoueur().get(0).setFin(true);
				controller.setEtat(0);
				update(null, null);
			}
		});
		btnFin.setBounds(137, 249, 97, 25);
		panel.add(btnFin);
		
		btnCarte = new JButton("carte");
		btnCarte.setEnabled(false);
		btnCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.getJoueur().get(0).getMain().ajouteCarte(model.getDeck());
				controller.setEtat(0);
				update(null, null);
			}
		});
		btnCarte.setBounds(259, 249, 97, 25);
		panel.add(btnCarte);
		
		btnMise = new JButton("mise");
		btnMise.setEnabled(false);
		btnMise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setEtat(1);
				update(null, null);
			}
		});
		btnMise.setBounds(12, 249, 97, 25);
		panel.add(btnMise);
		
		NomJoueur = new JLabel("Nom");
		NomJoueur.setBounds(49, 34, 87, 16);
		panel.add(NomJoueur);
		
		Argent = new JLabel("argent");
		Argent.setBounds(49, 63, 87, 16);
		panel.add(Argent);
		
		txtrCarte = new JTextArea();
		txtrCarte.setBackground(SystemColor.menu);
		txtrCarte.setEditable(false);
		txtrCarte.setText("Carte");
		txtrCarte.setBounds(120, 109, 189, 113);
		panel.add(txtrCarte);
		f.setVisible(true);
		
	}
	
	public void printJoueur(Player joueur){
		NomJoueur.setText("Nom: "+joueur.getNom());
		Argent.setText("Argent: "+joueur.getMoney());
		txtrCarte.setText(joueur.getMain().toString(model.getJoueur().get(0)));
	}
	
	public String input(String string){
		return JOptionPane.showInputDialog(string);
	}
	@Override
	public void update(Observable o, Object arg) {
		int nbfin =0;
		//System.out.println(controller.getEtat());
		switch (controller.getEtat()){
			case 0: for(Player joueur : model.getJoueur()){
						printJoueur(joueur);
						if(joueur.isFin()==true){
							nbfin+=1;
						}
					}
					if(nbfin>=model.getNbJoueurs()){
						controller.result(model.getJoueur());
					}
				break;
			case 1:String mise;
					for(Player joueur : model.getJoueur()){
						do{
							mise = input(joueur.getNom()+" vous avez "+joueur.getMoney()+"\ncombien voulez-vous misez?(0 pour rien miser)");//demande la mise a chaque joueur
						}while(!(controller.ChecMise(mise, joueur)));
						controller.setEtat(0);
						update(null, null);
					}
				break;
		}
		
	}
	@Override
	public void affiche(String string) {
		System.out.println(string);
		
	}
	@Override
	public void menu() {
		// TODO Auto-generated method stub
		
	}
}
