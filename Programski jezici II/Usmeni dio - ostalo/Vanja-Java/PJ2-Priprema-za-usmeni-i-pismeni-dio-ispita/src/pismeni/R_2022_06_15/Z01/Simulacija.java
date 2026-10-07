package pismeni.R_2022_06_15.Z01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Simulacija extends Thread {

    // dijeljeni buffer privatnih poruka svih radnika
    public static BlockingQueue<Poruka> poruke = new LinkedBlockingQueue<>();

    // javne poruke se ispisuju na teleekran
    public static BlockingQueue<Poruka> telePoruke = new LinkedBlockingQueue<>();
    public static HashMap<String, String> zamjena = new HashMap<>();
    public static ArrayList<Radnik> radnici = new ArrayList<>();
    public static boolean ZAUSTAVI = false;

    private static boolean PAUZA = false;


    // Big Brother
    @Override
    public void run() {
        // najmanje 7 parova riječi
        zamjena.put("ljubav", "mržnja");
        zamjena.put("sloboda", "nadzor");
        zamjena.put("neznanje", "moć");
        zamjena.put("sreća", "nesreća");
        zamjena.put("prelijepo", "jadno");
        zamjena.put("priroda", "fabrika");
        zamjena.put("uživanje", "rad");

        // 1. kreira se proizvoljan broj radnika
        Radnik radnik1 = new Radnik("Mihajlo", "Mihajlović", "123", 123);
        Radnik radnik2 = new Radnik("Katarina", "Katani", "5000", 5000);
        radnici.add(radnik1);
        radnici.add(radnik2);

        // kreira se 1 nadzornik i prepravljač
        Nadzornik nadzornik = new Nadzornik("Aksentije", "Moćni", "666");
        Prepravljač prepravljač = new Prepravljač("Editor", "Edit", "56895");

        radnik1.start();
        radnik2.start();
        nadzornik.start();
        prepravljač.start();

        String input;
        long startTime = System.currentTimeMillis();
        while (!ZAUSTAVI) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Nisam zaustavljena");
        }
        System.out.println("Zaustavila sam se");
        long endTime = System.currentTimeMillis();
        for (Radnik radnik : radnici) {
            System.out.println(radnik);
        }
        System.out.println("Vrijeme trajanja simulacije: " + (endTime - startTime) + " ms");
    }
}