package ChatApplication.client;

import java.io.*;
import java.net.*;

public class Client {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ChatGUI gui;

    public Client(String serverAddress, int port, ChatGUI gui) {

        this.gui = gui;

        try {

            socket = new Socket(serverAddress, port);

            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            out = new PrintWriter(
                    socket.getOutputStream(),
                    true
            );

            new Thread(new MessageListener()).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    class MessageListener implements Runnable {

        @Override
        public void run() {

            try {

                String message;

                while ((message = in.readLine()) != null) {
                    gui.displayMessage(message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        ChatGUI gui = new ChatGUI();

        new Client("localhost", 1234, gui);
    }
}
