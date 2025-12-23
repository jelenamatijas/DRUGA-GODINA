package fabrika;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Igor
 */
public class ProizvodnaTraka extends Thread {

   public static final String PROIZVODI = "proizvodi.txt";
   
    public int broj;
    public int brzinaRada;

    public ProizvodnaTraka() {
    }

    public ProizvodnaTraka(int broj, int brzinaRada) {
        this.broj = broj;
        this.brzinaRada = brzinaRada;
    }

    @Override
    public void run() {
        Random rand = new Random();
        try {
            System.out.println("Proizvodna traka " + broj + " pocinje sa radom brzinom od " + brzinaRada);
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("Napravljen " + i + " proizvod na traci " + broj);
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Fabrika.DIR_PROIZVODI+"proizvod" + broj + i));
                    Proizvod p = new Proizvod("Proizvod");
                    p.serijskiBroj = broj + "a" + new Date().getTime();
                    p.imaGresku = rand.nextInt(100) < 8;
                    out.writeObject(p);
                    out.close();

                    //azuriranje stanja u fajlu
                    synchronized (this) {
                        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(PROIZVODI, true)));
                        pw.println("Traka " + broj + " je napravila proizvod " + p.serijskiBroj);
                        pw.close();
                    }
                    sleep(brzinaRada);

                    //zastoj
                    if (rand.nextInt(100) > 90) {
                        System.out.println("Zastoj na traci broj " + broj);
                        sleep(5000);
                    }
                    //otkaz
                    if (rand.nextInt(100) > 95) {
                        System.out.println("Prekid rada na traci broj " + broj);
                        throw new Exception("Prekid rada " + broj + " trake!");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();

                }
            }

            System.out.println("Traka " + broj + " je zavrsila sa radom.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
