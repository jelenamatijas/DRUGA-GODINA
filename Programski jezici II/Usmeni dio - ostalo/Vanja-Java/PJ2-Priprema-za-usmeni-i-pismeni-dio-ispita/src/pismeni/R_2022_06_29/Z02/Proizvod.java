package pismeni.R_2022_06_29.Z02;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class Proizvod implements Serializable {

    String id;
    String naziv;
    String opis;
    int cijena;
    Tip tip;
    static int count;

    public Proizvod() {
        id = "ID" + new Date().getTime();
        naziv = "Proizvod" + count++;
        opis = "Opis" + count;
        cijena = new Random().nextInt(100) + 1000;
        tip = (count % 2 == 0) ? Tip.KNJIGA : Tip.SVESKA;
    }

    public Proizvod(String id, String naziv, String opis, int cijena, Tip tip) {
        this.id = id;
        this.naziv = naziv;
        this.cijena = cijena;
        this.opis = opis;
        this.tip = tip;
    }

    @Override
    public String toString() {
        return id + " " + " " + naziv + " " + opis + " " + cijena + " " + tip.toString();
    }
}