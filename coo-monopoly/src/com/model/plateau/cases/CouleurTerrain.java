package com.model.plateau.cases;

import java.awt.Color;

/**
 * Enumération des différentes couleurs de terrain
 * @author loick
 *
 */
public enum CouleurTerrain {

	MARRON,BLEU_CLAIR,VIOLET,ORANGE,ROUGE,JAUNE,VERT,BLEU_FONCE;
	
	public static CouleurTerrain getColorFromString(String s) {
        switch(s) {
        case "MARRON":
            return CouleurTerrain.MARRON;
        case "BLEU_CLAIR":
        	return CouleurTerrain.BLEU_CLAIR;
        case "VIOLET":
        	return CouleurTerrain.VIOLET;
        case "ORANGE":
        	return CouleurTerrain.ORANGE;
        case "ROUGE":
        	return CouleurTerrain.ROUGE;
        case "JAUNE":
        	return CouleurTerrain.JAUNE;
        case "VERT":
        	return CouleurTerrain.VERT;
        case "BLEU_FONCE":
        	return CouleurTerrain.BLEU_FONCE;
        
        }
        return null;
    }
	
	public static Color getColorFromEnum(CouleurTerrain c) {
        switch(c) {
        case MARRON:
            return new Color(156, 93, 82);
        case BLEU_CLAIR:
        	return Color.cyan;
        case VIOLET:
        	return new Color(128,0,128);
        case ORANGE:
        	return Color.orange;
        case ROUGE:
        	return Color.red;
        case JAUNE:
        	return Color.YELLOW;
        case VERT:
        	return Color.GREEN;
        case BLEU_FONCE:
        	return Color.BLUE;
        
        }
        return null;
    }
	
	public static Color getForegroundColorFromEnum(CouleurTerrain c) {
        switch(c) {
        case MARRON:
            return Color.white;
        case BLEU_CLAIR:
        	return Color.black;
        case VIOLET:
        	return Color.white;
        case ORANGE:
        	return Color.white;
        case ROUGE:
        	return Color.white;
        case JAUNE:
        	return Color.black;
        case VERT:
        	return Color.white;
        case BLEU_FONCE:
        	return Color.white;
        
        }
        return null;
    }
	
}
