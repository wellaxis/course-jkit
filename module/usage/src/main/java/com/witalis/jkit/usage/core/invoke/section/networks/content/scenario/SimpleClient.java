package com.witalis.jkit.usage.core.invoke.section.networks.content.scenario;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Desc: Simple client
 * User: Wellaxis
 * Date: 19.01.2022
 */
public class SimpleClient implements Runnable {
    private final String serverHost;
    private final int serverPort;

    private static final Logger logger = Logger.getLogger(SimpleClient.class.getName());

    static {
        logger.setLevel(Level.INFO);
    }

    public SimpleClient(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(serverHost, serverPort)) {
            logger.info(() -> "Client has been started...");
            process(socket);
        } catch (IOException e) {
            logger.warning(() -> "Simple client errors: " + e.getMessage());
            logger.throwing(SimpleClient.class.getSimpleName(), "run", e);
        } finally {
            logger.info(() -> "Client has been stopped...");
        }
    }

    private void process(Socket socket) {
        try (
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);
        ) {
            // sent message to server
            dos.writeUTF("Hi, I'm a new client!");
            dos.flush();
            // read answer from server
            String answer = dis.readUTF();
            logger.info(() -> "Server's answer: " + answer);
        } catch (IOException e) {
            logger.warning(() -> "Simple client errors: " + e.getMessage());
            logger.throwing(SimpleClient.class.getSimpleName(), "process", e);
        }
    }

    public static void main(String[] args) {
        SimpleClient client = new SimpleClient("localhost", 7777);
        client.run();
    }
}
