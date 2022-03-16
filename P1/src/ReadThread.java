import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread {

    private Socket socket;

    public ReadThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            while (true) {
                BufferedReader input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                String message = input.readLine();
                if (message != null && message.length() > 0) {
                    if (message == "FI") {
                        System.out.println("Chat has finished.");
                        System.exit(0);
                    }
                    System.out.println(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.interrupt();
        }
    }

}
