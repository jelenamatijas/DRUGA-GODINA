package pismeni.R_2023_04_26.Z2;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static final String folder = "results";

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Unesite putanju do fajla/foldera.");
            return;
        }

        new File(folder).mkdir();
        try {
            File file = new File(args[0]);
            if (file.isFile()) {
                System.out.println("IT IS A FILE");
                List<String> lines = Files.readAllLines(file.toPath());
                System.out.println(lines);
                List<String> sorted = lines.stream().sorted(String::compareTo).collect(Collectors.toList());
                System.out.println("SORTED" + sorted);
                Map<String, List<String>> mapa = sorted.stream().collect(Collectors.groupingBy(s -> s.split("")[0]));

                for (Map.Entry<String, List<String>> entry : mapa.entrySet()) {
                    String naziv = folder + File.separator + "sortirani" + entry.getKey() + ".txt";
                    try (PrintWriter pw = new PrintWriter(new FileWriter(naziv))) {
                        entry.getValue().forEach(s -> {
                            pw.println(s);
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(new File(naziv).getAbsolutePath() + " broj redova " + entry.getValue().size());
                }

            } else if (file.isDirectory()) {
                System.out.println("IT IS A FOLDER");
                File[] files = file.listFiles();
                ArrayList<File> fajlovi = new ArrayList<>(Arrays.asList(files));

                // max
                OptionalLong max = fajlovi.stream().mapToLong(File::length).max();
                System.out.println("MAX " + max.getAsLong());
                fajlovi.stream().filter(f -> f.length() == max.getAsLong()).forEach(System.out::println);

                // total
                System.out.println("TOTAL SPACE " + fajlovi.stream().mapToLong(File::length).sum());

                // grouping by extension
                fajlovi.stream().collect(Collectors.groupingBy(f -> f.getName().split("\\.")[1])).entrySet().stream().sorted((e1, e2) -> {
                    return e1.getKey().compareTo(e2.getKey());
                }).forEach(System.out::println);

                // map
                Map<String, File> mapa = new HashMap<>();
                fajlovi.forEach(f -> mapa.put(f.getAbsolutePath(), f));
                mapa.entrySet().forEach(System.out::println);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}