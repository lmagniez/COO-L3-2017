package com.vue.grid;
import java.awt.Color;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.controler.AbstractControler;
import com.model.CaseValue;
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
	private Grid grid;
	protected Score score;
	protected boolean swapColor;

	public static final int TAILLE_ECRAN_GRILLE=600;
	public static final int TAILLE_ECRAN_SCORE=300;
	

	// L'instance de notre objet controleur
	protected AbstractControler controler;

	/**
	 * Constructeur de la vue de la grille.
	 * @param controler Controler de la grille
	 * @param nbRow nombre de lignes
	 * @param nbCol nombre de colonnes
	 * @param swapColor 
	 * @param menu Vue correspondant au menu
	 */
	
	//ADD CONTROLEr
	public VueGrid(AbstractControler controler, int nbRow, int nbCol, boolean swapColor, Vue1 menu) {
		
		this.swapColor=swapColor;
		this.controler=controler;
		this.vueMenu=menu;
		this.setSize(TAILLE_ECRAN_GRILLE+TAILLE_ECRAN_SCORE, TAILLE_ECRAN_GRILLE);
		this.setTitle("Connect 4");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));
		
		this.controler = controler;
		this.grid=new Grid(this, nbRow, nbCol);
		this.score= new Score(this,2);
		
		this.add(grid);
		this.add(score);
		this.setVisible(true);
		
		getContentPane().setBackground(Color.LIGHT_GRAY);
		//grid.setBackground(Color.BLUE);
		//repaint();
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
	 * Recuperer la couleur correspondant au joueur
	 * @param v
	 * @return
	 */
	public Color getColorByCaseValue(CaseValue v)
	{
		if(v==CaseValue.J1)
		{
			if(!swapColor)
				return Color.YELLOW;
			else
				return Color.RED;
		}
		if(v==CaseValue.J2)
		{
			if(!swapColor)
				return Color.RED;
			else
				return Color.YELLOW;
		}
		if(v==CaseValue.WIN)
			return Color.GREEN;
		if(v==CaseValue.NONE)
			return Color.WHITE;

		return Color.PINK;
	}

	
	/**
	 * Mettre à jour l'affichage du tour dans le panel.
	 */
	public void updateTour(int tour){
		score.changeTour(tour);
	}

	public void updateReinit() {
		for(int i=0; i<grid.cases.length; i++){
			for(int j=0; j<grid.cases[0].length; j++){
				grid.cases[i][j].c=getColorByCaseValue(CaseValue.NONE);
			}
		}
		grid.repaint();
		this.grid.actif=true;
		
	}

	/**
	 * Mettre à jour la grille suite à l'ajout d'un jeton dans le modèle.
	 * @param x abscisse de la case
	 * @param y ordonnée de la case 
	 * @param v Valeur de la case 
	 */
	@Override
	public void updateChip(int x, int y, CaseValue v) {
		
		this.grid.cases[x][y].c=this.getColorByCaseValue(v);
		this.repaint();
		
	}

	/**
	 * Mettre à jour l'affichage et stopper la partie suite à la détection d'un
	 * pattern de victoire.
	 * @param x abscisse de la case
	 * @param y ordonnée de la case
	 * @param tour tour du joueur
	 */
	@Override
	public void updateWinner(int tour) {
		// TODO Auto-generated method stub
		System.out.println("WINNER");
		this.grid.actif=false;
		this.score.displayWinner(tour);
		
		
	}

}