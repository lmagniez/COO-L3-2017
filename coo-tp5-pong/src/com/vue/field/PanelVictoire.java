package com.vue.field;

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

import com.model.Constantes;

public class PanelVictoire extends JPanel{

	private VueField vue;
	
	public PanelVictoire(int idJoueur, VueField vue)
	{
		this.vue=vue;
		this.setMaximumSize(new Dimension(Constantes.DIMENSION_X,Constantes.DIMENSION_Y));
		this.setPreferredSize(new Dimension(Constantes.DIMENSION_X,Constantes.DIMENSION_Y));
		this.setBackground(new Color(0,0,0,65));
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2,BoxLayout.LINE_AXIS));
		
		JButton b1=new JButton("Menu");
		JButton b2=new JButton("Quitter");
		b1.addActionListener(new ButtonListener());
		b2.addActionListener(new ButtonListener());
		p2.add(b1);
		p2.add(b2);
		
		JLabel l = new JLabel("J"+idJoueur+" a gagné");
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
				vue.menu.setVisible(true);
				vue.dispose();
				
			}
			
			if(command=="Quitter")
				vue.dispose();
		} 
	}
}
