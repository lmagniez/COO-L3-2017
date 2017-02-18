package com.vue.grid;

import java.awt.Color;

/**
 * Enum correspondant aux différentes valeurs que peut avoir une case
 * @author loick
 *
 */
public enum CaseValueVue {
	NONE,TOUCHE,PLOUF;
	
	/**
	 * Accéder à la CaseValue en fonction d'un e,ntier
	 * @param x entier 
	 * @return CaseValue associé à l'entier
	 */
	
	public static CaseValueVue fromInteger(int x) {
        switch(x) {
        case 0:
            return NONE;
        case 1:
        	return TOUCHE;
        case 2:
            return PLOUF;
        }
        
        return null;
    }
	
	/**
	 * Acceder à la couleur en fonction du type
	 * @param v Type
	 * @return Couleur associée
	 */
	public static Color fromType(CaseValueVue v) {
        switch(v) {
        case NONE:
            return Color.BLUE;
        case TOUCHE:
        	return Color.RED;
        case PLOUF:
            return Color.white;
        }
        
        return null;
    }
	
}