package com.witalis.jkit.utils.file;

import java.io.*;

/**
 * Desc: Log parser
 * User: Wellaxis
 * Date: 26.05.2019
 */
public class LogParser {
    // constant
    public static final String FILE = "file";
    public static final String MODE = "mode";
    // mode
    public static final char MODE_SOURCE = 's';
    public static final char MODE_CLASS  = 'c';
    // variable
    private File file;
    private char mode;

    public LogParser(File file, char mode) {
        setFile(file);
        setMode(mode);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public char getMode() {
        return mode;
    }

    public void setMode(char mode) {
        this.mode = mode;
    }

    private void parse() {
        // read file
        try (FileReader fr = new FileReader(getFile());
             BufferedReader br = new BufferedReader(fr);
             FileWriter fw = new FileWriter(getFile() + ".log");
             BufferedWriter bw = new BufferedWriter(fw)
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                line = process(line);
                if (line != null && line.length() > 0) {
                    bw.write(line + '\n');
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private String process(String line) {
        String result = null;
        if (line != null) {
            switch (getMode()) {
                case MODE_CLASS: {
                    if (line.endsWith(".class")) {
                        result = null;
                    } else if (line.endsWith("/metadata")) {
                        result = null;
                    } else if (line.endsWith(".properties") ||
                        line.endsWith(".xml") ||
                        line.endsWith(".java") ||
                        line.matches("^(.*)\\.(?i)(.*)$")
                    ) {
                        result = line;
                    }
                    break;
                }
                case MODE_SOURCE: {
                    if (line.endsWith(".class")) {
                        result = null;
                    } else if (line.endsWith("/metadata")) {
                        result = null;
                    } else if (line.contains("/rtbs/v1/rmi/csapi/") ||
                        line.contains("/rtbs/v1/rtre/ws/") ||
                        line.contains("/servlet/") ||
                        line.contains("/wsapi/t2/dto/") ||
                        line.contains("/wsapi/ws/dto/monitor/") ||
                        line.contains("/monitor/agent/generated/") ||
                        line.contains("/util/maintenance/soapAPI/") ||
                        line.contains("/gwt/MonitorAgentWeb/") ||
                        line.contains("/gwt/selfcareBootstrap/selfcare/")
                    ) {
                        result = null;
                    } else if (line.endsWith(".properties") ||
                        line.endsWith(".ctl") ||
                        line.endsWith(".sql") ||
                        line.endsWith(".xml") ||
                        line.endsWith(".java") ||
                        line.matches("^(.*)\\.(?i)(.*)$")
                    ) {
                        result = line;
                    }
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LogParser logParser = null;
        String file = null;
        String mode = null;
        // initialisation
        for (String arg : args) {
            if (arg != null && arg.length() > 0) {
                String[] kvp = arg.split("=");
                if (kvp != null && kvp.length > 0) {
                    String key = kvp[0];
                    if (key != null) {
                        String value = kvp[1];
                        if (value != null && value.length() > 0) {
                            switch (key) {
                                case FILE: {
                                    file = value;
                                    break;
                                }
                                case MODE: {
                                    mode = value;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        // verification
        assert file != null : "Log file should be specified";
        assert mode != null : "Log mode should be specified";
        logParser = new LogParser(new File(file), mode.charAt(0));
        logParser.parse();
    }
}
