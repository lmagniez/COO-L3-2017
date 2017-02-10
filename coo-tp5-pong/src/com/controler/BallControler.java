package com.controler;

import com.model.AbstractModel;

/**
 * Classe controler de la balle
 * Fait déplacer la balle
 * @author loick
 *
 */
public class BallControler extends AbstractControler {


	public BallControler(AbstractModel g) {
		 super(g);
		 //this.calc=g;
	 }

	/**
	 * Demande la mise à jour de la balle si la balle n'est pas arrêtée.
	 */
	public void control()
	{	
		if(!this.calc.isStopped())
			this.calc.updateBall(this.id);
		
	}
}
