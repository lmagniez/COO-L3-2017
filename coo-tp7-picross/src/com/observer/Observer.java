package com.observer;

import java.util.ArrayList;
import com.model.CaseValue;
import com.model.patternWin;
import com.vue.grid.VueGrid;

/**
 * Interface des différents observer. Contient les différents updates.
 */

public interface Observer {
	//public void update(String str); // Le meme type que la methode notify	
	public void updateReinit();
	
	
	public void updateChangeValue(int x, int y);
	public void updateWin();
	public void updateLose();
	
	public void updateInfosGrilles(int nbGrille, int id[], String nom[], boolean reussite[]);
	public void updateGrilleDetail(int id, String nom, String[] indicesLigne, String[] indicesColonne, boolean reussite);
	
	
}