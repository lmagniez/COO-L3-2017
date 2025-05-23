package com.vue.plateau.jeu;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.model.ConstantesModel;
import com.model.ConstantesParam;
import com.model.ConstantesVue;
import com.vue.plateau.EcranJeu;

/**
 * Représente un plateau
 * @author loick
 *
 */
public class Plateau extends JPanel{

	protected EcranJeu e;
	private Case[] cases;
	private Pion[] pions;
	public static boolean casesParcourues[];
	
	protected Image bgImage;
	
	protected Icon maison=transform(new ImageIcon(getClass().getResource("/Sprites/pion/maison.png")),25,25);
	protected Icon hotel=transform(new ImageIcon(getClass().getResource("/Sprites/pion/hotel.png")),25,25);
	
	/**
	 * Constructeur
	 * @param ecranJeu écran du jeu
 	 */
	public Plateau(EcranJeu ecranJeu){
		
		this.e=ecranJeu;
		
		this.setMaximumSize(new Dimension(ConstantesVue.DIMENSION_PLATEAU_X,ConstantesVue.DIMENSION_PLATEAU_Y));
		this.setPreferredSize(new Dimension(ConstantesVue.DIMENSION_PLATEAU_X,ConstantesVue.DIMENSION_PLATEAU_Y));
		this.setSize(new Dimension(ConstantesVue.DIMENSION_PLATEAU_X,ConstantesVue.DIMENSION_PLATEAU_Y));
		this.setMinimumSize(new Dimension(ConstantesVue.DIMENSION_PLATEAU_X,ConstantesVue.DIMENSION_PLATEAU_Y));
		
		this.casesParcourues=new boolean[ConstantesModel.NB_CASES];
		for(int i=0; i<ConstantesModel.NB_CASES; i++){
			this.casesParcourues[i]=true;
		}
		if(ConstantesParam.CASE_MASQUE_ENABLED){
			for(int i=0; i<ConstantesModel.NB_CASES; i++){
				this.casesParcourues[i]=false;
			}
		}
		
		
		bgImage=new ImageIcon(getClass().getResource("/Sprites/plateautest.jpg")).getImage();
		
		this.setLayout(null);
		this.setCases(new Case[ConstantesModel.NB_CASES]);
		int nbCases=0;
		
		//cases[nbCases]=new Case(nbCases,nbCases);
		
		for(int i=0;i<ConstantesModel.NB_CASES; i++){
			getCases()[i]=new Case(this, i,i);
		}
		
		
		
		setPions(new Pion[ConstantesParam.NB_JOUEURS]);
		for(int i=0; i<ConstantesParam.NB_JOUEURS; i++){
			getPions()[i]=new Pion(this,0,i,ConstantesParam.ID_ICONES[i]);
			this.add(getPions()[i]);
			//pions[i].setLocation(50*i,50*i);
			//pions[i].setSize(50,50);
			
		//	pions[i].setLocation(pions[i].posX,pions[i].posY);
		//	pions[i].setSize(pions[i].hX,pions[i].hY);
			
		}
		
		
		for(int i=0; i<ConstantesModel.NB_CASES; i++)
		{
			this.add(getCases()[i]);
			getCases()[i].setLocation(getCases()[i].posX,getCases()[i].posY);
			getCases()[i].setSize(getCases()[i].hX,getCases()[i].hY);
			if(!casesParcourues[i])
				getCases()[i].setVisible(false);
		}
		
		
		
		
		
		
	}
	
	/**
	 * Supprimer une maison
	 * @param position position de la maison
	 * @param nbMaison nombre de maison
	 */
	public void removeMaison(int position, int nbMaison){
		Case c =getCases()[position];
		
		if(nbMaison==4){
			this.remove(c.hotel);
			c.nbMaisons=0;
			for(int i=0; i<4; i++)
				c.addMaison();
		}
		else{
			if(nbMaison==3)this.remove(c.maison4);
			if(nbMaison==2)this.remove(c.maison3);
			if(nbMaison==1)this.remove(c.maison2);
			if(nbMaison==0)this.remove(c.maison1);
		}
		
	}
	
