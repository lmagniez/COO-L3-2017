package com.vue.field;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.controler.AbstractControler;
import com.model.Constantes;
import com.observer.Observer;
import com.vue.Fenetre;
import com.vue.MyKeyAdapter;
import com.vue.titre.Vue1;

/**
 * Vue du terrain
 * @author loick
 *
 */
public class VueField extends Fenetre implements Observer{

	protected Vue1 menu;
	protected Field f;
	protected AbstractControler controlerBall;
	protected AbstractControler controlerRacket;
	protected AbstractControler controlerTimer;
	protected MyKeyAdapter adapter;
	
	
	/**
	 * Constructeur
	 * @param menu vue du menu
	 * @param isIA type de joueur
	 * @param controlerBall controler de la balle
	 * @param controlerRacket controler de la raquette 
	 * @param controlerTimer controler des evenements
	 */
	public VueField(Vue1 menu, boolean[] isIA, AbstractControler controlerBall, AbstractControler controlerRacket,
			AbstractControler controlerTimer){
	
		
		this.controlerBall=controlerBall;
		this.controlerRacket=controlerRacket;
		this.controlerTimer=controlerTimer;
		
		this.menu=menu;
		this.f=new Field(this,isIA);
		
		this.setSize(Constantes.DIMENSION_X,Constantes.DIMENSION_Y);
		this.setTitle("PONG");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		
		this.add(f);
		this.setVisible(true);
		
		this.repaint();
	
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		adapter= new MyKeyAdapter();
		this.addKeyListener(adapter);
		
		
		f.balls.get(f.balls.size()-1).start();
		
		f.rackets[0].start();
		f.rackets[1].start();
		
		f.t.start();
		
	}
	
	
	
	public void updateWinner(int idJoueur) {
		
		PanelVictoire v=new PanelVictoire(idJoueur, this);
		this.f.add(v);
		
	}

	/**
	 * Réinitialise la vue.
	 */
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
		
		this.f.bonus=new Vector<Bonus>();
		this.f.balls=new Vector<Ball>();
		Ball.NB_BALL=0;
		Bonus.nb_bonus=0;
		//this.f.balls.add(new Ball(this,0,Constantes.BALLE_X_J1,Constantes.BALLE_X_J2));
		//f.balls.get(0).start();
		
		this.f.rackets[0].posY=Constantes.RAQUETTE_Y;
		this.f.rackets[1].posY=Constantes.RAQUETTE_Y;
	
		
	}


	/**
	 * Met à jour la position de la balle
	 * @param idballe id de la balle
	 * @param posX position X de la balle
	 * @param posY position Y de la balle
	 */
	public void updatePosBalle(int idBalle, int posX, int posY) {
		
		Ball b=getBallById(idBalle);
		if(b!=null){
			b.posX=posX;
			b.posY=posY;
			b.diam=Constantes.DIAMETRE_BALLE;
		}
		
	}

	/**
	 * Récupère une balle en fonction de son id
	 * @param id id de la balle
	 * @return la balle, ou null
	 */
	public Ball getBallById(int id)
	{
		for(int i=0; i<f.balls.size(
				); i++)
		{
			if(f.balls.get(i).id==id)
				return f.balls.get(i);
		}
		return null;
	}
	
	/**
	 * Récupère l'id dans le tableau du bonus en fonction de son id 
	 * @param id id du bonus
	 * @return id dans le tableau
	 */
	public int getIndiceBonusById(int id)
	{
		for(int i=0; i<f.bonus.size(); i++)
		{
			if(f.bonus.get(i).id==id)
				return i;
		}
		return -1;
	}
	

	
	/**
	 * Mise a jour de la position de la raquette
	 * @param idRacket id de la raquette
	 * @param posX position X de la raquette
	 * @param posY position Y de la raquette
	 */
	public void updatePosRacket(int idRacket, int posX, int posY) {
		this.f.rackets[idRacket].posX=posX;
		this.f.rackets[idRacket].posY=posY;
		this.f.rackets[idRacket].width=Constantes.RAQUETTE_WIDTH;
		this.f.rackets[idRacket].height=Constantes.RAQUETTE_HEIGHT;
	}


	/**
	 * Mise a jour de la position du mur horizontal
	 * @param idMur id du mur
	 * @param posX position X du mur
	 * @param posY position Y du mur
	 */
	public void initMurH(int idMur, int posX, int posY) {
		this.f.murs[idMur].posX=posX;
		this.f.murs[idMur].posY=posY;
		this.f.murs[idMur].width=Constantes.MUR_WIDTH;
		this.f.murs[idMur].height=Constantes.MUR_HEIGHT;
		
	}


	/**
	 * Mise a jour d'une nouvelle balle
	 * @param idBalle id de la balle
	 * @param posX position X de la balle
	 * @param posY position Y de la balle
	 */
	public void updateNewBalle(int idBalle, int posX, int posY) {
		Ball b = new Ball(this,idBalle,posX,posY);
		this.f.balls.add(b);
		b.start();
		
		f.revalidate();
		f.repaint();
		
		//f.balls.get(f.balls.size()-1).start();
	}


	/**
	 * Mise a jour d'un nouveau bonus
	 * @param idB id du bonus
	 * @param posX position X du bonus
	 * @param posY position Y du bonus
	 */
	public void updateNewBonus(int idB, int x, int y) {
		this.f.bonus.add(new Bonus(this,idB,x,y));
	}

	

	/**
	 * Mise a jour de la disparition d'un bonus
	 * @param idB id du bonus
	 */
	public void updateEraseBonus(int idB) {
		// TODO Auto-generated method stub
		int i = getIndiceBonusById(idB);
		if(i!=-1)
			this.f.bonus.remove(i);
	}


	/**
	 * Mise a jour du score
	 * @param sJ1 score du J1
	 * @param sJ2 score du J2
	 */
	public void updateScore(int sJ1, int sJ2) {
		// TODO Auto-generated method stub
		this.f.scoreJ1=sJ1;
		this.f.scoreJ2=sJ2;
		
	}

	
}
