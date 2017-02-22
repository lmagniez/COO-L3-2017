package com.controler;

import com.model.AbstractModel;
import com.model.IllegalMoveException;

public class GameControler extends AbstractControler{

	 public GameControler(AbstractModel cal) {
		    super(cal);
	 }

	 public void control()
	 {
		 
		 if(this.lancerDes){
			 this.calc.lancerDes(idJoueur);
		 }

		 /*
		 if(this.x>=0&&this.x<=AbstractModel.NB_LIGNE
					 &&this.y>=0&&this.y<=AbstractModel.NB_LIGNE)
			 this.calc.set_grille(x, y);
		 */
	 }
	
}
