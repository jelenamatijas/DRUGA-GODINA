package pismeni.R_2023_04_26.Z1;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    static Boolean STOP = false;
    static Scanner scan = new Scanner(System.in);
    public static PriorityQueue<Poruka> poruke = new PriorityQueue<>();

    public static void main(String[] args) {

        try {
            List<Motor> motori = Stream.generate(Motor::new).limit(4).collect(Collectors.toList());
            Letjelica letjelica = new Letjelica(motori, 0);

            String input = "";
            while (!"STOP".equals(input)) {
                System.out.println("");
                input = scan.nextLine();
            }
            letjelica.join();
            System.out.println("Zavrsena simuilacija.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}