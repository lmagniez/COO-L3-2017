package com.controler;


import com.model.AbstractModel;
import com.vue.titre.Vue1;

/**
 * Controler de la grille.
 * Permet la modification de celle-ci.
 * 
 * @author loick
 *
 */

public class GridControler extends AbstractControler{

	 public GridControler(AbstractModel g) {
		 this.calc=g;
	 }

	 /**
	  * Appeler le modèle pour modifier une ligne.
	  */
	public void control()
	{
		if(!this.calc.columnFull(x))
		{
			System.out.println("not full");
			this.calc.ajoutJeton(x);
			this.calc.verifWin();
		}
		else
			this.calc.notifyFull();
		
	}

	 
	

	
}
