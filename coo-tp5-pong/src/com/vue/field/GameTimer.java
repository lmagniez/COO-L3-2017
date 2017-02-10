package com.vue.field;

/**
 * Thread des evenements. Demande l'apparition de bonus et l'augmentation de vitesse
 * @author loick
 *
 */
public class GameTimer extends Thread {

	protected boolean running=true;
	protected VueField vue;
	
	public GameTimer(VueField vue)
	{
		this.vue=vue;
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
			
			vue.controlerTimer.setNouveauBonus(true);
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			vue.controlerTimer.setVitesse(true);
			
			
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
