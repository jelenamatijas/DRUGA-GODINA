package pismeni.R2018_09_06.Z03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static ArrayList<Objekat> obj = new ArrayList<>();

    public static void main(String[] args) {
        try {
            List<String> linije = Files.readAllLines(Path.of("data.txt"));

            linije.forEach(s -> {
                int kljuc = Integer.parseInt(s.split(" ")[1]);
                String vr = s.split(" ")[0];
                Objekat o = new Objekat(kljuc, vr);
                obj.add(o);
            }); // 1

            // 2 TODO
            // 3
            obj.stream().sorted(Comparator.reverseOrder()).limit(10).forEach(o -> System.out.println(o.kljuc));
            // 4
            Files.createFile(Path.of("izlaz.txt"));
            obj.stream().sorted(Comparator.reverseOrder()).limit(10).forEach(o -> {
                try {
                    Files.write(Path.of("izlaz.txt"), (String.valueOf(o.kljuc) + "\n").getBytes(), StandardOpenOption.APPEND);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // 5
            obj.stream().filter(o -> o.kljuc > 10).forEach(System.out::println);


            // 6
            System.out.println("Suma kljuceva " + obj.stream().mapToInt(o -> o.kljuc).sum());
            System.out.println("Ukupan broj kljuceva " + obj.stream().mapToInt(o -> o.kljuc).count());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}