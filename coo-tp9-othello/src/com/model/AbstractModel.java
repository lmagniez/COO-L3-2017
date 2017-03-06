
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
	public abstract boolean[] peutPlacer(int x, int y, CaseValue v);
	public abstract void remplirCase(int x, int y, boolean[] directions, CaseValue v);
	public abstract void changerTour();
	public abstract CaseValue getTour();
	public abstract  boolean peutJouer(CaseValue tour);
	public abstract void getWinner();
	
	
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
	 * Notifier la modification d'une valeur
	 */
	@Override
	public void notifyChangeValue(int x, int y, CaseValue v) {
		// TODO Auto-generated method stub
		for(Observer obs : listObserver)
			obs.updateChangeValue(x, y, v);
	}


	/**
	 * Notifier la victoire 
	 */
	@Override
	public void notifyWin(CaseValue v) {
		// TODO Auto-generated method stub
		for(Observer obs : listObserver)
			obs.updateWin(v);
	}

	



	public void reinit() {
		// TODO Auto-generated method stub
		
	}
	
	
	


}