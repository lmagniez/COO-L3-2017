package com.vue.grid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.model.Constantes;

public class PanelVictoire extends JPanel{

	private VueGrid vue;
	
	public PanelVictoire(int idJoueur, VueGrid vue, boolean boutons)
	{
		this.vue=vue;
		this.setMaximumSize(new Dimension(Constantes.TAILLE_ECRAN_GRILLE,Constantes.TAILLE_ECRAN_GRILLE+Constantes.TAILLE_ECRAN_SCORE));
		this.setPreferredSize(new Dimension(Constantes.TAILLE_ECRAN_GRILLE,Constantes.TAILLE_ECRAN_GRILLE+Constantes.TAILLE_ECRAN_SCORE));
		this.setBackground(new Color(0,0,0,65));
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2,BoxLayout.LINE_AXIS));
		
		if(boutons){
			JButton b1=new JButton("Menu");
			JButton b2=new JButton("Quitter");
			b1.addActionListener(new ButtonListener());
			b2.addActionListener(new ButtonListener());
			p2.add(b1);
			p2.add(b2);
		}
		
		JLabel l; 
		if(idJoueur==0)
			l= new JLabel("Gagné");
		else
			l= new JLabel("Perdu");
			
		
		l.setAlignmentX(this.CENTER_ALIGNMENT);
		l.setForeground(Color.WHITE);
		l.setFont(new Font("Arial",Font.BOLD,40));
		
		this.add(l);
		this.add(Box.createRigidArea(new Dimension(5,50)));
		this.add(p2);
		
		
	}
	
	class ButtonListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e) {
			String command = ((JButton) e.getSource()).getActionCommand();
			
			if(command=="Menu")
			{
				vue.vueMenu.setVisible(true);
				vue.dispose();
				
			}
			
			if(command=="Quitter")
				vue.dispose();
		} 
	}
}
