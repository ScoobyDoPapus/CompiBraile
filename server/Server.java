import java.net.*;
import java.io.*;
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        boolean listening = true;

        try {
            serverSocket = new ServerSocket(8084);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 8084.");
            System.exit(-1);
        }

        while (listening)
          new MultiServer(serverSocket.accept()).start();

        serverSocket.close();
    }
}
