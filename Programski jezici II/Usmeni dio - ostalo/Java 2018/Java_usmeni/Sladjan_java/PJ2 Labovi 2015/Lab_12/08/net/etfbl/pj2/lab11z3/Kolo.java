package net.etfbl.pj2.lab11z3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Igor
 */
public class Kolo {

    public ArrayList<Tiket> tiketi = new ArrayList<>();

    private ArrayList<Integer> dobitniBrojevi = new ArrayList<>();

    public void provjeraDobitka(int id, int kolo) {
        int index = tiketi.indexOf(new Tiket(id));

        if (index >= 0) {
            Tiket t = (Tiket) tiketi.get(index);
            System.out.println("Imali ste: " + t.getBrojPogodjenihBrojeva() + " pogodaka!");
            System.out.println(t);
            System.out.println("Dobitni brojevi u kolu: " + Loto.kola.get(kolo).dobitniBrojevi);
        } else {
            System.out.println("Tiket sa brojem " + id + " nije odigran u ovom kolu!");
        }
    }

    public void odigrajTiket() {
        Tiket t = new Tiket();
        tiketi.add(t);
        System.out.println("Tiket uplacen!");
    }

    public void izvlacenjeBrojeva() {
        Random rand = new Random();
        while (dobitniBrojevi.size() < 6) {
            int br = rand.nextInt(45) + 1;
            if (!dobitniBrojevi.contains(br)) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                dobitniBrojevi.add(br);
                System.out.println("Izvucen broj " + br);               

            }

        }

        //azuriranje broja pogodjenih brojeva za svaki tiket
        for (Tiket t : tiketi) {
            int pb = 0;
            for (int br : t.getBrojevi()) {
                if (this.dobitniBrojevi.contains(br)) {
                    pb++;
                }
            }
            t.setBrojPogodjenihBrojeva(pb);
        }
    }

}
