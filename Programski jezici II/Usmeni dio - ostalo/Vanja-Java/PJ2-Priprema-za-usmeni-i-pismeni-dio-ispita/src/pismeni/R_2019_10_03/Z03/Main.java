package pismeni.R_2019_10_03.Z03;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    static ArrayList<Objekat> mapa = new ArrayList<>();
    static String PATH = "src/pismeni.R_2019_10_03/Z03/treci.txt"; // Inače pišemo ./naziv_fajla

    public static void funkcija(Lambda l, int n) {
        l.function(n);
    }

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Path.of(PATH));
            for (String line : lines) {
                String[] e = line.split(" ");
                mapa.add(new Objekat(Integer.parseInt(e[1]), e[0]));
            }
            mapa.forEach(System.out::println);

            Scanner scan = new Scanner(System.in);
            System.out.print("n =  ");
            final int n = scan.nextInt();
            Lambda l = (broj) -> {
                System.out.println(" kljucevi veci od " + broj);
                mapa.stream().filter(o -> o.key > broj).forEach(System.out::println);
            };
            funkcija(l, n);


            mapa.stream().sorted((o1, o2) -> o1.key - o2.key).limit(10).forEach(e -> System.out.println(e.key));

            List<Objekat> list = IntStream.range(0, mapa.size()).filter(i -> i % 2 == 0 && mapa.get(i).key > 5).mapToObj(mapa::get).collect(Collectors.toList());
            try (BufferedWriter bw = Files.newBufferedWriter(Path.of("./fajl.csv"))) {
                list.forEach(o -> {
                    try {
                        bw.write(o.getKey(), 0, o.getKey().length());
                        bw.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Suma kljuceva " + mapa.stream().mapToInt(o -> o.key).sum());
            System.out.println("Ukupan broj kljuceva " + mapa.stream().mapToInt(o -> o.key).distinct().count());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    interface Lambda {
        void function(int broj);
    }
}