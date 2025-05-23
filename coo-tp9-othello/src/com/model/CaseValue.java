package com.model;

import java.awt.Color;

/**
 * Enum correspondant aux différentes valeurs que peut avoir une case
 * @author loick
 *
 */
public enum CaseValue {
	EMPTY,J1,J2;
	
	/**
	 * Accéder à la CaseValue en fonction d'un entier
	 * @param x entier 
	 * @return CaseValue associé à l'entier
	 */
	public static CaseValue fromInteger(int x) {
        switch(x) {
        case 0:
            return EMPTY;
        case 1:
            return J1;
        case 2:
            return J2;
            
        }
        return null;
    }
	
	
	/**
	 * Retourne une CaseValue en fonction d'une string (utilisé dans le parsing XML)
	 * @param s chaine
	 * @return CaseValue correspondante
	 */
	public static CaseValue fromString(String s) {
        if(s.equals("EMPTY"))
        	return EMPTY;
        if(s.equals("J1"))
        	return J1;
        if(s.equals("J2"))
        	return J2;
        return null;
    }
	
	
	/**
	 * Récupérer une couleur en fonction de la valeur
	 * @param c valeur de la case
	 * @return couleur associé à la valeur
	 */
	public static Color getColorFromValue(CaseValue c){
		switch(c){
			case EMPTY:
				return new Color(46,115,58);
			case J1:
				return Color.white;
			case J2:
				return Color.black;
				
		}
		return null;
	}
	
}
