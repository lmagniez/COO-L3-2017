package com.model.plateau.cases;

import com.model.Constantes;
import com.model.plateau.JoueurModel;
import com.model.plateau.PlateauModel;

public class GareModel extends CaseModel{

	public static int[] tabAssoGareJoueur=initTab();
	public static int posGares[]=new int[Constantes.NB_GARES];
	protected int idGare;
	protected static int nb_gare=0;
	
	protected int prixAchat;
	protected static int[] loyers={2500,5000,10000,20000};
	
	protected PlateauModel p;
	
	public static int[] initTab()
	{
		int[] tabAsso=new int[Constantes.NB_GARES];
		for(int i=0; i<Constantes.NB_GARES; i++){
			tabAssoGareJoueur[i]=-1;
		}
		return tabAsso;
	}
	
	public GareModel(PlateauModel p, int idCase, int position, String nom, int prixAchat)
	{
		this.p=p;
		this.idCase=idCase;
		this.position=position;
		this.idGare=nb_gare++;
		this.prixAchat=prixAchat;
		GareModel.posGares[idGare]=position;
	}
	
	public int getNbGareMemeJoueur()
	{
		int nb=0;
		for(int i=0; i<Constantes.NB_GARES; i++)
			if(tabAssoGareJoueur[i]==tabAssoGareJoueur[idGare])
				nb++;
		return nb;
	}

	public void associer(JoueurModel j)
	{
		TerrainModel.tabAssoTerrainJoueur[this.idGare]=j.getIdJoueur();
		j.setArgent(j.getArgent()-prixAchat);
	}
	
	@Override
	public void action(JoueurModel j) {
		
		//choisir déplacement
		
		
		//payer loyer
		int idPossesseur=tabAssoGareJoueur[this.idGare];
		
		if(idPossesseur!=-1){
			int nbGareJ= getNbGareMemeJoueur();
			JoueurModel possesseur=this.p.getJoueurs()[idPossesseur];
			//joueur different du possesseur de la case: transfert argent
			if(j.getIdJoueur()!=idPossesseur){
				possesseur.setArgent(possesseur.getArgent()+loyers[nbGareJ]);
				j.setArgent(j.getArgent()-loyers[nbGareJ]);
			}
		}
		
	}
	
}