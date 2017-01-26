package com.controler;


import com.model.AbstractModel;
import com.model.BoxValues;
import com.model.Direction;
import com.model.GrilleModel;
import com.vue.titre.Vue1;

public class GrilleControler extends AbstractControler{

	 public GrilleControler(AbstractModel g) {
		 this.calc=g;
	 }

	 public void control()
	 {
		 if(vertical==false)
			 this.calc.ajoutTraitH(x, y);
		 else
			 this.calc.ajoutTraitV(x, y);
	 }

	@Override
	public void genererJeu(Vue1 vue, int nbLigne, int nbJoueur, boolean[] isIA) {
		// TODO Auto-generated method stub
		
	}

	
}
