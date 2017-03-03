package com.vue.grid;
import java.awt.Color;
import java.awt.Component;
import java.sql.SQLException;

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

public class VueGridCreation extends Fenetre implements Observer {

	// private JPanel container = new JPanel();

	protected Vue1 vueMenu;
	private GridCreation grid;
	protected ScoreCreation score;
	protected boolean swapColor;
	
	public static final int TAILLE_ECRAN_GRILLE_X=500;
	public static final int TAILLE_ECRAN_GRILLE_Y=500-Grid.ESPACEMENT_SIZE-Grid.ESPACEMENT2_SIZE;
	
	public static final int TAILLE_ECRAN_SCORE=150;
	

	/**
	 * Constructeur
	 * @param nom nom de la grille
	 * @param nbRow nombre de lignes
	 * @param nbCol nombre de colonnes
	 * @param controler controler
	 * @param menu vue
	 */
	
	//ADD CONTROLEr
	public VueGridCreation(String nom, int nbRow, int nbCol, AbstractControler controler, Vue1 menu) {
		
		super(controler);
		
		this.swapColor=swapColor;
		this.setControler(controler);
		this.vueMenu=menu;
		this.setSize(TAILLE_ECRAN_GRILLE_X, TAILLE_ECRAN_GRILLE_Y+TAILLE_ECRAN_SCORE);
		this.setTitle("PICROSS");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		this.setControler(controler);
		//this.grid=new Grid(this, nbRow, nbCol);
		this.score= new ScoreCreation(this,2);
		
		this.setVisible(true);
		
		getContentPane().setBackground(Color.LIGHT_GRAY);
		//grid.setBackground(Color.BLUE);
		//repaint();
		
		
		this.grid=new GridCreation(this, nbRow, nbCol, nom);
		
		this.add(score);
		this.add(grid);
		
		
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
	 * Réinitialise la grille
	 */
	public void updateReinit() {
		for(int i=0; i<getGrid().cases.length; i++){
			for(int j=0; j<getGrid().cases[0].length; j++){
				getGrid().cases[i][j].color=CaseValue.getColorFromValue(CaseValue.UNCHECKED);
			}
		}
		getGrid().repaint();
		this.getGrid().actif=true;
	}

	/**
	 * Mettre à jour la grille
	 * @param x abscisse de la grille
	 * @param y ordonnée de la grille
	 */
	@Override
	public void updateChangeValue(int x, int y) {
		// TODO Auto-generated method stub
		this.getGrid().cases[x][y].changeValue();
	}

	/**
	 * Mettre à jour la victoire
	 */
	@Override
	public void updateWin() {
		// TODO Auto-generated method stub
	}

	/**
	 * Mettre à jour une défaite
	 */
	@Override
	public void updateLose() {
		// TODO Auto-generated method stub
	}


	

	public GridCreation getGrid() {
		return grid;
	}

	public void setGrid(GridCreation grid) {
		this.grid = grid;
	}
	
	/**
	 * Réinitialiser une fenetre 
	 */
	@Override
	public void updateReinitWindow() {
		// TODO Auto-generated method stub
		this.getControler().removeObserverModel();
		try {
			vueMenu= new Vue1(vueMenu.getGridControler());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.getControler().addObserverModel(vueMenu);
		this.getControler().requestGenererGrilles();
		vueMenu.requestGrilles();
		
		//this.vueMenu.setVisible(true);
		
		
		this.setVisible(false);
	}
	
	@Override
	public void updateInfosGrilles(int nbGrille, int[] id, String[] nom, boolean[] reussite, int[] nbLignes, int[] nbColonnes) {
		//pour VueMenu
	}

	@Override
	public void updateGrilleDetail(int id, String nom, String[] indicesLigne, String[] indicesColonne,
			boolean reussite) {
		
	}

}