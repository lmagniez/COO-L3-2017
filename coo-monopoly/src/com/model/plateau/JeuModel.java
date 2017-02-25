package com.model.plateau;

import java.util.Random;

import com.model.AbstractModel;
import com.model.ConstantesModel;
import com.model.ConstantesParam;
import com.model.plateau.cases.CaseModel;
import com.model.plateau.cases.GareModel;
import com.model.plateau.cases.ServiceModel;
import com.model.plateau.cases.TerrainModel;

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
		j.setPosition((j.position+des)%ConstantesModel.NB_CASES);
		
		//this.notifyPosJoueur(j.idJoueur, j.position);
		
		
		
		
		
		
		this.p.cases[j.position].action(j);
		
		/*
		tour=(tour+1)%ConstantesParam.NB_JOUEURS;
		System.out.println("tour a notifier "+tour);
		this.notifyTour(tour);
		*/
	}
	
	public void tourSuivant(){
		tour=(tour+1)%ConstantesParam.NB_JOUEURS;
		System.out.println("tour a notifier "+tour);
		this.notifyTour(tour);
		this.notifyInitTour();
		
	}
	
	public void lancerEnchere()
	{
		
	}
	
	
	
	public void lancerNegociation()
	{
		
	}
	
	public void comblerDette(int idJoueur)
	{
		
	}

	public PlateauModel getP() {
		return p;
	}

	public void setP(PlateauModel p) {
		this.p = p;
	}

	/**
	 * Calcule si le joueur peut acheter la case
	 * A implémenter pour Terrain,Gare,Service
	 * @param idJoueur id du joueur
	 * @param positionAchat position de la case
	 */
	@Override
	public boolean hasEnoughMoney(int idJoueur, int positionAchat) {
		CaseModel c=p.getCases()[positionAchat];
		if(c instanceof TerrainModel)
			return (p.getJoueurs()[idJoueur].argent>=((TerrainModel) c).getPrixAchat());
		if(c instanceof GareModel)
			return (p.getJoueurs()[idJoueur].argent>=((GareModel) c).getPrixAchat());
		if(c instanceof ServiceModel)
			return (p.getJoueurs()[idJoueur].argent>=((ServiceModel) c).getPrixAchat());
		
		//GARE, SERVICE...
		
		return false;
		
	}

	/**
	 * Retourne si le joueur est endetté ou non (pour lancer l'hypothèque)
	 * @param idJoueur id du joueur
	 */
	public boolean isInDebt(int idJoueur){
		return p.getJoueurs()[idJoueur].argent<0;
	}
	
	/**
	 * Acheter une case
	 * Vérification déjà effectuée avant d'appeler (hasEnoughMoney)
	 */
	@Override
	public void achatCase(int idJoueur, int positionAchat) {
		// TODO Auto-generated method stub
		CaseModel c=p.getCases()[positionAchat];
		JoueurModel j =p.getJoueurs()[idJoueur];
		j.nbProprietes++;
			
		if(c instanceof TerrainModel){
			j.setArgent(j.argent-((TerrainModel) c).getPrixAchat());
			TerrainModel.tabAssoTerrainJoueur[((TerrainModel)c).getIdTerrain()]=idJoueur;
		}
		else if(c instanceof GareModel){
			j.setArgent(j.argent-((GareModel) c).getPrixAchat());
			GareModel.tabAssoGareJoueur[((GareModel)c).getIdGare()]=idJoueur;
		}
		else if(c instanceof ServiceModel){
			j.setArgent(j.argent-((ServiceModel) c).getPrixAchat());
			ServiceModel.tabAssoServiceJoueur[((ServiceModel)c).getIdService()]=idJoueur;
		}
		
		this.notifyAcquisitionJoueur(idJoueur, positionAchat);
		this.notifyArgentJoueur(idJoueur,j.getArgent());
		
		//GARE, SERVICE...
	}
	
	
	/**
	 * Un joueur paye un autre 
	 * appelé depuis le controler en cliquant sur un choixPaiement
	 */
	
	public void paiementJoueur(int idJoueur1, int idJoueur2, int position){
		
		CaseModel c=p.getCases()[position];
		JoueurModel j1 =p.getJoueurs()[idJoueur1];
		JoueurModel j2 =p.getJoueurs()[idJoueur2];
		
		if(c instanceof TerrainModel){
			int montant=((TerrainModel) c).getLoyers()[((TerrainModel)c).getNbMaisons()];
			j2.setArgent(j2.getArgent()+montant);
			j1.setArgent(j1.getArgent()-montant);
		}
		else if(c instanceof GareModel){
			int montant=GareModel.getLoyers()[((GareModel)c).getNbGareMemeJoueur()-1];
			j2.setArgent(j2.getArgent()+montant);
			j1.setArgent(j1.getArgent()-montant);
		}
		else if(c instanceof ServiceModel){
			int montant=ServiceModel.getLoyers()[((ServiceModel)c).getNbServiceMemeJoueur()];
			j2.setArgent(j2.getArgent()+montant);
			j1.setArgent(j1.getArgent()-montant);
		}
		
	}

	
	
}
