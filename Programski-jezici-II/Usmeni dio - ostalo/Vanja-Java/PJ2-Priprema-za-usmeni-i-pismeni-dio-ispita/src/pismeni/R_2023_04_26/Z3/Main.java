package pismeni.R_2023_04_26.Z3;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.stream.Stream;

public class Main {

    static TreeSet<Student> studenti = new TreeSet<>();
    static ArrayList<Student> nagradjeni = new ArrayList<>();
    static boolean END = false;

    public static void main(String[] args) {

        // a1 + b
        Stream.generate(Student::new).limit(2000).forEach(s -> {
            studenti.add(s);
        });
        studenti.stream().forEach(System.out::println);
        // c

        try {
            for (int i = 0; i < 50; i++) {
                Nit nit = new Nit(i);
            }

            while (!Main.END) {
                Thread.sleep(100);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // d
        System.out.println(" Pronađeni su: ");
        nagradjeni.stream().forEach(System.out::println);
    }
}