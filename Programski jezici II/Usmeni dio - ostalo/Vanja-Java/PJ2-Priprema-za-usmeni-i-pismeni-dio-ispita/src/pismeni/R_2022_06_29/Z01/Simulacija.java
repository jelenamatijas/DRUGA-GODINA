package pismeni.R_2022_06_29.Z01;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Simulacija {

    static final int DIM = 50;
    static Object[][] mapa = new Object[DIM][DIM];
    static Random rand = new Random();
    static BlockingQueue<String> poruke = new LinkedBlockingQueue<String>();
    static int brLetjelica = 0;
    static HashMap<String, Letjelica> letjelice = new HashMap<>();
    //static ArrayList<Integer> redovi = new ArrayList<>();

    public static void main(String[] args) {

        KontrolaLeta kontrola = new KontrolaLeta();
        kontrola.start();

        try {
            while (true) {
                generisiLetjelicu();
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void generisiLetjelicu() {
        int opcija = brLetjelica % 7;
        Letjelica l = null;
        switch (opcija) {
            case 0:
                l = new PutnickiAvion();
                break;
            case 1:
                l = new VojniAvion();
                break;
            case 2:
                l = new TeretniAvion();
                break;
            case 3:
                l = new VojniHelikopter();
                break;
            case 4:
                l = new PutnickiHelikopter();
                break;
            case 5:
                l = new CivilniDron();
                break;
            case 6:
                l = new VojniDron();
                break;
        }
        brLetjelica++;
        letjelice.put(l.oznaka, l);
        l.red = rand.nextInt(DIM);
        l.start();
    }
}