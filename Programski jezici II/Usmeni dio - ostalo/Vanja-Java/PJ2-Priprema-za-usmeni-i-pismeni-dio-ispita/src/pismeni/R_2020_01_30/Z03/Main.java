package pismeni.R_2020_01_30.Z03;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Collectors;


@SuppressWarnings("SpellCheckingInspection")
public class Main {
    public static HashSet<Zec> grupa1 = new HashSet<>();
    public static HashSet<Zec> grupa2 = new HashSet<>();
    public static Random rand = new Random();

    public static void main(String[] args) {
        generisiZeceve();
        System.out.println("GRUPA 2");
        grupa2.stream().forEach(System.out::println);

        // a1
        // spajanje grupe zečeva - spojiti dvije grupe, tako što se spajaju u prvu, a1 iz druge grupe se svi uklanjaju nakon spajanja.
        grupa2.stream().forEach(z -> {
            grupa1.add(z);
        });
        grupa2.clear();

        // b
        grupa1.stream().collect(Collectors.groupingBy(z -> z.hrana)).forEach((key, value) -> System.out.println(key + " " + value));

        // c
        grupa1.stream().sorted((z1, z2) -> Integer.compare(z2.tezina, z1.tezina)).forEach(System.out::println);

        // d
        int suma = grupa1.stream().filter(z -> z.hrana.equals(OmiljenaHrana.MRKVA) && z.tezina % 3 == 0).mapToInt(z -> z.tezina).sum();
        System.out.println("Suma tezina " + suma);

        // e
        System.out.print("Zec min tezine ");
        grupa1.stream().min(Comparator.comparing(z -> z.tezina)).ifPresent(System.out::println);
        System.out.print("Zec max tezine ");
        grupa1.stream().max(Comparator.comparing(z -> z.tezina)).ifPresent(System.out::println);
        System.out.print("Zecevi avg tezine ");
        int avg = (int) grupa1.stream().mapToInt(z -> z.tezina).average().getAsDouble();
        grupa1.stream().filter(z -> z.tezina == avg).forEach(System.out::println);
    }

    public static void generisiZeceve() {
        for (int i = 0; i < 30; i++) {
            Zec z = new Zec(rand.nextInt(20) + 2000, "Zec" + i, rand.nextInt(7) + 6, OmiljenaHrana.values()[rand.nextInt(3)]);
            Zec z2 = new Zec(rand.nextInt(20) + 2000, "Zec" + i, rand.nextInt(7) + 6, OmiljenaHrana.values()[rand.nextInt(3)]);
            grupa1.add(z);
            grupa2.add(z2);
        }
    }
}