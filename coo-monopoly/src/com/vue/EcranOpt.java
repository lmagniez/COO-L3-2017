package com.vue;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Ecran a appeler dans l'écran jeu
 * @author loick
 *
 */
public class EcranOpt extends Ecran implements ActionListener{
	
	
	public EcranOpt(VueMenu vue)
	{
		NB_BUTTONS_X=1;
		NB_BUTTONS_Y=2;
		
		this.vue=vue;
		JButton b1=new JButton("test");
		JButton b2=new JButton("Retour");
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		
		buttons=new JButton[NB_BUTTONS_X][NB_BUTTONS_Y];
		buttons[0][0]=b1;
		buttons[0][1]=b2;
		
		this.addListener();
		
		this.add(b1);
		this.add(b2);
		
		
		
		
		//this.setBounds(300, 200, 800, 400);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String command = ((JButton) arg0.getSource()).getActionCommand();
		
		if(command=="Retour")
		{
			
			this.focusNouvelEcran(vue.lePanneau3);
			//vue.lePanneau3.requestFocus();
		}
	}
	
	
	
}
