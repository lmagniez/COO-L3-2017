package com.observer;

import com.model.plateau.cases.CaseModel;

/**
 * Méthodes de notification
 * @author loick
 *
 */
public interface Observable {
	public void addObserver(Observer obs);
	public void removeObserver();
	public void notifyObserver(int x, int y, String s); // String est un exemple
	public void notifyWinner(String s);
	
	/**
	 * Notifier un changement de tour
	 * @param tour tour
	 */
	public void notifyTour(int tour);
	/**
	 * Notifier les cases 
	 * @param cases cases
	 */
	public void notifyCases(CaseModel[] cases);
	/**
	 * Notifier la position d'un joueur
	 * @param idJoueur id du joueur
	 * @param position position du joueur
	 */
	public void notifyPosJoueur(int idJoueur, int position);
	/**
	 * Notifier l'argent d'un joueur
	 * @param idJoueur id du joueur 
	 * @param argent argent du joueur
	 */
	public void notifyArgentJoueur(int idJoueur, int argent);
	/**
	 * Notifier l'ajout d'une maison
	 * @param position position de la case
	 */
	public void notifyAjoutMaison(int position);
	/**
	 * Notifier la suppression d'une maison
	 * @param position position de la case
	 */
	public void notifyRetirerMaison(int position);
	
	
	public void notifyAchatCase(int idJoueur, int position);
	public void notifyPaiementCase(int idJoueur, int idJoueur2, int position);
	
	
	public void notifyAcquisitionJoueur(int idJoueur, int position);
	public void notifyMessageChoix(String msg);
	
	public void notifyInitTour();
	
	
}
