package com.vue.grid;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.controler.AbstractControler;
import com.controler.GridControler;
import com.model.AbstractModel;
import com.model.CaseValue;
import com.model.Constantes;
import com.model.GridModel;
import com.model.Orientation;
import com.model.TypeBateau;
import com.model.patternWin;
import com.observer.Observer;
import com.vue.Fenetre;
import com.vue.titre.Vue1;

/**
 * Classe correspondant à la vue du jeu
 * @author loick
 *
 */

public class VueGrid extends Fenetre implements Observer {

	// private JPanel container = new JPanel();

	protected Vue1 vueMenu;
	private Grid gridJoueur,gridAdversaire;
	protected Score score,score2;
	protected boolean swapColor;
	private int idJoueur;
	private int tour;


	protected Bateau[] bateaux;
	
	// L'instance de notre objet controleur
	protected AbstractControler controler;

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
	public VueGrid(AbstractControler gridControler, int nbRow, int nbCol, Vue1 vue1) {
		
		this.controler=gridControler;
		this.vueMenu=vue1;
		
		this.setSize(Constantes.TAILLE_ECRAN_GRILLE*2+Constantes.TAILLE_SEPARATION, 
				Constantes.TAILLE_ECRAN_GRILLE+Constantes.TAILLE_ECRAN_SCORE);
		this.setTitle("Bataille Navale");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));
		
		this.gridJoueur=new Grid(this, nbRow, nbCol, true);
		this.gridAdversaire=new Grid(this, nbRow, nbCol, false);
		
		bateaux=new Bateau[Constantes.NB_BATEAUX];
		this.bateaux[0]=new Bateau(3,3, TypeBateau.BATEAU_4, Orientation.VERTICAL);
		this.bateaux[1]=new Bateau(2,5, TypeBateau.BATEAU_5, Orientation.VERTICAL);
		this.bateaux[2]=new Bateau(7,7, TypeBateau.BATEAU_3, Orientation.HORIZONTAL);
		this.bateaux[3]=new Bateau(0,0, TypeBateau.BATEAU_2, Orientation.HORIZONTAL);
		this.bateaux[4]=new Bateau(0,2, TypeBateau.BATEAU_2, Orientation.HORIZONTAL);
		
		
		
		this.score= new Score(this,0);
		this.score2= new Score(this,1);
		score.addCoupsPris();
		
		
		
		
		
		
		JPanel panelJ1=new JPanel();
		panelJ1.setLayout(new BoxLayout(panelJ1,BoxLayout.PAGE_AXIS));
		panelJ1.add(score);
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
	
		
		}

	private Component topJustify( JPanel panel )  {
	    Box  b = Box.createVerticalBox();
	    b.add( panel );
	    b.add( Box.createVerticalGlue() );
	    // (Note that you could throw a lot more components
	    // and struts and glue in here.)
	    return b;
	}
	
	

	
	/**
	 * Mettre à jour l'affichage du tour dans le panel.
	 */
	public void updateTour(){
		this.tour=(this.tour+1)%2;
		repaint();
	}

	public void updateFull(){
		this.score.textBox.setText("Déjà joué");
	}


	/**
	 * Mettre à jour l'affichage et stopper la partie suite à la détection d'un
	 * pattern de victoire.
	 * @param x abscisse de la case
	 * @param y ordonnée de la case
	 * @param tour tour du joueur
	 */
	@Override
	public void updateWinner() {
		// TODO Auto-generated method stub
		System.out.println("WINNER");
		this.gridJoueur.actif=false;
		this.score.displayWinner(1);
		
	}
	
	@Override
	public void updateLoser() {
		// TODO Auto-generated method stub
		System.out.println("Loser..;");
		this.gridJoueur.actif=false;
		this.score.displayWinner(0);
		
	}
	
	

	@Override
	public void updateReinit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCaseJoueur(int x, int y, CaseValueVue v) {
		this.gridJoueur.cases[x][y].c=v;
		this.repaint();
	}

	@Override
	public void updateCaseAdversaire(int x, int y, CaseValueVue v) {
		this.gridAdversaire.cases[x][y].c=v;
		this.repaint();
	}

	public static void main(String[] args) {
		
		//VueGrid vueJeu = new VueGrid(null, 10, 10, null);
		
		
		//Creation du modele de grille
		AbstractModel gridModel = new GridModel(10, 10);
		//Creation du controleur
		AbstractControler gridControler = new GridControler(gridModel);
		//Creation de notre fenetre avec le controleur en parametre
		VueGrid vueJeu = new VueGrid(gridControler, 10, 10,null);
		//Ajout de la fenetre comme observer de notre modele
		gridModel.addObserver(vueJeu);
		
		vueJeu.setVisible(true);
		
	}

	public int getTour() {
		// TODO Auto-generated method stub
		return tour;
	}
	

}