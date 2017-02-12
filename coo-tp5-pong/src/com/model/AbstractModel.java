
package com.model;

import java.util.ArrayList;

import com.observer.Observable;
import com.observer.Observer;

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
	
	//Vos methodes abstraites
	
	public abstract void updateBall(int idB);
	public abstract void updateRacket(int idR, Direction d);
	public abstract void updateRacket(int idR, boolean up, boolean down);
	
	public abstract void ajoutBonus();
	public abstract void augmenteVitesse();
	public abstract void throwBall();
	public abstract boolean isStopped();
	public abstract void decide(int idJoueur);
	
	public abstract void reinit();
	
	// Implementation du pattern observer
	// permet d'ajouter un observateur
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
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
	 * Notifie la position de la balle
	 * @param idBalle id de la balle
	 * @param x position x de la balle
	 * @param y position y de la balle
	 */
	public void notifyNewPosBall(int idBalle, int x, int y) {
		for(Observer obs : listObserver)
			obs.updatePosBalle(idBalle, x, y);
	}

	/**
	 * Notifie la position de la raquette
	 * @param idRacket id de la raquette
	 * @param posX position x de la raquette
	 * @param posY position y de la raquette
	 */
	public void notifyNewPosRacket(int idRacket, int posX, int posY) {
		for(Observer obs : listObserver)
			obs.updatePosRacket(idRacket, posX, posY);
	}

	/**
	 * Notifie l'initialisation d'un mur horizontal
	 * @param idMur id du mur
	 * @param posX position x du mur 
	 * @param posY position y du mur
	 */
	public void notifyInitMurH(int idMur, int posX, int posY) {
		for(Observer obs : listObserver)
			obs.initMurH(idMur, posX, posY);		
	}
	
	/**
	 * Notifie l'apparition d'une nouvelle balle
	 * @param idBalle id de la balle
	 * @param x position x de la balle
	 * @param y position y de la balle
	 */
	public void notifyNewBalle(int idBalle, int x, int y){
		for(Observer obs : listObserver)
			obs.updateNewBalle(idBalle, x,y);
	}
	
	/**
	 * Notifie l'apparition d'un bonus
	 * @param idB id du bonus
	 * @param x position x du bonus
	 * @param y position y du bonus
	 */
	public void notifyNewBonus(int idB, int x, int y)
	{
		for(Observer obs : listObserver)
			obs.updateNewBonus(idB, x, y);
	}
	
	/**
	 * Notifie la disparition d'un bonus
	 * @param idB id du bonus
	 */
	public void notifyEraseBonus(int idB){
		
		for(Observer obs : listObserver)
			obs.updateEraseBonus(idB);
	}
	
	/**
	 * Notifie la position de la balle
	 * @param sJ1 score du joueur 1
	 * @param sJ2 score du joueur 2
	 */
	public void notifyScore (int sJ1, int sJ2){
		for(Observer obs : listObserver)
			obs.updateScore(sJ1, sJ2);
	}
	
	@Override
	public void notifyWinner(int idJoueur) {
		for(Observer obs : listObserver)
			obs.updateWinner(idJoueur);
		
	}
	
	
	
}