package com.model.plateau.cases;

import com.model.ConstantesModel;
import com.model.ConstantesParam;
import com.model.plateau.JoueurModel;
import com.model.plateau.PlateauModel;

/**
 * Modèle représentant les cases services
 * @author loick
 *
 */
public class ServiceModel extends CaseModel{

	public static int[] tabAssoServiceJoueur;
	protected int idService;
	private static int nb_service=0;
	
	protected int prixAchat;
	protected static int[] loyers={400,1000};
	
	protected PlateauModel p;
	
	/**
	 * Constructeur XML
	 * @param p modèle du plateau
	 * @param idCase id de la case
	 * @param position position de la case
	 * @param nom nom de la case
	 * @param prixAchat prix d'achat
	 * @param idService 
	 */
	public ServiceModel(PlateauModel p, int idCase, int position, String nom, int prixAchat, int idService)
	{
		this.p=p;
		this.setNom(nom);
		this.idCase=idCase;
		this.setPosition(position);
		this.idService=idService;
		this.prixAchat=(int) (prixAchat*ConstantesParam.TAUX_ACHAT);
	}
	
	/**
	 * Constructeur
	 * @param p modèle du plateau
	 * @param idCase id de la case
	 * @param position position de la case
	 * @param nom nom de la case
	 * @param prixAchat prix d'achat
	 */
	public ServiceModel(PlateauModel p, int idCase, int position, String nom, int prixAchat)
	{
		this.p=p;
		this.setNom(nom);
		this.idCase=idCase;
		this.setPosition(position);
		this.idService=nb_service++;
		this.prixAchat=(int) (prixAchat*ConstantesParam.TAUX_ACHAT);;
	}
	
	/**
	 * Initialiser le tableau associatif
	 * @return tableau associatif initialisé à -1 (aucun joueur n'a de services)
	 */
	public static int[] initTab()
	{
		int[] tabAsso=new int[ConstantesModel.NB_CASES_SERVICES];
		for(int i=0; i<ConstantesModel.NB_CASES_SERVICES; i++){
			tabAsso[i]=-1;
		}
		return tabAsso;
	}
	
	/**
	 * Retourne le nombre de services appartenant au meme joueur
	 * @return nombre de services appartenant au meme joueur
	 */
	public int getNbServiceMemeJoueur()
	{
		int nb=0;
		for(int i=0; i<ConstantesModel.NB_CASES_SERVICES; i++)
			if(tabAssoServiceJoueur[i]==tabAssoServiceJoueur[idService])
				nb++;
		return nb;
	}
	
	/**
	 * Associer le service à un joueur
	 * @param j joueur disposant du service
	 */
	public void associer(JoueurModel j)
	{
		TerrainModel.tabAssoTerrainJoueur[this.idService]=j.getIdJoueur();
		j.setArgent(j.getArgent()-prixAchat);
		j.setValeurPatrimoine(j.getValeurPatrimoine()+prixAchat);
		
	}
	
	public void associerEchange(JoueurModel j)
	{
		JoueurModel j1=this.p.getJoueurs()[ServiceModel.tabAssoServiceJoueur[idService]];
		TerrainModel.tabAssoTerrainJoueur[this.idService]=j.getIdJoueur();
		j1.setValeurPatrimoine(j1.getValeurPatrimoine()-prixAchat);
		j.setValeurPatrimoine(j.getValeurPatrimoine()+prixAchat);
		
	}
	
	
	/**
	 * Hypothequer
	 * @param j joueur disposant du terrain
	 */
	public void hypothequer(JoueurModel j)
	{
		ServiceModel.tabAssoServiceJoueur[idService]=-1;
		j.setArgent((int) (j.getArgent()+prixAchat*ConstantesParam.TAUX_INTERET));
		j.setValeurPatrimoine(j.getValeurPatrimoine()-prixAchat);
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
				
				this.p.getModel().notifyPaiementCase(j.getIdJoueur(), idPossesseur, this.getPosition());
				/*
				possesseur.setArgent(possesseur.getArgent()+j.getLastSumDes()*loyers[nbServJ]);
				j.setArgent(j.getArgent()-j.getLastSumDes()*loyers[nbServJ]);*/
			}
			this.p.getModel().tourSuivant();
		}
		else{
			
			this.p.getModel().notifyAchatCase(j.getIdJoueur(), j.getPosition());
		}
		
	}

	public int getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(int prixAchat) {
		this.prixAchat = prixAchat;
	}

	public static int[] getLoyers() {
		return loyers;
	}

	public static void setLoyers(int[] loyers) {
		ServiceModel.loyers = loyers;
	}

	public int getIdService() {
		return idService;
	}

	public void setIdService(int idService) {
		this.idService = idService;
	}

	public static int getNb_service() {
		return nb_service;
	}

	public static void setNb_service(int nb_service) {
		ServiceModel.nb_service = nb_service;
	}

	
	
}
