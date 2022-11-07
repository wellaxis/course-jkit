package com.witalis.jkit.utils.db;

import lombok.extern.slf4j.Slf4j;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class DatabaseConfig {
    public static final String DB_NAME = "jdbc:oracle:thin:@127.0.0.1:1521:ngs";
    public static final String DB_USER = "bos";
    public static final String DB_PASS = "bos";

    public static void main(String[] args) throws Exception {
        Connection connection = null;
        try {
            if (true) {
                log.info("===================== Direct Oracle JDBC Connection. Start =====================");
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                } catch (ClassNotFoundException e) {
                    log.info("Where is your Oracle JDBC Driver?");
                    e.printStackTrace();
                    return;
                }
                log.info("Oracle JDBC Driver Registered.");
                log.info("Connecting...");
                try {
                    connection = DriverManager.getConnection(
                        DB_NAME,
                        DB_USER,
                        DB_PASS
                    );
                } catch (SQLException e) {
                    log.info("Connection Failed.");
                    e.printStackTrace();
                }
                if (connection != null) {
                    String alter = "ALTER SESSION SET NLS_DATE_FORMAT='YYYY-MM-DD HH24:MI:SS.\"0\"' nls_numeric_characters='. '";
                    CallableStatement ps = connection.prepareCall(alter);
                    var res = ps.execute();
                    log.info("Session: NLS changes status - " + res);
                    ps.close();
                }
                log.info("===================== Direct Oracle JDBC Connection. End =====================");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
