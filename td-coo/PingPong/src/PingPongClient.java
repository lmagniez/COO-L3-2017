
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class PingPongClient {

	private static final String LO_ADDR = "127.0.0.1";
	public static final int DEFAULT_LOCAL_PORT = 1989;
	private PrintStream defaultOutStream = System.out;
	private InputStream defaultInStream = System.in;

	public static void main(String args[]) throws UnknownHostException, IOException, InterruptedException {
		if(args.length > 1) {
			new PingPongClient(args[0], Integer.valueOf(args[1]));
		} else if (args.length > 0) {
			new PingPongClient(args[0]);
		} else {
			new PingPongClient();
		}
	}
	
	private PingPongClient() throws UnknownHostException, IOException, InterruptedException {
		this(LO_ADDR);
	}
	
	public PingPongClient(String hostAddr) throws UnknownHostException, IOException, InterruptedException {
		this(hostAddr, DEFAULT_LOCAL_PORT);
	}

	public PingPongClient(String hostAddr, Integer hostPort) throws UnknownHostException, IOException, InterruptedException {
		Socket socket = new Socket(InetAddress.getByName(hostAddr), hostPort);
		BufferedReader cliReader = new BufferedReader(new InputStreamReader(defaultInStream));
		BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		String line;
		while((line = cliReader.readLine()) != null) {
			if(line.length() == 0) break;
			socketWriter.write(line+"\n");
			socketWriter.flush();
			defaultOutStream.println("RECV: "+socketReader.readLine());
		}
		socket.close();
	}

}