package com.witalis.jkit.usage.core.invoke.section.io.context;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Desc: I/O base class
 * User: Wellaxis
 * Date: 02.02.2022
 */
@Slf4j
public class IO {
    public static final char MD_SYMBOL = 's';
    public static final char MD_LINE = 'l';

    private char mode;
    private boolean interactive;
    private InputStream is;
    private OutputStream os;

    public IO (char mode, boolean interactive, InputStream is, OutputStream os) {
        super();
        this.is = is;
        this.os = os;
        this.mode = mode;
        this.interactive = interactive;
    }

    public void read() throws IOException {
        if (interactive) return;
        BufferedReader br = new BufferedReader(new InputStreamReader(this.is));
        long currentTime = System.currentTimeMillis();
        switch (this.mode) {
            case MD_SYMBOL: {
                log.info("To exit type 'q'.");
                char character;
                do {
                    character = (char) br.read();
                    log.info("" + character);
                } while (character != 'q' || System.currentTimeMillis() - currentTime < 1000L);
                break;
            }
            case MD_LINE: {
                log.info("To exit type 'quit'.");
                String line;
                do {
                    line = br.readLine();
                    log.info(line);
                } while (!line.equals("quit") || System.currentTimeMillis() - currentTime < 1000L);
                break;
            }
        }
    }

    public void write(char c) throws IOException {
        this.os.write(c);
    }

    public void print(String line) throws IOException {
        PrintWriter pw = new PrintWriter(this.os, true, Charset.defaultCharset());
        pw.println("--------------> " + line);
    }
}