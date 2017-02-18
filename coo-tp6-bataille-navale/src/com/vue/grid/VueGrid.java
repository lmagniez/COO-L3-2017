package com.vue.grid;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.controler.AbstractControler;
import com.model.Constantes;
import com.model.Orientation;
import com.model.TypeBateau;
import com.observer.Observer;
import com.reseau.EtatClient;
import com.vue.Fenetre;
import com.vue.titre.Vue1;

/**
 * Classe correspondant à la vue du jeu
 * @author loick
 *
 */

public class VueGrid extends Fenetre implements Observer {

	//elements de la vue
	protected Vue1 vueMenu;
	private Grid gridJoueur,gridAdversaire;
	protected Score score;
	protected Score score2;
	protected boolean swapColor;
	private int idJoueur;
	private EtatClient etat;
	protected Bateau[] bateaux;

	/*
	//serveur
	protected Recevoir_infos infos;
	protected Socket socket;
	protected BufferedReader in;
	protected PrintWriter out;
	*/
	
	//controler
	private AbstractControler controler;

	/**
	 * Constructeur de la vue de la grille.
	 * @param gridControler 
	 * @param controler Controler de la grille
	 * @param nbRow nombre de lignes
	 * @param nbCol nombre de colonnes
	 * @param vue1 
	 * @param swapColor 
	 * @param menu Vue correspondant au menu
	 */
	
