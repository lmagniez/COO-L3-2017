
package com.controler;

import java.util.ArrayList;

import com.model.AbstractModel;
import com.model.IllegalMoveException;

public abstract class AbstractControler {

	protected AbstractModel calc;

	protected boolean lancerDes;
	protected boolean achat;
	protected boolean enchere;
	protected boolean paiement;
	
	
	protected int positionAchat;
	
	
	protected int idJoueur;
	protected int idJoueur2;
	
	
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
	
	public void requestAchat(int idJoueur, int positionAchat){
		this.achat=true;
		this.idJoueur=idJoueur;
		this.positionAchat=positionAchat;
		control();
	}
	
	
	public void requestPaiement(int idJoueur1, int idJoueur2, int positionAchat){
		this.paiement=true;
		this.idJoueur=idJoueur1;
		this.idJoueur2=idJoueur2;
		this.positionAchat=positionAchat;
		control();
	}
	
	public void requestEnchere(int idJoueur, int positionAchat){
		this.enchere=true;
		this.idJoueur=idJoueur;
		this.positionAchat=positionAchat;
		control();
	}
	
	
	
	
	public void reset(){
		calc.init_grille();
	}
	
	
	//Methode de controle
	abstract void control();
}
