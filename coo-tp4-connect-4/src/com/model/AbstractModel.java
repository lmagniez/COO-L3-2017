
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
	public abstract boolean ajoutJeton(int x);
	public abstract boolean columnFull(int x);
	public abstract boolean verifWin();
	
	
	public abstract void reinit();
	
	// Implementation du pattern observer
	// permet d'ajouter un observateur
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}
	

	/**
	 * Notifier quel est le prochain joueur
	 * @param tour prochain joueur 
	 */
	public void notifyTour(int tour){
		for(Observer obs : listObserver)
			obs.updateTour(tour);
	}

	
	
	@Override
	public void notifyWinner(int x, int y, patternWin p) {
		for(Observer obs : listObserver)
			obs.updateWinner(x,y,p);
	}
	
	/**
	 * Notifier un nouveau jeton
	 * @x Abscisse de la case
	 * @y Ordonnée de la case 
	 * @v Valeur du jeton
	 */
	@Override
	public void notifyNewChip(int x, int y, CaseValue v) {
		for(Observer obs : listObserver)
			obs.updateChip(x, y, v);
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
	public void notifyFull() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}