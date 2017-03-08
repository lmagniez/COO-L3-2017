package com.vue.grid;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.controler.AbstractControler;
import com.model.CaseValue;
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

	protected AbstractControler gridControler;
	
	protected Vue1 vueMenu;
	private Grid grid;
	protected Score score;
	protected boolean swapColor;
	protected CaseValue tour;
	protected ArrayList<int[]> posJouable;
	
	public static final int TAILLE_ECRAN_GRILLE_X=500;
	public static final int TAILLE_ECRAN_GRILLE_Y=500-Grid.ESPACEMENT_SIZE-Grid.ESPACEMENT2_SIZE;
	
	public static final int TAILLE_ECRAN_SCORE=200;
	

	

	
	
	/**
	 * COnstructeur
	 * @param controler controler
	 * @param menu vue
	 * @param nbColonne 
	 * @param nbLigne 
	 */
	
	//ADD CONTROLEr
	public VueGrid(AbstractControler controler, Vue1 menu, int nbLigne, int nbColonne) {
		
		this.gridControler=controler;
		
		this.swapColor=swapColor;
		this.vueMenu=menu;
		this.setSize(TAILLE_ECRAN_GRILLE_X, TAILLE_ECRAN_GRILLE_Y+TAILLE_ECRAN_SCORE);
		this.setTitle("PICROSS");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		this.grid=new Grid(this, nbColonne, nbLigne);
		this.score= new Score(this,2);
		this.tour=CaseValue.J1;
		
		this.setVisible(true);
		
		getContentPane().setBackground(Color.LIGHT_GRAY);
		//grid.setBackground(Color.BLUE);
		//repaint();
		this.repaint();
		
		
		//window listener!
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent we)
		    { 
		        String ObjButtons[] = {"Yes","No"};
		        int PromptResult = JOptionPane.showOptionDialog(null,"Sauvegarder la partie?","Confirmation",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
		        if(PromptResult==JOptionPane.YES_OPTION)
		        {
		        	//do something
		        	VueGrid.this.gridControler.requestSave();
		        }
		        System.exit(0);
		    }
		});
		
		this.add(score);
		this.add(grid);
		
		
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
				getGrid().cases[i][j].color=CaseValue.getColorFromValue(CaseValue.EMPTY);
			}
		}
		
		this.score.nbJetonsJ1=0;
		this.score.nbJetonsJ2=0;
		this.score.scoreJ1.setText("score J1: "+2);
		this.score.scoreJ2.setText("score J2: "+2);
		
		getGrid().repaint();
		this.getGrid().actif=true;
		
	}

	

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	@Override
	public void updateChangeValue(int x, int y, CaseValue v) {
		// TODO Auto-generated method stub
		this.grid.cases[x][y].changeValue(v);
		this.repaint();
	}

	@Override
	public void updateWin(CaseValue v) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this,v+" a gogné!"); 
		
		vueMenu.setVisible(true);
		this.setVisible(false);
	}

	@Override
	public void updateScore(int nbJetonsJ1, int nbJetonsJ2) {
		// TODO Auto-generated method stub
		this.score.nbJetonsJ1=nbJetonsJ1;
		this.score.nbJetonsJ2=nbJetonsJ2;
		this.score.scoreJ1.setText("Score J1: "+nbJetonsJ1);
		this.score.scoreJ2.setText("Score J2: "+nbJetonsJ2);
		
		this.score.p2.revalidate();
		this.score.p2.repaint();
		
	}

	@Override
	public void updateTour(CaseValue v) {
		// TODO Auto-generated method stub
		System.out.println("update tour "+v);
		this.tour=v;
	}

	@Override
	public void updatePosJouable(ArrayList<int[]> liste) {
		// TODO Auto-generated method stub
		
		this.posJouable=liste;
		this.grid.repaint();
		
	}

	
	
}