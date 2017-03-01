package com.vue.titre;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.controler.AbstractControler;
import com.controler.GridControler;
import com.model.AbstractModel;
import com.model.GrilleModel;
import com.model.JeuModel;
import com.observer.Observer;
import com.vue.ButtonMenu;
import com.vue.Colors;
import com.vue.Fenetre;
import com.vue.grid.VueGrid;
import com.vue.grid.VueGridCreation;

/**
 * Vue correspondant à un écran titre et un écran de parametrage.
 * Ne dispose pas de modèle (menu), mais accède à un controler.
 * @author loick
 *
 */

public class Vue1 extends Fenetre implements Observer{

	protected EcranTitre panneauTitre;
	protected EcranParam panneauParam;
	protected EcranParam2 panneauParam2;
	
	
	protected int nbGrille; 
	protected int[] ids;
	protected int[] nbLignes;
	protected int[] nbColonnes;
	
	protected String[] noms; 
	protected boolean[] reussites;
	
	
	
	
	protected AbstractControler gridControler;
	/**
	 * Contructeur de la vue (appelé une fois)
	 * @param controler
	 * @throws SQLException 
	 */
	public Vue1(AbstractControler controler) throws SQLException{
		
		this.gridControler=controler;
		
		this.setTitle("CONNECT 4");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setFocusable(false);
		
		
		
		panneauTitre= new EcranTitre(this);
		
		
		
		
		this.add(panneauTitre);
		this.setVisible(true);
		
		/*
		//Creation du modele de grille
		jeuModel = new JeuModel();
		jeuModel.addObserver(this);
		//Creation du controleur
		gridControler = new GridControler(jeuModel);
		*/
		
		
	}

	public void requestGrilles(){
		this.gridControler.requestGrilles();
	}
	
	/**
	 * Initialise une fenetre de jeu (et l'initialise)
	 * @param swapColor 
	 * @throws SQLException 
	 */
	public void initFenetreEcranJeu(int idPuzzle) throws SQLException
	{
		
		//Creation de notre fenetre avec le controleur en parametre
		VueGrid vueJeu = new VueGrid(gridControler, this);
		
		//Ajout de la fenetre comme observer de notre modele
		this.gridControler.addObserverModel(vueJeu);
		this.gridControler.requestGrilleDetail(idPuzzle);
		
		//((JeuModel) jeuModel).recupGrille(idPuzzle);
		
		
		this.setVisible(false);
		
	}

	public void initFenetreCreation(String nom, int nbRow, int nbCol) throws SQLException
	{
		
		//Creation de notre fenetre avec le controleur en parametre
		VueGridCreation vueJeu = new VueGridCreation(nom,nbRow,nbCol,gridControler, this);
		
		//Ajout de la fenetre comme observer de notre modele
		this.gridControler.addObserverModel(vueJeu);
		this.gridControler.requestCreationGrille(nom,nbRow,nbCol);
		
		//((JeuModel) jeuModel).recupGrille(idPuzzle);
		
		
		this.setVisible(false);
		
	}
	

	@Override
	public void updateReinit() {
		
	}



	@Override
	public void updateChangeValue(int x, int y) {
		
	}

	


	@Override
	public void updateWin() {
		
	}



	@Override
	public void updateLose() {
		
	}


	public void updateStart(){

	}

	@Override
	public void updateInfosGrilles(int nbGrille, int[] id, String[] nom, boolean[] reussite, int[] nbLignes, int[] nbColonnes) {
		// TODO Auto-generated method stub
		System.out.println("infos grille!!");
		this.nbGrille=nbGrille;
		this.ids=id;
		
		for(int i=0; i< nbGrille; i++)
			System.out.println("<<<< "+ids[i]);
		
		this.noms=nom;
		this.reussites=reussite;
		this.nbLignes=nbLignes;
		this.nbColonnes=nbColonnes;
		
	}




	@Override
	public void updateGrilleDetail(int id, String nom, String[] indicesLigne, String[] indicesColonne,
			boolean reussite) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	


}

