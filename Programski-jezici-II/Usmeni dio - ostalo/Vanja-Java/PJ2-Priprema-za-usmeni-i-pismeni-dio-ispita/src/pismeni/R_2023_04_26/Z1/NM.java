package pismeni.R_2023_04_26.Z1;

import java.util.HashMap;
import java.util.Random;

public class NM extends Modul {

    public static HashMap<Prioritet, String> poruke = new HashMap<>();
    int koord;

    public NM(int koord) {
        poruke.put(Prioritet.INFO, "Pomijeranje letjelice");
        poruke.put(Prioritet.UPOZORENJE, "Otkaz motora");
        poruke.put(Prioritet.KRITICNO, "Otkaz modula");
        this.koord = koord;
        setDaemon(true);
    }

    @Override
    public void run() {
        try {
            while (!Main.STOP) {
                for (int i = 0; i < 10; i++) {
                    synchronized (Main.poruke) {
                        Main.poruke.add(new Poruka(Prioritet.INFO, NM.poruke.get(Prioritet.INFO) + " KOORD: " + koord));
                    }
                    koord = new Random().nextInt(100);

                    if (koord == 100) {
                        synchronized (Main.STOP) {
                            Main.STOP = true;
                            break;
                        }
                    }
                    Thread.sleep(1000);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}