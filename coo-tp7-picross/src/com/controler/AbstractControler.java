package com.controler;



import java.util.ArrayList;

import com.model.AbstractModel;
import com.model.patternWin;
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
	protected int idPuzzle;
	
	
	
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
	public void reset(){
		calc.reinit();
	}
	
	public void requestGrilles(){
		this.requestGrilles=true;
		control();
	}
	
	
	//Methode de controle
	abstract void control();

	public void changeValue(int idPuzzle, int i, int j) {
		// TODO Auto-generated method stub
		this.idPuzzle=idPuzzle;
		this.x=i;
		this.y=j;
		control();
		
	}

	public void requestGrilleDetail(int idPuzzle) {
		// TODO Auto-generated method stub
		this.requestGrilleDetail=true;
		this.idPuzzle=idPuzzle;
		control();
	}
	
	public void requestVerif(int idPuzzle){
		this.requestVerifWin=true;
		this.idPuzzle=idPuzzle;
		control();
	}
	
}