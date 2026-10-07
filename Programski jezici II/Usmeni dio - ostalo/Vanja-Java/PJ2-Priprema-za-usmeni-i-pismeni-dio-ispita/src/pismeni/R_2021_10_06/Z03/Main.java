package pismeni.R_2021_10_06.Z03;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static Random rand = new Random();
    public static ArrayList<Oglas> oglasi = new ArrayList<>();

    public static void main(String[] args) {
        generisiOglase();
        System.out.println(" Ukupan broj objavljenih oglasa u jednom danu (za svaki datum pojedinačno)");
        oglasi.stream().collect(Collectors.groupingBy(o -> o.datum)).entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEach((e) -> System.out.println(e.getKey() + " " + e.getValue().size()));

        System.out.println(" Prosjecna ponudjena plata u IT");
        oglasi.stream().filter(o -> o.kategorija.equals(Kategorija.IT)).mapToInt(o -> o.plata).average().ifPresent(System.out::println);

        System.out.println(" Najcesci grad u kom se nudi posao");
        oglasi.stream().collect(Collectors.groupingBy(o -> o.grad)).entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().size() - e1.getValue().size()).limit(1)
                .forEach(e -> System.out.println(e.getKey() + " broj_oglasa " + e.getValue().size()));

        System.out.println("Prikaz svih oglasa grupisanih po mjesecu");
        oglasi.stream().collect(Collectors.groupingBy(o -> o.datum.split("-")[1])).forEach((mjesec, oglasi) -> System.out.println(mjesec + " " + oglasi));

        System.out.println("\nPrikaz svih oglasa sortiranih po vremenu trajanja opadajuce");
        oglasi.stream().sorted((o1, o2) -> o2.trajanje - o1.trajanje).forEach(System.out::println);

        System.out.println("\nPrikaz najbolje placenog posla za svaku kategoriju");
        oglasi.stream().collect(Collectors.groupingBy(o -> o.kategorija)).forEach((key, oglasi) -> {
            System.out.print(key + " ");
            oglasi.stream().sorted((o1, o2) -> Double.compare(o2.plata, o1.plata)).limit(1).forEach(System.out::println);
        });

        System.out.println("\nProsjecan broj godina radnog iskustva ukupno i posebno za svaku kategoriju");
        oglasi.stream().collect(Collectors.groupingBy(o -> o.kategorija)).forEach((key, oglasi) -> {
            System.out.print(key + " prosjecan broj godina radnog iskustva ");
            oglasi.stream().mapToInt(o -> o.iskustvo).average().ifPresent(System.out::println);
        });
        System.out.print("UKUPNO ");
        oglasi.stream().mapToInt(o -> o.iskustvo).average().ifPresent(System.out::println);

    }

    public static void generisiOglase() {
        String[] slova = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        for (int i = 0; i < Kategorija.values().length; i++) {
            Kategorija k = Kategorija.values()[i];
            for (int j = 0; j < 12; j++) {
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
                String datum = sdf.format(new Date(rand.nextInt() * 1000L));
                System.out.println(datum);
                Oglas o = new Oglas("Oglas" + j, "Opis oglasa", datum,
                        rand.nextInt(30) + 1, rand.nextInt(500) + 500,
                        rand.nextInt(10) + 1, slova[rand.nextInt(26)] + "Grad", k);
                oglasi.add(o);
            }
        }
    }
}