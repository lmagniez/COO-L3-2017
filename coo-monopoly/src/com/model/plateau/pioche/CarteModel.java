package com.model.plateau.pioche;

import com.model.ConstantesModel;
import com.model.plateau.JoueurModel;

public class CarteModel {

	protected PiocheModel pioche;
	
	protected int effetMonnaie;
	protected int effetPosition;
	protected int argParHotel;
	protected int argParMaison;
	protected int changerPosition;
	protected boolean allerPrison;
	protected boolean sortirPrison;
	protected boolean repiocher;
	protected int anniversaire;
	protected String msg;
	
	public CarteModel(int effArg, int effPos, int chPos, boolean allerPrison, 
			boolean sortirPrison, int argParHotel, int argParMaison, int anniversaire, 
			boolean repiocher, String msg)
	{
		this.effetMonnaie=effArg;
		this.effetPosition=effPos;
		this.changerPosition=chPos;
		this.allerPrison=allerPrison;
		this.sortirPrison=sortirPrison;
		this.repiocher=repiocher;
		this.msg=msg;
	}
	
	public void actionCarte(JoueurModel j)
	{
		
///////////////////////////
//		PASSE PAR DEPART
/////////////////////////
		
		//effet argent
		j.setArgent(j.getArgent() + effetMonnaie);
		j.setArgent(j.getArgent() + argParHotel*j.getNbHotel()+argParMaison*j.getNbMaison());
		
		//effet position: Relance l'effet de la prochaine case
		if(effetPosition!=0){
			j.setPosition(j.getPosition() + effetPosition);
			this.pioche.p.getCases()[j.getPosition()].action(j);
		}
		else if(changerPosition>=0){
			j.setPosition(changerPosition);
			this.pioche.p.getCases()[j.getPosition()].action(j);
		}
		
		//anniversaire: enleve de l'argent à chaque joueurs
		if(anniversaire!=0)
		{
			int nbJoueur=this.pioche.p.getNbJoueur();
			JoueurModel[] joueurs=this.pioche.p.getJoueurs();
			
			for(int i=0; i<nbJoueur; i++)
				joueurs[i].setArgent(joueurs[i].getArgent()-anniversaire);
			j.setArgent(j.getArgent() + anniversaire*nbJoueur);
			
		}
		
		
		//effets divers
		if(allerPrison)
			j.setPositionSansArgent(ConstantesModel.POS_PRISON);
		
		if(sortirPrison)
			j.ajoutCartePrison();
		
		if(repiocher)
			this.pioche.piocherCarte(j);
		
		
			
		
	}
	
	
	
}
