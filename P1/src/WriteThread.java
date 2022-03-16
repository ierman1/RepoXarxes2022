import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class WriteThread extends Thread {

    public static String SERVER = "Server";
    public static String CLIENT = "Client";

    private Socket socket;
    private String type;

    public WriteThread(Socket socket, String type) {
        this.socket = socket;
        this.type = type;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                String message = scanner.nextLine();

                if (message.length() != 0) {
                    PrintWriter output = new PrintWriter(this.socket.getOutputStream(), true);
                    output.print(this.type + ": " + message + "\n");
                    output.flush();

                    if (message.equals("FI")) {
                        this.socket.close();
                    }
                } else {
                    System.out.println("!> Message can't be empty...");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.interrupt();
        }
    }

}
