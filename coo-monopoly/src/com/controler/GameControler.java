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
			 lancerDes=false;
		 }

		 if(this.achat){
			 
			 System.out.println("requestAchat "+positionAchat+" idJ "+idJoueur);
			 
			 if(this.calc.hasEnoughMoney(idJoueur, this.positionAchat)){
				 this.calc.achatCase(idJoueur, this.positionAchat);
				 this.calc.tourSuivant();
			 }
			 else{
				 this.calc.notifyMessageChoix("Pas assez d'argent !");
			 }
			 this.achat=false;
		 }
		 
		 if(this.paiement){
			 
			 this.calc.paiementJoueur(idJoueur, idJoueur2, positionAchat);
			 if(this.calc.isInDebt(idJoueur))
				 this.calc.comblerDette(idJoueur);
			 else
				 this.calc.tourSuivant();
			 
			 this.paiement=false;
		 }
		
		 
		 if(this.enchere){
			 this.enchere=false;
			 this.calc.tourSuivant();
		 }
		 
		 
		 /*
		 if(this.x>=0&&this.x<=AbstractModel.NB_LIGNE
					 &&this.y>=0&&this.y<=AbstractModel.NB_LIGNE)
			 this.calc.set_grille(x, y);
		 */
	 }
	
}
