package pismeni.R_2022_06_15.Z02;

import java.io.Serializable;

public class Predmet implements Serializable {
    public String naziv;
    public String id;

    public Predmet(String naziv, String id) {
        this.naziv = naziv;
        this.id = id;
    }

}