package com.model.field;

import java.util.ArrayList;
import java.util.Vector;

import com.model.AbstractModel;
import com.model.Constantes;
import com.model.Direction;

/**
 * Modèle représentant un terrain
 * @author loick
 *
 */
public class FieldModel extends AbstractModel{

	protected Vector<BallModel> balls;
	protected RacketModel racketJ1;
	protected RacketModel racketJ2;
	protected MurHModel murHaut;
	protected MurHModel murBas;
	protected Vector<BonusModel> bonus;
	protected double actualSpeed;
	protected boolean gameStopped=false;
	protected boolean avantageJ1=true;
	protected int scoreJ1,scoreJ2;
	
	protected GameTimer t;
	
	
	/**
	 * Constructeur
	 */
	public FieldModel(boolean[] isIA)
	{
		//balls
		balls=new Vector<BallModel>();
		bonus=new Vector<BonusModel>();
		balls.add(new BallModel(this,1));
		balls.get(0).start();
		
		
		//rackets
		if(isIA[0])
			this.racketJ1=new RacketIA(this,0);
		else
			this.racketJ1=new RacketJoueur(this,0);
		if(isIA[1])
			this.racketJ2=new RacketIA(this,1);
		else
			this.racketJ2=new RacketJoueur(this,1);
		Thread t1=new Thread(racketJ1);
		Thread t2=new Thread(racketJ2);
		t1.start();
		t2.start();
		
		
		t=new GameTimer(this);
		t.start();
		this.murHaut=new MurHModel(0);
		this.murBas=new MurHModel(1);
		this.gameStopped=true;
		this.scoreJ1=0;
		this.scoreJ2=0;
		this.actualSpeed=1.0;
		
	}


	/**
	 * Teste si le jeu est arrêté (début de manche)
	 */
	public boolean isStopped(){
		return gameStopped;
	}


	/**
	 * Mise à jour de la balle (teste chaque type de collision et met à jour sa position)
	 * Notifie le changement.
	 * @param idB id de la balle
	 */
	public void updateBall(int idB) {
		
		
		BallModel b=getBallById(idB);
		
		if(b!=null)b.collideMurH(murHaut);
		if(b!=null)b.collideMurH(murBas);
		if(b!=null)if(b.collideMurVG())return;
		if(b!=null)if(b.collideMurVD())return;
		if(b!=null)b.collideRacket(racketJ1);
		if(b!=null)b.collideRacket(racketJ2);
		
		for(int i=0; i<bonus.size(); i++)
		{
			if(b!=null)
			if(bonus.get(i).onScreen&&b.collideBonus(bonus.get(i)))
			{
				this.notifyEraseBonus(bonus.get(i).id);
				BallModel newBall= new BallModel(this,b.posX+Constantes.DIAMETRE_BALLE,b.posY+Constantes.DIAMETRE_BALLE,b.coefX,-b.coefY);
				balls.add(newBall);
				newBall.start();
				
				
				//BallModel.nb_balle++;
				this.notifyNewBalle(BallModel.nb_balle, 
						balls.get(balls.size()-1).posX, 
						balls.get(balls.size()-1).posY);
			}
		}
		for(int i=0; i<balls.size(); i++)
			if(b!=null)
			if(i!=idB)
				b.collideBall(balls.get(i));
		if(b!=null)
		b.updateBall();
		if(b!=null)
		this.notifyNewPosBall(idB, b.posX, b.posY);
	
	}


	
	/**
	 * Mise à jour de la raquette (fait bouger la balle d'id 0
	 * si le jeu est arrêté et que l'avantage et pour la raquette en question).
	 * Notifie le changement
	 * @param idR id de la raquette
	 * @param d direction de la raquette
	 */
	public void updateRacket(int idR, Direction d) {
		
		if((gameStopped&&avantageJ1&&idR==0)
				||(gameStopped&&!avantageJ1&&idR==1))
		{
			for(int i=0; i<balls.size(); i++){
				BallModel b=balls.get(i);
				b.update(d);
				this.notifyNewPosBall(b.id, b.posX, b.posY);
			}
		}
		
		if(idR==0){
			this.racketJ1.update(d);
		}
		if(idR==1){
			this.racketJ2.update(d);
		}
	}
	
