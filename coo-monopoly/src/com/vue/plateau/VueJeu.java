package com.vue.plateau;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import com.controler.AbstractControler;
import com.model.ConstantesModel;
import com.model.ConstantesVue;
import com.model.plateau.cases.CaseModel;
import com.observer.Observer;
import com.vue.Fenetre;
import com.vue.menu.VueMenu;

public class VueJeu extends Fenetre implements Observer{

	protected EcranJeu lePanneau;
	protected AbstractControler controler;
	
	public VueJeu(AbstractControler controler){
		
		this.controler=controler;
		
		this.setTitle("Monopoly");
		this.setSize(ConstantesVue.DIMENSION_FENETRE_X, ConstantesVue.DIMENSION_FENETRE_Y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setFocusable(false);
		
		lePanneau=new EcranJeu(this);
		this.add(lePanneau);
		
		lePanneau.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				VueJeu.this.lePanneau.gestion(e);
			}
		});
	}
	
	/**
	 * Réinitialise un écran de jeu (Toutes les cases à zéro sauf une)
	 */
	public void initFenetreEcranJeu()
	{
		lePanneau.reinit();
		afficherPanneau(lePanneau);
		
	}

	
	
	@Override
	public void update(int x, int y, String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateWinner(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateArgentJoueur(int idJoueur, int argent) {
		// TODO Auto-generated method stub
		lePanneau.s.joueurs[idJoueur].argent=argent;
	}

	@Override
	public void updateAjoutMaison(int position) {
		// TODO Auto-generated method stub
		lePanneau.p.cases[position].addMaison();
	}

	@Override
	public void updateAcquisitionJoueur(int idJoueur, int position) {
		// TODO Auto-generated method stub
		lePanneau.s.joueurs[idJoueur].acquisition[position]=true;
	}

	@Override
	public void updatePosJoueur(int idJoueur, int position) {
		// TODO Auto-generated method stub
		lePanneau.p.pions[idJoueur].changePosition(position);
	}

	@Override
	public void updateCases(CaseModel[] cases) {
		// TODO Auto-generated method stub
		for(int i=0; i<ConstantesModel.NB_CASES; i++){
			lePanneau.p.cases[i].position=cases[i].getPosition();
		}
	}

	@Override
	public void updateTour(int tour) {
		// TODO Auto-generated method stub
		lePanneau.tour=tour;
		lePanneau.initTour(tour);
	}

}
