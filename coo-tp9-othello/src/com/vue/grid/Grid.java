package com.vue.grid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.model.CaseValue;



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
	
	public static int GRILLE_POSX;
	public static int GRILLE_POSY;
	
	public static final int INDICE_SIZE=10;
	public static final int ESPACEMENT_SIZE=15;
	public static final int ESPACEMENT2_SIZE=15;
	
	
	public static final int GRILLE_WIDTH=VueGrid.TAILLE_ECRAN_GRILLE_X-INDICE_SIZE-ESPACEMENT_SIZE*2;
	public static final int GRILLE_HEIGHT=VueGrid.TAILLE_ECRAN_GRILLE_Y-INDICE_SIZE;
	

	protected boolean actif=true;
	
	/**
	 * Constructeur créant la grille et l'intégrant à la vue.
	 * @param v vue 
	 * @param idGrid id de la grille
	 * @param nbRow nombre de ligne 
	 * @param nbCol nombre de colonne
	 * @param nom nom de la grille
	 * @param infosLigne indices de ligne
	 * @param infosColonne indices de colonne
	 */
	public Grid(VueGrid v, int nbRow, int nbCol)
	{
		this.nbRow=nbRow;
		this.nbCol=nbCol;
		
		setBackground(new Color(98,31,6));
		this.setOpaque(true);
		this.setVisible(true);
		
		
		GRILLE_POSX=(VueGrid.TAILLE_ECRAN_GRILLE_X
				-(GRILLE_WIDTH/2));
		GRILLE_POSY=GRILLE_POSY;
		
		this.setPreferredSize(new Dimension(VueGrid.TAILLE_ECRAN_GRILLE_X,VueGrid.TAILLE_ECRAN_GRILLE_Y));
		this.setMaximumSize(new Dimension(VueGrid.TAILLE_ECRAN_GRILLE_X,VueGrid.TAILLE_ECRAN_GRILLE_Y));
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
	 * Test de collision pour une case donnée
	 * @param x abscisse 
	 * @param y ordonée
	 * @param mouseX posX de la souris
	 * @param mouseY posY de la souris
	 * @return collision ou non
	 */
	public boolean collide(int x, int y, int mouseX, int mouseY)
	{
		return(mouseX>=cases[x][y].posX 
				&&mouseX<=cases[x][y].posX+Case.DIAMETRE_CASE
				&&mouseY>=cases[x][y].posY
				&&mouseY<=cases[x][y].posY+Case.DIAMETRE_CASE);
		
	}
	
	/**
	 * Test de collision
	 * @param arg0 
	 * @return collision ou non
	 */
	public int collision(MouseEvent arg0)
	{
		if(actif)
		for(int i=0; i<nbRow; i++)
		{	
			for(int j=0; j<nbCol; j++){
				if(collide(i,j,arg0.getX(), arg0.getY()))
				{
					this.vue.gridControler.changeValue(i,j,vue.tour);
					this.repaint();
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
		
		
		
		
		for(int i=0; i<nbRow; i++)
			for(int j=0; j<nbCol; j++)
			{
				
				g.setColor(new Color(46,115,58));
				g.fillRect(cases[i][j].posX, cases[i][j].posY, cases[i][j].hX, cases[i][j].hY);
				g.setColor(Color.LIGHT_GRAY);
				g.drawRect(cases[i][j].posX, cases[i][j].posY, cases[i][j].hX, cases[i][j].hY);
				
				
				g.setColor(cases[i][j].color);
				//System.out.println(cases[i][j].c);
				//g.clearRect(cases[i][j].posX, cases[i][j].posY, cases[i][j].hX, cases[i][j].hY);
				g.fillOval (cases[i][j].posX+4, cases[i][j].posY+4, cases[i][j].hX-8, cases[i][j].hY-8);
				
				
				g.setFont(new Font("Arial", Font.BOLD, 15)); 
				
			}
		
		if(vue.posJouable!=null)
			for(int i=0; i<vue.posJouable.size(); i++){
				
				if(this.vue.tour==CaseValue.J1)
					g.setColor(Color.WHITE);
				else
					g.setColor(Color.BLACK);
				int[] pos=vue.posJouable.get(i);
				g.drawOval(cases[pos[0]][pos[1]].posX+4, cases[pos[0]][pos[1]].posY+4, 
						cases[pos[0]][pos[1]].hX-8, cases[pos[0]][pos[1]].hY-8);
			}
		
	}

	
}
