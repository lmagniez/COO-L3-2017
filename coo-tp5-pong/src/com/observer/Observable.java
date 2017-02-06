package com.observer;

import java.util.ArrayList;

import com.model.grid.CaseValue;
import com.model.grid.patternWin;
import com.vue.titre.Vue1;

/**
 * Interface des différents elements observables
 * @author loick
 *
 */
public interface Observable {
	public void addObserver(Observer obs);
	public void removeObserver();
	
	public void notifyNewPosBall(int idBalle, int x, int y, int diam);
	public void notifyNewPosRacket(int idRacket, int posX, int posY, int width, int height);
	public void notifyInitMurH(int idMur, int posX, int posY, int width, int height);
	
	
	
	public void notifyReinit();
	
}
