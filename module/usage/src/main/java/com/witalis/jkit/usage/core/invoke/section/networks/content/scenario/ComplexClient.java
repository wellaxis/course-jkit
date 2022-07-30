package com.witalis.jkit.usage.core.invoke.section.networks.content.scenario;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Desc: Complex client
 * User: Wellaxis
 * Date: 19.01.2022
 */
public class ComplexClient implements Runnable {
    private final String name;
    private final String serverName;
    private final String serverHost;
    private final int serverPort;

    private static final Logger logger = Logger.getLogger(ComplexClient.class.getName());

    static {
        logger.setLevel(Level.INFO);
    }

    public ComplexClient(String name, String serverName, String serverHost, int serverPort) {
        this.name = name;
        this.serverName = serverName;
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    @Override
    public void run() {
        String client = null;
        try (
            Socket clientSocket = new Socket(serverHost, serverPort);
            var is = clientSocket.getInputStream();
            var dis = new DataInputStream(is);
            var os = clientSocket.getOutputStream();
            var dos = new DataOutputStream(os);
        ) {
            // greetings
            welcome(dis, dos);
            logger.info(() -> "Client[" + name + "] -> it has been started...");
            // processing
            process(dis, dos);
        } catch (IOException e) {
            logger.warning(() -> "Complex client errors: " + e.getMessage());
            logger.throwing(ComplexHandler.class.getSimpleName(), "run", e);
        } finally {
            logger.info(() -> "Client[" + name + "] -> it has been stopped...");
        }
    }

    private void welcome(DataInputStream dis, DataOutputStream dos) throws IOException {
        try {
            // authentication request
            String request = dis.readUTF();
            logger.info(() -> "Client[" + name + "] <- authentication request: " + request);
            // authentication response
            dos.writeUTF(name);
        } catch (IOException e) {
            throw new IOException("Complex handler greetings failed", e);
        }
    }

    private void process(DataInputStream dis, DataOutputStream dos) throws IOException {
        while (true) {
            try {
                // read message from server
                String information = dis.readUTF();
                logger.info(() -> "Client[" + name + "] <- request: " + information);

                // send message to server
                String message = ComplexDictionary.takeQuote();
                logger.info(() -> "Client[" + name + "] -> message: " + message);
                dos.writeUTF(message);
                dos.flush();

                if (message.equalsIgnoreCase("exit")) {
                    logger.info(() -> "Client[" + name + "] -> termination: it's time to close the connection with server...");
                    break;
                } else {
                    // read answer from server
                    String answer = dis.readUTF();
                    logger.info(() -> "Client[" + name + "] <- response: " + answer);
                }
            } catch (IOException e) {
                throw new IOException("Complex client processing failed", e);
            }
        }
    }

    public static void main(String[] args) {
        ComplexClient client = new ComplexClient("Client", "Server", "localhost", 7777);
        client.run();
    }
}
