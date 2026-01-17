import java.io.*;
import java.util.*;

class Main {
    static Random rand = new Random();
    static Object lock = new Object();
    final static int size = 10;
    static double proizvedenaEnergija[] = new double[size];
    static Rijeka rijeka = new Rijeka();

    public static void main(String []args) {
        Hidrocentrala h = new Hidrocentrala();
        h.start();

        Thread kontrola = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (h.isAlive()) {
                String line = scanner.nextLine();
                if ("STOP".equalsIgnoreCase(line.trim())) {
                    h.radi = false;
                    break;
                }
            }
        });
        kontrola.setDaemon(true); 
        kontrola.start();

        try {
            h.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Kraj simulacije.");
    }
}
