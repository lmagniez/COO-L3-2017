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

import com.model.CaseValue;


/**
 * JPanel du score de chaque joueur et du joueur courant.
 * @author loick
 *
 */
public class Score extends JPanel {
	
	
	private VueGrid vue;
	
	private ImageIcon title;
	
	JTextArea textBox;
	private String msgTour;
	
	private JButton retour,confirmer;
	private JButton restart;
	
	protected JLabel scoreJ1,scoreJ2;
	
	protected int nbJetonsJ1;
	protected int nbJetonsJ2;
	
	public ImageIcon logo= transform(new ImageIcon("./sprites/logo2.png"),VueGrid.TAILLE_ECRAN_GRILLE_X,VueGrid.TAILLE_ECRAN_SCORE-30);
	
	protected JPanel p2;
	
	
	/**
	 * Constructeur, initialise le Jpanel
	 * @param nbJoueur
	 */
	public Score(VueGrid vue, int nbJoueur)
	{
		this.vue=vue;
		this.nbJetonsJ1=2;
		this.nbJetonsJ2=2;
		
		
		
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
		
		
		
		
		
		JLabel title = new JLabel();

		title.setMaximumSize(new Dimension(VueGrid.TAILLE_ECRAN_GRILLE_X,VueGrid.TAILLE_ECRAN_SCORE*3/5));
		title.setSize(new Dimension(VueGrid.TAILLE_ECRAN_GRILLE_X,VueGrid.TAILLE_ECRAN_SCORE*3/5));
		title.setPreferredSize(new Dimension(VueGrid.TAILLE_ECRAN_GRILLE_X,VueGrid.TAILLE_ECRAN_SCORE*3/5));
		title.setIcon(logo);
		title.setAlignmentX( Component.CENTER_ALIGNMENT );
		
		this.add(title);
		
		restart= new JButton("Recommencer");
		restart.addActionListener(new ButtonListener());
		retour= new JButton("Retour");
		retour.addActionListener(new ButtonListener());
		confirmer= new JButton("Confirmer");
		confirmer.addActionListener(new ButtonListener());
		
		
		scoreJ1=new JLabel("Score J1: 2");
		scoreJ2=new JLabel("Score J2: 2");
		scoreJ1.setForeground(Color.WHITE);
		scoreJ2.setForeground(Color.WHITE);
		
		
		
		class ScoreJoueur extends JPanel{
		
			public ScoreJoueur(){
			
				this.setPreferredSize(new Dimension(VueGrid.TAILLE_ECRAN_GRILLE_X,VueGrid.TAILLE_ECRAN_SCORE/5));
				this.setMaximumSize(new Dimension(VueGrid.TAILLE_ECRAN_GRILLE_X,VueGrid.TAILLE_ECRAN_SCORE/5));
				this.setMinimumSize(new Dimension(VueGrid.TAILLE_ECRAN_GRILLE_X,VueGrid.TAILLE_ECRAN_SCORE/5));
				this.setSize(new Dimension(VueGrid.TAILLE_ECRAN_GRILLE_X,VueGrid.TAILLE_ECRAN_SCORE/5));
				
				this.setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
				
				this.setBackground(Color.LIGHT_GRAY);
				this.setOpaque(true);
				
				this.add(Box.createRigidArea(new Dimension(100,5)));
				this.add(scoreJ1);
				this.add(Box.createRigidArea(new Dimension(150,5)));
				this.add(scoreJ2);
				this.add(Box.createRigidArea(new Dimension(30,5)));
			}
			
			public void paintComponent(Graphics g ){
				
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, VueGrid.TAILLE_ECRAN_GRILLE_X, VueGrid.TAILLE_ECRAN_GRILLE_Y/5);
				
				if(Score.this.vue.tour==CaseValue.J1){
					g.setColor(Color.GREEN);
					g.fillOval(55, 10, 10, 10);
				}
				if(Score.this.vue.tour==CaseValue.J2){
					g.setColor(Color.GREEN);
					g.fillOval(275, 10, 10, 10);
				}
					
				g.setColor(Color.white);
				g.fillOval(70, 1, 25, 25);
				
				g.setColor(Color.white);
				g.drawOval(290, 1, 25, 25);
				
				
			}
		}
		
		p2=new ScoreJoueur();
		
		
		
		
		
		
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p,BoxLayout.LINE_AXIS));
		p.add(confirmer);
		p.add(restart);
		p.add(retour);
		
		this.add(p2);
		this.add(p);
		
		repaint();
		
	}
	
	
	
	class ButtonListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e) {
			
			
			String command = ((JButton) e.getSource()).getActionCommand();
			
			
			if(command=="Sauvegarder")
			{
				
			}
			
			if(command=="Recommencer")
			{
				vue.gridControler.reset();
			}
			
			
			if(command=="Retour")
			{
				vue.vueMenu.setVisible(true);
				vue.setVisible(false);
			}
		} 
	}

	
	public ImageIcon transform (ImageIcon img, int hx, int hy)
	{
		Image image=img.getImage();
		Image newImg= image.getScaledInstance(hx, hy, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newImg);
	}
	

	/**
	 * Afficher le gagnant 
	 * @param tour 
	 */
	public void displayWinner(int tour) {
		tour=(tour+1)%2;
		msgTour="J"+(tour+1)+" a remporté la partie !!";
		textBox.setText(msgTour);	
	}
	
	
	
	
}
