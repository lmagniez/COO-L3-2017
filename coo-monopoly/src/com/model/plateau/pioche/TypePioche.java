package com.model.plateau.pioche;

import com.model.plateau.cases.CouleurTerrain;

/**
 * Enum représentant les types de pioche
 * @author loick
 *
 */
public enum TypePioche {

	CHANCE,COMMUNAUTE;
	
	public static TypePioche getTypeFromString(String s) {
        switch(s) {
        case "CHANCE":
            return TypePioche.CHANCE;
        case "COMMUNAUTE":
        	return TypePioche.COMMUNAUTE;
        }
        return null;
    }
}
