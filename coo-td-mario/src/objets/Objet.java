package objets;

import java.awt.Image;

import javax.swing.ImageIcon;

import jeu.Main;

public class Objet {

	private int largeur;
	private int hauteur;
	private int posX;
	private int posY;
	
	protected Image imgObjet;
	protected ImageIcon icoObjet;
	
	public Objet(int largeur, int hauteur, int posX, int posY){
		this.largeur=largeur;
		this.hauteur=hauteur;
		this.posX=posX;
		this.posY=posY;
	}
	
	public void deplacement(){
		if(Main.scene.getxPos()>=0&&Main.scene.getxPos()<=4430)
			posX-=Main.scene.getDx();
	}

	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}
	
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
}
