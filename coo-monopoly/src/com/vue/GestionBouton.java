package com.vue;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;

/**
 * Classe abstraite gérant le déplacement des boutons au clavier
 * @author loick
 *
 */
public abstract class GestionBouton {
	
	
	
	private static KeyListener enter = new KeyAdapter() {
	      @Override
	      public void keyTyped(KeyEvent e) {
	         if (e.getKeyChar() == KeyEvent.VK_ENTER) {
	        	if(e.getComponent() instanceof JButton)
	        		((JButton) e.getComponent()).doClick();
	        	if(e.getComponent() instanceof JComboBox){
	        		if(((JComboBox) e.getComponent()).isPopupVisible())
	        			((JComboBox) e.getComponent()).setPopupVisible(false);
	        		else
	        			((JComboBox) e.getComponent()).setPopupVisible(true);
	        	}
	         }
	      }
	};
	
	public static void ajoutListenerBouton(JComponent[][] buttons)
	{
		
		for(int i=0; i<buttons.length; i++)
		{
			for(int j=0; j<buttons[i].length; j++)
			{	
				final int curRow = i;
				final int curCol = j;
				System.out.println(curRow);
				
				buttons[i][j].addKeyListener(enter);	
				buttons[i][j].addKeyListener(new KeyAdapter(){
					public void keyPressed(KeyEvent e) {
		                  switch (e.getKeyCode()) {
		                  case KeyEvent.VK_UP:
		                     if (curRow > 0)
		                     {
		                        buttons[curRow - 1][curCol].requestFocus();
		                     	buttons[curRow - 1][curCol].setForeground(Color.GREEN);
		                     	buttons[curRow][curCol].setForeground(Color.BLACK);
		                     }
		                     break;
		                  case KeyEvent.VK_DOWN:
		                     if (curRow < buttons.length - 1)
		                     {  
		                    	buttons[curRow + 1][curCol].requestFocus();
		                     	buttons[curRow + 1][curCol].setForeground(Color.GREEN);
		                     	buttons[curRow][curCol].setForeground(Color.BLACK);
		                     	
		                     }
		                     
		                     break;
		                  case KeyEvent.VK_LEFT:
		                      if (curCol > 0)
		                      {
		                         buttons[curRow][curCol - 1].requestFocus();
		                         buttons[curRow][curCol - 1].setForeground(Color.GREEN);
			                     buttons[curRow][curCol].setForeground(Color.BLACK);
		                      }
		                      break;
		                  case KeyEvent.VK_RIGHT:
		                      if (curCol < buttons[curRow].length - 1)
		                      {
		                         buttons[curRow][curCol + 1].requestFocus();
		                         buttons[curRow][curCol + 1].setForeground(Color.GREEN);
			                     buttons[curRow][curCol].setForeground(Color.BLACK);
		                      }
		                      break;
		                  default:
		                     break;
		                  }
		                  
						}
			
					}				
				);
			}//for
		}//for
	   
	}
}
