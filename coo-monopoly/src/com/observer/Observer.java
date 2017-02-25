package com.observer;

import com.model.plateau.cases.CaseModel;

public interface Observer {
	//public void update(String str); // Le meme type que la methode notify
	public void update(int x, int y, String s);
	public void updateWinner(String s);
	public void updateArgentJoueur(int idJoueur, int argent);
	public void updatePosJoueur(int idJoueur, int position);
	public void updateCases(CaseModel[] cases);
	public void updateAjoutMaison(int position);
	public void updateTour(int tour);
	
	public void updateAcquisitionJoueur(int idJoueur, int position);
	public void updateAchatCase(int idJoueur, int position);
	public void updatePaiementCase(int idJoueur, int idJoueur2, int position);
	public void updateMessageChoix(String msg);
	
	public void updateInitTour();
}
