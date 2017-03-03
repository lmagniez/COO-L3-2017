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

	private EcranTitre panneauTitre;
	private EcranParam panneauParam;
	protected EcranParam2 panneauParam2;
	
	
	protected int nbGrille; 
	protected int[] ids;
	protected int[] nbLignes;
	protected int[] nbColonnes;
	
	protected String[] noms; 
	protected boolean[] reussites;
	
	
	protected WindowAdapter windowAdapter;
	
	/**
	 * Contructeur de la vue (appelé une fois)
	 * @param controler
	 * @throws SQLException 
	 */
	public Vue1(AbstractControler controler) throws SQLException{
		
		//initFrame();
		
		super(controler);
		
		this.setTitle("PICROSS");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setFocusable(false);
		
		
		
		setPanneauTitre(new EcranTitre(this));
		
		
		
		
		this.add(getPanneauTitre());
		this.setVisible(true);
		
		/*
		//Creation du modele de grille
		jeuModel = new JeuModel();
		jeuModel.addObserver(this);
		//Creation du controleur
		gridControler = new GridControler(jeuModel);
		*/
		
		
	}

	
	private void initFrame() {

	    this.windowAdapter = new WindowAdapter() {
	        // WINDOW_CLOSING event handler
	        @Override
	        public void windowClosing(WindowEvent e) {
	            super.windowClosing(e);
	            // You can still stop closing if you want to
	            int res = JOptionPane.showConfirmDialog(Vue1.this, "Are you sure you want to close?", "Close?", JOptionPane.YES_NO_OPTION);
	            if ( res == 0 ) {
	                // dispose method issues the WINDOW_CLOSED event
	            	Vue1.this.dispose();
	            }
	        }

	        // WINDOW_CLOSED event handler
	        @Override
	        public void windowClosed(WindowEvent e) {
	            super.windowClosed(e);
	            // Close application if you want to with System.exit(0)
	            // but don't forget to dispose of all resources 
	            // like child frames, threads, ...
	            // System.exit(0);
	        }
	    };

	    // when you press "X" the WINDOW_CLOSING event is called but that is it
	    // nothing else happens
	    this.setDefaultCloseOperation(Vue1.DO_NOTHING_ON_CLOSE);
	    // don't forget this
	    this.addWindowListener(this.windowAdapter);
	}
	
	public void requestGrilles(){
		this.getGridControler().requestGrilles();
	}
	
	
	/**
	 * Initialise une fenetre de jeu
	 * @param idPuzzle id de la grille
	 * @throws SQLException
	 */
	public void initFenetreEcranJeu(int idPuzzle) throws SQLException
	{
		
		//Creation de notre fenetre avec le controleur en parametre
		VueGrid vueJeu = new VueGrid(getGridControler(), this);
		
		//Ajout de la fenetre comme observer de notre modele
		this.getGridControler().addObserverModel(vueJeu);
		this.getGridControler().requestGrilleDetail(idPuzzle);
		
		//((JeuModel) jeuModel).recupGrille(idPuzzle);
		
		
		this.setVisible(false);
		
	}

	/**
	 * Initialiser une fenetre de création
	 * @param nom nom de la grille
	 * @param nbRow nombre de ligne 
	 * @param nbCol nombre de colonne
	 * @throws SQLException
	 */
	public void initFenetreCreation(String nom, int nbRow, int nbCol) throws SQLException
	{
		
		//Creation de notre fenetre avec le controleur en parametre
		VueGridCreation vueJeu = new VueGridCreation(nom,nbRow,nbCol,getGridControler(), this);
		
		//Ajout de la fenetre comme observer de notre modele
		this.getGridControler().addObserverModel(vueJeu);
		this.getGridControler().requestCreationGrille(nom,nbRow,nbCol);
		
		//((JeuModel) jeuModel).recupGrille(idPuzzle);
		
		
		this.setVisible(false);
		
	}
	

	
	
	/**
	 * Mettre a jour l'ensemble des informations des grilles (Menu selection)
	 * @param nbGrille nombre de grille
	 * @param id ensemble des ids
	 * @param nom ensemble des noms 
	 * @param reussite ensemble des reussites de grille
	 * @param nbLignes ensemble des nombres de lignes
	 * @param nbColonnes ensemble des nombres de colonnes
	 */
	@Override
	public void updateInfosGrilles(int nbGrille, int[] id, String[] nom, boolean[] reussite, int[] nbLignes, int[] nbColonnes) {
		// TODO Auto-generated method stub
		this.nbGrille=nbGrille;
		this.ids=id;
		this.noms=nom;
		this.reussites=reussite;
		this.nbLignes=nbLignes;
		this.nbColonnes=nbColonnes;
		
	}

	public AbstractControler getGridControler() {
		return this.getControler();
	}

	public void setGridControler(AbstractControler gridControler) {
		this.setControler(gridControler);
	}

	public EcranParam getPanneauParam() {
		return panneauParam;
	}

	public void setPanneauParam(EcranParam panneauParam) {
		this.panneauParam = panneauParam;
	}

	public EcranTitre getPanneauTitre() {
		return panneauTitre;
	}

	public void setPanneauTitre(EcranTitre panneauTitre) {
		this.panneauTitre = panneauTitre;
	}
	
	
	@Override
	public void updateGrilleDetail(int id, String nom, String[] indicesLigne, String[] indicesColonne,
			boolean reussite) {	
	}
	@Override
	public void updateReinitWindow() {	
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


}

