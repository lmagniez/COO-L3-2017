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
	
		boolean res[] = calc.peutPlacer(x,y,v);
		boolean peutPlacer=false;
		for(int i=0; i<8; i++){
			if(res[i]){
				peutPlacer=true;
				break;
			}
		}
		
		if(peutPlacer){
			this.calc.remplirCase(x, y, res, v);
		}
		
		this.calc.changerTour();
		if(!calc.peutJouer(this.calc.getTour())){
			this.calc.changerTour();
			if(!calc.peutJouer(this.calc.getTour()))
				this.calc.getWinner();
		}
		
		
		
			
	}

	 
	

	
}
