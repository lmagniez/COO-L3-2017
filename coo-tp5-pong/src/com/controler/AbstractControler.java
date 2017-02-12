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
	protected boolean throwBall;
	
	protected boolean released;
	protected boolean upJ1;
	protected boolean upJ2;
	protected boolean downJ1;
	protected boolean downJ2;
	
	
	

	/**
	 * Constructeur avec modèle
	 * @param cal modèle
	 */
	public AbstractControler(AbstractModel cal){
		this.calc = cal;
	}
	

	/**
	 * Appuie touche haut
	 * @param id id joueur
	 */
	public void setUp(int id){
		this.id=id;
		
		if(id==0)
			this.upJ1=true;
		if(id==1)
			this.upJ2=true;
		control();
	}
	
	/**
	 * Appuie touche bas
	 * @param id id joueur
	 */
	public void setDown(int id){
		this.id=id;
		
		if(id==0)
			this.downJ1=true;
		if(id==1)
			this.downJ2=true;
		control();
	}
	
	/**
	 * Appuie touche entrée
	 */
	public void setEnter(){
		throwBall=true;
		control();
	}
	
	/**
	 * Relache la touche bas
	 * @param i id joueur
	 */
	public void setReleasedDown(int i) {
		if(i==0)
			this.downJ1=false;
		if(i==1)
			this.downJ2=false;
		control();
	}
	
	public void setReleasedUp(int i) {
		if(i==0)
			this.upJ1=false;
		if(i==1)
			this.upJ2=false;
		control();
	}
	
	
	/**
	 * Changer l'id et la direction de l'objet à modifier (pour la raquette).
	 * @param id id de l'objet
	 * @param d direction de l'objet (Haut/Bas)
	 */
	public void setChange(int id, Direction d){
		this.id=id;
		this.d=d;
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
