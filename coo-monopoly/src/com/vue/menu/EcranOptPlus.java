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

import com.model.ConstantesParam;
import com.vue.ButtonMenu;
import com.vue.Colors;


/**
 * Ecran a appeler dans l'écran jeu
 * Ecran d'options avancés
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
	
	protected JSpinner argent,maisons;
	protected JComboBox nbPropriete;
	
	protected JCheckBox choixTour;
	protected JSpinner nbTour;
	
	protected JCheckBox positionAlea,troisDes, prison;
	
	protected JCheckBox choixDes;
	protected JCheckBox suicide;
	protected JCheckBox enchere, timer;
	protected JCheckBox caseAlea, masquerCase, teleportGare;
	
	protected JSpinner tempsEnchere, nbTourPrison;
	
	
	protected JComboBox valeurAchat, valeurInteret;
	protected JCheckBox egalisation;
	
	protected final String nbPropri[]={"1","2","3","4","5"};
	protected final int nbPropriValue[]={1,2,3,4,5};
	
	protected final String valAch[]={"x0.5","x1","x2","x5","x10"};
	protected final double valAchValue[]={0.5,1,2,5,10};
	
	
	protected final String tauxInt[]={"x0.5","x1","x2","x5","x10"};
	protected final double tauxIntValue[]={0.5,1,2,5,10};
	
	
	
	
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
    	panelRegles[0].add(Box.createRigidArea(new Dimension(5,30)),c);
    	panelRegles[1].add(Box.createRigidArea(new Dimension(5,30)),c);
    	
    	//ARGENT DEBUT PARTIE
    	SpinnerModel model = new SpinnerNumberModel(1500, 0, 1000000, 100);     
    	argent = new JSpinner(model);
    	argent.setPreferredSize(new Dimension(120,30));
    	c.gridwidth=1;
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[0].add(new JLabel("Argent en début de partie: "),c);
    	c.gridx=1;
    	panelRegles[0].add(argent,c);
    	//buttons[cptButton++][0]=argent;  
    	
    	//NOMBRE DE MAISONS
    	SpinnerModel model2 = new SpinnerNumberModel(20, 1, 40, 1);     
    	maisons = new JSpinner(model2);
    	maisons.setPreferredSize(new Dimension(120,25));
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[0].add(new JLabel("Maisons disponibles: "),c);
    	c.gridx=1;
    	panelRegles[0].add(maisons,c);
    	//buttons[cptButton++][0]=maisons;  
    	
    	//NOMBRE DE PROPRIETES
    	nbPropriete = new JComboBox(nbPropri);
    	nbPropriete.setPreferredSize(new Dimension(120,25));
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[0].add(new JLabel("Nombre de propriétés allouées:"),c);
    	c.gridx=1;
    	panelRegles[0].add(nbPropriete,c);
    	buttons[cptButton++][0]=nbPropriete;  
    	
    	
    	//CHOIX DES TOURS
    	choixTour=new JCheckBox();
    	choixTour.addActionListener(this);
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[0].add(new JLabel("Définir le nombre de tour: "),c);
    	c.gridx=1;
    	panelRegles[0].add(choixTour,c);
    	buttons[cptButton++][0]=choixTour;  
    	
    	SpinnerModel model3 = new SpinnerNumberModel(15, 1, 30, 1);     
    	nbTour = new JSpinner(model3);
    	nbTour.setEnabled(false);
    	nbTour.setPreferredSize(new Dimension(120,30));
    	c.gridwidth=1;
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[0].add(new JLabel("Nombre de tour: "),c);
    	c.gridx=1;
    	panelRegles[0].add(nbTour,c);
    	
    	
    	
    	c.gridy=cpty1++;
    	panelRegles[0].add(Box.createRigidArea(new Dimension(5,40)),c);
    	
    	
    	
    	//POSITION ALEA
    	positionAlea=new JCheckBox();
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[0].add(new JLabel("Position aléatoire des joueurs: "),c);
    	c.gridx=1;
    	panelRegles[0].add(positionAlea,c);
    	
    	//PRISON ET DES
    	troisDes=new JCheckBox();
    	troisDes.addActionListener(this);
    	prison=new JCheckBox();
    	prison.setEnabled(false);
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[0].add(new JLabel("Utiliser 3 dés: "),c);
    	c.gridx=1;
    	panelRegles[0].add(troisDes,c);
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[0].add(new JLabel("Prison si somme>50: "),c);
    	c.gridx=1;
    	panelRegles[0].add(prison,c);
    	
    	
    	/*
    	choixDes=new JCheckBox();
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[0].add(new JLabel("Le joueur choisit le nombre de dés: "),c);
    	c.gridx=1;
    	panelRegles[0].add(choixDes,c);
    	*/
    	
    	suicide=new JCheckBox();
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[0].add(new JLabel("Mode suicide: "),c);
    	c.gridx=1;
    	panelRegles[0].add(suicide,c);
    	
    	//PRISON NB TOUR
    	SpinnerModel model5 = new SpinnerNumberModel(3, 1, 5, 1);     
    	nbTourPrison = new JSpinner(model5);
    	nbTourPrison.setPreferredSize(new Dimension(120,30));
    	c.gridwidth=1;
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[0].add(new JLabel("Nombre de tour en prison: "),c);
    	c.gridx=1;
    	panelRegles[0].add(nbTourPrison,c);
    	
    	//buttons[cptButton++][0]=argent; 
    	
    	
    	///////
    	//PLATEAU ET ENCHERE
    	///////
    	
    	enchere=new JCheckBox();
    	enchere.addActionListener(this);
    	enchere.setSelected(true);
    	c.gridx=0;c.gridy=cpty2++;
    	panelRegles[1].add(new JLabel("Autoriser les enchères: "),c);
    	c.gridx=1;
    	panelRegles[1].add(enchere,c);
    	
    	//ARGENT DEBUT PARTIE
    	SpinnerModel model4 = new SpinnerNumberModel(15, 5, 60, 1);     
    	tempsEnchere = new JSpinner(model4);
    	tempsEnchere.setPreferredSize(new Dimension(120,30));
    	c.gridwidth=1;
    	c.gridx=0;c.gridy=cpty2++;
    	panelRegles[1].add(new JLabel("Temps enchere: "),c);
    	c.gridx=1;
    	panelRegles[1].add(tempsEnchere,c);
    	
    	timer=new JCheckBox();
    	c.gridx=0;c.gridy=cpty2++;
    	panelRegles[1].add(new JLabel("Timer doublant les valeurs: "),c);
    	c.gridx=1;
    	panelRegles[1].add(timer,c);
    	
    	c.gridy=cpty2++;
    	panelRegles[0].add(Box.createRigidArea(new Dimension(5,50)),c);
    	
    	/*
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
    	*/
    	
    	caseAlea=new JCheckBox();
    	c.gridx=0;c.gridy=cpty2++;
    	panelRegles[1].add(new JLabel("Générer le plateau aléatoirement: "),c);
    	c.gridx=1;
    	panelRegles[1].add(caseAlea,c);
    	
    	masquerCase=new JCheckBox();
    	c.gridx=0;c.gridy=cpty2++;
    	panelRegles[1].add(new JLabel("Masquer les cases: "),c);
    	c.gridx=1;
    	panelRegles[1].add(masquerCase,c);
    	
    	teleportGare=new JCheckBox();
    	teleportGare.addActionListener(this);
    	teleportGare.setSelected(true);
    	c.gridx=0;c.gridy=cpty2++;
    	panelRegles[1].add(new JLabel("Téléport entre les gares: "),c);
    	c.gridx=1;
    	panelRegles[1].add(teleportGare,c);
    	
    	
    	///////
      	//TERRAIN
      	///////
    	
    	c.gridy=cpty2++;
    	panelRegles[1].add(Box.createRigidArea(new Dimension(5,40)),c);
    	
    	
    	c.gridx=0;
    	c.gridy=cpty2++;
    	panelRegles[1].add(new JLabel("Modification du terrain"),c);
    	c.gridy=cpty2++;
    	panelRegles[1].add(Box.createRigidArea(new Dimension(5,30)),c);
    	
    	
    	
    	valeurAchat = new JComboBox(valAch);
    	valeurAchat.setPreferredSize(new Dimension(120,25));
    	valeurAchat.setSelectedIndex(1);
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[1].add(new JLabel("Modifier la valeur d'achat:"),c);
    	c.gridx=1;
    	panelRegles[1].add(valeurAchat,c);
    	
    	
    	valeurInteret = new JComboBox(tauxInt);
    	valeurAchat.setPreferredSize(new Dimension(120,25));
    	valeurAchat.setSelectedIndex(1);
    	c.gridx=0;c.gridy=cpty1++;
    	panelRegles[1].add(new JLabel("Modifier intérêt rachat hypothèques :"),c);
    	c.gridx=1;
    	panelRegles[1].add(valeurInteret,c);
    	
    	
    	egalisation=new JCheckBox();
    	c.gridx=0;c.gridy=cpty1++;
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
	
	/**
	 * Gérer les paramètres et les enregistrer dans la classe constantesParam
	 */
	public void gererParam(){
		
		ConstantesParam.SOMME_DEPART=(int) this.argent.getValue();
		ConstantesParam.NB_MAISONS=(int) this.maisons.getValue();
		ConstantesParam.NB_PROPRIETES=this.nbPropriValue[this.nbPropriete.getSelectedIndex()];
		ConstantesParam.TOUR_ENABLED=this.choixTour.isSelected();
		ConstantesParam.NB_TOUR=(int) this.nbTour.getValue();
		
		ConstantesParam.POSITION_ALEA_ENABLED=this.positionAlea.isSelected();
		ConstantesParam.TROIS_DES_ENABLED=this.troisDes.isSelected();
		ConstantesParam.SOMME_PRISON_ENABLED=this.prison.isSelected();
		ConstantesParam.CHOIX_DES_ENABLED=this.choixDes.isSelected();
		ConstantesParam.SUICIDE_ENABLED=this.suicide.isSelected();
		ConstantesParam.ENCHERE_ENABLED=this.enchere.isSelected();
		ConstantesParam.ENCHERE_TIME=(int) this.tempsEnchere.getValue();
		
		
		ConstantesParam.TIMER_VALEUR_ENABLED=this.timer.isSelected();
		ConstantesParam.CASE_ALEA_ENABLED=this.caseAlea.isSelected();
		ConstantesParam.CASE_MASQUE_ENABLED=this.masquerCase.isSelected();
		
		ConstantesParam.TAUX_ACHAT=this.valAchValue[this.valeurAchat.getSelectedIndex()];
		ConstantesParam.TAUX_INTERET=this.tauxIntValue[this.valeurInteret.getSelectedIndex()];
		ConstantesParam.EGALISATION_ENABLED=this.egalisation.isSelected();
		
		ConstantesParam.NB_TOUR_PRISON=(int) this.nbTourPrison.getValue();
		
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource()==this.troisDes){
			if(troisDes.isSelected())
				this.prison.setEnabled(true);
			else
				this.prison.setEnabled(false);
		}
		
		if(arg0.getSource()==this.choixTour){
			if(choixTour.isSelected())
				this.nbTour.setEnabled(true);
			else
				this.nbTour.setEnabled(false);
		}
		
		if(arg0.getSource()==this.enchere){
			if(enchere.isSelected())
				this.tempsEnchere.setEnabled(true);
			else
				this.tempsEnchere.setEnabled(false);
		}
		
		
		if(arg0.getSource() instanceof JButton){
			String command = ((JButton) arg0.getSource()).getActionCommand();
		
		
			if(command=="Démarrer")
			{
				this.gererParam();
				vue.initFenetreEcranJeu();
				//vue.afficherPanneau(vue.lePanneau);
			}
			
			
			if(command=="Retour")
			{
				
				this.focusNouvelEcran(vue.lePanneau5);
			}
		}
	}
	
	public void paintComponent(Graphics g) {
        g.drawImage(fond, 0, 0, getWidth(), getHeight(), this);
    }
	
	
}
