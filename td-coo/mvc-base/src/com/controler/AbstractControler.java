
package com.controler;

import com.model.AbstractModel;

public abstract class AbstractControler {

	protected AbstractModel calc;

	//...

	public AbstractControler(AbstractModel cal){
		this.calc = cal;
		//On initialise toutes les variables utiles
		//...
	}
	//Definir toutes les methodes de modifications (les setters)
	public void setXXX(String s){
		//...
		control();
	}
	
	
	//Methode de controle
	abstract void control();
}