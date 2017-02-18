package objets;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bloc extends Objet{

	public Bloc(int x, int y){
		super(30,30,x,y);
		this.icoObjet=new ImageIcon(getClass().getResource("./images/bloc.png"));
		this.imgObjet=this.icoObjet.getImage();
	}
	
}
