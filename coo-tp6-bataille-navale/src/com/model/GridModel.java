package com.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

import com.reseau.EtatClient;
import com.reseau.Recevoir_infos;
import com.vue.grid.CaseValueVue;

/**
 * Classe correspondant au modèle de la grille
 * @author loick
 */

public class GridModel extends AbstractModel{

	protected int longueurX;
	protected int longueurY;
	
	protected CaseModel casesJoueur[][];//cases où le joueur intéragit
	private CaseModel casesAdversaire[][];//cases où l'adversaire intéragit (contient les bateaux)
	private BateauModel bateaux[];
	public static int nb_bateaux=0;
	protected boolean isIA[]; 

	
	private int coupsPrisJ1,coupsPrisJ2;
	private int coupsRatesJ1,coupsRatesJ2;
	private int bateauxCoulesJ1, bateauxCoulesJ2;
	
	//serveur
	protected Recevoir_infos infos;
	protected Socket socket;
	protected BufferedReader in;
	protected PrintWriter out;
	
	
	
	/**
	 * Constructeur de la grille
	 * @param s Socket du serveur 
	 * @param longueurX longueur X de la grille
	 * @param longueurY longueur Y de la grille
	 */
	public GridModel(Socket s, int longueurX, int longueurY){
		
		this.longueurX=longueurX;
		this.longueurY=longueurY;
		this.setBateaux(new BateauModel[Constantes.NB_BATEAUX]);
		
		this.casesJoueur=new CaseModel[longueurX][longueurY];
		this.setCasesAdversaire(new CaseModel[longueurX][longueurY]);
		for(int i=0; i<longueurX; i++){
			for(int j=0; j<longueurY; j++){
				casesJoueur[i][j]=new CaseModel();
				getCasesAdversaire()[i][j]=new CaseModel();
			}
		}
		genererBateaux();
		
		this.socket=s;
	}
	
	/**
	 * Ajouter un coup pris pour le joueur 1 et mettre à jour son score
	 */
	public void addCoupsPrisJ1(){
		this.coupsPrisJ1++;
		this.notifyScoreJ1(coupsPrisJ1, coupsRatesJ1, bateauxCoulesJ1);
	}
	/**
	 * Ajouter un coup raté pour le joueur 1 et mettre à jour son score
	 */
	public void addCoupsRatesJ1(){
		this.coupsRatesJ1++;
		this.notifyScoreJ1(coupsPrisJ1, coupsRatesJ1, bateauxCoulesJ1);
	}
	/**
	 * Ajouter un coup pris pour le joueur 2 et mettre à jour son score
	 */
	public void addCoupsPrisJ2(){
		this.coupsPrisJ2++;
		this.notifyScoreJ2(coupsPrisJ2, coupsRatesJ2, bateauxCoulesJ2);
	}
	/**
	 * Ajouter un coup raté pour le joueur 2 et mettre à jour son score
	 */
	public void addCoupsRatesJ2(){
		this.coupsRatesJ2++;
		this.notifyScoreJ2(coupsPrisJ2, coupsRatesJ2, bateauxCoulesJ2);
	}
	
	/**
	 * Ajouter un bateau coulé pour le joueur
	 */
	public void addBateauCouleJ1(){
		this.bateauxCoulesJ1++;
		this.notifyScoreJ1(coupsPrisJ1, coupsRatesJ1, bateauxCoulesJ1);
	}
	
	/**
	 * Ajouter un bateau coulés pour l'adversaire
	 */
	public void addBateauCouleJ2(){
		this.bateauxCoulesJ2++;
		this.notifyScoreJ2(coupsPrisJ1, coupsRatesJ1, bateauxCoulesJ1);
	}
	
