package com.model;

import com.model.field.FieldModel;

public abstract class Constantes {

	//field
	public static final int DIMENSION_X=800;
	public static final int DIMENSION_Y=600;
	
	//balle
	public static final int DIAMETRE_BALLE=20;
	public static final int BALLE_COEFY=3;
	public static final int BALLE_COEFX=3;
	public static final int BALLE_Y=DIMENSION_Y/2;
	public static final int BALLE_X_J1=DIMENSION_X*3/10;
	public static final int BALLE_X_J2=DIMENSION_X*7/10;
	
	//raquette

	public static final int RAQUETTE_HEIGHT=200;
	public static final int RAQUETTE_WIDTH=20;
	public static final int RAQUETTE_X_J1=DIMENSION_X*1/12;
	public static final int RAQUETTE_X_J2=DIMENSION_X*11/12;
	public static final int RAQUETTE_Y=(DIMENSION_Y/2)-RAQUETTE_HEIGHT/2;
	public static final int RAQUETTE_VITESSE=3;
	
	
	//mur
	public static final int MUR_WIDTH=DIMENSION_X;
	public static final int MUR_HEIGHT=50;
	public static final int MUR_X=0;
	public static final int MUR1_Y=0;
	public static final int MUR2_Y=DIMENSION_Y-MUR_HEIGHT;
	
	//bonus
	public static final int DIAMETRE_BONUS=70;
	public static final int SCORE_GAGNANT=7;
	
	
	
	
	
}
