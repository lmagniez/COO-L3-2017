package com.controler;



import java.util.ArrayList;

import com.model.AbstractModel;
import com.model.BoxValues;
import com.vue.titre.Vue1;

public abstract class AbstractControler {

	protected AbstractModel calc;

	protected int x;
	protected int y;
	protected boolean vertical ;
	
	
	

	public AbstractControler(AbstractModel cal){
		this.calc = cal;
		
		//On initialise toutes les variables utiles
		//...
	}
	
	public AbstractControler(){	
	}
	
	
	//Definir toutes les methodes de modifications (les setters)
	public void setLigneVerticale(int x, int y){
		
		this.x=x;
		this.y=y;
		this.vertical=true;
		
		control();
	}
	
	public void setLigneHorizontale(int x, int y){
		
		this.x=x;
		this.y=y;
		this.vertical=false;
		
		control();
	}
	
	
	public void reset(){
		calc.reinit();
	}
	
	
	//Methode de controle
	abstract void control();
	//public abstract void changeScreen(Vue1 vue);
	public abstract void genererJeu(Vue1 vue, int nbLigne, int nbJoueur, boolean[] isIA);
}
