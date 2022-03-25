import java.io.IOException;
import java.net.Socket;

/**
 * @author Marc Fernández Parra
 * @author Germán Pérez Bordera
 */
public class Client {

    private final Socket socket;

    public Client() {
        try {
            System.out.println("Connecting to server [" + Server.HOST + ":" + Server.PORT + "] ...");
            this.socket = new Socket(Server.HOST, Server.PORT);
            System.out.println("Connected!");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void start() {
        ReadThread rt = new ReadThread(this.socket, ReadThread.SERVER);
        rt.start();

        WriteThread wt = new WriteThread(this.socket);
        wt.start();
    }

    public static void main(String[] args) {

        Client c = new Client();
        c.start();

    }

}
