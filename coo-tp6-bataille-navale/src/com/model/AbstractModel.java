
package com.model;

import java.util.ArrayList;

import com.observer.Observable;
import com.observer.Observer;
import com.vue.grid.CaseValueVue;

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
	
	/*
	public abstract boolean ajoutJeton(int x);
	public abstract boolean columnFull(int x);
	public abstract boolean verifWin();
	public abstract boolean ajoutJetonIA(int x, CaseValue v);
	public abstract boolean retirerJetonIA(int x);
	*/
	
	public abstract void bombAdversaire(int x, int y);
	public abstract void bombJoueur(int x, int y);
	public abstract boolean isAlreadyShot(int x, int y);
	public abstract void verifWin();
	public abstract void notifierBateaux();
	public abstract void closeSocket();
	public abstract void sendToServer(String msg);
	public abstract void lancerCommunication();
	
	
	public abstract void reinit();
	
	
	// Implementation du pattern observer
	// permet d'ajouter un observateur
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}
	

	/**
	 * Notifier quel est le prochain joueur
	 */
	public void notifyTour(){
		for(Observer obs : listObserver)
			obs.updateTour();
	}

	
	
	
	@Override
	public void notifyWinner() {
		for(Observer obs : listObserver)
			obs.updateWinner();
	}

	@Override
	public void notifyLoser() {
		for(Observer obs : listObserver)
			obs.updateLoser();
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
	public void notifyBombJoueur(int x, int y, CaseValueVue v) {
		// TODO Auto-generated method stub
		for(Observer obs : listObserver)
			obs.updateCaseJoueur(x, y, v);
	}

	/**
	 * Notifier qu'une bombe a été lancé sur l'adversaire
	 */
	@Override
	public void notifyBombAdversaire(int x, int y, CaseValueVue v) {
		// TODO Auto-generated method stub
		for(Observer obs : listObserver)
			obs.updateCaseAdversaire(x, y, v);
	}
	
	@Override
	public void notifyNewBateau(int x, int y, TypeBateau type, Orientation o, int idB) {
		for(Observer obs : listObserver)
			obs.updateBateau(x,y,type,o,idB);
	}

	
	@Override
	public void notifyFull() {
		// TODO Auto-generated method stub
		for(Observer obs : listObserver)
			obs.updateFull();
	}
	
	public void notifyMsgScore(String msg){
		System.out.println("AbstractModel okay");
		for(Observer obs : listObserver)
			obs.updateMsgScore(msg);
	}
	
	
	
}