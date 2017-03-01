package com.vue.grid;

import java.awt.Color;
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
	
	JTextArea textBox;
	private String msgTour;
	
	private JButton retour,confirmer;
	private JButton restart;
	
	
	
	
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
		
		title = new ImageIcon("./sprites/title.jpeg");
		JLabel titre = new JLabel();
		JLabel titre2 = new JLabel("test");
		titre2.setIcon(title);
		
		msgTour="La textBox";
		textBox=initTextArea(msgTour);
		textBox.setEditable(false);
		textBox.setMaximumSize(new Dimension(VueGrid.TAILLE_ECRAN_GRILLE_X,VueGrid.TAILLE_ECRAN_SCORE-30));
		textBox.setSize(new Dimension(VueGrid.TAILLE_ECRAN_GRILLE_X,VueGrid.TAILLE_ECRAN_SCORE-30));
		textBox.setPreferredSize(new Dimension(VueGrid.TAILLE_ECRAN_GRILLE_X,VueGrid.TAILLE_ECRAN_SCORE-30));
		
		
		this.add(titre);
		this.add(textBox);
		
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
		this.changeTour(0);
		
		repaint();
		
	}
	
	/**
	 * Initialisation de la zone de texte.
	 * @param s Le contenu de la JTextArea
	 * @return La JTextArea initialisé
	 */
	
	public JTextArea initTextArea(String s)
	{
		
		JTextArea textArea = new JTextArea();
        textArea.setRows(15);
        textArea.setColumns(10);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setBackground(Color.lightGray);
		textArea.append(s);
		return textArea;

	}
	
	
	/**
	 * Changer le joueur courant (maj affichage)
	 * @param tour joueur courant
	 */
	public void changeTour(int tour)
	{
		msgTour="Au tour de: J"+(tour+1);
		textBox.setText(msgTour);	
	}
	
	
	
	class ButtonListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e) {
			
			
			String command = ((JButton) e.getSource()).getActionCommand();
			
			System.out.println("recommencer");
			
			if(command=="Confirmer")
			{
				//vue.controler.requestVerif(ScoreCreation.this.vue.getGrid().idGrid);
			}
			
			if(command=="Recommencer")
			{
				vue.controler.reset();
			}
			
			
			if(command=="Retour")
			{
				
				ScoreCreation.this.vue.controler.removeObserverModel();
				vue.vueMenu.setVisible(true);
				vue.setVisible(false);
				
				
			}
		} 
	}


	public void displayWinner(int tour) {
		tour=(tour+1)%2;
		msgTour="J"+(tour+1)+" a remporté la partie !!";
		textBox.setText(msgTour);	
	}
	
	
}
