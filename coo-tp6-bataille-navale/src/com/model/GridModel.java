package com.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

import com.vue.grid.CaseValueVue;

import reseau.Shifumi.Recevoir_infos;

/**
 * Classe correspondant au modèle de la grille
 * @author loick
 */

public class GridModel extends AbstractModel{

	protected int longueurX;
	protected int longueurY;
	
	protected CaseModel casesJoueur[][];//cases où le joueur intéragit
	private CaseModel casesAdversaire[][];//cases où l'adversaire intéragit (contient les bateaux)
	protected BateauModel bateaux[];
	public static int nb_bateaux=0;
	protected boolean isIA[]; 

	private int nbBateauxTouchesJoueur,nbBateauxTouchesAdversaire;
	
	
	//serveur
	protected Recevoir_infos infos;
	protected Socket socket;
	protected BufferedReader in;
	protected PrintWriter out;
	
	
	
	/**
	 * Constructeur de la grille
	 * @param rows nombre de lignes
	 * @param longueurY nombre de colonnes
	 * @param nbJR nombre de jetons requis pour gagner
	 */
	public GridModel(Socket s, int longueurX, int longueurY){
		
		this.longueurX=longueurX;
		this.longueurY=longueurY;
		this.bateaux=new BateauModel[Constantes.NB_BATEAUX];
		this.nbBateauxTouchesJoueur=0;
		this.nbBateauxTouchesAdversaire=0;
		
		
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
	
	public void lancerCommunication(){
		try{
			
			System.out.println("Demande de connexion");
			this.in=new BufferedReader (new InputStreamReader(this.socket.getInputStream()));
			this.out=new PrintWriter(this.socket.getOutputStream());
			String message_distant = this.in.readLine();
			System.out.println(message_distant);
			
			
			this.out.println("Joueur: La reponse du joueur");
			this.out.flush();
			
			/*
			String msg_distant = in.readLine();
			System.out.println("has red:");
			System.out.println("Recevoir_infos: "+msg_distant);
			
			this.getScore().setMsg(msg_distant);
			*/
			
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
	
	
	public void notifierBateaux(){
		for(int i=0; i<Constantes.NB_BATEAUX; i++){
			this.notifyNewBateau(bateaux[i].debutX,bateaux[i].debutY,bateaux[i].type,
					bateaux[i].o,i);
		}
	}

	public void genererBateaux(){
		nb_bateaux=0;
		placerBateau(TypeBateau.BATEAU_2);
		placerBateau(TypeBateau.BATEAU_3);
		placerBateau(TypeBateau.BATEAU_3);
		placerBateau(TypeBateau.BATEAU_4);
		placerBateau(TypeBateau.BATEAU_5);
	}
	
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
		bateaux[nb_bateaux]=new BateauModel(this,nb_bateaux,positionX,positionY,t,orientation);
		this.notifyNewBateau(bateaux[nb_bateaux].debutX,bateaux[nb_bateaux].debutY,bateaux[nb_bateaux].type,
				bateaux[nb_bateaux].o,nb_bateaux);
		nb_bateaux++;
		

		
	}
	
	@Override
	public void verifWin() {
		if(this.nbBateauxTouchesJoueur==Constantes.NB_COUPS_NECESSAIRES)
			this.notifyWinner();
		if(this.nbBateauxTouchesAdversaire==Constantes.NB_COUPS_NECESSAIRES)
			this.notifyLoser();
	}
	
	public boolean isAlreadyShot(int x, int y){
		return (casesJoueur[x][y].getV()==CaseValue.TOUCHE);
	}
	
	/**
	 * Se faire bombarder par l'adversaire
	 * Recoit BOMB X Y du serveur
	 * Renvoie au serveur TOUCHE ou MISSED
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public void bombAdversaire(int x, int y){
			
		int idB=casesJoueur[x][y].getIdBateau();
		casesJoueur[x][y].setV(CaseValue.TOUCHE);
		if(idB!=-1){
			bateaux[idB].setNbTouches(bateaux[idB].getNbTouches()+1);
			this.nbBateauxTouchesAdversaire++;
			this.notifyBombAdversaire(x, y, CaseValueVue.TOUCHE);
			//renvoyer TOUCHE
		}
		else{
			this.notifyBombAdversaire(x, y, CaseValueVue.PLOUF);
			//renvoyer MISSED
		}
	}
	
	/**
	 * Bombarder l'adversaire
	 * Envoie BOMB X Y au serveur
	 * Recoit MISSED/TOUCHE
	 * @param x
	 * @param y
	 * @return
	 */
	public void bombJoueur(int x, int y){
		
		//DEMANDER ET RECEVOIR REPONSE
		
		this.sendToServer("BOMB "+x+" "+y);
		
		/*
		String reponse="TOUCHE";
		casesJoueur[x][y].setV(CaseValue.TOUCHE);
		if(reponse.equals("TOUCHE")){
			this.notifyBombJoueur(x, y, CaseValueVue.TOUCHE);
			this.nbBateauxTouchesJoueur++;
		}
		if(reponse.equals("MISSED")){
			this.notifyBombJoueur(x, y, CaseValueVue.PLOUF);
		}*/
		this.notifyTour();
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
					System.out.print(bateaux[idB].getType()+"("+getCasesAdversaire()[i][j].getV()+")"+"\t");
				
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

	public CaseModel[][] getCasesAdversaire() {
		return casesAdversaire;
	}

	public void setCasesAdversaire(CaseModel casesAdversaire[][]) {
		this.casesAdversaire = casesAdversaire;
	}

	
	 
	
	

	



	
}


