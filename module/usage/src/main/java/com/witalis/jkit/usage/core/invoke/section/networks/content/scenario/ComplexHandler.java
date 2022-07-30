package com.witalis.jkit.usage.core.invoke.section.networks.content.scenario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Desc: Complex handler
 * User: Wellaxis
 * Date: 19.01.2022
 */
public class ComplexHandler extends Thread {
    private final String serverName;
    private final Socket socket;
    private String clientName;
    private int counter;

    private static final Logger logger = Logger.getLogger(ComplexHandler.class.getName());

    static {
        logger.setLevel(Level.INFO);
    }

    public ComplexHandler(String serverName, Socket clientSocket) {
        this.serverName = serverName;
        this.socket = clientSocket;
        this.counter = 0;
    }

    @Override
    public void run() {
        try (
            var is = socket.getInputStream();
            var dis = new DataInputStream(is);
            var os = socket.getOutputStream();
            var dos = new DataOutputStream(os);
        ) {
            // greetings
            welcome(dis, dos);
            logger.info(() -> "Server[" + serverName + "] -> start session with client: " + clientName);
            // processing
            process(dis, dos);
        } catch (IOException e) {
            logger.warning(() -> "Complex handler errors: " + e.getMessage());
            logger.throwing(ComplexHandler.class.getSimpleName(), "run", e);
        } finally {
            logger.info(() -> "Server[" + serverName + "] -> close session with client: " + clientName);
        }
    }

    private void welcome(DataInputStream dis, DataOutputStream dos) throws IOException {
        try {
            // authentication request
            dos.writeUTF("Hello, who are you?");
            // authentication response
            clientName = dis.readUTF();
            logger.info(() -> "Server[" + serverName + "] <- authentication response: I'm " + clientName);
        } catch (IOException e) {
            throw new IOException("Complex handler greetings failed", e);
        }
    }

    private void process(DataInputStream dis, DataOutputStream dos) throws IOException {
        while (true) {
            try {
                // send message to client from server
                dos.writeUTF("Please, send a message... or 'exit' command to terminate connection.");
                dos.flush();

                // receive the message from client
                String message = dis.readUTF();
                if (message.equalsIgnoreCase("exit")) {
                    logger.info(() -> "Server[" + serverName + "] <- the termination command has been received from client: " + clientName);
                    logger.info(() -> "Server[" + serverName + "] -> handler is closing connection with client: " + clientName);
                    socket.close();
                    break;
                } else {
                    // public message
                    logger.info(() -> "Server[" + serverName + "] <- message from client " + clientName + ": " + message);
                    // answer message
                    dos.writeUTF("Thanks for your activity, it's your " + ++counter + "' message!");
                    dos.flush();
                }
            } catch (IOException e) {
                throw new IOException("Complex handler processing failed", e);
            }
        }
    }
}
