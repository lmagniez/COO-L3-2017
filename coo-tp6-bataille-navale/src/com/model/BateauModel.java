package com.model;

public class BateauModel {

	private int idJoueur;
	private GridModel grid;
	private int debutX;
	private int debutY;
	private TypeBateau type;
	private int tailleBateau;
	private int nbTouches;
	private int id;
	
	private Orientation o;
	
	
	public BateauModel(GridModel grid, int id, int debutX, int debutY, TypeBateau type, Orientation d) {
		this.grid = grid;
		this.id=id;
		this.debutX = debutX;
		this.debutY = debutY;
		this.type=type;
		this.tailleBateau=TypeBateau.fromType(type);
		this.setNbTouches(0);
		this.o = d;
		placerBateau();
	}
	
	public void placerBateau(){
		
		int tailleBateau=getType().fromType(this.getType());
		if(o==Orientation.VERTICAL)
			for(int x=debutX; x<debutX+tailleBateau;x++){
				grid.cases[x][debutY].v=CaseValue.NONE;
				grid.cases[x][debutY].idBateau=id;
			}
		else if(o==Orientation.HORIZONTAL)
			for(int y=debutY; y<debutY+tailleBateau;y++){
				grid.cases[debutX][y].v=CaseValue.NONE;
				grid.cases[debutX][y].idBateau=id;;
			}
		
	}

	public TypeBateau getType() {
		return type;
	}

	public void setType(TypeBateau type) {
		this.type = type;
	}

	public int getNbTouches() {
		return nbTouches;
	}

	public void setNbTouches(int nbTouches) {
		this.nbTouches = nbTouches;
	}
	
	
	
}
