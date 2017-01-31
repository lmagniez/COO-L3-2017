package com.vue.grid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.model.BoxValues;
import com.vue.grille.Vue2;

/**
 * JPanel du score de chaque joueur et du joueur courant
 * @author loick
 *
 */
public class Score extends JPanel {
	
	private int nbJoueur;
	
	private JLabel labelScore[];
	private int scoreJ[];
	
	private JLabel labelNbTraits;
	private int nbTraits;
	
	private JLabel labelTour;
	private int tour;
	
	private Image title;
	
	/**
	 * Constructeur, initialise le Jpanel
	 * @param nbJoueur
	 */
	public Score(int nbJoueur)
	{
		
		this.setMaximumSize(new Dimension(200,200));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(Color.BLACK);
		//this.setOpaque(true);
		
		title = new ImageIcon("./sprites/title.jpeg").getImage();
		//Icon title=new ImageIcon(new ImageIcon("./sprites/title.jpeg").getImage().getScaledInstance(300, 100, Image.SCALE_DEFAULT));
		JLabel titre = new JLabel();
		JLabel titre2 = new JLabel("test");
		
		this.add(titre);
		this.add(titre2);
		
		repaint();
		
	}
	
	/**
	 * Ajouter un trait (maj affichage)
	 */
	public void addTrait()
	{
		nbTraits++;
		labelNbTraits.setText("Nombre Traits: "+nbTraits);
	}
	
	/**
	 * Ajouter un carré à un joueur (maj affichage)
	 * @param idJoueur (id joueur)
	 */
	public void addCarreJoueur(int idJoueur)
	{
		scoreJ[idJoueur]++;
		labelScore[idJoueur].setText("Score J"+(idJoueur+1)+": "+scoreJ[idJoueur]);
	}
	
	/**
	 * Réinitialiser le score (maj affichage)
	 */
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
	
	/**
	 * Changer le joueur courant (maj affichage)
	 * @param tour joueur courant
	 */
	public void changeTour(int tour)
	{
		this.tour=tour;
		labelTour.setText("Au tour de: J"+(tour+1));
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//g.drawImage(title, VueGrille.TAILLE_ECRAN_GRILLE, 0, 
		//		VueGrille.TAILLE_ECRAN_SCORE,100,this);
		
		g.drawImage(title, 500, 100,this);
		
		
	}
	
	
	
}
