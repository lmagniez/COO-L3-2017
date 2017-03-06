package com.controler;



import java.util.ArrayList;

import com.model.AbstractModel;
import com.model.CaseValue;
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
	protected CaseValue v;
	protected boolean requestSave;
	
	
	
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
	public void setJeton(int x, int y, CaseValue v){
		
		this.x=x;
		this.y=y;
		this.v=v;
		control();
	}
	
	
	/**
	 * Reinitialiser le modèle
	 */
	public void reset(){
		calc.reinit();
	}
	
	//Methode de controle
	abstract void control();

	/**
	 * Changer une valeur dans une grille donnée
	 * @param idPuzzle id du puzzle
	 * @param i abscisse dans le puzzle
	 * @param j ordonée dans le puzzle
	 */
	public void changeValue(int i, int j, CaseValue v) {
		// TODO Auto-generated method stub
		this.x=i;
		this.y=j;
		this.v=v;
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
	 * Demander la sauvegarde de la grille (création)
	 */
	public void requestSave() {
		// TODO Auto-generated method stub
		this.requestSave=true;
		control();
		
	}
	
	
	
}
