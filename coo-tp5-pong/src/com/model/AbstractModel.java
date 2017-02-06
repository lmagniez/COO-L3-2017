
package com.model;

import java.util.ArrayList;

import com.model.grid.CaseValue;
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
	
	/*
	public abstract boolean ajoutJeton(int x);
	public abstract boolean columnFull(int x);
	public abstract boolean verifWin();
	public abstract boolean ajoutJetonIA(int x, CaseValue v);
	public abstract boolean retirerJetonIA(int x);
	*/
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
	public void notifyNewPosBall(int idBalle, int x, int y, int diam) {
		for(Observer obs : listObserver)
			obs.updatePosBalle(idBalle, x, y, diam);
	}

	@Override
	public void notifyNewPosRacket(int idRacket, int posX, int posY, int width, int height) {
		for(Observer obs : listObserver)
			obs.updatePosRacket(idRacket, posX, posY, width, height);
	}

	@Override
	public void notifyInitMurH(int idMur, int posX, int posY, int width, int height) {
		for(Observer obs : listObserver)
			obs.initMurH(idMur, posX, posY, width, height);		
	}
	
	
}