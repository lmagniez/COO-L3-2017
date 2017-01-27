package com.controler;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.model.AbstractModel;
import com.vue.Fenetre;
import com.vue.titre.Vue1;


/**
 * Controler du Menu.
 * Permet d'initier une nouvelle fenêtre de jeu.
 * @author loick
 *
 */
public class MenuControler extends AbstractControler{

	/**
	 * Génération de la fenetre de jeu
	 * @param vue Vue de la fenetre
	 * @param nbLigne nombre de ligne de la grille
	 * @param nbJoueur nombre de joueurs dans la partie
	 * @param isIA tableau correspondant au type de chaque joueurs
	 */
	public void genererJeu(Vue1 vue, int nbLigne, int nbJoueur, boolean[] isIA)
	{
		vue.initFenetreEcranJeu(nbLigne,nbJoueur,isIA);	
	}
	
	/**
	 * Controle (non utilisé)
	 */
	@Override
	public void control() {
		// TODO Auto-generated method stub
		
	}

}
