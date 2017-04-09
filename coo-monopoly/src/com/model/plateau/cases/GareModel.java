package com.model.plateau.cases;

import com.model.ConstantesModel;
import com.model.plateau.JoueurModel;
import com.model.plateau.PlateauModel;

/**
 * Modèle de la gare
 * @author loick
 *
 */
public class GareModel extends CaseModel{

	public static int[] tabAssoGareJoueur;
	public static int posGares[]=new int[ConstantesModel.NB_GARES];
	protected int idGare;
	public static int nb_gare=0;
	
	protected int prixAchat;
	protected static int[] loyers={2500,5000,10000,20000};
	
	protected PlateauModel p;
	
	/**
	 * Initialisé le tableau static
	 * @return tableau associatif des gares aux joueurs
	 */
	public static int[] initTab()
	{
		int[] tabAsso=new int[ConstantesModel.NB_GARES];
		for(int i=0; i<ConstantesModel.NB_GARES; i++){
			tabAsso[i]=-1;
////////
		}
		return tabAsso;
	}
	/**
	 * Constructeur
	 * @param p modèle du plateau
	 * @param idCase id de la case
	 * @param position position de la case
	 * @param nom nom de la case
	 * @param prixAchat prix d'achat de la case
	 */
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
	
	/**
	 * Retourne le nombre de gare possédés par le même joueur
	 * @return nombre de gares ayant le même propriétaire
	 */
	public int getNbGareMemeJoueur()
	{
		int nb=0;
		for(int i=0; i<ConstantesModel.NB_GARES; i++)
			if(tabAssoGareJoueur[i]==tabAssoGareJoueur[idGare])
				nb++;
		return nb;
	}

	/**
	 * Associer la gare à un joueur
	 * @param j joueur disposant de la gare
	 */
	public void associer(JoueurModel j)
	{
		TerrainModel.tabAssoTerrainJoueur[this.idGare]=j.getIdJoueur();
		j.setArgent(j.getArgent()-prixAchat);
	}
	
	public void associerEchange(JoueurModel j)
	{
		TerrainModel.tabAssoTerrainJoueur[this.idGare]=j.getIdJoueur();
	}
	
	/**
	 * Hypothequer
	 * @param j joueur disposant du terrain
	 */
	public void hypothequer(JoueurModel j)
	{
		GareModel.tabAssoGareJoueur[idGare]=-1;
		j.setArgent(j.getArgent()+prixAchat/2);
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
				/*
				possesseur.setArgent(possesseur.getArgent()+loyers[nbGareJ]);
				j.setArgent(j.getArgent()-loyers[nbGareJ]);
				this.p.getModel().tourSuivant();
				*/
				this.p.getModel().notifyPaiementCase(j.getIdJoueur(), idPossesseur, this.getPosition());
				
			}
			else
			{
				this.p.getModel().tourSuivant();
			}
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
		GareModel.loyers = loyers;
	}

	public int getIdGare() {
		return idGare;
	}

	public void setIdGare(int idGare) {
		this.idGare = idGare;
	}
	
	
	
}
