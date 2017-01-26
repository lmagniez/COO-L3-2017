
package com.model;

import java.util.ArrayList;

import com.observer.Observable;
import com.observer.Observer;

public abstract class AbstractModel implements Observable{
	
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	//Vos methodes abstraites
	public abstract boolean ajoutTraitH(int x, int y);
	public abstract boolean ajoutTraitV(int x, int y);
	public abstract void reinit();
	
	// Implementation du pattern observer
	// permet d'ajouter un observateur
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}
	
	public void notifyNewLineH(int x, int y, BoxValues v) {
		
		// si tout est ok, on met a jour les observateurs
		for(Observer obs : listObserver)
		{
			obs.updateLineH(x,y,v);
			
		}
		
	
	}
	
	public void notifyNewLineV(int x, int y, BoxValues v) {
		
		// si tout est ok, on met a jour les observateurs
		for(Observer obs : listObserver)
		{
			obs.updateLineV(x,y,v);
		}
	}
	
	public void notifyTour(int tour){
		for(Observer obs : listObserver)
		{
			obs.updateTour(tour);
		}
	}
	
	public void notifyNewSquare(int x, int y, BoxValues v) {
		
		// si tout est ok, on met a jour les observateurs
		for(Observer obs : listObserver)
			obs.updateSquare(x,y,v);
	
	}
	
	
	public void notifyWinner(ArrayList<BoxValues> winners) {	
		// si tout est ok, on met a jour les observateurs
		for(Observer obs : listObserver)
			obs.updateWinner(winners);
	}
	
	public void notifyReinit()
	{
		for(Observer obs : listObserver)
			obs.updateReinit();
	}
	
	
	
	
	
	// permet de supprimer les observateurs
	public void removeObserver() {
		listObserver = new ArrayList<Observer>();

	}
}