package com.vue.field;

public class GameTimer extends Thread {

	protected boolean running=true;
	protected VueField vue;
	
	public GameTimer(VueField vue)
	{
		this.vue=vue;
	}
	
	public void run()
	{
		
		while(running)
		{
			
			vue.controlerTimer.setNouveauBonus(true);
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
		
	}
	/**
	 * Stoppe le thread
	 */
	public void arret() { // Méthode 2
		running = false;
	}
	public void reprendre() { // Méthode 2
		running = true;
	}
	
}
