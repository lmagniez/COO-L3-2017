import java.awt.BorderLayout;
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


/**
 * JPanel correspondant à une fin de partie.
 * On copie la grille de jeu et on empeche toute modification de celle-ci.
 * On demande à l'utilisateur si il souhaite recommencer une partie.
 * Deux situations: 
 * -Perd la partie : Propose de recommencer le jeu
 * -Gagne la partie : Propose également de continuer la partie entamée et faire plus de 2048
 * @author loick
 *
 */
public class EcranFinDePartie extends JPanel implements ActionListener{

	private Fenetre f;
	
	protected JButton b1;
	protected JButton b2;
	protected JButton b3;
	protected JButton b4;
	protected JPanel p;
	
	protected JLabel gameOverLabel;
	protected Grille g;
	
	protected Score s;
	
	
	/**
	 * Initialisation du JPanel (Une fois)
	 * @param f Fenetre utilisé pour les ActionListener
	 */
	public EcranFinDePartie(Fenetre f)
	{
		this.f=f;
		
		this.setLayout(new FlowLayout());
		
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));
		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2, BoxLayout.LINE_AXIS));
		
		
		
		
		s=new Score();
		s.setFont(new Font("Arial",Font.BOLD,20));
		b4=new JButton("Continuer");
		b4.addActionListener(this);
		b1=new JButton("Rejouer");
		b1.addActionListener(this);
		b2=new JButton("Menu");
		b2.addActionListener(this);
		b3=new JButton("Quitter");
		b3.addActionListener(this);

		gameOverLabel=new JLabel("Game over");
		gameOverLabel.setSize(new Dimension(150,50));
		gameOverLabel.setFont(new Font("Arial",Font.BOLD,20));
		gameOverLabel.setAlignmentX(this.CENTER_ALIGNMENT);
		
		p2.add(gameOverLabel);
		p2.add(Box.createRigidArea(new Dimension(20,0)));
		p2.add(s);	
		
		
		p.add(b4);
		p.add(b1);
		p.add(b2);
		p.add(b3);
		
		this.add(p2);
		this.add(Box.createRigidArea(new Dimension(0,40)));
		this.add(p);
		
		b4.setVisible(false);
		
	}

	/**
	 * Rejouer: Recommence la partie (IA ou joueur)
	 * Continuer: Continuer la partie entamée en cas de victoire
	 * Quitter: Ferme la fenetre
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = ((JButton) e.getSource()).getActionCommand();
		
		if(command=="Rejouer")
		{	
			this.remove(g);
			f.initFenetreEcranJeu();
			
			if(f.lePanneau.is_IA)
				try {
					f.lePanneau.initIA();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
		}
		
		if(command=="Quitter")
		{
			f.dispose();
		}
		if(command=="Menu")
		{
			this.remove(g);
			f.afficherPanneau(f.lePanneau3);
		}
		
		if(command=="Continuer")
		{
			f.lePanneau.continuer=true;
			this.remove(g);
			f.afficherPanneau(f.lePanneau);
		}
	}
	

}
