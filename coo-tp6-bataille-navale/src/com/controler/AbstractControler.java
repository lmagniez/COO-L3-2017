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
	protected String msg;
	protected boolean bateaux ;
	protected boolean closeSocket ;
	protected boolean sendServer ;
	
	
	
	
	
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
	public void setBomb(int x,int y){
		
		this.x=x;
		this.y=y;
		control();
	}
	
	public void requestBateaux(){
		this.bateaux=true;
		control();
	}
	
	public void requestSendToServer(String msg){
		this.sendServer=true;
		this.msg=msg;
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

	public void requestCloseSocket() {
		this.closeSocket=true;
		control();
	}

	
}
