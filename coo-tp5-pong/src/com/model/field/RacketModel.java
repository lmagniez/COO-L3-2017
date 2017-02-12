package com.model.field;

import com.model.Constantes;
import com.model.Direction;

/**
 * Modèle de la raquette
 * @author loick
 *
 */
public abstract class RacketModel implements Runnable{

	protected FieldModel field;
	protected int idJoueur;
	protected int posX;
	protected int posY;
	protected int coefY;
	protected int width;
	protected int height;
	
	protected Direction d;
	protected boolean up,down;
	
	/**
	 * Constructeur
	 * @param idJoueur id du joueur
	 */
	public RacketModel(FieldModel field, int idJoueur)
	{
		this.field=field;
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
	
	/**
	 * Mise à jour logique de la raquette en fonction de la direction souhaitée (haut/bas)
	 * @param d direction souhaitée
	 */
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
	
	/**
	 * Met a jour la direction
	 * @param d direction
	 */
	public void updateDir(Direction d)
	{
		this.d=d;
	}

	/**
	 * Met a jour la direction (avec booleens)
	 * @param up dirige vers le haut
	 * @param down dirige vers le bas
	 */
	public void updateDir(boolean up, boolean down) {
		// TODO Auto-generated method stub
		this.up=up;
		this.down=down;
		
	}
	
	public void update(boolean up, boolean down)
	{
		
		if((field.gameStopped&&field.avantageJ1&&idJoueur==0)
				||(field.gameStopped&&!field.avantageJ1&&idJoueur==1))
		{
			for(int i=0; i<field.balls.size(); i++){
				BallModel b=field.balls.get(i);
				if(up)b.update(Direction.NORD);
				if(down)b.update(Direction.SUD);
				
				field.notifyNewPosBall(b.id, b.posX, b.posY);
			}
		}
		
		if(up&&posY>0+Constantes.MUR_HEIGHT)
		{
			this.posY-=coefY;
		}
		if(down&&posY+Constantes.RAQUETTE_HEIGHT<Constantes.DIMENSION_Y-Constantes.MUR_HEIGHT)
		{
			this.posY+=coefY;
		}
		if(posY<0)posY=0;
		if(posY>Constantes.DIMENSION_Y)posY=Constantes.DIMENSION_Y;
		
	}
	
	
}


