package reseau.Shifumi;

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
	private int idClient;
	
	PrintWriter out;
	
	public Accepter_clients(Serveur2 serveur, ServerSocket s, int idClient)
	{
		this.serveur=serveur;
		this.socketserver=s;
		this.idClient=idClient;
		
	}
	public void run()
	{
		BufferedReader in;
		try{
			System.out.println("Serveur lancé");
			while(true){
				
				socket=socketserver.accept();
				System.out.println("Le client num "+nbrClient+" est connecte ! (Thread "+idClient+")");
				
				//Connection d'un client, on passe un booléen a vrai
				if(idClient==0)
					this.serveur.hasClient1=true;
				else if(idClient==1)
					this.serveur.hasClient2=true;
				nbrClient++;
				//envoie message connection
				out=new PrintWriter(socket.getOutputStream());
				out.println("Vous etes connecte joueur "+idClient+" !");
				out.flush();
				
				serveur.addText("ok");
				
				
				//Attente de la seconde connection
				out.println("En attente de l'adversaire... ");
				out.flush();
				while(!this.serveur.hasClient2||!this.serveur.hasClient1){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				//confirmation des deux joueurs connectés
				serveur.addText("Let's go!");
				if(idClient==0){
					out.println("GO J1");
					out.flush();
				}
				else if(idClient==1){
					out.println("GO J2");
					out.flush();
				}
				
				
				
				while(true){
					in=new BufferedReader (new InputStreamReader(socket.getInputStream()));
					String msg = in.readLine();
					
					System.out.println("Serveur: Message recu du Thread "+ idClient +" : "+msg);
					serveur.addText(msg);
					
					if(msg.equals("ABORT")){
						serveur.addText("abort"+idClient);
						break;
					}
					
					String[] msg_splited = msg.split("\\s+");
					if(msg_splited[0].equals("BOMB")||msg_splited[0].equals("PLOUF")||msg_splited[0].equals("TOUCHE")){
						//int bombx=Integer.parseInt(msg_splited[1]);
						//int bomby=Integer.parseInt(msg_splited[2]);
						if(idClient==0){
							this.serveur.client2.out.println(msg);
							this.serveur.client2.out.flush();
						}
						else if(idClient==1){
							this.serveur.client1.out.println(msg);
							this.serveur.client1.out.flush();
						}
					}
					
					if(msg==null){
						break;
					}
				}
				if(idClient==0)
					this.serveur.hasClient1=false;
				if(idClient==1)
					this.serveur.hasClient2=false;
				System.out.println("(II) closing connection from "+socket.getInetAddress()+":"+socket.getPort());
				socket.close();
				
			}
			
		}catch(IOException e){
			e.printStackTrace();
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