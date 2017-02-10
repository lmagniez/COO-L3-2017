package com.vue.field;

/**
 * Vue du mur horizontal
 * @author loick
 *
 */
public class MurH {
	
	protected int posX;
	protected int posY;
	protected int height,width;
	
	/**
	 * Constructeur
	 * @param posX posX 
	 * @param posY poY
	 * @param width épaisseur
	 * @param height hauteur
	 */
	public MurH(int posX, int posY, int width, int height)
	{
		this.posX=posX;
		this.posY=posY;
		this.height=height;
		this.width=width;
	}
	
}