	//ADD CONTROLEr
	//public VueGrid(AbstractControler gridControler, Socket s, int nbRow, int nbCol, Vue1 vue1) {
	public VueGrid(AbstractControler gridControler, int nbRow, int nbCol, Vue1 vue1) {	
		
		this.setSize(Constantes.TAILLE_ECRAN_GRILLE*2+Constantes.TAILLE_SEPARATION, 
				Constantes.TAILLE_ECRAN_GRILLE+Constantes.TAILLE_ECRAN_SCORE);
		this.setTitle("Bataille Navale");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));
		
		this.setControler(gridControler);
		this.vueMenu=vue1;
		this.gridJoueur=new Grid(this, nbRow, nbCol, true);
		this.gridAdversaire=new Grid(this, nbRow, nbCol, false);
		this.score=new Score(this,0);
		this.score2= new Score(this,1);
		
		JPanel panelJ1=new JPanel();
		panelJ1.setLayout(new BoxLayout(panelJ1,BoxLayout.PAGE_AXIS));
		panelJ1.add(getScore());
		panelJ1.add(gridJoueur);
		
		JPanel panelJ2=new JPanel();
		panelJ2.setLayout(new BoxLayout(panelJ2,BoxLayout.PAGE_AXIS));
		panelJ2.add(score2);
		panelJ2.add(gridAdversaire);
		
		this.add(panelJ1);
		this.add(Box.createRigidArea(new Dimension(Constantes.TAILLE_SEPARATION,10)));
		this.add(panelJ2);
		
		this.setVisible(true);
		
		getContentPane().setBackground(Color.LIGHT_GRAY);
		this.repaint();
	
		bateaux=new Bateau[Constantes.NB_BATEAUX];
		getControler().requestBateaux();
	}

	
	/**
	 * Mettre à jour l'affichage du tour dans le panel.
	 */
	public void updateTour(EtatClient etat){
		this.etat=etat;
		this.score.repaint();
		this.score2.repaint();
		
		//repaint();
	}
	
	/**
	 * Prévenir qu'une case à déjà été jouée
	 */
	public void updateFull(){
		this.getScore().setMsg("Déjà joué");
	}


	/**
	 * Prévenir l'apparition d'un gagnant
	 */
	@Override
	public void updateWinner() {
		// TODO Auto-generated method stub
		//System.out.println("WINNER");
		this.gridJoueur.actif=false;
		//this.getScore().displayWinner(1);
		
		
		PanelVictoire v=new PanelVictoire(0, this,true);
		this.gridJoueur.add(v);
		
		//PanelVictoire v2=new PanelVictoire(idJoueur, this,false);
		//this.gridAdversaire.add(v2);
		
		this.score.stop=true;
		this.score2.stop=true;
		
		
	}
	
	/**
	 * Prévenir l'apparition d'un perdant
	 */
	@Override
	public void updateLoser() {
		// TODO Auto-generated method stub
		//System.out.println("Loser..;");
		this.gridJoueur.actif=false;
		//this.getScore().displayWinner(0);
		
		
		PanelVictoire v=new PanelVictoire(1, this,true);
		this.gridJoueur.add(v);
		
		//PanelVictoire v2=new PanelVictoire(idJoueur, this,false);
		//this.gridAdversaire.add(v2);
		
		this.score.stop=true;
		this.score2.stop=true;
		
	}
	
	
	/**
	 * Prévenir la réinitialisation de la vue
	 */
	@Override
	public void updateReinit() {
		// TODO Auto-generated method stub
		for(int i=0; i<this.gridAdversaire.nbRow; i++)
		{
			for(int j=0; j<this.gridAdversaire.nbCol; j++)
			{	
				this.gridAdversaire.cases[i][j].c=CaseValueVue.NONE;
				this.gridJoueur.cases[i][j].c=CaseValueVue.NONE;
			}
		}
	}
	
	/**
	 * Mettre à jour une case du joueur 
	 * @param x abscisse
	 * @param y ordonnée
	 * @param v Valeur de la case
	 */
	@Override
	public void updateCaseJoueur(int x, int y, CaseValueVue v) {
		this.gridJoueur.cases[x][y].c=v;
		this.repaint();
	}

	/**
	 * Mettre à jour une case de l'adversaire 
	 * @param x abscisse
	 * @param y ordonnée
	 * @param v Valeur de la case
	 */
	@Override
	public void updateCaseAdversaire(int x, int y, CaseValueVue v) {
		this.gridAdversaire.cases[x][y].c=v;
		this.repaint();
	}

	/**
	 * Mettre à jour l'apparition d'un bateau 
	 * @param x abscisse
	 * @param y ordonnée
	 * @param type type du bateau
	 * @param o orientation du bateau
	 * @param idB id du bateau
	 */
	@Override
	public void updateBateau(int x, int y, TypeBateau type, Orientation o, int idB) {
		this.bateaux[idB]=new Bateau(x,y,type,o);
	}
	
	/**
	 * Mettre à jour l'affichage du message du score J1
	 */
	public void updateMsgScore(String msg){
		this.score.setMsg(msg);		
		this.score.repaint();
	}
	
	/**
	 * Mettre à jour l'affichage du message du score J2
	 */
	public void updateMsgScore2(String msg){
		this.score2.setMsg(msg);		
		this.score2.repaint();
	}


	public EtatClient getTour() {
		// TODO Auto-generated method stub
		return etat;
	}

	public AbstractControler getControler() {
		return controler;
	}

	public void setControler(AbstractControler controler) {
		this.controler = controler;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}


	/**
	 * Mettre à jour score J1
	 * @param coupsPris nombre de coups pris
	 * @param coupsRatés nombre de coups ratés
	 */
	@Override
	public void updateScoreJ1(int coupsPris, int coupsRates, int nbBateauxCoules) {
		// TODO Auto-generated method stub
		this.score.changeCoupsPris(coupsPris,0);
		this.score.changeCoupsRates(coupsRates,0);
		this.score.changeNbBateau(nbBateauxCoules);
	}

	/**
	 * Mettre à jour score J2
	 * @param coupsPris nombre de coups pris
	 * @param coupsRatés nombre de coups ratés
	 */
	@Override
	public void updateScoreJ2(int coupsPris, int coupsRates, int nbBateauxCoules) {
		// TODO Auto-generated method stub
		this.score2.changeCoupsPris(coupsPris,1);
		this.score2.changeCoupsRates(coupsRates,1);
		this.score2.changeNbBateau(nbBateauxCoules);
	}

	
	

}