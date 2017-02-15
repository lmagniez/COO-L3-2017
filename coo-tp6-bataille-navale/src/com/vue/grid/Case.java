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
	protected CaseValueVue c;
	
	public static int DIAMETRE_CASE;

	
	
	/**
	 * On calcule la position posX et posY en fonction de la taille des traits et leurs indices sur la grille 
	 * @param x indice grille
	 * @param y indice grille
	 */
	public Case(int x, int y, int nbCol, int nbRow)
	{
		
		int DIAMETRE_CASE1=(int) (Grid.GRILLE_WIDTH/nbCol);
		int DIAMETRE_CASE2=(int) (Grid.GRILLE_WIDTH/nbRow);
		
		
		if(DIAMETRE_CASE1<DIAMETRE_CASE2)
			DIAMETRE_CASE=DIAMETRE_CASE1;
		else
			DIAMETRE_CASE=DIAMETRE_CASE2;
		
		
		
		int espacementX=(Grid.GRILLE_WIDTH-DIAMETRE_CASE*nbCol)/(nbCol+1);
		int espacementY=(Grid.GRILLE_HEIGHT-DIAMETRE_CASE*nbRow)/(nbRow+1);
		
		
		this.x=x;
		this.y=y;
		this.hX=DIAMETRE_CASE;
		this.hY=DIAMETRE_CASE;
		this.posX=espacementX*(x+1)+DIAMETRE_CASE*x;
		this.posY=espacementY*(y+1)+DIAMETRE_CASE*y;
		this.c=CaseValueVue.NONE;
		
	}
}