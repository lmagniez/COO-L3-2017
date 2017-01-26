package com.vue.grille;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.model.BoxValues;

public class Score extends JPanel {
	
	private int nbJoueur;
	
	private JLabel labelScore[];
	private int scoreJ[];
	
	private JLabel labelNbTraits;
	private int nbTraits;
	
	private JLabel labelTour;
	private int tour;
	
	
	public Score(int nbJoueur)
	{
		
		this.setMaximumSize(new Dimension(200,200));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(Color.LIGHT_GRAY);
		//this.setOpaque(true);
		
		this.nbJoueur=nbJoueur;
		labelScore=new JLabel[nbJoueur];
		scoreJ= new int[nbJoueur];
		
		for(int i=0; i<nbJoueur; i++)
		{
			scoreJ[i]=0;
			this.labelScore[i]=new JLabel("Score J"+(i+1)+": 0");
			this.labelScore[i].setBorder(new EmptyBorder(10, 10, 0, 0));
			this.labelScore[i].setForeground(Vue2.getColorByBoxValues(BoxValues.fromInteger(i)));
			this.add(labelScore[i]);
		}
		
		
		this.add(Box.createRigidArea(new Dimension(0,10)));
		
		labelNbTraits=new JLabel("Nombre Traits: "+nbTraits);
		labelNbTraits.setBorder(new EmptyBorder(10, 10, 0, 0));
		this.add(labelNbTraits);
		
		
		labelTour=new JLabel("Au tour de: J"+(tour+1));
		labelTour.setBorder(new EmptyBorder(10, 10, 0, 0));
		this.add(labelTour);
		
		
	}
	
	public void addTrait()
	{
		nbTraits++;
		labelNbTraits.setText("Nombre Traits: "+nbTraits);
	}
	
	public void addCarreJoueur(int idJoueur)
	{
		scoreJ[idJoueur]++;
		labelScore[idJoueur].setText("Score J"+(idJoueur+1)+": "+scoreJ[idJoueur]);
	}
	
	
	public void reinit()
	{
		for(int i=0; i<nbJoueur; i++)
		{
			scoreJ[i]=0;
			labelScore[i].setText("Score J"+(i+1)+": 0");
			nbTraits=0;
			labelNbTraits.setText("Nombre Traits: "+nbTraits);
		}
	}
	
	public void changeTour(int tour)
	{
		this.tour=tour;
		labelTour.setText("Au tour de: J"+(tour+1));
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
	
	
	
}
