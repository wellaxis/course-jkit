package com.witalis.jkit.usage.core.invoke.section.networks.content.datagram;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Desc: Datagram server
 * User: Wellaxis
 * Date: 18.01.2022
 */
@Slf4j
@Getter
@Setter
public
class DatagramServer extends DatagramProgram {

    public DatagramServer(String name, String host, int port) throws IOException {
        super(name, host, port);
        log.info("SERVER> thread[" + getName() + "]: start");
    }

    @Override
    public void run() {
        while (!finalize) {
            log.debug("SERVER> counter: " + getCounter());
            if (socket.isBound()) {
                try {
                    // take request
                    DatagramPacket packet = new DatagramPacket(
                        buf,
                        buf.length
                    );
                    socket.receive(packet);
                    var request = new String(packet.getData(), 0, packet.getLength());
                    if (ObjectUtils.isEmpty(request)) return;
                    log.info("SERVER> request[take-" + getCounter() + "]: {}", request);
                    // send response
                    var response = request + " | done at " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
                    buf = response.getBytes();
                    packet = new DatagramPacket(
                        buf,
                        buf.length,
                        packet.getAddress(),
                        packet.getPort()
                    );
                    log.info("SERVER> response[send-" + getCounter() + "]: {}", response);
                    socket.send(packet);
                    incrementCounter();
                } catch (IOException e) {
                    log.error("Datagram server errors", e);
                }
            }
        }
        socket.close();
        log.info("SERVER> thread[" + getName() + "]: stop");
    }
}
