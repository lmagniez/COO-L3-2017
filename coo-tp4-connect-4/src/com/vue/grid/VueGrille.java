package com.vue.grid;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.controler.AbstractControler;
import com.controler.GridControler;
import com.controler.MenuControler;
import com.model.AbstractModel;
import com.model.CaseValue;
import com.model.GridModel;
import com.model.patternWin;
import com.observer.Observer;
import com.vue.Colors;
import com.vue.Fenetre;
import com.vue.grille.Vue2;
import com.vue.titre.Vue1;

/**
 * Classe correspondant à la vue du jeu
 * @author loick
 *
 */

public class VueGrille extends Fenetre implements Observer {

	// private JPanel container = new JPanel();

	private Vue1 vueMenu;
	private Grid grid;
	private Score score;
	

	public static final int TAILLE_ECRAN_GRILLE=600;
	public static final int TAILLE_ECRAN_SCORE=300;
	

	// L'instance de notre objet controleur
	protected AbstractControler controler;

	/**
	 * Constructeur, initialise la vue du jeu
	 * @param controler controler du jeu
	 * @param nbLigne nombre de cases par ligne 
	 * @param nbJoueur nombre de joueurs
	 * @param vueMenu vue du menu (rappelle plus tard)
	 * @param isIA tableau type de joueurs
	 */
	
	//ADD CONTROLEr
	public VueGrille(GridControler controler, int nbRow, int nbCol, int nbJoueur) {
		
		this.controler=controler;
		this.vueMenu=vueMenu;
		this.setSize(TAILLE_ECRAN_GRILLE+TAILLE_ECRAN_SCORE, TAILLE_ECRAN_GRILLE);
		this.setTitle("3 Dots 3 Boxes");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));
		
		this.controler = controler;
		this.grid=new Grid(this, nbRow, nbCol);
		this.score= new Score(2);
		
		this.add(grid);
		this.add(score);
		this.setVisible(true);
		
	

		getContentPane().setBackground(Color.LIGHT_GRAY);
		//grid.setBackground(Color.BLUE);
		//repaint();
	
		}

	private Component topJustify( JPanel panel )  {
	    Box  b = Box.createVerticalBox();
	    b.add( panel );
	    b.add( Box.createVerticalGlue() );
	    // (Note that you could throw a lot more components
	    // and struts and glue in here.)
	    return b;
	}
	
	public static Color getColorByCaseValue(CaseValue v)
	{
		if(v==CaseValue.J1)
			return Color.RED;
		if(v==CaseValue.J2)
			return Color.YELLOW;
		if(v==CaseValue.NONE)
			return Color.WHITE;

		
		return Color.PINK;
	}

	
	
	public void updateTour(int tour){
		
	}
	

	public void updateReinit() {
		
	}

	@Override
	public void updateChip(int x, int y, CaseValue v) {
		
		this.grid.cases[x][y].c=VueGrille.getColorByCaseValue(v);
		this.repaint();
		
	}

	@Override
	public void updateWinner(int x, int y, patternWin p) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		GridModel m=new GridModel(7, 6, 2);
		GridControler c = new GridControler(m);
		VueGrille v=new VueGrille(c,7,6,2);
		
		m.addObserver(v);
		
		
	}

}