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
	
	/**
	 * Constructeur
	 * @param p modèle du plateau
	 * @param idCase id de la case
	 * @param position position de la case
	 * @param value argent gagné/perdu
	 * @param nom nom de la case
	 */
	public DepartImpotTaxeModel(PlateauModel p, int idCase, int position, int value, String nom)
	{
		this.p=p;
		this.setNom(nom);
		this.idCase=idCase;
		this.setPosition(position);
		this.setValue(value);
		this.setNom(nom);
	}
	
	@Override
	public void action(JoueurModel j) {
		j.setArgent(j.getArgent() + getValue());
		this.p.getModel().tourSuivant();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	
}