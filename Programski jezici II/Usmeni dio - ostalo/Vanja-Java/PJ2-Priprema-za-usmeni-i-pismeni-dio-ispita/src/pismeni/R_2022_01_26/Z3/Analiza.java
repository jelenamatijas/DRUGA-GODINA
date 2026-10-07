package pismeni.R_2022_01_26.Z3;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Analiza {
    static final String ekstenzija = ".java";
    static ArrayList<String> packages = new ArrayList<>();
    static int paketi = 0;
    static int klase = 0;
    static int linije = 0;
    static int komentari = 0;

    public Analiza() {

    }

    // broj paketa
    // broj klasa
    // broj linija koda
    // broj komentara
    public void analiza(File file) {
        if (file.isFile() && file.getPath().endsWith(ekstenzija)) {
            obradiFajl(file);
        } else if (file.isDirectory()) {
            File[] fajlovi = file.listFiles();
            for (File f : fajlovi) {
                if (f.isDirectory())
                    analiza(f);
                else if (f.isFile() && f.getPath().endsWith(ekstenzija)) {
                    obradiFajl(f);
                }
            }
        }
        ispis();
    }

    public void ispis() {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(Main.putanja))))) {
            pw.println("Broj paketa: " + paketi);
            pw.println("Broj klasa: " + klase);
            pw.println("Broj linija: " + linije);
            pw.println("Broj komentara: " + komentari);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void obradiFajl(File file) {
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            linije += lines.size();
            for (String line : lines) {
                if (line.contains("//")) {
                    komentari++;
                }
                if (line.startsWith("package")) {
                    String[] parts = line.split(" ");
                    if (!packages.contains(parts[1])) {
                        packages.add(parts[1]);
                        paketi++;
                    }
                }
                if (line.contains("class")) {
                    klase++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}