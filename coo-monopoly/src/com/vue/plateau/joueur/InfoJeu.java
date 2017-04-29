package com.vue.plateau.joueur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.model.ConstantesParam;
import com.model.ConstantesVue;

/**
 * Classe contenant les différentes infos de jeu
 * @author loick
 *
 */
public class InfoJeu extends JPanel {

	protected JTextArea textBox;
	protected JButton saveGame;
	protected JButton quitGame;
	protected JButton savequitGame;
	
	protected Score s;
	
	protected String nbMaisons;
	protected String tourRestants;

	
	public InfoJeu(Score score){
		
		this.s=score;
		
		this.setMaximumSize(new Dimension(ConstantesVue.DIMENSION_INFO_X,ConstantesVue.DIMENSION_INFO_Y*2));
		this.setPreferredSize(new Dimension(ConstantesVue.DIMENSION_INFO_X,ConstantesVue.DIMENSION_INFO_Y));
		this.setSize(new Dimension(ConstantesVue.DIMENSION_INFO_X,ConstantesVue.DIMENSION_INFO_Y));
		this.setMinimumSize(new Dimension(ConstantesVue.DIMENSION_INFO_X,ConstantesVue.DIMENSION_INFO_Y));
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JLabel l=new JLabel("Informations: ");
		l.setBackground(Color.BLACK);
		l.setForeground(Color.WHITE);
		l.setAlignmentX(CENTER_ALIGNMENT);
		this.add(l);
		textBox=initTextArea("");
		textBox.setEditable(false);
		this.add(textBox);
		
		JPanel p= new JPanel();
		p.setLayout(new BoxLayout(p,BoxLayout.LINE_AXIS));
		saveGame=new JButton("Sauvegarder");
		saveGame.addActionListener(new ButtonListener());
		p.add(saveGame);
		
		quitGame=new JButton("Quitter");
		quitGame.addActionListener(new ButtonListener());
		p.add(quitGame);
		
		savequitGame=new JButton("Sauvegarder et Quitter");
		savequitGame.addActionListener(new ButtonListener());
		p.add(savequitGame);
		
		this.add(p);
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(new Color(0,0,0));
		
		nbMaisons="";
		tourRestants="";
		
		updateMaison(ConstantesParam.NB_MAISONS);
		if(ConstantesParam.TOUR_ENABLED){
			updateTour(1);
		}
		
		
	}
	
	public void updateTextBox(){
		this.textBox.setText(nbMaisons+"\n"+tourRestants);
	}
	
	public void updateMaison(int nbM){
		this.nbMaisons="Nombre de maisons disponibles: "+nbM+"/"+ConstantesParam.NB_MAISONS;
		updateTextBox();
	}
	
	public void updateTour(int tour){
		this.tourRestants="Tour actuel: "+tour+"/"+ConstantesParam.NB_TOUR;
		updateTextBox();
	}
	
	
	/**
	 * Initialisation de la zone de texte.
	 * @param s Le contenu de la JTextArea
	 * @return La JTextArea initialisé
	 */
	
	public JTextArea initTextArea(String s){
		
		JTextArea textArea = new JTextArea();
        textArea.setRows(15);
        textArea.setColumns(20);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setBackground(Color.lightGray);
		textArea.append(s);
		return textArea;

	}
	
	class ButtonListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e) {
			String command = ((JButton) e.getSource()).getActionCommand();
			if(command=="Sauvegarder"){
				InfoJeu.this.s.ecran.getVue().getControler().requestSave();
			}
			if(command=="Quitter"){
				InfoJeu.this.s.ecran.getVue().setVisible(false);
				InfoJeu.this.s.ecran.getVue().getMenu().setVisible(true);
				
			}
			
			if(command=="Sauvegarder et Quitter"){
				InfoJeu.this.s.ecran.getVue().getControler().requestSave();
				InfoJeu.this.s.ecran.getVue().setVisible(false);
				InfoJeu.this.s.ecran.getVue().getMenu().setVisible(true);
				
			}
		}
	}
	
}
