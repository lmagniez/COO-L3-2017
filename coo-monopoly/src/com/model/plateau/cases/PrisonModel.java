package com.model.plateau.cases;

import com.model.plateau.JoueurModel;
import com.model.plateau.PlateauModel;

/**
 * Modèle représentant la prison
 * @author loick
 *
 */
public class PrisonModel extends CaseModel{

	protected PlateauModel p;
	
	/**
	 * Constructeur
	 * @param p modèle du plateau
	 * @param idCase id de la case
	 * @param position position de la case
	 */
	public PrisonModel(PlateauModel p, int idCase, int position)
	{
		this.p=p;
		this.setNom("Prison");
		this.idCase=idCase;
		this.setPosition(position);
	}
	
	@Override
	public void action(JoueurModel j) {
		// TODO Auto-generated method stub
		this.p.getModel().tourSuivant();
		//this.p.getModel().notifyInitTour();
	}

	
}
