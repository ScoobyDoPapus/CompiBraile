import java.io.*;
import java.net.*;
public class Client {
	 public static void main(String[] args) throws IOException {

		    Socket clientSocket = null;   //client socket
		    PrintWriter out = null;
		    BufferedReader in = null;

		    try {
		      clientSocket = new Socket("localhost", 8082);
		      out = new PrintWriter(clientSocket.getOutputStream(), true);
		      in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		    } catch (UnknownHostException e) {
		      System.err.println("Don't know about host: manis.csi.ull.es.");
		      System.exit(1);
		    } catch (IOException e) {
		      System.err.println("Couldn't get I/O for the connection to: manis.csi.ull.es.");
		      System.exit(1);
		    }

		    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		    String fromServer;
		    String fromUser;

		    while ((fromServer = in.readLine()) != null) { // El sistema toma el texto enviado por
		      System.out.println("Server: " + fromServer); //el server y lo imprime en consola
		      if (fromServer.equals("Bye."))               //una vez que diga "bye" se cierra el proceso
		        break;
		        
		      fromUser = stdIn.readLine();               //Imprime el texto escrito por el usuario
		      if (fromUser != null) {
		        System.out.println("Client: " + fromUser);
		        out.println(fromUser);
		      }
		    }

		    out.close();
		    in.close();
		    stdIn.close();
		    clientSocket.close();
		 }
}
