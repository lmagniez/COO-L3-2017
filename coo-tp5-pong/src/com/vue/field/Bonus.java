package com.vue.field;

import com.model.Constantes;

/**
 * Vue du bonus
 * @author loick
 *
 */
public class Bonus {

	protected VueField vue;
	protected int id;
	protected int posX;
	protected int posY;
	protected int diam;
	public static int nb_bonus=0;
	
	/**
	 * Constructeur
	 * @param vue vue du terrain 
	 * @param id id du bonus
	 * @param posX posX initiale
	 * @param posY posY initiale
	 */
	public Bonus(VueField vue, int id, int posX, int posY)
	{
		this.vue=vue;
		this.id=nb_bonus++;
		this.posX=posX;
		this.posY=posY;
		this.diam=Constantes.DIAMETRE_BONUS;
		
	}
	
}
