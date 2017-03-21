package com.model;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSpinner;

/**
 * Ensemble des paramètres de la partie (modifié dans les écrans d'options)
 * @author loick
 *
 */
public class ConstantesParam {

	public static int NB_JOUEURS=4;
	public static int SOMME_DEPART=50000;
	public static boolean[] IS_IA={false,false,false,false};
	
	public static int[] ID_ICONES;
	public static String[] NAMES;
	
	public static int NB_MAISONS=20;
	public static int NB_PROPRIETES=20;
	
	public static boolean TOUR_ENABLED=false;
	public static int NB_TOUR=-1;
	
	public static boolean POSITION_ALEA_ENABLED=false;
	public static boolean TROIS_DES_ENABLED=false;
	public static boolean SOMME_PRISON_ENABLED=false;
	public static boolean CHOIX_DES_ENABLED=false;
	public static boolean SUICIDE_ENABLED=false;
	public static boolean ENCHERE_ENABLED=false;
	public static boolean TIMER_VALEUR_ENABLED=false;
	public static boolean CASE_ALEA_ENABLED=false;
	public static boolean CASE_MASQUE_ENABLED=false;
	
	public static double TAUX_ACHAT=1.0;
	public static double TAUX_INTERET=1.0;
	public static boolean EGALISATION_ENABLED=false;
	
}
