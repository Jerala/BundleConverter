package edu.jerala.bc;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Properties;

public class BundleWriter {
    private boolean readUnicodeSymbols = false;
    private boolean copyComments = false;
    private String destination;
    private Object[][] data;

    public BundleWriter(Object[][] data, String destination, boolean readUnicodeSymbols, boolean copyComments) {
        this.data = data;
        this.destination = destination;
        this.readUnicodeSymbols = readUnicodeSymbols;
        this.copyComments = copyComments;
    }

    public void write() {
        Properties prop = new Properties();

        for (int i = 0; i < data.length; i++)
            prop.setProperty((String) data[i][0], (String) data[i][1]);

        try {
            if(readUnicodeSymbols) prop.store(new OutputStreamWriter(
                            new FileOutputStream(destination), "UTF-8"),
                    null);
            else prop.store(new OutputStreamWriter(
                            new FileOutputStream(destination)),
                    null);

            if(copyComments) {} // TODO

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
