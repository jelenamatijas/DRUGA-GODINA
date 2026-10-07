package pismeni.R_2022_01_26.Z03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

// Lab 8
public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("niste unijeli argument komande linije");
            return;
        }

        Path path = Path.of(args[0]);
        String pattern = "*.java";
        Finder finder = new Finder(pattern);
        Files.walkFileTree(path, finder);
        System.out.println(finder);
        for (Map.Entry<String, Integer> entry : finder.paketBrojKlasa.entrySet()) {
            String p = entry.getKey();
            Integer value = entry.getValue();
            System.out.println( p + " " + value);
        }
    }
}