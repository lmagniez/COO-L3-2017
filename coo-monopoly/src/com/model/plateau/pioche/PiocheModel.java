package com.model.plateau.pioche;

import java.util.Random;

import com.model.Constantes;
import com.model.plateau.JoueurModel;
import com.model.plateau.PlateauModel;

public class PiocheModel {

	protected PlateauModel p;
	protected CarteModel[] cartes;
	protected int carteActuelle;
	protected boolean isChance;
	protected TypePioche type;
	

	public PiocheModel(PlateauModel p, TypePioche type)
	{
		this.type=type;
		this.p=p;
		this.carteActuelle=0;
		this.cartes=new CarteModel[Constantes.NB_CARTES_CHANCE];
		if(type==TypePioche.CHANCE)
			genererCartesChance();
		else if(type==TypePioche.COMMUNAUTE)
			genererCartesCommunaute();
		
		melangerPaquet(Constantes.NB_SWAP);
	}
	
	/**
	 * Teste de pioche vide
	 * @return pioche vide ou non
	 */
	public boolean piocheVide()
	{
		return(carteActuelle==Constantes.NB_CARTES_CHANCE-1);
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
			int i1=r.nextInt(Constantes.NB_CARTES_CHANCE);
			int i2=r.nextInt(Constantes.NB_CARTES_CHANCE);
			CarteModel tmp=cartes[i1];
			cartes[i1]=cartes[i2];
			cartes[i2]=tmp;
			
		}
	}
	
	public boolean piocherCarte(JoueurModel j)
	{
		if(piocheVide())
			return false;
		
		this.cartes[carteActuelle].actionCarte(j);
		carteActuelle++;
		return true;
	}
	
	public void genererCartesChance()
	{
		int nbCarte=0;
		cartes[nbCarte++]=new CarteModel(0, 0, 0, true, false, 0, 0,0, false,
				"Allez en prison. Ne franchissez pas la case ≪ Départ ≫ .");
		cartes[nbCarte++]=new CarteModel(-2000, 0, -1, false, false, 0, 0,0, false,
				"Amende pour ivresse. Payez 2.000.");
		cartes[nbCarte++]=new CarteModel(10000, 0, -1, false, false, 0, 0,0, false,
				"Vous avez gagné le prix de mots croisés. Recevez 10.000.");
		cartes[nbCarte++]=new CarteModel(0, 0, -1, false, false, -10000, -2500,0, false,
				"Faites des réparations. 2.500 par maison, 10.000 par hôtel.");
		cartes[nbCarte++]=new CarteModel(15000, 0, -1, false, false, 0, 0,0, false,
				"Votre immeuble et votre prêt vous rapportent. Vous devez toucher 15.000.");
		cartes[nbCarte++]=new CarteModel(0, 0, -1, false, true, 0, 0,0, false,
				"Vous êtes libérés de prison.");
		cartes[nbCarte++]=new CarteModel(0, 0, -1, false, true, 0, 0,0, false,
				"Vous êtes libérés de prison.");
		cartes[nbCarte++]=new CarteModel(0, 0, this.p.getIdCaseByName("Avenue Henri-Martin"), false, false, 0, 0,0, false,
				"Rendez-vous à l’Avenue Henri-Martin.");
		cartes[nbCarte++]=new CarteModel(0, 0, Constantes.POS_DEPART, false, false, 0, 0,0, false,
				"Avancez jusqu’à la case ≪ Départ ≫ .");
		cartes[nbCarte++]=new CarteModel(0, 0, this.p.getIdCaseByName("Boulevard de la Vilette"), false, false, 0, 0,0, false,
				"Avancez au Boulevard de la Villette.");
		cartes[nbCarte++]=new CarteModel(0, 0, this.p.getIdCaseByName("Gare de Lyon"), false, false, 0, 0,0, false,
				"Allez à la gare de Lyon.");
		cartes[nbCarte++]=new CarteModel(0, -3, -1, false, false, 0, 0,0, false,
				"Reculez de trois cases.");
		cartes[nbCarte++]=new CarteModel(-1500, 0, -1, false, false, 0, 0,0, false,
				"Amende pour excès de vitesse, 1.500.");
		cartes[nbCarte++]=new CarteModel(0, 0, -1, false, false, -11500, -4000,0, false,
				"Vous êtes imposés pour des réparations. 4.000 par maison. 11.500 par hôtel.");
		cartes[nbCarte++]=new CarteModel(-15000, 0, this.p.getIdCaseByName("Rue de la paix"), false, false, 0, 0,0, false,
				"Payez pour frais de scolarité, 15.000.");
		cartes[nbCarte++]=new CarteModel(-5000, 0, -1, false, false, 0, 0,0, false,
				"La banque vous verse 5.000.");
	}
	
	public void genererCartesCommunaute()
	{
		int nbCarte=0;
		cartes[nbCarte++]=new CarteModel(0, 0, 0, false, false, 0, 0,0, false,
				"Placez-vous sur la case ≪ Départ ≫ .");
		cartes[nbCarte++]=new CarteModel(5000, 0, -1, false, false, 0, 0,0, false,
				"La vente de votre stock vous rapporte 5.000.");
		cartes[nbCarte++]=new CarteModel(10000, 0, -1, false, false, 0, 0,0, false,
				"Payez à l’Hôpital 10.000.");
		cartes[nbCarte++]=new CarteModel(0, 0, -1, false, false, 0, 0,1000, false,
				"C’est votre anniversaire, chaque joueur doit vous donner 1.000.");
		cartes[nbCarte++]=new CarteModel(-5000, 0, -1, false, false, 0, 0,0, false,
				"Payez votre police d’Assurance s’élevant à 5.000.");
		cartes[nbCarte++]=new CarteModel(10000, 0, -1, false, false, 0, 0,0, false,
				"Recevez votre revenu annuel 10.000.");
		cartes[nbCarte++]=new CarteModel(-1000, 0, -1, false, false, 0, 0,0, false,
				"Payez une amende de 1.000 ou tirez une carte chance.");
		cartes[nbCarte++]=new CarteModel(0, 0, 0, true, false, 0, 0,0, false,
				"Allez en prison. Ne franchissez pas la case ≪ Départ ≫");
		cartes[nbCarte++]=new CarteModel(10000, 0, -1, false, false, 0, 0,0, false,
				"Vous héritez de 10.000.");
		cartes[nbCarte++]=new CarteModel(0, 0, -1, false, true, 0, 0,0, false,
				"Vous êtes libérés de prison.");
		cartes[nbCarte++]=new CarteModel(2000, 0, -1, false, false, 0, 0,0, false,
				"Les contributions vous rapportent 2.000.");
		cartes[nbCarte++]=new CarteModel(1000, 0, this.p.getIdCaseByName("Belleville"), false, false, 0, 0,0, false,
				"Retournez à Belleville.");
		cartes[nbCarte++]=new CarteModel(5000, 0, -1, false, false, 0, 0,0, false,
				"Payez la note du médecin, 5.000.");
		cartes[nbCarte++]=new CarteModel(20000, 0, -1, false, false, 0, 0,0, false,
				"Erreur de la banque, recevez 20.000.");
	}
	
	
}
