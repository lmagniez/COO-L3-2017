package com.model;

import java.awt.Color;

/**
 * Enum correspondant aux différentes valeurs que peut avoir une case
 * @author loick
 *
 */
public enum CaseValue {
	CHECKED,UNCHECKED;
	
	/**
	 * Accéder à la CaseValue en fonction d'un entier
	 * @param x entier 
	 * @return CaseValue associé à l'entier
	 */
	public static CaseValue fromInteger(int x) {
        switch(x) {
        case 0:
            return CHECKED;
        case 1:
            return UNCHECKED;
        }
        return null;
    }
	
	/**
	 * Récupérer une couleur en fonction de la valeur
	 * @param c valeur de la case
	 * @return couleur associé à la valeur
	 */
	public static Color getColorFromValue(CaseValue c){
		switch(c){
			case CHECKED:
				return Color.black;
			case UNCHECKED:
				return Color.white;
		}
		return null;
	}
	
}
