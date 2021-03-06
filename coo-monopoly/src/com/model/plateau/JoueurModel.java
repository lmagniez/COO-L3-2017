package com.model.plateau;

import com.model.ConstantesModel;
import com.model.ConstantesParam;

/**
 * Modèle du joueur
 * @author loick
 *
 */
public class JoueurModel {

	protected PlateauModel p;
	protected int idJoueur;
	protected int position;
	protected int argent;
	protected int valeurPatrimoine;
	protected boolean cartePrison;
	protected int enPrison;
	protected int nbMaison;
	protected int nbHotel;
	protected int nbProprietes;
	protected boolean gameOver;
	protected String nom;
	protected int lastSumDes;//utile pour le loyer service public
	
	/**
	 * Constructeur
	 * @param p modèle du plateau
	 * @param idJ id du joueur
	 * @param pos position du joueur
	 * @param arg argent du joueur
	 */
	public JoueurModel(PlateauModel p, int idJ, int pos, int arg, String nom)
	{
		this.p=p;
		this.setIdJoueur(idJ);
		this.position=0;
		this.setPosition(pos);
		this.argent=arg;
		this.enPrison=0;
		this.cartePrison=false;
		this.nbMaison=0;
		this.nbHotel=0;
		this.nbProprietes=0;
		this.gameOver=false;
		this.setLastSumDes(0);
		this.valeurPatrimoine=0;
		this.nom=nom;
		
	}
	
	/**
	 * Constructeur complet pour Charger XML
	 * @param position2 
	 */
	public JoueurModel(PlateauModel p, int idJ, String nom, int argent, int valeurPatrimoine, 
			boolean cartePrison, int enPrison, int nbMaison, int nbHotel, int nbProprietes, boolean gameOver, int position)
	{
		this.p=p;
		this.idJoueur=idJ;
		this.nom=nom;
		this.argent=argent;
		this.valeurPatrimoine=valeurPatrimoine;
		this.cartePrison=cartePrison;
		this.enPrison=enPrison;
		this.nbMaison=nbMaison;
		this.nbHotel=nbHotel;
		this.nbProprietes=nbProprietes;
		this.gameOver=gameOver;
		this.position=position;
	}
	/**
	 * Setter
	 * @param position
	 */
	public void setPosition(int position) {
		if(this.position>position)
			this.setArgent(argent+20000);
		this.position = position;
		p.getModel().notifyPosJoueur(idJoueur,position);
	}
	
	/**
	 * Exemple: Aller en prison
	 * @param posPrison
	 */
	public void setPositionSansArgent(int posPrison) {
		this.position = posPrison;
		p.getModel().notifyPosJoueur(idJoueur,position);
	}
	
	/**
	 * Ajouter une carte prison au joueur 
	 */
	public void ajoutCartePrison(){
		this.cartePrison=true;
		p.model.notifyCartePrison(this.idJoueur,true);
	}

	public int getArgent() {
		return argent;
	}


	public void setArgent(int argent) {
		this.argent = argent;
		p.getModel().notifyArgentJoueur(idJoueur, argent);
		if(this.p.joueurs[idJoueur].argent<0){
			this.p.model.comblerDette(idJoueur);
		}
		
	}

	
	

	public int getValeurPatrimoine() {
		return valeurPatrimoine;
	}

	public void setValeurPatrimoine(int valeurPatrimoine) {
		this.valeurPatrimoine = valeurPatrimoine;
		p.getModel().notifyPatrimoineJoueur(idJoueur, valeurPatrimoine);
	}

	public int getPosition() {
		return position;
	}


	


	public int getNbMaison() {
		return nbMaison;
	}


	public void setNbMaison(int nbMaison) {
		this.nbMaison = nbMaison;
	}


	public int getNbHotel() {
		return nbHotel;
	}


	public void setNbHotel(int nbHotel) {
		this.nbHotel = nbHotel;
	}

	public int getIdJoueur() {
		return idJoueur;
	}

	public void setIdJoueur(int idJoueur) {
		this.idJoueur = idJoueur;
	}

	public int getLastSumDes() {
		return lastSumDes;
	}

	public void setLastSumDes(int lastSumDes) {
		this.lastSumDes = lastSumDes;
	}

	/**
	 * Mettre le joueur en prison
	 */
	public void allerEnPrison() {
		if(cartePrison){
			cartePrison=false;
			p.model.notifyCartePrison(this.idJoueur,false);
		}
		else{
			this.setPositionSansArgent(ConstantesModel.POS_PRISON);
			this.enPrison=ConstantesParam.NB_TOUR_PRISON;
			p.model.notifyPrison(this.idJoueur,this.enPrison);
		}
	}


	
}
