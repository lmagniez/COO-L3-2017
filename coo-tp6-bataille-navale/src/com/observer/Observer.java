package com.observer;

import java.util.ArrayList;

import com.model.CaseValue;
import com.model.Orientation;
import com.model.TypeBateau;
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
	public void updateBateau(int x, int y, TypeBateau type, Orientation o, int idB);
	
	public void updateMsgScore(String msg);
	
	public void updateWinner();
	public void updateLoser();
	
	
	public void updateReinit();

	public void updateFull();
}
