package com.vue.menu;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.vue.ButtonMenu;
import com.vue.Colors;


/**
 * Ecran a appeler dans l'écran jeu
 * @author loick
 *
 */
public class EcranOptPlus extends Ecran implements ActionListener{
	
	
	protected JButton start;
	protected JButton option;
	protected JButton quit;
	
	protected JLabel startLabel;
	
	protected JComboBox typeJoueurs[];
	
	protected JPanel panelRegles[];
	protected Image fond;
	
	
	public static final int MIN_PARAM=2;
	public static final int MAX_PARAM=9;
	
	public EcranOptPlus(VueMenu vue)
	{
		
		fond=new ImageIcon("./Sprites/aide.png").getImage();
		
		this.vue=vue;
		NB_BUTTONS_X=2;
		NB_BUTTONS_Y=1;
		int cptButton=0;
		JPanel tmp;
		GridBagConstraints c = new GridBagConstraints();
		
		int cpty1=0,cpty2=0;
		  
		c.anchor=GridBagConstraints.LINE_START;
		c.gridx=0;
		c.gridy=cpty1++;
		cpty2++;
		c.gridwidth=2;
		
		
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		/*
		 * Init les composants
		 */
		
		//init bouton start
		start=new ButtonMenu("Démarrer",Colors.textColor2,Colors.case8);
		start.addActionListener(new ButtonListener());
		start.addActionListener(this);
		
		//init bouton quitter
		quit=new ButtonMenu("Retour",Colors.textColor2,Colors.case16);	
		quit.addActionListener(new ButtonListener());
		quit.addActionListener(this);
		
		//init label titre
		startLabel=new JLabel("Paramètres de la partie ");
		startLabel.setSize(new Dimension(150,50));
		startLabel.setFont(new Font("Arial",Font.BOLD,20));
		startLabel.setAlignmentX(this.CENTER_ALIGNMENT);
		
		
		buttons=new JComponent[NB_BUTTONS_X][NB_BUTTONS_Y];
    	panelRegles=new JPanel[3];
		
		//init combo box
		String str[]={"JOUEUR","IA"};
		String str2[]={"NONE","JOUEUR","IA"};
		

	    for(int i=0; i<2; i++)
	    {
	    	
	    	//Init panel
	    	panelRegles[i]=new JPanel();
	    	//panelRegles[i].setLayout(new BoxLayout(panelRegles[i],BoxLayout.PAGE_AXIS));
	    	panelRegles[i].setLayout(new GridBagLayout());
	    	panelRegles[i].setBorder(BorderFactory.createLineBorder(Color.black));
	    	Border border = panelRegles[i].getBorder();
	    	Border margin = new EmptyBorder(10,10,10,10);
	    	panelRegles[i].setBorder(new CompoundBorder(border, margin));
	    	
	    	
	    	
	    	
	    	
	    	
	    }
	   
	    
	    
	    
    	//Ajout des éléments au panel
    	panelRegles[0].add(new JLabel("Déroulement de la partie"),c);
    	panelRegles[1].add(new JLabel("Plateau et enchère"),c);
    	
    	
    	c.gridy=cpty1++;
    	cpty2++;
    	panelRegles[0].add(Box.createRigidArea(new Dimension(5,50)),c);
    	panelRegles[1].add(Box.createRigidArea(new Dimension(5,50)),c);
    	
    	
    	
    	SpinnerModel model = new SpinnerNumberModel(1500, 1, 10000, 100);     
    	JSpinner argent = new JSpinner(model);
    	argent.setPreferredSize(new Dimension(120,30));
    	c.gridwidth=1;
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[0].add(new JLabel("Argent en début de partie: "),c);
    	c.gridx=1;
    	panelRegles[0].add(argent,c);
    	//buttons[cptButton++][0]=argent;  
    	
    	
    	SpinnerModel model2 = new SpinnerNumberModel(20, 1, 40, 1);     
    	JSpinner maisons = new JSpinner(model2);
    	maisons.setPreferredSize(new Dimension(120,25));
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[0].add(new JLabel("Maisons en début de partie: "),c);
    	c.gridx=1;
    	panelRegles[0].add(maisons,c);
    	//buttons[cptButton++][0]=maisons;  
    	
    	String nbPropri[]={"1","2","3","4","5"};
    	JComboBox nbPropriete = new JComboBox(nbPropri);
    	nbPropriete.setPreferredSize(new Dimension(120,25));
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[0].add(new JLabel("Nombre de maisons placées:"),c);
    	c.gridx=1;
    	panelRegles[0].add(nbPropriete,c);
    	buttons[cptButton++][0]=nbPropriete;  
    	
    	
    	JCheckBox choixTour=new JCheckBox();
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[0].add(new JLabel("Définir le nombre de tour: "),c);
    	c.gridx=1;
    	panelRegles[0].add(choixTour,c);
    	buttons[cptButton++][0]=choixTour;  
    	
    	SpinnerModel model3 = new SpinnerNumberModel(15, 1, 30, 1);     
    	JSpinner nbTour = new JSpinner(model3);
    	nbTour.setPreferredSize(new Dimension(120,30));
    	c.gridwidth=1;
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[0].add(new JLabel("Nombre de tour: "),c);
    	c.gridx=1;
    	panelRegles[0].add(nbTour,c);
    	
    	
    	
    	c.gridy=cpty1++;
    	panelRegles[0].add(Box.createRigidArea(new Dimension(5,50)),c);
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	JCheckBox positionAlea=new JCheckBox();
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[0].add(new JLabel("Position aléatoire des joueurs: "),c);
    	c.gridx=1;
    	panelRegles[0].add(positionAlea,c);
    	
    	
    	
    	JCheckBox troisDes=new JCheckBox();
    	JCheckBox prison=new JCheckBox();
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[0].add(new JLabel("Utiliser 3 dés: "),c);
    	c.gridx=1;
    	panelRegles[0].add(troisDes,c);
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[0].add(new JLabel("Prison si somme>50: "),c);
    	c.gridx=1;
    	panelRegles[0].add(prison,c);
    	
    	
    	
    	JCheckBox choixDes=new JCheckBox();
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[0].add(new JLabel("Choisir le nombre de dés: "),c);
    	c.gridx=1;
    	panelRegles[0].add(choixDes,c);
    	
    	JCheckBox suicide=new JCheckBox();
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[0].add(new JLabel("Mode suicide: "),c);
    	c.gridx=1;
    	panelRegles[0].add(suicide,c);
    	
    	
    	///////
    	//PLATEAU ET ENCHERE
    	///////
    	
    	JCheckBox enchere=new JCheckBox();
    	enchere.setSelected(true);
    	c.gridx=0;c.gridy=cpty2++;
    	panelRegles[1].add(new JLabel("Autoriser les enchères: "),c);
    	c.gridx=1;
    	panelRegles[1].add(enchere,c);
    	
    	JCheckBox timer=new JCheckBox();
    	c.gridx=0;c.gridy=cpty2++;
    	panelRegles[1].add(new JLabel("Timer doublant les valeurs: "),c);
    	c.gridx=1;
    	panelRegles[1].add(timer,c);
    	
    	c.gridy=cpty2++;
    	panelRegles[0].add(Box.createRigidArea(new Dimension(5,50)),c);
    	
    	
    	ButtonGroup bg1 = new ButtonGroup( );
    	JRadioButton plateau1= new JRadioButton("Normal");
    	JRadioButton plateau2= new JRadioButton("Truc 1");
    	JRadioButton plateau3= new JRadioButton("Truc 2");
    	bg1.add(plateau1);
    	bg1.add(plateau2);
    	bg1.add(plateau3);
    	plateau1.setSelected(true);
    	c.gridx=0;c.gridy=cpty2++;
    	panelRegles[1].add(new JLabel("Type de plateau: "),c);
    	c.gridy=cpty2++;
    	c.gridx=0;
    	panelRegles[1].add(plateau1,c);
    	c.gridx=1;
    	panelRegles[1].add(plateau2,c);
    	c.gridx=2;
    	panelRegles[1].add(plateau3,c);
    	
    	
    	JCheckBox caseAlea=new JCheckBox();
    	c.gridx=0;c.gridy=cpty2++;
    	panelRegles[1].add(new JLabel("Générer le plateau aléatoirement: "),c);
    	c.gridx=1;
    	panelRegles[1].add(caseAlea,c);
    	
    	JCheckBox masquerCase=new JCheckBox();
    	c.gridx=0;c.gridy=cpty2++;
    	panelRegles[1].add(new JLabel("Masquer les cases: "),c);
    	c.gridx=1;
    	panelRegles[1].add(masquerCase,c);
    	
    	
    	///////
      	//TERRAIN
      	///////
    	
    	c.gridy=cpty2++;
    	panelRegles[1].add(Box.createRigidArea(new Dimension(5,50)),c);
    	
    	
    	c.gridx=0;
    	c.gridy=cpty2++;
    	panelRegles[1].add(new JLabel("Modification du terrain"),c);
    	c.gridy=cpty2++;
    	panelRegles[1].add(Box.createRigidArea(new Dimension(5,50)),c);
    	
    	
    	String valAch[]={"x0.5","x1","x2","x5","x10"};
    	JComboBox valeurAchat = new JComboBox(valAch);
    	valeurAchat.setPreferredSize(new Dimension(120,25));
    	valeurAchat.setSelectedIndex(1);
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[1].add(new JLabel("Modifier la valeur d'achat:"),c);
    	c.gridx=1;
    	panelRegles[1].add(valeurAchat,c);
    	
    	String tauxInt[]={"x0.5","x1","x2","x5","x10"};
    	JComboBox valeurInteret = new JComboBox(tauxInt);
    	valeurAchat.setPreferredSize(new Dimension(120,25));
    	valeurAchat.setSelectedIndex(1);
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[1].add(new JLabel("Modifier intérêt rachat hypothèques :"),c);
    	c.gridx=1;
    	panelRegles[1].add(valeurInteret,c);
    	
    	
    	JCheckBox egalisation=new JCheckBox();
    	c.gridx=0;c.gridy=cpty2++;
    	panelRegles[1].add(new JLabel("Pas d'égalisation de terrain : "),c);
    	c.gridx=1;
    	panelRegles[1].add(egalisation,c);
    	
    	
    	/*
	    buttons[0][cptButton++]=start;
		buttons[0][cptButton++]=option;
		buttons[0][cptButton++]=quit;
		*/
	    

	    
	    
	    /*
	     * Placement des composants
	     */
	    
		this.add(Box.createRigidArea(new Dimension(5,50)));
	    this.add(startLabel);
		this.add(Box.createRigidArea(new Dimension(5,50)));
		
		//Parametres des joueurs
		
		
		JPanel p2= new JPanel();
		p2.setLayout(new BoxLayout(p2,BoxLayout.LINE_AXIS));
		
		for(int i=0; i<2; i++)
		{
			p2.add(Box.createRigidArea(new Dimension(25,5)));
			p2.add(panelRegles[i]);
			p2.add(Box.createRigidArea(new Dimension(25,5)));
		}
		
		
		this.add(p2);
		this.add(Box.createRigidArea(new Dimension(30,30)));
		
		JPanel p3= new JPanel();
		p3.setLayout(new BoxLayout(p3,BoxLayout.LINE_AXIS));
		
		
		
	    //Boutons start et quitter
		
		p3.add(start);
		p3.add(Box.createRigidArea(new Dimension(30,30)));
		
		p3.add(quit);
		p3.add(Box.createRigidArea(new Dimension(30,50)));
		
		this.add(p3);
		
		
		this.addListener();
	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		
		String command = ((JButton) arg0.getSource()).getActionCommand();
		
		if(command=="Démarrer")
		{
			vue.afficherPanneau(vue.lePanneau);
		}
		
		
		if(command=="Retour")
		{
			
			this.focusNouvelEcran(vue.lePanneau5);
		}
	}
	
	public void paintComponent(Graphics g) {
        g.drawImage(fond, 0, 0, getWidth(), getHeight(), this);
    }
	
	
}
