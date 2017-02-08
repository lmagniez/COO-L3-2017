package com.vue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyAdapter extends KeyAdapter {

	//this.addKeyListener(new MyKeyAdapter());
	
	private boolean isUPPressed = false;
	private boolean isDownPressed = false;
	private boolean isZPressed = false;
	private boolean isSPressed = false;
	private boolean isEnterPressed = false;
	
	
	private boolean presser = false;

	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			isUPPressed = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			isDownPressed = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_Z) {
			isZPressed = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_S) {
			isSPressed = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			isEnterPressed = true;
		}
		
		
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			isUPPressed = false;
		}

		if (e.getKeyCode() ==  KeyEvent.VK_DOWN) {
			isDownPressed = false;
		}
		
		if (e.getKeyCode() ==  KeyEvent.VK_Z) {
			isZPressed = false;
		}
		
		if (e.getKeyCode() ==  KeyEvent.VK_S) {
			isSPressed = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			isEnterPressed = false;
		}
	}

	public boolean doActionUP() {
		return isUPPressed;
	}
	
	public boolean doActionDOWN() {
		return isDownPressed;
	}
	
	public boolean doActionZ() {
		return isZPressed;
	}
	
	public boolean doEnter(){
		return isEnterPressed;
	}
	
	public boolean doActionS() {
		return isSPressed;
	}
	
	

}