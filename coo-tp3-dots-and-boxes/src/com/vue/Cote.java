package com.vue;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Classe représentant un côté de manière graphique.
 * Un coté sera soit de sens horizontal ou vertical.
 * On cherchera dans cette classe à déterminer la position de l'objet graphique en fonction de sa 
 * position logique et de son placement sur l'écran.
 * 
 * @author loick
 *
 */

public class Cote {

	protected int x;
	protected int y;
	protected int posX;
	protected int posY;
	protected int hX;
	protected int hY;
	protected boolean vertical;
	protected Color c;
	
	public static final int LONG = 50;
	public static final int LARG = 20;
	
	/**
	 * Constructeur d'un coté
	 * @param x abscisse logique du coté
	 * @param y ordonnée logique du coté
	 * @param vertical change le sens du coté 
	 */
	public Cote(int x, int y, boolean vertical)
	{
		
		this.x=x;
		this.y=y;
		this.c=Color.BLACK;
		
		this.vertical=vertical;
		
		if(vertical)
		{
			this.posX=VuePrincipale2.GRILLE_POSX+x*LONG;
			this.posY=VuePrincipale2.GRILLE_POSY+y*LONG+Cote.LARG;
			this.hX=LARG;
			this.hY=LONG-LARG;
		}
		else
		{
			
			//inverser
			this.posX=VuePrincipale2.GRILLE_POSX+x*LONG+Cote.LARG;
			this.posY=VuePrincipale2.GRILLE_POSY+y*LONG;
			this.hX=LONG-LARG;
			this.hY=LARG;	
			
		}
		
	}
	
	
	/**
	 * Test de collision (on teste si la position de souris est dans le bounding box du coté en question).
	 * @param mouseX
	 * @param mouseY
	 * @return Vrai si collision
	 */
	public boolean collide(int mouseX, int mouseY)
	{
		return(mouseX>=posX&&mouseX<=posX+hX&&mouseY>=posY&&mouseY<=posY+hY);
	}
	
	
	
	
}
