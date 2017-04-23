package com.model.plateau.pioche;

import com.model.ConstantesModel;
import com.model.plateau.JoueurModel;

/**
 * Modèle d'une carte de la pioche
 * @author loick
 *
 */
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
	
	/**
	 * Constructeur
	 * @param effArg effet sur l'argent
	 * @param effPos effet sur la position
	 * @param chPos effet sur la position direct
	 * @param allerPrison aller en prison
	 * @param sortirPrison sortir de prison
	 * @param argParHotel argent a payer par hotel
	 * @param argParMaison argent a payer par maison
	 * @param anniversaire argent a gagner
	 * @param repiocher repiocher
	 * @param msg message de la carte
	 */
	public CarteModel(PiocheModel p, int effArg, int effPos, int chPos, boolean allerPrison, 
			boolean sortirPrison, int argParHotel, int argParMaison, int anniversaire, 
			boolean repiocher, String msg)
	{
		this.pioche=p;
		this.effetMonnaie=effArg;
		this.effetPosition=effPos;
		this.changerPosition=chPos;
		this.allerPrison=allerPrison;
		this.sortirPrison=sortirPrison;
		this.repiocher=repiocher;
		this.msg=msg;
	}
	
	/**
	 * Action d'une carte sur un joueur donné
	 * @param j joueur subissant l'effet de la carte
	 */
	public void actionCarte(JoueurModel j, int posCarte)
	{
		
		this.pioche.p.getModel().notifyMessageCarte(this.msg, j.getIdJoueur(), posCarte, this.pioche.type);
		
///////////////////////////
//		PASSE PAR DEPART
/////////////////////////
	}
	
	public void execActionCarte(JoueurModel j)
	{
		//effet argent
		j.setArgent(j.getArgent() + effetMonnaie);
		j.setArgent(j.getArgent() + argParHotel*j.getNbHotel()+argParMaison*j.getNbMaison());
		
		//effet position: Relance l'effet de la prochaine case
		if(effetPosition!=0){
			j.setPositionSansArgent(j.getPosition() + effetPosition);
			//this.pioche.p.getCases()[j.getPosition()].action(j);
			
		}
		else if(changerPosition>=0){
			j.setPosition(changerPosition);
			//this.pioche.p.getCases()[j.getPosition()].action(j);
			if(changerPosition==0){
				j.setArgent(j.getArgent()+2000);
			}
			
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
		if(allerPrison){
			j.allerEnPrison();
		}
			
		if(sortirPrison){
			j.ajoutCartePrison();
			
		}
		
		if(repiocher){
			this.pioche.piocherCarte(j);
		}
		
		if(!allerPrison&&!repiocher){
			this.pioche.p.getModel().tourSuivant();
		}
			
		
	}

	public PiocheModel getPioche() {
		return pioche;
	}

	public void setPioche(PiocheModel pioche) {
		this.pioche = pioche;
	}

	public int getEffetMonnaie() {
		return effetMonnaie;
	}

	public void setEffetMonnaie(int effetMonnaie) {
		this.effetMonnaie = effetMonnaie;
	}

	public int getEffetPosition() {
		return effetPosition;
	}

	public void setEffetPosition(int effetPosition) {
		this.effetPosition = effetPosition;
	}

	public int getArgParHotel() {
		return argParHotel;
	}

	public void setArgParHotel(int argParHotel) {
		this.argParHotel = argParHotel;
	}

	public int getArgParMaison() {
		return argParMaison;
	}

	public void setArgParMaison(int argParMaison) {
		this.argParMaison = argParMaison;
	}

	public int getChangerPosition() {
		return changerPosition;
	}

	public void setChangerPosition(int changerPosition) {
		this.changerPosition = changerPosition;
	}

	public boolean isAllerPrison() {
		return allerPrison;
	}

	public void setAllerPrison(boolean allerPrison) {
		this.allerPrison = allerPrison;
	}

	public boolean isSortirPrison() {
		return sortirPrison;
	}

	public void setSortirPrison(boolean sortirPrison) {
		this.sortirPrison = sortirPrison;
	}

	public boolean isRepiocher() {
		return repiocher;
	}

	public void setRepiocher(boolean repiocher) {
		this.repiocher = repiocher;
	}

	public int getAnniversaire() {
		return anniversaire;
	}

	public void setAnniversaire(int anniversaire) {
		this.anniversaire = anniversaire;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
	
}
