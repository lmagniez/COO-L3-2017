
package com.controler;

import java.util.ArrayList;

import com.model.AbstractModel;

/**
 * Classe abstraite du controler
 * @author loick
 *
 */
public abstract class AbstractControler {

	protected AbstractModel calc;

	protected boolean lancerDes;
	protected boolean achat;
	protected boolean enchere;
	protected boolean paiement;
	protected boolean achatMaison;
	protected boolean venteMaison;
	protected boolean echangePropriete;
	
	protected int positionAchat;
	protected int somme;
	
	protected int idJoueur;
	protected int idJoueur2;
	
	
	public AbstractControler(){
	
	}

	public AbstractControler(AbstractModel cal){
		this.calc = cal;
		
		//On initialise toutes les variables utiles
		//...
	}
	
	/**
	 * Demander de lancer le dé
	 * @param idJoueur id du joueur
	 */
	public void requestLancerDes(int idJoueur){
		this.lancerDes=true;
		this.idJoueur=idJoueur;
		control();
	}
	
	/**
	 * Demander un achat d'un terrain
	 * @param idJoueur id du joueur
	 * @param positionAchat position du terrain
	 */
	public void requestAchat(int idJoueur, int positionAchat){
		this.achat=true;
		this.idJoueur=idJoueur;
		this.positionAchat=positionAchat;
		control();
	}
	
	/**
	 * Demander un paiement entre 2 joueurs
	 * @param idJoueur1 id du joueur qui paye
	 * @param idJoueur2 id du joueur qui reçoit
	 * @param positionAchat position de l'achat que l'on transfère
	 */
	public void requestPaiement(int idJoueur1, int idJoueur2, int positionAchat){
		this.paiement=true;
		this.idJoueur=idJoueur1;
		this.idJoueur2=idJoueur2;
		this.positionAchat=positionAchat;
		control();
	}
	
	/**
	 * Demander le démarrage d'une enchère
	 * @param idJoueur id du joueur
	 * @param positionAchat position de la case à mettre aux enchères
	 */
	public void requestEnchere(int idJoueur, int positionAchat){
		this.enchere=true;
		this.idJoueur=idJoueur;
		this.positionAchat=positionAchat;
		control();
	}
	
	/**
	 * Demander l'achat d'une maison
	 * @param idJoueur id du joueur
	 * @param position position de la case à mettre aux enchères
	 */
	public void requestAchatMaison(int idJoueur, int position){
		this.achatMaison=true;
		this.idJoueur=idJoueur;
		this.positionAchat=position;
		control();
	}
	
	/**
	 * Demander la réinitialisation de la grille
	 */
	public void reset(){
		calc.init_grille();
	}
	
	
	//Methode de controle
	abstract void control();

	/**
	 * Demander la vente d'une maison
	 * @param idJoueur id du joueur
	 * @param position position de la case 
	 */
	public void requestVendreMaison(int idJoueur, int position) {
		this.venteMaison=true;
		this.idJoueur=idJoueur;
		this.positionAchat=position;
		control();
	}
	
	public void requestEchange(int idJ1, int idJ2, int position, int somme){
		this.echangePropriete=true;
		this.idJoueur=idJ1;
		this.idJoueur2=idJ2;
		this.positionAchat=position;
		this.somme=somme;
		control();
	}
	
	
}
