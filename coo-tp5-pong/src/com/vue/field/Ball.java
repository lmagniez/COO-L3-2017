package com.vue.field;

import com.model.Constantes;

public class Ball extends Thread {

	public static int NB_BALL=0;
	protected int nb;
	protected VueField vue;
	protected int id;
	protected volatile int posX;
	protected volatile int posY;
	protected int diam;
	protected boolean running=true;
	
	public Ball(VueField vue, int id, int posX, int posY)
	{
		this.running=true;
		this.id=NB_BALL++;
		this.vue=vue;
		this.posX=posX;
		this.posY=posY;
		this.diam=Constantes.DIAMETRE_BALLE;
		//nb=NB_BALL++;
		
	}
	
	public void run()
	{
		
		while(running)
		{
			//System.out.println("run "+id + " running "+running);
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("ok thread");
			this.vue.controlerBall.setChange(id);
			this.vue.revalidate();
			this.vue.repaint();
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
