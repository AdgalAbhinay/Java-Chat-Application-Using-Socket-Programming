package ChatApplication.client;

import ChatApplication.client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatGUI extends JFrame {

    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;

    private Client client;

    public ChatGUI() {

        setTitle("Java Chat Application");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(chatArea);

        messageField = new JTextField();

        sendButton = new JButton("Send");

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(messageField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        client = new Client("localhost", 1234, this);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        setVisible(true);
    }

    private void sendMessage() {

        String message = messageField.getText();

        if (!message.isEmpty()) {
            client.sendMessage(message);
            displayMessage("Me: " + message);
            messageField.setText("");
        }
    }

    public void displayMessage(String message) {
        chatArea.append(message + "\n");

        Toolkit.getDefaultToolkit().beep();
    }

    public static void main(String[] args) {
        new ChatGUI();
    }
}