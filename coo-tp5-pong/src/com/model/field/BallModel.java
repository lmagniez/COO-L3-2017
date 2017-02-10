package com.model.field;

import com.model.Constantes;
import com.model.Direction;

/**
 * Classe modèle représentant une balle
 * @author loick
 *
 */
public class BallModel {
	
	protected int id;
	protected FieldModel field;
	protected int posX,posY;
	protected int coefX, coefY;
	public static int nb_balle=0;
	
	/**
	 * Constructeur
	 * @param field modele du terrain 
	 * @param idJ id du joueur
	 */
	public BallModel(FieldModel field, int idJ)
	{

		this.id=nb_balle++;
		this.field=field;
		this.posY=Constantes.BALLE_Y;
		this.coefX=Constantes.BALLE_COEFX;
		this.coefY=Constantes.BALLE_COEFY;
		if(idJ==1)
			this.posX=Constantes.BALLE_X_J1;
		else{
			this.posX=Constantes.BALLE_X_J2;
			this.coefX*=-1;
		}
		
	}
	
	/**
	 * Constructeur complet
	 * @param field modele du terrain
	 * @param posX pos x de la balle 
	 * @param posY pos y de la balle
	 * @param coefX coefficient x de la balle
	 * @param coefY coefficient y de la balle
	 */
	public BallModel(FieldModel field, int posX, int posY, int coefX, int coefY)
	{

		this.id=nb_balle++;
		this.field=field;
		this.posX=posX;
		this.posY=posY;
		this.coefX=coefX;
		this.coefY=coefY;
		
		
	}
	
	/**
	 * Mise à jour de la balle en fonction de son coeff et sa vitesse
	 */
	public void updateBall()
	{
		
		posX+=(coefX*field.actualSpeed);
		posY+=(coefY*field.actualSpeed);
	}
	
	/**
	 * Inverse le coefficient x de la balle
	 */
	public void changeDirectionX()
	{
		coefX*=-1;
	}
	
	/**
	 * Inverse le coefficient y de la balle
	 */
	public void changeDirectionY()
	{
		coefY*=-1;
	}
	
	/**
	 * Test de la collision de la balle contre une autre balle
	 * @param b balle à tester
	 * @return collision ou non
	 */
	public boolean collideBall(BallModel b)
	{
		if((this.posX>=b.posX)&&(this.posX<=b.posX+Constantes.DIAMETRE_BALLE)
				&&(this.posY>=b.posY)&&(this.posY<=b.posY+Constantes.DIAMETRE_BALLE))
		{
			this.changeDirectionY();
			this.changeDirectionX();
			
			b.changeDirectionX();
			b.changeDirectionY();
			
			b.updateBall();
			updateBall();
			
			return true;
		}
		return false;
		
	}
	
	/**
	 * Test de collision d'une balle contre une raquette
	 * @param r raquette à tester
	 * @return collision ou non
	 */
	public boolean collideRacket(RacketModel r)
	{
		
		if((this.posX+Constantes.DIAMETRE_BALLE>r.posX)
			&&(this.posX<r.posX+r.width)
			&&(this.posY>=r.posY)
			&&(this.posY<=r.posY+r.height))
		{
				//{
			if(r.idJoueur==0)
				this.posX=r.posX+r.width;
			if(r.idJoueur==1)
				this.posX=r.posX-Constantes.DIAMETRE_BALLE;
			
			this.changeDirectionX();
			return true;
		}
		return false;
		
	}
	
	/**
	 * Test de collision d'une balle contre un mur
	 * @param m mur a tester
	 * @return collision ou non
	 */
	public boolean collideMurH(MurHModel m)
	{
		if((m.idMur==0)&&(this.posY<m.posY+m.height)
				&&(this.posY<(m.posY+m.height)*0.95))
		{
			this.posY=m.posY+m.height;
			this.changeDirectionY();
			return true;
		}
		if((m.idMur==1)&&(this.posY>(m.posY-Constantes.DIAMETRE_BALLE))
				&&(this.posY>(m.posY-(Constantes.DIAMETRE_BALLE)*1.05)))
		{
			this.posY=m.posY-Constantes.DIAMETRE_BALLE;
			this.changeDirectionY();
			return true;
		}
		
		return false;
	}
	
	/**
	 * Collision du mur vertical gauche (point pour j2)
	 * @return collision ou non
	 */
	public boolean collideMurVG()
	{
		if(this.posX<0)
		{
			this.posX=0;
			//this.scoreJ2
			this.field.avantageJ1=false;
			this.field.scoreJ2++;
			this.field.notifyScore(this.field.scoreJ1, this.field.scoreJ2);
			this.field.reinit();
			if(this.field.scoreJ2==Constantes.SCORE_GAGNANT)
				this.field.notifyWinner(2);
			return true;
		}
		return false;
	}
	
	/**
	 * Collision du mur vertical droit (point pour j1)
	 * @return collision ou non
	 */
	public boolean collideMurVD()
	{
		if(this.posX>Constantes.DIMENSION_X-Constantes.DIAMETRE_BALLE)
		{
			this.posX=Constantes.DIMENSION_X-Constantes.DIAMETRE_BALLE;
			//this.scoreJ1
			this.field.avantageJ1=true;
			this.field.scoreJ1++;
			this.field.notifyScore(this.field.scoreJ1, this.field.scoreJ2);
			this.field.reinit();
			if(this.field.scoreJ1==Constantes.SCORE_GAGNANT)
				this.field.notifyWinner(1);
			return true;
		}
		return false;
	}

	/**
	 * Teste la collision contre un bonus donnée
	 * @param b bonus à tester
	 * @return collision ou non
	 */
	public boolean collideBonus(BonusModel b) {
		// TODO Auto-generated method stub
		
		if((this.posX>=b.posX)&&(this.posX<=b.posX+Constantes.DIAMETRE_BONUS)
				&&(this.posY>=b.posY)&&(this.posY<=b.posY+Constantes.DIAMETRE_BONUS))
		{
			b.onScreen=false;
			return true;
		}
		return false;
	}
	
	/**
	 * Met à jour la direction de la balle en fonction de la direction donnée
	 * (Utilisé quand la balle est stoppée et associée à une raquette en début de manche)
	 * @param d Direction de la balle
	 */
	public void update(Direction d)
	{
		if(d==Direction.NORD&&posY>Constantes.RAQUETTE_HEIGHT/2+Constantes.MUR_HEIGHT)
		{
			this.posY-=coefY;
		}
		if(d==Direction.SUD
				&&posY<Constantes.DIMENSION_Y-Constantes.MUR_HEIGHT-Constantes.RAQUETTE_HEIGHT/2)
		{
			this.posY+=coefY;
		}
		if(posY<0)posY=0;
		if(posY>Constantes.DIMENSION_Y)posY=Constantes.DIMENSION_Y;
		
		
	}
	
	
}
