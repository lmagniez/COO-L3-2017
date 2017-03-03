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
	  * Controle
	  * RequestGrille: demande au modèle de notifier les grilles
	  * RequestGenererGrille: demande au modele de générer les grilles
	  * RequestGrilleDetail: demande de notifier une grille a la vue
	  * RequestVerifWin: demande au modèle de vérifier la grille (victoire?)
	  * Creation: Change la valeur d'une case de la grille de création
	  * CreationGrille: Demande la création de la grille de création
	  * requestSave: Demande la sauvegarde de la grille de création
	  */
	public void control()
	{
		
		if(this.requestGrilles){
			this.calc.requestGrilles();
			this.requestGrilles=false;
		}
		else if(this.requestGenererGrille){
			this.calc.genererGrilles();
			this.requestGenererGrille=false;
		}
		else if(this.requestGrilleDetail){
			this.calc.requestGrilleDetail(idPuzzle);
			this.requestGrilleDetail=false;
		}
		else if(this.requestVerifWin){
			this.calc.verifWin(idPuzzle);
			this.requestVerifWin=false;
		}
		else if(this.creation){
			this.calc.changeValueCreation(x, y);
			this.creation=false;
		}
		else if(this.creationGrille){
			this.calc.createGrille(nom,nbRow,nbCol);
			this.creationGrille=false;
		}
		else if(this.requestSave){
			this.calc.saveTable();
			this.requestSave=false;
		}
		else if(this.requestUpdateReussite){
			this.calc.updateReussite();
			this.requestUpdateReussite=false;
		}
		else
			this.calc.changeValue(idPuzzle, x, y);
			
	}

	 
	

	
}
