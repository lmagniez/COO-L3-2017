package com.model.plateau.cases;

import com.model.plateau.JoueurModel;
import com.model.plateau.PlateauModel;

public class GendarmeModel extends CaseModel{

	
	protected PlateauModel p;
	
	public GendarmeModel(PlateauModel p, int idCase, int position)
	{
		this.p=p;
		this.setNom("Gendarme");
		this.idCase=idCase;
		this.setPosition(position);
	}
	
	@Override
	public void action(JoueurModel j) {
		// TODO Auto-generated method stub
		this.p.getModel().tourSuivant();
		
	}

	
}