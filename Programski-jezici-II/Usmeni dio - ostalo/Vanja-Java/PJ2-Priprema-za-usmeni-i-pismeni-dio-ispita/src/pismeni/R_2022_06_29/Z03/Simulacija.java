package pismeni.R_2022_06_29.Z03;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Simulacija {

    static Boolean END = false;
    static Red red = new Red();

    public static void main(String[] args) throws FileNotFoundException {

        if (args.length < 1) {
            return;
        }
        Long postotak = 0L;
        try {
            postotak = Long.parseLong(args[0]);

            if (postotak < 0 || postotak > 100)
                return;
        } catch (Exception e) {
            return;
        }
        AddThread t1 = new AddThread(postotak);
        RemoveThread t2 = new RemoveThread(100 - postotak);

        // Simulacija traje 1 minut
        try {
            Thread.sleep(60_000); // 1 min
        } catch (Exception e) {
            e.printStackTrace();
        }

        END = true;
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Ispisivanje preostalih studenta iz reda u fajl
        String fajl = "src/pismeni.R_2022_06_29/Z03/fajl.txt";
        PrintWriter pw = new PrintWriter(fajl);
        red.studenti.forEach(pw::println);
    }

}