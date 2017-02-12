package com.model.plateau.cases;

import com.model.plateau.JoueurModel;

public class GendarmeModel extends CaseModel{

	public GendarmeModel(int idCase, int position)
	{
		this.setNom("Gendarme");
		this.idCase=idCase;
		this.position=position;
	}
	
	@Override
	public void action(JoueurModel j) {
		// TODO Auto-generated method stub
		
	}

	
}