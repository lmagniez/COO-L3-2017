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
 * Classe principale contenant les 3 JFrames correspondant aux écrans de jeux
 * Implémente ActionListener et va gérer tous les cas rencontrés lors
 *
 */
public class Fenetre extends JFrame {
	
	/**
	 * Initie la JFrame, puis lance le jeu du pendu
	 * @throws IOException 
	 */
	
	protected EcranJeu lePanneau;
	protected EcranFinDePartie lePanneau2;
	protected EcranChoixDifficulte lePanneau3;
	
	/**
	 * Constructeur, initialise les panneaux en leur associant la fenetre 
	 * (pour pouvoir leur associer les listener)
	 * @throws IOException
	 */
	public Fenetre(){
		this.setTitle("Animation");
		this.setSize(425, 525);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setFocusable(false);
		
		
		lePanneau= new EcranJeu(this);
		lePanneau2 = new EcranFinDePartie(this);
		lePanneau3 = new EcranChoixDifficulte(this);
		
		this.add(lePanneau3);
		
		lePanneau.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				Fenetre.this.lePanneau.gestion(e);
			}
		});
		
		
		
		Thread thread=new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					//Fenetre.this.app.getA().calcul();
					lePanneau.repaint();
					try{
						
						Thread.sleep(100);
					}catch(InterruptedException e){
						//
					}
				}
				
			}
		});
		thread.start();
		
		
		this.setVisible(true);
		//this.pack();
		
	}

	/**
	 * Réinitialise un écran de jeu en lui changeant le nombre de vie 
	 * (choisit dans l'écran de difficulté)
	 * @param vieMax nombre de vie pour la nouvelle partie
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