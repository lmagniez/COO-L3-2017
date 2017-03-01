
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
	public abstract void verifWin(int idGrille);
	public abstract void changeValue(int idGrille, int x, int y);
	public abstract void changeValueCreation(int x, int y);
	public abstract void requestGrilles();
	public abstract void requestGrilleDetail(int idPuzzle);
	public abstract void createGrille(String nom, int nbRow, int nbCol);
	
	
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
	 * Notifier Colonne pleine
	 */
	@Override
	public void notifyFull() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void notifyChangeValue(int x, int y) {
		// TODO Auto-generated method stub
		for(Observer obs : listObserver)
			obs.updateChangeValue(x, y);
	}


	@Override
	public void notifyWin() {
		// TODO Auto-generated method stub
		for(Observer obs : listObserver)
			obs.updateWin();
	}


	@Override
	public void notifyLose() {
		// TODO Auto-generated method stub
		for(Observer obs : listObserver)
			obs.updateLose();
	}
	
	@Override
	public void notifyInfosGrilles(int nbGrille, int[] id, String[] nom, boolean[] reussite, int[] nbLignes, int[] nbColonnes) {
		// TODO Auto-generated method stub
		for(Observer obs : listObserver)
			obs.updateInfosGrilles(nbGrille, id, nom, reussite, nbLignes, nbColonnes);
	}
	
	
	@Override
	public void notifyGrilleDetail(int id, String nom, String[] indicesLigne, String[] indicesColonne,
			boolean reussite) {
		// TODO Auto-generated method stub
		for(Observer obs : listObserver)
			obs.updateGrilleDetail(id, nom, indicesLigne, indicesColonne, reussite);
		
	}
	
	public void notifyStart(){
		for(Observer obs : listObserver)
			obs.updateStart();
		
	}
	
}