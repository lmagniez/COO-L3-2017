package com.model.plateau.pioche;

import java.util.Random;

import com.model.ConstantesModel;
import com.model.plateau.JoueurModel;
import com.model.plateau.PlateauModel;

/**
 * Modèle de la pioche
 * @author loick
 *
 */
public class PiocheModel {

	protected PlateauModel p;
	private CarteModel[] cartes;
	protected int carteActuelle;
	protected boolean isChance;
	protected TypePioche type;
	

	/**
	 * Constructeur
	 * @param p modèle du plateau
	 * @param type type de pioche
	 */
	public PiocheModel(PlateauModel p, TypePioche type)
	{
		this.type=type;
		this.p=p;
		this.carteActuelle=0;
		this.setCartes(new CarteModel[ConstantesModel.NB_CARTES_CHANCE]);
		if(type==TypePioche.CHANCE)
			genererCartesChance();
		else if(type==TypePioche.COMMUNAUTE)
			genererCartesCommunaute();
		
		melangerPaquet(ConstantesModel.NB_SWAP);
	}
	
	/**
	 * Teste de pioche vide
	 * @return pioche vide ou non
	 */
	public boolean piocheVide()
	{
		return(carteActuelle==ConstantesModel.NB_CARTES_CHANCE-1);
	}
	
	/**
	 * Swap 2 cartes d'un paquet, opération répétée nbSwap fois
	 * @param nbSwap nombre de swap
	 */
	public void melangerPaquet(int nbSwap)
	{
		Random r=new Random();
		for(int i=0; i<nbSwap; i++)
		{
			int i1=0,i2=0;
			if(type==TypePioche.CHANCE){
				i1=r.nextInt(ConstantesModel.NB_CARTES_CHANCE-1);
				i2=r.nextInt(ConstantesModel.NB_CARTES_CHANCE-1);
			}
			if(type==TypePioche.COMMUNAUTE){
				i1=r.nextInt(ConstantesModel.NB_CARTES_COMMUNAUTE-1);
				i2=r.nextInt(ConstantesModel.NB_CARTES_COMMUNAUTE-1);
			}
			CarteModel tmp=getCartes()[i1];
			getCartes()[i1]=getCartes()[i2];
			getCartes()[i2]=tmp;
			
		}
	}
	
	/**
	 * Piocher une carte
	 * @param j joueur piochant la carte
	 * @return pioche ou non
	 */
	public boolean piocherCarte(JoueurModel j)
	{
		System.out.println("pioche "+type+" "+carteActuelle+"/"+getCartes().length);
		
		if(piocheVide())
			return false;
		
		this.getCartes()[carteActuelle].actionCarte(j,carteActuelle);
		carteActuelle++;
		return true;
	}
	
	/**
	 * Génération des cartes chances (16
	 */
	public void genererCartesChance()
	{
		int nbCarte=0;
		getCartes()[nbCarte++]=new CarteModel(this, 0, 0, 0, true, false, 0, 0,0, false,
				"Allez en prison. Ne franchissez pas la case ≪ Départ ≫ .");
		getCartes()[nbCarte++]=new CarteModel(this, -2000, 0, -1, false, false, 0, 0,0, false,
				"Amende pour ivresse. Payez 2.000.");
		getCartes()[nbCarte++]=new CarteModel(this, 10000, 0, -1, false, false, 0, 0,0, false,
				"Vous avez gagné le prix de mots croisés. Recevez 10.000.");
		getCartes()[nbCarte++]=new CarteModel(this, 0, 0, -1, false, false, -10000, -2500,0, false,
				"Faites des réparations. 2.500 par maison, 10.000 par hôtel.");
		getCartes()[nbCarte++]=new CarteModel(this, 15000, 0, -1, false, false, 0, 0,0, false,
				"Votre immeuble et votre prêt vous rapportent. Vous devez toucher 15.000.");
		getCartes()[nbCarte++]=new CarteModel(this, 0, 0, -1, false, true, 0, 0,0, false,
				"Vous êtes libérés de prison.");
		getCartes()[nbCarte++]=new CarteModel(this, 0, 0, -1, false, true, 0, 0,0, false,
				"Vous êtes libérés de prison.");
		getCartes()[nbCarte++]=new CarteModel(this, 0, 0, this.p.getIdCaseByName("Avenue Henri-Martin"), false, false, 0, 0,0, false,
				"Rendez-vous à l’Avenue Henri-Martin.");
		getCartes()[nbCarte++]=new CarteModel(this, 0, 0, ConstantesModel.POS_DEPART, false, false, 0, 0,0, false,
				"Avancez jusqu’à la case ≪ Départ ≫ .");
		getCartes()[nbCarte++]=new CarteModel(this, 0, 0, this.p.getIdCaseByName("Boulevard de la Vilette"), false, false, 0, 0,0, false,
				"Avancez au Boulevard de la Villette.");
		getCartes()[nbCarte++]=new CarteModel(this, 0, 0, this.p.getIdCaseByName("Gare de Lyon"), false, false, 0, 0,0, false,
				"Allez à la gare de Lyon.");
		getCartes()[nbCarte++]=new CarteModel(this, 0, -3, -1, false, false, 0, 0,0, false,
				"Reculez de trois cases.");
		getCartes()[nbCarte++]=new CarteModel(this, -1500, 0, -1, false, false, 0, 0,0, false,
				"Amende pour excès de vitesse, 1.500.");
		getCartes()[nbCarte++]=new CarteModel(this, 0, 0, -1, false, false, -11500, -4000,0, false,
				"Vous êtes imposés pour des réparations. 4.000 par maison. 11.500 par hôtel.");
		getCartes()[nbCarte++]=new CarteModel(this, -15000, 0, this.p.getIdCaseByName("Rue de la paix"), false, false, 0, 0,0, false,
				"Payez pour frais de scolarité, 15.000.");
		getCartes()[nbCarte++]=new CarteModel(this, -5000, 0, -1, false, false, 0, 0,0, false,
				"La banque vous verse 5.000.");
	}
	
