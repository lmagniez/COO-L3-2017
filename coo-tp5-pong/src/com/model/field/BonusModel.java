package com.model.field;

import java.util.Random;

import com.model.Constantes;

public class BonusModel {

	
	protected FieldModel field;
	protected int posX,posY,diam;
	protected int id;
	protected boolean onScreen=true;
	
	public static int nb_bonus=0;
	
	public BonusModel(FieldModel field)
	{
		this.field=field;
		
		Random r =new Random();
		
		this.posX=Constantes.RAQUETTE_X_J1+r.nextInt(Constantes.RAQUETTE_X_J2);
		this.posY=Constantes.MUR1_Y+r.nextInt(Constantes.MUR2_Y);
		this.diam=Constantes.DIAMETRE_BONUS;
		this.id=nb_bonus++;
		
		
	}

	
}
