package com.controler;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.model.AbstractModel;
import com.vue.Fenetre;
import com.vue.titre.Vue1;

public class MenuControler extends AbstractControler{

	
	private JPanel PanelToDisplay;
	private JFrame FrameToDisplay;
	
	
	public MenuControler() {
		
	}

	public void genererJeu(Vue1 vue, int nbLigne, int nbJoueur, boolean[] isIA)
	{
		vue.initFenetreEcranJeu(nbLigne,nbJoueur,isIA);	
	}
	
	@Override
	public void control() {
		// TODO Auto-generated method stub
		
	}

}
