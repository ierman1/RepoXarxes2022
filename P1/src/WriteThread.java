import sun.misc.Signal;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Marc Fernández Parra
 * @author Germán Pérez Bordera
 */
public class WriteThread extends Thread {

    private final Socket socket;

    public WriteThread(Socket socket) {
        this.socket = socket;
    }

    private void sendCommand(Command command) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(this.socket.getOutputStream());
            output.writeObject(command);
            output.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        Signal.handle(new Signal("INT"),
                signal -> {
                    sendCommand(new Command(CommandType.SHUTDOWN, "Connection closed."));
                    System.exit(0);
                });

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String message = scanner.nextLine();

            if (message.length() != 0) {
                if (message.equals("FI")) {
                    this.sendCommand(new Command(CommandType.SHUTDOWN, message));
                    System.out.println("Finishing chat...");
                    System.exit(0);
                } else {
                    this.sendCommand(new Command(CommandType.SEND_MESSAGE, message));
                }
            } else {
                System.out.println("!> Message can't be empty...");
            }
        }
    }

}
