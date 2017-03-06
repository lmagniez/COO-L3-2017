package com.observer;

import com.model.CaseValue;

/**
 * Interface des différents observer. Contient les différents updates.
 */

public interface Observer {

	public void updateReinit();

	public void updateChangeValue(int x, int y, CaseValue v);
	public void updateWin(CaseValue v);
	
	
	
}
