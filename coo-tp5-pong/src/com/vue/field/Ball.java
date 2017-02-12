package com.vue.field;

import com.model.Constantes;

/**
 * Vue de la balle implémentant un thread demandant la mise à jour de celle-ci
 * @author loick
 *
 */
public class Ball {

	public static int NB_BALL=0;
	protected int nb;
	protected VueField vue;
	protected int id;
	protected volatile int posX;
	protected volatile int posY;
	protected int diam;
	
	/**
	 * Constructeur
	 * @param vue vue du terrain
	 * @param id id de la balle
	 * @param posX posX initiale
	 * @param posY posY initiale
	 */
	public Ball(VueField vue, int id, int posX, int posY)
	{
		this.id=NB_BALL++;
		this.vue=vue;
		this.posX=posX;
		this.posY=posY;
		this.diam=Constantes.DIAMETRE_BALLE;
		
	}
	
	
}
