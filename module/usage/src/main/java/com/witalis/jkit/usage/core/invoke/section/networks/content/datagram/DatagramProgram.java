package com.witalis.jkit.usage.core.invoke.section.networks.content.datagram;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.DatagramSocket;

/**
 * Desc: Datagram program
 * User: Wellaxis
 * Date: 18.01.2022
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public abstract class DatagramProgram extends Thread {
    public static final int threshold = 5;
    protected String host;
    protected int port;
    protected long counter;
    protected boolean finalize = false;
    protected DatagramSocket socket = null;
    protected byte[] buf = new byte[256];

    protected DatagramProgram(String name, String host, int port) throws IOException {
        super(name);
        setName(name);
        setHost(host);
        setPort(port);
        setSocket(new DatagramSocket(port));
    }

    protected void incrementCounter() {
        counter++;
        if (getCounter() >= threshold) {
            setFinalize(true);
        }
    }
}
