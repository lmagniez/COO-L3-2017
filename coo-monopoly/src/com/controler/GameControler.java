package com.controler;

import com.model.AbstractModel;

/**
 * Controler du jeu, demande les différentes actions de jeu
 * @author loick
 *
 */
public class GameControler extends AbstractControler{

	 public GameControler(AbstractModel cal) {
		    super(cal);
	 }
	 
	 /**
	  * Méthode de contrôle
	  */
	 public void control()
	 {
		 
		 if(this.lancerDes){
			 this.calc.lancerDes(idJoueur);
			 lancerDes=false;
		 }

		 if(this.achat){
			 
			 
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
			 this.calc.tourSuivant();
			 if(this.calc.isInDebt(idJoueur))
				 this.calc.comblerDette(idJoueur); 
			 
			 
			 this.paiement=false;
		 }
		
		 
		 if(this.enchere){
			 this.enchere=false;
			 this.calc.tourSuivant();
		 }
		 
		 if(this.achatMaison){
		 
			this.achatMaison=false;
			this.calc.achatMaison(idJoueur, positionAchat);
		 }
		 if(this.venteMaison){
			 this.venteMaison=false;
			 this.calc.venteMaison(idJoueur,positionAchat);
		 }
		 
		 if(this.echangePropriete){
			this.echangePropriete=false;
			this.calc.echangePropriete(idJoueur, idJoueur2, positionAchat, somme);
			control();
		 }
		 
		 if(this.effetPioche){
			 this.effetPioche=false;
			 this.calc.effetPioche(idJoueur, idCarte, this.type);
			 this.calc.tourSuivant();
		 }
		 if(this.hypotheque){
			 this.hypotheque=false;
			 this.calc.hypothequer(idJoueur, positionAchat);
		 }
		 
		 if(this.gameOver){
			 this.gameOver=false;
			 this.calc.gameOver(idJoueur);
		 }
		 if(this.tour){
			 this.tour=false;
			 this.calc.tourSuivant();
		 }
		 
	 }
	
}