	/**
	 * Mise a jour de la raquette via booleen 
	 * @param idR id raquette
	 * @param up dirige la raquette vers le haut
	 * @param down dirige la raquette vers le bas
	 */
	public void updateRacket(int idR, boolean up, boolean down)
	{
		if(idR==0)
			this.racketJ1.updateDir(up,down);
		if(idR==1)
			this.racketJ2.updateDir(up,down);
	}
	

	/**
	 * Ajoute un bonus sur le modèle et notifie
	 */
	public void ajoutBonus()
	{
		BonusModel b=new BonusModel(this);
		this.bonus.add(b);
		this.notifyNewBonus(b.id, b.posX, b.posY);
	}
	
	/**
	 * Récupérer une balle en fonction de son id
	 * @param id id de la balle
	 * @return Modèle de la balle, null si non trouvé
	 */
	public BallModel getBallById(int id)
	{
		//System.out.println("fieldModel id: "+id);
		for(int i=0; i<balls.size(); i++)
		{
			//System.out.println("i:"+i+" "+balls.get(i).id);
			if(balls.get(i).id==id)
				return balls.get(i);
		}
		return null;
	}
	
	/**
	 * Augmente le coefficient de la vitesse
	 */
	public void augmenteVitesse()
	{
		this.actualSpeed+=0.2;
	}
	
	/**
	 * Lancer la balle (redémarre le jeu)
	 */
	public void throwBall()
	{
		if(gameStopped)
			this.actualSpeed=1.0;
		this.gameStopped=false;
		
	}
	
	/**
	 * Met à jour la raquette de manière indépendante (IA).
	 * On va chercher la balle la plus proche sur l'abscisse x 
	 * et essayer de renvoyer celle ci en atteignant son axe y
	 * @param idJoueur joueur concerné par le déplacement
	 */
	public void decide(int idJoueur)
	{
		if((gameStopped&&avantageJ1&&idJoueur==0)
				||(gameStopped&&!avantageJ1&&idJoueur==1))
			throwBall();
		
		RacketModel r;
		if(idJoueur==0) r=racketJ1;
		else r=racketJ2;
		
		BallModel b;
		if(idJoueur==0)//cherche minX
		{	
			int minX=0;
			for(int i=0; i<balls.size(); i++)
			{
				if(balls.get(minX).posX>balls.get(i).posX)
					minX=i;
			}
			b=balls.get(minX);
		}
		else//cherche maxX
		{
			int maxX=0;
			for(int i=0; i<balls.size(); i++)
			{
				if(balls.get(maxX).posX<balls.get(i).posX)
					maxX=i;
			}
			b=balls.get(maxX);
		}
		
		
		//si la barre est déjà centré, on ne bouge plus
		if(balls.get(0).posY>=r.posY+Constantes.RAQUETTE_HEIGHT/2-r.coefY
				&&balls.get(0).posY<=r.posY+Constantes.RAQUETTE_HEIGHT/2+r.coefY)
			return;
		
		//Déplace la balle
		if(b.posY<r.posY+Constantes.RAQUETTE_HEIGHT/2)
			updateRacket(idJoueur,Direction.NORD);
		else if(b.posY>r.posY+Constantes.RAQUETTE_HEIGHT/2)
			updateRacket(idJoueur,Direction.SUD);
	}
	
	/**
	 * Réinitialisation du terrain
	 */
	@Override
	public void reinit() {
		// TODO Auto-generated method stub
		
		
		for(int i=1; i<balls.size(); i++)
		{
			this.balls.get(i).arret();		
		}
		
		BallModel.nb_balle=0;
		BonusModel.nb_bonus=0;
		
		this.racketJ1.posY=Constantes.RAQUETTE_Y;
		this.racketJ2.posY=Constantes.RAQUETTE_Y;
		
		this.bonus=new Vector<BonusModel>();
		this.balls=new Vector<BallModel>();
		BallModel b;
		if(avantageJ1) b = new BallModel(this,1);
		else b = new BallModel(this,2);
			
			
		this.balls.add(b);
		
		
		this.actualSpeed=1.0;
		this.gameStopped=true;
		
		this.notifyReinit();
		this.notifyNewBalle(BallModel.nb_balle, b.posX, b.posY);

		
		
		
		
		
	}


	

	
}
