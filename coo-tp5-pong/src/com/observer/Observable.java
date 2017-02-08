package com.observer;

import java.util.ArrayList;
import com.vue.titre.Vue1;

/**
 * Interface des différents elements observables
 * @author loick
 *
 */
public interface Observable {
	public void addObserver(Observer obs);
	public void removeObserver();
	
	public void notifyNewPosBall(int idBalle, int x, int y);
	public void notifyNewPosRacket(int idRacket, int posX, int posY);
	public void notifyInitMurH(int idMur, int posX, int posY);
	
	public void notifyNewBalle(int idBalle, int x, int y);
	public void notifyNewBonus(int idB, int x, int y);
	public void notifyEraseBonus(int idB);
	
	public void notifyScore(int sJ1, int sJ2);
	
	
	public void notifyReinit();
	
}
