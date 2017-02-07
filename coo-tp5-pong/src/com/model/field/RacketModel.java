package com.model.field;

import com.model.Constantes;
import com.model.Direction;

public class RacketModel {

	protected int idJoueur;
	protected int posX;
	protected int posY;
	protected int coefY;
	protected int width;
	protected int height;
	
	public RacketModel(int idJoueur)
	{
		this.idJoueur=idJoueur;
		this.coefY=Constantes.RAQUETTE_VITESSE;
		this.height=Constantes.RAQUETTE_HEIGHT;
		this.width=Constantes.RAQUETTE_WIDTH;
		
		//Raquette J1
		if(this.idJoueur==0)
			this.posX=Constantes.RAQUETTE_X_J1;
		else if(this.idJoueur==1)
			this.posX=Constantes.RAQUETTE_X_J2;
			
		this.posY=Constantes.RAQUETTE_Y;
		
	}

	public void update(Direction d)
	{
		if(d==Direction.NORD&&posY>0+Constantes.MUR_HEIGHT)
		{
			this.posY-=coefY;
		}
		if(d==Direction.SUD
				&&posY+Constantes.RAQUETTE_HEIGHT<Constantes.DIMENSION_Y-Constantes.MUR_HEIGHT)
		{
			this.posY+=coefY;
		}
		if(posY<0)posY=0;
		if(posY>Constantes.DIMENSION_Y)posY=Constantes.DIMENSION_Y;
		
		
	}
}


