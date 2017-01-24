package com.vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Grille extends JPanel implements MouseListener{
	
	private VuePrincipale2 vue;
	
	protected Case[][] cases;
	protected Cote[][] cotesH;
	protected Cote[][] cotesV;
	private int nbLigne;
	
	
	public Grille(VuePrincipale2 v, int nbLigne)
	{
		this.nbLigne=nbLigne;
		this.cases = new Case[nbLigne][nbLigne];
		this.cotesV = new Cote[nbLigne + 1][nbLigne];
		this.cotesH = new Cote[nbLigne][nbLigne + 1];
		this.vue=v;
		
		
		for(int i=0; i<nbLigne; i++)
			for(int j=0; j<nbLigne; j++)
				cases[i][j]=new Case(i,j);
		
		System.out.println("--------");

		
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
	
	
	public void paint(Graphics g)
	{
		
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
				g.fillRect (cases[i][j].posX, cases[i][j].posY, cases[i][j].hX, cases[i][j].hY);
			}	
	}

	
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
