package personnages;

import java.awt.Image;

import javax.swing.ImageIcon;

import objets.Objet;

public class Personnage {

	private int largeur, hauteur;
	private int x,y;
	public int compteur;
	public int compteurMort;
	protected boolean marche, versDroite, vivant;
	
	public Personnage(int x, int y, int largeur, int hauteur){
		this.largeur=largeur;
		this.hauteur=hauteur;
		this.setX(x);
		this.setY(y);
		this.compteur=0;
		this.compteurMort=0;
		this.vivant=true;
		
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
				if(this.versDroite)
					return new ImageIcon(getClass().getResource("./images/"+nom+"MarcheDroite.png")).getImage();
				else
					return new ImageIcon(getClass().getResource("./images/"+nom+"MarcheGauche.png")).getImage();
				
			}
		}
		if(this.compteur==frequence*2)
			compteur=0;
		
		return null;
		
	}
	
	public boolean contactAvant(Objet o){
		
		return ((this.getX()+this.largeur)>=o.getPosX());
		
	}
	public boolean contactArriere(Objet o){
		
		return (this.getX()<=(o.getPosX()+o.getLargeur()));
	}
	
	public boolean contactDessous(Objet o){
		
		return ((this.getY()+this.hauteur)>=o.getPosY());
	}
	
	public boolean contactDessus(Objet o){
		
		return ((this.getY())<=(o.getPosY()+o.getHauteur()));
	}
	
	public boolean proche(Objet o){
		
		return (this.getX()<=(o.getPosX()+o.getLargeur())-10)||
				((this.getX()+this.largeur)>=o.getPosX()+10);
	}
	
	
	public boolean contactAvant(Personnage p){
		
		return ((this.getX()+this.largeur)>=p.getX());
	}
	
	public boolean contactArriere(Personnage p){
		
		return (this.getX()<=(p.getX()+p.hauteur));
	}
	
	public boolean contactDessous(Personnage p){
		
		return ((this.getY()+this.hauteur)>=p.getY());
	}
	
	public boolean contactDessus(Personnage p){
		
		return ((this.getY())<=(p.getY()+p.hauteur));
	}
	
	public boolean proche(Personnage p){
		
		return (this.getX()<=(p.getX()+p.largeur)-10)||
				((this.getX()+this.largeur)>=p.getX()+10);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
	
}