	/**
	 * Lancer la communication entre le modèle et le serveur
	 */
	public void lancerCommunication(){
		try{
			
			//Demande de connexion
			this.in=new BufferedReader (new InputStreamReader(this.socket.getInputStream()));
			this.out=new PrintWriter(this.socket.getOutputStream());
			String message_distant = this.in.readLine();
			System.out.println(message_distant);
			
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
	
	/**
	 * Envoyer au serveur une chaine
	 * @param msg chaine a envoyer au serveur
	 */
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
	
	/**
	 * Notifier les bateaux à la vue
	 */
	public void notifierBateaux(){
		for(int i=0; i<Constantes.NB_BATEAUX; i++){
			this.notifyNewBateau(getBateaux()[i].debutX,getBateaux()[i].debutY,getBateaux()[i].type,
					getBateaux()[i].o,i);
		}
	}

	/**
	 * Générer l'ensemble des bateaux sur la grille
	 */
	public void genererBateaux(){
		nb_bateaux=0;
		placerBateau(TypeBateau.BATEAU_2);
		placerBateau(TypeBateau.BATEAU_3);
		placerBateau(TypeBateau.BATEAU_3);
		placerBateau(TypeBateau.BATEAU_4);
		placerBateau(TypeBateau.BATEAU_5);
	}
	
	/**
	 * Placer un bateau en fonction de son type sur la grille
	 * @param t type du bateau
	 */
	public void placerBateau(TypeBateau t){
		int tailleBateau=TypeBateau.fromType(t);
		
		int positionX = 0,positionY = 0;
		Random r=new Random();
		
		Orientation orientation=Orientation.fromInteger(r.nextInt(2));
		
		boolean ok=false;
		while(!ok){
			if(orientation==Orientation.VERTICAL){
				
				positionX=r.nextInt(longueurX-tailleBateau);
				positionY=r.nextInt(longueurY);
				ok=true;
				for(int x=positionX; x<positionX+tailleBateau; x++){
					if(getCasesAdversaire()[x][positionY].getIdBateau()!=-1)
						ok=false;		
				}
			}
			else if(orientation==Orientation.HORIZONTAL){
				positionX=r.nextInt(longueurX);
				positionY=r.nextInt(longueurY-tailleBateau);
				//checker positions libres
				ok=true;
				for(int y=positionY; y<positionY+tailleBateau; y++){
					if(getCasesAdversaire()[positionX][y].getIdBateau()!=-1)
						ok=false;
				}
			}
		}
		getBateaux()[nb_bateaux]=new BateauModel(this,nb_bateaux,positionX,positionY,t,orientation);
		this.notifyNewBateau(getBateaux()[nb_bateaux].debutX,getBateaux()[nb_bateaux].debutY,getBateaux()[nb_bateaux].type,
				getBateaux()[nb_bateaux].o,nb_bateaux);
		nb_bateaux++;
		

		
	}
	
	/**
	 * Vérifier si un joueur a gagné (en fonction du nombre de coups pris
	 */
	@Override
	public void verifWin() {
		
		
		
		if(this.coupsPrisJ1==Constantes.NB_COUPS_NECESSAIRES){
			System.out.println("WINNER");
			this.notifyMsgScore("WINNER");
			this.notifyMsgScore2("LOSER");
			this.notifyWinner();
		}
		if(this.coupsPrisJ2==Constantes.NB_COUPS_NECESSAIRES){
			System.out.println("LOSER");
			this.notifyMsgScore("LOSER");
			this.notifyMsgScore2("WINNER");
			this.notifyLoser();
		}
	}
	
	/**
	 * Notifier qu'une case a déjà été bombardée
	 * @param x abscisse de la case
	 * @param y ordonnée de la case
	 */
	public boolean isAlreadyShot(int x, int y){
		return (casesJoueur[x][y].getV()==CaseValue.TOUCHE);
	}
	

	
	/**
	 * Bombarder l'adversaire
	 * Envoie BOMB X Y au serveur
	 * Reçoit MISSED/TOUCHE
	 * @param x
	 * @param y
	 */
	public void bombJoueur(int x, int y){
		//DEMANDER ET RECEVOIR REPONSE
		
		this.casesJoueur[x][y].setV(CaseValue.TOUCHE);
		this.sendToServer("BOMB "+x+" "+y);
		this.setEtat(EtatClient.OPPONENT_TURN);
	}
	

	/**
	 * Reinitialiser la grille
	 */
	@Override
	public void reinit() {
		for(int i=0; i<longueurX; i++){
			for(int j=0; j<longueurY; j++){
				casesJoueur[i][j].setV(CaseValue.NONE);
			}
		}
		this.notifyReinit();
	}

	/**
	 * Fermer la socket du serveur
	 */
	public void closeSocket(){
		try {
			this.sendToServer("ABORT");
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Test de console, affiche le contenu de la grille
	 */
	public void afficher()
	{
		for(int i=0; i<longueurX; i++){
			for(int j=0; j<longueurY; j++){
				int idB=getCasesAdversaire()[i][j].getIdBateau();
				if(idB!=-1){
					System.out.print(getBateaux()[idB].getType()+"("+getCasesAdversaire()[i][j].getV()+")"+"\t");
				
				}else
					System.out.print(TypeBateau.NONE.name()+"("+getCasesAdversaire()[i][j].getV()+")"+"\t");
			}
			System.out.println();
		}
		
		for(int i=0; i<longueurX; i++){
			for(int j=0; j<longueurY; j++){
				int idB=getCasesAdversaire()[i][j].getIdBateau();
				System.out.print(idB+" ");
				
			}
			System.out.println();
		}
		
	}

	/**
	 * Getter
	 * @return
	 */
	public CaseModel[][] getCasesAdversaire() {
		return casesAdversaire;
	}

	/**
	 * Setter
	 * @param casesAdversaire
	 */
	public void setCasesAdversaire(CaseModel casesAdversaire[][]) {
		this.casesAdversaire = casesAdversaire;
	}

	public BateauModel[] getBateaux() {
		return bateaux;
	}

	public void setBateaux(BateauModel bateaux[]) {
		this.bateaux = bateaux;
	}

	
	 
	
	

	



	
}


