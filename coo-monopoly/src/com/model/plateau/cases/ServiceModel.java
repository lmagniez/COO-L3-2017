package com.model.plateau.cases;

import com.model.ConstantesModel;
import com.model.plateau.JoueurModel;
import com.model.plateau.PlateauModel;

public class ServiceModel extends CaseModel{

	public static int[] tabAssoServiceJoueur=initTab();
	protected int idService;
	protected static int nb_service=0;
	
	protected int prixAchat;
	protected static int[] loyers={400,1000};
	
	protected PlateauModel p;
	
	public ServiceModel(PlateauModel p, int idCase, int position, String nom, int prixAchat)
	{
		this.p=p;
		this.setNom(nom);
		this.idCase=idCase;
		this.position=position;
		this.idService=nb_service++;
		this.prixAchat=prixAchat;
	}
	
	public static int[] initTab()
	{
		int[] tabAsso=new int[ConstantesModel.NB_CASES_SERVICES];
		for(int i=0; i<ConstantesModel.NB_CASES_SERVICES; i++){
			tabAssoServiceJoueur[i]=-1;
		}
		return tabAsso;
	}
	
	public int getNbServiceMemeJoueur()
	{
		int nb=0;
		for(int i=0; i<ConstantesModel.NB_GARES; i++)
			if(tabAssoServiceJoueur[i]==tabAssoServiceJoueur[idService])
				nb++;
		return nb;
	}
	
	public void associer(JoueurModel j)
	{
		TerrainModel.tabAssoTerrainJoueur[this.idService]=j.getIdJoueur();
		j.setArgent(j.getArgent()-prixAchat);
	}
	
	@Override
	public void action(JoueurModel j) {
		int idPossesseur=tabAssoServiceJoueur[this.idService];
		
		if(idPossesseur!=-1){
			//payer loyer
			int nbServJ= getNbServiceMemeJoueur();
			JoueurModel possesseur=this.p.getJoueurs()[idPossesseur];
			//joueur different du possesseur de la case: transfert argent
			if(j.getIdJoueur()!=idPossesseur){
				possesseur.setArgent(possesseur.getArgent()+j.getLastSumDes()*loyers[nbServJ]);
				j.setArgent(j.getArgent()-j.getLastSumDes()*loyers[nbServJ]);
			}
		}
	}

}
