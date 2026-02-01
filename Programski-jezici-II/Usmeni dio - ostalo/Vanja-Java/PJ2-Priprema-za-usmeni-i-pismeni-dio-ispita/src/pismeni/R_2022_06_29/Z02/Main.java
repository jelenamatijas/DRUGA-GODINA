package pismeni.R_2022_06_29.Z02;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Proizvod> proizvodi;

    public static void main(String[] args) {

        // DESERIJALIZACIJA
        String fajlIn = "";
        File[] files = new File("src/pismeni.R_2022_06_29/Z02").listFiles();
        assert files != null;
        for (File f : files) {
            if (f.getPath().endsWith(".ser")) {
                System.out.println(f.getPath());
                fajlIn = f.getPath(); // posljednji je najnovija kolekcija
            }
        }

        if (!fajlIn.isEmpty()) {
            //System.out.println("ser" + fajlIn);
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fajlIn))) {
                proizvodi = (ArrayList<Proizvod>) ois.readObject();
                //proizvodi.forEach(System.out::println);
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }

        } else {
            proizvodi = new ArrayList<>();
        }

        String input = "";
        while (!"END".equals(input)) {
            System.out.println();
            System.out.println("a1. Pregled svih proizvoda");
            System.out.println("b. Pregled jednog proizvoda po sifri");
            System.out.println("c. Dodavanje proizvoda");
            System.out.println("d. Brisanje jednog proizvoda po sifri");
            System.out.println("e. Spisak svih proizvoda grupsianih po tipu");
            System.out.println("f. spisak svih prouzvoda čija je cijena u zadatom opsegu od x do y");

            System.out.print("Unesite opciju ");
            input = scan.nextLine();
            switch (input) {
                case "a1": {
                    if (proizvodi.isEmpty())
                        System.out.println("Nema proizvoda.");
                    proizvodi.forEach(System.out::println);
                    break;
                }
                case "b": {
                    System.out.print("Unesite id ");
                    String id = scan.nextLine();
                    proizvodi.stream().filter(p -> p.id.equals(id)).forEach(System.out::println);
                    break;
                }
                case "c": {
                    System.out.print("Unesite id ");
                    String id = scan.nextLine();
                    System.out.print("Unesite naziv ");
                    String naziv = scan.nextLine();
                    System.out.print("Unesite opis ");
                    String opis = scan.nextLine();
                    System.out.print("Unesite cijena ");
                    int cijena = scan.nextInt();
                    System.out.print("Unesite tip (0-1)");
                    int tip = scan.nextInt();
                    Tip tipInput = Tip.KNJIGA;
                    if (tip == 1) {
                        tipInput = Tip.SVESKA;
                    }
                    Proizvod proizvod = new Proizvod(id, naziv, opis, cijena, tipInput);
                    proizvodi.add(proizvod);
                    System.out.println("Proizvod je dodan.");
                    break;
                }
                case "d": {
                    System.out.print("Unesite id ");
                    String id = scan.nextLine();
                    Proizvod proizvod = proizvodi.stream().filter(p -> p.id.equals(id)).limit(1).collect(Collectors.toList()).get(0);
                    proizvodi.remove(proizvod);
                    break;
                }
                case "e":
                    proizvodi.stream().collect(Collectors.groupingBy(p -> p.tip)).forEach((k, v) -> System.out.println(k + " " + v));
                    break;
                case "f": {
                    System.out.print("Unesite donju cijenu ");
                    int min = Integer.parseInt(scan.nextLine());
                    System.out.print("Unesite gornju cijenu ");
                    int max = Integer.parseInt(scan.nextLine());
                    proizvodi.stream().filter(p -> p.cijena > min && p.cijena < max).forEach(System.out::println);
                    break;
                }
            }
        }

        // SERIJALIZACIJA
        ArrayList<Proizvod> copyProizvodi = new ArrayList<>(proizvodi);

        String fajl = "src/pismeni.R_2022_06_29/Z02/" + Main.class.getName() + "_" + new Date().getTime() + ".ser";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fajl))) {
            oos.writeObject(copyProizvodi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}