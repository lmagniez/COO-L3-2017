package com.controler;


import java.util.ArrayList;

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
	 }

	/**
	 * Méthode de controle
	 * 1- Vérifie si on peut placer la case aux coordonées (x,y) pour la valeur v
	 * 2- Place et change de valeur les cases touchés par l'action du joueur
	 * 3- Change le tour et vérifie si le prochain joueur peut jouer
	 * 		-Si il ne peut pas jouer, on change à nouveau le tour
	 * 		-Si le 2e joueur ne peut pas jouer, fin de partie
	 * 4- Notifie les positions où le joueur peut placer ses pions
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
		
		
			this.calc.changerTour();
			
			ArrayList<int[]> liste= this.calc.peutJouer(this.calc.getTour());
			
			if(liste.size()==0){
				this.calc.changerTour();
				liste= this.calc.peutJouer(this.calc.getTour());
				if(liste.size()==0){
					this.calc.getWinner();
					this.calc.stopIA();
				}
			}
			
			this.calc.notifyPosJouable(liste);
			
		}
		
		
			
	}

	 
	

	
}
