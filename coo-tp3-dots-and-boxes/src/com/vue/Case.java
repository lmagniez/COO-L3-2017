package com.vue;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Case
{
	protected int x;
	protected int y;
	protected int posX;
	protected int posY;
	protected int hX;
	protected int hY;
	protected Color c;
	
	public Case(int x, int y)
	{
		this.x=x;
		this.y=y;
		this.posX=VuePrincipale2.GRILLE_POSX+x*Cote.LONG+Cote.LARG+1;
		this.posY=VuePrincipale2.GRILLE_POSY+y*Cote.LONG+Cote.LARG+1;
		this.hX=-1+Cote.LONG-Cote.LARG;
		this.hY=-1+Cote.LONG-Cote.LARG;
		this.c=Color.GRAY;
		
	}
}