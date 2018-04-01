package edu.jerala.bc;

public class BundleConverterMain {
    public static void main(String[] args) {
        BundleConverter bc = new BundleConverter(args[0]);
        bc.convert();
    }
}
