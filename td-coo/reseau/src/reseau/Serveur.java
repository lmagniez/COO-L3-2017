package reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

	public static void main(String[] arg){
		ServerSocket socketserver;
		Socket socketduserveur;
		BufferedReader in;
		PrintWriter out;
		
		try{
			socketserver = new ServerSocket(1789);
			System.out.println("Le serveur ecoute sur le port "+socketserver.getLocalPort());
			socketduserveur = socketserver.accept();
			System.out.println("Un joueur s'est connecté !");
			out=new PrintWriter(socketduserveur.getOutputStream());
			out.println("Vous etes connecte joueur!");
			out.flush();
			socketserver.close();
			socketduserveur.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
}
