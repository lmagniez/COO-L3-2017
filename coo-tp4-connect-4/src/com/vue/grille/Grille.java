package com.vue.grille;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.model.BoxValues;

/**
 * Classe représentant une Grille
 * Dispose d'un tableau de traits horizontaux, un tableau de traits verticaux, et un tableau de cases 
 * @author loick
 *
 */
public class Grille extends JPanel implements MouseListener{
	
	private Vue2 vue;
	
	protected Case[][] cases;
	protected Cote[][] cotesH;
	protected Cote[][] cotesV;
	protected int nbLigne;
	
	public static int GRILLE_POSX;
	public static int GRILLE_POSY;
	
	/**
	 * Constructeur créant la grille et l'intégrant à la vue.
	 * @param v vue 
	 * @param nbLigne nombre de ligne de la grille
	 */
	public Grille(Vue2 v, int nbLigne)
	{
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		
		
		GRILLE_POSX=(Vue2.TAILLE_ECRAN_GRILLE
				-((Cote.LONG)*nbLigne))/2;
		GRILLE_POSY=GRILLE_POSX;
		
		this.setPreferredSize(new Dimension(Vue2.TAILLE_ECRAN_GRILLE,Vue2.TAILLE_ECRAN_GRILLE));
		this.setMaximumSize(new Dimension(Vue2.TAILLE_ECRAN_GRILLE,Vue2.TAILLE_ECRAN_GRILLE));
		this.setVisible(true);
		
		
		this.nbLigne=nbLigne;
		this.cases = new Case[nbLigne][nbLigne];
		this.cotesV = new Cote[nbLigne + 1][nbLigne];
		this.cotesH = new Cote[nbLigne][nbLigne + 1];
		this.vue=v;
		
		
		for(int i=0; i<nbLigne; i++)
			for(int j=0; j<nbLigne; j++)
				cases[i][j]=new Case(i,j);

		
		for(int i=0; i<nbLigne+1; i++)
			for(int j=0; j<nbLigne; j++)
			{	
				cotesH[j][i]=new Cote(j, i,false);
				cotesV[i][j]=new Cote(i, j,true);
			}
		
		
		this.addMouseListener(this);
		this.setFocusable(true);
		this.requestFocus();
	}
	
	
	/**
	 * Réinitialiser la grille.
	 */
	public void reinit()
	{
		
		Color c=Vue2.getColorByBoxValues(BoxValues.NONE);
		for(int i=0; i<nbLigne+1; i++){
			for(int j=0; j<nbLigne; j++){
				cotesH[j][i].c=c;
				cotesV[i][j].c=c;
			}
		}
		Color c2=Vue2.getSquareColorByBoxValues(BoxValues.EMPTY_SQUARE);
		for(int i=0; i<nbLigne; i++){
			for(int j=0; j<nbLigne; j++){
				cases[i][j].c=c2;
			}
		}
	}
	
	public void paintComponent(Graphics g)
	{
		g.clearRect(0, 0, Vue2.TAILLE_ECRAN_GRILLE, Vue2.TAILLE_ECRAN_GRILLE);
		
		for(int i=0; i<nbLigne+1; i++)
			for(int j=0; j<nbLigne; j++)
			{	
				g.setColor(cotesH[j][i].c);
				//vertical
				g.fillRect (cotesH[j][i].posX, cotesH[j][i].posY, cotesH[j][i].hX, cotesH[j][i].hY);
				
				g.setColor(cotesV[i][j].c);
				//horizont
				g.fillRect (cotesV[i][j].posX, cotesV[i][j].posY, cotesV[i][j].hX, cotesV[i][j].hY);
				
				
				
			}
		
		for(int i=0; i<nbLigne; i++)
			for(int j=0; j<nbLigne; j++)
			{
				g.setColor(cases[i][j].c);
				//g.clearRect(cases[i][j].posX, cases[i][j].posY, cases[i][j].hX, cases[i][j].hY);
				g.fillRect (cases[i][j].posX, cases[i][j].posY, cases[i][j].hX, cases[i][j].hY);
			}
		
		for(int i=0; i<nbLigne+1; i++)
			for(int j=0; j<nbLigne+1; j++)
			{
				g.setColor(new Color(220,220,220));
				g.fillOval(GRILLE_POSX+(Cote.LONG)*i-3, GRILLE_POSY+(Cote.LONG)*j-3,15,15);
			}
		
	}

	
	/**
	 * Test de collision sur les différents traits
	 * @param arg0 Souris 
	 * @return Coté si on en trouve un qui concorde avec la position de souris
	 */
	public Cote collision(MouseEvent arg0)
	{
		for(int i=0; i<nbLigne+1; i++)
			for(int j=0; j<nbLigne; j++)
			{	
				if(cotesH[j][i].collide(arg0.getX(), arg0.getY()))
				{
					//cotesH[j][i].c=Color.BLUE;
					
					
					vue.controler.setLigneHorizontale(j, i);
					repaint();
					return cotesH[j][i];
				}
				if(cotesV[i][j].collide(arg0.getX(), arg0.getY()))
				{	
					//cotesV[i][j].c=Color.BLUE;
					
					
					vue.controler.setLigneVerticale(i, j);
					repaint();
					return cotesV[i][j];
				}
				
			}
		
		return null;
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
	
}
