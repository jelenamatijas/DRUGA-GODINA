package net.etfbl.pj2;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author igor
 */
public class Simulacija {

    private static Random rand = new Random();
    public static Object[][] mapa = new Object[3][20];

    public static void postavljanjePrepreka(String vrsta) {
        int brojPrepreka = 4;
        while (brojPrepreka > 0) {
            int x = rand.nextInt(3);
            int y = rand.nextInt(20);
            if (mapa[x][y] == null) {
                brojPrepreka--;
                mapa[x][y] = new Prepreka(vrsta);
            }
        }
    }

    private static void printMap() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        //postavljanje prepreka
        postavljanjePrepreka(Prepreka.STIJENA);
        postavljanjePrepreka(Prepreka.VATRA);
        postavljanjePrepreka(Prepreka.VODA);
        printMap();
        //osobe
        Plivac pl = new Plivac("ime1", "prezime1", "neka oprema", 0);
        Planinar pn = new Planinar("ime planinara", "prezime planinara", "neka oprema", 1);
        Vatrogasac vt = new Vatrogasac("ime vatrogasca", "prezime vatrogasca", "Oprema", 2);

        Scanner scan = new Scanner(System.in);
        String opcija = "";
        pl.start();
        pn.start();
        vt.start();
        while (!"END".equals(opcija)) {
            opcija = scan.nextLine();
            if ("PAUZA".equals(opcija)) {
                pl.pause = true;
                pn.pause = true;
                vt.pause = true;
            }
            if ("NASTAVAK".equals(opcija)) {
                pl.pause = false;
                pn.pause = false;
                vt.pause = false;
                try {
                    synchronized (pl) {
                        pl.notify();
                    }
                    synchronized (pn) {
                        pn.notify();
                    }
                    synchronized (vt) {
                        vt.notify();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            pl.join();
            pn.join();
            vt.join();            
        } catch (Exception e) {
            e.printStackTrace();
        }
        printMap();

    }
}
