package com.model.plateau;

import java.util.Random;

import com.model.AbstractModel;
import com.model.ConstantesModel;
import com.model.ConstantesParam;

/**
 * Differents elements apparaissant dans la partie
 * @author loick
 *
 */
public class JeuModel extends AbstractModel{

	private PlateauModel p;
	protected int tour;
	
	public JeuModel(int nbJoueur, int posDepart, int sommeDepart)
	{
		this.setP(new PlateauModel(this,nbJoueur, posDepart, sommeDepart));
		this.tour=0;
	}
	
	/**
	 * lancer un tour, notifier la vue du nouveau joueur devant jouer (ajoute bouton lancer dés)
	 */
	public void lancerTour()
	{
		this.notifyTour(tour);
	}
	
	/**
	 * Lance le dé après que le joueur l'ai demandé.
	 * @param j
	 */
	public void lancerDes(int idJoueur)
	{
		JoueurModel j=this.getP().joueurs[idJoueur];
		//gerer le cas en prison
		Random r= new Random();
		int des=2+r.nextInt(10);
		
		j.lastSumDes=des;
		j.position=(j.position+des)%ConstantesModel.NB_CASES;
		
		this.notifyPosJoueur(j.idJoueur, j.position);
		this.p.cases[j.position].action(j);
		
		
		
		tour=(tour+1)%ConstantesParam.NB_JOUEURS;
		lancerTour();
		
	}
	
	public void lancerEnchere()
	{
		
	}
	
	
	
	public void lancerNegociation()
	{
		
	}
	
	public void comblerDette(JoueurModel j)
	{
		
	}

	public PlateauModel getP() {
		return p;
	}

	public void setP(PlateauModel p) {
		this.p = p;
	}
	
}
