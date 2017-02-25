
package com.model;

import java.util.ArrayList;

import com.model.plateau.JoueurModel;
import com.model.plateau.cases.CaseModel;
import com.observer.Observable;
import com.observer.Observer;

public abstract class AbstractModel implements Observable{
	
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	public static final int NB_LIGNE = 3; 
	
	//Vos methodes abstraites
	//public abstract void init_grille();
	//public abstract void set_grille(int x, int y);
	
	public abstract void lancerDes(int idJoueur);
	public abstract void lancerEnchere();
	public abstract void lancerNegociation();
	public abstract void tourSuivant();
	
	
	public abstract void comblerDette(int idJoueur);
	public abstract boolean hasEnoughMoney(int idJoueur, int positionAchat);
	public abstract boolean isInDebt(int idJoueur);
	public abstract void achatCase(int idJoueur, int positionAchat);
	public abstract void paiementJoueur(int idJoueur1, int idJoueur2, int position);
	
	
	// Implementation du pattern observer
	// permet d'ajouter un observateur
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}
	
	public void notifyObserver(int x, int y, String s) {
		
		// si tout est ok, on met a jour les observateurs
		for(Observer obs : listObserver)
			obs.update(x,y,s);
	
	}
	
	public void notifyWinner(String s) {	
		// si tout est ok, on met a jour les observateurs
		for(Observer obs : listObserver)
			obs.updateWinner(s);
	}
	
	
	@Override
	public void notifyCases(CaseModel[] cases) {
		// TODO Auto-generated method stub
		for(Observer obs : listObserver)
			obs.updateCases(cases);
	}

	@Override
	public void notifyPosJoueur(int idJoueur, int position) {
		// TODO Auto-generated method stub
		for(Observer obs : listObserver)
			obs.updatePosJoueur(idJoueur,position);
	}

	@Override
	public void notifyArgentJoueur(int idJoueur, int argent) {
		// TODO Auto-generated method stub
		for(Observer obs : listObserver)
			obs.updateArgentJoueur(idJoueur,argent);
	}

	@Override
	public void notifyAcquisitionJoueur(int idJoueur, int position) {
		// TODO Auto-generated method stub
		for(Observer obs : listObserver)
			obs.updateAcquisitionJoueur(idJoueur,position);
		
	}

	@Override
	public void notifyAjoutMaison(int position) {
		// TODO Auto-generated method stub
		for(Observer obs : listObserver)
			obs.updateAjoutMaison(position);
	}
	
	// permet de supprimer les observateurs
	public void removeObserver() {
		listObserver = new ArrayList<Observer>();

	}
	
	public void notifyTour(int tour){
		for(Observer obs : listObserver)
			obs.updateTour(tour);
	}

	public void init_grille() {
		// TODO Auto-generated method stub
		
	}
	
	public void notifyAchatCase(int idJoueur, int position){
		for(Observer obs : listObserver)
			obs.updateAchatCase(idJoueur, position);
	}
	
	public void notifyMessageChoix(String message){
		for(Observer obs : listObserver)
			obs.updateMessageChoix(message);
	}
	
	@Override
	public void notifyInitTour() {
		// TODO Auto-generated method stub
		for(Observer obs : listObserver)
			obs.updateInitTour();
	}
	
	public void notifyPaiementCase(int idJoueur, int idJoueur2, int position){
		for(Observer obs : listObserver)
			obs.updatePaiementCase(idJoueur, idJoueur2, position);
	}
	
}