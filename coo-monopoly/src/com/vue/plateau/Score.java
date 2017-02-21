package com.vue.plateau;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.model.ConstantesParam;
import com.model.ConstantesVue;

public class Score extends JPanel{

	protected EcranJeu ecran;
	protected Image fond;
	protected InfoJoueur[] joueurs;
	protected InfoJeu infos;
	
	public Score(EcranJeu e){
		this.ecran=e;
		this.setMaximumSize(new Dimension(ConstantesVue.DIMENSION_SCORE_X,ConstantesVue.DIMENSION_SCORE_Y));
		this.setSize(new Dimension(ConstantesVue.DIMENSION_SCORE_X,ConstantesVue.DIMENSION_SCORE_Y));
		this.setMinimumSize(new Dimension(ConstantesVue.DIMENSION_SCORE_X,ConstantesVue.DIMENSION_SCORE_Y));
		this.setPreferredSize(new Dimension(ConstantesVue.DIMENSION_SCORE_X,ConstantesVue.DIMENSION_SCORE_Y));
		
		fond=new ImageIcon("./Sprites/aide.png").getImage();
	
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.add(Box.createRigidArea(new Dimension(5,25)));
		joueurs=new InfoJoueur[ConstantesParam.NB_JOUEURS];
		for(int i=0; i<ConstantesParam.NB_JOUEURS; i++){
			joueurs[i]=new InfoJoueur(this, i,5000,i);
			this.add(joueurs[i]);
			
		}
		infos=new InfoJeu();
		this.add(infos);
		
		
		

	}
	
	
	public void paintComponent(Graphics g) {
        g.drawImage(fond, 0, 0, getWidth(), getHeight(), this);
    }
	
}
