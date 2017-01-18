import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * JPanel correspondant à l'écran de choix de difficulté avant de lancer une partie.
 * @author loick
 *
 */

public class EcranChoixDifficulte extends JPanel implements ActionListener{

	
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
		start.addActionListener(this);
		quit=new JButton("Quitter");			
		quit.addActionListener(this);
		
		startLabel=new JLabel("2048:");
				
		this.add(startLabel);
		this.add(start);
		this.add(quit);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = ((JButton) e.getSource()).getActionCommand();
		
		if(command=="Démarrer")
		{
			
			f.initFenetreEcranJeu();
			f.lePanneau.setFocusable(true);
			f.lePanneau.requestFocus();
			
		}
		if(command=="Quitter")
			f.dispose();
	}
	
}
