package com.vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class Ecran extends JPanel{

	protected int NB_BUTTONS_X;
	protected int NB_BUTTONS_Y;
	protected JButton buttons[][];
	protected VueMenu vue;
	
	public void addListener()
	{
		for(int i=0; i<NB_BUTTONS_X; i++)
			for(int j=0; j<NB_BUTTONS_Y; j++)
				buttons[i][j].addActionListener(new ButtonListener());
		
		GestionBouton.ajoutListenerBouton(buttons);
		
	}
	
	public void focusNouvelEcran(Ecran c)
	{
		
		for(int i=0; i<NB_BUTTONS_X; i++)
		{
			for(int j=0; j<NB_BUTTONS_Y; j++)
			{
				buttons[i][j].setEnabled(false);
				buttons[i][j].setForeground(Color.WHITE);
			}
		}
		
		System.out.println(c.NB_BUTTONS_X + " "+ c.NB_BUTTONS_Y);
		
		for(int i=0; i<c.NB_BUTTONS_X; i++)
		{
			for(int j=0; j<c.NB_BUTTONS_Y; j++)
			{
				c.buttons[i][j].setEnabled(true);
				c.buttons[i][j].setForeground(Color.WHITE);
			}
		}
		
		vue.afficherPanneau(c);
		
		
		this.setVisible(false);
		c.setVisible(true);
		
		c.buttons[0][0].requestFocus();
		c.buttons[0][0].setForeground(Color.GREEN);
		
		
		
	}
	
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			for(int i=0; i<NB_BUTTONS_X; i++)
				for(int j=0; j<NB_BUTTONS_Y; j++)
					buttons[i][j].setForeground(Color.WHITE);
			
			JButton b = ((JButton) e.getSource());
			b.setForeground(Color.GREEN);
			b.requestFocus();

		}
	}
	
}
