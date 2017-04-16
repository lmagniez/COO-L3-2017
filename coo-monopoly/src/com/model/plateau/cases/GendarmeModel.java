package com.model.plateau.cases;

import com.model.plateau.JoueurModel;
import com.model.plateau.PlateauModel;

/**
 * Classe représentant une case gendarme
 * @author loick
 *
 */
public class GendarmeModel extends CaseModel{

	
	protected PlateauModel p;
	
	/**
	 * Constructeur
	 * @param p modèle du plateau
	 * @param idCase id de la case
	 * @param position position de la case
	 */
	public GendarmeModel(PlateauModel p, int idCase, int position)
	{
		this.p=p;
		this.setNom("Gendarme");
		this.idCase=idCase;
		this.setPosition(position);
	}
	
	@Override
	public void action(JoueurModel j) {
		j.allerEnPrison();
	}

	
}