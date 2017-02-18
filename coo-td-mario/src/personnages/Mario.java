package personnages;

import java.awt.Image;

import javax.swing.ImageIcon;

import jeu.Main;
import objets.Piece;

public class Mario extends Personnage {

	private Image imgMario;
	private ImageIcon icoMario;
	private boolean saut;
	private int compteurSaut;
	private int compteurMort;
	
	
	public Mario(int x, int y){
		super(x,y,28,50);
		saut=false;
		marche=false;
		this.versDroite=true;
		
		this.icoMario=new ImageIcon(getClass().getResource("./images/marioArretDroite.png"));
		this.imgMario=this.icoMario.getImage();
		
		compteurMort=0;
		compteurSaut=0;
		
		
	}
	
	public Image meurt(){
		Image img;
		ImageIcon icon;
		
		compteurMort+=1;
		if(compteurMort>=100){
			icon=new ImageIcon(getClass().getResource("./images/marioMeurt.png"));
			this.setY(this.getY()-1);
		}
		else{
			icon=new ImageIcon(getClass().getResource("./images/boom.png"));
		}
		img=this.icoMario.getImage();
		return img;
		
	}

	public void contact(Personnage p){
		if(this.contactAvant(p)||this.contactArriere(p)){
			this.meurt();
			this.marche=false;
		}
	}
	
	public boolean contact(Piece p){
		return(this.contactAvant(p)||this.contactArriere(p)||this.contactDessus(p)||this.contactDessous(p));
	}
	
	public Image marche(String nom, int frequence){
		if(!marche&&versDroite){
			return new ImageIcon(getClass().getResource("./images/"+nom+"ArretDroite.png")).getImage();
		}
		if(!marche&&!versDroite){
			return new ImageIcon(getClass().getResource("./images/"+nom+"ArretGauche.png")).getImage();
		}
		if(marche){
			this.compteur+=1;
			if(this.compteur/frequence==0){
				if(this.versDroite&&(Main.scene.getxPos()<=0||Main.scene.getxPos()>4430))
					return new ImageIcon(getClass().getResource("./images/"+nom+"MarcheDroite.png")).getImage();
				else if(!this.versDroite&&(Main.scene.getxPos()<=0||Main.scene.getxPos()>4430))
					return new ImageIcon(getClass().getResource("./images/"+nom+"MarcheGauche.png")).getImage();
				
			}
		}
		if(this.compteur==frequence*2)
			compteur=0;
		
		return null;
		
	}
	
	
	
}
