package com.vue.grid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.model.patternWin;


/**
 * Classe représentant une Grille
 * Dispose d'un tableau de traits horizontaux, un tableau de traits verticaux, et un tableau de cases 
 * @author loick
 *
 */
public class Grid extends JPanel implements MouseListener{
	
	private VueGrid vue;
	
	protected Case[][] cases;
	protected int nbRow, nbCol;
	
	protected int lignePosX, lignePosY;
	
	
	public static int GRILLE_POSX;
	public static int GRILLE_POSY;
	public static final int GRILLE_WIDTH=VueGrid.TAILLE_ECRAN_GRILLE;
	public static final int GRILLE_HEIGHT=VueGrid.TAILLE_ECRAN_GRILLE;
	

	protected boolean actif=true;
	
	/**
	 * Constructeur créant la grille et l'intégrant à la vue.
	 * @param v vue 
	 * @param nbLigne nombre de ligne de la grille
	 */
	public Grid(VueGrid v, int nbRow, int nbCol)
	{
		setBackground(Color.BLUE);
		this.setOpaque(true);
		this.setVisible(true);
		
		//this.setBorder(BorderFactory.createLineBorder(Color.white));
		
		
		GRILLE_POSX=(VueGrid.TAILLE_ECRAN_GRILLE
				-(GRILLE_WIDTH/2));
		GRILLE_POSY=GRILLE_POSY;
		
		this.setPreferredSize(new Dimension(VueGrid.TAILLE_ECRAN_GRILLE,VueGrid.TAILLE_ECRAN_GRILLE));
		this.setMaximumSize(new Dimension(VueGrid.TAILLE_ECRAN_GRILLE,VueGrid.TAILLE_ECRAN_GRILLE));
		this.setVisible(true);
		
		
		this.nbRow=nbRow;
		this.nbCol=nbCol;
		
		this.cases = new Case[nbRow][nbCol];
		this.vue=v;
		
		
		for(int i=0; i<nbRow; i++)
			for(int j=0; j<nbCol; j++)
				cases[i][j]=new Case(i,j,nbRow,nbCol);
		
		
		this.addMouseListener(this);
		this.setFocusable(true);
		this.requestFocus();
		
		repaint();
	}
	
	
	/**
	 * Réinitialiser la grille.
	 */
	public void reinit()
	{
		
		/*
		Color c2=Vue2.getSquareColorByBoxValues(BoxValues.EMPTY_SQUARE);
		for(int i=0; i<nbLigne; i++){
			for(int j=0; j<nbLigne; j++){
				cases[i][j].c=c2;
			}
		}*/
	}
	
	
	
	/**
	 * Test de collision sur les différents traits
	 * @param arg0 Souris 
	 * @return Coté si on en trouve un qui concorde avec la position de souris
	 */
	
	public boolean collide(int x, int mouseX, int mouseY)
	{
		return(mouseX>=cases[x][0].posX 
				&&mouseX<=cases[x][0].posX+Case.DIAMETRE_CASE
				&&mouseY>=cases[x][0].posY
				&&mouseY<=cases[x][nbCol-1].posY+Case.DIAMETRE_CASE);
		
	}
	
	public int collision(MouseEvent arg0)
	{
		if(actif)
		for(int i=0; i<nbRow; i++)
		{	
				if(collide(i,arg0.getX(), arg0.getY()))
				{
					vue.controler.setJeton(i);
					this.repaint();
					return 1;
				}
				
				
		}
		
		return -1;
	}
	
	
		

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		collision(arg0);
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//g.clearRect(0, 0, VueGrille.TAILLE_ECRAN_GRILLE, VueGrille.TAILLE_ECRAN_GRILLE);
		
		int epaisseurTrait=10;
		
		for(int i=0; i<nbRow; i++)
			for(int j=0; j<nbCol; j++)
			{
				g.setColor(new Color(0, 0, 128));
				g.fillOval (cases[i][j].posX-epaisseurTrait/2, cases[i][j].posY-epaisseurTrait/2
						, cases[i][j].hX+epaisseurTrait, cases[i][j].hY+epaisseurTrait);
				
				
				g.setColor(cases[i][j].c);
				//System.out.println(cases[i][j].c);
				//g.clearRect(cases[i][j].posX, cases[i][j].posY, cases[i][j].hX, cases[i][j].hY);
				g.fillOval (cases[i][j].posX, cases[i][j].posY, cases[i][j].hX, cases[i][j].hY);
				
			}
		
	}

	
}
