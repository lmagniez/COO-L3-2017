
package com.controler;

import java.util.ArrayList;

import com.model.AbstractModel;
import com.model.plateau.pioche.TypePioche;

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
	protected boolean effetPioche;
	protected boolean hypotheque;
	protected boolean gameOver;
	protected boolean tour;
	protected boolean achatEnchere;
	protected boolean save;
	
	
	
	
	protected int positionAchat;
	protected int somme;
	
	protected int idJoueur;
	protected int idJoueur2;
	protected int idCarte;
	protected int prix;
	protected TypePioche type;
	
	public AbstractControler(){
	
	}

	/**
	 * Constructeur
	 * @param cal
	 */
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
	
	/**
	 * Demande un echange entre 2 joueurs
	 * @param idJ1 id joueur 1
	 * @param idJ2 id joueur 2
	 * @param position position de la carte
	 * @param somme somme de la carte
	 */
	public void requestEchange(int idJ1, int idJ2, int position, int somme){
		this.echangePropriete=true;
		this.idJoueur=idJ1;
		this.idJoueur2=idJ2;
		this.positionAchat=position;
		this.somme=somme;
		control();
	}

	/**
	 * Demander l'effet d'une carte d'une pioche
	 * @param idJ1 id joueur 
	 * @param idCarte id de la carte
	 * @param type type de la carte
	 */
	public void requestEffetPioche(int idJ1, int idCarte, TypePioche type) {
		this.idJoueur=idJ1;
		this.idCarte=idCarte;
		this.type=type;
		this.effetPioche=true;
		control();
	}

	/**
	 * Demander l'hypotheque d'une propriété
	 * @param idJ1 id joueur 
	 * @param idSelected id sélectionné
	 */
	public void requestHypothequer(int idJ1, int idSelected) {
		this.hypotheque=true;
		this.idJoueur=idJ1;
		this.positionAchat=idSelected;
		control();
		
	}

	/**
	 * Demander la fin de partie pour un joueur 
	 * @param idJoueur3 id joueur
	 */
	public void requestFinPartie(int idJoueur3) {
		this.gameOver=true;
		this.idJoueur=idJoueur3;
		control();
	}

	/**
	 * Demander le tour suivant
	 */
	public void requestTour() {
		this.tour=true;
		control();
	}

	/**
	 * Demander l'achat via une enchere
	 * @param idJoueur3 id joueur
	 * @param position position carte
	 * @param prix prix carte
	 */
	public void requestAchatEnchere(int idJoueur3, int position, int prix) {
		this.achatEnchere=true;
		this.idJoueur=idJoueur3;
		this.positionAchat=position;
		this.prix=prix;
		control();
	}
	
	/**
	 * Demander la sauvegarde de la partie
	 */
	public void requestSave(){
		this.save=true;
		control();
	}
	
}
