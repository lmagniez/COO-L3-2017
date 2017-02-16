package com.vue.grid;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.controler.AbstractControler;
import com.controler.GridControler;
import com.model.AbstractModel;
import com.model.CaseValue;
import com.model.Constantes;
import com.model.GridModel;
import com.model.Orientation;
import com.model.TypeBateau;
import com.model.patternWin;
import com.observer.Observer;
import com.vue.Fenetre;
import com.vue.titre.Vue1;

import reseau.Shifumi.Recevoir_infos;
import reseau.Shifumi.Serveur2;

/**
 * Classe correspondant à la vue du jeu
 * @author loick
 *
 */

public class VueGrid extends Fenetre implements Observer {

	//elements de la vue
	protected Vue1 vueMenu;
	private Grid gridJoueur,gridAdversaire;
	private Score score;
	protected Score score2;
	protected boolean swapColor;
	private int idJoueur;
	private int tour;
	protected Bateau[] bateaux;

	/*
	//serveur
	protected Recevoir_infos infos;
	protected Socket socket;
	protected BufferedReader in;
	protected PrintWriter out;
	*/
	
	//controler
	private AbstractControler controler;

	/**
	 * Constructeur de la vue de la grille.
	 * @param gridControler 
	 * @param controler Controler de la grille
	 * @param nbRow nombre de lignes
	 * @param nbCol nombre de colonnes
	 * @param vue1 
	 * @param swapColor 
	 * @param menu Vue correspondant au menu
	 */
	
	//ADD CONTROLEr
	//public VueGrid(AbstractControler gridControler, Socket s, int nbRow, int nbCol, Vue1 vue1) {
	public VueGrid(AbstractControler gridControler, int nbRow, int nbCol, Vue1 vue1) {	
		//this.socket=s;
		
		this.setSize(Constantes.TAILLE_ECRAN_GRILLE*2+Constantes.TAILLE_SEPARATION, 
				Constantes.TAILLE_ECRAN_GRILLE+Constantes.TAILLE_ECRAN_SCORE);
		this.setTitle("Bataille Navale");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));
		
		this.setControler(gridControler);
		this.vueMenu=vue1;
		this.gridJoueur=new Grid(this, nbRow, nbCol, true);
		this.gridAdversaire=new Grid(this, nbRow, nbCol, false);
		this.score=new Score(this,0);
		this.score2= new Score(this,1);
		getScore().addCoupsPris();
		
		
		
		JPanel panelJ1=new JPanel();
		panelJ1.setLayout(new BoxLayout(panelJ1,BoxLayout.PAGE_AXIS));
		panelJ1.add(getScore());
		panelJ1.add(gridJoueur);
		
		JPanel panelJ2=new JPanel();
		panelJ2.setLayout(new BoxLayout(panelJ2,BoxLayout.PAGE_AXIS));
		panelJ2.add(score2);
		panelJ2.add(gridAdversaire);
		
		this.add(panelJ1);
		this.add(Box.createRigidArea(new Dimension(Constantes.TAILLE_SEPARATION,10)));
		this.add(panelJ2);
		
		this.setVisible(true);
		
		getContentPane().setBackground(Color.LIGHT_GRAY);
		this.repaint();
	
		bateaux=new Bateau[Constantes.NB_BATEAUX];
		getControler().requestBateaux();
		
		
		/*
		 mettre le code dans une classe contenue dans modele grille
		 lancerCommunication déclaré dans abstract model
		 appele via le controler quand on initie la fenetre (constructeur Vue? Après init?)
		 
		 Quand on clique sur une case, appelle le controler de la meme maniere que pour modele
		 Check à partir de là...
		 faire notifyMessage UpdateMessage pour la vue.
		 
		 */
		//this.lancerCommunication();
		
		
		/*
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        System.out.println("yes!!");
		    	try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
		*/
		
	}

	/*
	public void lancerCommunication(){
		try{
			
			System.out.println("Demande de connexion");
			this.in=new BufferedReader (new InputStreamReader(this.socket.getInputStream()));
			this.out=new PrintWriter(this.socket.getOutputStream());
			String message_distant = this.in.readLine();
			System.out.println(message_distant);
			
			
			this.out.println("La reponse du joueur");
			this.out.flush();
			
			
			
			infos = new Recevoir_infos(this, socket, in, out);
			Thread t=new Thread(infos);
			t.start();
			
			
		}
		catch(UnknownHostException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	*/
	/*
	public void sendToServer(String msg){
		
		try {
			out=new PrintWriter(socket.getOutputStream());
			out.println(msg);
			out.flush();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	/**
	 * Mettre à jour l'affichage du tour dans le panel.
	 */
	public void updateTour(){
		this.tour=(this.tour+1)%2;
		repaint();
	}

	public void updateFull(){
		this.getScore().setMsg("Déjà joué");
	}


	/**
	 * Mettre à jour l'affichage et stopper la partie suite à la détection d'un
	 * pattern de victoire.
	 * @param x abscisse de la case
	 * @param y ordonnée de la case
	 * @param tour tour du joueur
	 */
	@Override
	public void updateWinner() {
		// TODO Auto-generated method stub
		System.out.println("WINNER");
		this.gridJoueur.actif=false;
		this.getScore().displayWinner(1);
		
	}
	
	@Override
	public void updateLoser() {
		// TODO Auto-generated method stub
		System.out.println("Loser..;");
		this.gridJoueur.actif=false;
		this.getScore().displayWinner(0);
		
	}
	
	

	@Override
	public void updateReinit() {
		// TODO Auto-generated method stub
		for(int i=0; i<this.gridAdversaire.nbRow; i++)
		{
			for(int j=0; j<this.gridAdversaire.nbCol; j++)
			{	
				this.gridAdversaire.cases[i][j].c=CaseValueVue.NONE;
				this.gridJoueur.cases[i][j].c=CaseValueVue.NONE;
			}
		}
	}

	@Override
	public void updateCaseJoueur(int x, int y, CaseValueVue v) {
		this.gridJoueur.cases[x][y].c=v;
		this.repaint();
	}

	@Override
	public void updateCaseAdversaire(int x, int y, CaseValueVue v) {
		this.gridAdversaire.cases[x][y].c=v;
		this.repaint();
	}

	@Override
	public void updateBateau(int x, int y, TypeBateau type, Orientation o, int idB) {
		this.bateaux[idB]=new Bateau(x,y,type,o);
	}
	
	public void updateMsgScore(String msg){
		System.out.println("update VUE GRID " + msg);
		this.score.setMsg(msg);		
		this.score.repaint();
	}
	
	/*
	public static void main(String[] args) {
		
		//VueGrid vueJeu = new VueGrid(null, 10, 10, null);
		
		
		//Creation du modele de grille
		AbstractModel gridModel = new GridModel(10, 10);
		//Creation du controleur
		AbstractControler gridControler = new GridControler(gridModel);
		//Creation de notre fenetre avec le controleur en parametre
		VueGrid vueJeu = new VueGrid(gridControler, 10, 10,null);
		//Ajout de la fenetre comme observer de notre modele
		gridModel.addObserver(vueJeu);
		
		vueJeu.setVisible(true);
		
	}*/

	public int getTour() {
		// TODO Auto-generated method stub
		return tour;
	}

	public AbstractControler getControler() {
		return controler;
	}

	public void setControler(AbstractControler controler) {
		this.controler = controler;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	
	

}