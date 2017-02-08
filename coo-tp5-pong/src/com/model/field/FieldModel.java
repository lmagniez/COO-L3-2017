package com.model.field;

import java.util.ArrayList;
import java.util.Vector;

import com.model.AbstractModel;
import com.model.Constantes;
import com.model.Direction;

public class FieldModel extends AbstractModel{

	protected Vector<BallModel> balls=new Vector<BallModel>();
	protected RacketModel racketJ1;
	protected RacketModel racketJ2;
	protected MurHModel murHaut;
	protected MurHModel murBas;
	protected Vector<BonusModel> bonus=new Vector<BonusModel>();
	protected int actualSpeed=1;
	protected boolean gameStopped=false;
	protected boolean avantageJ1=true;
	protected int scoreJ1,scoreJ2;
	
	
	
	public FieldModel()
	{
		balls.add(new BallModel(this,1));
		this.racketJ1=new RacketModel(0); 
		this.racketJ2=new RacketModel(1);
		this.murHaut=new MurHModel(0);
		this.murBas=new MurHModel(1);
		this.gameStopped=true;
		this.scoreJ1=0;
		this.scoreJ2=0;
		
		//this.bonus=new BonusModel(this);
		
		//System.out.println("bonus "+bonus.posX+ " "+bonus.posY);
		
	}


	public boolean isStopped(){
		return gameStopped;
	}


	@Override
	public void updateBall(int idB) {
		
		BallModel b=getBallById(idB);
		
		if(b!=null)b.collideMurH(murHaut);
		if(b!=null)b.collideMurH(murBas);
		if(b!=null)if(b.collideMurVG())return;
		if(b!=null)if(b.collideMurVD())return;
		if(b!=null)b.collideRacket(racketJ1);
		if(b!=null)b.collideRacket(racketJ2);
		
		for(int i=0; i<bonus.size(); i++)
		{
			if(b!=null)
			if(bonus.get(i).onScreen&&b.collideBonus(bonus.get(i)))
			{
				this.notifyEraseBonus(bonus.get(i).id);
				balls.add(new BallModel(this,b.posX+Constantes.DIAMETRE_BALLE,b.posY+Constantes.DIAMETRE_BALLE,b.coefX,-b.coefY));
				
				
				
				//BallModel.nb_balle++;
				this.notifyNewBalle(BallModel.nb_balle, 
						balls.get(balls.size()-1).posX, 
						balls.get(balls.size()-1).posY);
			}
		}
		for(int i=0; i<balls.size(); i++)
			if(b!=null)
			if(i!=idB)
				b.collideBall(balls.get(i));
		if(b!=null)
		b.updateBall();
		if(b!=null)
		this.notifyNewPosBall(idB, b.posX, b.posY);
	
	}


	@Override
	public void updateRacket(int idR, Direction d) {
		
		if((gameStopped&&avantageJ1&&idR==0)
				||(gameStopped&&!avantageJ1&&idR==1))
		{
			for(int i=0; i<balls.size(); i++){
				BallModel b=balls.get(i);
				b.update(d);
				this.notifyNewPosBall(b.id, b.posX, b.posY);
			}
		}
		
		if(idR==0){
			this.racketJ1.update(d);
			this.notifyNewPosRacket(0, racketJ1.posX, racketJ1.posY);
		}
		if(idR==1){
			this.racketJ2.update(d);
			this.notifyNewPosRacket(1, racketJ2.posX, racketJ2.posY);
		}
	}

	
	public void ajoutBonus()
	{
		BonusModel b=new BonusModel(this);
		this.bonus.add(b);
		this.notifyNewBonus(b.id, b.posX, b.posY);
	}
	
	public BallModel getBallById(int id)
	{
		//System.out.println("fieldModel id: "+id);
		for(int i=0; i<balls.size(); i++)
		{
			//System.out.println("i:"+i+" "+balls.get(i).id);
			if(balls.get(i).id==id)
				return balls.get(i);
		}
		return null;
	}
	
	
	public void augmenteVitesse()
	{
		this.actualSpeed+=1;
	}
	
	public void throwBall()
	{
		this.gameStopped=false;
	}
	
	
	@Override
	public void reinit() {
		// TODO Auto-generated method stub
		
		BallModel.nb_balle=0;
		BonusModel.nb_bonus=0;
		
		this.racketJ1.posY=Constantes.RAQUETTE_Y;
		this.racketJ2.posY=Constantes.RAQUETTE_Y;
		
		this.bonus=new Vector<BonusModel>();
		this.balls=new Vector<BallModel>();
		BallModel b;
		if(avantageJ1) b = new BallModel(this,1);
		else b = new BallModel(this,2);
			
			
		this.balls.add(b);
		
		
		
		//System.out.println("balle "+b.posX+" "+b.posY);
		
		this.actualSpeed=1;
		this.gameStopped=true;
		
		this.notifyReinit();
		this.notifyNewBalle(BallModel.nb_balle, b.posX, b.posY);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
}
