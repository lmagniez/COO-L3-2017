package com.controler;

import com.model.AbstractModel;

/**
 * Classe controler des evenements.
 * Demande l'apparition des bonus et l'augmentation de vitesse
 * @author loick
 *
 */

public class TimeControler extends AbstractControler{

	
	 public TimeControler(AbstractModel g) {
		 super(g);
		 //this.calc=g;
	 }

	/**
	 * Demande l'ajout d'un bonus et/ou l'augmentation de vitesse des balles
	 */
	public void control()
	{
		
		if(bonus)
		{
			if(!this.calc.isStopped())
				this.calc.ajoutBonus();
			bonus=false;
		}
		
		if(speed)
		{
			if(!this.calc.isStopped())
				this.calc.augmenteVitesse();
			speed=false;
		}
		
	}

}
