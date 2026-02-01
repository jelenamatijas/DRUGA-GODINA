package main;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import model.Faktura;
import model.Narudzbenica;
import parser.FakturaParser;
import parser.NarudzbenicaParser;
import validator.Validator;

public class App {

    private static final String CSV_PATH = "data.csv";
    private static ArrayList<Faktura> fakture = new ArrayList<>();
    private static ArrayList<Narudzbenica> narudzbenice = new ArrayList<>();
    private static String path = "result.txt";

    public static void main(String[] args) {

        if (args.length > 0) {
            path = args[0];
            System.out.println("Fajl za rezultate je: " + path);
        }

        // 1.
        proccessData();
        // 2. i 3.
        validateData();
    }

    public static void validateData() {
        // 2.
        Predicate<Faktura> fakturaUkupanIznos = f -> f.getStavke().stream()
                .mapToDouble(s -> s.getCijena() * s.getKolicina()).sum() == f.getUkupanIznos();
        ;
        Predicate<Faktura> fakturaBrojStavki = f -> f.getStavke().size() >= 1;

        Predicate<Narudzbenica> narudzbenicaBrojStavki = n -> n.getStavke().size() >= 1;
        Predicate<Narudzbenica> narudzbenicaStavkaUkupno = n -> n.getStavke().stream()
                .filter(s -> (s.getCijena() * s.getKolicina() < 0)).collect(Collectors.toList()).size() == 0;

        // validacija faktura
        ArrayList<Predicate<Faktura>> fakturaPredicates = new ArrayList<>();
        fakturaPredicates.add(fakturaUkupanIznos);
        fakturaPredicates.add(fakturaBrojStavki);

        ArrayList<Faktura> validneFakture = new ArrayList<>();
        ArrayList<Faktura> nevalidneFakture = new ArrayList<>();
        fakture.forEach(f -> {
            if (Validator.validate(fakturaPredicates, f)) {
                validneFakture.add(f);
            } else {
                nevalidneFakture.add(f);
            }
        });

        // validacija narudzbenica
        ArrayList<Predicate<Narudzbenica>> narudzbenicaPredicates = new ArrayList<>();
        narudzbenicaPredicates.add(narudzbenicaBrojStavki);
        narudzbenicaPredicates.add(narudzbenicaStavkaUkupno);

        ArrayList<Narudzbenica> validneNarudzbenice = new ArrayList<>();
        ArrayList<Narudzbenica> nevalidneNarudzbenice = new ArrayList<>();
        narudzbenice.forEach(n -> {
            if (Validator.validate(narudzbenicaPredicates, n)) {
                validneNarudzbenice.add(n);
            } else {
                nevalidneNarudzbenice.add(n);
            }
        });

        System.out.println("Nevalidne fakture: " + nevalidneFakture.size());
        nevalidneFakture.forEach(i -> System.out.println(i.getSifra() + " Broj stavki: " + i.getStavke().size()));

        System.out.println("Nevalidne narudzbenice: " + nevalidneNarudzbenice.size());
        nevalidneNarudzbenice.forEach(i -> System.out.println(i.getSifra() + " Broj stavki: " + i.getStavke().size()));

        System.out.println("Sortirane validne fakture:");
        validneFakture.stream()
                .sorted((f1, f2) -> Double.valueOf(f2.getUkupanIznos()).compareTo(Double.valueOf(f1.getUkupanIznos())))
                .limit(5).forEach(i -> System.out.println("Sifra " + i.getSifra() + " Ukupno: " + i.getUkupanIznos()));

        // 3. upis u fajl
        Path destination = Paths.get(path);

        double prosjecanBrojStavki = validneNarudzbenice.stream().mapToDouble(n -> n.getStavke().size()).average()
                .orElse(0);
        double ukupno = fakture.stream().mapToDouble(f -> f.getUkupanIznos()).sum();

        String content = "Broj parsiranih fakura: " + fakture.size() + "." + " Ukupan iznos za placanje" + ukupno
                + " Broj parsiranih narudzbenica: " + narudzbenice.size() + "."
                + " Prosjecan broj stavki u narudzbenicama: " + prosjecanBrojStavki;

        try {
            Files.write(destination, content.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void proccessData() {

        FakturaParser fakturaParser = new FakturaParser(CSV_PATH);
        NarudzbenicaParser narudzbenicaParser = new NarudzbenicaParser(CSV_PATH);

        ConsoleThread keyboard = new ConsoleThread(narudzbenicaParser, fakturaParser);
        keyboard.start();

        fakturaParser.start();
        narudzbenicaParser.start();

        try {
            narudzbenicaParser.join();
            fakturaParser.join();

            keyboard.setActive(false);

            fakture = fakturaParser.getDataList();
            narudzbenice = narudzbenicaParser.getDataList();

            System.out.println("Ukupno faktura " + fakture.size());
            fakture.forEach(item -> System.out.println(item.getSifra() + " " + item.getStavke().size()));

            System.out.println("Ukupno narudzbenica " + narudzbenice.size());
            narudzbenice.forEach(item -> System.out.println(item.getSifra() + " " + item.getStavke().size()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}