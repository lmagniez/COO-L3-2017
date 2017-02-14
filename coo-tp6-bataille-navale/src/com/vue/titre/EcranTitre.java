package com.vue.titre;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.vue.Colors;
import com.vue.ButtonMenu;

public class EcranTitre extends JPanel{
	protected Vue1 f;
	
	protected ButtonMenu start;
	protected ButtonMenu quit;
	
	protected JPanel p;
	
	protected JLabel startLabel;
	
	/**
	 * Initialisation du JPanel (1 fois)
	 * @param f Fenetre utilisé pour les ActionListener
	 */
	public EcranTitre(Vue1 f)
	{
		this.f=f;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		start=new ButtonMenu("Démarrer",Colors.textColor2,Colors.case8);
		start.addActionListener(new ButtonListener());
		
		quit=new ButtonMenu("Quitter",Colors.textColor2,Colors.case16);	
		quit.addActionListener(new ButtonListener());
		
		startLabel=new JLabel("Bataille Navale");
		startLabel.setSize(new Dimension(150,50));
		startLabel.setFont(new Font("Arial",Font.BOLD,40));
		startLabel.setAlignmentX(this.CENTER_ALIGNMENT);
		
		this.add(Box.createRigidArea(new Dimension(5,50)));
		this.add(startLabel);
		this.add(Box.createRigidArea(new Dimension(5,50)));
		this.add(start);
		this.add(Box.createRigidArea(new Dimension(5,50)));
		this.add(quit);
	}

	/**
	 * Démarrer: Lance la fenetre de jeu
	 * Quitter: Ferme la fenetre
	 */
	
	class ButtonListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e) {
			String command = ((JButton) e.getSource()).getActionCommand();
			
			if(command=="Démarrer")
			{
				f.setSize(new Dimension(500,500));
				f.afficherPanneau(f.lePanneau2);
				
			}
			
			if(command=="Quitter")
				f.dispose();
		} 
	}
	
}
