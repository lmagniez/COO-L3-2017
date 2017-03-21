package com.vue.titre;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.vue.Colors;
import com.vue.grid.VueGrid;
import com.controler.AbstractControler;
import com.controler.GridControler;
import com.model.AbstractModel;
import com.model.GrilleModel;
import com.vue.ButtonMenu;

public class EcranTitre extends JPanel{
	protected Vue1 f;
	
	protected ButtonMenu start;
	private ButtonMenu charger;	
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
		
		setCharger(new ButtonMenu("Charger",Colors.textColor2,Colors.case16));	
		getCharger().addActionListener(new ButtonListener());
		
		File fileXML = new File("save.xml");
		if(!fileXML.exists()){
			getCharger().setEnabled(false);
		}
		else{
			getCharger().setEnabled(true);
		}
		
		
		startLabel=new JLabel("OTHELLO");
		startLabel.setSize(new Dimension(150,50));
		startLabel.setFont(new Font("Arial",Font.BOLD,40));
		startLabel.setAlignmentX(this.CENTER_ALIGNMENT);
		
		this.add(Box.createRigidArea(new Dimension(5,50)));
		this.add(startLabel);
		this.add(Box.createRigidArea(new Dimension(5,30)));
		this.add(start);
		this.add(Box.createRigidArea(new Dimension(5,30)));
		this.add(getCharger());
		this.add(Box.createRigidArea(new Dimension(5,30)));
		this.add(quit);
	}

	public ButtonMenu getCharger() {
		return charger;
	}

	public void setCharger(ButtonMenu charger) {
		this.charger = charger;
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
				f.setPanneauParam(new EcranParam(f));
				f.setSize(new Dimension(500,500));
				f.afficherPanneau(f.getPanneauParam());
				
			}
			
			if(command=="Charger")
			{
					
					GrilleModel gridModel = new GrilleModel();
					AbstractControler gameControler = new GridControler(gridModel);
					VueGrid vueJeu = new VueGrid(gameControler, EcranTitre.this.f, gridModel.getNbX(), gridModel.getNbY());
					gridModel.addObserver(vueJeu);
					
					gridModel.initCharger();
					
					gridModel.startIA();
					
					EcranTitre.this.f.setVisible(false);
				
				
				
				
			}
			
			
			
			
			if(command=="Quitter")
				f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
				//f.dispose();
		} 
	}
	
}
