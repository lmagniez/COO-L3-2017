import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Ecran a appeler dans l'écran jeu
 * @author loick
 *
 */
public class EcranOpt extends JPanel implements ActionListener{
	
	private Fenetre f;
	protected JButton buttons[][];
	public static final int NB_BUTTONS_X=1;
	public static final int NB_BUTTONS_Y=2;
	
	
	
	public EcranOpt(Fenetre f)
	{
		this.f=f;
		JButton b1=new JButton("test");
		JButton b2=new JButton("Retour");
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		
		buttons=new JButton[NB_BUTTONS_X][NB_BUTTONS_Y];
		buttons[0][0]=b1;
		buttons[0][1]=b2;
		
		GestionBouton.ajoutListenerBouton(buttons);
		
		this.add(b1);
		this.add(b2);
		
		this.setBounds(300, 200, 800, 400);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String command = ((JButton) arg0.getSource()).getActionCommand();
		
		if(command=="Retour")
		{
			for(int i=0; i<f.lePanneau3.NB_BUTTONS_X; i++)
			{
				for(int j=0; j<f.lePanneau3.NB_BUTTONS_Y; j++)
				{
					f.lePanneau3.buttons[i][j].setEnabled(true);
			
				}
			}
			f.lePanneau3.start.requestFocus();
			f.lePanneau3.start.setForeground(Color.GREEN);
			
			
			
			this.setVisible(false);
		}
	}
}
