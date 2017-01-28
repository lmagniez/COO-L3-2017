package com.controler;

import com.model.AbstractModel;
import com.model.IllegalMoveException;

public class GrilleControler extends AbstractControler{

	 public GrilleControler(AbstractModel cal) {
		    super(cal);
	 }

	 public void control()
	 {

		 if(this.x>=0&&this.x<=AbstractModel.NB_LIGNE
					 &&this.y>=0&&this.y<=AbstractModel.NB_LIGNE)
			 this.calc.set_grille(x, y);
		 
	 }
	
}
