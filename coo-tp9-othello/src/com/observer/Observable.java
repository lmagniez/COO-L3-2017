package com.observer;

import com.model.CaseValue;
/**
 * Interface des différents elements observables
 * @author loick
 *
 */
public interface Observable {
	public void addObserver(Observer obs);
	public void removeObserver();
	
	public void notifyChangeValue(int x, int y, CaseValue v);
	public void notifyWin(CaseValue v);
	
	public void notifyReinit();
	
	
}
