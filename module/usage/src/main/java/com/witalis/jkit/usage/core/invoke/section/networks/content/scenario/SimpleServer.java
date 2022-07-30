package com.witalis.jkit.usage.core.invoke.section.networks.content.scenario;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Desc: Simple server
 * User: Wellaxis
 * Date: 19.01.2022
 */
public class SimpleServer implements Runnable {
    private final int port;

    private static final Logger logger = Logger.getLogger(SimpleServer.class.getName());

    static {
        logger.setLevel(Level.INFO);
    }

    public SimpleServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("Server has been started...");
            Socket clientSocket = serverSocket.accept();
            logger.info("Client has been connected...");
            process(clientSocket);
        } catch (IOException e) {
            logger.warning(() -> "Simple server errors: " + e.getMessage());
            logger.throwing(SimpleServer.class.getSimpleName(), "run", e);
        } finally {
            logger.info("Server has been stopped...");
        }
    }

    private void process(Socket socket) {
        try (
            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
        ) {
            // read message from client
            String message = dis.readUTF();
            logger.info(() -> "Client's message: " + message);
            // send answer to client
            String answer = "Thanks for your activity!";
            dos.writeUTF(answer);
            dos.flush();
        } catch (IOException e) {
            logger.warning(() -> "Simple server errors: " + e.getMessage());
            logger.throwing(SimpleServer.class.getSimpleName(), "process", e);
        }
    }

    public static void main(String[] args) {
        SimpleServer server = new SimpleServer(7777);
        server.run();
    }
}
