package com.vue.plateau;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.controler.AbstractControler;
import com.model.ConstantesModel;
import com.model.ConstantesParam;
import com.model.ConstantesVue;
import com.model.plateau.cases.CaseModel;
import com.model.plateau.cases.GareModel;
import com.model.plateau.cases.ServiceModel;
import com.model.plateau.cases.TerrainModel;
import com.model.plateau.pioche.TypePioche;
import com.observer.Observer;
import com.vue.Fenetre;
import com.vue.menu.VueMenu;
import com.vue.plateau.jeu.TypeCase;
import com.vue.plateau.joueur.InfoJoueur;
import com.vue.plateau.joueur.ProprietesJoueur;

/**
 * Vue du jeu
 * @author loick
 *
 */
public class VueJeu extends Fenetre implements Observer{

	protected EcranJeu lePanneau;
	private AbstractControler controler;
	private VueMenu menu;
	
	/**
	 * Constructeur
	 * @param controler controler du jeu
	 */
	public VueJeu(AbstractControler controler, VueMenu menu){
		
		this.setControler(controler);
		
		this.setTitle("Monopoly");
		this.setSize(ConstantesVue.DIMENSION_FENETRE_X, ConstantesVue.DIMENSION_FENETRE_Y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setFocusable(false);
		this.setMenu(menu);
		
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
	public void updateArgentJoueur(int idJoueur, int argent) {
		lePanneau.s.getJoueurs()[idJoueur].setArgent(argent);
		if(lePanneau.s.getProprietes()!=null)
			lePanneau.s.getProprietes().setArgent(argent);
		
	}

	@Override
	public void updateAjoutMaison(int position) {
		lePanneau.getP().getCases()[position].addMaison();
	}

	@Override
	public void updateRetirerMaison(int position) {
		lePanneau.getP().getCases()[position].removeMaison();
	}
	
	@Override
	public void updateAcquisitionJoueur(int idJoueur, int position) {
		System.out.println(idJoueur + "  "+position);
		lePanneau.s.getJoueurs()[idJoueur].getAcquisition()[position]=true;
	}

	@Override
	public void updatePosJoueur(int idJoueur, int position) {
		lePanneau.getP().getPions()[idJoueur].changePosition(position);
	}

	@Override
	public void updateCases(CaseModel[] cases) {
		for(int i=0; i<ConstantesModel.NB_CASES; i++){
			
			System.out.println(lePanneau.getP());
			System.out.println(lePanneau.getP().getCases()[i]);
			System.out.println(cases[i]);
			//System.out.println(cases[i].getPosition());
			
			System.out.println(cases[i].getIdCase());
			
			
			lePanneau.getP().getCases()[i].setPosition(cases[i].getPosition());
			lePanneau.getP().getCases()[i].setIdCase((cases[i].getIdCase()));
			
			//System.out.println();
			
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
		lePanneau.setTour(tour);
	}
	
	@Override
	public void updateInitTourDes() {
		lePanneau.initTour();
		
	}
	
	@Override
	public void updateInitTourFenetre() {
		lePanneau.choixA.setVisible(false);
		lePanneau.choixP.setVisible(false);
		lePanneau.choixA.setActif(false);
		lePanneau.choixP.setActif(false);
		
	}
	
	@Override
	public void updateAchatCase(int idJoueur, int position) {
		lePanneau.choixA.genererChoixAchat(idJoueur, position);
	}
	
	@Override
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
		lePanneau.choixA.setMessage(msg);
	}

	@Override
	public void updateEchangeJoueur(int idJoueur1, int idJoueur2, int position) {
		
		
		lePanneau.s.getJoueurs()[idJoueur1].getAcquisition()[position]=false;
		lePanneau.s.getJoueurs()[idJoueur2].getAcquisition()[position]=true;
		lePanneau.s.revalidate();
		lePanneau.s.repaint();
		
		
		lePanneau.choixE.setVisible(false);
		if(lePanneau.s.getProprietes().getCarte()!=null)
			lePanneau.s.getProprietes().remove(lePanneau.s.getProprietes().getCarte());
		lePanneau.s.getProprietes().remove(lePanneau.s.getProprietes().getEchange());
		lePanneau.s.getProprietes().remove(lePanneau.s.getProprietes().getMaison());
		lePanneau.s.getProprietes().remove(lePanneau.s.getProprietes().getMaisonVente());
		
		
	}

	@Override
	public void updateMessageCarte(String msg, int idJoueur, int posCarte, TypePioche type) {
		lePanneau.choixC.genererPiocheCarte(idJoueur, posCarte, msg, type);
	}

	@Override
	public void updateDesacquisitionJoueur(int idJoueur, int position) {
		lePanneau.s.getJoueurs()[idJoueur].getAcquisition()[position]=false;
	}

	@Override
	public void updateDette(int idJoueur) {
		this.lePanneau.s.setProprietes(new ProprietesJoueur(idJoueur,lePanneau.s.getJoueurs()[idJoueur],
				lePanneau.p.getCases()));
		lePanneau.s.afficherProprietes();
	}

	

	@Override
	public void updateWinner(int idJoueur) {
		System.out.println("WINNER!!");
	}
	
	@Override
	public void updateGameOver(int idJoueur) {		
		lePanneau.s.getJoueurs()[idJoueur].setGameOver(true);
		lePanneau.s.afficherInfosJoueur();
		lePanneau.getChoixE().setVisible(false);
	}

	@Override
	public void updateEnchere(int position) {
		lePanneau.choixEn.genererEnchere(position);
	}

	@Override
	public void updateCartePrison(int idJoueur, boolean hasCarte) {
		// TODO Auto-generated method stub
		System.out.println("ajoute carte joueur");
		this.lePanneau.getS().getJoueurs()[idJoueur].updateCartePrison(hasCarte);
		//ajoute la carte au joueur
		
	}

	@Override
	public void updatePrison(int idJoueur, int nbTour) {
		System.out.println("ici");
		this.lePanneau.getChoixM().genererMessageAllerPrison(idJoueur, nbTour);
		this.lePanneau.getS().getJoueurs()[idJoueur].updatePrison(nbTour);;
	}

	@Override
	public void updateEnPrison(int idJoueur, int nbTour) {
		// TODO Auto-generated method stub
		this.lePanneau.getChoixM().genererMessageEnPrison(idJoueur, nbTour);
		this.lePanneau.getS().getJoueurs()[idJoueur].updatePrison(nbTour);
	}

	@Override
	public void updateLiberePrison(int idJoueur) {
		this.lePanneau.getChoixM().genererMessageLiberePrison(idJoueur);
		this.lePanneau.getS().getJoueurs()[idJoueur].updatePrison(0);
	}

	@Override
	public void updateNbMaison(int nbMaison) {
		this.lePanneau.s.getInfos().updateMaison(ConstantesParam.NB_MAISONS-nbMaison);
	}

	@Override
	public void updateTourPartie(int tourPartie) {
		this.lePanneau.s.getInfos().updateTour(tourPartie);
	}

	@Override
	public void updatePatrimoineJoueur(int idJoueur, int argent) {
		this.lePanneau.s.getJoueurs()[idJoueur].setPatrimoine(argent);
	}

	@Override
	public void updateMessageGameOver(int idJoueur) {
		this.lePanneau.getChoixG().genererGameOver(idJoueur);
	}

	public VueMenu getMenu() {
		return menu;
	}

	public void setMenu(VueMenu menu) {
		this.menu = menu;
	}

	

	
	
	
	

}
