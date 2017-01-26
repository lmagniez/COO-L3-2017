package com.vue.titre;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import com.vue.Colors;
import com.vue.grille.ButtonMenu;

public class EcranParam extends JPanel{

	protected Vue1 f;
	
	protected ButtonMenu start;
	protected ButtonMenu quit;
	
	protected JLabel startLabel;
	
	protected JSlider nombreCase;
	protected JComboBox typeJoueurs[];
	
	
	
	public static final int MIN_PARAM=2;
	public static final int MAX_PARAM=9;
	
	
	
	/**
	 * Initialisation du JPanel (1 fois)
	 * @param f Fenetre utilisé pour les ActionListener
	 */
	public EcranParam(Vue1 f)
	{
		this.f=f;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		/*
		 * Init les composants
		 */
		
		//init bouton start
		start=new ButtonMenu("Démarrer23",Colors.textColor2,Colors.case8);
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
        
		//init combo box
		String str[]={"JOUEUR","IA"};
		String str2[]={"NONE","JOUEUR","IA"};
		
        typeJoueurs = new JComboBox[4];
        for(int i=0; i<2; i++)
        {
        	typeJoueurs[i] = new JComboBox(str);
        	typeJoueurs[i].setMaximumSize(new Dimension(150,50));
        	typeJoueurs[i].setSelectedIndex(0);
        }
        for(int i=2; i<4; i++)
        {
        	typeJoueurs[i] = new JComboBox(str2);
        	typeJoueurs[i].setMaximumSize(new Dimension(150,50));
        	typeJoueurs[i].setSelectedIndex(0);
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
		p2.add(new JLabel("J1: "));
		p2.add(typeJoueurs[0]);
		p2.add(Box.createRigidArea(new Dimension(30,10)));
		p2.add(new JLabel("J2: "));
		p2.add(typeJoueurs[1]);
		
		JPanel p3=new JPanel();
		p3.setLayout(new BoxLayout(p3,BoxLayout.LINE_AXIS));
		p3.add(new JLabel("J3: "));
		p3.add(typeJoueurs[2]);
		p3.add(Box.createRigidArea(new Dimension(30,10)));
		p3.add(new JLabel("J4: "));
		p3.add(typeJoueurs[3]);
		
		this.add(p2);
		this.add(p3);
		this.add(Box.createRigidArea(new Dimension(5,50)));
		
        //Boutons start et quitter
        
		this.add(start);
		this.add(Box.createRigidArea(new Dimension(5,30)));
		this.add(quit);
		this.add(Box.createRigidArea(new Dimension(5,50)));
		
	}
	
	class ButtonListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e) {
			String command = ((JButton) e.getSource()).getActionCommand();
			
			if(command=="Démarrer23")
			{
				System.out.println("Demarrer");
				int nbJoueurs=0;
				boolean isIA[];
				
				for(int i=0; i<4; i++){
					if("JOUEUR".equals(typeJoueurs[i].getSelectedItem())
							||"IA".equals(typeJoueurs[i].getSelectedItem()))
						nbJoueurs++;
				}
				
				isIA=new boolean[nbJoueurs];
				int cpt=0;//si une case a none puis une ia
				for(int i=0; i<4; i++){
					if("IA".equals(typeJoueurs[i].getSelectedItem()))
					{
						isIA[cpt]=true;
						cpt++;
					}
					if("JOUEUR".equals(typeJoueurs[i].getSelectedItem())) 
					{	
						isIA[cpt]=false;
						cpt++;
					}
				}	
				
				f.controler.genererJeu(f, nombreCase.getValue(), nbJoueurs,isIA);				
			}
			
			
			if(command=="Quitter")
				f.dispose();
		} 
	}
	
}

