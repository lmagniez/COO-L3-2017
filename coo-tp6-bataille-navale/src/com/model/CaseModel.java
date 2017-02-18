package com.model;

/**
 * Classe représentant une case de la grille de manière logique.
 * @author loick
 *
 */
public class CaseModel {

	private CaseValue v;
	private int idBateau;
	
	/**
	 * Constructeur
	 */
	public CaseModel()
	{
		this.setV(CaseValue.NONE);
		this.setIdBateau(-1);
	}


	public int getIdBateau() {
		return idBateau;
	}


	public void setIdBateau(int idBateau) {
		this.idBateau = idBateau;
	}


	public CaseValue getV() {
		return v;
	}


	public void setV(CaseValue v) {
		this.v = v;
	}
	
}
