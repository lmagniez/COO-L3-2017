package com.vue.grid;

import java.awt.Color;
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
	private String msgPlus;
	
	
	
	private int score;
	private int coupsPris;
	private int coupsRates;
	
	
	
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
		
		title = new ImageIcon("./sprites/title.jpeg");
		JLabel titre = new JLabel();
		JLabel titre2 = new JLabel("test");
		titre2.setIcon(title);
		
		textBox=initTextArea(msgTour);
		textBox.setEditable(false);
		
		this.add(titre);
		this.add(textBox);
		
		restart= new JButton("Recommencer");
		restart.addActionListener(new ButtonListener());
		retour= new JButton("Retour");
		retour.addActionListener(new ButtonListener());
		
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p,BoxLayout.LINE_AXIS));
		p.add(restart);
		p.add(retour);
		this.add(p);
		
		this.coupsPris=0;
		this.coupsRates=0;
		this.score=0;
		
		this.msgCoupsPris= "Coups Pris: "+coupsPris;
		this.msgCoupsRates= "Coups Rates: "+coupsRates;
		this.msgScore= "Score: "+score;
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
	
	
	public void changeScore(Score adversaire){
		this.score=adversaire.coupsPris*1000+adversaire.coupsRates*100;
		msgScore="Score: "+score;
		majTextBox();
	}
	
	public void addCoupsPris(){
		coupsPris++;
		msgCoupsPris="Coups pris: "+coupsPris;
		vue.score2.changeScore(this);
		majTextBox();
	}
	
	public void addCoupsRates(){
		coupsRates++;
		msgCoupsRates="Coups rates: "+coupsRates;
		vue.score2.changeScore(this);
		majTextBox();
	}
	
	public void setMsg(String s){
		this.msgPlus=s;
		majTextBox();
	}
	
	public void majTextBox(){
		textBox.setText(msgTour+"\n"+msgScore+"\n"+msgCoupsPris+"\n"+msgCoupsRates+"\n"+msgPlus);
	}
	
	
	class ButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			
			
			String command = ((JButton) e.getSource()).getActionCommand();
			
			System.out.println("recommencer");
			
			if(command=="Recommencer")
			{
				vue.getControler().reset();
				
			}
			
			
			if(command=="Retour")
			{
				try {
					vue.socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		if(this.vue.getTour()==idJoueur)
			g.fillOval (10,Constantes.TAILLE_ECRAN_SCORE-20, 10, 10);
		//if(this.vue.getTour()==1)
		//	g.fillOval (10,Constantes.TAILLE_ECRAN_SCORE-20, 10, 10);
	}
	
}
