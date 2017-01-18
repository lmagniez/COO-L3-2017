import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

public abstract class GestionBouton {
	
	
	
	private static KeyListener enter = new KeyAdapter() {
	      @Override
	      public void keyTyped(KeyEvent e) {
	         if (e.getKeyChar() == KeyEvent.VK_ENTER) {
	            ((JButton) e.getComponent()).doClick();
	         }
	      }
	   };
	
	public static void ajoutListenerBouton(JButton[][] buttons)
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
		                     	buttons[curRow][curCol].setForeground(Color.WHITE);
		                     }
		                     break;
		                  case KeyEvent.VK_DOWN:
		                     if (curRow < buttons.length - 1)
		                     {  
		                    	buttons[curRow + 1][curCol].requestFocus();
		                     	buttons[curRow + 1][curCol].setForeground(Color.GREEN);
		                     	buttons[curRow][curCol].setForeground(Color.WHITE);
		                     	
		                     }
		                     
		                     break;
		                  case KeyEvent.VK_LEFT:
		                      if (curCol > 0)
		                      {
		                         buttons[curRow][curCol - 1].requestFocus();
		                         buttons[curRow][curCol - 1].setForeground(Color.GREEN);
			                     buttons[curRow][curCol].setForeground(Color.WHITE);
		                      }
		                      break;
		                  case KeyEvent.VK_RIGHT:
		                      if (curCol < buttons[curRow].length - 1)
		                      {
		                         buttons[curRow][curCol + 1].requestFocus();
		                         buttons[curRow][curCol + 1].setForeground(Color.GREEN);
			                     buttons[curRow][curCol].setForeground(Color.WHITE);
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
