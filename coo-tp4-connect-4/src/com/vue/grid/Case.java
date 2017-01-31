package com.vue.grid;

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
	
	public static final int DIAMETRE_CASE=50;

	
	
	/**
	 * On calcule la position posX et posY en fonction de la taille des traits et leurs indices sur la grille 
	 * @param x indice grille
	 * @param y indice grille
	 */
	public Case(int x, int y, int nbCol, int nbRow)
	{
		int espacementX=(Grid.GRILLE_WIDTH-DIAMETRE_CASE*nbCol)/(nbCol+1);
		int espacementY=(Grid.GRILLE_HEIGHT-DIAMETRE_CASE*nbRow)/(nbRow+1);
		
		
		this.x=x;
		this.y=y;
		this.hX=DIAMETRE_CASE;
		this.hY=DIAMETRE_CASE;
		this.posX=espacementX*(x+1)+DIAMETRE_CASE*x;
		this.posY=espacementY*(y+1)+DIAMETRE_CASE*y;
		this.c=Color.WHITE;
		
	}
}