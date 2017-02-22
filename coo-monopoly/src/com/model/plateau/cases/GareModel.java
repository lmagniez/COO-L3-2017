package com.model.plateau.cases;

import com.model.ConstantesModel;
import com.model.plateau.JoueurModel;
import com.model.plateau.PlateauModel;

public class GareModel extends CaseModel{

	public static int[] tabAssoGareJoueur;
	public static int posGares[]=new int[ConstantesModel.NB_GARES];
	protected int idGare;
	public static int nb_gare=0;
	
	protected int prixAchat;
	protected static int[] loyers={2500,5000,10000,20000};
	
	protected PlateauModel p;
	
	public static int[] initTab()
	{
		int[] tabAsso=new int[ConstantesModel.NB_GARES];
		for(int i=0; i<ConstantesModel.NB_GARES; i++){
			tabAsso[i]=-1;
		}
		return tabAsso;
	}
	
	public GareModel(PlateauModel p, int idCase, int position, String nom, int prixAchat)
	{
		this.p=p;
		this.idCase=idCase;
		this.setNom(nom);
		this.setPosition(position);
		this.idGare=nb_gare++;
		this.prixAchat=prixAchat;
		GareModel.posGares[idGare]=position;
	}
	
	public int getNbGareMemeJoueur()
	{
		int nb=0;
		for(int i=0; i<ConstantesModel.NB_GARES; i++)
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
