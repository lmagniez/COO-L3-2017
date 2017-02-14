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
	
	public void notifyTour(int tour);
	public void notifyNewBombJ1(int x, int y, CaseValue v);
	public void notifyNewBombJ2(int x, int y, CaseValue v);
	
	public void notifyWinner(int tour);
	public void notifyFull();
	
	
	public void notifyReinit();
	
}
