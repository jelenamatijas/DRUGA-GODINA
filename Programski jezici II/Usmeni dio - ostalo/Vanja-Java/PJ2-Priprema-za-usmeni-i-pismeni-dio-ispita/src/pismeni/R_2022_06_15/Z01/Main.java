package pismeni.R_2022_06_15.Z01;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Simulacija simulacija = new Simulacija();
        simulacija.start();

        while (true) {
            String input = scanner.nextLine();
            if ("ZAUSTAVI".equals(input)) {
                Simulacija.ZAUSTAVI = true;
                break;
            }
        }
        System.out.println("Kraj programa");
    }
}