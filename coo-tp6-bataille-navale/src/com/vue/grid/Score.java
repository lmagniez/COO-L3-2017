package com.vue.grid;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

import com.model.Constantes;
import com.reseau.EtatClient;


/**
 * JPanel du score de chaque joueur et du joueur courant.
 * @author loick
 *
 */
public class Score extends JPanel {
	
	
	private VueGrid vue;
	
	private ImageIcon title;
	private int idJoueur;
	
	JTextArea textBox;
	private String msgScore;
	private String msgCoupsPris;
	private String msgCoupsRates;
	private String msgTour;
	private String msgNbBateaux;
	protected boolean stop=false;
	
	private int nbBateauxCoules;
	private int score;
	private int coupsPris;
	private int coupsRates;
	
	private JLabel message;
	
	private JButton retour;
	private JButton restart;
	
	
	
	
	/**
	 * Constructeur, initialise le Jpanel
	 * @param nbJoueur
	 */
	public Score(VueGrid vue, int idJoueur){
		
		this.vue=vue;
		this.idJoueur=idJoueur;
		
		this.setPreferredSize(new Dimension(Constantes.TAILLE_ECRAN_GRILLE,Constantes.TAILLE_ECRAN_SCORE));
		this.setMaximumSize(new Dimension(Constantes.TAILLE_ECRAN_GRILLE,Constantes.TAILLE_ECRAN_SCORE));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(Color.BLACK);
		//this.setOpaque(true);
		
		
		textBox=initTextArea(msgTour);
		textBox.setEditable(false);
		this.add(textBox);
		
		restart= new JButton("Recommencer");
		restart.addActionListener(new ButtonListener());
		retour= new JButton("Retour");
		retour.addActionListener(new ButtonListener());
		
		
		
		//this.add(this.add(Box.createRigidArea(new Dimension(5,50))));
		
		
		this.message=new JLabel("Message J1");
		this.message.setForeground(Color.WHITE);
		message.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(message);
		
		
		
		this.coupsPris=0;
		this.coupsRates=0;
		this.score=0;
		this.nbBateauxCoules=0;
		
		this.msgCoupsPris= "Coups Pris: "+coupsPris;
		this.msgCoupsRates= "Coups Rates: "+coupsRates;
		this.msgScore= "Score: "+score;
		this.msgNbBateaux="Bateaux coulés: "+nbBateauxCoules;
		this.changeJoueur(idJoueur);
		
		//this.majTextBox();
		
		repaint();
		
	}
	
	/**
	 * Initialisation de la zone de texte.
	 * @param s Le contenu de la JTextArea
	 * @return La JTextArea initialisé
	 */
	
	public JTextArea initTextArea(String s){
		
		JTextArea textArea = new JTextArea();
        textArea.setRows(15);
        textArea.setColumns(20);
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
	public void changeJoueur(int idJoueur){
		if(idJoueur==0)
			msgTour="JOUEUR";
		if(idJoueur==1)
			msgTour="ADVERSAIRE";
		
		majTextBox();
	}
	
	/**
	 * Changer le score 
	 * @param adversaire score à changer
	 */
	public void changeScore(Score adversaire){
		this.score=adversaire.coupsPris*1000+adversaire.coupsRates*100;
		msgScore="Score: "+score;
		majTextBox();
	}
	
	/**
	 * Changer le nombre de coups pris et mettre à jour le score
	 * @param coupsPris nombre de coups pris
	 */
	public void changeCoupsPris(int coupsPris, int idJoueur){
		this.coupsPris=coupsPris;
		msgCoupsPris="Coups pris: "+coupsPris;
		
		if(this.idJoueur==0)
			vue.score.changeScore(this);
		if(this.idJoueur==1)
			vue.score2.changeScore(this);
		
		majTextBox();
	}
	
	/**
	 * Changer le nombre de coups rates et mettre à jour le score
	 * @param coupsPris nombre de coups rates
	 */
	public void changeCoupsRates(int coupsRates, int idJoueur){
		this.coupsRates=coupsRates;
		msgCoupsRates="Coups rates: "+coupsRates;
		
		if(this.idJoueur==0)
			vue.score.changeScore(this);
		if(this.idJoueur==1)
			vue.score2.changeScore(this);
		//vue.score2.changeScore(this);
		majTextBox();
	}
	
	/**
	 * Mettre à jour le message d'information
	 * @param s
	 */
	public void setMsg(String s){
		this.message.setText(s);
		
	}
	
	public void changeNbBateau(int nbBateau){
		this.msgNbBateaux="Bateaux coulés: "+nbBateau;
		majTextBox();
	}
	
	
	
	/**
	 * Mettre à jour l'affichage de la textBox
	 */
	public void majTextBox(){
		textBox.setText(msgTour+"\n"+msgScore+"\n"+msgCoupsPris+"\n"+msgCoupsRates+"\n"+msgNbBateaux);
		this.repaint();
	}
	
	
	class ButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			
			
			String command = ((JButton) e.getSource()).getActionCommand();
			
			if(command=="Recommencer")
			{
				vue.getControler().reset();	
			}
			
			
			if(command=="Retour")
			{
				vue.getControler().requestCloseSocket();
				vue.vueMenu.setVisible(true);
				vue.setVisible(false);
			}
		} 
	}

	/**
	 * Afficher le gagnant en fonction du tour
	 * @param tour
	 */
	public void displayWinner(int tour) {
		tour=(tour+1)%2;
		msgTour="J"+(tour+1)+" a remporté la partie !!";
		textBox.setText(msgTour);	
	}
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		if((this.vue.getTour()==EtatClient.PLAYER_TURN&&idJoueur==0)
		||(this.vue.getTour()==EtatClient.OPPONENT_TURN&&idJoueur==1))
		{
			g.fillOval (10,Constantes.TAILLE_ECRAN_SCORE-15, 10, 10);
		}
	}
	
}
