
package com.controler;

import java.util.ArrayList;

import com.model.AbstractModel;
import com.model.IllegalMoveException;

public abstract class AbstractControler {

	protected AbstractModel calc;

	protected boolean lancerDes;
	protected int idJoueur;
	
	
	public AbstractControler(){
	
	}

	public AbstractControler(AbstractModel cal){
		this.calc = cal;
		
		//On initialise toutes les variables utiles
		//...
	}
	//Definir toutes les methodes de modifications (les setters)
	public void requestLancerDes(int idJoueur){
		this.lancerDes=true;
		this.idJoueur=idJoueur;
		control();
	}
	
	public void reset(){
		calc.init_grille();
	}
	
	
	//Methode de controle
	abstract void control();
}
