package pismeni.R_2019_10_03.Z02;

public class Cetvrta extends Thread {
    String odgovor, unos;

    public Cetvrta(String odgovor, String unos) {
        this.odgovor = odgovor;
        this.unos = unos;
    }

    public void run() {
        if (odgovor.equals(unos)) {
            System.out.println("Igra završenaaa. pogodili ste riječ");
        } else {
            (new Druga()).start();
        }
    }
}