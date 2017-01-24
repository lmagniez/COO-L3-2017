package com.model;

import java.util.ArrayList;

public class CasesModel {

	
	protected int nbLigne;
	protected int nbJoueur;
	protected BoxValues[][] cases;
	protected int nbCases[];
	
	/**
	 * Construit un tableau de case et l'initialise
	 * @param nbLigne nombre de cases (nbLigne x nbLigne)
	 * @param nbJoueur nombre de joueurs (pour compter le nombre de cases par joueurs)
	 */
	public CasesModel(int nbLigne,int nbJoueur)
	{
		this.nbLigne=nbLigne;
		this.nbJoueur=nbJoueur;
		this.cases=new BoxValues[nbLigne][nbLigne];
		nbCases=new int[nbJoueur];
		reinit();
	}
	
	/**
	 * Réinitialise le tableau de case
	 */
	public void reinit()
	{
		for(int i=0; i<nbLigne; i++)
			for(int j=0; j<nbLigne; j++)
				cases[i][j]=BoxValues.NONE;

		for(int i=0; i<nbJoueur; i++)
			nbCases[i]=0;
	}
	
	/**
	 * Ajout d'un carré dans le modèle.
	 * La case doit être vide et la valeur non vide
	 * @param x abscisse de la case
	 * @param y ordonnée de la case
	 * @param v valeur de la case
	 * @return vrai si l'ajout a eu lieu.
	 */
	
	public boolean ajoutCarre(int x, int y, BoxValues v)
	{
		if(cases[x][y]==BoxValues.NONE&&v!=BoxValues.NONE)
		{
			cases[x][y]=v;
			nbCases[v.ordinal()]++;
			return true;
		}
		return false;
	}
	
	/**
	 * Test fin de partie
	 * @return true si fin de partie
	 */
	public boolean isGameOver()
	{
		int sum=0;
		for(int i=0; i<nbJoueur; i++)
			sum+=nbCases[i];
		return(sum==nbLigne*nbLigne);
	}
	
	/**
	 * Retourne le ou les gagnants de la partie (Vérifier au préalable que la partie est terminée
	 * @return Retourne une arraylist contenant la liste des vainqueurs
	 */
	public ArrayList<BoxValues> getWinner()
	{
		ArrayList<BoxValues> winner= new ArrayList<BoxValues>();
		int max=0;
		for(int i=0; i<nbJoueur; i++)
		{
			if(max<nbCases[i])
			{	
				max=nbCases[i];
				winner=new ArrayList<BoxValues>();
				winner.add(BoxValues.fromInteger(i));
			}
			else if(max==nbCases[i])
				winner.add(BoxValues.fromInteger(i));
		}
		return winner;
	}
		
}
