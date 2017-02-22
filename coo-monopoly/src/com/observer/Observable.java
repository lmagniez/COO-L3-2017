package com.observer;

import com.model.plateau.cases.CaseModel;

public interface Observable {
	public void addObserver(Observer obs);
	public void removeObserver();
	public void notifyObserver(int x, int y, String s); // String est un exemple
	public void notifyWinner(String s);
	
	public void notifyTour(int tour);
	public void notifyCases(CaseModel[] cases);
	public void notifyPosJoueur(int idJoueur, int position);
	public void notifyArgentJoueur(int idJoueur, int argent);
	public void notifyAcquisitionJoueur(int idJoueur, int position);
	public void notifyAjoutMaison(int position);
	
	
	
	
}
