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
	
	protected JButton facile;
	protected JButton moyen;
	protected JButton difficile;
	
	protected JPanel p;
	
	protected JLabel difficulteLabel;
	
	/**
	 * Initialisation du JPanel (1 fois)
	 * @param f Fenetre utilisé pour les ActionListener
	 */
	public EcranChoixDifficulte(Fenetre f)
	{
		this.f=f;
		
		this.setLayout(new FlowLayout());
		
		facile=new JButton("Facile");
		facile.addActionListener(f);
		moyen=new JButton("Moyen");			
		moyen.addActionListener(f);
		difficile=new JButton("Difficile");			
		difficile.addActionListener(f);
		
		difficulteLabel=new JLabel("Choisir un niveau de difficulté:");
				
		this.add(difficulteLabel);
		this.add(facile);
		this.add(moyen);
		this.add(difficile);
		
		
	}
	
}
