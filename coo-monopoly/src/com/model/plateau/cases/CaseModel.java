package com.model.plateau.cases;

import com.model.plateau.JoueurModel;

public abstract class CaseModel {

	protected int idCase;
	protected int position;
	protected String nom;
	
	public abstract void action(JoueurModel j);

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}
