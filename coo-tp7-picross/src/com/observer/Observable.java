package com.observer;

import java.util.ArrayList;
import com.model.CaseValue;
import com.model.patternWin;
import com.vue.titre.Vue1;

/**
 * Interface des différents elements observables
 * @author loick
 *
 */
public interface Observable {
	public void addObserver(Observer obs);
	public void removeObserver();
	
	public void notifyFull();
	
	public void notifyChangeValue(int x, int y);
	public void notifyWin();
	public void notifyLose();
	
	public void notifyStart();
	
	public void notifyInfosGrilles(int nbGrille, int id[], String nom[], boolean reussite[], int[] nbLignes, int[] nbColonnes);
	public void notifyGrilleDetail(int id, String nom, String[] indicesLigne, String[] indicesColonne, boolean reussite);
	
	public void notifyReinitWindow();
	
	
}
