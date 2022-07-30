package com.witalis.jkit.usage.core.invoke.section.networks.content.datagram;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.util.UUID;

/**
 * Desc: Datagram client
 * User: Wellaxis
 * Date: 18.01.2022
 */
@Slf4j
@Getter
@Setter
public class DatagramClient extends DatagramProgram {
    private String serverHost;
    private int serverPort;

    public DatagramClient(String name, String host, int port, String serverHost, int serverPort) throws IOException {
        super(name, host, port);
        setServerHost(serverHost);
        setServerPort(serverPort);
        log.info("CLIENT> thread[" + getName() + "]: start");
    }

    @Override
    public void run() {
        while (!finalize) {
            log.debug("CLIENT> counter: " + getCounter());
            if (socket.isBound()) {
                try {
                    // send request
                    var request = UUID.randomUUID().toString();
                    buf = request.getBytes();
                    DatagramPacket packet = new DatagramPacket(
                        buf,
                        buf.length,
                        new InetSocketAddress(
                            getServerHost(),
                            getServerPort()
                        )
                    );
                    log.info("CLIENT> request[send-" + getCounter() + "]: {}", request);
                    socket.send(packet);
                    // take response
                    packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    var response = new String(packet.getData(), 0, packet.getLength()) + " | status - ok";
                    log.info("CLIENT> response[take-" + getCounter() + "]: {}", response);
                    incrementCounter();
                } catch (IOException e) {
                    log.error("Datagram client errors", e);
                }
            }
            // wait
            try {
                sleep(100);
            } catch (InterruptedException e) {
                log.error("Datagram client errors", e);
            }
        }
        socket.close();
        log.info("CLIENT> thread[" + getName() + "]: stop");
    }
}
