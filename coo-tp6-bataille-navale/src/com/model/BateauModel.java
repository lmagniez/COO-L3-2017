package com.model;

public class BateauModel {

	private GridModel grid;
	protected int debutX;
	protected int debutY;
	protected TypeBateau type;
	protected int tailleBateau;
	private int nbTouches;
	protected int id;
	
	protected Orientation o;
	
	
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
	
	@Override
	public String toString() {
		return "BateauModel [grid=" + grid + ", debutX=" + debutX + ", debutY=" + debutY + ", type=" + type
				+ ", tailleBateau=" + tailleBateau + ", nbTouches=" + nbTouches + ", id=" + id + ", o=" + o + "]";
	}

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

	public int getNbTouches() {
		return nbTouches;
	}

	public void setNbTouches(int nbTouches) {
		this.nbTouches = nbTouches;
	}
	
	
	
}
