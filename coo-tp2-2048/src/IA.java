import java.awt.event.KeyEvent;
import java.util.Random;

public class IA {
	
	Fenetre f;
	
	public IA(Fenetre f)
	{
		this.f=f;
		Thread thread=new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					System.out.println(f.lePanneau.g);  
					System.out.println(f.lePanneau.score);
					IA.this.deciderAlea(f.lePanneau.g,f.lePanneau.score);
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
		
		int d=r.nextInt(4);
		
		if(d==1)
		{
			has_fused=g.fusion(Direction.NORD,score,true);
        	has_moved=g.deplacerCase(Direction.NORD,true);
		}
		else if(d==2)
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
}
