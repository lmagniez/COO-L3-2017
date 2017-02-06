package com.vue.field;

import javax.swing.JFrame;

import com.controler.AbstractControler;
import com.model.Constantes;
import com.model.field.FieldModel;
import com.model.grid.CaseValue;
import com.observer.Observer;
import com.vue.Fenetre;
import com.vue.titre.Vue1;

public class VueField extends Fenetre implements Observer{

	protected Vue1 menu;
	protected Field f;
	protected AbstractControler controler;
	
	public VueField(Vue1 menu, AbstractControler controler){
	
		
		this.controler=controler;
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
		f.balls.get(0).start();
		
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
		
	}


	@Override
	public void updatePosBalle(int idBalle, int posX, int posY, int diam) {
		this.f.balls.get(idBalle).posX=posX;
		this.f.balls.get(idBalle).posY=posY;
		this.f.balls.get(idBalle).diam=diam;
	}


	@Override
	public void updatePosRacket(int idRacket, int posX, int posY, int width, int height) {
		this.f.rackets[idRacket].posX=posX;
		this.f.rackets[idRacket].posY=posY;
		this.f.rackets[idRacket].width=width;
		this.f.rackets[idRacket].width=height;
	}


	@Override
	public void initMurH(int idMur, int posX, int posY, int width, int height) {
		this.f.murs[idMur].posX=posX;
		this.f.murs[idMur].posY=posY;
		this.f.murs[idMur].width=width;
		this.f.murs[idMur].height=height;
		
	}

	
}
