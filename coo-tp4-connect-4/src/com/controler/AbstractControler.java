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
	protected boolean vertical ;

	/**
	 * Constructeur avec modèle
	 * @param cal modèle
	 */
	public AbstractControler(AbstractModel cal){
		this.calc = cal;
	}
	
	/**
	 * Contructeur vide utilisé pour un menu (pas de modèles)
	 */
	public AbstractControler(){	
	}
	
	//Definir toutes les methodes de modifications (les setters)
	
	
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
	
	
	//Methode de controle
	abstract void control();
}