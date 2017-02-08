package com.controler;

import com.model.AbstractModel;

public class RacketControler extends AbstractControler{

	
	 public RacketControler(AbstractModel g) {
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
		if(throwBall)
		{
			this.calc.throwBall();
			throwBall=false;
		}
		else
			this.calc.updateRacket(id, d);
	}

}
