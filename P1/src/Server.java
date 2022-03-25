import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Marc Fernández Parra
 * @author Germán Pérez Bordera
 */
public class Server {

    public static String HOST = "localhost";
    public static int PORT = 42069;

    private final ServerSocket socket;

    public Server() {
        System.out.println("Starting the server...");
        try {
            this.socket = new ServerSocket(Server.PORT);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void start() {
        try {
            System.out.println("Waiting for a client...");

            Socket client = this.socket.accept();
            System.out.println("Client online.");

            ReadThread rt = new ReadThread(client, ReadThread.CLIENT);
            rt.start();

            WriteThread wt = new WriteThread(client);
            wt.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void main(String[] args) {

        Server s = new Server();
        s.start();

    }

}
