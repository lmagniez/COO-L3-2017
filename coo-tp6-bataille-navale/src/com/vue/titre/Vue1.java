package com.vue.titre;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.controler.AbstractControler;
import com.controler.GridControler;
import com.model.AbstractModel;
import com.model.GridModel;
import com.observer.Observer;
import com.vue.Fenetre;
import com.vue.grid.VueGrid;

/**
 * Vue correspondant à un écran titre et un écran de parametrage.
 * Ne dispose pas de modèle (menu), mais accède à un controler.
 * @author loick
 *
 */

public class Vue1 extends Fenetre{

	protected EcranTitre lePanneau;
	protected EcranParam lePanneau2; 
	
	
	
	
	/**
	 * Contructeur de la vue (appelé une fois)
	 * @param controler
	 */
	public Vue1(){
		
		this.setTitle("Bataille Navale");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setFocusable(false);
		
		lePanneau= new EcranTitre(this);
		lePanneau2 = new EcranParam(this);
		
		this.add(lePanneau);
		this.setVisible(true);
		
	}

	
	
	/**
	 * Initialise une fenetre de jeu (et l'initialise)
	 * @param swapColor 
	 */
	public void initFenetreEcranJeu(int nbRow, int nbCol, int nbJR, boolean[] isIA, boolean swapColor)
	{
		
		//Creation du modele de grille
		AbstractModel gridModel = new GridModel(nbRow, nbCol);
		//Creation du controleur
		AbstractControler gridControler = new GridControler(gridModel);
		//Creation de notre fenetre avec le controleur en parametre
		VueGrid vueJeu = new VueGrid(gridControler, nbRow, nbCol,this);
		//Ajout de la fenetre comme observer de notre modele
		gridModel.addObserver(vueJeu);
		this.setVisible(false);
		
	}
	
	
	
	


}

