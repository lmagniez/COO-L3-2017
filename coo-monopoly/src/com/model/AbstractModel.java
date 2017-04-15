
package com.model;

import java.util.ArrayList;

import com.model.plateau.JoueurModel;
import com.model.plateau.cases.CaseModel;
import com.model.plateau.pioche.TypePioche;
import com.observer.Observable;
import com.observer.Observer;

/**
 * Modèle abstrait, indique un ensemble de méthode à implémenter et notifie les vues de changements
 * @author loick
 *
 */
public abstract class AbstractModel implements Observable{
	
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	public static final int NB_LIGNE = 3; 
	
	/** Lancer un dé
	 * @param idJoueur id du joueur */
	public abstract void lancerDes(int idJoueur);
	/** Lancer une enchère */
	public abstract void lancerEnchere(int position);
	/** Lancer une négociation entre deux joueurs */
	public abstract void lancerNegociation();
	/** Lancer le prochain tour */
	public abstract void tourSuivant();
	/** Acheter une maison
	 * @param idJoueur id du joueur
	 * @param positionAchat position de l'achat */
	public abstract void achatMaison(int idJoueur, int positionAchat);
	
	/** Combler une dette
	 * @param idJoueur */
	public abstract void comblerDette(int idJoueur);
	/** Vérifie si un joueur peut acheter une case
	 * @param idJoueur id du joueur
	 * @param positionAchat position de la case
	 * @return assez d'argent ou non */
	public abstract boolean hasEnoughMoney(int idJoueur, int positionAchat);
	/**	Vérifie si le joueur est endetté 
	 * @param idJoueur id du joueur
	 * @return endetté ou non */
	public abstract boolean isInDebt(int idJoueur);
	/** Acheter une case 
	 * @param idJoueur id du joueur
	 * @param positionAchat position de la case */
	public abstract void achatCase(int idJoueur, int positionAchat);
	/** Payer un joueur
	 * @param idJoueur1 joueur qui recoit l'argent
	 * @param idJoueur2 joueur qui recoit la case
	 * @param position position de la case */
	public abstract void paiementJoueur(int idJoueur1, int idJoueur2, int position);
	/** Vendre une maison
	 * @param idJoueur id du joueur
	 * @param positionAchat position de la case */
	public abstract void venteMaison(int idJoueur, int positionAchat);
	
	/**
	 * Donner une propriété a un joueur
	 * @param idJoueur1 id du joueur 1
	 * @param idJoueur2 id du joueur 2
	 * @param positionAchat position achat
	 * @param somme montant achat
	 */
	public abstract void echangePropriete(int idJoueur1, int idJoueur2, int positionAchat, int somme);
	
	public abstract void hypothequer(int idJoueur1, int positionAchat);
	public abstract void effetPioche(int idJoueur, int idCarte, TypePioche type);
	public abstract void gameOver(int idJoueur);
	public abstract void achatCaseEnchere(int idJoueur, int positionAchat, int prix);
	
	
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
	
	public void notifyWinner(int idJoueur) {	
		// si tout est ok, on met a jour les observateurs
		for(Observer obs : listObserver)
			obs.updateWinner(idJoueur);
	}
	public void notifyGameOver(int idJoueur) {	
		// si tout est ok, on met a jour les observateurs
		for(Observer obs : listObserver)
			obs.updateGameOver(idJoueur);
	}
	
	
	@Override
	public void notifyCases(CaseModel[] cases) {
		for(Observer obs : listObserver)
			obs.updateCases(cases);
	}

	@Override
	public void notifyPosJoueur(int idJoueur, int position) {
		for(Observer obs : listObserver)
			obs.updatePosJoueur(idJoueur,position);
	}

	@Override
	public void notifyArgentJoueur(int idJoueur, int argent) {
		for(Observer obs : listObserver)
			obs.updateArgentJoueur(idJoueur,argent);
	}

	@Override
	public void notifyAcquisitionJoueur(int idJoueur, int position) {
		for(Observer obs : listObserver)
			obs.updateAcquisitionJoueur(idJoueur,position);
		
	}

	@Override
	public void notifyDesacquisitionJoueur(int idJoueur, int position) {
		for(Observer obs : listObserver)
			obs.updateDesacquisitionJoueur(idJoueur,position);
		
	}
	
	@Override
	public void notifyAjoutMaison(int position) {
		for(Observer obs : listObserver)
			obs.updateAjoutMaison(position);
	}
	
	public void notifyRetirerMaison(int position) {
		for(Observer obs : listObserver)
			obs.updateRetirerMaison(position);
	}
	
	// permet de supprimer les observateurs
	public void removeObserver() {
		listObserver = new ArrayList<Observer>();

	}
	
	public void notifyTour(int tour){
		for(Observer obs : listObserver)
			obs.updateTour(tour);
	}

	public void init_grille() {
		
	}
	
	public void notifyAchatCase(int idJoueur, int position){
		for(Observer obs : listObserver)
			obs.updateAchatCase(idJoueur, position);
	}
	
	public void notifyMessageChoix(String message){
		for(Observer obs : listObserver)
			obs.updateMessageChoix(message);
	}
	
	@Override
	public void notifyInitTour() {
		for(Observer obs : listObserver)
			obs.updateInitTour();
	}
	
	@Override
	public void notifyEchangeJoueur(int idJoueur1, int idJoueur2, int position){
		for(Observer obs : listObserver){
			obs.updateEchangeJoueur(idJoueur1, idJoueur2, position);
		}
	}
	

	@Override
	public void notifyPaiementCase(int idJoueur, int idPossesseur, int position) {
		for(Observer obs : listObserver){
			obs.updatePaiementCase(idJoueur, idPossesseur, position);
		}
	}
	
	@Override
	public void notifyMessageCarte(String msg, int idJoueur, int posCarte, TypePioche type) {
		for(Observer obs : listObserver){
			obs.updateMessageCarte(msg, idJoueur, posCarte, type);
		}
	}
	
	@Override
	public void notifyDette(int idJoueur) {
		for(Observer obs : listObserver){
			obs.updateDette(idJoueur);
		}
	}
	
	@Override
	public void notifyEnchere(int position) {
		for(Observer obs : listObserver){
			obs.updateEnchere(position);
		}
	}


	
	
}