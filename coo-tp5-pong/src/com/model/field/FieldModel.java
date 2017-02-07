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
	
	
	
	public FieldModel()
	{
		balls.add(new BallModel(this,1));
		balls.add(new BallModel(this,2));
		this.racketJ1=new RacketModel(0); 
		this.racketJ2=new RacketModel(1);
		this.murHaut=new MurHModel(0);
		this.murBas=new MurHModel(1);
		//this.bonus=new BonusModel(this);
		
		//System.out.println("bonus "+bonus.posX+ " "+bonus.posY);
		
	}


	


	@Override
	public void updateBall(int idB) {
		
		
		BallModel b=getBallById(idB);
		
		if(b!=null)b.collideMurH(murHaut);
		if(b!=null)b.collideMurH(murBas);
		if(b!=null)b.collideMurVG();
		if(b!=null)b.collideMurVD();
		if(b!=null)b.collideRacket(racketJ1);
		if(b!=null)b.collideRacket(racketJ2);
		
		for(int i=0; i<bonus.size(); i++)
		{
			System.out.println("FieldModel idBonus "+ bonus.get(i).id);
			if(b!=null)
			if(bonus.get(i).onScreen&&b.collideBonus(bonus.get(i)))
			{
				this.notifyEraseBonus(bonus.get(i).id);
				balls.add(new BallModel(this,1));
				//BallModel.nb_balle++;
				this.notifyNewBalle(BallModel.nb_balle, Constantes.DIMENSION_X/2, Constantes.DIMENSION_Y/2);
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
		
		/*
		if(balls.size()<=idB)return;
		this.balls.get(idB).collideMurH(murHaut);
		if(balls.size()<=idB)return;
		this.balls.get(idB).collideMurH(murBas);
		if(balls.size()<=idB)return;
		this.balls.get(idB).collideMurVG();
		if(balls.size()<=idB)return;
		this.balls.get(idB).collideMurVD();
		if(balls.size()<=idB)return;
		this.balls.get(idB).collideRacket(racketJ1);
		if(balls.size()<=idB)return;
		this.balls.get(idB).collideRacket(racketJ2);
		if(balls.size()<=idB)return;
		
		for(int i=0; i<bonus.size(); i++)
			if(bonus.get(i).onScreen&&this.balls.get(idB).collideBonus(bonus.get(i)))
			{
				this.notifyEraseBonus(0);
				BallModel.nb_balle++;
				this.notifyNewBalle(BallModel.nb_balle, Constantes.DIMENSION_X/2, Constantes.DIMENSION_Y/2);
			}
		
		for(int i=0; i<balls.size(); i++)
			if(balls.size()<=idB)return;
			else if(i!=idB)
				this.balls.get(idB).collideBall(balls.get(i));
		
		this.balls.get(idB).updateBall();
		
		this.notifyNewPosBall(idB, this.balls.get(idB).posX, this.balls.get(idB).posY);
		*/
	}


	@Override
	public void updateRacket(int idR, Direction d) {
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
	
	
	
	@Override
	public void reinit() {
		// TODO Auto-generated method stub
		
		BallModel.nb_balle=0;
		
		this.racketJ1.posY=Constantes.RAQUETTE_Y;
		this.racketJ2.posY=Constantes.RAQUETTE_Y;
		
		this.balls=new Vector<BallModel>();
		this.balls.add(new BallModel(this,1));
		
		this.notifyReinit();
		this.notifyNewBalle(BallModel.nb_balle, Constantes.DIMENSION_X/2, Constantes.DIMENSION_Y/2);
		
		
	}
	
}
