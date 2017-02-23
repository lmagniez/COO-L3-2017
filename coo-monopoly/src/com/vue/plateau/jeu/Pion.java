package com.vue.plateau.jeu;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.model.ConstantesVue;

public class Pion extends JLabel {

	protected int position;
	protected int idJoueur;
	protected int idIcon;
	protected Plateau p;
	
	protected Icon image;
	
	protected int posX,posY,hX,hY;
	
	
	public Pion(Plateau p, int position, int idJoueur, int idIcon){
		this.p=p;
		this.position=position;
		this.idJoueur=idJoueur;
		this.idIcon=idIcon;
		
		this.hX=40;
		this.hY=40;
		
		this.setText("test");
		
		Object items[]={
				transform(new ImageIcon("./Sprites/pion/1.png"),hX,hY),
				transform(new ImageIcon("./Sprites/pion/2.png"),hX,hY),
				transform(new ImageIcon("./Sprites/pion/3.png"),hX,hY),
				transform(new ImageIcon("./Sprites/pion/4.png"),hX,hY),
				transform(new ImageIcon("./Sprites/pion/5.png"),hX,hY),
				transform(new ImageIcon("./Sprites/pion/6.png"),hX,hY),
				transform(new ImageIcon("./Sprites/pion/7.png"),hX,hY),
				transform(new ImageIcon("./Sprites/pion/8.png"),hX,hY)
		};
		
		image=(Icon) items[idIcon];
		this.setIcon(image);
		
		
		changePosition(position);
		
	}
	
	public void changePosition(int position){
		
		this.position=position;
		posX=this.p.getCases()[position].posX;
		posY=this.p.getCases()[position].posY;
		
		if(idJoueur==0){
			
		}
		if(idJoueur==1){
			posY=posY+this.p.getCases()[position].hY*1/3;
		}
		if(idJoueur==2){
			posX=posX+this.p.getCases()[position].hX*1/2;
			
		}
		if(idJoueur==3){
			posY=posY+this.p.getCases()[position].hY*1/3;
			posX=posX+this.p.getCases()[position].hX*1/2;
		}
		
		//this.posX=50;
		//this.posY=50;
		
		this.setLocation(posX,posY);
		this.setSize(hX,hY);
		
		
		
	}
	
	public ImageIcon transform (ImageIcon img, int hx, int hy)
	{
		Image image=img.getImage();
		Image newImg= image.getScaledInstance(hx, hy, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newImg);
		
	}
	
}
