package com.vue.grid;

import com.model.CaseValue;
import com.model.GridModel;
import com.model.Orientation;
import com.model.TypeBateau;

public class Bateau {

	private VueGrid vue;
	private int debutX;
	private int debutY;
	private TypeBateau type;
	private Orientation orientation;
	private int tailleBateau;
	private int nbTouches;
	private int id;
	
	protected int posX,posY;
	protected int hX,hY;
	
	public Bateau(int debutX,int debutY, TypeBateau t, Orientation o){
		this.debutX=debutX;
		this.debutY=debutY;
		this.type=t;
		this.orientation=o;
		tailleBateau=TypeBateau.fromType(t);
		
		placerBateau();
	}
	
	public void placerBateau(){
		
		if(orientation==Orientation.VERTICAL){
			this.posX=Case.DIAMETRE_CASE*debutY;
			this.posY=Case.DIAMETRE_CASE*debutX;
			this.hX=Case.DIAMETRE_CASE;
			this.hY=Case.DIAMETRE_CASE*tailleBateau;
							
		}
		if(orientation==Orientation.HORIZONTAL){
			this.posX=Case.DIAMETRE_CASE*debutY;
			this.posY=Case.DIAMETRE_CASE*debutX;
			this.hX=Case.DIAMETRE_CASE*tailleBateau;
			this.hY=Case.DIAMETRE_CASE;
							
		}
	}
	
	
}
