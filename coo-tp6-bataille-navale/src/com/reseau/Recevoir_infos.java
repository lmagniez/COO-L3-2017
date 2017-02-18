package com.reseau;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.model.BateauModel;
import com.model.CaseModel;
import com.model.CaseValue;
import com.model.GridModel;
import com.vue.grid.CaseValueVue;
import com.vue.grid.Score;
import com.vue.grid.VueGrid;

/**
 * Thread du client, recoit l'ensemble des infos et les traite si besoin pour les renvoyer au serveur
 * @author loick
 *
 */
public class Recevoir_infos implements Runnable {

	private static final String LO_ADDR = "127.0.0.1";
	public static final int DEFAULT_LOCAL_PORT = 1791;
	private PrintStream defaultOutStream = System.out;
	private InputStream defaultInStream = System.in;
	
	protected JTextArea l1;
	protected GridModel model;
	protected Socket socket;
	protected PrintWriter out;
	protected BufferedReader in;
	protected boolean running=true;
	
	/**
	 * Constructeur
	 * @param gridModel modele de la grille
	 * @param socket socket du serveur
	 * @param in 
	 * @param out
	 */
	public Recevoir_infos(GridModel gridModel,Socket socket, BufferedReader in, PrintWriter out){;
		this.model=gridModel;
		this.socket=socket;
		this.out=out;
		this.in=in;
		
	}
	
	/**
	 * Stopper le thread
	 */
	public void stopper()
	{
		this.running=false;
	}
	
	/**
	 * Reprendre le thread
	 */
	public void reprendre()
	{
		this.running=true;
	}
	
	/**
	 * Attend la connection de deux joueurs
	 * Décide de qui commence la partie
	 * Si se fait bombarder -> Vérifie son modèle et renvoie la réponse positive ou négative
	 */
	public void run() 
	{

		try {
		
			while(running){
				String msg_distant = in.readLine();
				
				if(msg_distant==null){
					this.model.notifyMsgScore("Problème de communication serveur!");
					this.model.notifyMsgScore2("Problème de communication serveur!");					
					break;
				}
				
				if(msg_distant.contains("En attente de")){
					this.model.notifyMsgScore(msg_distant);
					this.model.notifyMsgScore2(msg_distant);
				}
				
				if(msg_distant.equals("GO J1")){
					this.model.setEtat(EtatClient.PLAYER_TURN);
					this.model.notifyMsgScore("A vous de commencer!");
					this.model.notifyMsgScore2("En attente...");
				}
				if(msg_distant.equals("GO J2")){
					this.model.setEtat(EtatClient.OPPONENT_TURN);
					this.model.notifyMsgScore2("A vous de commencer!");
					this.model.notifyMsgScore("En attente...");
				}
				if(msg_distant.equals("CONNECTION LOST"))
					this.model.setEtat(EtatClient.LOST_CONNECTION);
				
				
				//Se faire bombarder
				String[] msg_splited = msg_distant.split("\\s+");
				if(msg_splited[0].equals("BOMB")){
					int bombx=Integer.parseInt(msg_splited[1]);
					int bomby=Integer.parseInt(msg_splited[2]);
					CaseModel caseTmp=this.model.getCasesAdversaire()[bomby][bombx];
					caseTmp.setV(CaseValue.TOUCHE);					
					if(caseTmp.getIdBateau()!=-1){
						
						BateauModel b =this.model.getBateaux()[caseTmp.getIdBateau()];
						b.setNbVie(b.getNbVie() - 1);
						String coules="";
						if(b.getNbVie()==0){
							this.model.addBateauCouleJ2();
							coules=" SINKED";
						}
						
						this.model.notifyBombAdversaire(bombx, bomby, CaseValueVue.TOUCHE);
						this.model.sendToServer("TOUCHED "+bombx+" "+bomby+coules);
						this.model.notifyMsgScore2("Touché !!");
						this.model.notifyMsgScore("A votre tour...");
						this.model.addCoupsPrisJ2();
						this.model.verifWin();
						
					}
					else{
						this.model.notifyBombAdversaire(bombx, bomby, CaseValueVue.PLOUF);
						this.model.sendToServer("MISSED "+bombx+" "+bomby);
						this.model.notifyMsgScore2("Raté !!");
						this.model.notifyMsgScore("A votre tour...");
						this.model.addCoupsRatesJ2();
					}
					model.setEtat(EtatClient.PLAYER_TURN);
					
				}
				
				//L'adversaire indique qu'il est touché
				if(msg_splited[0].equals("TOUCHED")){
					int bombx=Integer.parseInt(msg_splited[1]);
					int bomby=Integer.parseInt(msg_splited[2]);
					if(msg_splited.length==4)
						if(msg_splited[3].equals("SINKED"))
							this.model.addBateauCouleJ1();
					
					this.model.notifyBombJoueur(bombx, bomby, CaseValueVue.TOUCHE);
					this.model.notifyMsgScore("Touché !!");
					this.model.notifyMsgScore2("A votre tour...");
					this.model.addCoupsPrisJ1();
					this.model.verifWin();
				}
				
				//L'adversaire indique qu'il n'est pas touché
				if(msg_splited[0].equals("MISSED")){
					int bombx=Integer.parseInt(msg_splited[1]);
					int bomby=Integer.parseInt(msg_splited[2]);
					this.model.notifyBombJoueur(bombx, bomby, CaseValueVue.PLOUF);
					this.model.notifyMsgScore("Raté !");
					this.model.notifyMsgScore2("A votre tour...");
					this.model.addCoupsRatesJ1();
					
				}
				
			}
		} 
		
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
}
