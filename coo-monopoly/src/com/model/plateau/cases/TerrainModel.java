package com.model.plateau.cases;

import java.util.Random;

import com.model.ConstantesModel;
import com.model.ConstantesParam;
import com.model.plateau.JoueurModel;
import com.model.plateau.PlateauModel;

/**
 * Classe représentant un modèle de terrain
 * @author loick
 *
 */
public class TerrainModel extends CaseModel{

	
	public static int[] tabAssoTerrainJoueur;
	protected CouleurTerrain couleurTerrain;
	
	protected int idTerrain;
	protected int nbMaisons;
	
	protected int prixAchat;
	protected int[] loyers;
	protected int prixMaison;//5000 marron, 10000 violet, orange 15000 rouge et jaune, 20000 vert
	
	
	protected PlateauModel p;
	private static int nbTerrain=0;
	
	/**
	 * Initialiser le tableau associatif des terrains
	 * @return tableau associatif initialisé à -1 (aucun joueur ne dispose de terrains
	 */
	public static int[] initTab()
	{
		int[] tabAsso=new int[ConstantesModel.NB_CASES_TERRAIN];
		for(int i=0; i<ConstantesModel.NB_CASES_TERRAIN; i++){
			tabAsso[i]=-1;
			
			
			
			//pour tester, associe toute les propriétés au j2
			if(ConstantesParam.J1_GOD_MODE)
				tabAsso[i]=1;

		}
		return tabAsso;
	}
	
	/**
	 * Constructeur pour XML
	 * @param p modèle du plateau
	 * @param idCase id de la case
	 * @param position position de la case
	 * @param couleurTerrain couleur du terrain
	 * @param nom nom de la case
	 * @param prixAchat prix d'achat de la case
	 * @param loyers differents loyers de la case
	 * @param prixMaison prix d'une maison
	 * @param nbMaisons
	 * @param idTerrain
	 */
	public TerrainModel(PlateauModel p, int idCase, int position, CouleurTerrain couleurTerrain, String nom
			,int prixAchat, int[] loyers, int prixMaison, int nbMaisons, int idTerrain)
	{
		this.p=p;
		this.couleurTerrain=couleurTerrain;
		this.idCase=idCase;
		this.setPosition(position);
		this.nom=nom;
		this.prixAchat=(int) (prixAchat*ConstantesParam.TAUX_ACHAT);
		this.loyers=loyers;
		this.prixMaison=prixMaison;
		this.nbMaisons=0;
		this.idTerrain=idTerrain;
	}
	
	/**
	 * Constructeur
	 * @param p modèle du plateau
	 * @param idCase id de la case
	 * @param position position de la case
	 * @param couleurTerrain couleur du terrain
	 * @param nom nom de la case
	 * @param prixAchat prix d'achat de la case
	 * @param loyers differents loyers de la case
	 * @param prixMaison prix d'une maison
	 */
	public TerrainModel(PlateauModel p, int idCase, int position, CouleurTerrain couleurTerrain, String nom
			,int prixAchat, int[] loyers, int prixMaison)
	{
		this.idTerrain=nbTerrain++;
		this.p=p;
		this.couleurTerrain=couleurTerrain;
		this.idCase=idCase;
		this.setPosition(position);
		this.nom=nom;
		this.prixAchat=(int) (prixAchat*ConstantesParam.TAUX_ACHAT);
		this.loyers=loyers;
		this.prixMaison=prixMaison;
		this.nbMaisons=0;
	}
	
	/**
	 * Associer un joueur au terrain
	 * @param j joueur disposant du terrain
	 */
	public void associer(JoueurModel j)
	{
		TerrainModel.tabAssoTerrainJoueur[idTerrain]=j.getIdJoueur();
		j.setArgent(j.getArgent()-prixAchat);
		j.setValeurPatrimoine(j.getValeurPatrimoine()+prixAchat);
	}
	
	/**
	 * Associer sans payer
	 * @param j joueur disposant du terrain
	 */
	public void associerEchange(JoueurModel j)
	{
		JoueurModel j1=this.p.getJoueurs()[TerrainModel.tabAssoTerrainJoueur[idTerrain]];
		TerrainModel.tabAssoTerrainJoueur[idTerrain]=j.getIdJoueur();
		j1.setValeurPatrimoine(j1.getValeurPatrimoine()-prixAchat);
		j.setValeurPatrimoine(j.getValeurPatrimoine()+prixAchat);
	}
	
	/**
	 * Hypothequer
	 * @param j joueur disposant du terrain
	 */
	public void hypothequer(JoueurModel j)
	{
		TerrainModel.tabAssoTerrainJoueur[idTerrain]=-1;
		j.setArgent((int) (j.getArgent()+prixAchat*ConstantesParam.TAUX_INTERET));
		j.setValeurPatrimoine(j.getValeurPatrimoine()-prixAchat);
	}
	
	
	
