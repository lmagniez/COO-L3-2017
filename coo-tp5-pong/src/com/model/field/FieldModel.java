package com.model.field;

import java.util.ArrayList;

import com.model.AbstractModel;
import com.model.Constantes;
import com.model.Direction;
import com.model.grid.CaseValue;

public class FieldModel extends AbstractModel{

	protected ArrayList<BallModel> balls=new ArrayList<BallModel>();
	protected RacketModel racketJ1;
	protected RacketModel racketJ2;
	protected MurHModel murHaut;
	protected MurHModel murBas;
	
	
	
	public FieldModel()
	{
		balls.add(new BallModel(1));
		this.racketJ1=new RacketModel(0); 
		this.racketJ2=new RacketModel(1);
		this.murHaut=new MurHModel(0);
		this.murBas=new MurHModel(1);
			
	}


	


	@Override
	public void updateBall(int idB) {
		this.balls.get(idB).updateBall();
		
		this.balls.get(idB).collideMurH(murHaut);
		this.balls.get(idB).collideMurH(murBas);
		
		this.notifyNewPosBall(idB, this.balls.get(idB).posX, this.balls.get(idB).posY, Constantes.DIAMETRE_BALLE);
	}


	@Override
	public void updateRacket(int idR, Direction d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reinit() {
		// TODO Auto-generated method stub
		
	}
	
}
