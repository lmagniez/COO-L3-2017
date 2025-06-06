
package com.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.controler.AbstractControler;
import com.model.AbstractModel;
import com.observer.Observer;

public class VuePrincipale extends JFrame implements Observer{
	
	private JPanel container = new JPanel();
	private Case[][] cases;
	// l'ensemble des objets de vue
	//private 
	
	//L'instance de notre objet controleur
	private AbstractControler controler;
	
	
	public VuePrincipale(AbstractControler controler){
		this.setSize(500, 500);
		this.setTitle("Calculette");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		//initComposant();

		
		
		// initialisation des composants
		//...
		this.controler = controler;
		//...
		
		container=new JPanel();
		container.setBackground(Color.GRAY);
		container.setPreferredSize(new Dimension(400,400));
		container.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		this.setMaximumSize(new Dimension (400,400));
		
		this.cases=new Case[AbstractModel.NB_LIGNE][AbstractModel.NB_LIGNE];
		for(int i=0; i<AbstractModel.NB_LIGNE; i++)
		{	
			for(int j=0; j<AbstractModel.NB_LIGNE; j++)
			{
				c.gridx=j;
				c.gridy=i;
				c.insets=new Insets(3,3,3,3);;
				
				cases[i][j]=new Case(i,j);
				cases[i][j].addActionListener(new CaseListener());
				container.add(cases[i][j],c);
			}
		}

		//this.setContentPane(container);
	    this.setVisible(true);
		this.add(container);
		
	}
	
	//Les listeners de chaque bouton ou composant
	class CaseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Case c = ((Case) e.getSource());
			controler.setCase(c.x, c.y);

		}
	}
	
	
	class ResetListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			controler.reset();
		}               
	}
	
	@Override
	public void update(int x, int y, String s) {
		// TODO Auto-generated method stub
		cases[x][y].setText(s);
	}

	@Override
	public void updateWinner(String s) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, "Le gagnant est"+s);
		//System.out.println("Le gagnant est"+s);
		controler.reset();
	}

}