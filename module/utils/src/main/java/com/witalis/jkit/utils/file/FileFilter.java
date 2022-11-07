package com.witalis.jkit.utils.file;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.io.File;
import java.util.regex.Pattern;

/**
 * Desc: File Filter
 * User: Wellaxis
 * Date: 19.04.2019
 */
@Slf4j
public class FileFilter {
    private static java.io.FileFilter dbfFilter;

    public static void initializeDBFFilter() {
        dbfFilter = new java.io.FileFilter() {
            public static final String DBF = "dbf";
            public final Pattern DBF_PATTERN = Pattern.compile("^(.+)([\\u002E]{1})([d[D]]{1}[b[B]]{1}[f[F]]{1})$");

            public boolean accept(File f) {
                if (f != null) {
                    if (f.isDirectory()) {
                        return true;
                    }
                    String type = getFileType(f);
                    return type != null && type.equals(DBF);
                }
                return false;
            }

            public String getFileType(File f) {
                if (f != null && f.isFile()) {
                    String fileName = f.getName();
                    if (DBF_PATTERN.matcher(fileName).find()) {
                        return DBF;
                    }
                }
                return null;
            }
        };
    }

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Root Folder");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setMultiSelectionEnabled(false);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File folder = chooser.getSelectedFile();
            File[] files = folder.listFiles(dbfFilter);
            if (files != null && files.length > 0) {
                log.info("Files Detected: {}", files.length);
            } else {
                log.info("No Data Found");
            }
        }
    }
}
