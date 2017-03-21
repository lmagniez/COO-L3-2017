package com.model.plateau.cases;

import com.model.plateau.JoueurModel;

/**
 * Modèle d'une case
 * Classe abstraite utilisé par chaque type de case
 * @author loick
 *
 */
public abstract class CaseModel {

	protected int idCase;
	private int position;
	protected String nom;
	
	/**
	 * Action engendrée par la case sur le joueur
	 * @param j modèle du joueur
	 */
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
