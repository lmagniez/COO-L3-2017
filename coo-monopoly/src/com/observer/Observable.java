package com.observer;

import com.model.plateau.cases.CaseModel;
import com.model.plateau.pioche.TypePioche;

/**
 * Méthodes de notification
 * @author loick
 *
 */
public interface Observable {
	public void addObserver(Observer obs);
	public void removeObserver();
	public void notifyObserver(int x, int y, String s); // String est un exemple
	public void notifyWinner(int idJoueur);
	public void notifyGameOver(int idJoueur);
	
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
	
	public void notifyAcquisitionJoueur(int idJoueur, int position);
	public void notifyDesacquisitionJoueur(int idJoueur, int position);
	
	public void notifyEchangeJoueur(int idJoueur1, int idJoueur2, int position);
	
	
	public void notifyMessageCarte(String msg, int idJoueur, int posCarte, TypePioche type);
	
	public void notifyMessageChoix(String msg);
	
	public void notifyInitTourDes();
	public void notifyInitTourFenetre();
	
	public void notifyPaiementCase(int idJoueur, int idPossesseur, int position);

	public void notifyDette(int idJoueur);
	
	public void notifyEnchere(int position);
	
	public void notifyCartePrison(int idJoueur, boolean hasCarte);
	public void notifyPrison(int idJoueur, int nbTour);
	public void notifyEnPrison(int idJoueur, int nbTour);
	public void notifyLiberePrison(int idJoueur);
	
	
	
	
	
}
