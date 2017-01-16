import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * JPanel correspondant à une fin de partie.
 * On demande à l'utilisateur si il souhaite recommencer une partie.
 * @author loick
 *
 */
public class EcranFinDePartie extends JPanel{

	private Fenetre f;
	
	protected JButton b1;
	protected JButton b2;
	protected JPanel p;
	protected Pendu pendu;
	
	protected JLabel gameOverLabel;
	
	/**
	 * Initialisation du JPanel (Une fois)
	 * @param f Fenetre utilisé pour les ActionListener
	 */
	public EcranFinDePartie(Fenetre f)
	{
		this.f=f;
		
		this.setLayout(new FlowLayout());
		
		pendu=new Pendu();
		pendu.changerPendu(0);
		
		b1=new JButton("Ok");
		b1.addActionListener(f);
		b2=new JButton("Non");			
		b2.addActionListener(f);
		gameOverLabel=new JLabel("Game over");
				
		this.add(gameOverLabel);
		this.add(b1);
		this.add(b2);
		this.add(pendu);		
		
	}
	

}
