import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * JPanel correspondant à une partie, l'utilisateur clique sur des lettres pour essayer de deviner le mot
 * @author loick
 *
 */

public class EcranJeu extends JPanel{

	private Fenetre f;
	
	protected JLabel scoreLabel;
	
	protected Panneau score;
	protected Panneau keyboard;
	protected Grille g;
	
	
	
	
	/**
	 * Créé un nouveau JPanel de jeu (Une fois)
	 * @param f Fenetre pour ajouter les ActionListener
	 * @throws IOException
	 */
	public EcranJeu(Fenetre f)
	{
		this.f=f;
		this.setFocusable(true);
		
		//this.setLayout(new BorderLayout());
		
		score = new Panneau();
		scoreLabel=new JLabel("Score: 0");
		
		score.add(scoreLabel);
		
		g = new Grille();
		
		this.add(score);
		this.add(g);
		//this.add(new Case());
		
        
		
		
	}
	
	/**
	 * Appelé à chaque début de partie, on remet chaque bouton à 0, 
	 * change le mot et remet le compteur de vie
	 */
	public void reinit()
	{
		
	}
	
	
	public void gestion(KeyEvent e){
		int keyCode=e.getKeyCode();
	    switch(keyCode) { 
	        case KeyEvent.VK_UP:
	        	System.out.println("up");
	        	this.g.deplacerCase(Direction.NORD);
	        	this.g.apparitionCase();
	            break;
	        case KeyEvent.VK_DOWN:
	        	System.out.println("down");
	        	this.g.deplacerCase(Direction.SUD);
	        	this.g.apparitionCase();
	            break;
	        case KeyEvent.VK_LEFT:
	        	System.out.println("left");
	        	this.g.deplacerCase(Direction.OUEST);
	        	this.g.apparitionCase();
	            break;
	        case KeyEvent.VK_RIGHT :
	        	System.out.println("right");
	        	this.g.deplacerCase(Direction.EST);
	        	this.g.apparitionCase();
	            break;
	     }
	}

}
