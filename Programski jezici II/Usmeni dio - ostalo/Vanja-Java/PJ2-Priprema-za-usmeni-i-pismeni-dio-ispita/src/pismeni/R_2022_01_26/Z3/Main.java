package pismeni.R_2022_01_26.Z3;

import java.io.File;

public class Main {


    static final String putanja = "./fajl.txt";

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Unesite putanju do foldera.");
            return;
        }
        new Analiza().analiza(new File(args[0]));
    }
}