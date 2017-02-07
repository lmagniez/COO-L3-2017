package com.vue.field;

import com.model.Constantes;

public class Bonus {

	
	protected VueField vue;
	protected int id;
	protected int posX;
	protected int posY;
	protected int diam;
	public static int nb_bonus=0;
	
	public Bonus(VueField vue, int id, int posX, int posY)
	{
		this.vue=vue;
		this.id=nb_bonus++;
		this.posX=posX;
		this.posY=posY;
		this.diam=Constantes.DIAMETRE_BONUS;
		
	}
	
}
