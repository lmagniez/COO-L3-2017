package com.vue.field;

import com.model.Direction;

public class Racket extends Thread {

	protected VueField vue;
	protected int idJ;
	protected int posX;
	protected int posY;
	protected int height,width;
	
	public Racket(VueField vue, int idJ, int posX, int posY, int width, int height)
	{
		this.vue=vue;
		this.idJ=idJ;
		this.posX=posX;
		this.posY=posY;
		this.height=height;
		this.width=width;
		
	}
	
	public void run(){
		
		while(true)
		{
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(idJ==1&&vue.adapter.doActionUP())
			{
				vue.controlerRacket.setChange(this.idJ,Direction.NORD);
			}
			if(idJ==1&&vue.adapter.doActionDOWN())
			{
				vue.controlerRacket.setChange(this.idJ,Direction.SUD);
			}
			if(idJ==0&&vue.adapter.doActionS())
			{
				vue.controlerRacket.setChange(this.idJ,Direction.SUD);
			}
			if(idJ==0&&vue.adapter.doActionZ())
			{
				vue.controlerRacket.setChange(this.idJ,Direction.NORD);
			}
			this.vue.revalidate();
			this.vue.repaint();
		}
		
	}
	
	
}
