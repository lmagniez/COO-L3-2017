package personnages;

import java.awt.Image;

import javax.swing.ImageIcon;

import objets.Objet;

public class Champ extends Personnage implements Runnable{

	private int dxChamp;
	Image imgChamp;
	ImageIcon icoChamp;
	
	public Champ(int x, int y){
		super(x,y,27,30);
		this.versDroite=false;
		this.marche=true;
		dxChamp=-1;
		this.icoChamp=new ImageIcon(getClass().getResource("./images/champArretGauche.png"));
		this.imgChamp=this.icoChamp.getImage();
		
		Thread chronoTortue = new Thread(this);
		chronoTortue.start();
	}
	
	public void bouge(){
		if(this.vivant){
			if(this.versDroite)
				dxChamp=1;
			else
				dxChamp=-1;
			this.setX(this.getX() + dxChamp);
		}
	}
	
	public Image meurt(){
		
		Image img;
		ImageIcon icon;
		
		dxChamp=0;
		if(this.versDroite)
			icon=new ImageIcon(getClass().getResource("./images/champEcraseGauche.png"));
		else
			icon=new ImageIcon(getClass().getResource("./images/champEcraseDroite.png"));
		img=this.icoChamp.getImage();
	
		return img;
	}
	
	public void contact(Objet o){
		if(this.contactAvant(o)&&this.versDroite)
			this.dxChamp=-1;
		if(this.contactArriere(o)&&!this.versDroite)
			this.dxChamp=1;
		
	}
	
	public void contact(Personnage p){
		if(this.contactAvant(p)&&this.versDroite)
			this.dxChamp=-1;
		if(this.contactArriere(p)&&!this.versDroite)
			this.dxChamp=1;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
	
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true){
			bouge();
		}
	}

}
