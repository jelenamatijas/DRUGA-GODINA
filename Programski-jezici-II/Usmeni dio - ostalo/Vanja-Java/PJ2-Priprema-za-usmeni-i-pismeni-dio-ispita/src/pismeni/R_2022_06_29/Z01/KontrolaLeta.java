package pismeni.R_2022_06_29.Z01;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class KontrolaLeta extends Thread {

    public KontrolaLeta() {
        setDaemon(true);
    }

    public void run() {
        try (PrintWriter pw = new PrintWriter(new File("./kontrolaLeta.txt"))) {
            while (true) {
                try {
                    String poruka = Simulacija.poruke.element();
                    String[] params = poruka.split(",");
                    int x = Integer.parseInt(params[1]);
                    int y = Integer.parseInt(params[2]);
                    if ((Simulacija.mapa[x][y]) instanceof Letjelica) {
                        Letjelica l = (Letjelica) Simulacija.mapa[x][y];
                        if (l.transponder == false) {
                            if (new Random().nextInt(100) < 50) {
                                String por = "Letjelica " + l.oznaka + " nema ukljucen transponder i rusi se";
                                System.out.println(por);
                                pw.println(por);
                                l.srusena = true;
                            }
                        }
                    }
                    Thread.sleep(500); // provjeri svakih pola minute
                } catch (Exception e) {
                    continue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}