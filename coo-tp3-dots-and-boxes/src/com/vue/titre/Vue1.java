package com.vue.titre;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.controler.AbstractControler;
import com.controler.GrilleControler;
import com.controler.MenuControler;
import com.model.AbstractModel;
import com.model.BoxValues;
import com.model.GrilleModel;
import com.observer.Observer;
import com.vue.Fenetre;
import com.vue.grille.Vue2;

public class Vue1 extends Fenetre{

	protected EcranTitre lePanneau;
	protected EcranParam lePanneau2; 
	protected AbstractControler controler;
	
	
	protected AbstractModel grilleModel;
	protected AbstractControler grilleControler;
	
	
	
	public Vue1(AbstractControler controler){
		
		this.controler=controler;
		
		this.setTitle("3 DOTS 3 BOXES");
		this.setSize(400, 475);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setFocusable(false);
		
		lePanneau= new EcranTitre(this);
		lePanneau2 = new EcranParam(this);
		
		this.add(lePanneau);
		
		
		this.setVisible(true);
		//this.pack();
		
	}

	
	
	/**
	 * Réinitialise un écran de jeu (Toutes les cases à zéro sauf une)
	 */
	public void initFenetreEcranJeu(int nbLigne, int nbJoueur, boolean[] isIA)
	{
		
		grilleModel = new GrilleModel(nbLigne, nbJoueur, isIA);
		//Creation du controleur
		grilleControler = new GrilleControler(grilleModel);
		//Creation de notre fenetre avec le controleur en parametre
		Vue2 vueJeu = new Vue2(grilleControler, nbLigne,nbJoueur,this,isIA);
		//Ajout de la fenetre comme observer de notre modele
		grilleModel.addObserver(vueJeu);
		this.setVisible(false);
		
	}
	
	
	
	public static void main(String[] args) throws IOException {
		Vue1 f = new Vue1(new MenuControler());
		
	}


}

