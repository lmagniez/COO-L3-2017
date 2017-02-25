package com.model.plateau.cases;

import com.model.plateau.JoueurModel;
import com.model.plateau.PlateauModel;

public class ParkingModel extends CaseModel{

	protected PlateauModel p;
	
	public ParkingModel(PlateauModel p, int idCase, int position)
	{
		this.p=p;
		this.setNom("Parking Gratuit");
		this.idCase=idCase;
		this.setPosition(position);
	}
	
	@Override
	public void action(JoueurModel j) {

		//Rien ne se passe
		this.p.getModel().tourSuivant();
		
	}

	
}