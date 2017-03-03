package com.controler;



import java.util.ArrayList;

import com.model.AbstractModel;
import com.model.patternWin;
import com.observer.Observer;
import com.vue.titre.Vue1;

/**
 * Class abstraite des controlers.
 * Chaque controler étendra cette classe.
 * @author loick
 *
 */

public abstract class AbstractControler {

	
	//Modèle
	protected AbstractModel calc;

	//Coordonées du trait à ajouter
	protected int x;
	protected int y;
	protected boolean requestGrilles ;
	protected boolean requestGrilleDetail ;
	protected boolean requestVerifWin;
	protected boolean requestSave;
	protected boolean requestGenererGrille;
	
	protected boolean reussiteGrille;
	protected boolean creationGrille;
	protected boolean creation;
	protected boolean requestUpdateReussite;
	
	
	protected int idPuzzle;
	protected String nom;
	protected int nbRow,nbCol;
	
	
	/**
	 * Constructeur avec modèle
	 * @param cal modèle
	 */
	public AbstractControler(AbstractModel cal){
		this.calc = cal;
	}
	
	//Definir toutes les methodes de modifications (les setters)

	/**
	 * Ajouter un jeton à l'abscisse x (appelle la méthode control).
	 * @param x
	 */
	public void setJeton(int x){
		
		this.x=x;	
		control();
	}
	
	
	/**
	 * Reinitialiser le modèle
	 */
	public void reset(int idGrille){
		calc.reinit(idGrille);
	}
	
	/**
	 * Reinitialise la grille de création
	 */
	public void resetCreation(){
		calc.reinitCreation();
	}
	
	public void requestGrilles(){
		this.requestGrilles=true;
		control();
	}
	
	
	//Methode de controle
	abstract void control();

	/**
	 * Changer une valeur dans une grille donnée
	 * @param idPuzzle id du puzzle
	 * @param i abscisse dans le puzzle
	 * @param j ordonée dans le puzzle
	 */
	public void changeValue(int idPuzzle, int i, int j) {
		// TODO Auto-generated method stub
		this.idPuzzle=idPuzzle;
		this.x=i;
		this.y=j;
		control();
		
	}

	/**
	 * Demander au modèle d'envoyer l'ensemble des informations pour une grille
	 * @param idPuzzle id du puzzle
	 */
	public void requestGrilleDetail(int idPuzzle) {
		// TODO Auto-generated method stub
		this.requestGrilleDetail=true;
		this.idPuzzle=idPuzzle;
		control();
	}
	
	/**
	 * Demander au modèle de vérifier si la grille est correcte ou non
	 * @param idPuzzle id du puzzle
	 */
	public void requestVerif(int idPuzzle){
		this.requestVerifWin=true;
		this.idPuzzle=idPuzzle;
		control();
	}
	
	/**
	 * Demander l'ajout d'observer
	 * @param obs
	 */
	public void addObserverModel(Observer obs){
		this.calc.addObserver(obs);
	}
	
	/**
	 * Demander la suppression d'observer
	 */
	public void removeObserverModel(){
		this.calc.removeObserver();
	}

	/**
	 * Changer la valeur d'une case pour la grille de création
	 * @param i abscisse de la grille
	 * @param j ordonnée de la grille
	 */
	public void changeValueCreate(int i, int j) {
		// TODO Auto-generated method stub
		this.creation=true;
		this.x=i;
		this.y=j;
		control();
	}

	/**
	 * Demander la création d'une nouvelle grille (initialisation création)
	 * @param nom nom de la grille
	 * @param nbRow nombre de ligne
	 * @param nbCol nombre de colonne
	 */
	public void requestCreationGrille(String nom, int nbRow, int nbCol) {
		this.creationGrille=true;
		this.nom=nom;
		this.nbRow=nbRow;
		this.nbCol=nbCol;
		control();
	}

	/**
	 * Demander la sauvegarde de la grille (création)
	 */
	public void requestSave() {
		// TODO Auto-generated method stub
		this.requestSave=true;
		control();
		
	}
	
	/**
	 * Demander la génération des grilles dans le modèle
	 */
	public void requestGenererGrilles() {
		// TODO Auto-generated method stub
		this.requestGenererGrille=true;
		control();
		
	}

	public void requestUpdateReussite() {
		// TODO Auto-generated method stub
		this.requestUpdateReussite=true;
		control();
	}
	
	
}
