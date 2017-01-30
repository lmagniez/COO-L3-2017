package com.vue.menu;
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

public class EcranJeu extends Ecran{

	private VueMenu vue;
	private JLabel title;
	
	protected JPanel menu;
	
	
	
	/**
	 * Créé un nouveau JPanel de jeu (Une fois)
	 * @param vue Fenetre pour ajouter les ActionListener
	 * @throws IOException
	 */
	public EcranJeu(VueMenu vue)
	{
		menu=new JPanel();
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		menu.setLayout(new BoxLayout(menu, BoxLayout.LINE_AXIS));
		
		this.vue=vue;
		this.setFocusable(true);
		this.requestFocus();
		
		title=new JLabel("MONOPOLY");
		title.setSize(new Dimension(150,50));
		title.setFont(new Font("Arial",Font.BOLD,40));
		
		
		
		this.add(Box.createRigidArea(new Dimension(0,15)));
		menu.add(title);
		
		this.add(menu);
		
		
        
		
		
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
	        	vue.afficherPanneau(vue.lePanneau2);
	        	break;
	        	
	     }
	    
	   
	}


}
