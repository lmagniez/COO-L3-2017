package com.vue.grid;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;


/**
 * JPanel du score de chaque joueur et du joueur courant.
 * @author loick
 *
 */
public class ScoreCreation extends JPanel {
	
	
	private VueGridCreation vue;
	
	private ImageIcon title;
	
	private String msgTour;
	
	private JButton retour,confirmer;
	private JButton restart;
	public ImageIcon logo= transform(new ImageIcon("./sprites/picross2.png"),VueGrid.TAILLE_ECRAN_GRILLE_X,VueGrid.TAILLE_ECRAN_SCORE-30);
	
	
	
	
	/**
	 * Constructeur, initialise le Jpanel
	 * @param nbJoueur
	 */
	public ScoreCreation(VueGridCreation vue, int nbJoueur)
	{
		this.vue=vue;
		
		this.setMaximumSize(new Dimension(VueGrid.TAILLE_ECRAN_GRILLE_X,VueGrid.TAILLE_ECRAN_SCORE));
		this.setPreferredSize(new Dimension(VueGrid.TAILLE_ECRAN_GRILLE_X,VueGrid.TAILLE_ECRAN_SCORE));
		this.setSize(new Dimension(VueGrid.TAILLE_ECRAN_GRILLE_X,VueGrid.TAILLE_ECRAN_SCORE));
		this.setMinimumSize(new Dimension(VueGrid.TAILLE_ECRAN_GRILLE_X,VueGrid.TAILLE_ECRAN_SCORE));
		
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(Color.BLACK);
		//this.setOpaque(true);
		
		
		JLabel title = new JLabel();
		title.setMaximumSize(new Dimension(VueGrid.TAILLE_ECRAN_GRILLE_X,VueGrid.TAILLE_ECRAN_SCORE-30));
		title.setSize(new Dimension(VueGrid.TAILLE_ECRAN_GRILLE_X,VueGrid.TAILLE_ECRAN_SCORE-30));
		title.setPreferredSize(new Dimension(VueGrid.TAILLE_ECRAN_GRILLE_X,VueGrid.TAILLE_ECRAN_SCORE-30));
		title.setIcon(logo);
		title.setAlignmentX( Component.CENTER_ALIGNMENT );
		
		this.add(title);
		
		restart= new JButton("Recommencer");
		restart.addActionListener(new ButtonListener());
		retour= new JButton("Retour");
		retour.addActionListener(new ButtonListener());
		confirmer= new JButton("Confirmer");
		confirmer.addActionListener(new ButtonListener());
		
		
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p,BoxLayout.LINE_AXIS));
		p.add(confirmer);
		p.add(restart);
		p.add(retour);
		
		this.add(p);
		
		repaint();
		
	}

	public ImageIcon transform (ImageIcon img, int hx, int hy)
	{
		Image image=img.getImage();
		Image newImg= image.getScaledInstance(hx, hy, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newImg);
	}

	
	
	class ButtonListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e) {
			
			
			String command = ((JButton) e.getSource()).getActionCommand();
			
			if(command=="Confirmer")
			{
				//vue.controler.requestVerif(ScoreCreation.this.vue.getGrid().idGrid);
				vue.getControler().requestSave();
				ScoreCreation.this.confirmer.setEnabled(false);
				ScoreCreation.this.restart.setEnabled(false);
				ScoreCreation.this.retour.setEnabled(false);
				
			}
			
			if(command=="Recommencer")
			{
				vue.getControler().resetCreation();
			}
			
			
			if(command=="Retour")
			{
				
				ScoreCreation.this.vue.getControler().removeObserverModel();
				vue.vueMenu.setVisible(true);
				vue.setVisible(false);
				
				
			}
		} 
	}


	public void displayWinner(int tour) {
		tour=(tour+1)%2;
		msgTour="J"+(tour+1)+" a remporté la partie !!";
	}
	
	
}
