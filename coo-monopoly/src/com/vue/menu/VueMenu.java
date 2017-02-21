
package com.vue.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.controler.AbstractControler;
import com.controler.GrilleControler;
import com.model.AbstractModel;
import com.model.ConstantesVue;
import com.model.GrilleModel;
import com.observer.Observer;
import com.vue.Fenetre;
import com.vue.plateau.EcranJeu;
import com.vue.plateau.VueJeu;

public class VueMenu extends Fenetre implements Observer{
	
	protected EcranFinDePartie lePanneau2;
	protected EcranStart lePanneau3;
	protected EcranRegle lePanneau4;
	protected EcranOpt lePanneau5;
	protected EcranOptPlus lePanneau6;
	
	// l'ensemble des objets de vue
	//private 
	
	//L'instance de notre objet controleur
	private AbstractControler controler;
	
	
	public VueMenu(AbstractControler controler){
		
		this.controler=controler;
		
		this.setTitle("Monopoly");
		this.setSize(ConstantesVue.DIMENSION_FENETRE_X, ConstantesVue.DIMENSION_FENETRE_Y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setFocusable(false);
		
		lePanneau2 = new EcranFinDePartie(this);
		lePanneau3 = new EcranStart(this);
		lePanneau4 = new EcranRegle(this);
		lePanneau5 = new EcranOpt(this);
		lePanneau6 = new EcranOptPlus(this);
		
		
		//this.add(lePanneau5);
		//this.lePanneau5.setVisible(false);
		//this.add(lePanneau4);
		//this.lePanneau4.setVisible(false);
		
		
		this.add(lePanneau3);
		
		
		
		
		
		
		this.setVisible(true);
		//this.pack();
		
	}
	

	
	/**
	 * Réinitialise un écran de jeu (Toutes les cases à zéro)
	 */
	public void initFenetreEcranJeu()
	//public void initFenetreEcranJeu(int nbLigne, int nbJoueur, boolean[] isIA)
	{
	
		VueJeu jeu=new VueJeu();
		jeu.setVisible(true);
		this.setVisible(false);
		
		/*
		
		//Creation du modele de grille
		AbstractModel grilleModel = new GrilleModel(nbLigne, nbJoueur, isIA);
		//Creation du controleur
		AbstractControler grilleControler = new GrilleControler(grilleModel);
		//Creation de notre fenetre avec le controleur en parametre
		Vue2 vueJeu = new Vue2(grilleControler, nbLigne,nbJoueur,this,isIA);
		//Ajout de la fenetre comme observer de notre modele
		grilleModel.addObserver(vueJeu);
		this.setVisible(false);
		
		*/
		
		
	}
	
	
	
	class ResetListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			controler.reset();
		}               
	}
	

	@Override
	public void updateWinner(String s) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, "Le gagnant est"+s);
		//System.out.println("Le gagnant est"+s);
		controler.reset();
	}


	@Override
	public void update(int x, int y, String s) {
		// TODO Auto-generated method stub
		
	}

}