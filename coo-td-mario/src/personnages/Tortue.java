package personnages;

import java.awt.Image;

import javax.swing.ImageIcon;

import objets.Objet;

public class Tortue extends Personnage implements Runnable{

	
	private int dxTortue;
	Image imgTortue;
	ImageIcon icoTortue;
	
	public Tortue(int x, int y){
		super(x,y,27,30);
		this.versDroite=false;
		this.marche=true;
		dxTortue=-1;
		this.icoTortue=new ImageIcon(getClass().getResource("./images/tortueArretGauche.png"));
		this.imgTortue=this.icoTortue.getImage();
		
		Thread chronoTortue = new Thread(this);
		chronoTortue.start();
	}
	
	public void bouge(){
		if(this.vivant){
			if(this.versDroite)
				dxTortue=1;
			else
				dxTortue=-1;
			this.setX(this.getX() + dxTortue);
		}
	}
	
	public Image meurt(){
		
		Image img;
		ImageIcon icon;
		
		dxTortue=0;
		icon=new ImageIcon(getClass().getResource("./images/tortueFermee.png"));
		img=this.icoTortue.getImage();
		return img;
		
	}
	
	public void contact(Objet o){
		if(this.contactAvant(o)&&this.versDroite)
			this.dxTortue=-1;
		if(this.contactArriere(o)&&!this.versDroite)
			this.dxTortue=1;
		
	}
	
	public void contact(Personnage p){
		if(this.contactAvant(p)&&this.versDroite)
			this.dxTortue=-1;
		if(this.contactArriere(p)&&!this.versDroite)
			this.dxTortue=1;
		
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
