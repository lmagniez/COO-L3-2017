package com.observer;

import com.model.plateau.cases.CaseModel;
import com.model.plateau.pioche.TypePioche;

public interface Observer {
	//public void update(String str); // Le meme type que la methode notify
	public void update(int x, int y, String s);
	public void updateWinner(int idJoueur);
	public void updateGameOver(int idJoueur);
	
	/**
	 * Mettre à jour l'argent du joueur
	 * @param idJoueur id du joueur
	 * @param argent argent du joueur
	 */
	public void updateArgentJoueur(int idJoueur, int argent);
	/**
	 * Mettre à jour la position du joueur
	 * @param idJoueur id du joueur
	 * @param position position du joueur
	 */
	public void updatePosJoueur(int idJoueur, int position);
	/**
	 * Mettre à jour les cases 
	 * @param cases cases
	 */
	public void updateCases(CaseModel[] cases);
	/**
	 * Mettre à jour l'ajout d'une maison
	 * @param position position de la case
	 */
	public void updateAjoutMaison(int position);
	/**
	 * Mettre a jour la suppression d'une maison
	 * @param position position de la case
	 */
	public void updateRetirerMaison(int position);
	
	/**
	 * Mettre à jour le tour
	 * @param tour tour
	 */
	public void updateTour(int tour);
	
	/**
	 * Mettre à jour l'acquisition d'un joueur
	 * @param idJoueur id du joueur
	 * @param position position de la case
	 */
	public void updateAcquisitionJoueur(int idJoueur, int position);
	
	public void updateDesacquisitionJoueur(int idJoueur, int position);
	
	/**
	 * Mettre à jour le paiement d'une case
	 * @param idJoueur1 id du joueur propriétaire
	 * @param idJoueur2 id du joueur receveur
	 * @param position position de la case
	 */
	public void updateEchangeJoueur(int idJoueur1, int idJoueur2, int position);
	
	
	/**
	 * Mettre à jour l'achat d'une case
	 * @param idJoueur id du joueur
	 * @param position position de la case
	 */
	public void updateAchatCase(int idJoueur, int position);
	
	/**
	 * Mettre à jour un message de choix
	 * @param msg message a afficher
	 */
	public void updateMessageChoix(String msg);
	
	/**
	 * Mettre à jour l'initialisation d'un tour
	 */
	
	public void updateInitTourDes();
	public void updateInitTourFenetre();
	
	public void updateMessageCarte(String msg, int idJoueur, int posCarte, TypePioche type);
	
	public void updatePaiementCase(int idJoueur, int idPossesseur, int position);

	public void updateDette(int idJoueur);
	
	public void updateEnchere(int position);
	
	public void updateCartePrison(int idJoueur, boolean hasCarte);
	
	public void updatePrison(int idJoueur, int nbTour);
	
	public void updateEnPrison(int idJoueur, int nbTour);
	
	public void updateLiberePrison(int idJoueur);
	
	public void updateNbMaison(int nbMaison);
	public void updateTourPartie (int tourPartie);
	public void updatePatrimoineJoueur(int idJoueur, int argent);
	public void updateMessageGameOver(int idJoueur);
	
}


