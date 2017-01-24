
package com.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.controler.AbstractControler;
import com.model.AbstractModel;
import com.model.BoxValues;
import com.observer.Observer;

public class VuePrincipale2 extends JFrame implements Observer {

	private JPanel container = new JPanel();
	
	private Grille grid;
	public static final int GRILLE_POSX=100;
	public static final int GRILLE_POSY=100;
	
	
	// l'ensemble des objets de vue
	// private

	// L'instance de notre objet controleur
	protected AbstractControler controler;

	public VuePrincipale2(AbstractControler controler, int nbLigne, int nbJoueur) {
		this.setSize(500, 500);
		this.setTitle("3 Dots 3 Boxes");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		// initComposant();

		// initialisation des composants
		// ...
		this.controler = controler;
		this.grid=new Grille(this, nbLigne);
		// ...

		
		this.setMaximumSize(new Dimension(400, 400));
		
		this.add(grid);
		// this.setContentPane(container);
		this.setVisible(true);
		//this.add(container);
		
		repaint();
		

	}

	/*
	 * //Les listeners de chaque bouton ou composant class CaseListener
	 * implements ActionListener{ public void actionPerformed(ActionEvent e) {
	 * Case c = ((Case) e.getSource()); controler.setCase(c.x, c.y);
	 * 
	 * } }
	 */

	/*
	class ResetListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			controler.reset();
		}
	}*/
	
	public Color getColorByBoxValues(BoxValues v)
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
			return Color.PINK;
		return Color.GRAY;
	}
	
	@Override
	public void updateWinner(ArrayList<BoxValues> gagnants) {
		// TODO Auto-generated method stub
		repaint();
		JOptionPane.showMessageDialog(this, "Le gagnant est" + gagnants.get(0));
		// System.out.println("Le gagnant est"+s);
		controler.reset();
	}

	@Override
	public void updateSquare(int x, int y, BoxValues v) {
		grid.cases[x][y].c=getColorByBoxValues(v);
	}

	@Override
	public void updateLineH(int x, int y, BoxValues v) {
		// TODO Auto-generated method stub
		grid.cotesH[x][y].c=getColorByBoxValues(v);
	}

	@Override
	public void updateLineV(int x, int y, BoxValues v) {
		// TODO Auto-generated method stub
		grid.cotesV[x][y].c=getColorByBoxValues(v);

	}

}