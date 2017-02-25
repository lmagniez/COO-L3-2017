package com.vue.plateau;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import com.controler.AbstractControler;
import com.model.ConstantesModel;
import com.model.ConstantesVue;
import com.model.plateau.cases.CaseModel;
import com.model.plateau.cases.GareModel;
import com.model.plateau.cases.ServiceModel;
import com.model.plateau.cases.TerrainModel;
import com.observer.Observer;
import com.vue.Fenetre;
import com.vue.menu.VueMenu;
import com.vue.plateau.jeu.TypeCase;

public class VueJeu extends Fenetre implements Observer{

	protected EcranJeu lePanneau;
	private AbstractControler controler;
	
	public VueJeu(AbstractControler controler){
		
		this.setControler(controler);
		
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
		lePanneau.s.getJoueurs()[idJoueur].setArgent(argent);
	}

	@Override
	public void updateAjoutMaison(int position) {
		// TODO Auto-generated method stub
		lePanneau.getP().getCases()[position].addMaison();
	}

	@Override
	public void updateAcquisitionJoueur(int idJoueur, int position) {
		// TODO Auto-generated method stub
		lePanneau.s.getJoueurs()[idJoueur].getAcquisition()[position]=true;
	}

	@Override
	public void updatePosJoueur(int idJoueur, int position) {
		// TODO Auto-generated method stub
		lePanneau.getP().getPions()[idJoueur].changePosition(position);
	}

	@Override
	public void updateCases(CaseModel[] cases) {
		// TODO Auto-generated method stub
		for(int i=0; i<ConstantesModel.NB_CASES; i++){
			lePanneau.getP().getCases()[i].setPosition(cases[i].getPosition());
			if(cases[i] instanceof TerrainModel){
				lePanneau.getP().getCases()[i].setType(TypeCase.TERRAIN);
				lePanneau.getP().getCases()[i].setPrixAchat(((TerrainModel) cases[i]).getPrixAchat());
				lePanneau.getP().getCases()[i].setLoyers(((TerrainModel) cases[i]).getLoyers());
				lePanneau.getP().getCases()[i].setPrixMaison(((TerrainModel) cases[i]).getPrixMaison());
				lePanneau.getP().getCases()[i].setCouleurTerrain(((TerrainModel) cases[i]).getCouleurTerrain());
				lePanneau.getP().getCases()[i].setNom(((TerrainModel) cases[i]).getNom());
			}
			if(cases[i] instanceof GareModel){
				lePanneau.getP().getCases()[i].setType(TypeCase.GARE);
				lePanneau.getP().getCases()[i].setPrixAchat(((GareModel) cases[i]).getPrixAchat());
				lePanneau.getP().getCases()[i].setLoyers(GareModel.getLoyers());
				lePanneau.getP().getCases()[i].setPrixMaison(-1);
				lePanneau.getP().getCases()[i].setCouleurTerrain(null);
				lePanneau.getP().getCases()[i].setNom(((GareModel) cases[i]).getNom());
			}
			if(cases[i] instanceof ServiceModel){
				lePanneau.getP().getCases()[i].setType(TypeCase.SERVICE);
				lePanneau.getP().getCases()[i].setPrixAchat(((ServiceModel) cases[i]).getPrixAchat());
				lePanneau.getP().getCases()[i].setLoyers(ServiceModel.getLoyers());
				lePanneau.getP().getCases()[i].setPrixMaison(-1);
				lePanneau.getP().getCases()[i].setCouleurTerrain(null);
				lePanneau.getP().getCases()[i].setNom(((ServiceModel) cases[i]).getNom());
			}
			
		}
	}

	@Override
	public void updateTour(int tour) {
		// TODO Auto-generated method stub
		lePanneau.setTour(tour);
	}
	
	public void updateInitTour() {
		// TODO Auto-generated method stub
		lePanneau.initTour();
		lePanneau.choixA.setVisible(false);
		lePanneau.choixP.setVisible(false);
		
	}
	
	@Override
	public void updateAchatCase(int idJoueur, int position) {
		// TODO Auto-generated method stub
		lePanneau.choixA.genererChoixAchat(idJoueur, position);
	}
	

	public void updatePaiementCase(int idJoueur, int idJoueur2, int position){
		lePanneau.choixP.genererPaiement(idJoueur, idJoueur2, position); 
	}
	
	public AbstractControler getControler() {
		return controler;
	}

	public void setControler(AbstractControler controler) {
		this.controler = controler;
	}

	@Override
	public void updateMessageChoix(String msg) {
		// TODO Auto-generated method stub
		lePanneau.choixA.setMessage(msg);
	}

	

}
