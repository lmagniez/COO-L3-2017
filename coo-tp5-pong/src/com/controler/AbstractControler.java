package com.controler;



import java.sql.Time;

import com.model.AbstractModel;
import com.model.Direction;

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
	protected Direction d;
	protected boolean bonus;
	protected boolean speed;
	protected boolean throwBall;
	
	
	

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
	
	public void setChange(int id, Direction d){
		this.id=id;
		this.d=d;
		control();
	}
	
	public void setNouveauBonus(boolean bonus){
		this.bonus=true;
		control();
	}
	
	public void setVitesse(boolean vitesse){
		this.speed=true;
		control();
	}
	
	public void throwBall() {
		this.throwBall=true;
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