	/**
	 * Ajouter une maison
	 * @param position position de la case
	 * @param nbMaison nombre de maison
	 */
	public void addMaison(int position, int nbMaison){
		
		
		Case c =getCases()[position];
	
		
		maison=transform(new ImageIcon(getClass().getResource("/Sprites/pion/maison.png")),25,25);
		
		if(nbMaison==5){
			
			
			this.remove(c.maison1);
			this.remove(c.maison2);
			this.remove(c.maison3);
			this.remove(c.maison4);
			
			c.maison1=null;
			c.maison2=null;
			c.maison3=null;
			c.maison4=null;
			
			
			
			
			c.hotel= new JLabel();
			c.hotel.setIcon(hotel);
			c.hotel.setSize(25,25);
			if(position<10)
				c.hotel.setLocation(c.posX, c.posY);
			else if(position<20)
				c.hotel.setLocation(c.posX+ConstantesVue.CASE_HEIGHT/3-25, c.posY);
			else if(position<30)
				c.hotel.setLocation(c.posX, c.posY+ConstantesVue.CASE_HEIGHT/3-25);
			else
				c.hotel.setLocation(c.posX, c.posY-60+ConstantesVue.CASE_HEIGHT/3);
				
			this.add(c.hotel);
			this.setComponentZOrder(c.hotel, 0);
			
			repaint();
			
		}
		else {
			if(nbMaison==1){
				c.maison1= new JLabel();
				c.maison1.setIcon(maison);
				c.maison1.setSize(25,25);
				c.maison1.setPreferredSize(new Dimension(25,25));
				c.maison1.setMaximumSize(new Dimension(25,25));
				c.maison1.setMinimumSize(new Dimension(25,25));
				
				
				
				if(position<10)
					c.maison1.setBounds(c.posX, c.posY,25,25);
				else if(position<20)
					c.maison1.setBounds(c.posX+ConstantesVue.CASE_HEIGHT/3-25, c.posY,25,25);
				else if(position<30)
					c.maison1.setBounds(c.posX, c.posY+ConstantesVue.CASE_HEIGHT/3-25,25,25);
				else
					c.maison1.setBounds(c.posX, c.posY+ConstantesVue.CASE_HEIGHT/3-60,25,25);
				
				this.add(c.maison1);
				//this.setComponentZOrder(c, -1);
				this.setComponentZOrder(c.maison1, 0);				
			}
			else if(nbMaison==2){
				c.maison2= new JLabel();
				c.maison2.setIcon(maison);
				c.maison2.setSize(25,25);
				
				if(position<10)
					c.maison2.setLocation(c.posX+(ConstantesVue.CASE_WIDTH/3)*1/4, c.posY);
				else if(position<20)
					c.maison2.setLocation(c.posX+ConstantesVue.CASE_HEIGHT/3-25, c.posY+(ConstantesVue.CASE_WIDTH/3)*1/4);
				else if(position<30)
					c.maison2.setLocation(c.posX+(ConstantesVue.CASE_WIDTH/3)*1/4, c.posY+ConstantesVue.CASE_HEIGHT/3-25);
				else
					c.maison2.setLocation(c.posX, c.posY+ConstantesVue.CASE_HEIGHT/3-60-(ConstantesVue.CASE_WIDTH/3)*1/4);
				
				
				this.add(c.maison2);
				this.setComponentZOrder(c.maison2, 0);
				
				
			}
			else if(nbMaison==3){
				c.maison3= new JLabel();
				c.maison3.setIcon(maison);
				c.maison3.setSize(25,25);
				
				if(position<10)
					c.maison3.setLocation(c.posX+(ConstantesVue.CASE_WIDTH/3)*2/4, c.posY);
				else if(position<20)
					c.maison3.setLocation(c.posX+ConstantesVue.CASE_HEIGHT/3-25, c.posY+(ConstantesVue.CASE_WIDTH/3)*2/4);
				else if(position<30)
					c.maison3.setLocation(c.posX+(ConstantesVue.CASE_WIDTH/3)*2/4, c.posY+ConstantesVue.CASE_HEIGHT/3-25);
				else
					c.maison3.setLocation(c.posX, c.posY+ConstantesVue.CASE_HEIGHT/3-60-(ConstantesVue.CASE_WIDTH/3)*2/4);
				
				
				this.add(c.maison3);
				this.setComponentZOrder(c.maison3, 0);
			}
			else if(nbMaison==4){
				c.maison4= new JLabel();
				c.maison4.setIcon(maison);
				c.maison4.setSize(25,25);
				c.maison4.setLocation(c.posX+(ConstantesVue.CASE_WIDTH/3)*3/4, c.posY);
				
				if(position<10)
					c.maison4.setLocation(c.posX+(ConstantesVue.CASE_WIDTH/3)*3/4, c.posY);
				else if(position<20)
					c.maison4.setLocation(c.posX+ConstantesVue.CASE_HEIGHT/3-25, c.posY+(ConstantesVue.CASE_WIDTH/3)*3/4);
				else if(position<30)
					c.maison4.setLocation(c.posX+(ConstantesVue.CASE_WIDTH/3)*3/4, c.posY+ConstantesVue.CASE_HEIGHT/3-25);
				else
					c.maison4.setLocation(c.posX, c.posY+ConstantesVue.CASE_HEIGHT/3-60-(ConstantesVue.CASE_WIDTH/3)*3/4);
				
				
				this.add(c.maison4);
				this.setComponentZOrder(c.maison4, 0);
			}
		}
		
		this.e.revalidate();
		this.e.repaint();
		
		this.revalidate();		
		this.repaint();
		
	}
	
	
	
	protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
	    g.drawImage(bgImage, 0, 0, getHeight(), getHeight(), this);
	}
	
	public ImageIcon transform (ImageIcon img, int hx, int hy)
	{
		Image image=img.getImage();
		Image newImg= image.getScaledInstance(hx, hy, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newImg);
	}

	public Case[] getCases() {
		return cases;
	}

	public void setCases(Case[] cases) {
		this.cases = cases;
	}

	public Pion[] getPions() {
		return pions;
	}

	public void setPions(Pion[] pions) {
		this.pions = pions;
	}
	
	
	
}
