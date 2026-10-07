package pismeni.R_2021_07_30.Z03;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static final String PATH = "./quotes/";
    public static final String FILE = "./statistics.txt";

    public static void main(String args[]) {
        long begin = System.currentTimeMillis();
        FileSystemReader fsr = new FileSystemReader();
        QuoteStorage qs = new QuoteStorage();
        QuoteReader qr = new QuoteReader();
        qr.start();
        FileWatcher fw = new FileWatcher();
        fw.start();
        ArrayList<String> themes = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        String s = "";
        s = sc.nextLine();
        while (!"STOP".equals(s)) {
            themes.add(s);
            fsr.ocitaj(qs, s);
            s = sc.nextLine();
        }
        long end = System.currentTimeMillis();
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(FILE)))) {
            pw.println("Vrijeme trajanja simulacije: " + ((end - begin) / 1000) + " sekundi.");
            pw.println("Teme:");
            for (String st : themes)
                pw.println(st);
            pw.println("Broj novih fajlova: " + fw.cnt);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}