package com.model.plateau.cases;

import com.model.plateau.JoueurModel;
import com.model.plateau.PlateauModel;
import com.model.plateau.pioche.TypePioche;

/**
 * Cases changeant l'argent du joueur
 * @author loick
 *
 */
public class CommunauteChanceModel extends CaseModel{

	private PlateauModel p;
	private TypePioche type;

	/**
	 * Constructeur
	 * @param p modèle du plateau
	 * @param idCase id de la case
	 * @param position position de la case
	 * @param nom nom de la case
	 * @param type type de pioche
	 */
	public CommunauteChanceModel(PlateauModel p, int idCase, int position, String nom, TypePioche type)
	{
		this.p=p;
		this.setNom(nom);
		this.idCase=idCase;
		this.setPosition(position);
		this.nom=nom;
		this.setType(type);
	}
	
	@Override
	public void action(JoueurModel j) {
		
		
		if(getType()==TypePioche.CHANCE)
			p.getPiocheChance().piocherCarte(j);
		if(getType()==TypePioche.COMMUNAUTE)
			p.getPiocheCommunaute().piocherCarte(j);
			
		//this.p.getModel().tourSuivant();
		
	}

	public TypePioche getType() {
		return type;
	}

	public void setType(TypePioche type) {
		this.type = type;
	}

	
}