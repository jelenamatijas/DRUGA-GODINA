package pismeni.R_2021_06_16.Z03;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class Main {
    public ArrayList<String> putanje = new ArrayList<>();
    public String ekstenzija;

    public void obidji(File putanja) {
        if (putanja.isFile() && putanja.getPath().endsWith(ekstenzija)) {
            putanje.add(putanja.getPath());
        } else if (putanja.isDirectory()) {
            File[] files = putanja.listFiles();
            for (File f : files) {
                if (f.isDirectory())
                    obidji(f);
                else if (f.isFile() && f.getPath().endsWith(ekstenzija))
                    putanje.add(f.getPath());
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("nedostaju argumenti komandne linije");
            return;
        }
        Main m = new Main();
        m.ekstenzija = args[2];
        File root = new File(args[0]);
        System.out.println(root.isDirectory());
        m.obidji(root);
        // ispis
        m.putanje.forEach(System.out::println);
        System.out.println();
        Path destDir = Paths.get(args[1]);
        destDir.toFile().mkdir(); // napravi direktorijum ako ne postoji
        // kopiranje
        m.putanje.forEach(f -> {
            try {
                Path source = Paths.get(f);
                // kopiranje jednog fajla u drugi
                Files.copy(source, Path.of(destDir.toString(), source.getFileName().toString()), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}