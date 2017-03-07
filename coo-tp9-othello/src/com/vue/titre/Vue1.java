package com.vue.titre;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.controler.AbstractControler;
import com.controler.GridControler;
import com.model.AbstractModel;
import com.model.GrilleModel;
import com.observer.Observer;
import com.vue.ButtonMenu;
import com.vue.Colors;
import com.vue.Fenetre;
import com.vue.grid.VueGrid;

/**
 * Vue correspondant à un écran titre et un écran de parametrage.
 * Ne dispose pas de modèle (menu), mais accède à un controler.
 * @author loick
 *
 */

public class Vue1 extends Fenetre{

	private EcranTitre panneauTitre;
	private EcranParam panneauParam;
	
	protected WindowAdapter windowAdapter;
	
	/**
	 * Contructeur de la vue (appelé une fois)
	 * @param controler
	 * @throws SQLException 
	 */
	public Vue1() throws SQLException{
		
		
		this.setTitle("OTHELLO");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setFocusable(false);
		
		
		
		this.panneauTitre=(new EcranTitre(this));
		
		
		
		
		this.add(panneauTitre);
		this.setVisible(true);
		
		/*
		//Creation du modele de grille
		jeuModel = new JeuModel();
		jeuModel.addObserver(this);
		//Creation du controleure
		gridControler = new GridControler(jeuModel);
		*/
		
		
	}
	
	/**
	 * Initialise une fenetre de jeu
	 * @param nbColonne 
	 * @param nbLigne 
	 * @param isIA 
	 * @param idPuzzle id de la grille
	 * @throws SQLException
	 */
	public void initFenetreEcranJeu(int nbLigne, int nbColonne, boolean[] isIA) 
	{
		
		
		//Creation du modele de grille
		AbstractModel gridModel = new GrilleModel(nbColonne,nbLigne,isIA,true);
		//Creation du controleur
		AbstractControler gameControler = new GridControler(gridModel);
		//Creation de notre fenetre avec le controleur en parametre
		VueGrid vueJeu = new VueGrid(gameControler, this, nbLigne, nbColonne);
		//Ajout de la fenetre comme observer de notre modele
		gridModel.addObserver(vueJeu);
		
		gridModel.placerinit();
		gridModel.startIA();
		
		this.setVisible(false);
		
	}

	public EcranParam getPanneauParam() {
		return panneauParam;
	}

	public void setPanneauParam(EcranParam panneauParam) {
		this.panneauParam = panneauParam;
	}





}

