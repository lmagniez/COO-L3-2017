package com.model.plateau;

public class JoueurModel {

	protected PlateauModel p;
	protected int idJoueur;
	protected int position;
	protected int argent;
	protected int cartePrison;
	protected boolean enPrison;
	protected int nbMaison;
	protected int nbHotel;
	protected int nbProprietes;
	
	
	protected int lastSumDes;//utile pour le loyer service public
	
	public JoueurModel(PlateauModel p, int idJ, int pos, int arg)
	{
		this.p=p;
		this.setIdJoueur(idJ);
		this.position=pos;
		this.argent=arg;
		this.enPrison=false;
		this.cartePrison=0;
		this.nbMaison=0;
		this.nbHotel=0;
		this.nbProprietes=0;
		
		this.setLastSumDes(0);
		
		
		
	}

	public void setPosition(int position) {
		if(this.position>position)
			this.argent+=20000;
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
	
	public void ajoutCartePrison(){
		this.cartePrison++;
	}

	public int getArgent() {
		return argent;
	}


	public void setArgent(int argent) {
		this.argent = argent;
		p.getModel().notifyArgentJoueur(idJoueur, argent);
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


	
}
