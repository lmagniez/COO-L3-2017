package com.vue.field;

import java.util.Vector;

import javax.swing.JFrame;

import com.controler.AbstractControler;
import com.model.Constantes;
import com.observer.Observer;
import com.vue.Fenetre;
import com.vue.MyKeyAdapter;
import com.vue.titre.Vue1;

public class VueField extends Fenetre implements Observer{

	protected Vue1 menu;
	protected Field f;
	protected AbstractControler controlerBall;
	protected AbstractControler controlerRacket;
	protected AbstractControler controlerTimer;
	protected MyKeyAdapter adapter;
	
	
	
	public VueField(Vue1 menu, AbstractControler controlerBall, AbstractControler controlerRacket,
			AbstractControler controlerTimer){
	
		
		this.controlerBall=controlerBall;
		this.controlerRacket=controlerRacket;
		this.controlerTimer=controlerTimer;
		
		this.menu=menu;
		this.f=new Field(this);
		
		this.setSize(Constantes.DIMENSION_X,Constantes.DIMENSION_Y);
		this.setTitle("PONG");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		
		this.add(f);
		this.setVisible(true);
		
		//getContentPane().setBackground(Color.LIGHT_GRAY);
		//grid.setBackground(Color.BLUE);
		//repaint();
		this.repaint();
	
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		adapter= new MyKeyAdapter();
		this.addKeyListener(adapter);
		
		
		f.balls.get(0).start();
		f.balls.get(1).start();
		
		f.rackets[0].start();
		f.rackets[1].start();
		
		f.t.start();
		
		
		
	/*
	 * 
	 * UPDATES
	 */
	}
	
	
	@Override
	public void updateWinner(int tour) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateReinit() {
		// TODO Auto-generated method stub
		
		
		for(int i=0; i<f.balls.size(); i++)
		{
			this.f.balls.get(i).arret();		
		}
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.f.balls=new Vector<Ball>();
		Ball.NB_BALL=0;
		//this.f.balls.add(new Ball(this,0,Constantes.BALLE_X_J1,Constantes.BALLE_X_J2));
		//f.balls.get(0).start();
		
		this.f.rackets[0].posY=Constantes.RAQUETTE_Y;
		this.f.rackets[1].posY=Constantes.RAQUETTE_Y;
	}


	@Override
	public void updatePosBalle(int idBalle, int posX, int posY) {
		
		Ball b=getBallById(idBalle);
		//System.out.println(f.balls.size());
		b.posX=posX;
		b.posY=posY;
		b.diam=Constantes.DIAMETRE_BALLE;
		/*
		this.f.balls.get(idBalle).posX=posX;
		this.f.balls.get(idBalle).posY=posY;
		this.f.balls.get(idBalle).diam=Constantes.DIAMETRE_BALLE;
		*/
	}

	public Ball getBallById(int id)
	{
		for(int i=0; i<f.balls.size(); i++)
		{
			if(f.balls.get(i).id==id)
				return f.balls.get(i);
		}
		return null;
	}
	
	public int getIndiceBonusById(int id)
	{
		for(int i=0; i<f.bonus.size(); i++)
		{
			System.out.println("i: "+i+" bonusID: "+f.bonus.get(i).id);
			if(f.bonus.get(i).id==id)
				return i;
		}
		return -1;
	}
	

	
	@Override
	public void updatePosRacket(int idRacket, int posX, int posY) {
		this.f.rackets[idRacket].posX=posX;
		this.f.rackets[idRacket].posY=posY;
		this.f.rackets[idRacket].width=Constantes.RAQUETTE_WIDTH;
		this.f.rackets[idRacket].height=Constantes.RAQUETTE_HEIGHT;
	}


	@Override
	public void initMurH(int idMur, int posX, int posY) {
		this.f.murs[idMur].posX=posX;
		this.f.murs[idMur].posY=posY;
		this.f.murs[idMur].width=Constantes.MUR_WIDTH;
		this.f.murs[idMur].height=Constantes.MUR_HEIGHT;
		
	}


	@Override
	public void updateNewBalle(int idBalle, int posX, int posY) {
		Ball b = new Ball(this,idBalle,posX,posY);
		this.f.balls.add(b);
		b.start();
		//f.balls.get(f.balls.size()-1).start();
	}


	@Override
	public void updateNewBonus(int idB, int x, int y) {
		System.out.println("add! "+idB);
		this.f.bonus.add(new Bonus(this,idB,x,y));
	}


	@Override
	public void updateEraseBonus(int idB) {
		// TODO Auto-generated method stub
		int i = getIndiceBonusById(idB);
		System.out.println("updateEraseBonus vue field indiceRecherché idB: "+idB+" "+"i: "+i);
		this.f.bonus.remove(i);
	}

	
}
