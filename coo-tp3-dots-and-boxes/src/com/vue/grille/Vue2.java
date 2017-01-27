
package com.vue.grille;

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
import com.controler.MenuControler;
import com.model.AbstractModel;
import com.model.BoxValues;
import com.observer.Observer;
import com.vue.Colors;
import com.vue.Fenetre;
import com.vue.titre.Vue1;

/**
 * Classe correspondant à la vue du jeu
 * @author loick
 *
 */

public class Vue2 extends Fenetre implements Observer {

	// private JPanel container = new JPanel();

	private Vue1 vueMenu;
	private Grille grid;
	private Score score;
	

	public static final int TAILLE_ECRAN_GRILLE=600;
	public static final int TAILLE_ECRAN_SCORE=200;
	

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
	public Vue2(AbstractControler controler, int nbLigne, int nbJoueur, Vue1 vueMenu, boolean[] isIA) {
		this.vueMenu=vueMenu;
		this.setSize(TAILLE_ECRAN_GRILLE+TAILLE_ECRAN_SCORE, TAILLE_ECRAN_GRILLE);
		this.setTitle("3 Dots 3 Boxes");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));
		
		this.controler = controler;
		this.grid=new Grille(this, nbLigne);
		this.score = new Score(nbJoueur);

		this.add(grid);
		this.add(score);
		this.setVisible(true);
	

		this.setBackground(Color.BLACK);
		repaint();
	
		}

	private Component topJustify( JPanel panel )  {
	    Box  b = Box.createVerticalBox();
	    b.add( panel );
	    b.add( Box.createVerticalGlue() );
	    // (Note that you could throw a lot more components
	    // and struts and glue in here.)
	    return b;
	}
	
	public static Color getColorByBoxValues(BoxValues v)
	{
		if(v==BoxValues.J1)
			return Color.RED;
		if(v==BoxValues.J2)
			return Color.BLUE;
		if(v==BoxValues.J3)
			return Color.YELLOW;
		if(v==BoxValues.J4)
			return Color.GREEN;
		if(v==BoxValues.NONE)
			return Color.GRAY;
		
		return Color.PINK;
	}
	
	public static Color getSquareColorByBoxValues(BoxValues v)
	{
		if(v==BoxValues.J1)
			return Colors.RED_TRANSPARENT;
		if(v==BoxValues.J2)
			return Colors.BLUE_TRANSPARENT;
		if(v==BoxValues.J3)
			return Colors.YELLOW_TRANSPARENT;
		if(v==BoxValues.J4)
			return Colors.GREEN_TRANSPARENT;
		if(v==BoxValues.EMPTY_SQUARE)
			return Color.BLACK;
		
		return Color.PINK;
	}

	/**
	 * Mise a jour du gagnant
	 * @param gagnants liste des gagnants
	 */
	@Override
	public void updateWinner(ArrayList<BoxValues> gagnants) {
		// TODO Auto-generated method stub
		repaint();
		JOptionPane.showMessageDialog(this, "Le gagnant est" + gagnants.get(0));
		// System.out.println("Le gagnant est"+s);
		
		//controler.reset();
		this.vueMenu.setVisible(true);
		this.dispose();
	}
	
	/**
	 * Mise a jour d'un carré
	 * @param x abscisse 
	 * @param y ordonée
	 * @param v valeur
	 */
	@Override
	public void updateSquare(int x, int y, BoxValues v) {
		grid.cases[x][y].c = getSquareColorByBoxValues(v);
		score.addCarreJoueur(v.ordinal());
		repaint();
	}

	/**
	 * Mise a jour d'une ligne
	 * @param x abscisse
	 * @param y ordonée
	 * @param v valeur 
	 */
	@Override
	public void updateLineH(int x, int y, BoxValues v) {
		// TODO Auto-generated method stub
		grid.cotesH[x][y].c = getColorByBoxValues(v);
		score.addTrait();
		repaint();
	}
	
	/**
	 * Mise a jour d'une ligne
	 * @param x abscisse
	 * @param y ordonée
	 * @param v valeur 
	 */
	@Override
	public void updateLineV(int x, int y, BoxValues v) {
		// TODO Auto-generated method stub
		grid.cotesV[x][y].c = getColorByBoxValues(v);
		score.addTrait();
		repaint();
	}
	
	
	public void updateTour(int tour){
		score.changeTour(tour);
	}
	

	public void updateReinit() {
		// TODO Auto-generated method stub
		grid.reinit();
	}

}