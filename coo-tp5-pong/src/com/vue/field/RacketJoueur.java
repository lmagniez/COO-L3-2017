package com.vue.field;

import com.model.Direction;

/**
 * Extension de la classe raquette pour joueur
 * @author loick
 *
 */
public class RacketJoueur extends Racket{

	
	public RacketJoueur(VueField vue, int idJ, int posX, int posY, int width, int height) {
			super(vue, idJ, posX, posY, width, height);
			// TODO Auto-generated constructor stub
		}
	
	/**
	 * En fonction des evenements claviers, modifie la position de la raquette
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
				if(vue.adapter.doEnter())
				{
					vue.controlerRacket.throwBall();
				}
				
				
				this.vue.revalidate();
				this.vue.repaint();
			}
			
		}
	
}
