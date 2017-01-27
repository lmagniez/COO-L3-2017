
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
	public abstract boolean ajoutTraitH(int x, int y);
	public abstract boolean ajoutTraitV(int x, int y);
	public abstract void reinit();
	
	// Implementation du pattern observer
	// permet d'ajouter un observateur
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}
	
	
	/**
	 * Notifier l'ajout d'un trait horizontal sur la grille à la vue
	 * @param x abscisse du trait
	 * @param y ordonée du trait
	 * @param v valeur du trait (joueur)
	 */
	public void notifyNewLineH(int x, int y, BoxValues v) {
		
		// si tout est ok, on met a jour les observateurs
		for(Observer obs : listObserver)
			obs.updateLineH(x,y,v);
			
	}
	
	/**
	 * Notifier l'ajout d'un trait vertical sur la grille à la vue
	 * @param x abscisse du trait
	 * @param y ordonée du trait
	 * @param v valeur du trait (joueur)
	 */
	public void notifyNewLineV(int x, int y, BoxValues v) {
		
		// si tout est ok, on met a jour les observateurs
		for(Observer obs : listObserver)
			obs.updateLineV(x,y,v);
	}
	
	/**
	 * Notifier quel est le prochain joueur
	 * @param tour prochain joueur 
	 */
	public void notifyTour(int tour){
		for(Observer obs : listObserver)
			obs.updateTour(tour);
	}
	
	/**
	 * Notifier la formation d'un nouveau carré
	 * @param x abscisse du carré
	 * @param y ordonée du carré
	 * @param v valeur du carré
	 */
	public void notifyNewSquare(int x, int y, BoxValues v) {
		
		// si tout est ok, on met a jour les observateurs
		for(Observer obs : listObserver)
			obs.updateSquare(x,y,v);
	
	}
	
	/**
	 * Notifier le(s) gagnants de la partie
	 * @winners liste des gagnants
	 */
	public void notifyWinner(ArrayList<BoxValues> winners) {	
		// si tout est ok, on met a jour les observateurs
		for(Observer obs : listObserver)
			obs.updateWinner(winners);
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
}