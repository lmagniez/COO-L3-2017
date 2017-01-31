package com.controler;


import com.model.AbstractModel;
import com.model.BoxValues;
import com.model.Direction;
import com.model.GrilleModel;
import com.vue.titre.Vue1;

/**
 * Controler de la grille.
 * Permet la modification de celle-ci.
 * 
 * @author loick
 *
 */

public class GrilleControler extends AbstractControler{

	 public GrilleControler(AbstractModel g) {
		 this.calc=g;
	 }

	 /**
	  * Appeler le modèle pour modifier une ligne.
	  */
	public void control()
	{
		if(vertical==false)
			this.calc.ajoutTraitH(x, y);
		else
			this.calc.ajoutTraitV(x, y);
	}

	 
	/**
	 * Non utilisé pour ce controler (problème de conception?).
	 */
	@Override
	public void genererJeu(Vue1 vue, int nbLigne, int nbJoueur, boolean[] isIA) {
		// TODO Auto-generated method stub
		
	}

	
}
