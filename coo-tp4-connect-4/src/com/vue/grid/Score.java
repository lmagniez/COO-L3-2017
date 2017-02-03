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
public class Score extends JPanel {
	
	
	private VueGrid vue;
	
	private Image title;
	
	JTextArea textBox;
	private String msgTour;
	
	private JButton retour;
	private JButton restart;
	
	
	
	
	/**
	 * Constructeur, initialise le Jpanel
	 * @param nbJoueur
	 */
	public Score(VueGrid vue, int nbJoueur)
	{
		this.vue=vue;
		
		this.setMaximumSize(new Dimension(200,200));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(Color.BLACK);
		//this.setOpaque(true);
		
		title = new ImageIcon("./sprites/title.jpeg").getImage();
		//Icon title=new ImageIcon(new ImageIcon("./sprites/title.jpeg").getImage().getScaledInstance(300, 100, Image.SCALE_DEFAULT));
		JLabel titre = new JLabel();
		JLabel titre2 = new JLabel("test");
		
		msgTour="La textBox";
		textBox=initTextArea(msgTour);
		
		this.add(titre);
		this.add(titre2);
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
	public void changeTour(int tour)
	{
		msgTour="Au tour de: J"+(tour+1);
		textBox.setText(msgTour);	
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(title, 500, 100,this);
		
		
	}
	
	
	class ButtonListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e) {
			
			
			String command = ((JButton) e.getSource()).getActionCommand();
			
			System.out.println("recommencer");
			
			if(command=="Recommencer")
			{
				System.out.println("recommencer");
				vue.controler.reset();
			}
			
			
			if(command=="Retour")
			{
				vue.vueMenu.setVisible(true);
				vue.setVisible(false);
			}
		} 
	}


	public void displayWinner(int tour) {
		msgTour="J"+(tour+1)+" a remporté la partie !!";
		textBox.setText(msgTour);	
	}
	
	
}
