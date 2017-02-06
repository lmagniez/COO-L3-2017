package com.model.grid;

/**
 * Enum correspondant aux différentes valeurs que peut avoir une case
 * @author loick
 *
 */
public enum CaseValue {
	J1,J2,WIN,NONE;
	
	/**
	 * Accéder à la CaseValue en fonction d'un entier
	 * @param x entier 
	 * @return CaseValue associé à l'entier
	 */
	public static CaseValue fromInteger(int x) {
        switch(x) {
        case 1:
            return J1;
        case 0:
            return J2;
        }
        return null;
    }
	
}
