
package com.model;

import java.util.ArrayList;

import com.observer.Observable;
import com.observer.Observer;
import com.reseau.EtatClient;
import com.vue.grid.CaseValueVue;

/**
 * Classe abstraite des modèles 
 * Chaque modèle étendra cette classe
 * 
 * @author loick
 *
 */

public abstract class AbstractModel implements Observable{
	
	//liste des observers
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	private EtatClient etat;
	
	//Vos methodes abstraites
	public abstract void bombJoueur(int x, int y);
	public abstract boolean isAlreadyShot(int x, int y);
	public abstract void verifWin();
	public abstract void notifierBateaux();
	public abstract void closeSocket();
	public abstract void sendToServer(String msg);
	public abstract void lancerCommunication();
	
	
	public abstract void reinit();
	
	
	// Implementation du pattern observer
	// permet d'ajouter un observateur
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}
	

	/**
	 * Notifier quel est le prochain joueur
	 */
	public void notifyTour(){
		for(Observer obs : listObserver)
			obs.updateTour(this.etat);
	}

	
	
	/**
	 * Notifier un winner sur le jeu
	 */
	@Override
	public void notifyWinner() {
		for(Observer obs : listObserver)
			obs.updateWinner();
	}

	/**
	 * Notifier un perdant sur le jeu
	 */
	@Override
	public void notifyLoser() {
		for(Observer obs : listObserver)
			obs.updateLoser();
	}

	
	
	/**
	 * Notifier la réinitialisation de la grille
	 */
	public void notifyReinit()
	{
		for(Observer obs : listObserver)
			obs.updateReinit();
	}
	
	
	/**
	 *  permet de supprimer les observateurs
	 */
	public void removeObserver() {
		listObserver = new ArrayList<Observer>();

	}
	
	/**
	 * Notifier l'envoi d'une bombe sur l'adversaire
	 * @param x abscisse de la bombe
	 * @param y ordonnée de la bombe
	 * @param v Valeur de la case après bombardement
	 */
	public void notifyBombJoueur(int x, int y, CaseValueVue v) {
		// TODO Auto-generated method stub
		for(Observer obs : listObserver)
			obs.updateCaseJoueur(x, y, v);
	}

	/**
	 * Notifier qu'une bombe a été lancé sur l'adversaire
	 * @param x abscisse de la bombe
	 * @param y ordonnée de la bombe
	 * @param v Valeur de la case après bombardement
	 */
	@Override
	public void notifyBombAdversaire(int x, int y, CaseValueVue v) {
		// TODO Auto-generated method stub
		for(Observer obs : listObserver)
			obs.updateCaseAdversaire(x, y, v);
	}
	
	/**
	 * Notifier un bateau sur la grille
	 * @param x abscisse du début du bateau
	 * @param y ordonée du début du bateau
	 * @param type type du bateau
	 * @param o Orientation du bateau
	 * @param idB id du Bateau
	 */
	@Override
	public void notifyNewBateau(int x, int y, TypeBateau type, Orientation o, int idB) {
		for(Observer obs : listObserver)
			obs.updateBateau(x,y,type,o,idB);
	}

	/**
	
	 * Notifier qu'une case a déjà été sélectionnée
	 */
	@Override
	public void notifyFull() {
		// TODO Auto-generated method stub
		for(Observer obs : listObserver)
			obs.updateFull();
	}
	
	/**
	 * Notifier l'affichage d'un nouveau message sur le panneau score J1
	 * @param msg message du score
	 */
	public void notifyMsgScore(String msg){
		for(Observer obs : listObserver)
			obs.updateMsgScore(msg);
	}
	
	/**
	 * Notifier l'affichage d'un nouveau message sur le panneau score J2
	 * @param msg message du score
	 */
	public void notifyMsgScore2(String msg){
		for(Observer obs : listObserver)
			obs.updateMsgScore2(msg);
	}
	
	/**
	 * Notifier la modification du score pour le joueur 1
	 * @param coupsPris nombre de coups pris
	 * @param coupsRates nombre de coups rates
	 */
	public void notifyScoreJ1(int coupsPris, int coupsRates, int nbBateauxCoules)
	{
		for(Observer obs : listObserver)
			obs.updateScoreJ1(coupsPris,coupsRates, nbBateauxCoules);
	}
	
	/**
	 * Notifier la modification du score pour le joueur 2
	 * @param coupsPris nombre de coups pris
	 * @param coupsRates nombre de coups rates
	 */
	public void notifyScoreJ2(int coupsPris, int coupsRates, int nbBateauxCoules)
	{
		for(Observer obs : listObserver)
			obs.updateScoreJ2(coupsPris,coupsRates, nbBateauxCoules);
	}
	
	/**
	 * Retourner l'état actuel du client
	 * @return état du client
	 */
	public EtatClient getEtat() {
		return etat;
	}
	
	/** 
	 * Modifier l'état du client
	 * @param etat nouvel état du client
	 */
	public void setEtat(EtatClient etat) {
		this.etat = etat;
		this.notifyTour();
	}
	
}