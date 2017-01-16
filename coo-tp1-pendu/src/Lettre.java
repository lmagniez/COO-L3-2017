import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;


/**
 * Classe bouton correspondant à une lettre
 * @author loick
 *
 */
public class Lettre extends JButton{

	private char lettre;

	
	public Lettre (char c)
	{
		this.lettre=c;
		this.setText(c+"");
	}

}
