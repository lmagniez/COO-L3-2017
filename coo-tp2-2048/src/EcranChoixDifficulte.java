import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * JPanel correspondant à l'écran de choix de difficulté avant de lancer une partie.
 * @author loick
 *
 */

public class EcranChoixDifficulte extends JPanel{

	
	private Fenetre f;
	
	protected JButton start;
	protected JButton quit;
	
	protected JPanel p;
	
	protected JLabel startLabel;
	
	/**
	 * Initialisation du JPanel (1 fois)
	 * @param f Fenetre utilisé pour les ActionListener
	 */
	public EcranChoixDifficulte(Fenetre f)
	{
		this.f=f;
		this.setLayout(new FlowLayout());
		
		start=new JButton("Démarrer");
		start.addActionListener(f);
		quit=new JButton("Quitter");			
		quit.addActionListener(f);
		
		startLabel=new JLabel("2048:");
				
		this.add(startLabel);
		this.add(start);
		this.add(quit);
		
	}
	
}
