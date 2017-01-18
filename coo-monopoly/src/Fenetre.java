import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


/**
 * 
 * @author loick
 * Classe principale contenant les 4 JFrames correspondant aux écrans de jeux
 *
 */

public class Fenetre extends JFrame {
	
	/**
	 * Initie la JFrame, puis lance le jeu du pendu
	 * @throws IOException 
	 */
	
	protected EcranJeu lePanneau;
	protected EcranFinDePartie lePanneau2;
	protected EcranStart lePanneau3;
	protected EcranRegle lePanneau4;
	
	/**
	 * Constructeur, initialise les panneaux en leur associant la fenetre 
	 * (pour pouvoir leur associer les listener)
	 * @throws IOException
	 */
	public Fenetre(){
		this.setTitle("Monopoly");
		this.setSize(1200, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setFocusable(false);
		
		
		lePanneau= new EcranJeu(this);
		lePanneau2 = new EcranFinDePartie(this);
		lePanneau3 = new EcranStart(this);
		lePanneau4 = new EcranRegle(this);
		
		this.add(lePanneau3);
		
		lePanneau.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				Fenetre.this.lePanneau.gestion(e);
			}
		});
		
		
		
		this.setVisible(true);
		//this.pack();
		
	}

	/**
	 * Réinitialise un écran de jeu (Toutes les cases à zéro sauf une)
	 */
	public void initFenetreEcranJeu()
	{
		lePanneau.reinit();
		afficherPanneau(lePanneau);
		
	}
	
	/**
	 * Méthode générique affichant un panneau sur la fenêtre
	 * @param p
	 */
	public void afficherPanneau(JPanel p)
	{
		getContentPane().removeAll();
		getContentPane().repaint();
		getContentPane().add(p);
		getContentPane().validate();
		this.repaint();
	}
	
	
	public static void main(String[] args) throws IOException {
		Fenetre f = new Fenetre();
		
	}


}