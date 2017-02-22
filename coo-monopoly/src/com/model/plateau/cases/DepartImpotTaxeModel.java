package com.model.plateau.cases;

import com.model.plateau.JoueurModel;
import com.model.plateau.PlateauModel;

/**
 * Cases changeant l'argent du joueur
 * @author loick
 *
 */
public class DepartImpotTaxeModel extends CaseModel{

	private int value;
	protected PlateauModel p;
	
	public DepartImpotTaxeModel(PlateauModel p, int idCase, int position, int value, String nom)
	{
		this.p=p;
		this.setNom(nom);
		this.idCase=idCase;
		this.setPosition(position);
		this.value=value;
		this.setNom(nom);
	}
	
	@Override
	public void action(JoueurModel j) {
		j.setArgent(j.getArgent() + value);
	}

	
}