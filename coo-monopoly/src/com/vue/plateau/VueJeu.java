package com.vue.plateau;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import com.model.ConstantesVue;
import com.observer.Observer;
import com.vue.Fenetre;
import com.vue.menu.VueMenu;

public class VueJeu extends Fenetre implements Observer{

	protected EcranJeu lePanneau;
	
	public VueJeu(){
		
		
		
		this.setTitle("Monopoly");
		this.setSize(ConstantesVue.DIMENSION_FENETRE_X, ConstantesVue.DIMENSION_FENETRE_Y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setFocusable(false);
		
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
	public void updateWinner(String s) {
		// TODO Auto-generated method stub
		
	}

}
