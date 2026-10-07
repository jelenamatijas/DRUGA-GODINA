package pismeni.R_2022_02_09.Z03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Main {

    static HashMap<String, Integer> putanje = new HashMap<>();
    static String ekstenzija;

    static String rijec;

    public static void obidji(File file) {
        if (file.isFile() && file.getPath().endsWith(ekstenzija)) {
            prebrojRijeci(file);
        } else if (file.isDirectory()) {
            File[] fajlovi = file.listFiles();
            for (File f : fajlovi) {
                if (f.isDirectory())
                    obidji(f);
                else if (f.isFile() && f.getPath().endsWith(ekstenzija))
                    prebrojRijeci(f);
            }
        }
    }

    private static void prebrojRijeci(File file) {
        int broj = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linija = null;
            while ((linija = br.readLine()) != null) {
                if (linija.toUpperCase().contains(rijec))
                    broj++;
            }
            putanje.put(file.getPath(), broj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("nedostaju argumenti komandne linije");
            return;
        }

        File root = new File(args[0]);
        rijec = args[1].toUpperCase();
        ekstenzija = args[2];
        obidji(root);
        putanje.forEach((key, value) -> System.out.println(key + " broj rijeci " + value));
    }
}