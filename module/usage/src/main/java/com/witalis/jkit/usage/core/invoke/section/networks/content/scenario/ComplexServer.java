package com.witalis.jkit.usage.core.invoke.section.networks.content.scenario;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Desc: Complex server
 * User: Wellaxis
 * Date: 19.01.2022
 */
public class ComplexServer implements Runnable {
    private final String name;
    private final int port;
    private final int timeout;

    private static final Logger logger = Logger.getLogger(ComplexServer.class.getName());

    static {
        logger.setLevel(Level.INFO);
    }

    public ComplexServer(String name, int port, int timeout) {
        this.name = name;
        this.port = port;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setSoTimeout(timeout);
            logger.info(() -> "Server[" + name + "] -> it has been started...");
            // processing
            process(serverSocket);
        } catch (IOException e) {
            logger.warning(() -> "Complex server errors: " + e.getMessage());
            logger.throwing(ComplexServer.class.getSimpleName(), "run", e);
        } finally {
            logger.info(() -> "Server[" + name + "] -> it has been stopped...");
        }
    }

    private void process(ServerSocket serverSocket) throws IOException {
        while (!Thread.currentThread().isInterrupted()) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                logger.info("Server[" + name + "] <- A new client has been connected: " + clientSocket);
                Thread handler = new ComplexHandler(name, clientSocket);
                handler.start();
            } catch (IOException e) {
                if (clientSocket != null) clientSocket.close();
                logger.warning(() -> "Complex server errors: " + e.getMessage());
                logger.throwing(ComplexServer.class.getSimpleName(), "process", e);
            }
        }
    }

    public static void main(String[] args) {
        ComplexServer server = new ComplexServer("Server", 7777, 1_000);
        server.run();
    }
}