	/**
	 * Génération des cartes communautés (14
	 */
	public void genererCartesCommunaute()
	{
		int nbCarte=0;
		getCartes()[nbCarte++]=new CarteModel(this, 0, 0, 0, false, false, 0, 0,0, false,
				"Placez-vous sur la case ≪ Départ ≫ .");
		getCartes()[nbCarte++]=new CarteModel(this, 5000, 0, -1, false, false, 0, 0,0, false,
				"La vente de votre stock vous rapporte 5.000.");
		getCartes()[nbCarte++]=new CarteModel(this, 10000, 0, -1, false, false, 0, 0,0, false,
				"Payez à l’Hôpital 10.000.");
		getCartes()[nbCarte++]=new CarteModel(this, 0, 0, -1, false, false, 0, 0,1000, false,
				"C’est votre anniversaire, chaque joueur doit vous donner 1.000.");
		getCartes()[nbCarte++]=new CarteModel(this, -5000, 0, -1, false, false, 0, 0,0, false,
				"Payez votre police d’Assurance s’élevant à 5.000.");
		getCartes()[nbCarte++]=new CarteModel(this, 10000, 0, -1, false, false, 0, 0,0, false,
				"Recevez votre revenu annuel 10.000.");
		getCartes()[nbCarte++]=new CarteModel(this, -1000, 0, -1, false, false, 0, 0,0, false,
				"Payez une amende de 1.000 ou tirez une carte chance.");
		getCartes()[nbCarte++]=new CarteModel(this, 0, 0, 0, true, false, 0, 0,0, false,
				"Allez en prison. Ne franchissez pas la case ≪ Départ ≫");
		getCartes()[nbCarte++]=new CarteModel(this, 10000, 0, -1, false, false, 0, 0,0, false,
				"Vous héritez de 10.000.");
		getCartes()[nbCarte++]=new CarteModel(this, 0, 0, -1, false, true, 0, 0,0, false,
				"Vous êtes libérés de prison.");
		getCartes()[nbCarte++]=new CarteModel(this, 2000, 0, -1, false, false, 0, 0,0, false,
				"Les contributions vous rapportent 2.000.");
		getCartes()[nbCarte++]=new CarteModel(this, 1000, 0, this.p.getIdCaseByName("Belleville"), false, false, 0, 0,0, false,
				"Retournez à Belleville.");
		getCartes()[nbCarte++]=new CarteModel(this, 5000, 0, -1, false, false, 0, 0,0, false,
				"Payez la note du médecin, 5.000.");
		getCartes()[nbCarte++]=new CarteModel(this, 20000, 0, -1, false, false, 0, 0,0, false,
				"Erreur de la banque, recevez 20.000.");
	}

	public CarteModel[] getCartes() {
		return cartes;
	}

	public void setCartes(CarteModel[] cartes) {
		this.cartes = cartes;
	}
	
	
}
