package pismeni.R_2021_09_08.Z01;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    static ArrayList<String> folderi = new ArrayList<>();
    static BlockingQueue<Element> podaci = new LinkedBlockingQueue<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.startsWith("IMPORT")) {
                String fajl = input.split("\t")[1]; // WARNING Splitujemo na tabu!!!
                processFile(fajl);
            } else if (input.startsWith("AUTO_IMPORT")) {
                String folder = input.split("\t")[1]; // WARNING Splitujemo na tabu!!!
                AutoImportThread autoImportThread = new AutoImportThread(folder);
                folderi.add(folder);
            } else if ("STATUS".equals(input)) {
                System.out.println("Broj foldera koji se prate " + folderi.size());
                System.out.println("Broj stavki u kolekciji " + podaci.size());
                folderi.forEach(System.out::println);
            } else if ("SAVE".equals(input)) {
                String path = System.getProperty("user.home");
                try (ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream(path + File.separator + "podaci.ser"))) {
                    oos.writeObject(podaci);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void processFile(String fajl) {
        Adapter adapter = null;
        if (fajl.endsWith("csv")) {
            adapter = new CSVAdapter();
        } else if (fajl.endsWith("txt")) {
            adapter = new TXTAdapter();
        }
        if (adapter != null)
            adapter.importData(fajl);
    }
}