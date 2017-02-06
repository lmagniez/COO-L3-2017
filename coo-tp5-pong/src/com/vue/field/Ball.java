package com.vue.field;

public class Ball extends Thread {

	protected VueField vue;
	protected int id;
	protected int posX,posY,diam;
	
	public Ball(VueField vue, int id, int posX, int posY, int diam)
	{
		this.id=id;
		this.vue=vue;
		this.posX=posX;
		this.posY=posY;
		this.diam=diam;
		
	}
	
	public void run()
	{
		
		while(true)
		{
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("ok thread");
			this.vue.controler.setChange(id);
			this.vue.revalidate();
			this.vue.repaint();
		}
		
		
	}
	
}
