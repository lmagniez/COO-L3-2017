package com.model;

/**
 * Classe correspondant au modèle d'un bateau
 * @author loick
 *
 */
public class BateauModel {

	private GridModel grid;
	protected int debutX;
	protected int debutY;
	protected TypeBateau type;
	protected int tailleBateau;
	private int nbVie;
	protected int id;
	
	protected Orientation o;
	
	
	/**
	 * Constructeur d'un bateau
	 * @param grid modele de la grille
	 * @param id id du bateau
	 * @param debutX debut X du bateau
	 * @param debutY debut Y du bateau
	 * @param type type du bateau
	 * @param d orientation du bateau
	 */
	public BateauModel(GridModel grid, int id, int debutX, int debutY, TypeBateau type, Orientation d) {
		this.grid = grid;
		this.id=id;
		this.debutX = debutX;
		this.debutY = debutY;
		this.type=type;
		this.tailleBateau=TypeBateau.fromType(type);
		this.setNbVie(tailleBateau);
		this.o = d;
		placerBateau();
		
	}
	
	/**
	 * To string du bateau
	 */
	@Override
	public String toString() {
		return "BateauModel [grid=" + grid + ", debutX=" + debutX + ", debutY=" + debutY + ", type=" + type
				+ ", tailleBateau=" + tailleBateau + ", nbTouches=" + getNbVie() + ", id=" + id + ", o=" + o + "]";
	}

	/**
	 * Place un bateau sur la grille en fonction de sa position de départ sa taille et son orientation
	 * Set chaque case avec une valeur et un id correspondant au bateau
	 */
	public void placerBateau(){
		
		int tailleBateau=getType().fromType(this.getType());
		if(o==Orientation.VERTICAL)
			for(int x=debutX; x<debutX+tailleBateau;x++){
				grid.getCasesAdversaire()[x][debutY].setV(CaseValue.NONE);
				grid.getCasesAdversaire()[x][debutY].setIdBateau(id);
			}
		else if(o==Orientation.HORIZONTAL)
			for(int y=debutY; y<debutY+tailleBateau;y++){
				grid.getCasesAdversaire()[debutX][y].setV(CaseValue.NONE);
				grid.getCasesAdversaire()[debutX][y].setIdBateau(id);
			}
		
	}

	public TypeBateau getType() {
		return type;
	}

	public void setType(TypeBateau type) {
		this.type = type;
	}

	public int getNbVie() {
		return nbVie;
	}

	public void setNbVie(int nbVie) {
		this.nbVie = nbVie;
	}
	
	
	
}
