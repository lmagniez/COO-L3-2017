package com.vue.plateau.joueur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.model.ConstantesModel;
import com.model.ConstantesVue;
import com.model.plateau.cases.CouleurTerrain;
import com.vue.ButtonMenu;
import com.vue.Colors;
import com.vue.plateau.CarteAchat;
import com.vue.plateau.jeu.Case;

public class ProprietesJoueur extends JPanel{

	protected InfoJoueur infos;
	protected int idJoueur;
	
	protected JButton retour;
	
	
	
	protected JPanel[] couleurs;
	protected CarteAchat carte;
	protected Case[] cases;
	
	public ProprietesJoueur(int idJoueur, InfoJoueur infos, Case[] cases){
		
		this.infos=infos;
		this.idJoueur=idJoueur;
		this.cases=cases;
		
		this.setMaximumSize(new Dimension(ConstantesVue.DIMENSION_SCORE_X,ConstantesVue.DIMENSION_SCORE_X*5/3));
		this.setSize(new Dimension(ConstantesVue.DIMENSION_SCORE_X,ConstantesVue.DIMENSION_SCORE_X*5/3));
		this.setMinimumSize(new Dimension(ConstantesVue.DIMENSION_SCORE_X,ConstantesVue.DIMENSION_SCORE_X*5/3));
		this.setPreferredSize(new Dimension(ConstantesVue.DIMENSION_SCORE_X,ConstantesVue.DIMENSION_SCORE_X*5/3));
		
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(new Color(200,200,200,90));
		
		
		
		this.couleurs=new JPanel[ConstantesModel.NB_COULEUR];
		for(int i=0; i<ConstantesModel.NB_COULEUR;i++){
			couleurs[i]=new JPanel();
			couleurs[i].setLayout(new BoxLayout(couleurs[i], BoxLayout.LINE_AXIS));
			
		}
		
		JLabel l[] = new JLabel[ConstantesModel.NB_COULEUR];
		for(int i=0; i<ConstantesModel.NB_COULEUR; i++){
			l[i]=new JLabel("  ");
			l[i].setSize(new Dimension(50,50));
			l[i].setVisible(true);
			l[i].setOpaque(true);

		}
		
		l[0].setBackground(CouleurTerrain.getColorFromEnum(CouleurTerrain.MARRON));
		couleurs[0].add(l[0]);
		
		l[1].setBackground(CouleurTerrain.getColorFromEnum(CouleurTerrain.BLEU_CLAIR));
		l[2].setBackground(CouleurTerrain.getColorFromEnum(CouleurTerrain.VIOLET));
		l[3].setBackground(CouleurTerrain.getColorFromEnum(CouleurTerrain.ORANGE));
		l[4].setBackground(CouleurTerrain.getColorFromEnum(CouleurTerrain.ROUGE));
		l[5].setBackground(CouleurTerrain.getColorFromEnum(CouleurTerrain.JAUNE));
		l[6].setBackground(CouleurTerrain.getColorFromEnum(CouleurTerrain.VERT));
		l[7].setBackground(CouleurTerrain.getColorFromEnum(CouleurTerrain.BLEU_FONCE ));
		
		for(int i=0; i<ConstantesModel.NB_COULEUR; i++){
			couleurs[i].add(l[i]);
		}
		
		for(int i=0; i<cases.length; i++){
			JButton b=new JButton(cases[i].getNom());
			b.addActionListener(new ButtonListener());
			if(cases[i].getCouleurTerrain()==CouleurTerrain.MARRON)
				couleurs[0].add(b);
			else if(cases[i].getCouleurTerrain()==CouleurTerrain.BLEU_CLAIR)
				couleurs[1].add(b);
			else if(cases[i].getCouleurTerrain()==CouleurTerrain.VIOLET)
				couleurs[2].add(b);
			else if(cases[i].getCouleurTerrain()==CouleurTerrain.ORANGE)
				couleurs[3].add(b);
			else if(cases[i].getCouleurTerrain()==CouleurTerrain.ROUGE)
				couleurs[4].add(b);
			else if(cases[i].getCouleurTerrain()==CouleurTerrain.JAUNE)
				couleurs[5].add(b);
			else if(cases[i].getCouleurTerrain()==CouleurTerrain.VERT)
				couleurs[6].add(b);
			else if(cases[i].getCouleurTerrain()==CouleurTerrain.BLEU_FONCE)
				couleurs[7].add(b);
		}
		
		
		
		retour=new ButtonMenu("Retour",Colors.textColor2,Colors.case8);
		retour.addActionListener(new ButtonListener());
		
		
		for(int i=0; i<ConstantesModel.NB_COULEUR; i++){
			this.add(couleurs[i]);
		}
		
		this.add(retour);
		
		
		
		
		
		
	}
	
	class ButtonListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e) {
			String command = ((JButton) e.getSource()).getActionCommand();
			
			if(command=="Retour"){
				ProprietesJoueur.this.infos.score.afficherInfosJoueur();
			}
			else{
				
				for(int i=0; i<ConstantesModel.NB_CASES; i++){
					System.out.println(command);
					System.out.println(cases[i].getNom());
					if(command.equals(cases[i].getNom())){
						if(ProprietesJoueur.this.carte!=null)
							ProprietesJoueur.this.remove(ProprietesJoueur.this.carte);
						ProprietesJoueur.this.carte=new CarteAchat(i,cases[i].getNom(),
								cases[i].getCouleurTerrain(),cases[i].getPrixAchat(),cases[i].getLoyers(),
								cases[i].getPrixMaison());
						
						ProprietesJoueur.this.add(ProprietesJoueur.this.carte);
						
						ProprietesJoueur.this.revalidate();
						ProprietesJoueur.this.repaint();
						
						break;
					}
				}
				
				
			}
		
		} 
	}
}
