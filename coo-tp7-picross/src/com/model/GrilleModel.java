package com.model;

/**
 * Classe représentant une grille de jeu de manière logique
 * @author loick
 *
 */

public class GrilleModel {

	private JeuModel model;
	private int id;
	private String nom;
	private int nbLigne,nbColonne;
	private boolean[][] solution;
	private boolean[][] grille;
	private String InfoLigne[];
	private String InfoColonne[];
	private boolean reussite;
	
	
	/*
	 On va d'abord recuperer chaque id dans une classe principale (jeu?)
	 
	 Pour chaque id, on va lancer le constructeur de GrilleModel et récuperer chaque donnée
	 
	 */
	
	public GrilleModel(JeuModel model, int idJeu){
		this.model=model;
		this.id=idJeu;
	}
	
	
	
}
