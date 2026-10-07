package pismeni.R_2022_01_26.Z2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);
    static int broj = 1;

    public static void main(String[] args) {

        try {
            String input = "";
            while (!"STOP".equals(input)) {
                System.out.print("Unesite naziv fajla: ");
                input = scan.nextLine();
                if (!"STOP".equals(input)) {
                    GetContent gc = new GetContent(input);
                    gc.join();
                }
            }

            new File("results").mkdir();
            String result = "." + File.separator + "results" + File.separator;


            Character.mapa.entrySet().stream().sorted((e1, e2) -> e2.getValue() - e1.getValue()).forEach((entry) -> {
                try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(result + broj + ".txt")))) {
                    pw.println(entry.getKey());
                    pw.println(entry.getValue());
                    broj++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}