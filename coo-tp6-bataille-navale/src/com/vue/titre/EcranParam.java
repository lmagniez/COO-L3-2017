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
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

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
	
	SpinnerModel numberServer = new SpinnerNumberModel(1792, 1, 2000, 1);     
	JSpinner spinner = new JSpinner(numberServer);
	
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
        
        
        
        /*
         * Placement des composants
         */
		this.add(Box.createRigidArea(new Dimension(5,30)));
        this.add(startLabel);
		this.add(Box.createRigidArea(new Dimension(5,40)));
		
       
        //spinner numéro serveur
		spinner.setMaximumSize(new Dimension(100,25));

		JPanel p3=new JPanel();
		p3.add(Box.createRigidArea(new Dimension(50,5)));
		p3.setLayout(new BoxLayout(p3,BoxLayout.LINE_AXIS));
		p3.add(new JLabel("Port du serveur: "));
		p3.add(spinner);
		p3.add(Box.createRigidArea(new Dimension(50,5)));
		
		this.add(p3);
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

				int nbLigne=10;
				int nbColonne=10;
				int numPort=(int)spinner.getValue();
				
				f.initFenetreEcranJeu(nbLigne, nbColonne, numPort);				
			}
			
			
			if(command=="Quitter")
				f.dispose();
		} 
	}
	
}

