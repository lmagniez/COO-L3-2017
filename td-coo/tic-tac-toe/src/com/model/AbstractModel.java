
package com.model;

import java.util.ArrayList;

import com.observer.Observable;
import com.observer.Observer;

public abstract class AbstractModel implements Observable{
	
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	public static final int NB_LIGNE = 3; 
	
	//Vos methodes abstraites
	public abstract void init_grille();
	public abstract void set_grille(int x, int y);
	
	// Implementation du pattern observer
	// permet d'ajouter un observateur
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}
	
	public void notifyObserver(int x, int y, String s) {
		
		// si tout est ok, on met a jour les observateurs
		for(Observer obs : listObserver)
			obs.update(x,y,s);
	
	}
	
	public void notifyWinner(String s) {	
		// si tout est ok, on met a jour les observateurs
		for(Observer obs : listObserver)
			obs.updateWinner(s);
	}
	
	
	// permet de supprimer les observateurs
	public void removeObserver() {
		listObserver = new ArrayList<Observer>();

	}
}