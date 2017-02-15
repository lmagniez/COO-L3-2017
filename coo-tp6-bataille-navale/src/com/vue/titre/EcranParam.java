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
import com.vue.ButtonMenu;

/**
 * Classe correspondant à l'écran de paramétrage de la partie.
 * Intervient dans la vue du menu.
 * @author loick
 *
 */

public class EcranParam extends JPanel{

	protected Vue1 f;
	
	protected ButtonMenu start;
	protected ButtonMenu quit;
	
	protected JLabel startLabel;
	
	protected JSlider nbRow,nbCol;
	protected JComboBox typeJoueurs[],nbJetonsG,couleurJeton;
	
	public static final int MIN_PARAM=3;
	public static final int MAX_PARAM=15;
	
	
	
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
        
		
		//init combo box
		String str[]={"JOUEUR","IA"};
		String str2[]={"NONE","JOUEUR","IA"};
		String str3[]={"3","4","5","6"};
		
		
        typeJoueurs = new JComboBox[2];
        for(int i=0; i<2; i++)
        {
        	typeJoueurs[i] = new JComboBox(str);
        	typeJoueurs[i].setMaximumSize(new Dimension(150,50));
        	typeJoueurs[i].setSelectedIndex(0);
        }
        
        
        /*
         * Placement des composants
         */
        
        this.add(startLabel);
		this.add(Box.createRigidArea(new Dimension(5,40)));
		
		
        	
		
		//Parametres des joueurs
		JPanel p2=new JPanel();
		p2.setLayout(new BoxLayout(p2,BoxLayout.LINE_AXIS));
		p2.add(new JLabel("J1: "));
		p2.add(typeJoueurs[0]);
		p2.add(Box.createRigidArea(new Dimension(30,10)));
		p2.add(new JLabel("J2: "));
		p2.add(typeJoueurs[1]);
		
		
		this.add(p2);
		this.add(Box.createRigidArea(new Dimension(5,50)));
		
        //Boutons start et quitter
        
		this.add(start);
		this.add(Box.createRigidArea(new Dimension(5,30)));
		this.add(quit);
		this.add(Box.createRigidArea(new Dimension(5,50)));
		
	}
	
	
	/**
	 * Listener des boutons de l'écran de paramètrage.
	 * @author loick
	 *
	 */
	
	class ButtonListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e) {
			String command = ((JButton) e.getSource()).getActionCommand();
			
			if(command=="Démarrer")
			{
				int nbJoueurs=2;
				boolean isIA[];
				
				isIA=new boolean[nbJoueurs];
				int cpt=0;//si une case a none puis une ia
				for(int i=0; i<2; i++){
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
				
				int nbLigne=10;
				int nbColonne=10;
				
				
				f.initFenetreEcranJeu(nbLigne, nbColonne, isIA);				
			}
			
			
			if(command=="Quitter")
				f.dispose();
		} 
	}
	
}

