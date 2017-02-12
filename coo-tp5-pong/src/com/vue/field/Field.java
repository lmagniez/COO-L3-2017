package com.vue.field;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;

import com.model.Constantes;
import com.model.field.GameTimer;
import com.model.field.RacketIA;
import com.model.field.RacketJoueur;
import com.vue.MyKeyAdapter;

/**
 * Vue du terrain
 * @author loick
 *
 */
public class Field extends JPanel{

	protected VueField vue;
	protected MurH murs[];
	protected Racket rackets[];
	protected Vector<Ball> balls;
	protected Vector<Bonus> bonus;
	protected int scoreJ1=0;
	protected int scoreJ2=0;
	
	
	/**
	 * Constructeur
	 * @param vue
	 */
	public Field(VueField vue)
	{
		this.vue=vue;
		this.murs=new MurH[2];
		this.rackets=new Racket[2];
		this.balls=new Vector<Ball>();
		this.bonus=new Vector<Bonus>();
		this.balls.add(new Ball(vue, 0,Constantes.BALLE_X_J1,Constantes.BALLE_Y));
		
		this.murs[0]=new MurH(Constantes.MUR_X,Constantes.MUR1_Y,Constantes.MUR_WIDTH,Constantes.MUR_HEIGHT);
		this.murs[1]=new MurH(Constantes.MUR_X,Constantes.MUR2_Y,Constantes.MUR_WIDTH,Constantes.MUR_HEIGHT);
		
		//DECISION DES JOUEURS
		this.rackets[0]=new Racket(vue,0,Constantes.RAQUETTE_X_J1,Constantes.RAQUETTE_Y,Constantes.RAQUETTE_WIDTH,Constantes.RAQUETTE_HEIGHT);
		this.rackets[1]=new Racket(vue,1,Constantes.RAQUETTE_X_J2,Constantes.RAQUETTE_Y,Constantes.RAQUETTE_WIDTH,Constantes.RAQUETTE_HEIGHT);
		
		
	}
	
	public void paintComponent(Graphics g)
	{
		
		Graphics2D g2d = (Graphics2D) g;
		
		float[] dashingPattern2 = {10f, 4f};
		Stroke stroke2 = new BasicStroke(4f, BasicStroke.CAP_BUTT,
		        BasicStroke.JOIN_MITER, 1.0f, dashingPattern2, 0.0f);
		 
		g2d.setStroke(stroke2);
		g2d.draw(new Line2D.Double(Constantes.DIMENSION_X/2, Constantes.MUR_HEIGHT, Constantes.DIMENSION_X/2, Constantes.MUR2_Y));
		
		
		
		for(int i=0; i<2; i++){
			g.fillRect(murs[i].posX, murs[i].posY, murs[i].width, murs[i].height);
			g.fillRect(rackets[i].posX, rackets[i].posY, rackets[i].width, rackets[i].height);
		}
		
		for(int i=0; i<balls.size(); i++)
			g.fillOval(this.balls.get(i).posX, this.balls.get(i).posY, 
					this.balls.get(i).diam, this.balls.get(i).diam);
		
		for(int i=0; i<bonus.size(); i++)
			g.fillRect(this.bonus.get(i).posX, this.bonus.get(i).posY, 
					this.bonus.get(i).diam, this.bonus.get(i).diam);
		
		g.setFont(new Font("Arial", Font.PLAIN, 50));
		g.setColor(Color.black);
		g.drawString(scoreJ1+"      "+scoreJ2, Constantes.DIMENSION_X/2*8/10, Constantes.MUR_HEIGHT*2);
		
		
	}
	
}
