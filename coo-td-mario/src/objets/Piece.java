package objets;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Piece extends Objet implements Runnable{

	private int compteur;
	
	public Piece(int x, int y){
		super(30,30,x,y);
		this.compteur=0;
		this.icoObjet=new ImageIcon(getClass().getResource("./images/piece1.png"));
		this.imgObjet=this.icoObjet.getImage();
	}
	
	public Image bouge(){
		Image img;
		ImageIcon icon;
		
		this.compteur+=1;
		if(compteur==200)
			compteur=0;
		
		if(this.compteur/100==0){
			icon=new ImageIcon(getClass().getResource("./images/piece1.png"));
			img=this.icoObjet.getImage();
		}
		else{
			icon=new ImageIcon(getClass().getResource("./images/piece2.png"));
			img=this.icoObjet.getImage();
		}
		
		return img;
		
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
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			this.imgObjet=bouge();
		}
		
	}

}
