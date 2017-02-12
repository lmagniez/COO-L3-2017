package com.model.plateau.cases;

import com.model.plateau.JoueurModel;

/**
 * Cases changeant l'argent du joueur
 * @author loick
 *
 */
public class DepartImpotTaxeModel extends CaseModel{

	private int value;
	
	public DepartImpotTaxeModel(int idCase, int position, int value, String nom)
	{
		this.setNom(nom);
		this.idCase=idCase;
		this.position=position;
		this.value=value;
		this.nom=nom;
	}
	
	@Override
	public void action(JoueurModel j) {
		j.setArgent(j.getArgent() + value);
	}

	
}