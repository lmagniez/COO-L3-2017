package com.model.plateau;

/**
 * Differents elements apparaissant dans la partie
 * @author loick
 *
 */
public class JeuModel {

	private PlateauModel p;
	
	public JeuModel(int nbJoueur, int posDepart, int sommeDepart)
	{
		this.p=new PlateauModel(nbJoueur, posDepart, sommeDepart);
	}
	
	public void lancerDe(JoueurModel j)
	{
		//gerer le cas en prison
	}
	
	public void lancerEnchere()
	{
		
	}
	
	public void lancerTour()
	{
		
	}
	
	public void lancerNegociation()
	{
		
	}
	
	public void ComblerDette(JoueurModel j)
	{
		
	}
	
}
