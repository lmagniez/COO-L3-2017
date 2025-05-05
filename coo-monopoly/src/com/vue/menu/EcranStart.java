package com.vue.menu;
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
import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.controler.AbstractControler;
import com.controler.GameControler;
import com.model.plateau.JeuModel;
import com.vue.Colors;
import com.vue.plateau.VueJeu;

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
	protected JButton regles;
	protected JButton charger;
	
	
	
	//protected JButton buttons[][];
	protected JLabel startLabel;
	
	
	/**
	 * Initialisation du JPanel (1 fois)
	 * @param f Fenetre utilisé pour les ActionListener
	 */
	public EcranStart(VueMenu f)
	{
		NB_BUTTONS_X=4;
		NB_BUTTONS_Y=1;
		
		this.vue=f;
		//fond= Toolkit.getDefaultToolkit().createImage("r")
		fond=new ImageIcon(getClass().getResource("/Sprites/title.jpg")).getImage();
		
		this.setLayout(null);
		
		
		start=new JButton("Démarrer");
		start.addActionListener(this);
		start.setAlignmentX(this.CENTER_ALIGNMENT);
		start.setForeground(Colors.textColor1);
		start.setBackground(Colors.case8);
		start.setBorder(new LineBorder (Color.BLACK, 1));
		start.setBounds(100, 250, 200, 50);
		
		credits=new JButton("Crédits");	
		credits.setAlignmentX(this.CENTER_ALIGNMENT);
		credits.addActionListener(this);
		credits.setForeground(Colors.textColor1);
		credits.setBackground(Colors.case128);
		credits.setBorder(new LineBorder (Color.BLACK, 1));
		credits.setBounds(100, 400, 200, 50);
		
		regles=new JButton("Règles");	
		regles.setAlignmentX(this.CENTER_ALIGNMENT);
		regles.addActionListener(this);
		regles.setForeground(Colors.textColor1);
		regles.setBackground(Colors.case128);
		regles.setBorder(new LineBorder (Color.BLACK, 1));
		regles.setBounds(900, 200, 200, 50);
		
		quit=new JButton("Quitter");	
		quit.setAlignmentX(this.CENTER_ALIGNMENT);
		quit.addActionListener(this);
		quit.setForeground(Colors.textColor1);
		quit.setBackground(Colors.case16);
		quit.setBorder(new LineBorder (Color.BLACK, 1));
		quit.setBounds(100, 550, 200, 50);
		
		charger=new JButton("Charger une partie");	
		charger.setAlignmentX(this.CENTER_ALIGNMENT);
		charger.addActionListener(this);
		charger.setForeground(Colors.textColor1);
		charger.setBackground(Colors.case8);
		charger.setBorder(new LineBorder (Color.BLACK, 1));
		charger.setBounds(900, 450, 200, 50);
		
		
		buttons=new JComponent[NB_BUTTONS_X][NB_BUTTONS_Y];
		buttons[0][0]=start;
		buttons[1][0]=credits;
		buttons[2][0]=quit;
		buttons[3][0]=regles;
		
		
		this.addListener();
		
		this.add(start);
		this.add(credits);
		this.add(regles);
		this.add(quit);
		this.add(charger);
	
		this.start.requestFocus();
		
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
			
			this.focusNouvelEcran(vue.lePanneau5);
			
		}
		
		if(command=="Règles")
		{
			
			//this.focusNouvelEcran(vue.lePanneau4);
			vue.afficherPanneau(vue.lePanneau4);
			vue.lePanneau4.requestFocus();
			
		}
		
		if(command=="Quitter")
			vue.dispose();
		
		if(command=="Charger une partie")
		{
				File fileXML = new File("save.xml");
				if(fileXML.exists()){
					JeuModel jeuModel = new JeuModel(true);
					AbstractControler jeuControler = new GameControler(jeuModel);
					VueJeu jeu=new VueJeu(jeuControler,this.vue);
					jeuModel.addObserver(jeu);
					
					jeuModel.notifierTerrain();
					jeuModel.notifyJoueurs();
					
					jeuModel.notifyCases(jeuModel.getP().getCases());
					jeuModel.notifyTour(jeuModel.getTour());
					jeuModel.notifyInitTourDes();
					jeuModel.notifyInitTourFenetre();
					
					
					jeu.setVisible(true);
					this.vue.setVisible(false);
				}
			
		}
	}
	
	public void paintComponent(Graphics g) {
        g.drawImage(fond, 0, 0, getWidth(), getHeight(), this);
    }
	
}
