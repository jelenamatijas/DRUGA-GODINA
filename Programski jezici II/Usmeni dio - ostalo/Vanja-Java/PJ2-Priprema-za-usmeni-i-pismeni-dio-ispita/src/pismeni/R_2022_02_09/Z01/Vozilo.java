package pismeni.R_2022_02_09.Z01;

import java.util.Random;

public abstract class Vozilo extends Thread implements Element {

    String id;
    Vozac vozac;
    Motor motor;
    String konfiguracija;

    double koefBrzine = 1;

    static Boolean kraj = false;

    long duration = 0;

    Random rand = new Random();

    public Vozilo(String id, Vozac vozac, Motor motor, String konfig) {
        this.id = id;
        this.vozac = vozac;
        this.motor = motor;
        this.konfiguracija = konfig;
    }

    @Override
    public void run() {
        Element[] polja = Simulacija.polja;
        long start = System.currentTimeMillis();
        for (int i = 0; i < polja.length; i++) {
            if (polja[i] instanceof Klizavo) {
                int mogucnost = ((KlizavoPolje) polja[i]).mogucnost;
                if (rand.nextInt(100) < mogucnost) {
                    System.out.println("Vozilo " + this + " je izletilo sa staze");
                    break;
                }
            } else if (polja[i] instanceof Neravno) {
                try {
                    Thread.sleep(brzina());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else { // obicno polje
                try {
                    Thread.sleep((long) 100.00 / motor.snaga * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // polja[i] = this;
            System.out.println("Krece se " + this.id + " na polju " + polja[i]);
        }
        long end = System.currentTimeMillis();
        duration = end - start;
        Simulacija.redoslijed.add(this + " je zavrsilo kretanje. Vrijeme kretanja " + duration);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s", id, vozac, motor, konfiguracija);
    }

    public long brzina() {
        return (long) (100.00 / motor.snaga * koefBrzine * 1000L);
    }
}