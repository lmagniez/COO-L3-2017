import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * JPanel correspondant à l'écran de choix de difficulté avant de lancer une partie.
 * @author loick
 *
 */

public class EcranStart extends JPanel implements ActionListener{

	
	private Fenetre f;
	
	protected JButton start;
	protected JButton quit;
	protected JButton ia;
	
	protected JPanel p;
	
	protected JLabel startLabel;
	
	/**
	 * Initialisation du JPanel (1 fois)
	 * @param f Fenetre utilisé pour les ActionListener
	 */
	public EcranStart(Fenetre f)
	{
		this.f=f;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		start=new JButton("Démarrer");
		start.addActionListener(this);
		start.setAlignmentX(this.CENTER_ALIGNMENT);
		start.setForeground(Colors.textColor2);
		start.setBackground(Colors.case8);
		start.setBorder(new LineBorder (Color.BLACK, 1));
		start.setMaximumSize(new Dimension(150,50));
		
		ia=new JButton("IA");	
		ia.setAlignmentX(this.CENTER_ALIGNMENT);
		ia.addActionListener(this);
		ia.setForeground(Colors.textColor2);
		ia.setBackground(Colors.case128);
		ia.setBorder(new LineBorder (Color.BLACK, 1));
		ia.setMaximumSize(new Dimension(150,50));
		
		quit=new JButton("Quitter");	
		quit.setAlignmentX(this.CENTER_ALIGNMENT);
		quit.addActionListener(this);
		quit.setForeground(Colors.textColor2);
		quit.setBackground(Colors.case16);
		quit.setBorder(new LineBorder (Color.BLACK, 1));
		quit.setMaximumSize(new Dimension(150,50));
		
		startLabel=new JLabel("2048");
		startLabel.setSize(new Dimension(150,50));
		startLabel.setFont(new Font("Arial",Font.BOLD,50));
		startLabel.setAlignmentX(this.CENTER_ALIGNMENT);
		
		this.add(Box.createRigidArea(new Dimension(5,50)));
		this.add(startLabel);
		this.add(Box.createRigidArea(new Dimension(5,50)));
		this.add(start);
		this.add(Box.createRigidArea(new Dimension(5,50)));
		this.add(ia);
		this.add(Box.createRigidArea(new Dimension(5,50)));
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
		
		if(command=="IA")
		{
			f.initFenetreEcranJeu();
			try {
				f.lePanneau.initIA();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(command=="Quitter")
			f.dispose();
	}
	
}
