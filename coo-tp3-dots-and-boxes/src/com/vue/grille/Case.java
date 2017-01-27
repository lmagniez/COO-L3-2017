package com.vue.grille;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Case de la grille
 * @author loick
 *
 */

public class Case
{
	protected int x;
	protected int y;
	protected int posX;
	protected int posY;
	protected int hX;
	protected int hY;
	protected Color c;
	
	/**
	 * On calcule la position posX et posY en fonction de la taille des traits et leurs indices sur la grille 
	 * @param x indice grille
	 * @param y indice grille
	 */
	public Case(int x, int y)
	{
		this.x=x;
		this.y=y;
		this.posX=Grille.GRILLE_POSX+x*Cote.LONG+Cote.LARG+1;
		this.posY=Grille.GRILLE_POSY+y*Cote.LONG+Cote.LARG+1;
		this.hX=-1+Cote.LONG-Cote.LARG;
		this.hY=-1+Cote.LONG-Cote.LARG;
		this.c=Color.BLACK;
		
	}
}