package edu.jerala.bc;

import java.util.*;
import java.io.*;

public class BundleConverter {
    private boolean readUnicodeSymbols = false;
    private boolean copyComments = false;
    private String fileName;

    public BundleConverter(String fileName) {
        if(fileName.endsWith(".java")) this.fileName = fileName.substring(0, fileName.length()-5);
        else this.fileName = fileName;
    }

    public void setReadingOfUnicodeSymbols(boolean setReadingOfUnicodeSymbols) {
        readUnicodeSymbols = setReadingOfUnicodeSymbols;
    }

    public void setCopyComments(boolean setCopyComments) {
        copyComments = setCopyComments;
    }

    /**
     * Converts bundles from .java file to .property file.
     */
    public void convert() {
        Object[][] data = null;
        try {
            data = getData();
        }
        catch(MissingResourceException e) {
            System.out.println(e.getMessage());
            return;
        }
        writeInProperties(data);
    }

    /**
     * @return bundles as an array or null if source file not found.
     */
    private Object[][] getData() throws MissingResourceException {
        try {
            Object[][] data;
            ResourceBundle bd = ResourceBundle.getBundle(fileName, Locale.getDefault(), this.getClass().getClassLoader());

            Set<String> keys = bd.keySet();
            data = new Object[keys.size()][2];
            int j = 0;
            for (Iterator<String> i = keys.iterator(); i.hasNext(); j++) {
                data[j][0] = i.next();
                data[j][1] = bd.getObject((String) data[j][0]);
            }
            return data;
        }catch(Exception e) {System.out.println(e.getMessage()); }
        return null;
    }

    /**
     * Writes bundles to a .properties file with the same name.
     */
    private void writeInProperties(Object[][] data) {
        try {
            File currentDirFile = new File(".");
            String helper = currentDirFile.getAbsolutePath();
            String currentDir = helper.substring(0, helper.length());// - currentDirFile.getCanonicalPath().length());
            String dest = System.getProperty("user.dir") + "/src/" + fileName.replace('.', '/');
            BundleWriter bw = new BundleWriter(data, dest + ".properties", readUnicodeSymbols, copyComments);
            bw.write();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


