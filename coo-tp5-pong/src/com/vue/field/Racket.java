package com.vue.field;

import com.model.Direction;

/**
 * Vue de la raquette (Classe abstraite)
 * @author loick
 *
 */
public class Racket {

	protected VueField vue;
	protected int idJ;
	protected int posX;
	protected int posY;
	protected int height,width;
	
	/**
	 * Constructeur
	 * @param vue vue du terrain
	 * @param idJ id du joueur
	 * @param posX posX initiale
	 * @param posY posY initiale
	 * @param width largeur
	 * @param height hauteur
	 */
	public Racket(VueField vue, int idJ, int posX, int posY, int width, int height)
	{
		this.vue=vue;
		this.idJ=idJ;
		this.posX=posX;
		this.posY=posY;
		this.height=height;
		this.width=width;
		
	}
	
	
}
