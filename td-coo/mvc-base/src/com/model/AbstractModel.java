
package com.model;

import java.util.ArrayList;

import com.observer.Observable;
import com.observer.Observer;

public abstract class AbstractModel implements Observable{
	
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	//Vos methodes abstraites
	//public abstract void ...
	
	
	// Implementation du pattern observer
	// permet d'ajouter un observateur
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}
	
	public void notifyObserver(String str) {
		
		//...
		// si tout est ok, on met a jour les observateurs
		for(Observer obs : listObserver)
			obs.update(str);
	
	}
	
	
	// permet de supprimer les observateurs
	public void removeObserver() {
		listObserver = new ArrayList<Observer>();

	}
}