package com.model;


/**
 * Enum correspondant aux différentes valeurs que peut avoir une case
 * @author loick
 *
 */
public enum CaseValue {
	NONE,TOUCHE;
	
	/**
	 * Accéder à la CaseValue en fonction d'un e,ntier
	 * @param x entier 
	 * @return CaseValue associé à l'entier
	 */
	
	public static CaseValue fromInteger(int x) {
        switch(x) {
        case 0:
            return NONE;
        case 1:
        	return TOUCHE;	
        }
        
        return null;
    }
	
}

