package net.etfbl.pj2.lab11z3;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Igor
 */
public class Loto {

    public static HashMap<Integer, Kolo> kola = new HashMap<Integer, Kolo>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int option = 1;
        while (option == 1 || option == 2) {
            System.out.println("Unesite 1 za igru, 2 za provjeru dobitka a ostalo za kraj:");
            option = scan.nextInt();

            if (option == 1) {
                System.out.println("Kolo " + kola.size());
                Kolo k = new Kolo();
                System.out.println("Unesite broj tiketa koje zelite odigrati:");
                int brTiketa = scan.nextInt();
                for (int i = 0; i < brTiketa; i++) {
                    k.odigrajTiket();
                }
                k.izvlacenjeBrojeva();
                kola.put(kola.size(), k);
            } else if (option == 2) {
                System.out.println("Unesi broj kola");
                int kolo = scan.nextInt();
                System.out.println("Unesi sifru tiketa za provjeru");
                int sifra = scan.nextInt();
                if (kolo < kola.size()) {
                    kola.get(kolo).provjeraDobitka(sifra, kolo);
                } else {
                    System.out.println("Kolo nije odigrano!");
                }
            }

        }

    }

}
