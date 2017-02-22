package com.vue.plateau;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.model.ConstantesVue;
import com.vue.plateau.RotatedIcon.Rotate;

public class Case extends JLabel {

	protected Plateau p;
	private Icon image;
	protected int idCase;
	protected int position;
	protected int posX;
	protected int posY;
	protected int hX,hY;
	protected int nbMaisons;
	
	protected JLabel maison1;
	protected JLabel maison2;
	protected JLabel maison3;
	protected JLabel maison4;
	protected JLabel hotel;
	
	
	
	
	public Case(Plateau p, int idCase, int position){
		
		this.p=p;
		this.setBorder(BorderFactory.createLineBorder(Color.black,1));
		
		
		//hX=ConstantesVue.CASE_WIDTH/3+3;
		//hY=ConstantesVue.CASE_HEIGHT/3+3;
		hX=ConstantesVue.CASE_WIDTH/3+5;
		hY=ConstantesVue.CASE_HEIGHT/3+5;
		
		if(position==0||position==10||position==20||position==30){
			hX=ConstantesVue.CASE_SPE_WIDTH/3+3;
			hY=ConstantesVue.CASE_SPE_HEIGHT/3+3;
		}
		String path="";
		this.posX=0;
		this.posY=0;
		this.nbMaisons=0;
		
		
		
		//GO
		if(position==0){
			path="Sprites/pieces/go.png";
			this.posX=ConstantesVue.DIMENSION_PLATEAU_X-(ConstantesVue.CASE_SPE_WIDTH/3)-2;
			this.posY=ConstantesVue.DIMENSION_PLATEAU_Y-(ConstantesVue.CASE_SPE_HEIGHT/3)-2;
		}
		//PRISON
		else if(position==10){
			path="Sprites/pieces/prison.png";
			this.posX=0;
			this.posY=ConstantesVue.DIMENSION_PLATEAU_Y-(ConstantesVue.CASE_SPE_HEIGHT/3)-2;
		}
		//PARK
		else if(position==20){
			path="Sprites/pieces/park.png";
			this.posX=0;
			this.posY=0;
		}
		//POLICE
		else if(position==30){
			path="Sprites/pieces/police.png";
			this.posX=ConstantesVue.DIMENSION_PLATEAU_X-(ConstantesVue.CASE_SPE_WIDTH/3);
			this.posY=0;
		}
		
		
		//CASE BAS
		if(position>0&&position<10){
			path="Sprites/pieces/BOTTOM/"+idCase+".png";
			this.posX=ConstantesVue.DIMENSION_PLATEAU_X-(hX)*(position)-(ConstantesVue.CASE_SPE_WIDTH/3-1);
			this.posY=ConstantesVue.DIMENSION_PLATEAU_Y-(hY)-2;
		}
		
		//CASE GAUCHE
		else if(position>10&&position<20){
			path="Sprites/pieces/LEFT/"+idCase+".png";
			//image=new RotatedIcon(image,Rotate.DOWN);
			int tmp=hY;
			this.hY=hX;
			this.hX=tmp;
			
			this.posY=ConstantesVue.DIMENSION_PLATEAU_Y-(hY)
			*(position%10)-(ConstantesVue.CASE_SPE_HEIGHT/3);
			
			//this.posY=ConstantesVue.DIMENSION_PLATEAU_Y-(ConstantesVue.CASE_WIDTH/3+4)
			//		*(position%10)-(ConstantesVue.CASE_SPE_HEIGHT*5/12-6);
			this.posX=2;
		}
		
		
		
		//CASE HAUT
		else if(position>20&&position<30){
			path="Sprites/pieces/UP/"+idCase+".png";
			//this.posX=(ConstantesVue.CASE_WIDTH/3)*(position%20)+(ConstantesVue.CASE_SPE_WIDTH/3);;
			//this.posY=0;
			
			
			this.posX=(hX)*(position%21)+(ConstantesVue.CASE_SPE_WIDTH/3-1);
			this.posY=2;
			
			
		}
		
		
		
		//CASE DROITE 
		else if(position>30&&position<40){
			int tmp=hY;
			this.hY=hX;
			this.hX=tmp;
			path="Sprites/pieces/RIGHT/"+idCase+".png";
			this.posY=(ConstantesVue.CASE_WIDTH/3+4)
					*(position%31)+(ConstantesVue.CASE_SPE_HEIGHT/3);;
			this.posX=ConstantesVue.DIMENSION_PLATEAU_X-hX-2;
			
		}
		image=new ImageIcon(path);
		image = transform((ImageIcon) image,hX,hY);
		this.setIcon(image);
	    this.idCase=idCase;
	    this.position=position;
	    
	}
	
	public void addMaison(){
		this.nbMaisons++;
		this.p.addMaison(this.position, this.nbMaisons);
	}
	
	public void removeMaison(){
		this.nbMaisons--;
		this.p.removeMaison(this.position, this.nbMaisons);
		
		
	}
	
	
	
	public ImageIcon transform (ImageIcon img, int hx, int hy)
	{
		Image image=img.getImage();
		Image newImg= image.getScaledInstance(hx, hy, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newImg);
	}
	
	
	

}
