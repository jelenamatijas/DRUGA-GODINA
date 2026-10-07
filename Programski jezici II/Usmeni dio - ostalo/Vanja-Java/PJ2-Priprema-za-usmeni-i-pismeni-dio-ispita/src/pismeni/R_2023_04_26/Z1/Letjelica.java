package pismeni.R_2023_04_26.Z1;

import java.util.List;

public class Letjelica extends Thread {

    PM pm;
    NM nm;
    KM km;

    public Letjelica(List<Motor> motori, int koord) {
        init(motori, koord);
        start();
    }

    @Override
    public void run() {
        pm.start();
        nm.start();
        km.start();
    }

    private void init(List<Motor> motori, int koord) {
        pm = new PM(motori);
        nm = new NM(koord);
        km = new KM();
    }
}