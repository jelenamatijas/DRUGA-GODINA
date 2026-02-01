package pismeni.R_2020_01_30.Z02;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Sakupljac_Watchera {
    static Object lock = new Object();
    static String datoteka;
    static boolean END = false;

    public static void main(String[] args) {
        if (args.length < 1)
            return;
        datoteka = "./" + args[0];
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        (new Aktivator_Watchera()).start();
        while (!"0".equals(input)) {
            synchronized (lock) {
                try (PrintWriter pw = new PrintWriter(new FileWriter(datoteka, true))) {
                    pw.println(input);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            input = scan.nextLine();
        }
        System.out.println("Kraj programa");
        END = true;
    }
}