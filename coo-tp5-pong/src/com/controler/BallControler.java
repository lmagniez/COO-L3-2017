package com.controler;

import com.model.AbstractModel;

public class BallControler extends AbstractControler {


	public BallControler(AbstractModel g) {
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
		if(!this.calc.isStopped())
			this.calc.updateBall(this.id);
		
	}
}