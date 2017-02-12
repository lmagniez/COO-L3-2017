package com.model.field;

import com.model.Direction;
import com.vue.field.Racket;
import com.vue.field.VueField;

/**
 * Extension de la classe raquette pour joueur
 * @author loick
 *
 */
public class RacketJoueur extends RacketModel {

	
	/**
	 * Constructeur
	 * @param field modele du terrain
	 * @param idJ id joueur
	 */
	public RacketJoueur(FieldModel field, int idJ) {
			super(field,idJ);
			this.d=Direction.NONE;
			// TODO Auto-generated constructor stub
		}
	
	
	/**
	 * En fonction des directions rentrées, change la direction de la raquette
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
				//this.update(d);
				this.update(up, down);
				this.field.notifyNewPosRacket(idJoueur, posX, posY);
			}
			
		}
		
	
}
