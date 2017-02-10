package com.vue.field;

import com.model.Direction;

/**
 * Extension de la classe raquette pour IA.
 * Le thread va demander au modèle de choisir lui même la prochaine position de la raquette.
 * @author loick
 *
 */
public class RacketIA extends Racket {

	
	public RacketIA(VueField vue, int idJ, int posX, int posY, int width, int height) {
		super(vue, idJ, posX, posY, width, height);
		
	}
	
	/**
	 * Demande au modèle de choisir la prochaine position.
	 */
	public void run(){
		
		while(true)
		{
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			vue.controlerRacket.decide(this.idJ);
			
			this.vue.revalidate();
			this.vue.repaint();
		}
		
	}
	
	
}
