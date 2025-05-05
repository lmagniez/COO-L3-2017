package com.vue.plateau.joueur;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.model.ConstantesModel;
import com.model.ConstantesParam;
import com.model.ConstantesVue;
import com.vue.plateau.EcranJeu;

/**
 * Classe représentant les différentes infos de jeu
 * Gère les tours des joueurs et les propriétés
 * @author loick
 *
 */
public class Score extends JPanel{

	protected EcranJeu ecran;
	protected Image fond;
	protected InfoJoueur[] joueurs;
	protected InfoJeu infos;
	protected ProprietesJoueur proprietes;
	


	/**
	 * Constructeur
	 * @param e Ecran de jeu
	 */
	public Score(EcranJeu e){
		
		
		this.ecran=e;
		this.setMaximumSize(new Dimension(ConstantesVue.DIMENSION_SCORE_X,ConstantesVue.DIMENSION_SCORE_Y));
		this.setSize(new Dimension(ConstantesVue.DIMENSION_SCORE_X,ConstantesVue.DIMENSION_SCORE_Y));
		this.setMinimumSize(new Dimension(ConstantesVue.DIMENSION_SCORE_X,ConstantesVue.DIMENSION_SCORE_Y));
		this.setPreferredSize(new Dimension(ConstantesVue.DIMENSION_SCORE_X,ConstantesVue.DIMENSION_SCORE_Y));
		
		fond=new ImageIcon(getClass().getResource("/Sprites/aide.png")).getImage();
	
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		setJoueurs(new InfoJoueur[ConstantesParam.NB_JOUEURS]);
		for(int i=0; i<ConstantesParam.NB_JOUEURS; i++)
			getJoueurs()[i]=new InfoJoueur(this, i,ConstantesParam.SOMME_DEPART,ConstantesParam.ID_ICONES[i]);
			
		infos=new InfoJeu(this);
		
		
		afficherInfosJoueur();
		

	}
	
	/**
	 * Afficher les propriétés d'un joueur
	 */
	public void afficherProprietes(){
		this.removeAll();
		this.add(Box.createRigidArea(new Dimension(5,25)));
		this.add(getProprietes());
		getProprietes().refresh();
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * Afficher les infos d'un joueur
	 */
	public void afficherInfosJoueur(){
		this.removeAll();
		this.add(Box.createRigidArea(new Dimension(5,25)));
		for(int i=0; i<ConstantesParam.NB_JOUEURS; i++){
			this.add(getJoueurs()[i]);
		}
		this.add(infos);
		this.revalidate();
		this.repaint();
	}
	
	
	public void paintComponent(Graphics g) {
        g.drawImage(fond, 0, 0, getWidth(), getHeight(), this);
    }

	/**
	 * Initialiser le tour
	 * @param tour
	 */
	public void initTour(int tour) {
		for(int i=0; i<ConstantesParam.NB_JOUEURS; i++)
			getJoueurs()[i].lanceDes.setEnabled(false);
		getJoueurs()[tour].lanceDes.setEnabled(true);
		this.revalidate();
		this.repaint();
		
	}
	
	
	

	public InfoJoueur[] getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(InfoJoueur[] joueurs) {
		this.joueurs = joueurs;
	}

	public ProprietesJoueur getProprietes() {
		return proprietes;
	}

	public void setProprietes(ProprietesJoueur proprietes) {
		this.proprietes = proprietes;
	}
	

	
	
	public InfoJeu getInfos() {
		return infos;
	}

	public void setInfos(InfoJeu infos) {
		this.infos = infos;
	}
	
}
