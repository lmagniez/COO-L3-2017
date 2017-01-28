package com.vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;


/**
 * JPanel affichant les règles à l'utilisateur.
 * Permet le déplacement entre les écrans de règle avec le clavier.
 * @author loick
 *
 */
public class EcranRegle extends Ecran implements ActionListener{
	
	private final static String ECRANS[] = {"Ecran1","Ecran2","Ecran3"};
	private final static int NB_ECRANS=3;
	private int ecranActuel;
	
	private JButton b1,b2,b3;
	private JPanel cardPanel;
	private JPanel reglePanels[];
	
	private JLabel regleLabel0;
	private JTextArea regleLabels[];
	
	/**
	 * Initialisation du JPanel (Une fois)
	 * @param f Fenetre utilisé pour les ActionListener
	 */
	public EcranRegle(VueMenu vue)
	{
		
		this.NB_BUTTONS_X=0;
		this.NB_BUTTONS_Y=0;
		
				
		
		this.vue=vue;
		
		this.setLayout(new BorderLayout());
		
		cardPanel=new JPanel();
		cardPanel.setLayout(new CardLayout());
		
		
		b1=new JButton("Ok");
		b1.addActionListener(this);
		b2=new JButton("<");
		b2.addActionListener(this);
		b3=new JButton(">");
		b3.addActionListener(this);
		
		
		
		regleLabel0=new JLabel("Les Règles");
		regleLabel0.setHorizontalAlignment(SwingConstants.CENTER);
		regleLabel0.setVerticalAlignment(SwingConstants.CENTER);
		regleLabel0.setMinimumSize(new Dimension(45,45));
		regleLabel0.setPreferredSize(new Dimension(90,90));
		
		String s1="Le but du jeu est de faire glisser des tuiles sur"
				+ "la grille pour combiner les tuiles de mêmes "
				+ "valeurs et créer ainsi une tuile portant le nombre "
				+ "2048.";
		
		String s2="Une fois 2048 atteint, la partie est gagnée.\n"+
		"Vous pouvez cependant continuer afin de faire le meilleur score possible."
		+ "Pour cela, appuyez sur la touche continuer.";

		String s3="La partie est perdu lorsque la grille est pleine et "
				+ "qu’il n’y a plus de combinaisons possibles.\n"+
				"Bonne chance !";
		
		regleLabels=new JTextArea[3];
		regleLabels[0]=initTextArea(s1);
		regleLabels[1]=initTextArea(s2);
		regleLabels[2]=initTextArea(s3);
		
		reglePanels=new JPanel[3];
		
		
		for(int i=0; i<NB_ECRANS; i++)
		{
			reglePanels[i]=new JPanel();
			reglePanels[i].add(regleLabels[i]);
			cardPanel.add(reglePanels[i], ECRANS[i]);
		}
		
		this.add(regleLabel0,BorderLayout.NORTH);
		this.add(b1,BorderLayout.SOUTH);
		this.add(cardPanel,BorderLayout.CENTER);
		this.add(b2,BorderLayout.WEST);
		this.add(b3,BorderLayout.EAST);
		
		this.setFocusable(true);
		this.requestFocus();
		
		this.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				EcranRegle.this.gestion(e);
			}
		});
		
	}

	/**
	 * Initie un JTextAera pour une chaine et le retourne
	 * @param s chaine
	 * @return JTextArea généré
	 */
	public JTextArea initTextArea(String s)
	{
		
		JTextArea textArea = new JTextArea();
        textArea.setRows(15);
        textArea.setColumns(20);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setBackground(Color.lightGray);
		textArea.append(s);
		return textArea;

	}
	
	/**
	 * Changer d'écran si possible
	 */
	public void changeScreenRight()
	{
		CardLayout c = (CardLayout) cardPanel.getLayout();
		if(ecranActuel<NB_ECRANS-1)
    	{
    		ecranActuel++;
    		c.show(cardPanel,ECRANS[ecranActuel]);
    	}
	}
	/**
	 * Changer d'écran si possible
	 */
	public void changeScreenLeft()
	{
		CardLayout c = (CardLayout) cardPanel.getLayout();
		if(ecranActuel>0)
    	{
    		ecranActuel--;
    		c.show(cardPanel,ECRANS[ecranActuel]);
    	}
	}
	
	
	/**
	 * Ok: quitte l'écran d'aide et revient au jeu
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = ((JButton) e.getSource()).getActionCommand();
		
		if(command=="Ok")
		{	
			//this.focusNouvelEcran(vue.lePanneau);
			//vue.afficherPanneau(vue.lePanneau);
			//vue.lePanneau.setFocusable(true);
			//vue.lePanneau.requestFocus();
			
			//vue.afficherPanneau(vue.lePanneau3);
			this.focusNouvelEcran(vue.lePanneau3);
        	
		}
		
		if(command==">")
		{	
			this.changeScreenRight();
		}
		if(command=="<")
		{
			this.changeScreenLeft();
		}
		
	}
	
	
	/**
	 * Gestion clavier des directions et des touches quitter(ESC) aide(H) et recommencer(J)
	 * @param e
	 */
	public void gestion(KeyEvent e){
		int keyCode=e.getKeyCode();
		
    	
	    switch(keyCode) {
	        case KeyEvent.VK_LEFT:
	        	this.changeScreenLeft();
	        	break;
	        case KeyEvent.VK_RIGHT :
	        	this.changeScreenRight();
	        	break;
	       
	        case KeyEvent.VK_H:
	        	vue.afficherPanneau(vue.lePanneau);
	        	break;
	        case KeyEvent.VK_ESCAPE:
	        	this.focusNouvelEcran(vue.lePanneau3);
	        	vue.afficherPanneau(vue.lePanneau3);
	        	break;
	        
	        	
	     }
	    
	}
}
