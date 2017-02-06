package com.model.field;

import com.model.Constantes;

public class BallModel {
	
	protected int posX,posY;
	protected int coefX, coefY;
	
	public BallModel(int idJ)
	{
		if(idJ==1)
			this.posX=Constantes.BALLE_X_J1;
		else
			this.posX=Constantes.BALLE_X_J2;
		
		this.posY=Constantes.BALLE_Y;
		this.coefX=Constantes.BALLE_COEFX;
		this.coefY=Constantes.BALLE_COEFY;
		
	}
	
	public void updateBall()
	{
		posX+=coefX;
		posY+=coefY;
		
		System.out.println("update "+posX + " "+posY);
	}
	
	public void changeDirectionX()
	{
		System.out.println("x");
		coefX*=-1;
	}
	
	public void changeDirectionY()
	{
		System.out.println("y");
		coefY*=-1;
	}
	
	public boolean collideBall(BallModel b)
	{
		if((this.posX>=b.posX)&&(this.posX<=b.posX+Constantes.DIAMETRE_BALLE)
				&&(this.posY>=b.posY)&&(this.posY<=b.posY+Constantes.DIAMETRE_BALLE))
		{
			this.changeDirectionY();
			this.changeDirectionX();
			return true;
		}
		return false;
		
	}
	
	public boolean collideRacket(RacketModel r)
	{
		return (this.posX>=r.posX)&&(this.posX<=r.posX+r.width)
				&&(this.posY>=r.posY)&&(this.posY<=r.posY+r.height);
	}
	
	public boolean collideMurH(MurHModel m)
	{
		//if((this.posX>=m.posX)&&(this.posX<=m.posX+m.width)
		//		&&(this.posY>=m.posY)&&(this.posY<=m.posY+m.height))
		
		if((m.idMur==0)&&(this.posY<=m.posY+m.height))
		{
			this.changeDirectionY();
			return true;
		}
		if((m.idMur==1)&&(this.posY>m.posY))
		{
			this.changeDirectionY();
			return true;
		}
		
		
		return false;
	}
	
	public boolean collideMurVD()
	{
		return (this.posX<=0);
	}
	
	public boolean collideMurVG()
	{
		return (this.posX>=Constantes.DIMENSION_X);
	}
	
}
