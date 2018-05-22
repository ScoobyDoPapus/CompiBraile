import java.net.*;
import java.io.*;

public class MultiServer extends Thread {
	 private Socket socket = null;

	    public MultiServer(Socket socket) {
		super("MultiServer");
		this.socket = socket;
	    }

	    public void run() {
		try {
		    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		    BufferedReader in = new BufferedReader(
					    new InputStreamReader(
					    socket.getInputStream()));

		    String inputLine;
		    String outputLine;
		    Protocol _protocol = new Protocol();
		    outputLine = _protocol.processInput(null);
		    out.println(outputLine);

		    while ((inputLine = in.readLine()) != null) {
		    
			outputLine = _protocol.processInput(inputLine);
			System.out.println(outputLine);
			out.println(outputLine);
			if (outputLine.equals("Bye"))
			    break;
		    }
		    out.close();
		    in.close();
		    socket.close();

		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
}
