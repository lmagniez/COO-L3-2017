package com.model.plateau.cases;

import com.model.plateau.JoueurModel;

public class PrisonModel extends CaseModel{

	public PrisonModel(int idCase, int position)
	{
		this.setNom("Prison");
		this.idCase=idCase;
		this.position=position;
	}
	
	@Override
	public void action(JoueurModel j) {
		// TODO Auto-generated method stub
		
	}

	
}
