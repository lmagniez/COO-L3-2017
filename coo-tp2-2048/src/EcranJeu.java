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
import java.util.Random;

import javax.swing.Box;
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
	private JLabel title;
	private IA ia;
	
	protected Score score;
	protected JPanel menu;
	protected Grille g;
	
	
	
	/**
	 * Créé un nouveau JPanel de jeu (Une fois)
	 * @param f Fenetre pour ajouter les ActionListener
	 * @throws IOException
	 */
	public EcranJeu(Fenetre f)
	{
		menu=new JPanel();
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		menu.setLayout(new BoxLayout(menu, BoxLayout.LINE_AXIS));
		
		this.f=f;
		this.setFocusable(true);
		this.requestFocus();
		
		title=new JLabel("2048");
		title.setSize(new Dimension(150,50));
		title.setFont(new Font("Arial",Font.BOLD,40));
		//title.setAlignmentX(this.LEFT_ALIGNMENT);
		//title.setFont(new Font());
		score = new Score();
		
		
		
		g = new Grille();
		
		this.add(Box.createRigidArea(new Dimension(0,15)));
		menu.add(title);
		menu.add(Box.createRigidArea(new Dimension(50,0)));
		menu.add(score);
		this.add(menu);
		
		this.add(Box.createRigidArea(new Dimension(0,15)));
		this.add(g);
		
        
		
		
	}
	
	/**
	 * Initialise l'IA si elle est demandé par l'utilisateur
	 * @throws InterruptedException
	 */
	public void initIA() throws InterruptedException
	{
		ia=new IA(f);
	}
	
	
	/**
	 * Appelé à chaque début de partie, on remet chaque label à 0, 
	 */
	public void reinit()
	{
		this.score.reinit();
		this.g.reinit();
		
	}
	
	/**
	 * Gestion clavier des directions et des touches quitter(ESC) aide(H) et recommencer(J)
	 * @param e
	 */
	public void gestion(KeyEvent e){
		int keyCode=e.getKeyCode();
		
		boolean has_moved=false, has_fused=false;
		
	    switch(keyCode) { 
	        case KeyEvent.VK_UP:
	        	has_fused=this.g.fusion(Direction.NORD,score,true);
	        	has_moved=this.g.deplacerCase(Direction.NORD,true);
	            break;
	        case KeyEvent.VK_DOWN:
	        	has_fused=this.g.fusion(Direction.SUD,score,true);
	        	has_moved=this.g.deplacerCase(Direction.SUD,true);
	            break;
	        case KeyEvent.VK_LEFT:
	        	has_fused=this.g.fusion(Direction.OUEST,score,true);
	        	has_moved=this.g.deplacerCase(Direction.OUEST,true);
	            break;
	        case KeyEvent.VK_RIGHT :
	        	has_fused=this.g.fusion(Direction.EST,score,true);
	        	has_moved=this.g.deplacerCase(Direction.EST,true);
	            break;
	        case KeyEvent.VK_ESCAPE:
	        	f.dispose();
	        	break;
	        case KeyEvent.VK_J:
	        	this.reinit();
	        	break;
	        case KeyEvent.VK_H:
	        	f.afficherPanneau(f.lePanneau4);
	        	break;
	        	
	     }
	    
	    if(has_moved||has_fused)
        	this.g.apparitionCase();
	    
	    //Verifier si déplacement possible 
	    if(this.g.nbSet==Grille.NB_COLONNES*Grille.NB_LIGNES&&!this.g.canMove())
	    {
	    	f.afficherPanneau(f.lePanneau2);
	    }
	}


}
