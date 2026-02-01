package pismeni.R_2021_06_16.Z02;

import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static HashSet<Knjiga> knjige = new HashSet<>();
    public static HashSet<Knjiga> knjige1 = new HashSet<>();

    public static void main(String[] args) {
        dodajKnjige();
        spajanje();
        sortirajKnjige();
        filtriraj();
        sumiranje();
        najduziNaslov();
        najkaraciNaslov();
    }

    public static void spajanje() {
        knjige1.stream().forEach(k -> {
            knjige.add(k);
        });
        knjige1.clear();
        long brPisaca = knjige.stream().map(k -> k.pisac).distinct().count();
        System.out.println("Broj knjiga u prvoj grupi: " + knjige.size() + " Broj razlicitih pisaca " + brPisaca);
    }

    public static void sortirajKnjige() { // opadajuće
        knjige.stream().sorted((k1, k2) -> k2.godinaIzdavanja - k1.godinaIzdavanja).forEach(System.out::println);
    }

    public static void filtriraj() {
        knjige.stream().collect(Collectors.groupingBy(k -> k.zanr)).entrySet().forEach(System.out::println);
    }

    public static void sumiranje() {
        System.out.println("Suma godina izdavanja " +
                knjige.stream().filter(k -> k.zanr.equals(Zanr.PUTOPIS) && k.godinaIzdavanja % 3 == 0).mapToInt(k -> k.godinaIzdavanja).sum());
    }

    public static void najduziNaslov() {
        System.out.println("Najduži naslov " + knjige.stream().max(Comparator.comparing(k -> k.naslov.length())).get().naslov);
    }

    public static void najkaraciNaslov() {
        System.out.println("Najkraći naslov " + knjige.stream().min(Comparator.comparing(k -> k.naslov.length())).get().naslov);
    }

    private static void dodajKnjige() {
        Zanr[] zanrovi = Zanr.values();
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            int broj = i + 1;
            Knjiga k = new Knjiga("Naslov" + broj, "Pisac" + broj, rand.nextInt(30) + 1990, zanrovi[rand.nextInt(zanrovi.length)]);
            Knjiga k2 = new Knjiga("Naslov" + broj, "Pisac" + broj, rand.nextInt(30) + 1990, zanrovi[rand.nextInt(zanrovi.length)]);
            knjige.add(k);
            knjige1.add(k2);
        }
    }
}