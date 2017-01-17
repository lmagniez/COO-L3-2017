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
public class EcranFinDePartie extends JPanel implements ActionListener{

	private Fenetre f;
	
	protected JButton b1;
	protected JButton b2;
	protected JPanel p;
	
	protected JLabel gameOverLabel;
	
	/**
	 * Initialisation du JPanel (Une fois)
	 * @param f Fenetre utilisé pour les ActionListener
	 */
	public EcranFinDePartie(Fenetre f)
	{
		this.f=f;
		
		this.setLayout(new FlowLayout());
		
		
		b1=new JButton("Ok");
		b1.addActionListener(this);
		b2=new JButton("Non");			
		b2.addActionListener(this);
		gameOverLabel=new JLabel("Game over");
				
		this.add(gameOverLabel);
		this.add(b1);
		this.add(b2);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = ((JButton) e.getSource()).getActionCommand();
		
		if(command=="Ok")
		{	
			f.afficherPanneau(f.lePanneau3);
		}
		
		if(command=="Non")
		{
			f.dispose();
		}
	}
	

}
