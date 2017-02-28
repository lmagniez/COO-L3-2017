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
		
		if(this.requestGrilles){
			this.calc.requestGrilles();
			this.requestGrilles=false;
		}
		else if(this.requestGrilleDetail){
			this.calc.requestGrilleDetail(idPuzzle);
			this.requestGrilleDetail=false;
		}
		else if(this.requestVerifWin){
			this.calc.verifWin(idPuzzle);
			this.requestVerifWin=false;
		}
		else
			this.calc.changeValue(idPuzzle, x, y);
		
		/*
		if(!this.calc.columnFull(x))
		{
			this.calc.ajoutJeton(x);
			this.calc.verifWin();
		}
		else
			this.calc.notifyFull();
		*/
	}

	 
	

	
}
