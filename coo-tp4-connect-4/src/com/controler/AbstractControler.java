package com.controler;



import java.util.ArrayList;

import com.model.AbstractModel;
import com.model.BoxValues;
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
	/**
	 * Modifier une ligne verticale
	 * @param x
	 * @param y
	 */
	public void setLigneVerticale(int x, int y){
		
		this.x=x;
		this.y=y;
		this.vertical=true;
		
		control();
	}
	
	/**
	 * Modifier une ligne horizontale
	 * @param x
	 * @param y
	 */
	public void setLigneHorizontale(int x, int y){
		
		this.x=x;
		this.y=y;
		this.vertical=false;
		
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
	//public abstract void changeScreen(Vue1 vue);
	public abstract void genererJeu(Vue1 vue, int nbLigne, int nbJoueur, boolean[] isIA);
}
