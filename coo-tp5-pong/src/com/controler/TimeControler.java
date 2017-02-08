package com.controler;

import com.model.AbstractModel;

public class TimeControler extends AbstractControler{

	
	 public TimeControler(AbstractModel g) {
		 super(g);
		 //this.calc=g;
	 }

	 /**
	  * Ajout d'un jeton.
	  * Appeler le modèle pour vérifier si la colonne d'abscisse x est pleine
	  * Si celle-ci n'est pas pleine, on ajoute le jeton.
	  * On vérifie ensuite si il y a un pattern impliquant la victoire du joueur. 
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
