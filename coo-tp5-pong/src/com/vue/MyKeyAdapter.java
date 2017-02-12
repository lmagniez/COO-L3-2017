package com.vue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.controler.AbstractControler;
import com.controler.RacketControler;

/**
 * KeyAdapter gérant les actions claviers simultanées
 * @author loick
 *
 */
public class MyKeyAdapter extends KeyAdapter{

	//this.addKeyListener(new MyKeyAdapter());
	
	protected AbstractControler controler;
	
	
	public MyKeyAdapter(AbstractControler controlerRacket){
		super();
		this.controler=controlerRacket;
	}
	
	private boolean presser = false;

	public void keyPressed(KeyEvent e) {
		
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			controler.setUp(1);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			controler.setDown(1);
		}
		
		
		if (e.getKeyCode() == KeyEvent.VK_Z) {
			controler.setUp(0);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_S) {
			controler.setDown(0);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			controler.setEnter();
		}
		
		
	}

	public void keyReleased(KeyEvent e) {
		
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			controler.setReleasedUp(1);
		}

		if (e.getKeyCode() ==  KeyEvent.VK_DOWN) {
			controler.setReleasedDown(1);
		}
		
		if (e.getKeyCode() ==  KeyEvent.VK_Z) {
			controler.setReleasedUp(0);
		}
		
		if (e.getKeyCode() ==  KeyEvent.VK_S) {
			controler.setReleasedDown(0);
		}
		
	}

	

}