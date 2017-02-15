package reseau.Shifumi;

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

import com.vue.grid.Score;
import com.vue.grid.VueGrid;

public class Recevoir_infos implements Runnable {

	private static final String LO_ADDR = "127.0.0.1";
	public static final int DEFAULT_LOCAL_PORT = 1791;
	private PrintStream defaultOutStream = System.out;
	private InputStream defaultInStream = System.in;
	
	protected JTextArea l1;
	protected VueGrid vue;
	protected Socket socket;
	protected PrintWriter out;
	protected BufferedReader in;
	protected boolean running=true;
	
	public Recevoir_infos(VueGrid vueGrid,Socket socket, BufferedReader in, PrintWriter out){;
		this.vue=vueGrid;
		this.socket=socket;
		this.out=out;
		this.in=in;
		
	}
	
	public void run() 
	{
		
		/*
		try{
			
			Socket socket = new Socket(InetAddress.getByName(LO_ADDR), DEFAULT_LOCAL_PORT);
			BufferedReader cliReader = new BufferedReader(new InputStreamReader(defaultInStream));
			BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			String line;
			while((line = cliReader.readLine()) != null) {
				if(line.length() == 0) break;
				System.out.println("line! "+line);
				socketWriter.write(line+"\n");
				socketWriter.flush();
				defaultOutStream.println("RECV: "+socketReader.readLine());
			}
			System.out.println("le close");
			socket.close();
		
		}catch(IOException e){
			e.printStackTrace();
		}
		*/
		
		
		
		System.out.println("A l'écoute...");
		
		try {
			
			//this.in=new BufferedReader (new InputStreamReader(this.socket.getInputStream()));
			//this.out=new PrintWriter(this.socket.getOutputStream());
			
			//while(running){
				System.out.println("va readline:");
				String msg_distant = in.readLine();
				System.out.println("has red:");
				System.out.println("Recevoir_infos: "+msg_distant);
				
				vue.getScore().setMsg(msg_distant);
				
				//vue.afficherMsgFenetre(msg_distant);
			//}
		} 
		
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
}
