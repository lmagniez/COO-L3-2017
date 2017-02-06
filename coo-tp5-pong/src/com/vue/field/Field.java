package com.vue.field;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.model.Constantes;

public class Field extends JPanel{

	protected VueField vue;
	protected MurH murs[];
	protected Racket rackets[];
	protected ArrayList<Ball> balls=new ArrayList<Ball>();
	
	
	public Field(VueField vue)
	{
		this.vue=vue;
		this.murs=new MurH[2];
		this.rackets=new Racket[2];
		this.balls.add(new Ball(vue, 0,Constantes.BALLE_X_J1,Constantes.BALLE_Y,Constantes.DIAMETRE_BALLE));
		this.murs[0]=new MurH(Constantes.MUR_X,Constantes.MUR1_Y,Constantes.MUR_WIDTH,Constantes.MUR_HEIGHT);
		this.murs[1]=new MurH(Constantes.MUR_X,Constantes.MUR2_Y,Constantes.MUR_WIDTH,Constantes.MUR_HEIGHT);
		this.rackets[0]=new Racket(Constantes.RAQUETTE_X_J1,Constantes.RAQUETTE_Y,Constantes.RAQUETTE_WIDTH,Constantes.RAQUETTE_HEIGHT);
		this.rackets[1]=new Racket(Constantes.RAQUETTE_X_J2,Constantes.RAQUETTE_Y,Constantes.RAQUETTE_WIDTH,Constantes.RAQUETTE_HEIGHT);
		
		
		//this.murs[0]=new
		
	}
	
	public void paintComponent(Graphics g)
	{
		for(int i=0; i<2; i++){
			g.fillRect(murs[i].posX, murs[i].posY, murs[i].width, murs[i].height);
			g.fillRect(rackets[i].posX, rackets[i].posY, rackets[i].width, rackets[i].height);
		}
		
		for(int i=0; i<balls.size(); i++)
			g.fillOval(this.balls.get(i).posX, this.balls.get(i).posY, 
					this.balls.get(i).diam, this.balls.get(i).diam);
	
	}
	
}
