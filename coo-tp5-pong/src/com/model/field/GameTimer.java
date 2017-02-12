package com.model.field;

import com.vue.field.VueField;

/**
 * Thread des evenements. Demande l'apparition de bonus et l'augmentation de vitesse
 * @author loick
 *
 */
public class GameTimer extends Thread {

	protected boolean running=true;
	protected FieldModel field;
	
	public GameTimer(FieldModel field)
	{
		this.field=field;
	}
	
	/**
	 *  Demande l'apparition de bonus et l'augmentation de vitesse
	 */
	public void run()
	{
		
		while(running)
		{
			
			//vue.controlerTimer.setNouveauBonus(true);
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			field.ajoutBonus();
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			field.augmenteVitesse();
			
			
		}
		
		
	}
	/**
	 * Stoppe le thread
	 */
	public void arret() { // Méthode 2
		running = false;
	}
	/**
	 * Reprend le thread
	 */
	public void reprendre() { // Méthode 2
		running = true;
	}
	
}
