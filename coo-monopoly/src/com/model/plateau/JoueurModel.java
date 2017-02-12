package com.model.plateau;

public class JoueurModel {

	private int idJoueur;
	private int position;
	private int argent;
	protected int cartePrison;
	protected boolean enPrison;
	private int nbMaison;
	private int nbHotel;
	
	private int lastSumDes;//utile pour le loyer service public
	
	public JoueurModel(int idJ, int pos, int arg)
	{
		this.setIdJoueur(idJ);
		this.position=pos;
		this.argent=arg;
		this.enPrison=false;
		this.cartePrison=0;
		this.nbMaison=0;
		this.nbHotel=0;
		
		this.setLastSumDes(0);
		
	}

	public void setPosition(int position) {
		if(this.position>position)
			this.argent+=20000;
		this.position = position;
	}
	
	public void setPositionSansArgent(int posPrison) {
		this.position = posPrison;
	}
	
	public void ajoutCartePrison(){
		this.cartePrison++;
	}

	public int getArgent() {
		return argent;
	}


	public void setArgent(int argent) {
		this.argent = argent;
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
