package com.model.plateau.cases;

import com.model.plateau.JoueurModel;

public class ParkingModel extends CaseModel{

	public ParkingModel(int idCase, int position)
	{
		this.setNom("Parking Gratuit");
		this.idCase=idCase;
		this.setPosition(position);
	}
	
	@Override
	public void action(JoueurModel j) {

		//Rien ne se passe
	}

	
}