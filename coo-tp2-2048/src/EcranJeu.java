import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * JPanel correspondant à une partie, l'utilisateur clique sur des lettres pour essayer de deviner le mot
 * @author loick
 *
 */

public class EcranJeu extends JPanel{

	private Fenetre f;
	
	protected HashMap<String,JButton> buttons = new HashMap<String,JButton>();
	protected JLabel motLabel;
	protected JLabel vieLabel;
	protected Pendu pendu;
	protected JButton abandon;
	
	protected Panneau text;
	protected Panneau score;
	protected Panneau keyboard;
	
	
	protected int vieMax;
	protected int vie;
	
	protected Mot mot;
	
	protected ArrayList<String> mots;
	
	/**
	 * Créé un nouveau JPanel de jeu (Une fois)
	 * @param f Fenetre pour ajouter les ActionListener
	 * @throws IOException
	 */
	public EcranJeu(Fenetre f) throws IOException
	{
		this.f=f;
		
		mots=new ArrayList<String>();
		lectureFichier();
		
		
		this.setLayout(new BorderLayout());
		
		score = new Panneau();
		text = new Panneau();
		keyboard = new Panneau();
		
		Panneau b1 = new Panneau();
		Panneau b2 = new Panneau();
		Panneau b3 = new Panneau();
		
		b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
		b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
		b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
		
		Lettre but;
		for(char c='A'; c<='I';c++)
		{	
			but=new Lettre(c);
			but.addActionListener(f);
			buttons.put(c+"", but);
			b1.add(but);
		}
		for(char c='J'; c<='S';c++)
		{
			but=new Lettre(c);
			but.addActionListener(f);
			buttons.put(c+"", but);
			b2.add(but);
		}
		for(char c='T'; c<='Z';c++)
		{
			but=new Lettre(c);
			but.addActionListener(f);
			buttons.put(c+"", but);
			b3.add(but);
		}
		
		
		//On positionne maintenant ces trois lignes en colonne
		keyboard.setLayout(new BoxLayout(keyboard, BoxLayout.PAGE_AXIS));
		keyboard.add(b1);
		keyboard.add(b2);
		keyboard.add(b3);
		
		
		
		this.mot=randomMot();
		
		this.motLabel=new JLabel(mot.getMotActuel());
		this.motLabel.setPreferredSize(new Dimension(300,100));
		this.motLabel.setFont(new Font("Serif", Font.PLAIN, 40));
		this.vieLabel=new JLabel("Vie: "+vie);
		this.abandon=new JButton("Abandon");
		this.abandon.addActionListener(f);
		
		this.pendu=new Pendu();
		
		text.add(motLabel);
		text.add(pendu);
		
		score.add(vieLabel);
		score.add(abandon);
		
		
		this.add(text,BorderLayout.CENTER);
		this.add(keyboard,BorderLayout.SOUTH);
		this.add(score,BorderLayout.NORTH);
		
		
	}
	
	/**
	 * Récupère un mot dans la liste de manière aléatoir
	 * @return Le Mot en question
	 */
	public Mot randomMot()
	{
		int res=(int) (Math.random() * (mots.size()));
		Mot m = new Mot(mots.get(res));
		return m;
	}
	
	/**
	 * Appelé à chaque début de partie, on remet chaque bouton à 0, 
	 * change le mot et remet le compteur de vie
	 */
	public void reinit()
	{
		for(char c='A'; c<'Z'; c++)
		{
			this.buttons.get(c+"").setEnabled(true);
			this.vieLabel.setText("Vie: "+vieMax);
			this.pendu.changerPendu(0);
			this.mot=randomMot();
			this.motLabel.setText(mot.getMotActuel());
		}
	}
	
	/**
	 * Lance le parser et récupère tous les mots d'un fichier texte "mot.txt" 
	 * pour le stocker dans une liste de mot.
	 * @throws IOException
	 */
	public void lectureFichier() throws IOException
	{
		BufferedReader lecteurAvecBuffer = null;
	    String ligne;
	    try
	    {
	    	lecteurAvecBuffer = new BufferedReader(new FileReader("mot.txt"));
	    }
	    catch(FileNotFoundException exc)
	    {
	    	System.out.println("Erreur d'ouverture");
	    }
	    while ((ligne = lecteurAvecBuffer.readLine()) != null)
	    	mots.add(ligne);
	    lecteurAvecBuffer.close();
	}
	
	

}
