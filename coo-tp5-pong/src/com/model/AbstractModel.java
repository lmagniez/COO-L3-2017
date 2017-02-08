
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
	
	public abstract void ajoutBonus();
	public abstract void augmenteVitesse();
	public abstract void throwBall();
	public abstract boolean isStopped();
	
	
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
	
	@Override
	public void notifyNewPosBall(int idBalle, int x, int y) {
		for(Observer obs : listObserver)
			obs.updatePosBalle(idBalle, x, y);
	}

	@Override
	public void notifyNewPosRacket(int idRacket, int posX, int posY) {
		for(Observer obs : listObserver)
			obs.updatePosRacket(idRacket, posX, posY);
	}

	@Override
	public void notifyInitMurH(int idMur, int posX, int posY) {
		for(Observer obs : listObserver)
			obs.initMurH(idMur, posX, posY);		
	}
	
	public void notifyNewBalle(int idBalle, int x, int y){
		for(Observer obs : listObserver)
			obs.updateNewBalle(idBalle, x,y);
	}
	
	public void notifyNewBonus(int idB, int x, int y)
	{
		for(Observer obs : listObserver)
			obs.updateNewBonus(idB, x, y);
	}
	public void notifyEraseBonus(int idB){
		
		for(Observer obs : listObserver)
			obs.updateEraseBonus(idB);
	}
	
	public void notifyScore (int sJ1, int sJ2){
		for(Observer obs : listObserver)
			obs.updateScore(sJ1, sJ2);
	}
	
	
	
	
}