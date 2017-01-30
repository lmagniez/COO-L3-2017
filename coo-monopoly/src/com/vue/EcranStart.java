package com.vue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.Image;

/**
 * JPanel correspondant à l'écran démarrage, propose de commencer en tant que joueur, ia, ou quitte la partie
 * @author loick
 *
 */

public class EcranStart extends Ecran implements ActionListener{

	private Image fond;
	
	protected JButton start;
	protected JButton quit;
	protected JButton credits;
	
	//protected JButton buttons[][];
	protected JLabel startLabel;
	
	
	/**
	 * Initialisation du JPanel (1 fois)
	 * @param f Fenetre utilisé pour les ActionListener
	 */
	public EcranStart(VueMenu f)
	{
		NB_BUTTONS_X=3;
		NB_BUTTONS_Y=1;
		
		this.vue=f;
		//fond= Toolkit.getDefaultToolkit().createImage("r")
		fond=new ImageIcon("./Sprites/title.jpg").getImage();
		
		this.setLayout(null);
		
		
		start=new JButton("Démarrer");
		start.addActionListener(this);
		start.setAlignmentX(this.CENTER_ALIGNMENT);
		start.setForeground(Colors.textColor2);
		start.setBackground(Colors.case8);
		start.setBorder(new LineBorder (Color.BLACK, 1));
		start.setBounds(100, 250, 200, 50);
		
		credits=new JButton("Crédits");	
		credits.setAlignmentX(this.CENTER_ALIGNMENT);
		credits.addActionListener(this);
		credits.setForeground(Colors.textColor2);
		credits.setBackground(Colors.case128);
		credits.setBorder(new LineBorder (Color.BLACK, 1));
		credits.setBounds(100, 400, 200, 50);
		
		quit=new JButton("Quitter");	
		quit.setAlignmentX(this.CENTER_ALIGNMENT);
		quit.addActionListener(this);
		quit.setForeground(Colors.textColor2);
		quit.setBackground(Colors.case16);
		quit.setBorder(new LineBorder (Color.BLACK, 1));
		quit.setBounds(100, 550, 200, 50);
		
		buttons=new JComponent[NB_BUTTONS_X][NB_BUTTONS_Y];
		buttons[0][0]=start;
		buttons[1][0]=credits;
		buttons[2][0]=quit;
		
		this.addListener();
		
		this.add(start);
		this.add(credits);
		this.add(quit);
	
		
		
		
		//this.add(opt);
		
		//f.lePanneau5.setVisible(false);
		
		
	}
	
	
	

	/**
	 * Démarrer: Lance la fenetre de jeu
	 * IA: Lance l'IA définie dans la classe IA
	 * Quitter: Ferme la fenetre
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = ((JButton) e.getSource()).getActionCommand();
		
		if(command=="Démarrer")
		{
			//this.opt.setVisible(true);
			//this.opt.buttons[0][0].requestFocus();
			
			this.focusNouvelEcran(vue.lePanneau5);
			
			/*
			for(int i=0; i<NB_BUTTONS_X;i++)
			{
				for(int j=0; j<NB_BUTTONS_Y; j++)
				{	
					buttons[i][j].setForeground(Color.WHITE);
					buttons[i][j].setEnabled(false);
				}
			}
			//f.initFenetreEcranJeu();
			//f.lePanneau.setFocusable(true);
			//f.lePanneau.requestFocus();
			*/
		}
		
		if(command=="Crédits")
		{
			
			//this.focusNouvelEcran(vue.lePanneau4);
			vue.afficherPanneau(vue.lePanneau4);
			vue.lePanneau4.requestFocus();
			
			/*
			vue.initFenetreEcranJeu();
			try {
				vue.lePanneau.initIA();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
		}
		
		if(command=="Quitter")
			vue.dispose();
	}
	
	public void paintComponent(Graphics g) {
        g.drawImage(fond, 0, 0, getWidth(), getHeight(), this);
    }
	
}
