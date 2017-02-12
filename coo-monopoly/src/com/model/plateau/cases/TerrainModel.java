package com.model.plateau.cases;

import com.model.Constantes;
import com.model.plateau.JoueurModel;
import com.model.plateau.PlateauModel;

public class TerrainModel extends CaseModel{

	
	public static int[] tabAssoTerrainJoueur=initTab();
	protected CouleurTerrain couleurTerrain;
	protected int idTerrain;
	protected int nbMaisons;
	
	protected int prixAchat;
	protected int[] loyers;
	protected int prixMaison;//5000 marron, 10000 violet, orange 15000 rouge et jaune, 20000 vert
	
	
	
	protected PlateauModel p;
	
	protected static int nbTerrain=0;
	
	
	public static int[] initTab()
	{
		int[] tabAsso=new int[Constantes.NB_CASES_TERRAIN];
		for(int i=0; i<Constantes.NB_CASES_TERRAIN; i++){
			tabAssoTerrainJoueur[i]=-1;
		}
		return tabAsso;
	}
	
	public TerrainModel(PlateauModel p, int idCase, int position, CouleurTerrain couleurTerrain, String nom
			,int prixAchat, int[] loyers, int prixMaison)
	{
		this.idTerrain=nbTerrain++;
		this.p=p;
		this.couleurTerrain=couleurTerrain;
		this.idCase=idCase;
		this.position=position;
		this.nom=nom;
		this.prixAchat=prixAchat;
		this.loyers=loyers;
		this.prixMaison=prixMaison;
		this.nbMaisons=0;
	}
	
	public void associer(JoueurModel j)
	{
		TerrainModel.tabAssoTerrainJoueur[idTerrain]=j.getIdJoueur();
		j.setArgent(j.getArgent()-prixAchat);
	}
	
	/**
	 * Doit appartenir à un joueur et les autres cases ont entre -1 et 0 maisons de différence
	 */
	public void ajouterMaison()
	{
		JoueurModel j=p.getJoueurs()[tabAssoTerrainJoueur[idTerrain]];
		if(resteDesMaisons()&&peutAjouterMaison()){
			j.setArgent(j.getArgent()-prixMaison);
			this.nbMaisons++;
			j.setNbMaison(j.getNbMaison()+1);
			
			//met a jour le nombre d'hotel total si besoin
			if(nbMaisons==5){
				if(resteDesHotels()){
					j.setNbMaison(j.getNbMaison()-5);
					j.setNbHotel(j.getNbHotel()+1);
				}
				else{
					nbMaisons--;
				}
			}
			
		}
		
	}
	
	
	public boolean resteDesHotels()
	{
		if(this.p.getNbHotelsTotal()==Constantes.NB_HOTELS)
			return false;
		return true;
	}
	
	
	public boolean resteDesMaisons()
	{
		if(this.p.getNbMaisonsTotal()==Constantes.NB_MAISONS)
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
				possesseur.setArgent(possesseur.getArgent()+loyers[nbMaisons]);
				j.setArgent(j.getArgent()-loyers[nbMaisons]);
			}
		}
	}
	
	
}
