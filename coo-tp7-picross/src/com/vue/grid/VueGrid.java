package com.vue.grid;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.controler.AbstractControler;
import com.model.CaseValue;
import com.model.patternWin;
import com.observer.Observer;
import com.vue.Fenetre;
import com.vue.titre.EcranParam;
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
	
	public static final int TAILLE_ECRAN_GRILLE_X=500;
	public static final int TAILLE_ECRAN_GRILLE_Y=500-Grid.ESPACEMENT_SIZE-Grid.ESPACEMENT2_SIZE;
	
	public static final int TAILLE_ECRAN_SCORE=150;
	

	

	
	
	/**
	 * COnstructeur
	 * @param controler controler
	 * @param menu vue
	 */
	
	//ADD CONTROLEr
	public VueGrid(AbstractControler controler, Vue1 menu) {
		
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
		this.score= new Score(this,2);
		
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
	 * Mettre à jour la valeur d'une case dans la grille
	 * @param x abscisse de la case
	 * @param y ordonée de la case
	 */
	@Override
	public void updateChangeValue(int x, int y) {
		// TODO Auto-generated method stub
		this.getGrid().cases[x][y].changeValue();
	}

	/**
	 * Mettre à jour une victoire
	 */
	@Override
	public void updateWin() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this,"Gogné!"); 
		updateReinitWindow();
	}

	
	/**
	 * Mettre à jour une défaite
	 */
	@Override
	public void updateLose() {
		JOptionPane.showMessageDialog(this,"Perdu, Réessayer!"); 
	}


	/**
	 * Mettre à jour une grille en détail
	 * @param id id de la grille
	 * @param nom nom de la grille
	 * @param indicesLigne liste des indices de ligne
	 * @param indicesColonne liste des indices de colonnes
	 */
	@Override
	public void updateGrilleDetail(int id, String nom, String[] indicesLigne, String[] indicesColonne,
			boolean reussite) {
		// TODO Auto-generated method stub
		
		this.setGrid(new Grid(this, id, indicesLigne.length, indicesColonne.length, nom, 
				indicesLigne, indicesColonne, reussite));
		this.add(score);
		this.add(getGrid());
		
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}


	/**
	 * Réinitialiser la fenêtre 
	 */
	@Override
	public void updateReinitWindow() {
		// TODO Auto-generated method stub
		//this
		
		
		this.getControler().removeObserverModel();
		
		this.getControler().addObserverModel(vueMenu);
		//this.controler.requestGenererGrilles();
		vueMenu.requestGrilles();
		this.vueMenu.setVisible(true);
		//this.vueMenu.afficherPanneau(this.vueMenu.getPanneauTitre());
		
		this.vueMenu.setPanneauParam(new EcranParam(vueMenu));
		vueMenu.setSize(new Dimension(500,500));
		vueMenu.afficherPanneau(vueMenu.getPanneauParam());
		
		this.setVisible(false);
		
	}

	@Override
	public void updateInfosGrilles(int nbGrille, int[] id, String[] nom, boolean[] reussite, int[] nbLignes, int[] nbColonnes) {
		//pour VueMenu
	}
	
}