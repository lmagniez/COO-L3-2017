package com.vue.plateau;
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

import com.model.ConstantesParam;
import com.model.ConstantesVue;
import com.vue.menu.Ecran;
import com.vue.menu.VueMenu;

/**
 * JPanel correspondant à une partie, l'utilisateur clique sur des lettres pour essayer de deviner le mot
 * @author loick
 *
 */

public class EcranJeu extends Ecran{

	private VueJeu vue;
	private JLabel title;
	
	protected JPanel menu;
	protected Plateau p;
	protected Score s;
	protected Choix c;
	
	protected int tour;
	
	
	
	/**
	 * Créé un nouveau JPanel de jeu (Une fois)
	 * @param vue Fenetre pour ajouter les ActionListener
	 * @throws IOException
	 */
	public EcranJeu(VueJeu vue)
	{
		
		//this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		this.setLayout(null);
		
		this.vue=vue;
		this.setFocusable(true);
		this.requestFocus();
		
		this.tour=0;
		this.changeTour();
		
		c=new Choix();
		c.setLocation(ConstantesVue.DIMENSION_CHOIX_POSX,ConstantesVue.DIMENSION_CHOIX_POSY);
		c.setSize(ConstantesVue.DIMENSION_CHOIX_X,ConstantesVue.DIMENSION_CHOIX_Y);
		
		
		p=new Plateau();
		s=new Score(this);
		p.setLocation(0,0);
		p.setSize(ConstantesVue.DIMENSION_PLATEAU_X,ConstantesVue.DIMENSION_PLATEAU_Y);
		s.setLocation(ConstantesVue.DIMENSION_PLATEAU_X,0);
		s.setSize(ConstantesVue.DIMENSION_SCORE_X,ConstantesVue.DIMENSION_SCORE_Y);
		
		
		
		
		//this.add(Box.createRigidArea(new Dimension(0,15)));
		//menu.add(title);

		this.add(c);
		this.add(p);
		this.add(s);
		
		//this.add(menu);
		
		
		
        
		
		
	}
	
	public void changeTour(){
		tour+=1%ConstantesParam.NB_JOUEURS;
	}
	
	
	/**
	 * Initialise l'IA si elle est demandé par l'utilisateur
	 * @throws InterruptedException
	 */
	public void initIA() throws InterruptedException
	{
	}
	
	
	/**
	 * Appelé à chaque début de partie, on remet chaque label à 0, 
	 */
	public void reinit()
	{
		
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
	        	
	            break;
	        case KeyEvent.VK_DOWN:
	        
	            break;
	        case KeyEvent.VK_LEFT:
	        	
	            break;
	        case KeyEvent.VK_RIGHT :
	        	
	            break;
	        case KeyEvent.VK_ESCAPE:

	        	break;
	        case KeyEvent.VK_J:
	        	
	        	break;
	        case KeyEvent.VK_H:
	        	//vue.afficherPanneau(vue.lePanneau2);
	        	break;
	        	
	     }
	    
	   
	}


}
