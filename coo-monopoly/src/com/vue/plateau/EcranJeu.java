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
import com.model.plateau.cases.CouleurTerrain;
import com.vue.menu.Ecran;
import com.vue.menu.VueMenu;
import com.vue.plateau.jeu.ChoixAchat;
import com.vue.plateau.jeu.ChoixEchange;
import com.vue.plateau.jeu.ChoixPaiement;
import com.vue.plateau.jeu.Plateau;
import com.vue.plateau.joueur.Score;

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
	protected ChoixAchat choixA;
	protected ChoixPaiement choixP;
	protected ChoixEchange choixE;
	
	
	private int tour;
	
	
	
	/**
	 * Créé un nouveau JPanel de jeu (Une fois)
	 * @param vue Fenetre pour ajouter les ActionListener
	 * @throws IOException
	 */
	public EcranJeu(VueJeu vue)
	{
		
		//this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		this.setLayout(null);
		
		this.setVue(vue);
		this.setFocusable(true);
		this.requestFocus();
		
		this.setTour(0);
		
		
		choixA=new ChoixAchat(this);
		choixA.setLocation(ConstantesVue.DIMENSION_CHOIX_POSX,ConstantesVue.DIMENSION_CHOIX_POSY);
		choixA.setSize(ConstantesVue.DIMENSION_CHOIX_X,ConstantesVue.DIMENSION_CHOIX_Y);
		
		choixP=new ChoixPaiement(this);
		choixP.setLocation(ConstantesVue.DIMENSION_CHOIX_POSX,ConstantesVue.DIMENSION_CHOIX_POSY);
		choixP.setSize(ConstantesVue.DIMENSION_CHOIX_X,ConstantesVue.DIMENSION_CHOIX_Y);
		
		setChoixE(new ChoixEchange(this));
		getChoixE().setLocation(ConstantesVue.DIMENSION_CHOIX_POSX,ConstantesVue.DIMENSION_CHOIX_POSY);
		getChoixE().setSize(ConstantesVue.DIMENSION_CHOIX_X,ConstantesVue.DIMENSION_CHOIX_Y);
		
		
		//int loyerTest[]={10,200,2000,3000,3000,4000};
		//c.genererChoixAchat(1, 2, "Case test", CouleurTerrain.MARRON, 500, loyerTest, 3000);
		
		
		setP(new Plateau(this));
		s=new Score(this);
		getP().setLocation(0,0);
		getP().setSize(ConstantesVue.DIMENSION_PLATEAU_X,ConstantesVue.DIMENSION_PLATEAU_Y);
		s.setLocation(ConstantesVue.DIMENSION_PLATEAU_X,0);
		s.setSize(ConstantesVue.DIMENSION_SCORE_X,ConstantesVue.DIMENSION_SCORE_Y);
		
		
		//this.add(Box.createRigidArea(new Dimension(0,15)));
		//menu.add(title);

		this.add(choixA);
		this.add(choixP);
		this.add(getChoixE());
		
		this.add(getP());
		this.add(s);
		
		//this.add(menu);
		
		
		
        
		
		
	}
	/**
	 * Changer le tour
	 */
	public void changeTour(){
		setTour(getTour() + 1%ConstantesParam.NB_JOUEURS);
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

	public void initTour() {
		// TODO Auto-generated method stub
		this.s.initTour(this.tour);
	}

	public VueJeu getVue() {
		return vue;
	}

	public void setVue(VueJeu vue) {
		this.vue = vue;
	}

	public int getTour() {
		return tour;
	}

	public void setTour(int tour) {
		this.tour = tour;
	}

	public Plateau getP() {
		return p;
	}

	public void setP(Plateau p) {
		this.p = p;
	}

	public Score getS() {
		return s;
	}

	public void setS(Score s) {
		this.s = s;
	}

	public ChoixAchat getChoixA() {
		return choixA;
	}

	public void setChoixA(ChoixAchat c) {
		this.choixA = c;
	}
	public ChoixEchange getChoixE() {
		return choixE;
	}
	public void setChoixE(ChoixEchange choixE) {
		this.choixE = choixE;
	}
	public ChoixPaiement getChoixP() {
		return choixP;
	}
	public void setChoixP(ChoixPaiement choixP) {
		this.choixP = choixP;
	}
	
	
	

}
