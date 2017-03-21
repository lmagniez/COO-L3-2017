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
 * Modèle du jeu
 * @author loick
 *
 */
public class JeuModel extends AbstractModel{

	private PlateauModel p;
	protected int tour;
	
	/**
	 * Constructeur
	 */
	public JeuModel()
	{
		int posDepart=0;
		this.setP(new PlateauModel(this,ConstantesParam.NB_JOUEURS, ConstantesParam.POSITION_ALEA_ENABLED, ConstantesParam.SOMME_DEPART));
		this.tour=0;
	}
	
	@Override
	public void lancerDes(int idJoueur)
	{
		JoueurModel j=this.getP().joueurs[idJoueur];
		//gerer le cas en prison
		Random r= new Random();
		
		int des;
		if(ConstantesParam.TROIS_DES_ENABLED)
			des=3+r.nextInt(15);
		else des=2+r.nextInt(10);
		
		j.lastSumDes=des;
		j.setPosition((j.position+des)%ConstantesModel.NB_CASES);
		
		//this.notifyPosJoueur(j.idJoueur, j.position);
		
		
		
		
		
		
		this.p.cases[j.position].action(j);
		
	}
	
	@Override
	public void tourSuivant(){
		tour=(tour+1)%ConstantesParam.NB_JOUEURS;
		this.notifyTour(tour);
		this.notifyInitTour();
		
	}
	@Override
	public void lancerEnchere()
	{
		
	}
	
	
	@Override
	public void lancerNegociation()
	{
		
	}
	@Override
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
	@Override
	public boolean isInDebt(int idJoueur){
		return p.getJoueurs()[idJoueur].argent<0;
	}
	
	/**
	 * Acheter une case
	 * Vérification déjà effectuée avant d'appeler (hasEnoughMoney)
	 */
	@Override
	public void achatCase(int idJoueur, int positionAchat) {
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
	 * Acheter une maison
	 * Vérification déjà effectuée avant d'appeler (hasEnoughMoney)
	 */
	@Override
	public void achatMaison(int idJoueur, int positionAchat) {
		((TerrainModel) this.p.cases[positionAchat]).ajouterMaison();
	}
	
	/**
	 * Un joueur paye un autre 
	 * appelé depuis le controler en cliquant sur un choixPaiement
	 */
	@Override
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
	
	public void notifyJoueurs(){
		for(int i=0; i<this.p.nbJoueur; i++){
			this.notifyPosJoueur(i, p.joueurs[i].position);
		}
	}

	@Override
	public void venteMaison(int idJoueur, int positionAchat) {
		((TerrainModel) this.p.cases[positionAchat]).retirerMaison();
	}

	@Override
	public void echangePropriete(int idJoueur1, int idJoueur2, int positionAchat, int somme) {
		//TerrainModel.tabAssoTerrainJoueur[positionAchat]=idJoueur2;
		((TerrainModel) this.p.cases[positionAchat]).associer(this.p.joueurs[idJoueur2]);
		this.p.joueurs[idJoueur1].setArgent(this.p.joueurs[idJoueur1].getArgent()+somme);
		this.p.joueurs[idJoueur2].setArgent(this.p.joueurs[idJoueur2].getArgent()-somme);
		this.notifyEchangeJoueur(idJoueur1, idJoueur2, positionAchat);
	}


	
	
	

	
	
}
