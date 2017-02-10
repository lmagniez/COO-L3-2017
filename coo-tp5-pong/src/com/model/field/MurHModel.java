package com.model.field;

import com.model.Constantes;

/**
 * Modèle du mur horizontal
 * @author loick
 *
 */
public class MurHModel {

	protected int idMur;
	protected int posY;
	protected final int posX=Constantes.DIMENSION_X;
	protected final int width=Constantes.DIMENSION_X;
	protected int height=Constantes.MUR_HEIGHT;
	
	/**
	 * Constructeur
	 * @param idMur id du mur
	 */
	public MurHModel(int idMur)
	{
		this.idMur=idMur;
		if(idMur==0){//mur du haut
			posY=Constantes.MUR1_Y;
		}
		else if(idMur==1){
			posY=Constantes.MUR2_Y;
		}
	}
	
}
