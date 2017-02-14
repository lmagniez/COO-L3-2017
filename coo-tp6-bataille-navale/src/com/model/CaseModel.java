package com.model;

/**
 * Classe représentant une case de la grille de manière logique.
 * @author loick
 *
 */
public class CaseModel {

	protected CaseValue v;
	protected int idBateau;
	
	
	public CaseModel()
	{
		this.v=CaseValue.NONE;
		this.idBateau=-1;
	}
	
}
