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
		
		//this.posX=300;
		//this.posY=300;
		
		
		this.posX=Constantes.RAQUETTE_X_J1*2+r.nextInt(Constantes.RAQUETTE_X_J2-Constantes.RAQUETTE_X_J1*3);
		this.posY=Constantes.MUR_HEIGHT+r.nextInt(Constantes.MUR2_Y-(Constantes.MUR_HEIGHT*2));
		
		this.diam=Constantes.DIAMETRE_BONUS;
		this.id=nb_bonus++;
		
		
	}

	
}
