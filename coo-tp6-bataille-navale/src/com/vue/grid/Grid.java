package com.vue.grid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.model.Constantes;
import com.model.Orientation;
import com.model.TypeBateau;
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
	public static final int GRILLE_WIDTH=Constantes.TAILLE_ECRAN_GRILLE;
	public static final int GRILLE_HEIGHT=Constantes.TAILLE_ECRAN_GRILLE;
	
	protected boolean joueur;
	protected boolean actif=true;
	
	/**
	 * Constructeur créant la grille et l'intégrant à la vue.
	 * @param v vue 
	 * @param nbLigne nombre de ligne de la grille
	 */
	public Grid(VueGrid v, int nbRow, int nbCol, boolean joueur)
	{
		setBackground(Color.BLUE);
		this.setOpaque(true);
		this.setVisible(true);
		
		//this.setBorder(BorderFactory.createLineBorder(Color.white));
		
		
		GRILLE_POSX=(Constantes.TAILLE_ECRAN_GRILLE
				-(GRILLE_WIDTH/2));
		GRILLE_POSY=GRILLE_POSY;
		
		this.setPreferredSize(new Dimension(Constantes.TAILLE_ECRAN_GRILLE,Constantes.TAILLE_ECRAN_GRILLE));
		this.setMaximumSize(new Dimension(Constantes.TAILLE_ECRAN_GRILLE,Constantes.TAILLE_ECRAN_GRILLE));
		this.setVisible(true);
		
		
		this.nbRow=nbRow;
		this.nbCol=nbCol;
		this.joueur=joueur;
				
		this.cases = new Case[nbRow][nbCol];
		this.vue=v;
		
		
		for(int i=0; i<nbRow; i++)
			for(int j=0; j<nbCol; j++)
				cases[i][j]=new Case(i,j,nbRow,nbCol);
		
		
		this.addMouseListener(this);
		this.setFocusable(true);
		this.requestFocus();
		
		//this.bomb(3, 6, true);
		//this.bomb(3, 5, false);
		//this.bomb(9, 9, true);
		
		
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
	
	public void bomb(int x, int y, boolean touche)
	{
		if(touche)
			this.cases[x][y].c=CaseValueVue.TOUCHE;
		if(!touche)
			this.cases[x][y].c=CaseValueVue.PLOUF;
	}
	
	/**
	 * Test de collision sur les différents traits
	 * @param arg0 Souris 
	 * @return Coté si on en trouve un qui concorde avec la position de souris
	 */
	
	public boolean collide(int x, int y, int mouseX, int mouseY)
	{
		return(mouseX>=cases[x][y].posX 
				&&mouseX<=cases[x][y].posX+Case.DIAMETRE_CASE
				&&mouseY>=cases[x][y].posY
				&&mouseY<=cases[x][y].posY+Case.DIAMETRE_CASE);
		
	}
	
	public int collision(MouseEvent arg0)
	{
		if(actif&&joueur)
		for(int i=0; i<nbRow; i++)
		{	
			for(int j=0; j<nbCol; j++)
			{	
				
				if(collide(i,j,arg0.getX(), arg0.getY()))
				{
					System.out.println("click!! "+i+" "+j);
					vue.controler.setBomb(i, j);
					//vue.controler.setJeton(i);
					//this.repaint();
					return 1;
				}
				
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
		
		//draw grille
		for(int i=0; i<nbRow; i++){
			for(int j=0; j<nbCol; j++){
				g.setColor(new Color(0, 0, 128));
				
				g.setColor(Color.BLACK);
				g.drawRect (cases[i][j].posX, cases[i][j].posY, cases[i][j].hX, cases[i][j].hY);
				
				
			}
		}
		
		//draw joueur
		if(!joueur){
			Bateau[] bat=this.vue.bateaux;
			for(int i=0; i<Constantes.NB_BATEAUX; i++){
				g.setColor(Color.GRAY);
				g.fillRect (bat[i].posX, bat[i].posY, bat[i].hX, bat[i].hY);
			}
		}
		
		//draw coups
		for(int i=0; i<nbRow; i++){
			for(int j=0; j<nbCol; j++){
		
				if(cases[i][j].c!=CaseValueVue.NONE){
					g.setColor(CaseValueVue.fromType(cases[i][j].c));
					g.fillOval (cases[i][j].posX+cases[i][j].hX/4, cases[i][j].posY+cases[i][j].hY/4, 
							cases[i][j].hX/2, cases[i][j].hY/2);
				}
			}
		}
		
		
		
		
			
		
		
	}

	
}
