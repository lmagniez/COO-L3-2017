import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


/**
 * 
 * @author loick
 * Classe principale contenant les 3 JFrames correspondant aux écrans de jeux
 * Implémente ActionListener et va gérer tous les cas rencontrés lors
 *
 */
public class Fenetre extends JFrame implements ActionListener{
	
	/**
	 * Initie la JFrame, puis lance le jeu du pendu
	 * @throws IOException 
	 */
	
	private EcranJeu lePanneau;
	private EcranFinDePartie lePanneau2;
	private EcranChoixDifficulte lePanneau3;
	
	/**
	 * Constructeur, initialise les panneaux en leur associant la fenetre 
	 * (pour pouvoir leur associer les listener)
	 * @throws IOException
	 */
	public Fenetre() throws IOException{
		this.setTitle("Animation");
		this.setSize(500, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		lePanneau= new EcranJeu(this);
		lePanneau2 = new EcranFinDePartie(this);
		lePanneau3 = new EcranChoixDifficulte(this);
		
		this.add(lePanneau3);
		this.setVisible(true);
		
	}

	/**
	 * Réinitialise un écran de jeu en lui changeant le nombre de vie 
	 * (choisit dans l'écran de difficulté)
	 * @param vieMax nombre de vie pour la nouvelle partie
	 */
	public void initFenetreEcranJeu(int vieMax)
	{
		lePanneau.vieMax=vieMax;
		lePanneau.vie=vieMax;
		lePanneau.reinit();
		
		
		afficherPanneau(lePanneau);
		
	}
	
	/**
	 * Méthode générique affichant un panneau sur la fenêtre
	 * @param p
	 */
	public void afficherPanneau(JPanel p)
	{
		getContentPane().removeAll();
		getContentPane().repaint();
		getContentPane().add(p);
		getContentPane().validate();
		this.repaint();
	}
	
	/**
	 * Mise en place de l'action listener pour chaque bouton
	 * Facile Moyen Difficile: Réinitialise une fenetre de jeu
	 * Abandon: Lance la fenetre de fin de partie
	 * Ok: Lance la fenetre de difficulté
	 * Non: Ferme la fenetre
	 * Lettre: Vérifie si la lettre est contenu dans le mot et agit en conséquence
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		boolean isOver=false;
		String command = ((JButton) arg0.getSource()).getActionCommand();
		
		if(command=="Facile")
			initFenetreEcranJeu(10);
		if(command=="Moyen")
			initFenetreEcranJeu(7);
		if(command=="Difficile")
			initFenetreEcranJeu(5);
		
		
		if(command=="Abandon")
		{
			
			lePanneau2.pendu.changerPendu(10);
			lePanneau2.gameOverLabel.setText("Abandon. La réponse était: "+lePanneau.mot.getMot()+". Rejouer?");
			isOver=true;
		}
		
		if(command=="Ok")
		{	
			afficherPanneau(lePanneau3);
		}
		
		if(command=="Non")
		{
			this.dispose();
		}
		
		JButton button = lePanneau.buttons.get(command);
	    if (null != button) 
	    {
	    	button.setEnabled(false);
	    	//trouve une lettre
	    	if(lePanneau.mot.update(command.charAt(0)))
	    	{
	    		lePanneau.motLabel.setText(lePanneau.mot.getMotActuel());
	    		//test victoire
	    		if(lePanneau.mot.getLettreRestante()==0)
	    		{
	    			lePanneau2.pendu.changerPendu(0);
	    			lePanneau2.gameOverLabel.setText("Bien joué! La réponse était: "+lePanneau.mot.getMot()+". Rejouer?");
	    			isOver=true;
	    			
	    		}
	    	}
	    	//retire une vie
	    	else
	    	{
	    		lePanneau.vie--;
	    		lePanneau.vieLabel.setText("Vie: "+lePanneau.vie);
	    		lePanneau.pendu.changerPendu(lePanneau.vieMax-lePanneau.vie);
	    		
	    		if(lePanneau.vie==0)
	    		{
	    			lePanneau2.pendu.changerPendu(10);
	    			lePanneau2.gameOverLabel.setText("Perdu. La réponse était: "+lePanneau.mot.getMot()+". Rejouer?");
	    			isOver=true;
	    		}
	    	}
	    }
	    
	    if(isOver)
		{
			afficherPanneau(lePanneau2);
		}
	    
		
	}
	
	public static void main(String[] args) throws IOException {
		Fenetre f = new Fenetre();
	}


}