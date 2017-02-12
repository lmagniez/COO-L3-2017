package com.model.field;

import com.model.Constantes;
import com.model.Direction;
import com.vue.field.VueField;

/**
 * Extension de la classe raquette pour IA.
 * Le thread va demander au modèle de choisir lui même la prochaine position de la raquette.
 * @author loick
 *
 */
public class RacketIA extends RacketModel {

	protected FieldModel field;
	
	
	/**
	 * Constructeur
	 * @param idJoueur id du joueur
	 */
	public RacketIA(FieldModel f, int idJoueur)
	{
		super(f,idJoueur);
		this.field=f;
		
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
			
			this.field.decide(this.idJoueur);
			
			this.field.notifyNewPosRacket(idJoueur, posX, posY);
		}
		
	}
	
	
}
