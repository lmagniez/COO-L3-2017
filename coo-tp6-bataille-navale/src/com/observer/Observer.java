package com.observer;

import java.util.ArrayList;
import com.model.CaseValue;
import com.model.patternWin;
import com.vue.grid.CaseValueVue;

/**
 * Interface des différents observer. Contient les différents updates.
 */

public interface Observer {
	//public void update(String str); // Le meme type que la methode notify
	public void updateTour();
	
	public void updateCaseJoueur(int x, int y, CaseValueVue v);
	public void updateCaseAdversaire(int x, int y, CaseValueVue v);
	
	public void updateWinner();
	public void updateLoser();
	
	
	public void updateReinit();

	public void updateFull();
}
