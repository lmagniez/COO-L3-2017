package com.model.field;

import com.model.Constantes;
import com.model.Direction;

public class BallModel {
	
	protected int id;
	protected FieldModel field;
	protected int posX,posY;
	protected int coefX, coefY;
	public static int nb_balle=0;
	
	public BallModel(FieldModel field, int idJ)
	{

		this.id=nb_balle++;
		this.field=field;
		this.posY=Constantes.BALLE_Y;
		this.coefX=Constantes.BALLE_COEFX;
		this.coefY=Constantes.BALLE_COEFY;
		if(idJ==1)
			this.posX=Constantes.BALLE_X_J1;
		else{
			this.posX=Constantes.BALLE_X_J2;
			this.coefX*=-1;
		}
		
	}
	
	public BallModel(FieldModel field, int posX, int posY, int coefX, int coefY)
	{

		this.id=nb_balle++;
		this.field=field;
		this.posX=posX;
		this.posY=posY;
		this.coefX=coefX;
		this.coefY=coefY;
		
		
	}
	
	
	public void updateBall()
	{
		//posX+=coefX;
		//posY+=coefY;
		
		posX+=(coefX*field.actualSpeed);
		posY+=(coefY*field.actualSpeed);
	}
	
	public void changeDirectionX()
	{
		//System.out.println("x");
		coefX*=-1;
	}
	
	public void changeDirectionY()
	{
		//System.out.println("y");
		coefY*=-1;
	}
	
	public boolean collideBall(BallModel b)
	{
		if((this.posX>=b.posX)&&(this.posX<=b.posX+Constantes.DIAMETRE_BALLE)
				&&(this.posY>=b.posY)&&(this.posY<=b.posY+Constantes.DIAMETRE_BALLE))
		{
			this.changeDirectionY();
			this.changeDirectionX();
			
			b.changeDirectionX();
			b.changeDirectionY();
			
			b.updateBall();
			updateBall();
			
			return true;
		}
		return false;
		
	}
	
	public boolean collideRacket(RacketModel r)
	{
		
		/*
		if((this.posX+Constantes.DIAMETRE_BALLE>=r.posX)
				&(this.posX+Constantes.DIAMETRE_BALLE<=r.posX+r.width)
				&&this.posY+Constantes.DIAMETRE_BALLE>=r.posY
				&&this.posY<=r.posY*1.05){
			this.changeDirectionY();
			return true;
		}
		
		if((this.posX+Constantes.DIAMETRE_BALLE>=r.posX)
				&&(this.posX+Constantes.DIAMETRE_BALLE<=r.posX+r.width)
				&&this.posY<=r.posY+r.height&&this.posY>=(r.posY+r.height)*0.95){
			this.changeDirectionY();
			return true;
		}*/
		
		
		if((this.posX+Constantes.DIAMETRE_BALLE>r.posX)
			&&(this.posX<r.posX+r.width)
			&&(this.posY>=r.posY)
			&&(this.posY<=r.posY+r.height))
		{
				//{
			if(r.idJoueur==0)
				this.posX=r.posX+r.width;
			if(r.idJoueur==1)
				this.posX=r.posX-Constantes.DIAMETRE_BALLE;
			
			this.changeDirectionX();
			return true;
		}
		
		
		
		
		/*
		if((this.posX+Constantes.DIAMETRE_BALLE>=r.posX)&&(this.posX<=r.posX+r.width)&&
				(this.posY+Constantes.DIAMETRE_BALLE>=r.posY)&&(this.posY<=r.posY+r.height)){
			this.changeDirectionY();
			return true;
		}
		*/
		return false;
		
	}
	
	public boolean collideMurH(MurHModel m)
	{
		if((m.idMur==0)&&(this.posY<m.posY+m.height)
				&&(this.posY<(m.posY+m.height)*0.95))
		{
			this.posY=m.posY+m.height;
			this.changeDirectionY();
			return true;
		}
		if((m.idMur==1)&&(this.posY>(m.posY-Constantes.DIAMETRE_BALLE))
				&&(this.posY>(m.posY-(Constantes.DIAMETRE_BALLE)*1.05)))
		{
			this.posY=m.posY-Constantes.DIAMETRE_BALLE;
			this.changeDirectionY();
			return true;
		}
		
		return false;
	}
	
	public boolean collideMurVG()
	{
		if(this.posX<0)
		{
			this.posX=0;
			//this.scoreJ2
			this.field.avantageJ1=false;
			this.field.scoreJ2++;
			this.field.notifyScore(this.field.scoreJ1, this.field.scoreJ2);
			this.field.reinit();
			return true;
		}
		return false;
	}
	
	public boolean collideMurVD()
	{
		if(this.posX>Constantes.DIMENSION_X-Constantes.DIAMETRE_BALLE)
		{
			this.posX=Constantes.DIMENSION_X-Constantes.DIAMETRE_BALLE;
			//this.scoreJ1
			this.field.avantageJ1=true;
			this.field.scoreJ1++;
			this.field.notifyScore(this.field.scoreJ1, this.field.scoreJ2);
			this.field.reinit();
			return true;
		}
		return false;
	}

	public boolean collideBonus(BonusModel b) {
		// TODO Auto-generated method stub
		
		if((this.posX>=b.posX)&&(this.posX<=b.posX+Constantes.DIAMETRE_BONUS)
				&&(this.posY>=b.posY)&&(this.posY<=b.posY+Constantes.DIAMETRE_BONUS))
		{
			b.onScreen=false;
			return true;
		}
		return false;
	}
	
	public void update(Direction d)
	{
		if(d==Direction.NORD&&posY>Constantes.RAQUETTE_HEIGHT/2+Constantes.MUR_HEIGHT)
		{
			this.posY-=coefY;
		}
		if(d==Direction.SUD
				&&posY<Constantes.DIMENSION_Y-Constantes.MUR_HEIGHT-Constantes.RAQUETTE_HEIGHT/2)
		{
			this.posY+=coefY;
		}
		if(posY<0)posY=0;
		if(posY>Constantes.DIMENSION_Y)posY=Constantes.DIMENSION_Y;
		
		
	}
	
	
}