	/**
	 * Doit appartenir à un joueur et les autres cases ont entre -1 et 0 maisons de différence
	 */
	public void ajouterMaison()
	{
		JoueurModel j=p.getJoueurs()[tabAssoTerrainJoueur[idTerrain]];
		
		if(resteDesMaisons()&&(peutAjouterMaison()||ConstantesParam.EGALISATION_ENABLED)){
			this.nbMaisons++;
			//met a jour le nombre d'hotel total si besoin
			if(nbMaisons==5){
				if(resteDesHotels()){
					j.setNbMaison(j.getNbMaison()-5);
					j.setNbHotel(j.getNbHotel()+1);
					this.p.getModel().notifyAjoutMaison(this.getPosition());
					j.setArgent(j.getArgent()-prixMaison);
					j.setNbMaison(j.getNbMaison()+1);
					j.setValeurPatrimoine(j.getValeurPatrimoine()+prixMaison);
				}
				else{
					nbMaisons--;
				}
			}
			else{
				this.p.getModel().notifyAjoutMaison(this.getPosition());
				j.setArgent(j.getArgent()-prixMaison);
				j.setNbMaison(j.getNbMaison()+1);
				j.setValeurPatrimoine(j.getValeurPatrimoine()+prixMaison);
			}
			
		}
		this.p.getModel().notifyNbMaison(this.p.getNbMaisonsTotal());
		
		
	}
	
	/**
	 * Retirer une maison au terrain si possible
	 */
	public void retirerMaison() {
		JoueurModel j=p.getJoueurs()[tabAssoTerrainJoueur[idTerrain]];
		
		if(peutSupprimerMaison()||ConstantesParam.EGALISATION_ENABLED){
			this.nbMaisons--;
			//met a jour le nombre d'hotel total si besoin
			if(nbMaisons==4){
				j.setNbMaison(j.getNbMaison()+4);
				j.setNbHotel(j.getNbHotel()-1);
			}
			else{
				j.setNbMaison(j.getNbMaison()-1);
			}
			
			this.p.getModel().notifyRetirerMaison(this.getPosition());
			this.p.getModel().notifyNbMaison(this.p.getNbMaisonsTotal());
			j.setValeurPatrimoine(j.getValeurPatrimoine()-prixMaison);
			
			j.setArgent(j.getArgent()+prixMaison);
			
			
		}
	}

	/**
	 * Vérifie si il reste des hotels en jeu
	 * @return reste des hotels
	 */
	public boolean resteDesHotels()
	{
		if(this.p.getNbHotelsTotal()==ConstantesModel.NB_HOTELS)
			return false;
		return true;
	}
	
	
	/**
	 * Vérifie si il reste des maisons en jeu
	 * @return reste des maisons
	 */
	public boolean resteDesMaisons()
	{
		if(this.p.getNbMaisonsTotal()==ConstantesParam.NB_MAISONS)
			return false;
		return true;
	}
	
	/**
	 * Si une case de la meme couleur a plus de maison que la case en question, retourne faux.
	 * OU si plus assez de maisons
	 * Peut pas construire
	 * 
	 */
	public boolean peutAjouterMaison()
	{
		TerrainModel tmp;
		for(int i=0; i<p.getCases().length; i++)
		{
			if(p.getCases()[i] instanceof TerrainModel)
			{
				tmp=(TerrainModel) p.getCases()[i];
				if(tmp.couleurTerrain==this.couleurTerrain&&tmp.nbMaisons<this.nbMaisons)
					return false;
			}
		}
		return true;
	}
	
	/**
	 * Vérifie si on peut supprimer une maison (égalisation de terrain)
	 * @return peut supprimer ou non
	 */
	public boolean peutSupprimerMaison()
	{
		if(this.nbMaisons==0)return false;
		TerrainModel tmp;
		for(int i=0; i<p.getCases().length; i++)
		{
			if(p.getCases()[i] instanceof TerrainModel)
			{
				tmp=(TerrainModel) p.getCases()[i];
				if(tmp.couleurTerrain==this.couleurTerrain&&tmp.nbMaisons>this.nbMaisons)
					return false;
			}
		}
		return true;
	}
	
	
	@Override
	public void action(JoueurModel j) {
		
		int idPossesseur=tabAssoTerrainJoueur[this.idTerrain];
		
		if(idPossesseur!=-1){
			
			JoueurModel possesseur=this.p.getJoueurs()[idPossesseur];
			//joueur different du possesseur de la case: transfert argent
			if(j.getIdJoueur()!=idPossesseur){
				
				/*
				possesseur.setArgent(possesseur.getArgent()+loyers[nbMaisons]);
				j.setArgent(j.getArgent()-loyers[nbMaisons]);
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



	public int getIdTerrain() {
		return idTerrain;
	}



	public void setIdTerrain(int idTerrain) {
		this.idTerrain = idTerrain;
	}



	public int getPrixAchat() {
		return prixAchat;
	}



	public void setPrixAchat(int prixAchat) {
		this.prixAchat = prixAchat;
	}



	public int[] getLoyers() {
		return loyers;
	}



	public void setLoyers(int[] loyers) {
		this.loyers = loyers;
	}



	public int getPrixMaison() {
		return prixMaison;
	}



	public void setPrixMaison(int prixMaison) {
		this.prixMaison = prixMaison;
	}



	public int getNbMaisons() {
		return nbMaisons;
	}



	public void setNbMaisons(int nbMaisons) {
		this.nbMaisons = nbMaisons;
	}

	
	public CouleurTerrain getCouleurTerrain() {
		return couleurTerrain;
	}



	public void setCouleurTerrain(CouleurTerrain couleurTerrain) {
		this.couleurTerrain = couleurTerrain;
	}




	public static int getNbTerrain() {
		return nbTerrain;
	}




	public static void setNbTerrain(int nbTerrain) {
		TerrainModel.nbTerrain = nbTerrain;
	}



	

	
	
}
