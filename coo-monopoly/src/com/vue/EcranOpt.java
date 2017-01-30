package com.vue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;


/**
 * Ecran a appeler dans l'écran jeu
 * @author loick
 *
 */
public class EcranOpt extends Ecran implements ActionListener{
	
	
	protected JButton start;
	protected JButton quit;
	
	protected JLabel startLabel;
	
	protected JSlider nombreCase;
	protected JComboBox typeJoueurs[];
	
	
	
	public static final int MIN_PARAM=2;
	public static final int MAX_PARAM=9;
	
	public EcranOpt(VueMenu vue)
	{
		
		
		NB_BUTTONS_X=1;
		NB_BUTTONS_Y=6;
		
		/*
		this.vue=vue;
		JButton b1=new JButton("test");
		JButton b2=new JButton("Retour");
		b1.addActionListener(this);
		b2.addActionListener(this);
		this.addListener();
		
		buttons=new JButton[NB_BUTTONS_X][NB_BUTTONS_Y];
		buttons[0][0]=b1;
		buttons[0][1]=b2;
		
		
		
		this.add(b1);
		this.add(b2);
		*/
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		
		/*
		 * Init les composants
		 */
		
		//init bouton start
		start=new ButtonMenu("Démarrer",Colors.textColor2,Colors.case8);
		start.addActionListener(new ButtonListener());
		
		//init bouton quitter
		quit=new ButtonMenu("Quitter",Colors.textColor2,Colors.case16);	
		quit.addActionListener(new ButtonListener());
		
		//init label titre
		startLabel=new JLabel("Paramètres de la partie ");
		startLabel.setSize(new Dimension(150,50));
		startLabel.setFont(new Font("Arial",Font.BOLD,20));
		startLabel.setAlignmentX(this.CENTER_ALIGNMENT);
		
		//init slider nombre case
		nombreCase = new JSlider(JSlider.HORIZONTAL, MIN_PARAM, MAX_PARAM, MAX_PARAM/2);
		nombreCase.setMinorTickSpacing(1);
	    nombreCase.setMajorTickSpacing(1);
	    nombreCase.setPaintTicks(true);
	    nombreCase.setPaintLabels(true);
	    nombreCase.setLabelTable(nombreCase.createStandardLabels(1));
		nombreCase.setMaximumSize(new Dimension(200,50));
		
		//nombreCase.addActionListener(this);
		
		buttons=new JComponent[NB_BUTTONS_X][NB_BUTTONS_Y];
	    
		//init combo box
		String str[]={"JOUEUR","IA"};
		String str2[]={"NONE","JOUEUR","IA"};
		
	    typeJoueurs = new JComboBox[4];
	    for(int i=0; i<2; i++)
	    {
	    	typeJoueurs[i] = new JComboBox(str);
	    	typeJoueurs[i].setMaximumSize(new Dimension(150,50));
	    	typeJoueurs[i].setSelectedIndex(0);
	    	
	    	buttons[0][i]=typeJoueurs[i];
	    	typeJoueurs[i].addActionListener(this);
	    }
	    for(int i=2; i<4; i++)
	    {
	    	typeJoueurs[i] = new JComboBox(str2);
	    	typeJoueurs[i].setMaximumSize(new Dimension(150,50));
	    	typeJoueurs[i].setSelectedIndex(0);
	    	
	    	buttons[0][i]=typeJoueurs[i];
	    	typeJoueurs[i].addActionListener(this);
	    }
	    
	    
	    

	    
	    
	    /*
	     * Placement des composants
	     */
	    
	    this.add(startLabel);
		this.add(Box.createRigidArea(new Dimension(5,50)));
		
		//Nombre de cases
		JPanel p=new JPanel();
		p.setLayout(new BoxLayout(p,BoxLayout.LINE_AXIS));
		p.add(new JLabel("Nombre de cases :"));
		p.add(Box.createRigidArea(new Dimension(30,10)));
		p.add(nombreCase);
		this.add(p);
		this.add(Box.createRigidArea(new Dimension(5,50)));
		
		//Parametres des joueurs
		
		
		JPanel p2=new JPanel();
		p2.setLayout(new BoxLayout(p2,BoxLayout.LINE_AXIS));
		
		for(int i=0; i<4; i++)
		{
			p2.add(new JLabel("J"+(i+1)+": "));
			p2.add(typeJoueurs[i]);
			p2.add(Box.createRigidArea(new Dimension(30,10)));
			
		}
		
		this.add(p2);
		this.add(Box.createRigidArea(new Dimension(5,50)));
		
	    //Boutons start et quitter
	    
		this.add(start);
		start.addActionListener(this);
		this.add(Box.createRigidArea(new Dimension(5,30)));
		
		quit.addActionListener(this);
		this.add(quit);
		this.add(Box.createRigidArea(new Dimension(5,50)));
		
		buttons[0][4]=start;
		buttons[0][5]=quit;
		
		
		//this.setBounds(300, 200, 800, 400);
		
		this.addListener();
	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		/*
		String command = ((JComponent) arg0.getSource()).getActionCommand();
		
		if(command=="Retour")
		{
			
			this.focusNouvelEcran(vue.lePanneau3);
			//vue.lePanneau3.requestFocus();
		}*/
	}
	
	
	
}
