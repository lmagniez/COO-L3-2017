package com.observer;

import java.util.ArrayList;

import com.model.BateauModel;
import com.model.CaseValue;
import com.model.Orientation;
import com.model.TypeBateau;
import com.model.patternWin;
import com.vue.grid.CaseValueVue;
import com.vue.titre.Vue1;

/**
 * Interface des différents elements observables
 * @author loick
 *
 */
public interface Observable {
	public void addObserver(Observer obs);
	public void removeObserver();
	
	public void notifyTour();
	public void notifyBombJoueur(int x, int y, CaseValueVue v);
	public void notifyBombAdversaire(int x, int y, CaseValueVue v);
	
	public void notifyNewBateau(int x, int y, TypeBateau type, Orientation o, int idB);
	public void notifyMsgScore(String msg);
	
	public void notifyWinner();
	public void notifyLoser();
	
	public void notifyFull();
	
	
	public void notifyReinit();
	
}
