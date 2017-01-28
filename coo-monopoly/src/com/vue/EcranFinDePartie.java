package com.vue;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * JPanel correspondant à une fin de partie.
 * On demande à l'utilisateur si il souhaite recommencer une partie.
 * @author loick
 *
 */
public class EcranFinDePartie extends JPanel implements ActionListener{

	private VueMenu vue;
	
	protected JButton b1;
	protected JButton b2;
	protected JPanel p;
	
	protected JLabel gameOverLabel;
	
	/**
	 * Initialisation du JPanel (Une fois)
	 * @param vue Fenetre utilisé pour les ActionListener
	 */
	public EcranFinDePartie(VueMenu vue)
	{
		this.vue=vue;
		
		this.setLayout(new FlowLayout());
		
		
		b1=new JButton("Ok");
		b1.addActionListener(this);
		b2=new JButton("Non");			
		b2.addActionListener(this);
		gameOverLabel=new JLabel("Game over");
				
		this.add(gameOverLabel);
		this.add(b1);
		this.add(b2);
		
	}

	/**
	 * Ok: Recommence la partie
	 * Non: Ferme la fenetre
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = ((JButton) e.getSource()).getActionCommand();
		
		if(command=="Ok")
		{	
			vue.afficherPanneau(vue.lePanneau3);
		}
		
		if(command=="Non")
		{
			vue.dispose();
		}
	}
	

}
