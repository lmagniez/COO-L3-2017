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
public class EcranRegle extends JPanel implements ActionListener{

	private Fenetre f;
	
	protected JButton b1;
	protected JPanel p;
	
	protected JLabel regleLabel;
	
	/**
	 * Initialisation du JPanel (Une fois)
	 * @param f Fenetre utilisé pour les ActionListener
	 */
	public EcranRegle(Fenetre f)
	{
		this.f=f;
		
		this.setLayout(new FlowLayout());
		
		
		b1=new JButton("Ok");
		b1.addActionListener(this);
		regleLabel=new JLabel("Règles");
				
		this.add(regleLabel);
		this.add(b1);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = ((JButton) e.getSource()).getActionCommand();
		
		if(command=="Ok")
		{	
			f.afficherPanneau(f.lePanneau);
			f.lePanneau.setFocusable(true);
			f.lePanneau.requestFocus();
		}
	}
	

}
