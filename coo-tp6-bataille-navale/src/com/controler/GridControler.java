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
		if(this.bateaux){
			this.bateaux=false;
			this.calc.notifierBateaux();
		}
		else if(this.sendServer)
		{
			this.sendServer=false;
			this.calc.sendToServer(msg);
		}
		else if(this.closeSocket){
			this.closeSocket=false;
			this.calc.closeSocket();
		}
		else if(!this.calc.isAlreadyShot(x, y))
		{
			this.calc.bombJoueur(x, y);
			this.calc.verifWin();
		}
		else
		{
			this.calc.notifyFull();
		}
	}

	 
	

	
}
