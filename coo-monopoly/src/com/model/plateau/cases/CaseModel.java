package com.model.plateau.cases;

import com.model.plateau.JoueurModel;

public abstract class CaseModel {

	protected int idCase;
	private int position;
	protected String nom;
	
	public abstract void action(JoueurModel j);

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	
}
