package Shifumi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class Accepter_clients implements Runnable {
	private Serveur2 serveur;
	private ServerSocket socketserver;
	private Socket socket;
	private int nbrClient=1;
	private int idJoueur;
	
	PrintWriter out;
	
	public Accepter_clients(Serveur2 serveur, ServerSocket s, int idJoueur)
	{
		this.serveur=serveur;
		this.socketserver=s;
		this.idJoueur=idJoueur;
		
	}
	public void run()
	{
		BufferedReader in;
		try{
			System.out.println("Serveur lancé");
			while(true){
				socket=socketserver.accept();
				System.out.println("Le client num "+nbrClient+" est connecte ! (Thread "+idJoueur+")");
				nbrClient++;
				
				out=new PrintWriter(socket.getOutputStream());
				out.println("Vous etes connecte joueur "+idJoueur+" !");
				out.flush();
				
				
				while(true){
					in=new BufferedReader (new InputStreamReader(socket.getInputStream()));
					String msg = in.readLine();
					System.out.println("Serveur: Message recu du Thread "+ idJoueur +" : "+msg);
					
					if(msg!=null&&(msg.equals("feuille")||msg.equals("ciseau")||msg.equals("pierre"))){
						set_value(msg,idJoueur);
						serveur.maj_affichage();
					}
					
					
					if(msg==null){
						break;
					}
				}
				socket.close();
				
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public void set_value(String s, int id){
		if(id==0){
			if(s.equals("feuille"))
				serveur.choixJ1=Choix.FEUILLE;
			if(s.equals("pierre"))
				serveur.choixJ1=Choix.PIERRE;
			if(s.equals("ciseau"))
				serveur.choixJ1=Choix.CISEAU;
		}
		
		if(id==1){
			if(s.equals("feuille"))
				serveur.choixJ2=Choix.FEUILLE;
			if(s.equals("pierre"))
				serveur.choixJ2=Choix.PIERRE;
			if(s.equals("ciseau"))
				serveur.choixJ2=Choix.CISEAU;
		}
		
	}
	
	public void envoyer_message(String msg) throws IOException{
		out=new PrintWriter(socket.getOutputStream());
		out.println(msg);
		out.flush();
	}
	
	
	public void fermerServer(){
		try {
			System.out.println("Arret du serveur...");
			socketserver.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}