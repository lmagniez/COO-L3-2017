package com.controler;



import java.util.ArrayList;

import com.model.AbstractModel;
import com.model.grid.patternWin;
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
	
	protected int id;
	
	

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
	public void setChange(int id){
		this.id=id;
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
}
