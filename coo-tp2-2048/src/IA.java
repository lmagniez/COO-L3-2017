import java.awt.event.KeyEvent;
import java.util.Random;

public class IA {
	
	Fenetre f;
	Thread thread;
	
	public IA(Fenetre f)
	{
		this.f=f;
		thread=new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					IA.this.deciderScore(f.lePanneau.g,f.lePanneau.score);
					f.revalidate();
					f.repaint();
					try{
						
						Thread.sleep(100);
					}catch(InterruptedException e){
						//
					}
				}
				
			}
		});
		thread.start();
	}
	
	public void deciderAlea(Grille g, Score score)
	{
		Random r=new Random();
		Direction dir;
		boolean has_moved=false, has_fused=false;
		
		int d=r.nextInt(2);
		
		if(d==0)
		{
			has_fused=g.fusion(Direction.EST,score,true);
        	has_moved=g.deplacerCase(Direction.EST,true);
		}
		else if(d==1)
		{
			has_fused=g.fusion(Direction.SUD,score,true);
        	has_moved=g.deplacerCase(Direction.SUD,true);
		}
		
		else if(d==3)
		{
			has_fused=g.fusion(Direction.OUEST,score,true);
        	has_moved=g.deplacerCase(Direction.OUEST,true);
		}
		else if(d==4)
		{
			has_fused=g.fusion(Direction.EST,score,true);
        	has_moved=g.deplacerCase(Direction.EST,true);
		}
		
		if(has_moved||has_fused)
        	g.apparitionCase();
		
	}
	
	public void deciderScore(Grille g, Score score)
	{
		Random r=new Random();
		Direction dir;
		boolean has_moved=false, has_fused=false,gameOver=false;
		
		Score sn=new Score();
		Score ss=new Score();
		Score se=new Score();
		Score so=new Score();
		
		g.fusion(Direction.NORD, sn, false);
		g.fusion(Direction.SUD, ss, false);
		g.fusion(Direction.EST, se, false);
		g.fusion(Direction.NORD, so, false);
		
		//System.out.println(sn.scoreValue+ " " + ss.scoreValue + " " +se.scoreValue+ " " + so.scoreValue);
		
		Direction d=null;
		//On cherche absolument à faire le score max
		//Priorité: 1-EST/SUD 2-OUEST 3-NORD
		
		//Ces 2 conditions impliquent que SUD et/ou EST est supérieur à 0
		if(ss.scoreValue>=se.scoreValue&&ss.scoreValue!=0)d=Direction.SUD;
		else if(ss.scoreValue<se.scoreValue)d=Direction.EST;
		//OUEST...
		else if(so.scoreValue>0)d=Direction.OUEST;
		//puis NORD en dernier recours
		else if(sn.scoreValue>0)d=Direction.NORD;
		
		//direction trouvée
		if(d!=null)
		{
			has_fused=g.fusion(d,score,true);
        	has_moved=g.deplacerCase(d,true);
		}
		
		//aucune fusion possible, aucun ajout de score, on teste le déplacement dans l'ordre SEON
		else
		{
			if(!g.deplacerCase(Direction.SUD,true))
				if(!g.deplacerCase(Direction.EST, true))
					if(!g.deplacerCase(Direction.OUEST, true))
						if(!g.deplacerCase(Direction.NORD, true))
						{	gameOver=true;
							this.f.afficherPanneau(this.f.lePanneau2);
							thread.interrupt();
							thread.stop();
							
						}
		}
		
		
		if(!gameOver)
			g.apparitionCase();
		
	}
	
}
