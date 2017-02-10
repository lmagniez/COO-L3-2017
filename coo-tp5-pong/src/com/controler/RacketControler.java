package com.controler;

import com.model.AbstractModel;

/**
 * Classe controler de la racket, lance la balle et dirige la raquette
 * @author loick
 *
 */
public class RacketControler extends AbstractControler{

	
	 public RacketControler(AbstractModel g) {
		 super(g);
		 //this.calc=g;
	 }

	 /**
	  * Demande le lancement de la balle, sinon:
	  * Demande le déplacement de la raquette
	  */
	public void control()
	{
		if(throwBall)
		{
			this.calc.throwBall();
			throwBall=false;
		}
		else
			this.calc.updateRacket(id, d);
	}

}
