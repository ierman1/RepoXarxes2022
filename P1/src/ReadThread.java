import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @author Marc Fernández Parra
 * @author Germán Pérez Bordera
 */
public class ReadThread extends Thread {

    public static String SERVER = "Server";
    public static String CLIENT = "Client";

    private final Socket socket;
    private final String source;

    public ReadThread(Socket socket, String source) {
        this.socket = socket;
        this.source = source;
    }

    public void run() {
        try {
            while (true) {
                ObjectInputStream input = new ObjectInputStream(this.socket.getInputStream());
                Command command = (Command) input.readObject();

                switch (command.getType()) {
                    case SEND_MESSAGE:
                        if (command.getMessage() != null && command.getMessage().length() > 0) {
                            System.out.println(this.source + ": «" + command.getMessage() + "»");
                        }
                        break;
                    case SHUTDOWN:
                        if (command.getMessage().length() > 0)
                            System.out.println(this.source + ": «" + command.getMessage() + "»");
                        System.out.println("Chat has finished.");
                        System.exit(0);
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            this.interrupt();
        }
    }

}
