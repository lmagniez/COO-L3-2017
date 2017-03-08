
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
	public abstract  ArrayList<int[]> peutJouer(CaseValue tour);
	public abstract void getWinner();
	public abstract void placerinit();
	public abstract void afficherGrille();
	public abstract void startIA();
	public abstract void stopIA();
	public abstract void sauvegarder();
	
	
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

	
	public void notifyScore(int nbJetonsJ1, int nbJetonsJ2){
		for(Observer obs : listObserver)
			obs.updateScore(nbJetonsJ1,nbJetonsJ2);
	}


	public void reinit() {
		for(Observer obs : listObserver)
			obs.updateReinit();
	}
	
	
	public void notifyTour(CaseValue v){
		for(Observer obs : listObserver)
			obs.updateTour(v);
	}
	public void notifyPosJouable(ArrayList<int[]> liste) {
		// TODO Auto-generated method stub
		for(Observer obs : listObserver)
			obs.updatePosJouable(liste);
	}
	


}