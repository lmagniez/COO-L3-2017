
package com.vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.controler.AbstractControler;
import com.observer.Observer;

public class NomVue extends JFrame implements Observer{
	
	private JPanel container = new JPanel();
	// l'ensemble des objets de vue
	//...
	
	//L'instance de notre objet controleur
	private AbstractControler controler;
	
	
	public NomVue(AbstractControler controler){
		
		// initialisation des composants
		//...
		this.controler = controler;
		//...
	}
	
	//Les listeners de chaque bouton ou composant
	class XXXListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//...
		}

		
	}
	//Implementation du pattern observer
	public void update(String str) {
		// MAJ des composants de la vue
		//...
	}

}