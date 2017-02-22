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

	public CommunauteChanceModel(PlateauModel p, int idCase, int position, String nom, TypePioche type)
	{
		this.p=p;
		this.setNom(nom);
		this.idCase=idCase;
		this.setPosition(position);
		this.nom=nom;
		this.type=type;
	}
	
	@Override
	public void action(JoueurModel j) {
		
		if(type==TypePioche.CHANCE)
			p.getPiocheChance().piocherCarte(j);
		if(type==TypePioche.COMMUNAUTE)
			p.getPiocheCommunaute().piocherCarte(j);
			
		
	}

	
}