
package com.controler;

import java.util.ArrayList;

import com.model.AbstractModel;
import com.model.IllegalMoveException;

public abstract class AbstractControler {

	protected AbstractModel calc;

	protected int x;
	protected int y;
	
	protected ArrayList<String> tabSymbole= new ArrayList<String>();
	
	public AbstractControler(){
	
	}

	public AbstractControler(AbstractModel cal){
		this.calc = cal;
		tabSymbole.add("x");
		tabSymbole.add("o");
		tabSymbole.add("");
		
		//On initialise toutes les variables utiles
		//...
	}
	//Definir toutes les methodes de modifications (les setters)
	public void setCase(int x, int y){
		//...
		this.x=x;
		this.y=y;
		//this.s=s;
		
		control();
	}
	
	public void reset(){
		calc.init_grille();
	}
	
	
	//Methode de controle
	abstract void control();
}
